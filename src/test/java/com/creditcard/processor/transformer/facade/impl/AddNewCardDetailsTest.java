package com.creditcard.processor.transformer.facade.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.creditcard.processor.request.mdel.AddCard;

@RunWith(MockitoJUnitRunner.class)
public class AddNewCardDetailsTest {
	
	@InjectMocks
	AddNewCardDetails addNewCard;
	
	@Test
	public void test_addCard_positive() {
		AddCard card = new AddCard();
		card.setCardNumber(1099823);
		card.setLimit(1000);
		card.setName("Peter");
		assertEquals(addNewCard.addCard(card),true);
	}
	
	@Test
	public void test_addCard_negative() {
		AddCard card = new AddCard();
		card.setCardNumber(999999);
		card.setLimit(100);
		card.setName("Rosy");
		assertEquals(addNewCard.addCard(card),false);
	}

}
