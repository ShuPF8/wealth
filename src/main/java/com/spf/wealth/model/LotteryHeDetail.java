package com.spf.wealth.model;

/**
 * @author ShuPF
 * @date 2018/10/21 0021 10:21
 */
public class LotteryHeDetail {
    private Long id;

    private String name;

    private int hDySeven = 0;

    private int hDyEight = 0;

    private int hDyNine = 0;

    private int hDyMax = 0;

    private int hXySeven = 0;

    private int hXyEight = 0;

    private int hXyNine = 0;

    private int hXyMax = 0;

    private int qDySeven = 0;

    private int qDyEight = 0;

    private int qDyNine = 0;

    private int qDyMax = 0;

    private int qXySeven = 0;

    private int qXyEight = 0;

    private int qXyNine = 0;

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

    public int gethDySeven() {
        return hDySeven;
    }

    public void sethDySeven(int hDySeven) {
        this.hDySeven = hDySeven;
    }

    public int gethDyEight() {
        return hDyEight;
    }

    public void sethDyEight(int hDyEight) {
        this.hDyEight = hDyEight;
    }

    public int gethDyNine() {
        return hDyNine;
    }

    public void sethDyNine(int hDyNine) {
        this.hDyNine = hDyNine;
    }

    public int gethDyMax() {
        return hDyMax;
    }

    public void sethDyMax(int hDyMax) {
        this.hDyMax = hDyMax;
    }

    public int gethXySeven() {
        return hXySeven;
    }

    public void sethXySeven(int hXySeven) {
        this.hXySeven = hXySeven;
    }

    public int gethXyEight() {
        return hXyEight;
    }

    public void sethXyEight(int hXyEight) {
        this.hXyEight = hXyEight;
    }

    public int gethXyNine() {
        return hXyNine;
    }

    public void sethXyNine(int hXyNine) {
        this.hXyNine = hXyNine;
    }

    public int gethXyMax() {
        return hXyMax;
    }

    public void sethXyMax(int hXyMax) {
        this.hXyMax = hXyMax;
    }

    public int getqDySeven() {
        return qDySeven;
    }

    public void setqDySeven(int qDySeven) {
        this.qDySeven = qDySeven;
    }

    public int getqDyEight() {
        return qDyEight;
    }

    public void setqDyEight(int qDyEight) {
        this.qDyEight = qDyEight;
    }

    public int getqDyNine() {
        return qDyNine;
    }

    public void setqDyNine(int qDyNine) {
        this.qDyNine = qDyNine;
    }

    public int getqDyMax() {
        return qDyMax;
    }

    public void setqDyMax(int qDyMax) {
        this.qDyMax = qDyMax;
    }

    public int getqXySeven() {
        return qXySeven;
    }

    public void setqXySeven(int qXySeven) {
        this.qXySeven = qXySeven;
    }

    public int getqXyEight() {
        return qXyEight;
    }

    public void setqXyEight(int qXyEight) {
        this.qXyEight = qXyEight;
    }

    public int getqXyNine() {
        return qXyNine;
    }

    public void setqXyNine(int qXyNine) {
        this.qXyNine = qXyNine;
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
                ", hDySeven=" + hDySeven +
                ", hDyEight=" + hDyEight +
                ", hDyNine=" + hDyNine +
                ", hDyMax=" + hDyMax +
                ", hXySeven=" + hXySeven +
                ", hXyEight=" + hXyEight +
                ", hXyNine=" + hXyNine +
                ", hXyMax=" + hXyMax +
                ", qDySeven=" + qDySeven +
                ", qDyEight=" + qDyEight +
                ", qDyNine=" + qDyNine +
                ", qDyMax=" + qDyMax +
                ", qXySeven=" + qXySeven +
                ", qXyEight=" + qXyEight +
                ", qXyNine=" + qXyNine +
                ", qXyMax=" + qXyMax +
                ", date='" + date + '\'' +
                '}';
    }
}
