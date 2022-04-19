package com.service.atm.service;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Service
public class OperationService {

        static private String Auth_token = "secret";
    //all constants will be in an external file
    private static final String GET_ALL_TRANSACTIONS = "http://localhost:8080/api/v1/transactions/statement";
    private static final String WITHDRAW_TRANSACTION = "http://localhost:8080/api/v1/transactions/withdraw";
    private static final String DEPOSIT_TRANSACTION = "http://localhost:8080/api/v1/transactions/deposit";
    private static final String CHECK_BALANCE = "http://localhost:8080/api/v1/transactions/balance";

    static RestTemplate restTemplate = new RestTemplate();


    public static ResponseEntity<String> withdraw(double amount){
        HttpHeaders httpHeaders = new HttpHeaders();
        //    httpHeaders.setAccept(MediaType.APPLICATION_JSON);
        httpHeaders.put("token", Collections.singletonList(Auth_token));
        httpHeaders.put("amount", Collections.singletonList(String.valueOf(amount)));
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);

        ResponseEntity<String> result =restTemplate.exchange(WITHDRAW_TRANSACTION, HttpMethod.POST, entity, String.class);
        log.debug(String.valueOf(result));

       return  result;
    }
    public static ResponseEntity<String> deposit(double amount){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept((List<MediaType>) MediaType.APPLICATION_JSON);
        httpHeaders.put("token", Collections.singletonList(Auth_token));
        httpHeaders.put("amount", Collections.singletonList(String.valueOf(amount)));

        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);

        ResponseEntity<String> result =restTemplate.exchange(DEPOSIT_TRANSACTION, HttpMethod.POST, entity, String.class);
        log.debug(String.valueOf(result));

        return result;
    }
    public static ResponseEntity<String> checkBalance(){

        HttpHeaders httpHeaders = new HttpHeaders();
        //    httpHeaders.setAccept(MediaType.APPLICATION_JSON);
        httpHeaders.put("token", Collections.singletonList(Auth_token));
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);

        ResponseEntity<String> result =restTemplate.exchange(CHECK_BALANCE, HttpMethod.POST, entity, String.class);

//        log.debug(result);

        return  result;
    }
    public static ResponseEntity<String> printStatement(){

        HttpHeaders httpHeaders = new HttpHeaders();
        //    httpHeaders.setAccept(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);

        ResponseEntity<String> result =restTemplate.exchange(GET_ALL_TRANSACTIONS, HttpMethod.POST, entity, String.class);
//        log.debug(result);

        return result;
    }
    public void logout(){
        //delete session/token
    }
}

