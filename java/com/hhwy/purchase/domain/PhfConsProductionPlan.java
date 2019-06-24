package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhfConsProductionPlan
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhfConsProductionPlan")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_f_cons_production_plan")
public class PhfConsProductionPlan extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户标识")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("年份")
    @Column(name="year", nullable=true, length=4) 
    private String year;
    
    @PropertyDesc("产品名称")
    @Column(name="product_name", nullable=true, length=256) 
    private String productName;
    
    @PropertyDesc("产品计量单位")
    @Column(name="unit", nullable=true, length=10) 
    private String unit;
    
    @PropertyDesc("1月")
    @Column(name="Jan", nullable=true, length=10) 
    private java.math.BigDecimal jan;
    
    @PropertyDesc("2月")
    @Column(name="Feb", nullable=true, length=10) 
    private java.math.BigDecimal feb;
    
    @PropertyDesc("3月")
    @Column(name="Mar", nullable=true, length=10) 
    private java.math.BigDecimal mar;
    
    @PropertyDesc("4月")
    @Column(name="Apr", nullable=true, length=10) 
    private java.math.BigDecimal apr;
    
    @PropertyDesc("5月")
    @Column(name="May", nullable=true, length=10) 
    private java.math.BigDecimal may;
    
    @PropertyDesc("6月")
    @Column(name="Jun", nullable=true, length=10) 
    private java.math.BigDecimal jun;
    
    @PropertyDesc("7月")
    @Column(name="Jul", nullable=true, length=10) 
    private java.math.BigDecimal jul;
    
    @PropertyDesc("8月")
    @Column(name="Aug", nullable=true, length=10) 
    private java.math.BigDecimal aug;
    
    @PropertyDesc("9月")
    @Column(name="Sept", nullable=true, length=10) 
    private java.math.BigDecimal sept;
    
    @PropertyDesc("10月")
    @Column(name="Oct", nullable=true, length=10) 
    private java.math.BigDecimal oct;
    
    @PropertyDesc("11月")
    @Column(name="Nov", nullable=true, length=10) 
    private java.math.BigDecimal nov;
    
    @PropertyDesc("12月")
    @Column(name="Dece", nullable=true, length=10) 
    private java.math.BigDecimal dece;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
    	this.consId = consId;
    }
    
    public String getYear(){
        return year;
    }
    
    public void setYear(String year){
    	this.year = year;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public void setProductName(String productName){
    	this.productName = productName;
    }
    
    public String getUnit(){
        return unit;
    }
    
    public void setUnit(String unit){
    	this.unit = unit;
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
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
    	this.orgNo = orgNo;
    }
    
}