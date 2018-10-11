package com.spf.wealth.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spf.wealth.mapper.LotteryDetailMapper;
import com.spf.wealth.model.LotteryDetail;
import com.spf.wealth.service.ILotteryDetailService;
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
public class ILotteryDetailServiceImpl extends ServiceImpl<LotteryDetailMapper, LotteryDetail> implements ILotteryDetailService {

    @Autowired
    private LotteryDetailMapper lotteryDetailMapper;

    @Override
    public LotteryDetail findByNumAndDate(String num, String date, String name) {
        return lotteryDetailMapper.findByNumAndDate(num, date, name);
    }
}
