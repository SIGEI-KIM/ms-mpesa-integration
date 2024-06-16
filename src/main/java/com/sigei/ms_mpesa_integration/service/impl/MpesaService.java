package com.sigei.ms_mpesa_integration.service.impl;

import com.sigei.ms_mpesa_integration.config.ConfigProperties;
import com.sigei.ms_mpesa_integration.model.response.MpesaAuthResponse;
import com.sigei.ms_mpesa_integration.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(MpesaService.class);
    private final Helper helper;
    private final RestTemplate restTemplate;
    private final ConfigProperties properties;

    public String mpesaToken() {
        try {
            HttpEntity<Void> requestEntity = new HttpEntity<>(getMpesaAuthHeaders());
            ResponseEntity<MpesaAuthResponse> mpesaAuthResponse = restTemplate.exchange(properties.getTokenUrl(), HttpMethod.GET, requestEntity, MpesaAuthResponse.class);
            return mpesaAuthResponse.getBody().getAccess_token();
        } catch (Exception ex) {
            LOG.error("Failed to generate token. {}", ex.getMessage());
            return null;
        }
    }

    private HttpHeaders getMpesaAuthHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(properties.getConsumerKey(), properties.getConsumerSecret());
        return httpHeaders;
    }

    public String getMpesaPassword() {
        String stringToEncode = properties.getBusinessShortCode() + properties.getPassKey() + helper.getTimeStamp();
        return Base64.getEncoder().encodeToString(stringToEncode.getBytes());
    }
}
