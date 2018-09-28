package com.spf.wealth.wealth.hu.huayu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spf.utils.HttpUtil;
import com.spf.wealth.wealth.hu.LotteryUtil;
import com.spf.wealth.wealth.hu.Properties;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-13 14:40
 */
public class HuaYuFfTask {
    private String path = this.getClass().getClassLoader().getResource("").getPath();
    private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    //和9 11 跨 0 1
    private String NineNum = "02,03,04,05,06,07,08,09,13,14,15,16,17,19,20,24,25,26,28,30,31,35,37,39,40,41,42,46,47,48,49,50,51,52,53,57,58,59,60,61,62,64,68,69,70,71,73,75,79,80,82,84,85,86,91,93,94,95,96,97";

    //和8 10 跨 0 1
    private String TenNum = "02,03,04,05,06,07,08,09,13,14,15,16,18,20,24,25,27,29,30,31,36,38,39,40,41,42,47,48,49,50,51,52,55,57,58,59,60,61,63,64,68,69,70,72,74,75,79,81,82,83,84,85,86,90,92,93,94,95,96,97";

    //和7 11 跨 0 1
    private String SevenNum = "02 03 04 05 06 08 09 13 14 15 17 18 19 20 24 26 27 28 30 31 35 36 37 39 40 41 42 46 48 49 50 51 53 57 58 59 60 62 63 64 68 69 71 72 73 75 79 80 81 82 84 85 86 90 91 93 94 95 96 97";

    //和9 10 跨 0 3
    private String NTnum = "01 02 04 05 06 07 08 10 12 13 15 16 17 20 21 23 24 26 29 31 32 34 35 38 39 40 42 43 48 49 50 51 53 56 57 59 60 61 62 65 67 68 70 71 75 76 78 79 80 83 84 86 87 89 92 93 94 95 97 98";

    //和6 12 跨0 1
    private String sixNum = "02 03 04 05 07 08 09 13 14 16 17 18 19 20 25 26 27 28 29 30 31 35 36 37 38 40 41 46 47 49 50 52 53 58 59 61 62 63 64 68 69 70 71 72 73 74 79 80 81 82 83 85 86 90 91 92 94 95 96 97";

    // 和9  夸2 定8   胆1-7
    private String NineNum64 = "00 01 03 04 05 06 07 10 11 12 14 15 16 17 19 21 22 23 25 26 29 30 32 33 34 37 39 40 41 43 44 47 49 50 51 52 55 56 59 60 61 62 65 66 67 69 70 71 73 74 76 77 80 82 83 84 85 87 91 92 93 94 95 96";

    //和10 定7  跨3  胆1-7
    private String TenNum65 = "00 01 02 04 05 06 08 09 10 11 12 13 15 16 18 20 21 22 23 24 26 29 31 32 33 34 35 38 39 40 42 43 44 45 48 49 50 51 53 54 56 59 60 61 62 65 66 68 70 71 72 75 76 78 79 80 81 83 84 86 90 92 93 94 95";

    //和10 夸0 1
    private String TenNum64 = "02 03 04 05 06 07 08 09 13 14 15 16 17 18 20 24 25 26 27 29 30 31 35 36 38 39 40 41 42 47 48 49 50 51 52 53 57 58 59 60 61 62 63 68 69 70 71 72 74 75 79 80 81 83 84 85 86 90 92 93 94 95 96 97";

    //和9 定5 夸1 胆3-9
    private String NineKua1 = "02 03 04 06 07 08 13 14 16 17 19 20 22 24 26 28 29 30 31 33 37 38 39 40 41 42 44 46 47 48 49 50 51 52 53 57 58 59 60 61 62 64 66 68 69 70 71 73 74 77 79 80 82 83 84 86 88 91 92 93 94 96 97 99";

    //和12 定2 跨3 胆0134568
    private String He12 = "00 01 04 05 06 07 08 09 10 11 13 15 16 17 18 19 20 21 23 24 26 28 31 33 34 35 37 38 40 43 44 45 46 49 50 51 53 54 55 56 59 60 61 64 65 67 68 70 71 73 76 78 80 81 83 86 87 88 89 90 91 94 95 98";


    private JSONObject json = new JSONObject();

    private Logger logger = LogManager.getLogger(HuaYuFfTask.class);

    @Test
    public void execute() throws Exception {
        CloseableHttpClient client = HttpUtil.getClient();
        LotteryCore lotteryCore = new LotteryCore(client,"http://pay4.hbcchy.com/lotterytrend/chart/8", logger);

        Map<String, Object> map = new HashMap<>();
        map.put("id","8");
        map.put("pnum","30");

        int nextqh = 851;
        if (nextqh == 1441) {
            nextqh = 1;
        }

        int finalNextqh = nextqh;
        List<Properties> list = new ArrayList<Properties>(){{
            add(new Properties("华宇分分", SevenNum, finalNextqh,7,7,"和7 11 跨 0 1", client, map));
            add(new Properties("华宇分分", NineNum, finalNextqh,7,7,"和9 11 跨 0 1", client, map));
            add(new Properties("华宇分分", TenNum, finalNextqh,7,7,"和8 10 跨 0 1", client, map));
            add(new Properties("华宇分分", NTnum, finalNextqh,7,7,"和9 10 跨 0 3", client, map));
            add(new Properties("华宇分分", sixNum, finalNextqh,7,7,"和6,12", client, map));
            add(new Properties("华宇分分", NineNum64, finalNextqh,6,6,"和9 夸2 定8 胆1-7", client, map));
            add(new Properties("华宇分分", TenNum65, finalNextqh,6,6,"和10 定7 跨3 胆1-7", client, map));
            add(new Properties("华宇分分", TenNum64, finalNextqh,6,6,"和10 夸0 1", client, map));
            add(new Properties("华宇分分", NineKua1, finalNextqh,6,6,"和9 定5 夸1 胆3-9", client, map));
            add(new Properties("华宇分分", He12, finalNextqh,6,6,"和12 定2 跨3 胆0134568", client, map));
        }};

        while (true) {
            long start = System.currentTimeMillis();

            login(lotteryCore,list.get(0), nextqh); //数据查询
            LotteryUtil.dataHandle(list, lotteryCore, json, path, "huayu", logger); // 数据处理

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

    public void login(LotteryCore lotteryCore, Properties properties, int nextqh) throws Exception {
        Integer qh = 0;
        do {
            Thread.sleep(3000);
            json = lotteryCore.query(properties, logger);
            JSONArray datas = json.getJSONArray("data");
            String kjqh = datas.getJSONArray(datas.size() - 1).getString(0);
            qh = Integer.valueOf(kjqh.split("-")[1]);
        } while (nextqh - qh != 0);

    }

    @Test
    public void test() throws Exception {

    }

}

