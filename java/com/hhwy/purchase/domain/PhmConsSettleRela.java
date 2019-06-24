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
@Entity(name="PhmConsSettleRela")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_cons_settle_rela")
public class PhmConsSettleRela extends Domain implements Serializable{
	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
	@PropertyDesc("用户id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
	
	@PropertyDesc("结算id")
    @Column(name="settle_id", nullable=true, length=32) 
    private String settleId;
	
	@PropertyDesc("用户偏差费用")
    @Column(name="cons_check", nullable=true, length=16) 
    private java.math.BigDecimal consCheck;
	
	@PropertyDesc("结算电价")
    @Column(name="settle_prc", nullable=true, length=16) 
    private java.math.BigDecimal settlePrc;

	@PropertyDesc("服务费")
    @Column(name="profit", nullable=true, length=16) 
    private java.math.BigDecimal profit;
	
	@PropertyDesc("代理人")
    @Column(name="agent", nullable=true, length=32) 
    private String agent;
	
	@PropertyDesc("代理费用")
    @Column(name="agent_prc", nullable=true, length=16) 
    private java.math.BigDecimal agentPrc;
	
	@PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=16) 
    private String orgNo;
	
	@PropertyDesc("结算电费")
    @Column(name="prc", nullable=true, length=16) 
    private java.math.BigDecimal prc;

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getSettleId() {
		return settleId;
	}

	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}

	public java.math.BigDecimal getConsCheck() {
		return consCheck;
	}

	public void setConsCheck(java.math.BigDecimal consCheck) {
		this.consCheck = consCheck;
	}

	public java.math.BigDecimal getSettlePrc() {
		return settlePrc;
	}

	public void setSettlePrc(java.math.BigDecimal settlePrc) {
		this.settlePrc = settlePrc;
	}

	public java.math.BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(java.math.BigDecimal profit) {
		this.profit = profit;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public java.math.BigDecimal getPrc() {
		return prc;
	}

	public void setPrc(java.math.BigDecimal prc) {
		this.prc = prc;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public java.math.BigDecimal getAgentPrc() {
		return agentPrc;
	}

	public void setAgentPrc(java.math.BigDecimal agentPrc) {
		this.agentPrc = agentPrc;
	}
	
}
