package com.creditcard.processor.transformer.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creditcard.processor.request.mdel.AddCard;
import com.creditcard.processor.service.dbconnection.CustomerDataOperations;
import com.creditcard.processor.service.secuirty.CreditCardValidator;
import com.creditcard.processor.transformer.facade.AddCardData;

@Component
public class AddNewCardDetails implements AddCardData{
	
	@Autowired
	CustomerDataOperations insertCard;
	
	@Autowired
	CreditCardValidator cardValidator;

	@Override
	public boolean addCard(AddCard card) {
		if (cardValidator.validateCard(card.getCardNumber())) {
			String message = insertCard.addNewCard(card);
			return true;
		} else {		
			return false;
		}
	}

}
