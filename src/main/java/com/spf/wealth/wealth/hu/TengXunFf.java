package com.spf.wealth.wealth.hu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spf.utils.HttpUtil;
import com.spf.utils.mail.MailSend;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 腾讯分分彩
 */
public class TengXunFf {
    public String myNum = "02,03,04,05,06,07,08,09,13,14,15,16,17,19,20,24,25,26,28,30,31,35,37,39,40,41,42,46,47,48,49,50,51,52,53,57,58,59,60,61,62,64,68,69,70,71,73,75,79,80,82,84,85,86,91,93,94,95,96,97";

    private Logger logger = LogManager.getLogger(TengXunFf.class);

    private Integer nextqh = 744; //开奖期号

    private int hcount = 0; //后二不中次数

    private int hlz = 0; //后二二连中次数

    private int hMax; //后二最大不中次数

    private int qcount = 0; //前二不中次数

    private int qlz = 0; //前二连中次数

    private int qMax; //前二最大不中次数

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private CloseableHttpClient client = null;

    public TengXunFf() {
        super();
    }

    public TengXunFf(String myNum, Integer nextqh, int hMax, int qMax) {
        this.myNum = myNum;
        this.nextqh = nextqh;
        this.hMax = hMax;
        this.qMax = qMax;

        client = HttpUtil.getClient();
        HttpUriRequest request = null;
        String url = null, data = null;

        url = "http://pay4.hbcchy.com/lotterytrend/chart/16";
        request = new HttpGet(url);
        try {
            HttpUtil.execute(client, request, logger);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean login() throws Exception {
        logger.info("--------------------------- 开始查询 {} ---------------------------", sdf.format(new Date()));
        boolean hflag = false, qflag = false;
        HttpUriRequest request = null;
        String url = null, data = null;

        Map<String, Object> map = new HashMap<>();
        map.put("id","16");
        map.put("pnum","30");

        url = "http://pay4.hbcchy.com/lotterytrend/getsscchart";
        request = HttpUtil.formRequest(url, map);
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
        request.addHeader("Referer","http://pay4.hbcchy.com/lotterytrend/chart/16");
        request.addHeader("X-Requested-With","XMLHttpRequest");
        request.addHeader("MS-ASPNETCORE-TOKEN","223b09d5-0df8-484d-ad07-c38e6a6a43b8");
        request.addHeader("Host","pay4.hbcchy.com");
        data = HttpUtil.execute(client, request, logger);

        JSONObject json = JSON.parseObject(data);
        JSONArray datas = json.getJSONArray("data");
        String kjqh = datas.getJSONArray(datas.size() - 1).getString(0);
        Integer qh = Integer.valueOf(kjqh.split("-")[1]);
        String kjxn = datas.getJSONArray(datas.size() - 1).getString(1);

        if (nextqh - qh == 1) { //是上一期
            Thread.sleep(5000);
            return login();
        }

        nextqh = qh + 1;
        String q2 = kjxn.substring(0,2);
        String h2 = kjxn.substring(3,kjxn.length());

        logger.info("------------------------------ 开奖信息为:" + kjqh + " " + kjxn);

        if (!myNum.contains(h2)) {
            hlz = 0;
            hcount++;
            hflag = true;
            logger.info("腾讯分分后二已有 " + hcount + " 期不中，开奖信息" +kjqh+ " " + kjxn);
            if (hcount == hMax) {
                hcount = 0;
                MailSend.sendMail("腾讯分分后二已有 " + hcount + " 期不中，开奖信息" +kjqh+ " " + kjxn);
            }
        }

        if (!myNum.contains(q2)) {
            qlz = 0;
            qcount++;
            qflag = true;
            logger.info("腾讯分分前二已有 " + qcount + " 期不中，开奖信息" +kjqh+ " " + kjxn);
            if (qcount == qMax) {
                qcount = 0;
                MailSend.sendMail("腾讯分分前二已有 " + qcount + " 期不中，开奖信息" +kjqh+ " " + kjxn);
            }
        }

        if (!hflag) {
            hcount = 0;
            hlz++;
        }

        if (!qflag) {
            qcount = 0;
            qlz++;
        }

        logger.info("------------------------------ hcount {}, qcount {}:",hcount, qcount);
        logger.info("------------------------------ 后二连中 : {} 次， 前二连中 ：{} 次",hlz,qlz);
        return true;
    }

}
