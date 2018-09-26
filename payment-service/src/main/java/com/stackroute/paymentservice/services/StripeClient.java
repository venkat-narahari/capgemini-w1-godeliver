package com.stackroute.paymentservice.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Refund;

@Component
public class StripeClient {

	@Autowired
	StripeClient() {
		Stripe.apiKey = "sk_test_vPdUFvDpA4y3GzNpQqGitpiz";
	}

	String chargeId;

	/**
	 * Gets amount from request mapping header and charges the card and returns the
	 * charged amount to stripe
	 **/
	public Charge chargeNewCard(String token, double amount) throws Exception {
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", (int) (amount * 100));
		chargeParams.put("currency", "INR");
		chargeParams.put("source", token);
		Charge charge = Charge.create(chargeParams);
		chargeId = charge.getId();
		System.out.println(charge.getId());
		return charge;
	}

	/**
	 * Refund a charge, perform a cardRefund call, providing the ID of
	 * the charge to be refunded.
	 **/
	public Refund cardRefund() throws Exception {
		Map<String, Object> refundParams = new HashMap<String, Object>();
		refundParams.put("charge", chargeId);

		Refund refund = Refund.create(refundParams);
		return refund;

	}

}
