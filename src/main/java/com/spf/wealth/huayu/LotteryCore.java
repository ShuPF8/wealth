package com.spf.wealth.huayu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spf.utils.HttpUtil;
import com.spf.utils.mail.MailSend;
import com.spf.wealth.model.DwdModel;
import com.spf.wealth.utils.LotteryHandleUtil;
import com.spf.wealth.utils.Properties;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ShuPF
 * @类说明： 公用类
 * @date 2018-09-13 14:12
 */
public class LotteryCore {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private String[] toMails = new String[]{"517292069@qq.com"};
             //: new String[]{"1215852831@qq.com"};

    private int[] bs = new int[]{1,2,6,18,54,162};


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


    /**
     *  数据处理
     * @param json
     * @param properties
     * @param count
     * @param logger
     * @return
     * @throws Exception
     */
    public boolean dataHandleHe(JSONObject json, Properties properties, int count,  Logger logger) throws Exception {
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
        this.zjCount(properties,kjxn, kjqh, count, logger);

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
    public boolean dataHandleHe2(JSONObject json, Properties properties, int count,  Logger logger) throws Exception {
        JSONArray datas = json.getJSONArray("data");
        String kjqh = datas.getJSONArray(datas.size() - 1).getString(0); //开奖期号
        String kjxn = datas.getJSONArray(datas.size() - 1).getString(1); //开奖号码

        Integer qh = Integer.valueOf(kjqh);
        int nextqh = properties.getNextqh();
        if (nextqh - qh == 1) { //是上一期
            Thread.sleep(5000);
            json = query(properties, logger); //重新查询
            return dataHandleHe2(json, properties,count, logger);
        }

        properties.setNextqh(qh + 1);
        this.zjCount(properties,kjxn, kjqh, count, logger);

        return true;
    }

    public boolean dataHandDwd(JSONObject json,Properties properties, DwdModel dwdModel, int count,  Logger logger) throws Exception {
        JSONArray datas = json.getJSONArray("data");
        String kjqh = datas.getJSONArray(datas.size() - 1).getString(0);
        Integer qh = Integer.valueOf(kjqh.split("-")[1]);
        String kjxn = datas.getJSONArray(datas.size() - 1).getString(1);

        int nextqh = properties.getNextqh();
        if (nextqh - qh == 1) { //是上一期
            Thread.sleep(5000);
            json = query(properties, logger); //重新查询
            return dataHandDwd(json, properties, dwdModel, count, logger);
        }
        properties.setNextqh(qh + 1);
        String dwNum =kjxn.substring(dwdModel.getLocation() - 1, dwdModel.getLocation());

        boolean flag = true;
        if (!dwdModel.getTzNum().contains(dwNum)) { // 不中时重新筛选热号
            flag = false;
            dwdModel.setBuCount(dwdModel.getBuCount() + 1);
            dwdModel.setNotZjCount(dwdModel.getNotZjCount() + 1);
            dwdModel.setLzCount(0);
            if (dwdModel.getMaxBuCount() < dwdModel.getBuCount()) {
                dwdModel.setMaxBuCount(dwdModel.getBuCount());
            }
        }

        if (flag) {
            dwdModel.setZjCount(dwdModel.getZjCount() + 1);
            dwdModel.setLzCount(dwdModel.getLzCount() + 1);
            dwdModel.setBuCount(0);
            if (dwdModel.getMaxLzCount() < dwdModel.getLzCount()) {
                dwdModel.setMaxLzCount(dwdModel.getLzCount());
            }
        }
        logger.info("执行系统筛选热号.......");
        this.dwd(datas, dwdModel, 2);

        logger.info("------------------------------ "+ properties.getName() +"开奖信息为:" + kjqh + " [" + kjxn + "]\n");
        logger.info("数据统计： 连中次数：" + dwdModel.getLzCount() + ", 不中次数：" + dwdModel.getBuCount() + "， 最大连中：" +dwdModel.getMaxLzCount() + ", 最大不中：" + dwdModel.getMaxBuCount());
        logger.info("本期胆码：" + dwdModel.getTzNum() + "，下期胆码："+dwdModel.getNextTzNum()+", 中奖次数：[ " + dwdModel.getZjCount() + " ]，不中次数：[ " + dwdModel.getNotZjCount() + " ]" );
        dwdModel.setTzNum(dwdModel.getNextTzNum());

        return true;
    }

    public boolean brainPowerDoNumHandle(JSONObject json, Properties properties, DwdModel dwdModel, Logger logger) throws Exception {
        JSONArray datas = json.getJSONArray("data");
        String kjqh = datas.getJSONArray(datas.size() - 1).getString(0);
        Integer qh = Integer.valueOf(kjqh.split("-")[1]);
        String kjxn = datas.getJSONArray(datas.size() - 1).getString(1);

        int nextqh = properties.getNextqh();
        if (nextqh - qh == 1) { //是上一期
            Thread.sleep(5000);
            json = query(properties, logger); //重新查询
            return brainPowerDoNumHandle(json, properties, dwdModel, logger);
        }

        properties.setQs(properties.getQs() + 1);
        properties.setNextqh(qh + 1);
        String q2 = kjxn.substring(0,2);
        String h2 = kjxn.substring(3,kjxn.length());

        boolean isZj = true; // 是否中奖
        if (dwdModel.getQhLocation() == 5) { //后二
            if (!dwdModel.getTzNum().contains(h2)) {
                isZj = false;
            }
        } else {
            if (!dwdModel.getTzNum().contains(q2)) {
                isZj = false;
            }
        }

        int dqbs = properties.getHbs();
        if (!isZj) {
            String newTzNum = LotteryHandleUtil.brainPowerDoNum(datas, dwdModel);
            dwdModel.setTzNum(newTzNum);
            dwdModel.setBuCount(dwdModel.getBuCount() + 1);
            dwdModel.setBtBzCount(dwdModel.getBtBzCount() + properties.getHbs());
            dwdModel.setNotZjCount(dwdModel.getNotZjCount() + 1);
            properties.setHbs(bs[(dwdModel.getBuCount() > 4 ? 4 : dwdModel.getBuCount())]);
            dwdModel.setLzCount(0);
            if (dwdModel.getMaxBuCount() < dwdModel.getBuCount()) {
                dwdModel.setMaxBuCount(dwdModel.getBuCount());
            }
        } else {
            dwdModel.setBtZjCount(dwdModel.getBtZjCount() + properties.getHbs());
            dwdModel.setZjCount(dwdModel.getZjCount() + 1);
            dwdModel.setLzCount(dwdModel.getLzCount() + 1);
            properties.setHbs(1);
            dwdModel.setBuCount(0);
            if (dwdModel.getMaxLzCount() < dwdModel.getLzCount()) {
                dwdModel.setMaxLzCount(dwdModel.getLzCount());
            }
        }

        logger.info("\n\n\n");
        logger.info("------------------------------ "+ properties.getName() +"开奖信息为:" + kjqh + " [" + kjxn + "]");
        logger.info("投注号码：" + dwdModel.getTzNum());
        logger.info("数据统计： 总投注：[ "+properties.getQs()+ "期 ], 连中次数：" + dwdModel.getLzCount() + ", 不中次数：" + dwdModel.getBuCount() + "， 最大连中：" +dwdModel.getMaxLzCount() + ", 最大不中：" + dwdModel.getMaxBuCount());
        logger.info("倍投数据统计: 当前倍数： [ "+dqbs+ "倍 ], 中奖次数：[ " + dwdModel.getBtZjCount() + " ]，不中次数：[ " + dwdModel.getBtBzCount() + " ]");
        logger.info("平刷数据统计: 中奖次数：[ " + dwdModel.getZjCount() + " ]，不中次数：[ " + dwdModel.getNotZjCount() + " ]");

        return true;
    }

    private void heCommon(Properties properties, String kjxn, String kjqh, int count, Logger logger) throws Exception {
        String q2 = kjxn.substring(0,2);
        int q_first = Integer.valueOf(q2.substring(0,1));
        int q_last = Integer.valueOf(q2.substring(1,2));
        int qHe = q_first + q_last;
        String h2 = kjxn.substring(3,kjxn.length());
        int h_first = Integer.valueOf(h2.substring(0,1));
        int h_last = Integer.valueOf(h2.substring(1,2));
        int hHe = h_first + h_last;

        if (count == 1) {
            logger.info("------------------------------ "+ properties.getName() +"开奖信息为:" + kjqh + " [" + kjxn + "]\n");
        }

        String name = properties.getName();
        if (Integer.valueOf(h2) > 49) {
            properties.setHxy(0);
            properties.setHdy(properties.getHdy() + 1);
            logger.info("后二和大于49 [ {} ] ", properties.getHdy());
            if (properties.getHdy() == properties.gethMax()) {
                MailSend.sendMail(name + "后二 和大于49" ,kjqh, "", toMails);
            }
        }

        if (Integer.valueOf(h2) < 50) {
            properties.setHdy(0);
            properties.setHxy(properties.getHxy() + 1);
            logger.info("后二和小于50 [ {} ] ", properties.getHxy());
            if (properties.getHxy() == properties.gethMax()) {
                MailSend.sendMail(name + "后二 和小于50" ,kjqh, "", toMails);
            }
        }

        if (Integer.valueOf(q2) > 49) {
            properties.setQxy(0);
            properties.setQdy(properties.getQdy() + 1);
            logger.info("前二和大于49 [ {} ] ", properties.getQdy());
            if (properties.getQdy() == properties.gethMax()) {
                MailSend.sendMail(name + "前二 和大于49" ,kjqh, "", toMails);
            }
        }

        if (Integer.valueOf(q2) < 50) {
            properties.setQdy(0);
            properties.setQxy(properties.getQxy() + 1);
            logger.info("前二和小于50 [ {} ] ", properties.getQxy());
            if (properties.getQxy() == properties.gethMax()) {
                MailSend.sendMail(name + "前二 和小于50" ,kjqh, "", toMails);
            }
        }
    }

    private void zjCount(Properties properties, String kjxn, String kjqh, int count, Logger logger) throws Exception {
        String q2 = kjxn.substring(0,2);
        String h2 = kjxn.substring(3,kjxn.length());

        if (count == 1) {
            logger.info("------------------------------ "+ properties.getName() +"开奖信息为:" + kjqh + " [" + kjxn + "]\n");
        }

        String qtj = properties.getQtj();
        if ("dy".equals(qtj)) {
            if (Integer.valueOf(q2) > 49) {
                properties.setQbs(bs[properties.getQcount() % 5]);
                properties.setqAccountAmount(properties.getqAccountAmount().add(properties.getAmount().multiply(BigDecimal.valueOf(properties.getQbs()))));
                properties.setQzjcs(properties.getQzjcs() + 1);
                properties.setQcount(0);
            } else {
                properties.setQbs(bs[properties.getQcount() % 5]);
                properties.setqAccountAmount(properties.getqAccountAmount().subtract(properties.getAmount().multiply(BigDecimal.valueOf(properties.getQbs()))));
                properties.setQbzcs(properties.getQbzcs() + 1);
                properties.setQcount(properties.getQcount() + 1);
                properties.setQtj("xy");
            }
        } else if ("xy".equals(qtj)) {
            if (Integer.valueOf(q2) <= 49) {
                properties.setQbs(bs[properties.getQcount() % 5]);
                properties.setqAccountAmount(properties.getqAccountAmount().add(properties.getAmount().multiply(BigDecimal.valueOf(properties.getQbs()))));
                properties.setQzjcs(properties.getQzjcs() + 1);
                properties.setQcount(0);
            } else {
                properties.setQbs(bs[properties.getQcount() % 5]);
                properties.setqAccountAmount(properties.getqAccountAmount().subtract(properties.getAmount().multiply(BigDecimal.valueOf(properties.getQbs()))));
                properties.setQbzcs(properties.getQbzcs() + 1);
                properties.setQcount(properties.getQcount() + 1);
                properties.setQtj("dy");
            }
        }

        String htj = properties.getHtj();
        if ("dy".equals(htj)) {
            if (Integer.valueOf(h2) > 49) {
                properties.setHbs(bs[properties.getHcount() % 5]);
                properties.sethAccountAmount(properties.gethAccountAmount().add(properties.getAmount().multiply(BigDecimal.valueOf(properties.getHbs()))));
                properties.setHcount(0);
                properties.setHzjcs(properties.getHzjcs() + 1);
            } else {
                properties.setHbs(bs[properties.getHcount() % 5]);
                properties.sethAccountAmount(properties.gethAccountAmount().subtract(properties.getAmount().multiply(BigDecimal.valueOf(properties.getHbs()))));
                properties.setHcount(properties.getHcount() + 1);
                properties.setHbzcs(properties.getHbzcs() + 1);
                properties.setHtj("xy");
            }
        } else if ("xy".equals(htj)) {
            if (Integer.valueOf(h2) <= 49) {
                properties.setHbs(bs[properties.getHcount() % 5]);
                properties.sethAccountAmount(properties.gethAccountAmount().add(properties.getAmount().multiply(BigDecimal.valueOf(properties.getHbs()))));
                properties.setHcount(0);
                properties.setHzjcs(properties.getHzjcs() + 1);
            } else {
                properties.setHbs(bs[properties.getHcount() % 5]);
                properties.sethAccountAmount(properties.gethAccountAmount().subtract(properties.getAmount().multiply(BigDecimal.valueOf(properties.getHbs()))));
                properties.setHcount(properties.getHcount() + 1);
                properties.setHbzcs(properties.getHbzcs() + 1);
                properties.setHtj("dy");
            }
        }

        if (properties.gethMaxbzCount() == 0 || properties.getHcount() > properties.gethMaxbzCount()) {
            properties.sethMaxbzCount(properties.getHcount());
        }

        if (properties.getqMaxbzCount() == 0 || properties.getQcount() > properties.getqMaxbzCount()) {
            properties.setqMaxbzCount(properties.getQcount());
        }

        int z = properties.getHzjcs();
        int b = properties.getHbzcs();
        logger.info("选号：{}，后二中 [ {} ] 次，不中 [ {} ], 连续不中次数：{}，当前倍数：{}， 最大不中次数：{}， 收益：{}",
                htj ,z, b,properties.getHcount(),properties.getHbs(), properties.gethMaxbzCount(), properties.gethAccountAmount());

        z = properties.getQzjcs();
        b = properties.getQbzcs();
        logger.info("选号：{}，前二中 [ {} ] 次，不中 [ {} ], 连续不中次数：{}，当前倍数：{}， 最大不中次数：{}，收益：{}",
                qtj ,z, b,properties.getQcount(),properties.getQbs(), properties.getqMaxbzCount(), properties.getqAccountAmount());
    }

    /**
     *
     * @param datas
     * @param dwdModel
     * @param next 1 本期热号 2下期热号
     */
    private void dwd(JSONArray datas,DwdModel dwdModel, int next) {
        int size =datas.size() - 1;
        Map<Integer, Integer> map = new HashMap<>();
        Integer dqNum = null;
        for (int i = size; i >= 15; i-- ) { //从15期中选热码
            String kjNum = datas.getJSONArray(i).getString(1);
            int dwNum =Integer.valueOf(kjNum.substring(dwdModel.getLocation() - 1, dwdModel.getLocation()));
            if (i == 29) {
                dqNum = dwNum;
            }
            switch (dwNum) {
                case 0:
                    map.put(0, map.get(0) == null ? 1 : map.get(0) + 1);
                    break;
                case 1:
                    map.put(1, map.get(1) == null ? 1 : map.get(1) + 1);
                    break;
                case 2:
                    map.put(2, map.get(2) == null ? 1 : map.get(2) + 1);
                    break;
                case 3:
                    map.put(3, map.get(3) == null ? 1 : map.get(3) + 1);
                    break;
                case 4:
                    map.put(4, map.get(4) == null ? 1 : map.get(4) + 1);
                    break;
                case 5:
                    map.put(5, map.get(5) == null ? 1 : map.get(5) + 1);
                    break;
                case 6:
                    map.put(6, map.get(6) == null ? 1 : map.get(6) + 1);
                    break;
                case 7:
                    map.put(7, map.get(7) == null ? 1 : map.get(7) + 1);
                    break;
                case 8:
                    map.put(8, map.get(8) == null ? 1 : map.get(8) + 1);
                    break;
                case 9:
                    map.put(9, map.get(9) == null ? 1 : map.get(9) + 1);
                    break;
            }
        }
        // 排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            //根据value降序排序
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });

        List<Integer> ji = new ArrayList<>();
        List<Integer> ou = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            Integer key = entry.getKey();
            if (key % 2 == 0) {
                ou.add(key);
            } else  {
                ji.add(key);
            }
        }

        List<Integer> list1 = new ArrayList<>();
        int length = dwdModel.getLength();
        int num = length / 2;
        if (dwdModel.getLength() % 2 == 0) {
            if (ji.size() >= num && ou.size() >= num) {
                for (int i = 0; i < num; i++) {
                    list1.add(ji.get(i));
                    list1.add(ou.get(i));
                }
            } else {
                if (list.size() > length) {
                    for (int i = 0; i < length; i++) {
                        Map.Entry<Integer, Integer> entry = list.get(i);
                        list1.add(entry.getKey());
                    }
                } else {
                    for (Map.Entry<Integer, Integer> entry : list) {
                        list1.add(entry.getKey());
                    }
                }
            }
        } else {
            if (list.size() > length) {
                for (int i = 0; i < length; i++) {
                    Map.Entry<Integer, Integer> entry = list.get(i);
                    list1.add(entry.getKey());
                }
            } else {
                for (Map.Entry<Integer, Integer> entry : list) {
                    list1.add(entry.getKey());
                }
            }
        }

        String tzNum = "";
        Collections.sort(list1);
        for (Integer integer : list1) {
            tzNum += integer;
        }
        if (next == 1) {
            dwdModel.setTzNum(tzNum);
        } else if (next == 2) {
            dwdModel.setNextTzNum(tzNum);
        }
    }

}
