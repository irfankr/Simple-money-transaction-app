package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;

import dao.DBConnection;

/**
 * LoginHandler klasa - upravljajne akcijama nad tabelom clients
 * 
 * Metode: checkLogin, getLogin, getAllClientUsers, getClientUser
 * 
 */
public class LoginHandler {
	/*
	 * Metoda za provjeru ispravnosti unešenog username-a i passworda. Ukoliko u
	 * bazi ne postoji ni jedan red sa odgovarajuÄ‡im username-om vrati false
	 * Ukoliko je pronašao usera i ukoliko unešeni password odgovara passwordu
	 * u bazi, vrati true
	 */
	public boolean checkLogin(String Username, String Password) {		
		boolean l = false;
		try {
			ClientUser lB = new ClientUser();
			lB = getLogin(Username);
			
			if (lB != null) {
				if (lB.getPassword().equals(Password))
					l = true;
			}
		} catch (Exception e) {
			l = false;
			System.out.println("Check login: " + e.toString());
		}

		return l;
	}

	public ClientUser getLogin(String Username) {
		ClientUser client = new ClientUser();
		try {
			Connection conn = DBConnection.getConnection();
			System.out.println("Pretrazivanje baze za Username=" + Username);
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM ssmkdb.clients WHERE Username = ?");
			System.out.println(Username);
			stmt.setString(1, Username);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				//Podesavamo parametre objekta client nakon citanja iz baze
				client.setId(rs.getInt("Id"));
				client.setUsername(rs.getString("Username"));
				client.setPassword(rs.getString("Password"));
				
				return client;
			}

		} catch (Exception e) {
			System.out.println("Nije uspjela konekcija prema bazi podataka: "
					+ e.toString());
		}

		return null;
	}

	/*
	 * Metoda vraća listu svih klijenata u tabeli clients
	 */
	public ArrayList<ClientUser> getAllClientUsers() {
		ArrayList<ClientUser> result = new ArrayList<ClientUser>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM ssmkdb.clients");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClientUser d = new ClientUser();

				d.setId(rs.getInt("Id"));
				d.setUsername(rs.getString("Username"));
				d.setPassword(rs.getString("Password"));
				d.setMoney(rs.getFloat("Money"));

				result.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Metoda vraća određenog usera za vrijednost Id kolone
	 */
	public ClientUser getClientUser(int Id) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM ssmkdb.clients where Id=?");
			stmt.setInt(1, Id); //Ova komanda postavlja da je prvi ? u sql upitu iznad vrijednost Id koji metoda prima
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClientUser d = new ClientUser();
				
				d.setId(rs.getInt("Id"));
				d.setUsername(rs.getString("Username"));
				d.setPassword(rs.getString("Password"));
				d.setMoney(rs.getFloat("Money"));
				
				return d;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
}
