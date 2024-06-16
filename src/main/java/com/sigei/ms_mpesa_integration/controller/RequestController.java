package com.sigei.ms_mpesa_integration.controller;

import com.sigei.ms_mpesa_integration.model.request.CustomerMpesaRequest;
import com.sigei.ms_mpesa_integration.model.response.ApiResponse;
import com.sigei.ms_mpesa_integration.model.response.StkConfirmationResponse;
import com.sigei.ms_mpesa_integration.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RequestController {
    private final ApiService apiService;
    @PostMapping("/stk-push")
    public ResponseEntity<ApiResponse> triggerSTKPush(@RequestBody CustomerMpesaRequest payload) throws Exception {
        return new ResponseEntity<>(apiService.triggerSTKPush(payload), HttpStatus.OK);
    }

    @PostMapping("/call-back")
    public void callBack(@RequestBody StkConfirmationResponse payload) throws Exception {
        apiService.processStkTransaction(payload);
    }
}
