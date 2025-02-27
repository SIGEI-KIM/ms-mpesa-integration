package com.sigei.ms_mpesa_integration.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private ResponseHeader responseHeader;
    private ResponseBody responseBody;
}
