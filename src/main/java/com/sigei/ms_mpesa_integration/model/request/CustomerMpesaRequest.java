package com.sigei.ms_mpesa_integration.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerMpesaRequest {
    private String msisdn;
    private String amount;
}
