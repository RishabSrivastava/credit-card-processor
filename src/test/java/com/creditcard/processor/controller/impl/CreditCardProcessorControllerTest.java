package com.creditcard.processor.controller.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.creditcard.processor.request.mdel.AddCard;
import com.creditcard.processor.transformer.facade.AddCardData;
import com.creditcard.processor.transformer.facade.AllCardData;
import com.creditcard.processor.transformer.facade.CardBalanceDetails;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditCardRemoteController.class)
public class CreditCardProcessorControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Mock
	AllCardData cardResponseFacade;
	@Mock
	AddCardData addCardData;
	@Mock
	CardBalanceDetails updateBalance;
	
	@InjectMocks
	CreditCardRemoteController creditCardRemoteController;
	
	@Test
	public void test_addCard() {
		AddCard addRequestCard = new AddCard();
		addRequestCard.setCardNumber(123456789);
		addRequestCard.setLimit(5000);
		addRequestCard.setName("James");
		
		this.mo
		
		when(addCardData.addCard().thenReturn(true));
	}

}
