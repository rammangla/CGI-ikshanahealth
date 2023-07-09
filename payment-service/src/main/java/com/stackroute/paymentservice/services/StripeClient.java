package com.stackroute.paymentservice.services;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class StripeClient {

    @Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_51JVTm3SAuNZd9whPZ19ZJ7TavsTR4khZyxR4FhZeuhJZNjO9mlXAwjBFTvwyRbwleBujvQjOLf7QxWqdDdXubxQa00zznWQPXl";
    }

    public Charge chargeCreditCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount));
        chargeParams.put("currency", "INR");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
