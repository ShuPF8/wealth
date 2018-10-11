package com.spf.wealth.base;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.spf.wealth.model.LotteryDetail;
import com.spf.wealth.model.LotteryType;
import com.spf.wealth.service.ILotteryDetailService;
import com.spf.wealth.service.ILotteryTypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-10-09 17:38
 */
public class EntFileTest extends TmallApplicationTests {

    @Autowired
    private ILotteryTypeService iLotteryTypeService;

    @Autowired
    private ILotteryDetailService detailService;

    @Test
    public void test() {
        List<LotteryType> lotteryTypes = iLotteryTypeService.selectList(new EntityWrapper<>());
        System.out.println(lotteryTypes);
    }

    @Test
    public void insert() throws Exception {
        LotteryDetail detail = new LotteryDetail();
        detail.setTypeName("腾讯分分");
        detail.setFiveHBz(1);
        detail.setFiveQBz(1);
        detail.sethMaxBz(1);
        detail.setLotteryNum("520");
        detail.setqMaxBz(1);
        detail.setShtj("520");
        detail.setSixHBz(1);
        detail.setSixQBz(1);
        detail.setCreateTime("2018-10-10");
        detailService.insert(detail);
    }

    @Test
    public void update() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        LotteryDetail detail = detailService.findByNumAndDate("520", "2018-10-10", "腾讯分分");
        detail.setSevenQBz(10);
        detailService.updateById(detail);
    }

}
