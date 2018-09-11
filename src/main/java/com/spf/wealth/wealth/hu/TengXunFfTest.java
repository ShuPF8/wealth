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
        String myNum = "02,03,04,05,06,07,08,09,13,14,15,16,17,19,20,24,25,26,28,30,31,35,37,39,40,41,42,46,47,48,49,50,51,52,53,57,58,59,60,61,62,64,68,69,70,71,73,75,79,80,82,84,85,86,91,93,94,95,96,97";
        tengXunFf = new TengXunFf(myNum,884,5,5);
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
