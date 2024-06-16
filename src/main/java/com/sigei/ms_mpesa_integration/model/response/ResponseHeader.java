package com.sigei.ms_mpesa_integration.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHeader {
    private String responseCode;
    private String responseDescription;
}
