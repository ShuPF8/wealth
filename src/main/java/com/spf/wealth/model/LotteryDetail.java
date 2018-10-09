package com.spf.wealth.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

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
     * 彩票号码唯一标识
     */
	private String title;
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
     * 后四次后不中
     */
	@TableField("four_h_bz")
	private Integer fourHBz;
    /**
     * 后五次后不中
     */
	@TableField("five_h_bz")
	private Integer fiveHBz;
    /**
     * 后6次不中次数
     */
	@TableField("six_h_bz")
	private Integer sixHBz;
    /**
     * 后最大不中
     */
	@TableField("h_max_bz")
	private Integer hMaxBz;
    /**
     * 前四次不中
     */
	@TableField("four_q_bz")
	private Integer fourQBz;
    /**
     * 前5次不中
     */
	@TableField("five_q_bz")
	private Integer fiveQBz;
    /**
     * 前6次不中次数
     */
	@TableField("six_q_bz")
	private Integer sixQBz;
    /**
     * 前最大不中
     */
	@TableField("q_max_bz")
	private Integer qMaxBz;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getFourHBz() {
		return fourHBz;
	}

	public void setFourHBz(Integer fourHBz) {
		this.fourHBz = fourHBz;
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

	public Integer getFourQBz() {
		return fourQBz;
	}

	public void setFourQBz(Integer fourQBz) {
		this.fourQBz = fourQBz;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
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
			", typeName=" + typeName +
			", title=" + title +
			", lotteryNum=" + lotteryNum +
			", shtj=" + shtj +
			", fourHBz=" + fourHBz +
			", fiveHBz=" + fiveHBz +
			", sixHBz=" + sixHBz +
			", hMaxBz=" + hMaxBz +
			", fourQBz=" + fourQBz +
			", fiveQBz=" + fiveQBz +
			", sixQBz=" + sixQBz +
			", qMaxBz=" + qMaxBz +
			", createTime=" + createTime +
			"}";
	}
}
