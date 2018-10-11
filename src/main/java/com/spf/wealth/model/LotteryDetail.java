package com.spf.wealth.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ShuPF
 * @since 2018-10-09
 */
@TableName("lottery_detail")
public class LotteryDetail extends Model<LotteryDetail> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 彩种名称
     */
	@TableField("type_name")
	private String typeName;
    /**
     * 彩票号码
     */
	@TableField("lottery_num")
	private String lotteryNum;
    /**
     * 杀号条件
     */
	private String shtj;
    /**
     * 后五次后不中
     */
	@TableField("five_h_bz")
	private Integer fiveHBz = 0;
    /**
     * 后6次不中次数
     */
	@TableField("six_h_bz")
	private Integer sixHBz = 0;
	/**
	 * 后7次后不中
	 */
	@TableField("seven_h_bz")
	private Integer sevenHBz = 0;
	/**
	 * 后8次不中
	 */
	@TableField("eight_h_bz")
	private Integer eightHBz = 0;
    /**
     * 后最大不中
     */
	@TableField("h_max_bz")
	private Integer hMaxBz = 0;
    /**
     * 前5次不中
     */
	@TableField("five_q_bz")
	private Integer fiveQBz = 0;
    /**
     * 前6次不中次数
     */
	@TableField("six_q_bz")
	private Integer sixQBz = 0;
	/**
	 * 前7次不中
	 */
	@TableField("seven_q_bz")
	private Integer sevenQBz = 0;
	/**
	 * 前8次不中
	 */
	@TableField("eight_q_bz")
	private Integer eightQBz = 0;
    /**
     * 前最大不中
     */
	@TableField("q_max_bz")
	private Integer qMaxBz = 0;
    /**
     * 创建时间 yyyy-MM-dd
     */
	@TableField("create_time")
	private String createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLotteryNum() {
		return lotteryNum;
	}

	public void setLotteryNum(String lotteryNum) {
		this.lotteryNum = lotteryNum;
	}

	public String getShtj() {
		return shtj;
	}

	public void setShtj(String shtj) {
		this.shtj = shtj;
	}

	public Integer getEightHBz() {
		return eightHBz;
	}

	public void setEightHBz(Integer eightHBz) {
		this.eightHBz = eightHBz;
	}

	public Integer getEightQBz() {
		return eightQBz;
	}

	public void setEightQBz(Integer eightQBz) {
		this.eightQBz = eightQBz;
	}

	public Integer getFiveHBz() {
		return fiveHBz;
	}

	public void setFiveHBz(Integer fiveHBz) {
		this.fiveHBz = fiveHBz;
	}

	public Integer getSixHBz() {
		return sixHBz;
	}

	public void setSixHBz(Integer sixHBz) {
		this.sixHBz = sixHBz;
	}

	public Integer gethMaxBz() {
		return hMaxBz;
	}

	public void sethMaxBz(Integer hMaxBz) {
		this.hMaxBz = hMaxBz;
	}

	public Integer getSevenHBz() {
		return sevenHBz;
	}

	public void setSevenHBz(Integer sevenHBz) {
		this.sevenHBz = sevenHBz;
	}

	public Integer getSevenQBz() {
		return sevenQBz;
	}

	public void setSevenQBz(Integer sevenQBz) {
		this.sevenQBz = sevenQBz;
	}

	public Integer getFiveQBz() {
		return fiveQBz;
	}

	public void setFiveQBz(Integer fiveQBz) {
		this.fiveQBz = fiveQBz;
	}

	public Integer getSixQBz() {
		return sixQBz;
	}

	public void setSixQBz(Integer sixQBz) {
		this.sixQBz = sixQBz;
	}

	public Integer getqMaxBz() {
		return qMaxBz;
	}

	public void setqMaxBz(Integer qMaxBz) {
		this.qMaxBz = qMaxBz;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LotteryDetail{" +
				"id=" + id +
				", typeName='" + typeName + '\'' +
				", lotteryNum='" + lotteryNum + '\'' +
				", shtj='" + shtj + '\'' +
				", fiveHBz=" + fiveHBz +
				", sixHBz=" + sixHBz +
				", sevenHBz=" + sevenHBz +
				", eightHBz=" + eightHBz +
				", hMaxBz=" + hMaxBz +
				", fiveQBz=" + fiveQBz +
				", sixQBz=" + sixQBz +
				", sevenQBz=" + sevenQBz +
				", eightQBz=" + eightQBz +
				", qMaxBz=" + qMaxBz +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
