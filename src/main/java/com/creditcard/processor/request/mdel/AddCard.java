package com.creditcard.processor.request.mdel;

public class AddCard {
	
	private String name;
	private double cardNumber;
	private double limit;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(double cardNumber) {
		this.cardNumber = cardNumber;
	}
	public double getLimit() {
		return limit;
	}
	public void setLimit(double limit) {
		this.limit = limit;
	}
	
}
