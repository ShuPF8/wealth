package com.spf.wealth.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spf.wealth.model.LotteryDetail;
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
public interface LotteryDetailMapper extends BaseMapper<LotteryDetail> {

    @Select("select * from lottery_detail where lottery_num=#{num} and create_time =#{date} and type_name=#{name} ")
    LotteryDetail findByNumAndDate(@Param("num") String num, @Param("date") String date, @Param("name") String name);

}
