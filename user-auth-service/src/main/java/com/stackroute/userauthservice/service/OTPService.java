package com.stackroute.userauthservice.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {


    private static final Integer EXPIRE_MINS = 10; // Otp will be expired in 10 mintes
    private LoadingCache<String, Integer> otpCache;  //creating cache to store otp as a key value pair

    public OTPService() {
        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }
/*Method to generate a  OTP*/
    public int generateOTP(String key) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return otp;
    }

    /*Method to fetch OTP*/
    public int getOTP(String key) {
        try {
            return otpCache.get(key);
        } catch (Exception e) {
            return 0;
        }


    }
    /*Method to delete OTP once it is validated*/
    public void clearOTP(String key){
        otpCache.invalidate(key);
    }
}
