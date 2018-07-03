package com.creditcard.processor.controller.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.processor.configuration.TransformerConstants;
import com.creditcard.processor.controller.CreditCardProcessorController;
import com.creditcard.processor.model.CardResponse;
import com.creditcard.processor.model.Cards;
import com.creditcard.processor.request.mdel.AddCard;
import com.creditcard.processor.request.mdel.UpdateCardBalance;
import com.creditcard.processor.transformer.facade.AddCardData;
import com.creditcard.processor.transformer.facade.AllCardData;
import com.creditcard.processor.transformer.facade.CardBalanceDetails;

@RestController
public class CreditCardRemoteController implements CreditCardProcessorController {
	
	AllCardData cardResponseFacade;
	
	AddCardData addCardData;
	
	CardBalanceDetails updateBalance;
	
	@Autowired
	public CreditCardRemoteController(AllCardData cardResponseFacade, AddCardData addCardData, CardBalanceDetails updateBalance) {
		this.cardResponseFacade = cardResponseFacade;
		this.addCardData = addCardData;
		this.updateBalance = updateBalance;
	}

	@Override
	@RequestMapping(value = TransformerConstants.GET_ALL_CARDS, method = RequestMethod.GET)
	public @ResponseBody Map<String, CardResponse> invokeAllCardsService(HttpServletResponse response) {
		Map<String, CardResponse> catResponseMap = new HashMap<String, CardResponse>();
		CardResponse cardresponse = cardResponseFacade.fetchDetails();
		catResponseMap.put("AllCards", cardresponse);
		return catResponseMap;
	}

	@Override
	@RequestMapping(value = TransformerConstants.ADD_NEW_CARD, method = RequestMethod.POST, consumes = "application/json")
	public Map<String, String> AddCardsService(@RequestBody AddCard addCard,HttpServletResponse response) {
		Map<String, String> successMap = new HashMap<String,String>();
		
		if (addCardData.addCard(addCard)) {
			successMap.put("Success", "CARD ADDED SUCCESSFULLY");
		} else {
			response.setStatus(404);
			successMap.put("ERROR", "CARD IS INVALID");
		}
		return successMap;
	}

	@Override
	@RequestMapping(value = TransformerConstants.CHARGE_EXISTING_CARD, method = RequestMethod.PUT, consumes = "application/json")
	public Map<String, Cards> ChargeExistingCard(@RequestBody UpdateCardBalance updateCardBalance,@PathVariable(value = "cardNumber") double cardNumber, HttpServletResponse response) {
		Map<String, Cards> successMap = new HashMap<String,Cards>();
		
		successMap.put("UpdatedCardDetails", updateBalance.chargeBalance(cardNumber, updateCardBalance));
		return successMap;
	}

	@Override
	@RequestMapping(value = TransformerConstants.CREDIT_EXISTING_CARD, method = RequestMethod.PUT, consumes = "application/json")
	public Map<String, Cards> CreditExistingCard(@RequestBody UpdateCardBalance updateCardBalance,double cardNumber, HttpServletResponse response) {
		Map<String, Cards> successMap = new HashMap<String,Cards>();
		
		successMap.put("UpdatedCardDetails", updateBalance.creditBalance(cardNumber, updateCardBalance));
		return successMap;
	}

}
