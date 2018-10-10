package com.spf.wealth.base;

import com.spf.wealth.model.LotteryDetail;
import com.spf.wealth.service.ILotteryDetailService;
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

    @Test
    public void test() throws UnsupportedEncodingException {
        System.out.println("中文");
        System.out.println("中文".getBytes("utf-8"));
    }

}
