package com.bankapp.model;

public class Customer {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private int customer_id;
	private String city;
	private int age;
	private String gender;
	private String customerStatus;
	private int user_id;
	
	public Customer(String firstName, String lastName, String email, String phoneNumber, int customer_id, String city,
			int age, String gender, String customerStatus) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.customer_id = customer_id;
		this.city = city;
		this.age = age;
		this.gender = gender;
		this.customerStatus = customerStatus;
	}

	public Customer(String firstName, String lastName, String email, String phoneNumber, int customer_id, String city,
			int age, String gender, String customerStatus, int user_id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.customer_id = customer_id;
		this.city = city;
		this.age = age;
		this.gender = gender;
		this.customerStatus = customerStatus;
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public Customer(String firstName, String lastName, String email, String phoneNumber, String city, int age,
			String gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.age = age;
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Customer(String firstName, String lastName, String email, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", customer_id=" + customer_id + ", city=" + city + ", age=" + age + ", gender="
				+ gender + "]";
	}
	
	
	

}
