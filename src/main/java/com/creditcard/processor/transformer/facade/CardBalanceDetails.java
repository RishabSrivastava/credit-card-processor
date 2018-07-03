package com.creditcard.processor.transformer.facade;

import com.creditcard.processor.model.Cards;
import com.creditcard.processor.request.mdel.UpdateCardBalance;

public interface CardBalanceDetails {
	
	public Cards chargeBalance(double cardNumber, UpdateCardBalance cardBalance);
	
	public Cards creditBalance(double cardNumber, UpdateCardBalance cardBalance);

}
