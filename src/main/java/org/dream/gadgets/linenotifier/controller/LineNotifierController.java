package org.dream.gadgets.linenotifier.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/notifier")
public class LineNotifierController {
    
    @PostMapping(value = "callback")
    public ResponseEntity<String> callback(@PathVariable String code, @PathVariable String state) {
        log.info("Code: {}, State: {}", code, state);

        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        // map.add("grant_type", "authorization_code");
        // map.add("code", code);
        // map.add("redirect_uri", "");
        // map.add("client_id", "MZDiy4gwlF0OQxlwRhSdhT");
        // map.add("client_secret","Q0f1wpqH88t9sfwUFdIACW5PwowpZO9VwxamxddNkAF");
        // HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        // RestTemplate restTemplate = new RestTemplate();
        // restTemplate.postForEntity(
        //     "https://notify-bot.line.me/oauth/token"

        // );

        ResponseEntity<String> response = ResponseEntity.ok("Token fetched");
        return response;
    }

}
