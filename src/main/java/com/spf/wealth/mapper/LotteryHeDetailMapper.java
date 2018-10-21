package com.spf.wealth.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spf.wealth.model.LotteryDetail;
import com.spf.wealth.model.LotteryHeDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ShuPF
 * @since 2018-10-09
 */
public interface LotteryHeDetailMapper extends BaseMapper<LotteryHeDetail> {

    @Select("select * from lottery_he_detail where date =#{date} and name=#{name} ")
    LotteryHeDetail findByNumAndDate(@Param("date") String date, @Param("name") String name);

}
