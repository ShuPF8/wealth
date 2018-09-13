package com.spf.wealth.wealth.hu.huayu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spf.utils.HttpUtil;
import com.spf.utils.mail.MailSend;
import com.spf.wealth.wealth.hu.Properties;
import com.spf.wealth.wealth.hu.Write;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-13 14:40
 */
public class HuaYuFfTask {
    private String path = this.getClass().getClassLoader().getResource("").getPath();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    private int indexItart = path.indexOf("/target");

    //和9 11 跨 0 1
    private String NineNum = "02,03,04,05,06,07,08,09,13,14,15,16,17,19,20,24,25,26,28,30,31,35,37,39,40,41,42,46,47,48,49,50,51,52,53,57,58,59,60,61,62,64,68,69,70,71,73,75,79,80,82,84,85,86,91,93,94,95,96,97";

    //和8 10 跨 0 1
    private String TenNum = "02,03,04,05,06,07,08,09,13,14,15,16,18,20,24,25,27,29,30,31,36,38,39,40,41,42,47,48,49,50,51,52,55,57,58,59,60,61,63,64,68,69,70,72,74,75,79,81,82,83,84,85,86,90,92,93,94,95,96,97";

    //和7 10 跨 0 1
    private String SevenNum = "02 03 04 05 06 08 09 13 14 15 17 18 19 20 24 26 27 28 30 31 35 36 37 39 40 41 42 46 48 49 50 51 53 57 58 59 60 62 63 64 68 69 71 72 73 75 79 80 81 82 84 85 86 90 91 93 94 95 96 97";

    //和9 10 跨 0 3
    private String NTnum = "01 02 04 05 06 07 08 10 12 13 15 16 17 20 21 23 24 26 29 31 32 34 35 38 39 40 42 43 48 49 50 51 53 56 57 59 60 61 62 65 67 68 70 71 75 76 78 79 80 83 84 86 87 89 92 93 94 95 97 98";

    //和6 12 跨0 1
    private String sixNum = "02 03 04 05 07 08 09 13 14 16 17 18 19 20 25 26 27 28 29 30 31 35 36 37 38 40 41 46 47 49 50 52 53 58 59 61 62 63 64 68 69 70 71 72 73 74 79 80 81 82 83 85 86 90 91 92 94 95 96 97";

    private JSONObject json = new JSONObject();

    private Logger logger = LogManager.getLogger(HuaYuFfTask.class);

    @Test
    public void execute() throws Exception {
        CloseableHttpClient client = HttpUtil.getClient();
        LotteryCore lotteryCore = new LotteryCore(client,"http://pay4.hbcchy.com/lotterytrend/chart/8", logger);

        Map<String, Object> map = new HashMap<>();
        map.put("id","8");
        map.put("pnum","30");

        int nextqh = 1014;
        if (nextqh == 1441) {
            nextqh = 1;
        }
        Properties sevenProperties = new Properties("华宇分分", SevenNum,nextqh,7,7,"和7,11", client, map);
        Properties nineProperties = new Properties("华宇分分", NineNum,nextqh,7,7,"和9,11", client, map);
        Properties tenProperties = new Properties("华宇分分", TenNum,nextqh,7,7,"和8,10", client, map);
        Properties ntProperties = new Properties("华宇分分", NTnum,nextqh,7,7,"和9,10", client, map);
        Properties sixProperties = new Properties("华宇分分", sixNum,nextqh,7,7,"和6,12", client, map);

        while (true) {
            long start = System.currentTimeMillis();

            login(lotteryCore,sevenProperties, nextqh);

            executeNine(lotteryCore,nineProperties);
            excuteSeven(lotteryCore,sevenProperties);
            excuteTen(lotteryCore,tenProperties);
            excuteNT(lotteryCore,ntProperties);
            excuteSix(lotteryCore, sixProperties);

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

    public void executeNine(LotteryCore lotteryCore, Properties properties) throws Exception {
        path = path.substring(0, indexItart) + "\\log\\" + sdf.format(new Date()) + "-华宇分分[和9-11]统计.txt";

        lotteryCore.dataHandle(json, properties,1, logger);

        if (properties.getPrevHMaxBuCount() < properties.gethMaxbzCount() || properties.getPrevQMaxBuCount() < properties.getqMaxbzCount()) {
            properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
            properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
            String content = _sdf.format(new Date()) + "\n 华宇分分[和9-11]今日统计：后二最大连中："+ properties.gethMaxlzCount() +"，后二最大不中："+properties.gethMaxbzCount()+"，前二最大连中："+properties.getqMaxlzCount()+"，前二最大不中：" + properties.getqMaxbzCount() +"\n";
            Write.write(content, path,true);
        }
    }

    public void excuteTen(LotteryCore lotteryCore, Properties properties) throws Exception{
        path = path.substring(0, indexItart) + "\\log\\" + sdf.format(new Date()) + "-华宇分分[和8-10]统计.txt";

        lotteryCore.dataHandle(json, properties,2, logger);

        if (properties.getPrevHMaxBuCount() < properties.gethMaxbzCount() || properties.getPrevQMaxBuCount() < properties.getqMaxbzCount()) {
            properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
            properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
            String content = _sdf.format(new Date()) + "\n 华宇分分[和8-10]今日统计：后二最大连中："+ properties.gethMaxlzCount() +"，后二最大不中："+properties.gethMaxbzCount()+"，前二最大连中："+properties.getqMaxlzCount()+"，前二最大不中：" + properties.getqMaxbzCount() + "\n";
            Write.write(content, path,true);
        }
    }

    public void excuteSeven(LotteryCore lotteryCore, Properties properties) throws Exception{
        path = path.substring(0, indexItart) + "\\log\\" + sdf.format(new Date()) + "-华宇分分[和7-11]统计.txt";

        lotteryCore.dataHandle(json, properties,3, logger);

        if (properties.getPrevHMaxBuCount() < properties.gethMaxbzCount() || properties.getPrevQMaxBuCount() < properties.getqMaxbzCount()) {
            properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
            properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
            String content = _sdf.format(new Date()) + "\n 华宇分分[和7-11]今日统计：后二最大连中："+ properties.gethMaxlzCount() +"，后二最大不中："+properties.gethMaxbzCount()+"，前二最大连中："+properties.getqMaxlzCount()+"，前二最大不中：" + properties.getqMaxbzCount() + "\n";
            Write.write(content, path,true);
        }
    }

    public void excuteNT(LotteryCore lotteryCore, Properties properties) throws Exception{
        path = path.substring(0, indexItart) + "\\log\\" + sdf.format(new Date()) + "-华宇分分[和9-10]统计.txt";

        lotteryCore.dataHandle(json, properties,3, logger);

        if (properties.getPrevHMaxBuCount() < properties.gethMaxbzCount() || properties.getPrevQMaxBuCount() < properties.getqMaxbzCount()) {
            properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
            properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
            String content = _sdf.format(new Date()) + "\n 华宇分分[和9-10]今日统计：后二最大连中："+ properties.gethMaxlzCount() +"，后二最大不中："+properties.gethMaxbzCount()+"，前二最大连中："+properties.getqMaxlzCount()+"，前二最大不中：" + properties.getqMaxbzCount() + "\n";
            Write.write(content, path,true);
        }
    }

    public void excuteSix(LotteryCore lotteryCore, Properties properties) throws Exception{
        path = path.substring(0, indexItart) + "\\log\\" + sdf.format(new Date()) + "-华宇分分[和6-12]统计.txt";

        lotteryCore.dataHandle(json, properties,3, logger);

        if (properties.getPrevHMaxBuCount() < properties.gethMaxbzCount() || properties.getPrevQMaxBuCount() < properties.getqMaxbzCount()) {
            properties.setPrevHMaxBuCount(properties.gethMaxbzCount());
            properties.setPrevQMaxBuCount(properties.getqMaxbzCount());
            String content = _sdf.format(new Date()) + "\n 华宇分分[和6-12]今日统计：后二最大连中："+ properties.gethMaxlzCount() +"，后二最大不中："+properties.gethMaxbzCount()+"，前二最大连中："+properties.getqMaxlzCount()+"，前二最大不中：" + properties.getqMaxbzCount() + "\n";
            Write.write(content, path,true);
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
        String[] toMails = new String[]{"517292069@qq.com"};
        MailSend.sendMail(  "腾讯分分测试-和5，7" ,"腾讯分分后二", "腾讯分分后二[和5,7]已有 6 期不中，开奖信息 20180913-5555! 投注号码：" + NineNum, toMails);
    }

}
