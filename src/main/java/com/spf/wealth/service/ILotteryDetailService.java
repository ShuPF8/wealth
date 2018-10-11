package com.spf.wealth.service;

import com.baomidou.mybatisplus.service.IService;
import com.spf.wealth.model.LotteryDetail;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ShuPF
 * @since 2018-10-09
 */
public interface ILotteryDetailService extends IService<LotteryDetail> {

    LotteryDetail findByNumAndDate(String num, String date, String name);

}
