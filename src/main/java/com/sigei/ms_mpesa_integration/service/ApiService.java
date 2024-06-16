package com.sigei.ms_mpesa_integration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sigei.ms_mpesa_integration.model.request.CustomerMpesaRequest;
import com.sigei.ms_mpesa_integration.model.response.ApiResponse;
import com.sigei.ms_mpesa_integration.model.response.StkConfirmationResponse;

public interface ApiService {
    ApiResponse triggerSTKPush(CustomerMpesaRequest payload) throws Exception;

    void processStkTransaction(StkConfirmationResponse payload) throws JsonProcessingException;
}
