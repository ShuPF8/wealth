package com.spf.wealth.wealth.hu;

import org.apache.http.impl.client.CloseableHttpClient;

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

    private CloseableHttpClient client;
    private Map<String, Object> params;


    public Properties(String name, String myNum, Integer nextqh, int hMax, int qMax, String shtj, CloseableHttpClient client) {
        this.name = name;
        this.myNum = myNum;
        this.nextqh = nextqh;
        this.hMax = hMax;
        this.qMax = qMax;
        this.shtj = shtj;
        this.client = client;
    }

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
}
