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

    private int buCount = 0; // 不中

    private int zjCount = 0; //中奖次数

    private int notZjCount = 0; //

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
