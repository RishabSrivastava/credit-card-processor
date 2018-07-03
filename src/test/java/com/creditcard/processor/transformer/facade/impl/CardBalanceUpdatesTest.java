package com.creditcard.processor.transformer.facade.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.creditcard.processor.request.mdel.UpdateCardBalance;
import com.creditcard.processor.service.dbconnection.CardDataOperations;

@RunWith(MockitoJUnitRunner.class)
public class CardBalanceUpdatesTest {
	
	@Mock
	CardDataOperations cardOperations;
	
	@InjectMocks
	CardBalanceUpdates cardBalance;
	
	@Test
	public void test_creditUpdate() {
		double cardNumber = 10003;
		
		UpdateCardBalance newBalance = new UpdateCardBalance();
		newBalance.setBalanceUpdate(500);
		
		assertEquals(cardBalance.creditBalance(cardNumber, newBalance).getBalance().getOutstandingBalance(),2500);
	}
	
	@Test
	public void test_chargeUpdate() {
		double cardNumber = 10003;
		
		UpdateCardBalance newBalance = new UpdateCardBalance();
		newBalance.setBalanceUpdate(500);
		
		assertEquals(cardBalance.creditBalance(cardNumber, newBalance).getBalance().getAvailableBalance(),4500);
	}

}
