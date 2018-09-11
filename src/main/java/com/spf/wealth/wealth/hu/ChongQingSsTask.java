package com.spf.wealth.wealth.hu;

import org.junit.Test;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-11 10:09
 */
@Component
public class ChongQingSsTask {

    long tenM = 10 * 60 * 1000; //十分钟

    long fiveM = 5 * 60 * 1000; //五分钟

    String date = "2018-09-11 22:00:00";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Test
    public void execute() throws Exception {
        String myNum = "02,03,04,05,06,07,08,09,13,14,15,16,17,19,20,24,25,26,28,30,31,35,37,39,40,41,42,46,47,48,49,50,51,52,53,57,58,59,60,61,62,64,68,69,70,71,73,75,79,80,82,84,85,86,91,93,94,95,96,97";
        ChongQingSs tengXunFf = new ChongQingSs();
        Properties properties = new Properties("重庆时时", myNum,0,5,5,"和值杀9,11");
        QueryTimeUtil.ssCommon(tengXunFf, properties);
    }

    @Test
    public void excuteTen() throws Exception{
        String myNum = "02,03,04,05,06,07,08,09,13,14,15,16,18,20,24,25,27,29,30,31,36,38,39,40,41,42,47,48,49,50,51,52,55,57,58,59,60,61,63,64,68,69,70,72,74,75,79,81,82,83,84,85,86,90,92,93,94,95,96,97";
        ChongQingSs chongQingSs = new ChongQingSs();
        Properties properties = new Properties("重庆时时", myNum,0,5,5,"和值杀8,10");
        long falg = 0;
        while (true) {
            if ((new Date()).compareTo(sdf.parse(date)) >= 0) {
                falg = fiveM;
            } else {
                falg = tenM;
            }
            long start = System.currentTimeMillis();
            chongQingSs.login(properties);
            long end = 0l;
            do {
                end = System.currentTimeMillis();
            } while (end - start < falg);
        }
    }

    @Test
    public void time() throws ParseException {
        Date dateT = sdf.parse(date);
        System.out.println();
    }

}
