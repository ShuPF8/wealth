package com.spf.wealth.model;

/**
 * @author ShuPF
 * @类说明： 定位胆
 * @date 2018-11-08 9:56
 */
public class DwdModel {
    private String tzNum; //投注号码

    private String nextTzNum;

    private int length = 6; // 定位胆个数

    private int location = 1; //定位胆位置 1=万位，2=千位.....

    private int lzCount = 0; //连中

    private int maxLzCount = 0; //最大连中

    private  int maxBuCount = 0; //最大不中

    private int buCount = 0; // 连续不中

    private int zjCount = 0; //总中奖次数

    private int notZjCount = 0; // 总不中次数

    private String kjxx;

    private int isZd;

    private boolean isXh; //是否时时筛选

    private int rmCount = 18; //热码期数 20期

    private int heNum = 2; //和值个数 默认2

    private int qhLocation = 5; //前二 1 后二5

    private int btZjCount = 0; // 倍投中奖次数

    private int btBzCount = 0; //倍投不中次数

    public int getBtZjCount() {
        return btZjCount;
    }

    public void setBtZjCount(int btZjCount) {
        this.btZjCount = btZjCount;
    }

    public int getBtBzCount() {
        return btBzCount;
    }

    public void setBtBzCount(int btBzCount) {
        this.btBzCount = btBzCount;
    }

    public String getKjxx() {
        return kjxx;
    }

    public void setKjxx(String kjxx) {
        this.kjxx = kjxx;
    }

    public int getIsZd() {
        return isZd;
    }

    public void setIsZd(int isZd) {
        this.isZd = isZd;
    }

    public boolean isXh() {
        return isXh;
    }

    public void setXh(boolean xh) {
        isXh = xh;
    }

    public int getRmCount() {
        return rmCount;
    }

    public void setRmCount(int rmCount) {
        this.rmCount = rmCount;
    }

    public int getHeNum() {
        return heNum;
    }

    public void setHeNum(int heNum) {
        this.heNum = heNum;
    }

    public int getQhLocation() {
        return qhLocation;
    }

    public void setQhLocation(int qhLocation) {
        this.qhLocation = qhLocation;
    }

    public int getNotZjCount() {
        return notZjCount;
    }

    public void setNotZjCount(int notZjCount) {
        this.notZjCount = notZjCount;
    }

    public DwdModel() {
        super();
    }

    public DwdModel(String tzNum) {
        this.tzNum = tzNum;
    }

    public int getMaxLzCount() {
        return maxLzCount;
    }

    public void setMaxLzCount(int maxLzCount) {
        this.maxLzCount = maxLzCount;
    }

    public int getMaxBuCount() {
        return maxBuCount;
    }

    public void setMaxBuCount(int maxBuCount) {
        this.maxBuCount = maxBuCount;
    }

    public String getTzNum() {
        return tzNum;
    }

    public void setTzNum(String tzNum) {
        this.tzNum = tzNum;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getLzCount() {
        return lzCount;
    }

    public void setLzCount(int lzCount) {
        this.lzCount = lzCount;
    }

    public int getBuCount() {
        return buCount;
    }

    public void setBuCount(int buCount) {
        this.buCount = buCount;
    }

    public int getZjCount() {
        return zjCount;
    }

    public void setZjCount(int zjCount) {
        this.zjCount = zjCount;
    }

    public String getNextTzNum() {
        return nextTzNum;
    }

    public void setNextTzNum(String nextTzNum) {
        this.nextTzNum = nextTzNum;
    }
}
