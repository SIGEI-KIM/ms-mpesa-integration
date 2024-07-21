package com.sigei.ms_mpesa_integration.dblayer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iD;

    @JsonProperty("MerchantRequestID")
    private String merchantRequestID;
    @JsonProperty("CheckoutRequestID")
    private String checkoutRequestID;
    @JsonProperty("AckResponseCode")
    private String ackResponseCode;
    @JsonProperty("AckResponseDescription")
    private String ackResponseDescription;
    @JsonProperty("AckCustomerMessage")
    private String ackCustomerMessage;
    @JsonProperty("MpesaResponseResultCode")
    private int mpesaResponseResultCode;
    @JsonProperty("MpesaResponseResultDesc")
    private String mpesaResponseResultDesc;
    @JsonProperty("Amount")
    private String amount;
    @JsonProperty("MpesaReceiptNumber")
    private String mpesaReceiptNumber;
    @JsonProperty("TransactionDate")
    private String transactionDate;
    @JsonProperty("PhoneNumber")
    private String phoneNumber;
}
