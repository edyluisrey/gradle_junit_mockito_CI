package dao;

import java.util.List;

public class User {

	private String id;
	private String firstName;
	private String lastName;
	private String address;
	private List<Object> roles;
	
	
	public User(String id, String firstName, String lastName, String address,
			List<Object> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.roles = roles;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public List<Object> getRoles() {
		return roles;
	}


	public void setRoles(List<Object> roles) {
		this.roles = roles;
	}

}