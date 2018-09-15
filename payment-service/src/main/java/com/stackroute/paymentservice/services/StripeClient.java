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

//    public Customer createCustomer(String token, String email) throws Exception {
//        Map<String, Object> customerParams = new HashMap<String, Object>();
//        customerParams.put("email", email);
//        customerParams.put("source", token);
//        return Customer.create(customerParams);
//    }
//
//    private Customer getCustomer(String id) throws Exception {
//        return Customer.retrieve(id);
//    }
 
    String chargeId;
    
    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "INR");
        chargeParams.put("source", token);
        
//        RequestOptions options = RequestOptions
//        		  .builder()
//        		  .setIdempotencyKey("A1m2a3r4")
//        		  .build();
        Charge charge = Charge.create(chargeParams);
        chargeId = charge.getId();
        System.out.println(charge.getId());
        return charge;
    }

    public Refund cardRefund() throws Exception {
    	Map<String, Object> refundParams = new HashMap<String, Object>();
    	refundParams.put("charge", chargeId);

    	Refund refund = Refund.create(refundParams);
		return refund;
    	
    }


}
