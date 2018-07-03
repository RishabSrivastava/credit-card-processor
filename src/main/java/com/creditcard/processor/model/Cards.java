package com.creditcard.processor.model;

import com.creditcard.processor.model.Balance;

public class Cards {
	
	private double cardNumber;
	
	private double totalLimit;
	
	private Balance balance;

	public double getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(double cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(double totalLimit) {
		this.totalLimit = totalLimit;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}
	
	

}
