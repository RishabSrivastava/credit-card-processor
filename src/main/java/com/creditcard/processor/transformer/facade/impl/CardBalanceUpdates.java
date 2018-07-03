package com.creditcard.processor.transformer.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creditcard.processor.model.Cards;
import com.creditcard.processor.request.mdel.UpdateCardBalance;
import com.creditcard.processor.service.dbconnection.CardDataOperations;
import com.creditcard.processor.transformer.facade.CardBalanceDetails;

@Component
public class CardBalanceUpdates implements CardBalanceDetails {
	
	@Autowired
	CardDataOperations cardOperations;

	@Override
	public Cards chargeBalance(double cardNumber, UpdateCardBalance cardBalance) {
		Cards card = cardOperations.chargeCardBalance(cardNumber, cardBalance);
		return card;
	}
	
	@Override
	public Cards creditBalance(double cardNumber, UpdateCardBalance cardBalance) {
		Cards card = cardOperations.creditCardBalance(cardNumber, cardBalance);
		return card;
	}

}
