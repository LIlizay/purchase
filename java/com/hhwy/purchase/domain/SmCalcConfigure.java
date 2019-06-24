package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmCalcConfigure
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmCalcConfigure")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_calc_configure")
public class SmCalcConfigure extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("省份(编码)")
    @Column(name="province", nullable=true, length=16) 
    private String province;
    
    @PropertyDesc("售电公司(库名)")
    @Column(name="sell_db", nullable=true, length=16) 
    private String sellDb;
    
    @PropertyDesc("算法名称")
    @Column(name="calc_name", nullable=true, length=128) 
    private String calcName;
    
    @PropertyDesc("算法说明)")
    @Column(name="calc_content", nullable=true, length=1024) 
    private String calcContent;
    
    @PropertyDesc("脚本选择(编码)")
    @Column(name="calc_code", nullable=true, length=32) 
    private String calcCode;
    
    @PropertyDesc("组织机构")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("脚本参数(json)")
    @Column(name="calc_param", nullable=true, length=1024) 
    private String calcParam;
    
    @PropertyDesc("状态")
    @Column(name="status", nullable=true, length=8) 
    private String status;

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCalcParam() {
		return calcParam;
	}

	public void setCalcParam(String calcParam) {
		this.calcParam = calcParam;
	}

	public String getProvince(){
        return province;
    }
    
    public void setProvince(String province){
        this.province = province;
    }
    
    public String getSellDb(){
        return sellDb;
    }
    
    public void setSellDb(String sellDb){
        this.sellDb = sellDb;
    }
    
    public String getCalcName(){
        return calcName;
    }
    
    public void setCalcName(String calcName){
        this.calcName = calcName;
    }
    
    public String getCalcContent(){
        return calcContent;
    }
    
    public void setCalcContent(String calcContent){
        this.calcContent = calcContent;
    }
    
    public String getCalcCode(){
        return calcCode;
    }
    
    public void setCalcCode(String calcCode){
        this.calcCode = calcCode;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}