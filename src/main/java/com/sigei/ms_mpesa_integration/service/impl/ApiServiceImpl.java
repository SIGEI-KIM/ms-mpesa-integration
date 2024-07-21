package com.sigei.ms_mpesa_integration.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigei.ms_mpesa_integration.config.ConfigProperties;
import com.sigei.ms_mpesa_integration.dblayer.entity.Transaction;
import com.sigei.ms_mpesa_integration.dblayer.repository.TransactionRepository;
import com.sigei.ms_mpesa_integration.model.request.CustomerMpesaRequest;
import com.sigei.ms_mpesa_integration.model.request.StkRequest;
import com.sigei.ms_mpesa_integration.model.response.*;
import com.sigei.ms_mpesa_integration.service.ApiService;
import com.sigei.ms_mpesa_integration.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.sigei.ms_mpesa_integration.utils.ApiVariables.*;


@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private static final Logger LOG = LoggerFactory.getLogger(ApiServiceImpl.class);
    private final MpesaService mpesaService;
    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Helper helper;
    private final ConfigProperties properties;

    @Override
    public ApiResponse triggerSTKPush(CustomerMpesaRequest payload) throws Exception {
        try {
            //initiate stk
            if (mpesaService.mpesaToken() == null){
                return new ApiResponse(new ResponseHeader(ERROR_CODE,ERROR_MESSAGE), new ResponseBody(null));
            }
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setBearerAuth(mpesaService.mpesaToken());

            StkRequest stkRequest = StkRequest.builder()
                    .businessShortCode(properties.getBusinessShortCode())
                    .password(mpesaService.getMpesaPassword())
                    .timestamp(helper.getTimeStamp())
                    .transactionType("CustomerPayBillOnline")
                    .amount(payload.getAmount())
                    .partyA(payload.getMsisdn())
                    .partyB(properties.getPartyB())
                    .phoneNumber(payload.getMsisdn())
                    .callBackURL(properties.getCallBackURL())
                    .accountReference("Test")
                    .transactionDesc("Test")
                    .build();

            LOG.info("M-pesa STK request. {}", objectMapper.writeValueAsString(stkRequest));
            HttpEntity<StkRequest> httpEntity = new HttpEntity<>(stkRequest, httpHeaders);
            ResponseEntity<StkResponse> result = restTemplate.postForEntity(properties.getStkUrl(), httpEntity, StkResponse.class);
            LOG.info("M-pesa STK response. {}", objectMapper.writeValueAsString(result.getBody()));
            if (!result.getBody().getResponseCode().equals("0")){
                return new ApiResponse(new ResponseHeader(ERROR_CODE,ERROR_MESSAGE), new ResponseBody(result.getBody()));
            }

            Transaction transaction = Transaction.builder()
                    .merchantRequestID(result.getBody().getMerchantRequestID())
                    .checkoutRequestID(result.getBody().getCheckoutRequestID())
                    .ackResponseCode(result.getBody().getResponseCode())
                    .ackResponseDescription(result.getBody().getResponseDescription())
                    .ackCustomerMessage(result.getBody().getCustomerMessage())
                    .amount(payload.getAmount())
                    .phoneNumber(payload.getMsisdn())
                    .build();

            transactionRepository.save(transaction);

            return new ApiResponse(new ResponseHeader(SUCCESS_CODE,SUCCESS_MESSAGE), new ResponseBody(result.getBody()));
        }catch (Exception e){
            LOG.error("Error occurred. {}", e.getMessage());
            throw new Exception("Error occurred. " + e.getMessage());
        }
    }

    @Override
    public void processStkTransaction(StkConfirmationResponse payload) throws JsonProcessingException {
        System.out.println("payload: " + objectMapper.writeValueAsString(payload));
    }
}
