package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SmPpa
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmPpa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_ppa")
public class SmPpa extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("合同编号")
    @Column(name="agre_no", nullable=true, length=16) 
    private String agreNo;
    
    @PropertyDesc("合同名称")
    @Column(name="agre_name", nullable=true, length=128) 
    private String agreName;
    
    @PropertyDesc("用户实体id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("合同类型")
    @Column(name="agre_type", nullable=true, length=8) 
    private String agreType;
    
    @PropertyDesc("合同版本号")
    @Column(name="agre_ver", nullable=true, length=32) 
    private String agreVer;
    
    @PropertyDesc("售电方签约人")
    @Column(name="party_b", nullable=true, length=8) 
    private String partyB;
    
    @PropertyDesc("客户签约人")
    @Column(name="party_a", nullable=true, length=8) 
    private String partyA;
    
    @PropertyDesc("签订时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="sign_date", nullable=true, length=10) 
    private Date signDate;
    
    @PropertyDesc("生效日期")
    @Column(name="effective_date", nullable=true, length=10) 
    private String effectiveDate;
    
    @PropertyDesc("失效日期")
    @Column(name="expiry_date", nullable=true, length=10) 
    private String expiryDate;
    
    @PropertyDesc("终止日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="stop_date", nullable=true, length=10) 
    private Date stopDate;
    
    @PropertyDesc("合同录入人")
    @Column(name="recorder", nullable=true, length=32) 
    private String recorder;
    
    @PropertyDesc("录入时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="record_date", nullable=true, length=10) 
    private Date recordDate;
    
    @PropertyDesc("合同状态")
    @Column(name="agre_status", nullable=true, length=8) 
    private String agreStatus;
    
    @PropertyDesc("附件")
    @Column(name="file_id", nullable=true, length=128) 
    private String fileId;
    
    @PropertyDesc("代理方式")
    @Column(name="proxy_mode", nullable=true, length=8) 
    private String proxyMode;
    
    @PropertyDesc("代理总电量")
    @Column(name="proxy_pq", nullable=true, length=16) 
    private java.math.BigDecimal proxyPq;
    
    @PropertyDesc("分配方式")
    @Column(name="dist_mode", nullable=true, length=8) 
    private String distMode;
    
    @PropertyDesc("1月")
    @Column(name="jan", nullable=true, length=16) 
    private java.math.BigDecimal jan;
    
    @PropertyDesc("2月")
    @Column(name="feb", nullable=true, length=16) 
    private java.math.BigDecimal feb;
    
    @PropertyDesc("3月")
    @Column(name="mar", nullable=true, length=16) 
    private java.math.BigDecimal mar;
    
    @PropertyDesc("4月")
    @Column(name="apr", nullable=true, length=16) 
    private java.math.BigDecimal apr;
    
    @PropertyDesc("5月")
    @Column(name="may", nullable=true, length=16) 
    private java.math.BigDecimal may;
    
    @PropertyDesc("6月")
    @Column(name="jun", nullable=true, length=16) 
    private java.math.BigDecimal jun;
    
    @PropertyDesc("7月")
    @Column(name="jul", nullable=true, length=16) 
    private java.math.BigDecimal jul;
    
    @PropertyDesc("8月")
    @Column(name="aug", nullable=true, length=16) 
    private java.math.BigDecimal aug;
    
    @PropertyDesc("9月")
    @Column(name="sept", nullable=true, length=16) 
    private java.math.BigDecimal sept;
    
    @PropertyDesc("10月")
    @Column(name="oct", nullable=true, length=16) 
    private java.math.BigDecimal oct;
    
    @PropertyDesc("11月")
    @Column(name="nov", nullable=true, length=16) 
    private java.math.BigDecimal nov;
    
    @PropertyDesc("12月")
    @Column(name="dece", nullable=true, length=16) 
    private java.math.BigDecimal dece;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getAgreNo(){
        return agreNo;
    }
    
    public void setAgreNo(String agreNo){
        this.agreNo = agreNo;
    }
    
    public String getAgreName(){
        return agreName;
    }
    
    public void setAgreName(String agreName){
        this.agreName = agreName;
    }
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getAgreType(){
        return agreType;
    }
    
    public void setAgreType(String agreType){
        this.agreType = agreType;
    }
    
    public String getAgreVer(){
        return agreVer;
    }
    
    public void setAgreVer(String agreVer){
        this.agreVer = agreVer;
    }
    
    public String getPartyB(){
        return partyB;
    }
    
    public void setPartyB(String partyB){
        this.partyB = partyB;
    }
    
    public String getPartyA(){
        return partyA;
    }
    
    public void setPartyA(String partyA){
        this.partyA = partyA;
    }
    
    public Date getSignDate(){
        return signDate;
    }
    
    public void setSignDate(Date signDate){
        this.signDate = signDate;
    }
    
    public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getStopDate(){
        return stopDate;
    }
    
    public void setStopDate(Date stopDate){
        this.stopDate = stopDate;
    }
    
    public String getRecorder(){
        return recorder;
    }
    
    public void setRecorder(String recorder){
        this.recorder = recorder;
    }
    
    public Date getRecordDate(){
        return recordDate;
    }
    
    public void setRecordDate(Date recordDate){
        this.recordDate = recordDate;
    }
    
    public String getAgreStatus(){
        return agreStatus;
    }
    
    public void setAgreStatus(String agreStatus){
        this.agreStatus = agreStatus;
    }
    
    public String getFileId(){
        return fileId;
    }
    
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
    
    public String getProxyMode(){
        return proxyMode;
    }
    
    public void setProxyMode(String proxyMode){
        this.proxyMode = proxyMode;
    }
    
    public java.math.BigDecimal getProxyPq(){
        return proxyPq;
    }
    
    public void setProxyPq(java.math.BigDecimal proxyPq){
        this.proxyPq = proxyPq;
    }
    
    public String getDistMode(){
        return distMode;
    }
    
    public void setDistMode(String distMode){
        this.distMode = distMode;
    }
    
    public java.math.BigDecimal getJan(){
        return jan;
    }
    
    public void setJan(java.math.BigDecimal jan){
        this.jan = jan;
    }
    
    public java.math.BigDecimal getFeb(){
        return feb;
    }
    
    public void setFeb(java.math.BigDecimal feb){
        this.feb = feb;
    }
    
    public java.math.BigDecimal getMar(){
        return mar;
    }
    
    public void setMar(java.math.BigDecimal mar){
        this.mar = mar;
    }
    
    public java.math.BigDecimal getApr(){
        return apr;
    }
    
    public void setApr(java.math.BigDecimal apr){
        this.apr = apr;
    }
    
    public java.math.BigDecimal getMay(){
        return may;
    }
    
    public void setMay(java.math.BigDecimal may){
        this.may = may;
    }
    
    public java.math.BigDecimal getJun(){
        return jun;
    }
    
    public void setJun(java.math.BigDecimal jun){
        this.jun = jun;
    }
    
    public java.math.BigDecimal getJul(){
        return jul;
    }
    
    public void setJul(java.math.BigDecimal jul){
        this.jul = jul;
    }
    
    public java.math.BigDecimal getAug(){
        return aug;
    }
    
    public void setAug(java.math.BigDecimal aug){
        this.aug = aug;
    }
    
    public java.math.BigDecimal getSept(){
        return sept;
    }
    
    public void setSept(java.math.BigDecimal sept){
        this.sept = sept;
    }
    
    public java.math.BigDecimal getOct(){
        return oct;
    }
    
    public void setOct(java.math.BigDecimal oct){
        this.oct = oct;
    }
    
    public java.math.BigDecimal getNov(){
        return nov;
    }
    
    public void setNov(java.math.BigDecimal nov){
        this.nov = nov;
    }
    
    public java.math.BigDecimal getDece(){
        return dece;
    }
    
    public void setDece(java.math.BigDecimal dece){
        this.dece = dece;
    }
    
    public String getCompanyId(){
        return companyId;
    }
    
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}