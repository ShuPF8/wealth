package com.spf.wealth.utils;

import org.apache.http.impl.client.CloseableHttpClient;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-11 15:08
 */
public class Properties {
    private String name; //彩种

    public String myNum; //我的号码

    private Integer nextqh; //开奖期号

    private int hcount = 0; //后二不中次数

    private int hlz = 0; //后二二连中次数

    private int hMax; //后二最大不中次数

    private int hMaxlzCount = 0; //后二最大连中统计

    private int prevHMaxLzCount = 7; //上一次后二最大连中统计

    private int prevHMaxBuCount = 4; //上一次后二最大不中次数

    private int hMaxbzCount = 0; //后二最大不中统计

    private int qcount = 0; //前二不中次数

    private int qlz = 0; //前二连中次数

    private int qMax; //前二最大不中次数

    private int qMaxlzCount = 0; //前二最大连中统计

    private int qMaxbzCount = 0; //前二最大不中统计

    private int prevQMaxLzCount = 7; //上一次前二最大连中统计

    private int prevQMaxBuCount = 4; //上一次前二最大不中次数

    private long prevWireTime = System.currentTimeMillis(); //上期记录文件时间

    private String shtj = ""; //杀号条件

    private int hdy = 0;

    private int hxy = 0;

    private int qdy = 0;

    private int qxy = 0;

    private int qzjcs = 0; // 中奖次数

    private int qbzcs = 0; // 不中次数

    private String qtj = "dy";

    private int qbs = 1; // 倍数

    private BigDecimal qAccountAmount = BigDecimal.ZERO;

    private BigDecimal amount; //投注金额

    private int hzjcs = 0; // 中奖次数

    private int hbzcs = 0; // 不中次数

    private int hbs = 1; //后倍数

    private String htj = "dy";

    private BigDecimal hAccountAmount = BigDecimal.ZERO;

    private BigDecimal commonAccountAmount = BigDecimal.ZERO; // 前后一起账户金额

    private CloseableHttpClient client;
    private Map<String, Object> params;


    public Properties(String name, String myNum, Integer nextqh, int hMax, int qMax, String shtj, CloseableHttpClient client, Map<String, Object> params) {
        this.name = name;
        this.myNum = myNum;
        this.nextqh = nextqh;
        this.hMax = hMax;
        this.qMax = qMax;
        this.shtj = shtj;
        this.client = client;
        this.params = params;
    }

    public Properties(String name, String shtj, BigDecimal amount, int nextqh, CloseableHttpClient client, Map<String, Object> params) {
        this.name = name;
        this.shtj = shtj;
        this.amount = amount;
        this.nextqh = nextqh;
        this.client = client;
        this.params = params;
    }

    public Properties(String name, String shtj, BigDecimal amount,String qtj,String htj, int nextqh, CloseableHttpClient client, Map<String, Object> params) {
        this.name = name;
        this.shtj = shtj;
        this.amount = amount;
        this.qtj = qtj;
        this.htj = htj;
        this.nextqh = nextqh;
        this.client = client;
        this.params = params;
    }

    public CloseableHttpClient getClient() {
        return client;
    }

    public void setClient(CloseableHttpClient client) {
        this.client = client;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public long getPrevWireTime() {
        return prevWireTime;
    }

    public void setPrevWireTime(long prevWireTime) {
        this.prevWireTime = prevWireTime;
    }

    public int getPrevHMaxLzCount() {
        return prevHMaxLzCount;
    }

    public void setPrevHMaxLzCount(int prevHMaxLzCount) {
        this.prevHMaxLzCount = prevHMaxLzCount;
    }

    public int getPrevQMaxLzCount() {
        return prevQMaxLzCount;
    }

    public void setPrevQMaxLzCount(int prevQMaxLzCount) {
        this.prevQMaxLzCount = prevQMaxLzCount;
    }

    public int gethMaxlzCount() {
        return hMaxlzCount;
    }

    public int getPrevHMaxBuCount() {
        return prevHMaxBuCount;
    }

    public void setPrevHMaxBuCount(int prevHMaxBuCount) {
        this.prevHMaxBuCount = prevHMaxBuCount;
    }

    public int getPrevQMaxBuCount() {
        return prevQMaxBuCount;
    }

    public void setPrevQMaxBuCount(int prevQMaxBuCount) {
        this.prevQMaxBuCount = prevQMaxBuCount;
    }

    public void sethMaxlzCount(int hMaxlzCount) {
        this.hMaxlzCount = hMaxlzCount;
    }

    public int gethMaxbzCount() {
        return hMaxbzCount;
    }

    public void sethMaxbzCount(int hMaxbzCount) {
        this.hMaxbzCount = hMaxbzCount;
    }

    public int getqMaxlzCount() {
        return qMaxlzCount;
    }

    public void setqMaxlzCount(int qMaxlzCount) {
        this.qMaxlzCount = qMaxlzCount;
    }

    public int getqMaxbzCount() {
        return qMaxbzCount;
    }

    public void setqMaxbzCount(int qMaxbzCount) {
        this.qMaxbzCount = qMaxbzCount;
    }

    public String getMyNum() {
        return myNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMyNum(String myNum) {
        this.myNum = myNum;
    }

    public Integer getNextqh() {
        return nextqh;
    }

    public void setNextqh(Integer nextqh) {
        this.nextqh = nextqh;
    }

    public int getHcount() {
        return hcount;
    }

    public void setHcount(int hcount) {
        this.hcount = hcount;
    }

    public int getHlz() {
        return hlz;
    }

    public void setHlz(int hlz) {
        this.hlz = hlz;
    }

    public int gethMax() {
        return hMax;
    }

    public void sethMax(int hMax) {
        this.hMax = hMax;
    }

    public int getQcount() {
        return qcount;
    }

    public void setQcount(int qcount) {
        this.qcount = qcount;
    }

    public int getQlz() {
        return qlz;
    }

    public void setQlz(int qlz) {
        this.qlz = qlz;
    }

    public int getqMax() {
        return qMax;
    }

    public void setqMax(int qMax) {
        this.qMax = qMax;
    }

    public String getShtj() {
        return shtj;
    }

    public void setShtj(String shtj) {
        this.shtj = shtj;
    }

    public int getHdy() {
        return hdy;
    }

    public void setHdy(int hdy) {
        this.hdy = hdy;
    }

    public int getHxy() {
        return hxy;
    }

    public void setHxy(int hxy) {
        this.hxy = hxy;
    }

    public int getQdy() {
        return qdy;
    }

    public void setQdy(int qdy) {
        this.qdy = qdy;
    }

    public int getQxy() {
        return qxy;
    }

    public void setQxy(int qxy) {
        this.qxy = qxy;
    }

    public int getQzjcs() {
        return qzjcs;
    }

    public void setQzjcs(int qzjcs) {
        this.qzjcs = qzjcs;
    }

    public int getQbzcs() {
        return qbzcs;
    }

    public void setQbzcs(int qbzcs) {
        this.qbzcs = qbzcs;
    }

    public int getHzjcs() {
        return hzjcs;
    }

    public void setHzjcs(int hzjcs) {
        this.hzjcs = hzjcs;
    }

    public int getHbzcs() {
        return hbzcs;
    }

    public void setHbzcs(int hbzcs) {
        this.hbzcs = hbzcs;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getQtj() {
        return qtj;
    }

    public void setQtj(String qtj) {
        this.qtj = qtj;
    }

    public String getHtj() {
        return htj;
    }

    public void setHtj(String htj) {
        this.htj = htj;
    }

    public int getQbs() {
        return qbs;
    }

    public void setQbs(int qbs) {
        this.qbs = qbs;
    }

    public int getHbs() {
        return hbs;
    }

    public void setHbs(int hbs) {
        this.hbs = hbs;
    }

    public BigDecimal getqAccountAmount() {
        return qAccountAmount;
    }

    public void setqAccountAmount(BigDecimal qAccountAmount) {
        this.qAccountAmount = qAccountAmount;
    }

    public BigDecimal gethAccountAmount() {
        return hAccountAmount;
    }

    public void sethAccountAmount(BigDecimal hAccountAmount) {
        this.hAccountAmount = hAccountAmount;
    }

    public BigDecimal getCommonAccountAmount() {
        return commonAccountAmount;
    }

    public void setCommonAccountAmount(BigDecimal commonAccountAmount) {
        this.commonAccountAmount = commonAccountAmount;
    }
}
