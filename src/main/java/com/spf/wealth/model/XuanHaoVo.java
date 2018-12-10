package com.spf.wealth.model;

import lombok.Data;

/**
 * @author ShuPF
 * @类说明： 选号条件
 * @date 2018-11-21 16:40
 */
@Data
public class XuanHaoVo {
    private String dm; //胆码 多个逗号隔开

    private String he; //和值 多个逗号隔开

    private String kua; // 跨度 多个逗号隔开

    private String hw; //和尾 多个逗号隔开

    private String sd; //十位定杀

    private String gd; //个位定杀

    private int twom = 1; //2 2码

    private int qhLocation = 5; //前二 1 后二5

    public XuanHaoVo() {super();}

    public XuanHaoVo(String dm, String he, String kua) {
        this.dm = dm;
        this.he = he;
        this.kua = kua;
    }

    public XuanHaoVo(String dm, String he, String kua, String hw, String sd, String gd, int twom) {
        this.dm = dm;
        this.he = he;
        this.kua = kua;
        this.hw = hw;
        this.sd = sd;
        this.gd = gd;
        this.twom = twom;
    }
}
