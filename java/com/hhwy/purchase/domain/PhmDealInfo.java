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
 * PhmDealInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2017年9月19日 下午3:48:09<br/>
 * <b>修改备注：</b>删除consId，添加reportDetailId属性<br/>
 * @version 1.0
 */
@Entity(name="PhmDealInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_deal_info")
public class PhmDealInfo extends Domain implements Serializable {

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
    
    @PropertyDesc("计划id")
    @Column(name="plan_id", nullable=true, length=32) 
    private String planId;
    
    @PropertyDesc("父节点id")
    @Column(name="parent_id", nullable=true, length=32) 
    private String parentId;
    
    @PropertyDesc("用户id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("交易申报明细id")
    @Column(name="report_detail_id", nullable=true, length=32) 
    private String reportDetailId;
    
    @PropertyDesc("电厂id")
    @Column(name="producer_id", nullable=true, length=32) 
    private String producerId;
    
    @PropertyDesc("成交电量")
    @Column(name="deal_pq", nullable=true, length=16) 
    private java.math.BigDecimal dealPq;
    
    @PropertyDesc("成交电价")
    @Column(name="deal_prc", nullable=true, length=16) 
    private java.math.BigDecimal dealPrc;
    
    @PropertyDesc("结算电价")
    @Column(name="delivery_prc", nullable=true, length=16) 
    private java.math.BigDecimal deliveryPrc;
    
    @PropertyDesc("机组id")
    @Column(name="generator_id", nullable=true, length=32) 
    private String generatorId;
    
    @PropertyDesc("交易对象")
    @Column(name="trader_name", nullable=true, length=64) 
    private String traderName;
    
    @PropertyDesc("合同转让方向")
    @Column(name="attorn_type", nullable=true, length=8) 
    private String attornType;
    
    @PropertyDesc("是否是年分项")
    @Column(name="subitem_flag", nullable=true, length=8) 
    private String subitemFlag;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    
    public String getReportDetailId() {
		return reportDetailId;
	}

	public void setReportDetailId(String reportDetailId) {
		this.reportDetailId = reportDetailId;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}
    
    public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getProducerId(){
        return producerId;
    }
    
    public void setProducerId(String producerId){
        this.producerId = producerId;
    }
    
    public java.math.BigDecimal getDealPq(){
        return dealPq;
    }
    
    public void setDealPq(java.math.BigDecimal dealPq){
        this.dealPq = dealPq;
    }
    
    public java.math.BigDecimal getDealPrc(){
        return dealPrc;
    }
    
    public void setDealPrc(java.math.BigDecimal dealPrc){
        this.dealPrc = dealPrc;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public java.math.BigDecimal getDeliveryPrc() {
		return deliveryPrc;
	}

	public void setDeliveryPrc(java.math.BigDecimal deliveryPrc) {
		this.deliveryPrc = deliveryPrc;
	}

	public String getGeneratorId() {
		return generatorId;
	}

	public void setGeneratorId(String generatorId) {
		this.generatorId = generatorId;
	}

	public String getTraderName() {
		return traderName;
	}

	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	public String getAttornType() {
		return attornType;
	}

	public void setAttornType(String attornType) {
		this.attornType = attornType;
	}

	public String getSubitemFlag() {
		return subitemFlag;
	}

	public void setSubitemFlag(String subitemFlag) {
		this.subitemFlag = subitemFlag;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}
	
}