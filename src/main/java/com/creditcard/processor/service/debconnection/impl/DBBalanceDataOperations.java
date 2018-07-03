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

import com.creditcard.processor.model.Balance;
import com.creditcard.processor.model.Cards;
import com.creditcard.processor.repository.model.CardBalance;
import com.creditcard.processor.request.mdel.UpdateCardBalance;
import com.creditcard.processor.service.dbconnection.CardDataOperations;

@Repository
@Component
public class DBBalanceDataOperations implements CardDataOperations{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<CardBalance> getCreditCard() throws DataAccessException{
		List<CardBalance> cardList = new ArrayList<CardBalance>();
		List<Map<String,Object>> cardDetails = jdbcTemplate.queryForList("SELECT * FROM creditcardinfo");
		for (Map<String, Object> row : cardDetails) {
			CardBalance card = new CardBalance();
			card.setId((double) row.get("id"));
			card.setAvailableBalance((double) row.get("availablebalance"));
			card.setOutstanding((double) row.get("outstandingbalance"));
			cardList.add(card);
		}
		return cardList;
	}
	
	@Override
	public Cards chargeCardBalance (double cradNumber, UpdateCardBalance bal) throws DataAccessException {
		Balance newBalance = new Balance();
		Cards updatedCard = new Cards();
		
		double cardBalance = jdbcTemplate.queryForObject("SELECT balance from creditcardinfo where id = ?", Double.class, cradNumber);
		double availableBalance = jdbcTemplate.queryForObject("SELECT availableBalance from cardLedger where id = ?", Double.class, cradNumber);
		double outstandingBalance = jdbcTemplate.queryForObject("SELECT outstandingBalance from cardLedger where id = ?", Double.class, cradNumber);
		
		if (bal.getBalanceUpdate() > cardBalance || ((bal.getBalanceUpdate() + outstandingBalance) > cardBalance)) {
			return updatedCard;
		} else {
			availableBalance = cardBalance - (bal.getBalanceUpdate() + outstandingBalance);
			outstandingBalance = bal.getBalanceUpdate() + outstandingBalance;
			addNewCardBalance(cradNumber, availableBalance, outstandingBalance);
			updatedCard.setTotalLimit(cardBalance);
			updatedCard.setCardNumber(cradNumber);
			updatedCard.setBalance(newBalance);
			newBalance.setAvailableBalance(availableBalance);
			newBalance.setOutstandingBalance(outstandingBalance);
			return updatedCard;
		}
			
		}
	
	@Override
	public Cards creditCardBalance (double cradNumber, UpdateCardBalance bal) throws DataAccessException {
		Balance newBalance = new Balance();
		Cards updatedCard = new Cards();
		
		double cardBalance = jdbcTemplate.queryForObject("SELECT balance from creditcardinfo where id = ?", Double.class, cradNumber);
		double availableBalance = jdbcTemplate.queryForObject("SELECT availableBalance from cardLedger where id = ?", Double.class, cradNumber);
		double outstandingBalance = jdbcTemplate.queryForObject("SELECT outstandingBalance from cardLedger where id = ?", Double.class, cradNumber);
		

			availableBalance = availableBalance - bal.getBalanceUpdate();
			outstandingBalance = outstandingBalance - bal.getBalanceUpdate();
			addNewCardBalance(cradNumber, availableBalance, outstandingBalance);
			updatedCard.setTotalLimit(cardBalance);
			updatedCard.setCardNumber(cradNumber);
			updatedCard.setBalance(newBalance);
			newBalance.setAvailableBalance(availableBalance);
			newBalance.setOutstandingBalance(outstandingBalance);
			return updatedCard;
			
		}
	
	private int addNewCardBalance(double cardNumber, double availableBalance, double outstandingBalance) throws DuplicateKeyException, DataAccessException {
		return jdbcTemplate.update("UPDATE cardLedger SET availablebalance = ?,  outstandingBalance = ? WHERE id = ?",
				availableBalance, outstandingBalance, cardNumber
		);
		
	}

}
