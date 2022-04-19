package com.service.atm.controller;

import com.service.atm.validator.CardNumberValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/card")
public class CardController {



    Logger logger = LoggerFactory.getLogger(CardController.class);
    static RestTemplate restTemplate = new RestTemplate();
    private static final String PIN_AUTH_URL = "http://localhost:8080/api/v1/auth"; // will put it in a constants class
    private static final String FINGERPRINT_AUTH_URL = "http://localhost:8080/api/v1/auth"; // will put it in a constants class

    private CardNumberValidator cardNumberValidator;
    @PostMapping("/validate")
    public boolean validateCardNumber(@Valid @RequestBody long cardNumber) {
        return cardNumberValidator.isValidCreditCardNumber(cardNumber);
    }
    @GetMapping("/hello")
    public String hello(){
        return  "works";
    }

    @PostMapping("/authenticate")
    public void createTransaction(@Valid @RequestBody String cardNumber, @Valid @RequestBody String authType,@Valid @RequestBody String password ) {
        if(authType.equals("PIN")) authenticateWithPin(cardNumber, password);
        else if(authType.equals("FINGERPRINT")) authenticateWithFingerPrint(cardNumber, password);
    }

    private ResponseEntity<String> authenticateWithFingerPrint(String cardNumber, String password) {
        HttpHeaders httpHeaders = new HttpHeaders();
           httpHeaders.setAccept((List<MediaType>) MediaType.APPLICATION_JSON);
            httpHeaders.put("username", Collections.singletonList(cardNumber));
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);

        ResponseEntity<String> result =restTemplate.exchange(PIN_AUTH_URL, HttpMethod.POST, entity, String.class);

        logger.debug(String.valueOf(result));
        return  result;
    }

    private ResponseEntity<String> authenticateWithPin(String cardNumber, String password) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept((List<MediaType>) MediaType.APPLICATION_JSON);
        httpHeaders.put("username", Collections.singletonList(cardNumber));
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);

        ResponseEntity<String> result =restTemplate.exchange(FINGERPRINT_AUTH_URL, HttpMethod.POST, entity, String.class);
        System.out.println(result);

        return result;
    }


}
