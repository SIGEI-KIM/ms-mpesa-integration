package com.sigei.ms_mpesa_integration.service.impl;

import com.sigei.ms_mpesa_integration.config.ConfigProperties;
import com.sigei.ms_mpesa_integration.model.response.MpesaAuthResponse;
import com.sigei.ms_mpesa_integration.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;


@Service
@RequiredArgsConstructor
public class MpesaService {
    private final Helper helper;
    private final RestTemplate restTemplate;
    private final ConfigProperties properties;
    public String mpesaToken(){
        //get token
        HttpEntity<Void> requestEntity = new HttpEntity<>(getMpesaAuthHeaders());
        ResponseEntity<MpesaAuthResponse> mpesaAuthResponse = restTemplate.exchange(properties.getTokenUrl(), HttpMethod.GET, requestEntity, MpesaAuthResponse.class);
        return mpesaAuthResponse.getBody().getAccess_token();
    }

    private HttpHeaders getMpesaAuthHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(properties.getConsumerKey(),properties.getConsumerSecret());
        return httpHeaders;
    }
    public String getMpesaPassword(){
        String stringToEncode = properties.getBusinessShortCode() + properties.getPassKey() + helper.getTimeStamp();
        return Base64.getEncoder().encodeToString(stringToEncode.getBytes());
    }
}
