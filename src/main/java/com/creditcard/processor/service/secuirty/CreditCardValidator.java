package com.creditcard.processor.service.secuirty;

import java.util.Map;

import com.creditcard.processor.model.CardError;

public interface CreditCardValidator {
	
	public boolean validateCard(double cardNumber);

}
