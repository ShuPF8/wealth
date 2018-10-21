package com.spf.wealth.base;

import com.spf.wealth.model.LotteryDetail;
import com.spf.wealth.model.LotteryHeDetail;
import com.spf.wealth.service.ILotteryDetailService;
import com.spf.wealth.service.ILotteryHeDatailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-10-10 18:48
 */
public class MyTest extends TmallApplicationTests {

    @Autowired
    private ILotteryDetailService detailService;

    @Autowired
    private ILotteryHeDatailService heDatailService;

    @Test
    public void test() throws UnsupportedEncodingException {
        LotteryHeDetail heDetail = heDatailService.findByNumAndDate("2018-10-21", "腾讯纷纷");
        System.out.println(heDetail);
    }



}
