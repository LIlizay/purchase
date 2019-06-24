package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * ScIndustrialZone
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="ScIndustrialZone")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_c_industrial_zone")
public class ScIndustrialZone extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("省")
    @Column(name="province_code", nullable=true, length=8) 
    private String provinceCode;
    
    @PropertyDesc("地级市")
    @Column(name="city_code", nullable=true, length=8) 
    private String cityCode;
    
    @PropertyDesc("区/县")
    @Column(name="county_code", nullable=true, length=8) 
    private String countyCode;
    
    @PropertyDesc("园区名称")
    @Column(name="iz_name", nullable=true, length=64) 
    private String izName;
    
    @PropertyDesc("园区描述")
    @Column(name="iz_content", nullable=true, length=512) 
    private String izContent;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getCompanyId(){
        return companyId;
    }
    
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }
    
    public String getCityCode(){
        return cityCode;
    }
    
    public void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    
    public String getCountyCode(){
        return countyCode;
    }
    
    public void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    
    public String getIzName(){
        return izName;
    }
    
    public void setIzName(String izName){
        this.izName = izName;
    }
    
    public String getIzContent(){
        return izContent;
    }
    
    public void setIzContent(String izContent){
        this.izContent = izContent;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
}