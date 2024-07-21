package com.sigei.ms_mpesa_integration.controller;

import com.sigei.ms_mpesa_integration.model.request.CustomerMpesaRequest;
import com.sigei.ms_mpesa_integration.model.response.ApiResponse;
import com.sigei.ms_mpesa_integration.model.response.StkConfirmationResponse;
import com.sigei.ms_mpesa_integration.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin
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

    @PostMapping("/callback")
    public ResponseEntity<?> callBack(@RequestBody String payload) {
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("responseCode","0");
        System.out.println("stk response: " + payload);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
