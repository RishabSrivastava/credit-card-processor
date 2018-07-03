package com.creditcard.processor.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.creditcard.processor.model.CardResponse;
import com.creditcard.processor.model.Cards;
import com.creditcard.processor.request.mdel.AddCard;
import com.creditcard.processor.request.mdel.UpdateCardBalance;

public interface CreditCardProcessorController {
	
	public Map<String, CardResponse> invokeAllCardsService(HttpServletResponse response);
	public Map<String, String> AddCardsService(@RequestBody AddCard addCard,HttpServletResponse response);
	public Map<String,Cards> ChargeExistingCard(@RequestBody UpdateCardBalance updateCardBalance,@PathVariable double cardNumber, HttpServletResponse response);
	public Map<String, Cards> CreditExistingCard(@RequestBody UpdateCardBalance updateCardBalance,@PathVariable double cardNumber, HttpServletResponse response);
	

}
