package org.dream.gadgets.linenotifier.controller;

import java.util.HashMap;
import java.util.Map;

import org.dream.gadgets.linenotifier.model.dto.AccessTokenResponse;
import org.dream.gadgets.linenotifier.model.dto.AuthorizeCodeAndState;
import org.dream.gadgets.linenotifier.model.dto.GenericResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/notifier")
public class LineNotifierController {
    
    private static final Map<String, String> tempTokenStore = new HashMap<>();

    @PostMapping(
        value = "callback",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<String> callback(AuthorizeCodeAndState authorizeCodeAndState) {
        String code = authorizeCodeAndState.getCode();
        String state = authorizeCodeAndState.getState();
        log.info("Code: {}, State: {}", code, state);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", "https://dreamer-line-notifier.herokuapp.com/notifier/callback");
        map.add("client_id", "MZDiy4gwlF0OQxlwRhSdhT");
        map.add("client_secret","Q0f1wpqH88t9sfwUFdIACW5PwowpZO9VwxamxddNkAF");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AccessTokenResponse> tokenExchangeResponseEntity = restTemplate.postForEntity(
            "https://notify-bot.line.me/oauth/token",
            entity,
            AccessTokenResponse.class
        );

        AccessTokenResponse tokenResponse = tokenExchangeResponseEntity.getBody();
        log.info(
            "Access token: {}, status: {}, message: {}", 
            tokenResponse.getAccessToken(), 
            tokenResponse.getStatus(),
            tokenResponse.getMessage()
        );

        tempTokenStore.put("accessToken", tokenResponse.getAccessToken());
        ResponseEntity<String> response = ResponseEntity.ok("Token fetched");
        return response;
    }

    @GetMapping(value = "sendMessage")
    public ResponseEntity<String> sendMessage() {
        String token = tempTokenStore.get("accessToken");
        log.info("Fetched token from storage: {}", token);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer "+ token);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("message", "line notifier test 444555666");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GenericResponse> tokenExchangeResponseEntity = restTemplate.postForEntity(
            "https://notify-api.line.me/api/notify",
            entity,
            GenericResponse.class
        );

        GenericResponse tokenResponse = tokenExchangeResponseEntity.getBody();
        log.info(
            "status: {}, message: {}", 
            tokenResponse.getStatus(),
            tokenResponse.getMessage()
        );
        ResponseEntity<String> response = ResponseEntity.ok("Message sent");
        return response;
    }

}
