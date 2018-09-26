package com.stackroute.paymentservice.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.paymentservice.services.StripeClient;
import com.stripe.model.Charge;
import com.stripe.model.Refund;

@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
public class PaymentController {

	private StripeClient stripeClient;

	@Autowired
	PaymentController(StripeClient stripeClient) {
		this.stripeClient = stripeClient;
	}

	/**
	 * Gets token and amount from post mapping request header and charges the credit
	 * card
	 **/
	@PostMapping("/charge")
	public Charge chargeCard(HttpServletRequest request) throws Exception {
		String token = request.getHeader("token");
		Double amount = Double.parseDouble(request.getHeader("amount"));
		return this.stripeClient.chargeNewCard(token, amount);
	}

	/**
	 * Refund a charge via the API, perform a cardRefund call, providing the ID
	 * of the charge to be refunded.
	 **/
	@PostMapping("/refund")
	public Refund refundCard(HttpServletRequest request) throws Exception {
		return this.stripeClient.cardRefund();
	}

}
