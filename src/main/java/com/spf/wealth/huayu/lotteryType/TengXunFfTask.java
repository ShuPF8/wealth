package com.spf.wealth.huayu.lotteryType;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spf.utils.HttpUtil;
import com.spf.wealth.base.TmallApplicationTests;
import com.spf.wealth.huayu.LotteryCore;
import com.spf.wealth.model.DwdModel;
import com.spf.wealth.model.LotteryDetail;
import com.spf.wealth.utils.LotteryUtil;
import com.spf.wealth.utils.Properties;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-13 14:40
 */
public class TengXunFfTask extends TmallApplicationTests {
    private JSONObject json = new JSONObject();

    private Logger logger = LogManager.getLogger(TengXunFfTask.class);

    @Test
    public void execute() throws Exception {
        CloseableHttpClient client = HttpUtil.getClient();
        LotteryCore lotteryCore = new LotteryCore(client,"http://pay4.hbcchy.com/lotterytrend/chart/16", logger);

        Map<String, Object> map = new HashMap<>();
        map.put("id","16"); //腾讯
        map.put("pnum","30");

        int q_bz_num = 8;
        int h_bz_num = 8;
        int nextqh = 1053;
        if (nextqh == 1441) {
            nextqh = 1;
        }

        int finalNextqh = nextqh;
        Properties properties = new Properties("腾讯分分", "dy", BigDecimal.valueOf(1.5), finalNextqh, client, map);
        List<Properties> list = new ArrayList<Properties>(){{
            add(new Properties("腾讯分分", "dy", BigDecimal.valueOf(1.5), finalNextqh, client, map));
//            add(new Properties("腾讯分分", ThirteenNum66, finalNextqh, h_bz_num, q_bz_num,"Q 和9 13 夸2 胆12345678", client, map));
//            add(new Properties("腾讯分分", EightNum66, finalNextqh,h_bz_num, q_bz_num,"Q 和8 10 跨0 定0 胆012345679", client, map));
//            add(new Properties("腾讯分分", ElevenNum66, finalNextqh, h_bz_num, q_bz_num,"Q 和11 夸1 胆23456789 定6", client, map));
//            add(new Properties("腾讯分分", SevenNum66, finalNextqh, h_bz_num, q_bz_num,"Q 和7 夸3 定2 胆1245689", client, map));
//            add(new Properties("腾讯分分", sixNum66, finalNextqh, h_bz_num, q_bz_num,"Q 和6 跨0 4 定9", client, map));
//            add(new Properties("腾讯分分", TwelveNum66, finalNextqh, h_bz_num, q_bz_num,"W 和12 跨0 5 定1", client, map));
//            add(new Properties("腾讯分分", Twelve2Num66, finalNextqh, h_bz_num, q_bz_num,"W 和12 跨0 1", client, map));
//            add(new Properties("腾讯分分", FourNum66, finalNextqh, h_bz_num,  q_bz_num,"W 和4 14 跨1 胆1235689", client, map));
        }};
        DwdModel dwdModel = new DwdModel("01 02 03 04 05 07 08 10 12 13 14 16 17 19 20 21 25 26 29 30 31 34 35 39 40 41 43 46 47 48 49 50 52 53 56 57 58 59 61 62 64 65 67 68 69 70 71 74 75 76 79 80 84 85 86 89 91 92 93 94 95 96 97 98");
        dwdModel.setLength(6);
        while (true) {
            long start = System.currentTimeMillis();

            login(lotteryCore,properties, properties.getNextqh()); //数据查询
            //LotteryUtil.dataHandle(list, lotteryCore, json, path, "tengxun", logger); // 数据处理
            //super.dataHandleHe(json, list,lotteryCore, 1, logger); // 数据处理
            lotteryCore.brainPowerDoNumHandle(json, properties, dwdModel,  logger);

            nextqh++;
            logger.info("--------------------------------------- 执行结束 {}----------------------------------------\n", _sdf.format(new Date()));

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

    private void login(LotteryCore lotteryCore, Properties properties, int nextqh) throws Exception {
        Integer qh = 0;
        int count = 0;
        do {
            count++;
            Thread.sleep(3000);
            json = lotteryCore.query(properties, logger);
            JSONArray datas = json.getJSONArray("data");
            String kjqh = datas.getJSONArray(datas.size() - 1).getString(0);
            qh = Integer.valueOf(kjqh.split("-")[1]);
            if (count == 10) {
                throw new Exception("超时");
            }
        } while (nextqh - qh != 0);

    }

    @Test
    public void test() {
        LotteryUtil.write("测试", "F:\\SPF\\github\\wealth\\log\\tengxun\\2018-09-17\\", "2018-09-17-腾讯分分[和8,10]统计.txt", true);
    }

}
