package com.creditcard.processor.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cardLedger")
public class CardBalance {
	@Id
	@Column(name = "id")
	private double id;
	@Column(name = "availableBalance")
	private double availableBalance;
	@Column(name = "outstandingBalance")
	private double outstanding;
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public double getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}
	public double getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(double outstanding) {
		this.outstanding = outstanding;
	}
	
	

}
