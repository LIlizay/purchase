package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SwConsInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SwConsInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_w_cons_info")
public class SwConsInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户档案标识")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("用户账号（loginName）")
    @Column(name="login_name", nullable=true, length=32) 
    private String loginName;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getLoginName(){
        return loginName;
    }
    
    public void setLoginName(String loginName){
        this.loginName = loginName;
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