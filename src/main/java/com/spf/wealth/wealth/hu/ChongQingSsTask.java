package com.spf.wealth.wealth.hu;

import org.junit.Test;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-11 10:09
 */
@Component
public class ChongQingSsTask {

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
        ChongQingSs tengXunFf = new ChongQingSs();
        Properties properties = new Properties("重庆时时", myNum,0,5,5,"和值杀8,10");
        QueryTimeUtil.ssCommon(tengXunFf, properties);
    }

}
