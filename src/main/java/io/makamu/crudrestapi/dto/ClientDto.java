package io.makamu.crudrestapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ClientDto {
	

	@NotNull
	@NotEmpty(message="Client first name field should not be empty/null")
	private String firstName;
	
	@NotNull
	@NotEmpty(message="Client last name field should not be empty/null")
	private String lastName;
	
	private String mobileNumber;
	@NotNull
	@NotEmpty(message="Client id field should not be empty")
	@Pattern(regexp="^\\d{13}?$",message="Client id number is invalid,please enter a valid SA id")
	private String idNumber;
	private String physicalAddress;
	public ClientDto(String firstName, String lastName, String mobileNumber, String idNumber,
			String physicalAddress) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.idNumber = idNumber;
		this.physicalAddress = physicalAddress;
	}
	public ClientDto() {
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
