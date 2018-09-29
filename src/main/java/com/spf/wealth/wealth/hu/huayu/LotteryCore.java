package com.spf.wealth.wealth.hu.huayu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spf.utils.HttpUtil;
import com.spf.utils.mail.MailSend;
import com.spf.wealth.wealth.hu.Properties;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ShuPF
 * @类说明： 公用类
 * @date 2018-09-13 14:12
 */
public class LotteryCore {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private String[] toMails = new String[]{"517292069@qq.com"};

    public LotteryCore(CloseableHttpClient client, String url, Logger logger) {
        try {
            HttpUtil.execute(client,  new HttpGet(url), logger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  查询数据
     */
    public JSONObject query(Properties properties, Logger logger) throws Exception {
        logger.info("--------------------------- "+ properties.getName() +" 开始查询 [第 {} 期] {} ---------------------------", properties.getNextqh(), sdf.format(new Date()));
        HttpUriRequest request = null;
        String url = null, data = null;

        url = "http://pay4.hbcchy.com/lotterytrend/getsscchart";
        request = HttpUtil.formRequest(url, properties.getParams());
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
        request.addHeader("Referer","http://pay4.hbcchy.com/lotterytrend/chart/16");
        request.addHeader("X-Requested-With","XMLHttpRequest");
        request.addHeader("MS-ASPNETCORE-TOKEN","223b09d5-0df8-484d-ad07-c38e6a6a43b8");
        request.addHeader("Host","pay4.hbcchy.com");
        data = HttpUtil.execute(properties.getClient(), request, logger);

        return JSON.parseObject(data);
    }

    /**
     *  数据处理
     * @param json
     * @param properties
     * @param count
     * @param logger
     * @return
     * @throws Exception
     */
    public boolean dataHandle(JSONObject json, Properties properties, int count,  Logger logger) throws Exception {
        boolean hflag = false, qflag = false;
        JSONArray datas = json.getJSONArray("data");
        String kjqh = datas.getJSONArray(datas.size() - 1).getString(0);
        Integer qh = Integer.valueOf(kjqh.split("-")[1]);
        String kjxn = datas.getJSONArray(datas.size() - 1).getString(1);

        int nextqh = properties.getNextqh();
        if (nextqh - qh == 1) { //是上一期
            Thread.sleep(5000);
            json = query(properties, logger); //重新查询
            return dataHandle(json, properties,count, logger);
        }

        properties.setNextqh(qh + 1);
        String q2 = kjxn.substring(0,2);
        String h2 = kjxn.substring(3,kjxn.length());

        if (count == 1) {
            logger.info("------------------------------ "+ properties.getName() +"开奖信息为:" + kjqh + " [" + kjxn + "]\n");
        }

        String title = properties.getName() +"["+ properties.getShtj() + "]";
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
            logger.info("------------------------------ "+ properties.getName() +"后二 ["+properties.getShtj()+"] 已有 [ " + hcount + " ] 期不中");
            if (hcount == properties.gethMax()) {
                MailSend.sendMail(title + "后二" ,kjqh+" [ "+ kjxn +" ]", properties.getName() +"后二["+properties.getShtj()+"]已有 " + hcount + " 期不中。 投注号码：" + myNum, toMails);
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
            logger.info("------------------------------ "+ properties.getName() +"前二 [" + properties.getShtj() + "] 已有 [ " + qcount + " ] 期不中");
            if (qcount == properties.getqMax()) {
                MailSend.sendMail(title + "前二" ,kjqh+" [ "+ kjxn +" ]", properties.getName() +"前二["+properties.getShtj()+"]已有 " + qcount + " 期不中。 投注号码：" + myNum, toMails);
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

    /**
     *  数据处理
     * @param json
     * @param properties
     * @param count
     * @param logger
     * @return
     * @throws Exception
     */
    public boolean dataHandle2(JSONObject json, Properties properties, int count,  Logger logger) throws Exception {
        boolean hflag = false, qflag = false;
        JSONArray datas = json.getJSONArray("data");
        String kjqh = datas.getJSONArray(datas.size() - 1).getString(0); //开奖期号
        String kjxn = datas.getJSONArray(datas.size() - 1).getString(1); //开奖号码

        Integer qh = Integer.valueOf(kjqh);
        int nextqh = properties.getNextqh();
        if (nextqh - qh == 1) { //是上一期
            Thread.sleep(5000);
            json = query(properties, logger); //重新查询
            return dataHandle(json, properties,count, logger);
        }

        properties.setNextqh(qh + 1);
        String q2 = kjxn.substring(0,2);
        String h2 = kjxn.substring(3,kjxn.length());

        if (count == 1) {
            logger.info("------------------------------ "+ properties.getName() +"开奖信息为:" + kjqh + " [" + kjxn + "]\n");
        }

        String title = properties.getName() +"["+ properties.getShtj() + "]";
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
            logger.info("------------------------------ "+ properties.getName() +"后二 ["+properties.getShtj()+"] 已有 [ " + hcount + " ] 期不中");
            if (hcount == properties.gethMax()) {
                MailSend.sendMail(title + "后二" ,kjqh+" [ "+ kjxn +" ]", properties.getName() +"后二["+properties.getShtj()+"]已有 " + hcount + " 期不中。 投注号码：" + myNum, toMails);
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
            logger.info("------------------------------ "+ properties.getName() +"前二 [" + properties.getShtj() + "] 已有 [ " + qcount + " ] 期不中");
            if (qcount == properties.getqMax()) {
                MailSend.sendMail(title + "前二" ,kjqh+" [ "+ kjxn +" ]", properties.getName() +"前二["+properties.getShtj()+"]已有 " + qcount + " 期不中。 投注号码：" + myNum, toMails);
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
