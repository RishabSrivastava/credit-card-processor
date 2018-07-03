package com.creditcard.processor.service.security.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidateCreditCardTest {
	
	@InjectMocks
	ValidateCreditCard validateCard;
	
	@Test
	public void test_validateCard() {
		double number = 1234567891;
		assertEquals(validateCard.validateCard(number), true);
	}
	
	@Test
	public void test_lengthOfCard() {
		double number = 8889912;
		assertEquals(validateCard.checkCardLength(number), true);
	}
	
	@Test
	public void test_luhnCheck() {
		double number = 449900123;
		assertEquals(validateCard.luhn10Check(number), true);
	}
	

}
