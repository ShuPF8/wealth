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

    @Test
    public void execute() throws Exception {
        String myNum = "02,03,04,05,06,07,08,09,13,14,15,16,17,19,20,24,25,26,28,30,31,35,37,39,40,41,42,46,47,48,49,50,51,52,53,57,58,59,60,61,62,64,68,69,70,71,73,75,79,80,82,84,85,86,91,93,94,95,96,97";
        TengXunFf tengXunFf = new TengXunFf();
        Properties properties = new Properties("腾讯分分", myNum,1262,5,5,"和值杀9,11");
        while (true) {
            long start = System.currentTimeMillis();
            tengXunFf.login(properties);
            long end = System.currentTimeMillis();
            long sjc = (end - start) / 1000; //时间差 秒
            if (sjc < 30) {
                Thread.sleep((60 - sjc) / 2 * 1000);
            }
            do {
                end = System.currentTimeMillis();
            } while (end - start < 60000);
        }
    }

    @Test
    public void excuteTen() throws Exception{
        String myNum = "02,03,04,05,06,07,08,09,13,14,15,16,18,20,24,25,27,29,30,31,36,38,39,40,41,42,47,48,49,50,51,52,55,57,58,59,60,61,63,64,68,69,70,72,74,75,79,81,82,83,84,85,86,90,92,93,94,95,96,97";
        TengXunFf tengXunFf = new TengXunFf();
        Properties properties = new Properties("腾讯分分", myNum,1263,5,5,"和值杀8,10");
        while (true) {
            long start = System.currentTimeMillis();
            tengXunFf.login(properties);
            long end = System.currentTimeMillis();
            long sjc = (end - start) / 1000; //时间差 秒
            if (sjc < 25) {
                Thread.sleep((60 - sjc) / 2 * 1000);
            }
            do {
                end = System.currentTimeMillis();
            } while (end - start < 60000);
        }
    }

}
