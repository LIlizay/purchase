package com.hhwy.purchase.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;

/**
 * @author hhwy
 * @date 2018-05-15 9:45:53
 * @version 1.0
 */
@Entity(name="PhmEnterSettle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_enter_settle")
public class PhmEnterSettle extends Domain implements Serializable{
	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
	@PropertyDesc("年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
	
	@PropertyDesc("售电公司购电收益")
    @Column(name="com_profit", nullable=true, length=16) 
    private java.math.BigDecimal comProfit;
	
	@PropertyDesc("售电公司偏差费用")
    @Column(name="com_check", nullable=true, length=16) 
    private java.math.BigDecimal comCheck;

	@PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;


	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public java.math.BigDecimal getComProfit() {
		return comProfit;
	}

	public void setComProfit(java.math.BigDecimal comProfit) {
		this.comProfit = comProfit;
	}

	public java.math.BigDecimal getComCheck() {
		return comCheck;
	}

	public void setComCheck(java.math.BigDecimal comCheck) {
		this.comCheck = comCheck;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	
}
