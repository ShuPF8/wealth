package com.spf.wealth.huayu.lotteryType;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spf.utils.HttpUtil;
import com.spf.wealth.base.TmallApplicationTests;
import com.spf.wealth.huayu.LotteryCore;
import com.spf.wealth.utils.LotteryUtil;
import com.spf.wealth.utils.Properties;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ShuPF
 * @类说明： 东京1.5分彩
 * @date 2018-09-13 14:40
 */
public class DongJing1D5FTask extends TmallApplicationTests {
    private JSONObject json = new JSONObject();

    private Logger logger = LogManager.getLogger(DongJing1D5FTask.class);

    @Test
    public void execute() throws Exception {
        CloseableHttpClient client = HttpUtil.getClient();
        LotteryCore lotteryCore = new LotteryCore(client,"http://pay4.hbcchy.com/lotterytrend/chart/12", logger);

        Map<String, Object> map = new HashMap<>();
        map.put("id","12");
        map.put("pnum","30");

        int q_bz_num = 8;
        int h_bz_num = 8;
        int nextqh = 502;
        if (nextqh == 1441) {
            nextqh = 1;
        }

        int finalNextqh = nextqh;
        List<Properties> list = new ArrayList<Properties>(){{
            add(new Properties("东京1.5分彩", ThirteenNum66, finalNextqh, h_bz_num,q_bz_num,"Q 和9 13 夸2 胆12345678", client, map));
//            add(new Properties("东京1.5分彩", EightNum66, finalNextqh, h_bz_num, q_bz_num,"Q 和8 10 跨0 定0 胆012345679", client, map));
//            add(new Properties("东京1.5分彩", ElevenNum66, finalNextqh, h_bz_num, q_bz_num,"Q 和11 夸1 胆23456789 定6", client, map));
//            add(new Properties("东京1.5分彩", SevenNum66, finalNextqh, h_bz_num, q_bz_num,"Q 和7 夸3 定2 胆1245689", client, map));
//            add(new Properties("东京1.5分彩", sixNum66, finalNextqh, h_bz_num, q_bz_num,"Q 和6 跨0 4 定9", client, map));
//            add(new Properties("东京1.5分彩", TwelveNum66, finalNextqh, h_bz_num, q_bz_num,"W 和12 跨0 5 定1", client, map));
//            add(new Properties("东京1.5分彩", Twelve2Num66, finalNextqh, h_bz_num, q_bz_num,"W 和12 跨0 1", client, map));
//            add(new Properties("东京1.5分彩", FourNum66, finalNextqh, h_bz_num, q_bz_num,"W 和4 14 跨1 胆1235689", client, map));
        }};

        while (true) {
            long start = System.currentTimeMillis();

            login(lotteryCore,list.get(0), nextqh); //数据查询
            //LotteryUtil.dataHandle(list, lotteryCore, json, path, "dongjing1D5", logger); // 数据处理
            super.dataHandleHe(json, list,lotteryCore,1, logger); // 数据处理

            nextqh++;
            logger.info("--------------------------------------- 执行结束 {}----------------------------------------\n", _sdf.format(new Date()));

            long end = System.currentTimeMillis();
            long sjc = (end - start) / 1000; //时间差 秒
            if (sjc < 48) {
                Thread.sleep((90 - sjc) / 2 * 1000);
            }

            end = System.currentTimeMillis();
            sjc = (90 - (end - start)) / 1000; //时间差 秒

            if (sjc > 30) {
                Thread.sleep(sjc / 2 * 1000);
            }

            do {
                end = System.currentTimeMillis();
            } while (end - start < 90000);

        }
    }

    private void login(LotteryCore lotteryCore, Properties properties, int nextqh) throws Exception {
        Integer qh = 0;
        do {
            Thread.sleep(14000);
            json = lotteryCore.query(properties, logger);
            JSONArray datas = json.getJSONArray("data");
            String kjqh = datas.getJSONArray(datas.size() - 1).getString(0);
            qh = Integer.valueOf(kjqh.split("-")[1]);
        } while (nextqh - qh != 0);

    }

}

