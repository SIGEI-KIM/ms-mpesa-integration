package com.sigei.ms_mpesa_integration.model.response;

import lombok.Data;

@Data
public class MpesaResponse {
    private int MerchantRequestID;
    private String CheckoutRequestID;
    private int ResponseCode;
    private String ResponseDescription;
    private String CustomerMessage;
}
