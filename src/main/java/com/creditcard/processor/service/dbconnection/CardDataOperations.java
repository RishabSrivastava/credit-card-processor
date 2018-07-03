package com.creditcard.processor.service.dbconnection;

import java.util.List;

import com.creditcard.processor.model.Cards;
import com.creditcard.processor.repository.model.CardBalance;
import com.creditcard.processor.request.mdel.UpdateCardBalance;

public interface CardDataOperations {
	
	public List<CardBalance> getCreditCard();
	public Cards chargeCardBalance (double cradNumber, UpdateCardBalance bal);
	public Cards creditCardBalance (double cradNumber, UpdateCardBalance bal); 

}
