package com.sigei.ms_mpesa_integration.model.response;

import lombok.Data;

@Data
public class MpesaAuthResponse {
    private String access_token;
    private String expires_in;
}
