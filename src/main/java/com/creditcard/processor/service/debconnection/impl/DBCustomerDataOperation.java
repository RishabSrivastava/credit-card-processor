package com.creditcard.processor.service.debconnection.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.creditcard.processor.repository.model.CustomerDetails;
import com.creditcard.processor.request.mdel.AddCard;
import com.creditcard.processor.service.dbconnection.CustomerDataOperations;

@Repository
@Component
public class DBCustomerDataOperation implements CustomerDataOperations {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<CustomerDetails> getCustomerDetails() throws DataAccessException {
		List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
		List<Map<String,Object>> customerdetails = jdbcTemplate.queryForList("SELECT * FROM creditcardinfo");
		for (Map<String, Object> row : customerdetails) {
			CustomerDetails customerDetail = new CustomerDetails();
			customerDetail.setId((double)row.get("id"));
			customerDetail.setName((String)row.get("name"));
			customerDetail.setBalance((double)row.get("balance"));
			customerList.add(customerDetail);
		}
		return customerList;
	}
	
	@Override
	public String addNewCard(AddCard newCard) throws DuplicateKeyException, DataAccessException {
		int numberOfRows = jdbcTemplate.update("INSERT INTO creditcardinfo (id, name, balance) VALUES (?,?,?)", new Object[]{
				newCard.getCardNumber(),newCard.getName(),newCard.getLimit()
		});
		if (numberOfRows == 1 && addNewCardBalance(newCard.getCardNumber()) == 1) {
			return "Details Succesfully Updated";
		} else {
			return "Error updating Details";
		}
	}
	
	private int addNewCardBalance(double cardNumber) throws DuplicateKeyException, DataAccessException {
		return jdbcTemplate.update("INSERT INTO cardLedger (id, availablebalance, outstandingbalance) VALUES (?,0,0)", new Object[]{
				cardNumber
		});
	}

}
