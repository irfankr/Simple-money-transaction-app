package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;

import dao.DBConnection;

public class ClientHandler {
	
	/*
	 * Metoda vraca listu svih klijenata u bazi
	 */
	public ArrayList<ClientUser> getAllClients() {
		ArrayList<ClientUser> result = new ArrayList<ClientUser>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.clients");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				ClientUser d = new ClientUser();
				d.setId(rs.getInt("Id"));
				d.setUsername(rs.getString("Username"));
				d.setPassword(rs.getString("Password"));
				d.setMoney(rs.getFloat("Money"));
				d.setEmail(rs.getString("Email"));
				d.setMobile(rs.getInt("Mobile"));
				result.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * Metoda vraca klijenta za proslijedjeni Id
	 */
	public ClientUser getUser(int id) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.clients where Id=?");
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ClientUser d = new ClientUser();
				
				d.setId(id);
				d.setUsername(rs.getString("Username"));
				d.setPassword(rs.getString("Password"));
				d.setMoney(rs.getFloat("Money"));
				d.setMobile(rs.getInt("Mobile"));
				d.setEmail(rs.getString("Email"));

				return d;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
	
	/*
	 * Metoda za dodavanje novog usera u tabelu
	 */
	public void insertData(ClientUser client) {  
		  
		  String sql = "INSERT INTO ssmkdb.clients "  
		    + "(Id, Username, Password, Money, Mobile, Email) VALUES (?, ?, ?, ?, ?, ?)";  
		  Connection conn;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, client.getId());
			stmt.setString(2, client.getUsername());
			stmt.setString(3, client.getPassword());
			stmt.setFloat(4, client.getMoney());
			stmt.setInt(5, client.getMobile());
			stmt.setString(6, client.getEmail());
			
			int rs = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  
	
	/*
	 * Metoda za izmjenu klijenata Id kolone
	 */
	public void updateData(ClientUser client) {  
		  
		  String sql = "UPDATE ssmkdb.clients SET Username = ?, Password = ?, Money = ?, Mobile = ?, Email = ? WHERE Id = ?";
		  Connection conn;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, client.getUsername());
			stmt.setString(2, client.getPassword());
			stmt.setFloat(3, client.getMoney());
			stmt.setInt(4, client.getMobile());
			stmt.setString(5, client.getEmail());
			stmt.setInt(6, client.getId());
			
			//System.out.println(client.getMobile());
			System.out.println(client.getEmail());

			int rs = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Brisanje klijenta
	 */
	public void deleteData(ClientUser client){
		String sql = "DELETE FROM ssmkdb.clients WHERE Id = ?";
		Connection conn;
		
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, client.getId());
			boolean rs = stmt.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Metoda vraca Transakciju za proslijedjeni kod (Code)
	 */
	public Transaction getTransaction(int code) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.transaction where Code = ?");
			
			stmt.setInt(1, code);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Transaction d = new Transaction();
				
				d.setId(rs.getInt("Id"));
				d.setUser_id(rs.getInt("User_id"));
				d.setStatus(rs.getString("Status"));
				d.setDescription(rs.getString("Description"));
				d.setReciever_User_Id(rs.getInt("Reciever_User_Id"));
				d.setMoney(rs.getFloat("Money"));

				return d;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
	 
}
