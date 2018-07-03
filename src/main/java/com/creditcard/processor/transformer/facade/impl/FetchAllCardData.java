package com.creditcard.processor.transformer.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creditcard.processor.model.CardResponse;
import com.creditcard.processor.model.Cards;
import com.creditcard.processor.repository.model.CardBalance;
import com.creditcard.processor.repository.model.CustomerDetails;
import com.creditcard.processor.service.dbconnection.CardDataOperations;
import com.creditcard.processor.service.dbconnection.CustomerDataOperations;
import com.creditcard.processor.transformer.facade.AllCardData;

@Component
public class FetchAllCardData implements AllCardData {
	
	@Autowired
	CustomerDataOperations dbOperations;
	
	@Autowired
	CardDataOperations cardOperations;

	@Override
	public CardResponse fetchDetails() {
		CardResponse response = new CardResponse();
		List<CustomerDetails> customerDetails = dbOperations.getCustomerDetails();
		List<CardBalance> balanceDetails = cardOperations.getCreditCard();
		List<Cards> cardBalanceDetails = new ArrayList<>();
		for (CustomerDetails customerDetail : customerDetails) {
			response.setCardGoldersName(customerDetail.getName());
			for (CardBalance balance : balanceDetails) {
				if (customerDetail.getId() == balance.getId()) {
					Cards cards = new Cards();
					com.creditcard.processor.model.Balance bal = new com.creditcard.processor.model.Balance();
					cards.setTotalLimit(customerDetail.getBalance());
					bal.setAvailableBalance(balance.getAvailableBalance());
					bal.setOutstandingBalance(balance.getOutstanding());
					cards.setBalance(bal);
					cards.setCardNumber(customerDetail.getId());
					cardBalanceDetails.add(cards);
				}
			}
		}
		response.setCardsHeld(cardBalanceDetails);
		return response;
	}

}
