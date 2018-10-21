package com.spf.wealth.model;

/**
 * @author ShuPF
 * @date 2018/10/21 0021 10:21
 */
public class LotteryHeDetail {
    private Long id;

    private String name;

    private int hDyFive = 0;

    private int hDySix = 0;

    private int hDyMax = 0;

    private int hXyFive = 0;

    private int hXySix = 0;

    private int hXyMax = 0;

    private int qDyFive = 0;

    private int qDySix = 0;

    private int qDyMax = 0;

    private int qXyFive = 0;

    private int qXySix = 0;

    private int qXyMax = 0;

    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int gethDyFive() {
        return hDyFive;
    }

    public void sethDyFive(int hDyFive) {
        this.hDyFive = hDyFive;
    }

    public int gethDySix() {
        return hDySix;
    }

    public void sethDySix(int hDySix) {
        this.hDySix = hDySix;
    }

    public int gethDyMax() {
        return hDyMax;
    }

    public void sethDyMax(int hDyMax) {
        this.hDyMax = hDyMax;
    }

    public int gethXyFive() {
        return hXyFive;
    }

    public void sethXyFive(int hXyFive) {
        this.hXyFive = hXyFive;
    }

    public int gethXySix() {
        return hXySix;
    }

    public void sethXySix(int hXySix) {
        this.hXySix = hXySix;
    }

    public int gethXyMax() {
        return hXyMax;
    }

    public void sethXyMax(int hXyMax) {
        this.hXyMax = hXyMax;
    }

    public int getqDyFive() {
        return qDyFive;
    }

    public void setqDyFive(int qDyFive) {
        this.qDyFive = qDyFive;
    }

    public int getqDySix() {
        return qDySix;
    }

    public void setqDySix(int qDySix) {
        this.qDySix = qDySix;
    }

    public int getqDyMax() {
        return qDyMax;
    }

    public void setqDyMax(int qDyMax) {
        this.qDyMax = qDyMax;
    }

    public int getqXyFive() {
        return qXyFive;
    }

    public void setqXyFive(int qXyFive) {
        this.qXyFive = qXyFive;
    }

    public int getqXySix() {
        return qXySix;
    }

    public void setqXySix(int qXySix) {
        this.qXySix = qXySix;
    }

    public int getqXyMax() {
        return qXyMax;
    }

    public void setqXyMax(int qXyMax) {
        this.qXyMax = qXyMax;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LotteryHeDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hDyFive=" + hDyFive +
                ", hDySix=" + hDySix +
                ", hDyMax=" + hDyMax +
                ", hXyFive=" + hXyFive +
                ", hXySix=" + hXySix +
                ", hXyMax=" + hXyMax +
                ", qDyFive=" + qDyFive +
                ", qDySix=" + qDySix +
                ", qDyMax=" + qDyMax +
                ", qXyFive=" + qXyFive +
                ", qXySix=" + qXySix +
                ", qXyMax=" + qXyMax +
                ", date='" + date + '\'' +
                '}';
    }
}
