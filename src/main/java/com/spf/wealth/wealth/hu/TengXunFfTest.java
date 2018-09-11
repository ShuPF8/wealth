package com.spf.wealth.wealth.hu;

import org.junit.Test;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-11 10:09
 */
@Component
public class TengXunFfTest {

    TengXunFf tengXunFf = null;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public void init() {
        tengXunFf = new TengXunFf();
    }


    @Test
    public void execute() throws Exception {
        init();
        while (true) {
            long start = System.currentTimeMillis();
            tengXunFf.login();
            long end = 0l;
            do {
                end = System.currentTimeMillis();
            } while (end - start < 60000);
        }

    }



}
