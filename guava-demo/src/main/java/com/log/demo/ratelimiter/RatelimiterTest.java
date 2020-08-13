package com.log.demo.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

public class RatelimiterTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(100);
        for (int i = 1; i < 10 ; i++){
            double acquire = rateLimiter.acquire(2);
            System.out.println("acq:" + acquire + "," + System.currentTimeMillis());
        }


//        for (int i = 1; i < 10 ; i++){
//            double acquire = rateLimiter.(2);
//            System.out.println("acq:" + acquire + "," + System.currentTimeMillis());
//        }



    }


}
