package com.sigei.ms_mpesa_integration.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StkConfirmationResponse {

    @JsonProperty("Body")
    private Body body;

    @Data
    public static class Body {
        @JsonProperty("stkCallback")
        private StkCallback stkCallback;

        @Data
        public static class StkCallback {
            @JsonProperty("MerchantRequestID")
            private String MerchantRequestID;
            @JsonProperty("CheckoutRequestID")
            private String CheckoutRequestID;
            @JsonProperty("ResultCode")
            private int ResultCode;
            @JsonProperty("ResultDesc")
            private String ResultDesc;

            @JsonProperty("CallbackMetadata")
            private CallbackMetadata CallbackMetadata;

            @Data
            public static class CallbackMetadata {
                @JsonProperty("Item")
                private List<Item> item;

                @Data
                public static class Item {
                    @JsonProperty("Name")
                    private String Name;
                    @JsonProperty("Value")
                    private String Value;
                }
            }
        }
    }
}
