package com.creditcard.processor.service.dbconnection;

import java.util.List;

import com.creditcard.processor.repository.model.CustomerDetails;
import com.creditcard.processor.request.mdel.AddCard;

public interface CustomerDataOperations {
	
	public List<CustomerDetails> getCustomerDetails();
	public String addNewCard(AddCard newCard);

}
