package controller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "transaction")
public class Transaction {
	int Id;
	int User_id;
	String Status;
	String Description; //Uplata, Isplata, Transfer
	int Code;
	int Reciever_User_Id;
	float Money;
	
	@XmlElement
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	@XmlElement
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	
	@XmlElement
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	@XmlElement
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	@XmlElement
	public int getCode() {
		return Code;
	}
	public void setCode(int code) {
		Code = code;
	}
	
	@XmlElement
	public int getReciever_User_Id() {
		return Reciever_User_Id;
	}
	public void setReciever_User_Id(int id) {
		Reciever_User_Id = id;
	}
	
	@XmlElement
	public float getMoney() {
		return Money;
	}
	public void setMoney(float money) {
		Money = money;
	}
}