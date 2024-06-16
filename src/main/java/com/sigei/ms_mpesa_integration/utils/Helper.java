package com.sigei.ms_mpesa_integration.utils;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Helper {
    public String getTimeStamp(){
        return  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
