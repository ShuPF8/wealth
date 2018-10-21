package com.spf.wealth.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spf.wealth.mapper.LotteryHeDetailMapper;
import com.spf.wealth.model.LotteryHeDetail;
import com.spf.wealth.service.ILotteryHeDatailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShuPF
 * @since 2018-10-09
 */
@Service
public class ILotteryHeDetailServiceImpl extends ServiceImpl<LotteryHeDetailMapper, LotteryHeDetail> implements ILotteryHeDatailService {

    @Autowired
    private LotteryHeDetailMapper lotteryHeDetailMapper;

    @Override
    public LotteryHeDetail findByNumAndDate(String date, String name) {
        return lotteryHeDetailMapper.findByNumAndDate(date, name);
    }
}
