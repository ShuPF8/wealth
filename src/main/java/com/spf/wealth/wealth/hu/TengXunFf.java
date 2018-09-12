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
    private Logger logger = LogManager.getLogger(TengXunFf.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private String[] toMails = new String[]{"517292069@qq.com","1129711788@qq.com"};

    private CloseableHttpClient client = null;

    public TengXunFf() {
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

    public JSONObject login(int nextqh) throws Exception {
        logger.info("--------------------------- 开始查询 [第 {} 期] {} ---------------------------", nextqh, sdf.format(new Date()));
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

        return JSON.parseObject(data);
    }

    public boolean execute(JSONObject json, Properties properties, int count) throws Exception {
        boolean hflag = false, qflag = false;
        JSONArray datas = json.getJSONArray("data");
        String kjqh = datas.getJSONArray(datas.size() - 1).getString(0);
        Integer qh = Integer.valueOf(kjqh.split("-")[1]);
        String kjxn = datas.getJSONArray(datas.size() - 1).getString(1);

        int nextqh = properties.getNextqh();
        if (nextqh - qh == 1) { //是上一期
            Thread.sleep(5000);
            json = login(nextqh); //重新查询
            return execute(json, properties,count);
        }

        properties.setNextqh(qh + 1);
        String q2 = kjxn.substring(0,2);
        String h2 = kjxn.substring(3,kjxn.length());

        if (count == 1) {
            logger.info("------------------------------ 开奖信息为:" + kjqh + " [" + kjxn + "]\n");
        }

        String title = properties.getShtj();
        int hcount = properties.getHcount();
        String myNum = properties.getMyNum();
        if (!myNum.contains(h2)) {
            properties.setHlz(0);
            hcount++;
            properties.setHcount(hcount);
            hflag = true;
            if (properties.gethMaxbzCount() < hcount) {
                properties.sethMaxbzCount(hcount); //记录最大不中次数
            }
            logger.info("------------------------------ 腾讯分分后二 ["+properties.getShtj()+"] 已有 [ " + hcount + " ] 期不中");
            if (hcount == properties.gethMax()) {
                MailSend.sendMail(title + "-" + qh,"后二","腾讯分分后二["+properties.getShtj()+"]已有 " + hcount + " 期不中，开奖信息" +kjqh+ " " + kjxn + "! 投注号码：" + myNum, toMails);
            }
        }

        int qcount = properties.getQcount();
        if (!myNum.contains(q2)) {
            properties.setQlz(0);
            qcount++;
            properties.setQcount(qcount);
            if (properties.getqMaxbzCount() < qcount) {
                properties.setqMaxbzCount(qcount); //记录最大不中次数
            }
            qflag = true;
            logger.info("------------------------------ 腾讯分分前二 [" + properties.getShtj() + "] 已有 [ " + qcount + " ] 期不中");
            if (qcount == properties.getqMax()) {
                MailSend.sendMail(title + "-" + qh, "前二","腾讯分分前二["+properties.getShtj()+"]已有 " + qcount + " 期不中，开奖信息" +kjqh+ " " + kjxn + "! 投注号码：" + myNum, toMails);
            }
        }

        int hlz = properties.getHlz();
        if (!hflag) {
            properties.setHcount(0);
            hlz++;
            properties.setHlz(hlz);
            if (properties.gethMaxlzCount() < hlz) {
                properties.sethMaxlzCount(hlz); //记录最大中次数
            }
        }

        int qlz = properties.getQlz();
        if (!qflag) {
            properties.setQcount(0);
            qlz++;
            properties.setQlz(qlz);
            if (properties.getqMaxlzCount() < qlz) {
                properties.setqMaxlzCount(qlz); //记录最大中次数
            }
        }

        //logger.info("------------------------------ 腾讯分分后二连中 : {} 次， 腾讯分分前二连中 ：{} 次",hlz,qlz);
        //logger.info("\n------------------------------------------------------------ 执行结束 {}---------------------------------------------\n", sdf.format(new Date()));
        return true;
    }

}
