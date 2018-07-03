package com.creditcard.processor.model;

import java.util.List;

public class CardResponse {
	
	private String cardGoldersName;
	
	private List<Cards> cardsHeld;

	public String getCardGoldersName() {
		return cardGoldersName;
	}

	public void setCardGoldersName(String cardGoldersName) {
		this.cardGoldersName = cardGoldersName;
	}

	public List<Cards> getCardsHeld() {
		return cardsHeld;
	}

	public void setCardsHeld(List<Cards> cardsHeld) {
		this.cardsHeld = cardsHeld;
	}

}
