package ssmtk_t3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.DBConnection;

public class ClientHandler {
	
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
	
	//Izmjena podataka klijenta
	public void updateData(ClientUser client) {  
		  
		  String sql = "UPDATE ssmkdb.clients SET Money = ? WHERE Id = ?";
		  Connection conn;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setFloat(1, client.getMoney());
			stmt.setFloat(2, client.getId());

			int rs = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
}
