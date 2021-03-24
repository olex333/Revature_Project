package com.bankapp.user.customer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bankapp.user.customer.service.CustomerValidations;

class CustomerValidationsTest {

	@Test
	void testIsValidUserName() {
		assertEquals(true, CustomerValidations.isValidUserName("dasda223")); 
		assertEquals(true, CustomerValidations.isValidUserName("32132dada223")); ;
		assertEquals(true, CustomerValidations.isValidUserName("223d")) ;
		assertEquals(false, CustomerValidations.isValidUserName("22a3a@")); 
		assertEquals(false, CustomerValidations.isValidUserName("22")); ;
	}

	@Test
	void testIsValidPassword() {
		assertEquals(true, CustomerValidations.isValidPassword("dasda223")); 
		assertEquals(true, CustomerValidations.isValidPassword("32132dada223")); ;
		assertEquals(true, CustomerValidations.isValidPassword("223d")) ;
		assertEquals(false, CustomerValidations.isValidPassword("22a3a@")); 
		assertEquals(false, CustomerValidations.isValidPassword("22")); ;
	}
//
	@Test
	void testIsValidCity() {
		assertEquals(true, CustomerValidations.isValidCity("Denver")); 
		assertEquals(true, CustomerValidations.isValidCity("Miami")); ;
		assertEquals(true, CustomerValidations.isValidCity("Napa")) ;
		assertEquals(false, CustomerValidations.isValidCity("Eke")); 
		assertEquals(false, CustomerValidations.isValidCity("22Denver")); ;
	}

	@Test
	void testIsValidFirstName() {
		assertEquals(true, CustomerValidations.isValidFirstName("John")); 
		assertEquals(true, CustomerValidations.isValidFirstName("Miami")); ;
		assertEquals(true, CustomerValidations.isValidFirstName("Napa")) ;
		assertEquals(false, CustomerValidations.isValidFirstName("John2")); 
		assertEquals(false, CustomerValidations.isValidFirstName("2Bob")); ;
	}

	@Test
	void testIsValidLastName() {
		assertEquals(true, CustomerValidations.isValidLastName("John")); 
		assertEquals(true, CustomerValidations.isValidLastName("Miami")); 
		assertEquals(true, CustomerValidations.isValidLastName("Napa")) ;
		assertEquals(false, CustomerValidations.isValidLastName("John2")); 
		assertEquals(false, CustomerValidations.isValidLastName("2Bob")); 
	}

	@Test
	void testIsValidPhoneNumber() {
		assertEquals(false, CustomerValidations.isValidPhoneNumber("John")); 
		assertEquals(true, CustomerValidations.isValidPhoneNumber("123456")); 
		assertEquals(true, CustomerValidations.isValidPhoneNumber("345567")) ;
		assertEquals(false, CustomerValidations.isValidPhoneNumber("123")); 
		assertEquals(false, CustomerValidations.isValidPhoneNumber("123451231")); 
	}

	@Test
	void testIsValidAge() {
		assertEquals(false, CustomerValidations.isValidAge(17)); 
		assertEquals(true, CustomerValidations.isValidAge(18)); 
		assertEquals(true, CustomerValidations.isValidAge(69)) ;
		assertEquals(false, CustomerValidations.isValidAge(71)); 
		assertEquals(false, CustomerValidations.isValidAge(222));
	}

	@Test
	void testIsValidGender() {
		assertEquals(true, CustomerValidations.isValidGender("M")); 
		assertEquals(true, CustomerValidations.isValidGender("m")); 
		assertEquals(true, CustomerValidations.isValidGender("f")) ;
		assertEquals(false, CustomerValidations.isValidGender("Male")); 
		assertEquals(false, CustomerValidations.isValidGender("Female"));
	}

	@Test
	void testIsValidCustomerStatus() {
		assertEquals(true, CustomerValidations.isValidCustomerStatus(1)); 
		assertEquals(true, CustomerValidations.isValidCustomerStatus(2)); 
		assertEquals(false, CustomerValidations.isValidCustomerStatus(0)) ;
		assertEquals(false, CustomerValidations.isValidCustomerStatus(3)); 

	}

	@Test
	void testIsValidUserId() {
		assertEquals(true, CustomerValidations.isValidUserId(1)); 
		assertEquals(true, CustomerValidations.isValidUserId(2)); 
		assertEquals(false, CustomerValidations.isValidUserId(0)) ;
		assertEquals(false, CustomerValidations.isValidUserId(-3)); 
	}

	@Test
	void testIsValidemail() {
		assertEquals(true, CustomerValidations.isValidemail("alex@mail.com")); 
		assertEquals(true, CustomerValidations.isValidemail("alex222@mail.com")); 
		assertEquals(false, CustomerValidations.isValidemail(".alex@mail.com")) ;
		assertEquals(false, CustomerValidations.isValidemail("alex@maill.com.")); 
	}

	@Test
	void testIsValidCustomerId() {
		assertEquals(true, CustomerValidations.isValidCustomerId(100)); 
		assertEquals(true, CustomerValidations.isValidCustomerId(2000)); 
		assertEquals(false, CustomerValidations.isValidCustomerId(0)) ;
		assertEquals(false, CustomerValidations.isValidCustomerId(-30)); 
	}

}
