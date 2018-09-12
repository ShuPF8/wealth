package com.spf.wealth.wealth.hu;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-09-11 15:08
 */
public class Properties {
    private String name; //彩种

    public String myNum; //我的号码

    private Integer nextqh = 744; //开奖期号

    private int hcount = 0; //后二不中次数

    private int hlz = 0; //后二二连中次数

    private int hMax; //后二最大不中次数

    private int hMaxlzCount = 0; //后二最大连中统计

    private int hMaxbzCount = 0; //后二最大不中统计

    private int qcount = 0; //前二不中次数

    private int qlz = 0; //前二连中次数

    private int qMax; //前二最大不中次数

    private int qMaxlzCount = 0; //前二最大连中统计

    private int qMaxbzCount = 0; //前二最大不中统计

    private String shtj = ""; //杀号条件

    public Properties(String name, String myNum, Integer nextqh, int hMax, int qMax, String shtj) {
        this.name = name;
        this.myNum = myNum;
        this.nextqh = nextqh;
        this.hMax = hMax;
        this.qMax = qMax;
        this.shtj = shtj;
    }

    public int gethMaxlzCount() {
        return hMaxlzCount;
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
