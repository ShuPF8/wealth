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
 * 重庆时时彩
 */
public class ChongQingSs {
    private Logger logger = LogManager.getLogger(ChongQingSs.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private CloseableHttpClient client = null;

    public ChongQingSs() {
        client = HttpUtil.getClient();
        HttpUriRequest request = null;
        String url = null, data = null;

        url = "http://pay4.hbcchy.com/lotterytrend/chart/1";
        request = new HttpGet(url);
        try {
            HttpUtil.execute(client, request, logger);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean login(Properties properties) throws Exception {
        logger.info("--------------------------- 开始查询 {} ---------------------------", sdf.format(new Date()));
        boolean hflag = false, qflag = false;
        HttpUriRequest request = null;
        String url = null, data = null;

        Map<String, Object> map = new HashMap<>();
        map.put("id","1");
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

        int nextqh = properties.getNextqh();
        if (nextqh - qh == 1) { //是上一期
            Thread.sleep(5000);
            return login(properties);
        }

        properties.setNextqh(qh + 1);
        String q2 = kjxn.substring(0,2);
        String h2 = kjxn.substring(3,kjxn.length());

        logger.info("------------------------------ 开奖信息为:" + kjqh + " " + kjxn);

        String name = properties.getName();
        int hcount = properties.getHcount();
        String myNum = properties.getMyNum();
        if (!myNum.contains(h2)) {
            properties.setHlz(0);
            hcount++;
            properties.setHcount(hcount);
            hflag = true;
            logger.info(name + "------------------------------ 后二已有 " + hcount + " 期不中");
            if (hcount == properties.gethMax()) {
                hcount = 0;
                MailSend.sendMail(name + "后二已有 " + hcount + " 期不中，开奖信息" +kjqh+ " " + kjxn + "!" + properties.getShtj() + "myNum：" + myNum);
            }
        }

        int qcount = properties.getQcount();
        if (!myNum.contains(q2)) {
            properties.setQlz(0);
            qcount++;
            properties.setQcount(qcount);
            qflag = true;
            logger.info(name + "------------------------------ 前二已有 " + qcount + " 期不中");
            if (qcount == properties.getqMax()) {
                qcount = 0;
                MailSend.sendMail(name + "前二已有 " + qcount + " 期不中，开奖信息" +kjqh+ " " + kjxn + "!" + properties.getShtj() + "myNum：" + myNum);
            }
        }

        int hlz = properties.getHlz();
        if (!hflag) {
            hcount = 0;
            hlz++;
            properties.setHlz(hlz);
        }

        int qlz = properties.getQlz();
        if (!qflag) {
            qcount = 0;
            qlz++;
            properties.setQlz(qlz);
        }

        logger.info("------------------------------ {}后二连中 : {} 次， {}前二连中 ：{} 次",name,hlz,name,qlz);
        return true;
    }

}
