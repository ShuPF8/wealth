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
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 腾讯分分彩
 */
public class TengXunFf {
    public String myNum = "00,02,03,04,05,06,07,09,11,13,14,15,16,18,20,22,24,25,27,29,30,31,33,36,38,39,40,41,42,47,48,49,50,51,52,57,58,59,60,61,63,66,68,69,70,72,74,75,77,79,81,83,84,85,86,88,90,92,93,94,95,96,97,99";

    private Logger logger = LogManager.getLogger(TengXunFf.class);

    private String _qh = "0";

    private int hcount = 0;

    private int qcount = 0;

    CloseableHttpClient client = HttpUtil.getClient();

    public boolean login() throws Exception {
        HttpUriRequest request = null;
        String url = null, data = null;

        url = "http://pay4.hbcchy.com/lotterytrend/chart/16";
        request = new HttpGet(url);
        data = HttpUtil.execute(client, request, logger);

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
        String qh = datas.getJSONArray(datas.size() - 1).getString(0);
        String kjxn = datas.getJSONArray(datas.size() - 1).getString(1);

        if (qh == _qh) {
            Thread.sleep(5000);
            return login();
        }

        _qh = qh;
        String q2 = kjxn.substring(0,2);
        String h2 = kjxn.substring(3,kjxn.length());

        logger.info("开奖信息为:{} {}",qh, kjxn);

        if (!myNum.contains(h2)) {
            hcount++;
            MailSend.sendMail("腾讯分分已有 " + hcount + " 期不中，开奖信息" +qh+ " " + kjxn);
        }

        if (!myNum.contains(q2)) {
            qcount++;
            MailSend.sendMail("腾讯分分已有 " + qcount + " 期不中，开奖信息" +qh+ " " + kjxn);
        }

        return true;
    }

    @Test
    public void test() throws Exception {
        while (true) {
            login();
            Thread.sleep(60000);
        }
    }

}
