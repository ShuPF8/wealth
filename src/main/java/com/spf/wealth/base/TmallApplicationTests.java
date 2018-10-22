package com.spf.wealth.base;

import com.alibaba.fastjson.JSONObject;
import com.spf.wealth.huayu.LotteryCore;
import com.spf.wealth.model.LotteryDetail;
import com.spf.wealth.model.LotteryHeDetail;
import com.spf.wealth.service.ILotteryDetailService;
import com.spf.wealth.service.ILotteryHeDatailService;
import com.spf.wealth.utils.Properties;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-10-09 17:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class TmallApplicationTests {

    @Autowired
    public ILotteryDetailService detailService;

    @Autowired
    public ILotteryHeDatailService heDatailService;

    public String path = this.getClass().getClassLoader().getResource("").getPath();

    public SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    //和9 13 夸2 胆12345678
    public String ThirteenNum66 = "01 03 04 05 06 07 08 10 11 12 14 15 16 17 19 21 22 23 25 26 28 29 30 32 33 34 37 38 39 40 41 43 44 47 48 50 51 52 55 56 59 60 61 62 65 66 69 70 71 73 74 77 78 80 82 83 84 87 88 89 91 92 93 95 96 98";

    //和8 10 跨0 定0 胆012345679
    public String EightNum66 = "01 02 03 04 05 06 07 09 12 13 14 15 16 18 21 23 24 25 27 29 31 32 34 36 38 39 41 42 43 45 47 48 49 51 52 54 56 57 58 59 61 63 65 67 68 69 72 74 75 76 78 79 81 83 84 85 86 87 89 92 93 94 95 96 97 98";

    //和11 夸1 胆23456789 定6
    public String ElevenNum66 = "02 03 04 05 07 08 09 13 14 15 17 18 19 20 22 24 25 27 28 30 31 33 35 37 39 40 41 42 44 48 49 50 51 52 53 55 57 58 59 60 61 62 63 64 68 69 70 71 72 73 75 77 79 80 81 82 84 85 88 90 91 93 94 95 97 99";

    //和7 夸3 定2 胆1245689
    public String SevenNum66 = "01 04 05 06 08 09 10 11 13 15 17 18 19 20 21 23 24 26 27 28 29 31 35 38 39 40 44 45 46 48 49 50 51 53 54 55 56 57 59 60 64 65 66 67 68 71 75 76 78 79 80 81 83 84 86 87 88 89 90 91 93 94 95 97 98 99";

    //和6 跨0 4 定9
    public String sixNum66 = "01 02 03 05 07 08 10 12 13 14 16 17 18 20 21 23 25 27 28 30 31 32 34 35 36 38 41 43 45 46 47 50 52 53 54 56 57 58 61 63 64 65 67 68 70 71 72 74 75 76 78 80 81 82 83 85 86 87 90 91 92 93 94 96 97 98";

    // 和12 跨0 5 定1
    public String TwelveNum66 = "02 03 04 06 07 08 09 10 12 13 14 15 17 18 19 20 23 24 25 26 28 29 30 32 34 35 36 37 40 42 43 45 46 47 52 53 54 56 58 59 60 62 63 64 65 67 68 69 70 73 74 76 78 79 80 82 85 86 87 89 90 92 95 96 97 98";

    //和12 跨0 1
    public String Twelve2Num66 = "02 03 04 05 06 07 08 09 13 14 15 16 17 18 19 20 24 25 26 27 28 29 30 31 35 36 37 38 40 41 42 46 47 49 50 51 52 53 58 59 60 61 62 63 64 68 69 70 71 72 73 74 79 80 81 82 83 85 86 90 91 92 94 95 96 97";

    //和4 14 跨1 胆1235689
    public String FourNum66 = "02 03 05 06 08 09 11 14 15 16 17 18 19 20 24 25 26 27 28 29 30 33 35 36 37 38 39 41 42 46 48 49 50 51 52 53 55 57 58 60 61 62 63 64 66 69 71 72 73 75 79 80 81 82 83 84 85 88 90 91 92 93 94 96 97 99";

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    public void dataHandle(JSONObject json, List<Properties> propertiess, LotteryCore lotteryCore, int type, Logger logger) throws Exception {
        for (int i = 0; i < propertiess.size(); i++) {
            Properties properties = propertiess.get(i);

            if (type == 1) {
                lotteryCore.dataHandle(json, properties, i + 1, logger);
            } else if (type == 2 ) {
                lotteryCore.dataHandle2(json, properties, i + 1, logger);
            }

            long time = System.currentTimeMillis();
            if (properties.getHcount() > 4 || properties.getQcount() > 4) { //记录连续四次不中的的日志
                properties.setPrevWireTime(time);
                properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
                properties.setPrevHMaxLzCount(properties.gethMaxlzCount());
                properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
                properties.setPrevQMaxLzCount(properties.getqMaxlzCount());

                // 保存数据库
                this.lotteryDetailHandle(properties);
            }

        }
    }

    public void dataHandleHe(JSONObject json, List<Properties> propertiess, LotteryCore lotteryCore, int type, Logger logger) throws Exception {
        for (int i = 0; i < propertiess.size(); i++) {
            Properties properties = propertiess.get(i);

            if (type == 1) {
                lotteryCore.dataHandleHe(json, properties, i + 1, logger);
            } else if (type == 2 ) {
                lotteryCore.dataHandleHe2(json, properties, i + 1, logger);
            }

            long time = System.currentTimeMillis();
            if (properties.getHdy() > 4 || properties.getHxy() > 4 || properties.getQdy() > 4 || properties.getQxy() > 4) { //记录连续四次不中的的日志
                properties.setPrevWireTime(time);
                properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
                properties.setPrevHMaxLzCount(properties.gethMaxlzCount());
                properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
                properties.setPrevQMaxLzCount(properties.getqMaxlzCount());

                // 保存数据库
                this.lotteryHeDetailHandle(properties);
            }

        }
    }

    private void lotteryDetailHandle(Properties properties) {
        String num = properties.getMyNum();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String name = properties.getName();
        LotteryDetail detail = detailService.findByNumAndDate(num, date, name);
        if (detail == null) {
            detail = new LotteryDetail();
            detail.setTypeName(name);
            detail.setLotteryNum(num);
            detail.setShtj(properties.getShtj());
            detail.setCreateTime(date);
        }

        switch (properties.getHcount()) {
            case 5:
                detail.setFiveHBz(detail.getFiveHBz() + 1);
                break;
            case 6:
                detail.setSixHBz(detail.getSixHBz() + 1);
                break;
            case 7:
                detail.setSevenHBz(detail.getSevenHBz() + 1);
                break;
            case 8:
                detail.setEightHBz(detail.getEightHBz() + 1);
                break;
        }

        if (properties.getHcount() > 8) {
            detail.sethMaxBz(properties.gethMaxbzCount());
        }

        switch (properties.getQcount()) {
            case 5:
                detail.setFiveQBz(detail.getFiveQBz() + 1);
                break;
            case 6:
                detail.setSixQBz(detail.getSixQBz() + 1);
                break;
            case 7:
                detail.setSevenQBz(detail.getSevenQBz() + 1);
                break;
            case 8:
                detail.setEightQBz(detail.getEightQBz() + 1);
                break;
        }

        if (properties.getQcount() > 8) {
            detail.setqMaxBz(properties.getqMaxbzCount());
        }

        if (detail.getId() != null) {
            detailService.updateById(detail);
        } else {
            detailService.insert(detail);
        }

    }

    private void lotteryHeDetailHandle(Properties properties) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String name = properties.getName();
        LotteryHeDetail heDetail = heDatailService.findByNumAndDate(date, name);
        if (heDetail == null) {
            heDetail = new LotteryHeDetail();
            heDetail.setName(properties.getName());
            heDetail.setDate(date);
        }

        switch (properties.getHdy()) {
            case 7:
                heDetail.sethDySeven(heDetail.getqDySeven() + 1);
                break;
            case 8:
                heDetail.sethDyEight(heDetail.gethDyEight() + 1);
                break;
            case 9:
                heDetail.sethDyNine(heDetail.gethDyNine() + 1);
                break;
        }

        if (properties.getHdy() > 9) {
            heDetail.sethDyMax(properties.getHdy());
        }

        switch (properties.getHxy()) {
            case 7:
                heDetail.sethXySeven(heDetail.gethXySeven() + 1);
                break;
            case 8:
                heDetail.sethXyEight(heDetail.gethXyEight() + 1);
                break;
            case 9:
                heDetail.sethXyNine(heDetail.gethXyNine() + 1);
                break;
        }

        if (properties.getHxy() > 9) {
            heDetail.sethXyMax(properties.getHxy());
        }

        switch (properties.getQdy()) {
            case 7:
                heDetail.setqDySeven(heDetail.getqDySeven() + 1);
                break;
            case 8:
                heDetail.setqDyEight(heDetail.getqDyEight() + 1);
                break;
            case 9:
                heDetail.setqDyNine(heDetail.gethDyNine() + 1);
                break;
        }

        if (properties.getQdy() > 9) {
            heDetail.setqDyMax(properties.getQdy());
        }

        switch (properties.getQxy()) {
            case 7:
                heDetail.setqXySeven(heDetail.getqXySeven() + 1);
                break;
            case 8:
                heDetail.setqXyEight(heDetail.getqXyEight() + 1);
                break;
            case 9:
                heDetail.setqXyNine(heDetail.getqXyNine() + 1);
                break;
        }

        if (properties.getQxy() > 9) {
            heDetail.setqXyMax(properties.getQxy());
        }


        if (heDetail.getId() != null) {
            heDatailService.updateById(heDetail);
        } else {
            heDatailService.insert(heDetail);
        }

    }

}
