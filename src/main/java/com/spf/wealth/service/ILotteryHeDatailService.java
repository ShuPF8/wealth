package com.spf.wealth.service;


import com.baomidou.mybatisplus.service.IService;
import com.spf.wealth.model.LotteryHeDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ShuPF
 * @since 2018-10-09
 */
public interface ILotteryHeDatailService extends IService<LotteryHeDetail> {

    LotteryHeDetail findByNumAndDate(String date, String name);

}
