package com.bankapp.model;

public class Employee {
	private String firstName;
	private String lastName;
	private String employee_id;
	private String username;
	private String password;
	
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
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Employee(String firstName, String lastName, String employee_id, String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.employee_id = employee_id;
		this.username = username;
	}
	public Employee() {
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Employee(String firstName, String lastName, String employee_id, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.employee_id = employee_id;
		this.username = username;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", employee_id=" + employee_id
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
	


	
	
	

}
