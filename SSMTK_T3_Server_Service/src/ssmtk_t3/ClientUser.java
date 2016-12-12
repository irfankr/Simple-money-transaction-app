package ssmtk_t3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "client")
public class ClientUser {
	private int Id;
	private String Username;
	private String Password;
	private float Money;
	private int Mobile;
	private String Email;
	
	@XmlElement
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	@XmlElement
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	
	@XmlElement
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	@XmlElement
	public float getMoney() {
		return Money;
	}
	public void setMoney(float money) {
		Money = money;
	}
	@XmlElement
	public int getMobile() {
		return Mobile;
	}
	public void setMobile(int mobile) {
		Mobile = mobile;
	}
	
	@XmlElement
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
}
