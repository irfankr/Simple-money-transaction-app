package ssmtk_t3;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;







import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@Path("Server_Service")
public class Server_Service {

	//Ova metoda se poziva ukoliko se vrsi uplata ili isplata
		@GET
		// filepath do metode i definiranje ulaznog parametra
		@Path("/MakeTransaction/{idkorisnika}/{novac}/{tiptransakcije}")
		// Definiranje izlaznog tipa
		@Produces(MediaType.APPLICATION_JSON)
		public Transaction MakeTransaction(@PathParam("idkorisnika") String idkorisnika, @PathParam("novac") String novac, @PathParam("tiptransakcije") String tiptransakcije) {
		System.out.println("ID korisnika: " + Integer.parseInt(idkorisnika));
		System.out.println("Novac: " + Integer.parseInt(novac));
		System.out.println("Tip transakcije: " + tiptransakcije);
		
		//Kreiramo objekat transakcije
		Transaction transakcija = new Transaction();
		transakcija.setDescription(tiptransakcije);
			float f = Float.parseFloat(novac);
		transakcija.setMoney(f);
		transakcija.setUser_id(Integer.parseInt(idkorisnika));
		
		//Generisemo kod transakcije
		int randomNum = 1000000 + (int)(Math.random() * 9999999);
		transakcija.setCode(randomNum);
		
		//Kreiramo objekat korisnika
		ClientHandler clienthandler = new ClientHandler();
		ClientUser clientuser = new ClientUser();
		clientuser = clienthandler.getUser(Integer.parseInt(idkorisnika));
		
		if(tiptransakcije.equals("Isplata")){ //Ukoliko je isplata u pitanju, potrebno je prvo provjeriti da li klijent ima dovoljno novca
			
			if(clientuser.getMoney() < Float.parseFloat(novac)){
				//Ako je novac koji ima korisnik manji od novca koji se trazi za isplatu onda je transakcija neuspjela
				transakcija.setStatus("Neuspjesno");
			} else {
				//Ako korisnik ima dovoljno novca na racunu za isplatu
				transakcija.setStatus("Uspjesno");
				
				//Vrismo modifikaciju objekta klijenta
				clientuser.setMoney(clientuser.getMoney() - f);
				
				//Updatujemo bazu podtaka
				clienthandler.updateData(clientuser);
			}
		} else if(tiptransakcije.equals("Uplata")){ //Uplata
			//Ako korisnik ima dovoljno novca na racunu za isplatu
			transakcija.setStatus("Uspjesno");
			
			//Vrismo modifikaciju objekta klijenta
			clientuser.setMoney(clientuser.getMoney() + f);
			
			//Updatujemo bazu podtaka
			clienthandler.updateData(clientuser);
		}
		
		//Upisujemo informacije o transakciji u bazu
		InsertTransactionInDatabase(transakcija);
		
		//Ukoliko je transakcija uspjesna poslati SMS i EMAIL notifikacije
		//Notifikacija ce sadrzati kod transakcije
		if(transakcija.getStatus().equals("Uspjesno")){
			//Generisemo tekst SMS poruke koja se salje
			String sms_saljemo = "Transakcija je uspjesno izvrsena. Kod transakcije je: " + transakcija.getCode();
			
			//Saljemo SMS
			SendSms sms_objekat = new SendSms();
			System.out.println(String.valueOf(clientuser.getMobile()));
			
			//Zakomentarisano zbog nepostojanja ParlayXSmsAccess aktivnog
			//sms_objekat.sendSms("tel:1106", String.valueOf(clientuser.getMobile()), sms_saljemo);
			
			System.out.println("Poslati notifikaciju");
			
		}
		
		return transakcija;
	}
	
		//Ova metoda se poziva ukoliko se izvrsava transakcija
		@GET
		// filepath do metode i definiranje ulaznog parametra
		@Path("/MakeTransaction/{idkorisnika}/{novac}/{tiptransakcije}/{idkorisnikaprijem}")
		// Definiranje izlaznog tipa
		@Produces(MediaType.APPLICATION_JSON)
		public Transaction MakeTransaction(@PathParam("idkorisnika") String idkorisnika, @PathParam("novac") String novac, @PathParam("tiptransakcije") String tiptransakcije, @PathParam("idkorisnikaprijem") String idkorisnikaprijem) {
		System.out.println("ID korisnika: " + Integer.parseInt(idkorisnika));
		System.out.println("Novac: " + Integer.parseInt(novac));
		System.out.println("Tip transakcije: " + tiptransakcije);
		
		//Kreiramo objekat transakcije
		Transaction transakcija = new Transaction();
		transakcija.setDescription(tiptransakcije);
			float f = Float.parseFloat(novac);
		transakcija.setMoney(f);
		transakcija.setUser_id(Integer.parseInt(idkorisnika));
		transakcija.setReciever_User_Id(Integer.parseInt(idkorisnikaprijem));
		
		//Generisemo kod transakcije
		int randomNum = 1000000 + (int)(Math.random() * 9999999);
		transakcija.setCode(randomNum);
		
		//Kreiramo objekat korisnika koji salje
		ClientHandler clienthandler = new ClientHandler();
		ClientUser clientuser = new ClientUser();
		clientuser = clienthandler.getUser(Integer.parseInt(idkorisnika));
		
		//Kreiramo objekat korisnika koji prima
		ClientUser clientuserprima = new ClientUser();
		clientuserprima = clienthandler.getUser(Integer.parseInt(idkorisnikaprijem));
		
		if(tiptransakcije.equals("Transfer")){
			//Provjera da li korisnik ima dovoljno novca da izvrsi transfer sredstava
			if(clientuser.getMoney() < Float.parseFloat(novac)){
				//Ako je novac koji ima korisnik manji od novca koji se trazi za isplatu onda je transakcija neuspjela
				transakcija.setStatus("Neuspjesno");
			} else {
				//Ako korisnik ima dovoljno novca na racunu za isplatu
				transakcija.setStatus("Uspjesno");
				
				//Vrismo modifikaciju objekta klijenta
				clientuser.setMoney(clientuser.getMoney() - f);
				clientuserprima.setMoney(clientuserprima.getMoney() + f);
				
				//Updatujemo bazu podtaka klijenta koji zahtjeva transakciju
				clienthandler.updateData(clientuser);
				
				//Updatujemo bazu podtaka klijenta koji prima transakciju
				clienthandler.updateData(clientuserprima);
			}
		} 
		
		//Upisujemo informacije o transakciji u bazu
		InsertTransactionInDatabase(transakcija);
		
		
		
		return transakcija;
	}
		
	// Definiramo tip metode – GET – korisnik može samo dobaviti podatke
	@GET
	// filepath do metode i definiranje ulaznog parametra
	@Path("/ReturnClientStatus/{id}")
	// Definiranje izlaznog tipa
	@Produces(MediaType.APPLICATION_JSON)
	// Metoda prima ulazni parametar tipa String, a vraća klasu Student u XML
	// formatu
	public ClientUser GetClientStatus(@PathParam("id") String id) {
		System.out.println("Pozivam klijenta da mi vrati koliko imam para");
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.clients WHERE Id = ?");
			stmt.setInt(1, Integer.parseInt(id));

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ClientUser client = new ClientUser();
				client.setId(rs.getInt("Id"));
				client.setMoney(rs.getFloat("Money"));
				return client;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Definiramo tip metode – GET – korisnik može samo dobaviti podatke
	@GET
	// filepath do metode i definiranje ulaznog parametra
	@Path("/GetClientTransactions/{id}")
	// Definiranje izlaznog tipa
	@Produces(MediaType.APPLICATION_JSON)
	// Metoda prima ulazni parametar tipa String, a vraća klasu Student u XML
	// formatu
	public ArrayList<Transaction> GetClientTransactions(@PathParam("id") String id) {
		System.out.println("Pozivam klijenta da mi vrati transakcije");
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.transaction WHERE User_id = ?");
			stmt.setInt(1, Integer.parseInt(id));
			
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Transaction> transaction_return = new ArrayList<Transaction>();
			
			while(rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setCode(rs.getInt("Code"));
				transaction.setDescription(rs.getString("Description"));
				transaction.setStatus(rs.getString("Status"));
				transaction.setMoney(rs.getFloat("Money"));
				
				transaction_return.add(transaction);
			}
			
			return transaction_return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Definiramo tip metode – GET – korisnik može samo dobaviti podatke
	@GET
	// filepath do metode i definiranje ulaznog parametra
	@Path("/ReturnTransactionStatus/{code}")
	// Definiranje izlaznog tipa
	@Produces(MediaType.APPLICATION_JSON)
	// Metoda prima ulazni parametar tipa String, a vraća klasu Student u XML
	// formatu
	public Transaction GetTransactionStatus(@PathParam("code") String code) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.transaction WHERE Code = ?");
			stmt.setInt(1, Integer.parseInt(code));

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setCode(rs.getInt("Code"));
				transaction.setDescription(rs.getString("Description"));
				transaction.setStatus(rs.getString("Status"));
				
				return transaction;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void InsertTransactionInDatabase(Transaction transaction){
		String sql = "INSERT INTO transaction (User_id, Status, Description, Money, Code, Reciever_User_Id) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction.getUser_id());
			stmt.setString(2, transaction.getStatus());
			stmt.setString(3, transaction.getDescription());
			stmt.setFloat(4, transaction.getMoney());
			stmt.setInt(5, transaction.getCode());
			stmt.setInt(6, transaction.getReciever_User_Id());
			
			int rs = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
