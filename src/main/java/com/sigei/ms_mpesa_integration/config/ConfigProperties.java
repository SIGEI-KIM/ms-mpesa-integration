package com.sigei.ms_mpesa_integration.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Data
@Service
public class ConfigProperties {
    @Value("${mpesa.businessShortCode}")
    private String businessShortCode;
    @Value("${mpesa.passKey}")
    private String passKey;
    @Value("${mpesa.consumerKey}")
    private String consumerKey;
    @Value("${mpesa.consumerSecret}")
    private String consumerSecret;

    @Value("${mpesa.stkUrl}")
    private String stkUrl;

    @Value("${mpesa.callBackURL}")
    private String callBackURL;

    @Value("${mpesa.partyB}")
    private String partyB;

    @Value("${mpesa.tokenUrl}")
    private String tokenUrl;
}
