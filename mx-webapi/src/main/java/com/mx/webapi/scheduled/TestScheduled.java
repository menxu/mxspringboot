package com.mx.webapi.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by menxu on 18/6/28.
 */
@Component
public class TestScheduled {

    @Scheduled(fixedRate = 1000*60*30)
    public void test(){
        System.out.println(LocalDateTime.now());
    }
}
