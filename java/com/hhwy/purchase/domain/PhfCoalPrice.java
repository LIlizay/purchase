package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhfCoalPrice
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhfCoalPrice")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_f_coal_price")
public class PhfCoalPrice extends Domain implements Serializable {

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
    
    @PropertyDesc("种类")
    @Column(name="coal_type", nullable=true, length=8) 
    private String coalType;
    
    @PropertyDesc("价格")
    @Column(name="price", nullable=true, length=10) 
    private java.math.BigDecimal price;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public String getCoalType(){
        return coalType;
    }
    
    public void setCoalType(String coalType){
        this.coalType = coalType;
    }
    
    public java.math.BigDecimal getPrice(){
        return price;
    }
    
    public void setPrice(java.math.BigDecimal price){
        this.price = price;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}