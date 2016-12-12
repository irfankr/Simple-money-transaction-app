package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import dao.DBConnection;

@Controller
public class MainController {

	@RequestMapping(value = "/")
	public ModelAndView ServerHomepage(HttpServletResponse response)
			throws IOException {
		// Metoda kreira ModelAndView nad home.jsp stranicom
		ModelAndView modelandview = new ModelAndView("home");
		return modelandview;
	}

	@RequestMapping(value = "/PregledTransakcija")
	public ModelAndView PregledTransakcija(HttpServletResponse response)
			throws IOException {
		ModelAndView model = null;
		try {
			// redirect na stranicu transakcije.jsp
			model = new ModelAndView("pregledtransakcija");
			try {
				//Popuni stranicu transakcije.jsp sa svim transakcijama sa ovog terminala
				ArrayList<Transaction> result = new ArrayList<Transaction>();
				try {
					Connection conn = DBConnection.getConnection();
					PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.transaction ORDER BY Id DESC");
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						Transaction d = new Transaction();
						d.setCode(rs.getInt("Code"));
						d.setDescription(rs.getString("Description"));
						d.setStatus(rs.getString("Status"));
						result.add(d);
					}
					
					model.addObject("transactionList", result);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				System.out.println("Error: " + e.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/StatistikaTransakcija")
	public ModelAndView StatistikaTransakcija(HttpServletResponse response)
			throws IOException {
		
		ArrayList<ClientTransactionStatistic> result = new ArrayList<ClientTransactionStatistic>();
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("SELECT *, COUNT(ssmkdb.clients.Id) AS broj_transakcija FROM ssmkdb.clients, ssmkdb.transaction WHERE ssmkdb.clients.Id = ssmkdb.transaction.User_id GROUP BY ssmkdb.transaction.User_id ORDER BY broj_transakcija DESC");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClientTransactionStatistic d = new ClientTransactionStatistic();

				d.setId(rs.getInt("clients.Id"));
				d.setUsername(rs.getString("clients.Username"));
				d.setTransactionNumber(rs.getInt("broj_transakcija"));

				result.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView modelandview = new ModelAndView("statistikatransakcija");
		modelandview.addObject("clientslist", result);
		return modelandview;
	}

	@RequestMapping(value = "/ProvjeraUslova")
	public ModelAndView ProvjeraUslova(HttpServletResponse response, @ModelAttribute("transakcijaprovjera") Transaction transaction)
			throws IOException {
		ModelAndView modelandview = new ModelAndView("provjerauslova");

		return modelandview;
	}
		@RequestMapping("/provjeriuslovzatransakciju")
		public ModelAndView provjeriuslovzatransakciju(@ModelAttribute("transakcijaprovjera") Transaction transaction) {
			// Metoda kreira ModelAndView stranice gdje ce se ipisati status
			ModelAndView modelandview = new ModelAndView("provjerauslovazatransakciju");
			
	
			try {
				Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.clients where Id = ?");
				stmt.setInt(1, transaction.getUser_id());
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					//Definisemo status POTENCIJALNE transakcije
					if(transaction.getMoney() > rs.getFloat("Money")){
						transaction.setStatus("Neuspjesno");
					} else {
						transaction.setStatus("Uspjesno");
					}
					
					//Vracamo transakciju u narednom JSP
					modelandview.addObject("provjerauslovazatransakciju", transaction);
	
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			return modelandview;
		}

	@RequestMapping(value = "/StorniranjeTransakcija")
	public ModelAndView StorniranjeTransakcija(HttpServletResponse response, @ModelAttribute("transakcijastorniranje") Transaction transaction)
			throws IOException {
		// Metoda kreira ModelAndView nad home.jsp stranicom
		ModelAndView modelandview = new ModelAndView("storniranjetransakcija");

		return modelandview;
	}
		@RequestMapping("/stornirajtransakciju")
		public ModelAndView stornirajtransakciju(@ModelAttribute("transakcijastorniranje") Transaction transaction) {
			// Metoda kreira ModelAndView stranice gdje ce se ipisati status
			ModelAndView modelandview = new ModelAndView("storniranjetransakcijaizvrseno");
			
			//Kreiramo objekat transakcije na osnovu proslijedjenog parametra
			ClientHandler ch = new ClientHandler();
			Transaction transakcija_stornirati = ch.getTransaction(transaction.getCode());
			transakcija_stornirati.setStatus("Neuspjesno"); //Default vrijednost
				if(transakcija_stornirati.getDescription().equalsIgnoreCase("Uplata")){ //Ukoliko je u pitanju bila uplata smanjujemo vrijednost novca na racunu
					//Updateujemo racun
					try {
						String sql = "UPDATE ssmkdb.clients SET Money = Money - ? WHERE Id = ?";
						Connection conn = DBConnection.getConnection();
						conn = DBConnection.getConnection();
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setFloat(1, transakcija_stornirati.getMoney());
						stmt.setInt(2, transakcija_stornirati.getUser_id());
						int rs = stmt.executeUpdate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Brisemo transakciju
					String sql2 = "DELETE FROM ssmkdb.transaction WHERE Code = ?";
					Connection conn2;
					try {
						conn2 = DBConnection.getConnection();
						PreparedStatement stmt2 = conn2.prepareStatement(sql2);
							
						stmt2.setInt(1, transaction.getCode());
						boolean rs2 = stmt2.execute();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					transakcija_stornirati.setStatus("Uspjesno");
				} else if(transakcija_stornirati.getDescription().equalsIgnoreCase("Isplata")){ //Ukoliko je u pitanju bila isuplata povecavamo vrijednost novca na racunu
					//Updateujemo racun
					try {
						String sql = "UPDATE ssmkdb.clients SET Money = Money + ? WHERE Id = ?";
						Connection conn = DBConnection.getConnection();
						conn = DBConnection.getConnection();
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setFloat(1, transakcija_stornirati.getMoney());
						stmt.setInt(2, transakcija_stornirati.getUser_id());
						int rs = stmt.executeUpdate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Brisemo transakciju
					String sql2 = "DELETE FROM ssmkdb.transaction WHERE Code = ?";
					Connection conn2;
					try {
						conn2 = DBConnection.getConnection();
						PreparedStatement stmt2 = conn2.prepareStatement(sql2);
							
						stmt2.setInt(1, transaction.getCode());
						boolean rs2 = stmt2.execute();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					transakcija_stornirati.setStatus("Uspjesno");
				} else if(transakcija_stornirati.getDescription().equalsIgnoreCase("Transfer")){ //Ukoliko je u pitanju transfer prebacujemo novac sa racuna na racun
					//Updateujemo racun posiljaoca (vracamo novac)
					try {
						String sql = "UPDATE ssmkdb.clients SET Money = Money + ? WHERE Id = ?";
						Connection conn = DBConnection.getConnection();
						conn = DBConnection.getConnection();
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setFloat(1, transakcija_stornirati.getMoney());
						stmt.setInt(2, transakcija_stornirati.getUser_id());
						int rs = stmt.executeUpdate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Updateujemo racun primaoca (uzimamo novac)
					try {
						String sql3 = "UPDATE ssmkdb.clients SET Money = Money - ? WHERE Id = ?";
						Connection conn3 = DBConnection.getConnection();
						conn3 = DBConnection.getConnection();
						PreparedStatement stmt3 = conn3.prepareStatement(sql3);
						stmt3.setFloat(1, transakcija_stornirati.getMoney());
						stmt3.setInt(2, transakcija_stornirati.getReciever_User_Id());
						int rs3 = stmt3.executeUpdate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Brisemo transakciju
					String sql2 = "DELETE FROM ssmkdb.transaction WHERE Code = ?";
					Connection conn2;
					try {
						conn2 = DBConnection.getConnection();
						PreparedStatement stmt2 = conn2.prepareStatement(sql2);
							
						stmt2.setInt(1, transaction.getCode());
						boolean rs2 = stmt2.execute();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					transakcija_stornirati.setStatus("Uspjesno");
				}
			
				modelandview.addObject("provjerauslovazatransakciju", transakcija_stornirati);
			return modelandview;
		}

	@RequestMapping(value = "/Korisnici")
	public ModelAndView showListClients(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;

		try {
			// redirect na stranicu UserList.jsp
			model = new ModelAndView("korisnici");
			try {
				//Popuni stranicu sa svim korisnicima iz baze
				ClientHandler ch = new ClientHandler();
				ArrayList<ClientUser> userList = new ArrayList<ClientUser>();
				userList = ch.getAllClients();
				model.addObject("userList", userList);
			} catch (Exception e) {
				System.out.println("Error: " + e.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	
	@RequestMapping("/DodavanjeKorisnika")
	public ModelAndView addUser(@ModelAttribute("ClientUser") ClientUser client) {
		return new ModelAndView("dodavanjekorisnika");
	}
	@RequestMapping("/insert")
	public String inserData(@ModelAttribute ClientUser client) {
		ClientHandler ch = new ClientHandler();
		if (client != null)
			ch.insertData(client);
		return "redirect:/Korisnici";
	}


	@RequestMapping("/IzmjenaKorisnika")
	public ModelAndView editUser(@RequestParam String id,
			@ModelAttribute("ClientUser") ClientUser client) {

		ClientHandler ch = new ClientHandler();
		ClientUser getUserData = ch.getUser(Integer.valueOf(id));

		ModelAndView v = new ModelAndView("izmjenakorisnika");
		v.addObject("clientuserupdate", getUserData);

		return v;
	}
	@RequestMapping("/update")
	public String updateUser(@ModelAttribute("ClientUser") ClientUser client) {
		ClientHandler ch = new ClientHandler();
		
		System.out.println("UPDATE USERA: " + client.getId());
		ch.updateData(client);
		return "redirect:/Korisnici";

	}

	
	@RequestMapping("/delete")
	public String deleteUser(@RequestParam String id,
			@ModelAttribute ClientUser client) {

		ClientHandler ch = new ClientHandler();
		ClientUser getUserData = ch.getUser(Integer.valueOf(id));

		// Brisanje usera iz baze
		ch.deleteData(getUserData);

		return "redirect:/Korisnici";

	}
	
}
