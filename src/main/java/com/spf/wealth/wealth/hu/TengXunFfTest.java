package com.spf.wealth.wealth.hu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-11 10:09
 */
@Component
public class TengXunFfTest {

    private Logger logger = LogManager.getLogger(TengXunFfTest.class);

    @Test
    public void execute() throws Exception {
        String myNum = "02,03,04,05,06,07,08,09,13,14,15,16,17,19,20,24,25,26,28,30,31,35,37,39,40,41,42,46,47,48,49,50,51,52,53,57,58,59,60,61,62,64,68,69,70,71,73,75,79,80,82,84,85,86,91,93,94,95,96,97";
        TengXunFf tengXunFf = new TengXunFf();
        Properties properties = new Properties("腾讯分分", myNum,650,5,5,"和9,11");
        int count = 0;
        while (true) {
            count++;
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
            if (count % 20 == 0) {
                logger.info("腾讯分分[和9-11]今日统计：后二最大连中：{}，后二最大不中：{}，前二最大连中：{}，前二最大不中：{}",properties.gethMaxlzCount(), properties.gethMaxbzCount(),properties.getqMaxlzCount(), properties.getqMaxbzCount());
            }
        }
    }

    @Test
    public void excuteTen() throws Exception{
        String myNum = "02,03,04,05,06,07,08,09,13,14,15,16,18,20,24,25,27,29,30,31,36,38,39,40,41,42,47,48,49,50,51,52,55,57,58,59,60,61,63,64,68,69,70,72,74,75,79,81,82,83,84,85,86,90,92,93,94,95,96,97";
        TengXunFf tengXunFf = new TengXunFf();
        Properties properties = new Properties("腾讯分分", myNum,651,5,5,"和8,10");
        int count = 0;
        while (true) {
            count++;
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
            if (count % 20 == 0) {
                logger.info("腾讯分分[和8-10]今日统计：后二最大连中：{}，后二最大不中：{}，前二最大连中：{}，前二最大不中：{}",properties.gethMaxlzCount(), properties.gethMaxbzCount(),properties.getqMaxlzCount(), properties.getqMaxbzCount());
            }
        }
    }

}
