package com.qa.ims.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
	
	@Test
	public void customerConstructorTest() {
		final Customer cus = new Customer("John", "Smith");
		assertNotNull(cus);
		assertTrue(cus instanceof Customer);
	}

}
