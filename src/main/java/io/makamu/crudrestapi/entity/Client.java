package io.makamu.crudrestapi.entity;


import javax.persistence.*;

@Entity
@Table(name="CLIENTS")
public class Client {
	
	@Id
	@GeneratedValue
	private int clientId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String idNumber;
	private String physicalAddress;
		
	public Client() {
	}
	
	public Client(String firstName, String lastName, String mobileNumber, String idNumber,
			String physicalAddress) {
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.idNumber = idNumber;
		this.physicalAddress = physicalAddress;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	
	
	

}
