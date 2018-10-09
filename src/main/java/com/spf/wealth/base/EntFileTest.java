package com.spf.wealth.base;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.spf.wealth.model.LotteryType;
import com.spf.wealth.service.ILotteryTypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-10-09 17:38
 */
public class EntFileTest extends TmallApplicationTests {

    @Autowired
    private ILotteryTypeService iLotteryTypeService;

    @Test
    public void test() {
        List<LotteryType> lotteryTypes = iLotteryTypeService.selectList(new EntityWrapper<>());
        System.out.println(lotteryTypes);
    }

}
