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
 * @类说明： 韩式1.5分彩
 * @date 2018-09-13 14:40
 */
public class HanShi1D5FTask extends TmallApplicationTests {
    private String path = this.getClass().getClassLoader().getResource("").getPath();
    private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    //和9 13 夸2 胆12345678
    private String ThirteenNum66 = "01 03 04 05 06 07 08 10 11 12 14 15 16 17 19 21 22 23 25 26 28 29 30 32 33 34 37 38 39 40 41 43 44 47 48 50 51 52 55 56 59 60 61 62 65 66 69 70 71 73 74 77 78 80 82 83 84 87 88 89 91 92 93 95 96 98";

    //和8 10 跨0 定0 胆012345679
    private String EightNum66 = "01 02 03 04 05 06 07 09 12 13 14 15 16 18 21 23 24 25 27 29 31 32 34 36 38 39 41 42 43 45 47 48 49 51 52 54 56 57 58 59 61 63 65 67 68 69 72 74 75 76 78 79 81 83 84 85 86 87 89 92 93 94 95 96 97 98";

    //和11 夸1 胆23456789 定6
    private String ElevenNum66 = "02 03 04 05 07 08 09 13 14 15 17 18 19 20 22 24 25 27 28 30 31 33 35 37 39 40 41 42 44 48 49 50 51 52 53 55 57 58 59 60 61 62 63 64 68 69 70 71 72 73 75 77 79 80 81 82 84 85 88 90 91 93 94 95 97 99";

    //和7 夸3 定2 胆1245689
    private String SevenNum66 = "01 04 05 06 08 09 10 11 13 15 17 18 19 20 21 23 24 26 27 28 29 31 35 38 39 40 44 45 46 48 49 50 51 53 54 55 56 57 59 60 64 65 66 67 68 71 75 76 78 79 80 81 83 84 86 87 88 89 90 91 93 94 95 97 98 99";

    //和6 跨0 4 定9
    private String sixNum66 = "01 02 03 05 07 08 10 12 13 14 16 17 18 20 21 23 25 27 28 30 31 32 34 35 36 38 41 43 45 46 47 50 52 53 54 56 57 58 61 63 64 65 67 68 70 71 72 74 75 76 78 80 81 82 83 85 86 87 90 91 92 93 94 96 97 98";

    // 和12 跨0 5 定1
    private String TwelveNum66 = "02 03 04 06 07 08 09 10 12 13 14 15 17 18 19 20 23 24 25 26 28 29 30 32 34 35 36 37 40 42 43 45 46 47 52 53 54 56 58 59 60 62 63 64 65 67 68 69 70 73 74 76 78 79 80 82 85 86 87 89 90 92 95 96 97 98";

    //和12 跨0 1
    private String Twelve2Num66 = "02 03 04 05 06 07 08 09 13 14 15 16 17 18 19 20 24 25 26 27 28 29 30 31 35 36 37 38 40 41 42 46 47 49 50 51 52 53 58 59 60 61 62 63 64 68 69 70 71 72 73 74 79 80 81 82 83 85 86 90 91 92 94 95 96 97";

    //和4 14 跨1 胆1235689
    private String FourNum66 = "02 03 05 06 08 09 11 14 15 16 17 18 19 20 24 25 26 27 28 29 30 33 35 36 37 38 39 41 42 46 48 49 50 51 52 53 55 57 58 60 61 62 63 64 66 69 71 72 73 75 79 80 81 82 83 84 85 88 90 91 92 93 94 96 97 99";


    private JSONObject json = new JSONObject();

    private Logger logger = LogManager.getLogger(HanShi1D5FTask.class);

    @Test
    public void execute() throws Exception {
        CloseableHttpClient client = HttpUtil.getClient();
        LotteryCore lotteryCore = new LotteryCore(client,"http://pay4.hbcchy.com/lotterytrend/chart/11", logger);

        Map<String, Object> map = new HashMap<>();
        map.put("id","11");
        map.put("pnum","30");

        int nextqh = 2301988;
        int finalNextqh = nextqh;
        List<Properties> list = new ArrayList<Properties>(){{
            add(new Properties("韩式1.5分彩", ThirteenNum66, finalNextqh,6,6,"Q 和9 13 夸2 胆12345678", client, map));
            add(new Properties("韩式1.5分彩", EightNum66, finalNextqh,6,6,"Q 和8 10 跨0 定0 胆012345679", client, map));
            add(new Properties("韩式1.5分彩", ElevenNum66, finalNextqh,6,6,"Q 和11 夸1 胆23456789 定6", client, map));
            add(new Properties("韩式1.5分彩", SevenNum66, finalNextqh,6,6,"Q 和7 夸3 定2 胆1245689", client, map));
            add(new Properties("韩式1.5分彩", sixNum66, finalNextqh,6,6,"Q 和6 跨0 4 定9", client, map));
            add(new Properties("韩式1.5分彩", TwelveNum66, finalNextqh,6,6,"W 和12 跨0 5 定1", client, map));
            add(new Properties("韩式1.5分彩", Twelve2Num66, finalNextqh,6,6,"W 和12 跨0 1", client, map));
            add(new Properties("韩式1.5分彩", FourNum66, finalNextqh,6,6,"W 和4 14 跨1 胆1235689", client, map));
        }};

        while (true) {
            long start = System.currentTimeMillis();

            login(lotteryCore,list.get(0), nextqh); //数据查询
            LotteryUtil.dataHandle2(list, lotteryCore, json, path, "hanshi1D5", logger); // 数据处理

            nextqh++;
            logger.info("--------------------------------------- 执行结束 {}----------------------------------------\n", _sdf.format(new Date()));

            long end = System.currentTimeMillis();
            long sjc = (end - start) / 1000; //时间差 秒
            if (sjc < 50) {
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
            qh = Integer.valueOf(kjqh);
        } while (nextqh - qh != 0);

    }

}

