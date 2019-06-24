package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * ScContactsInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="ScContactsInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_c_contacts_info")
public class ScContactsInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("传真号码")
    @Column(name="fax", nullable=true, length=32) 
    private String fax;
    
    @PropertyDesc("联系地址")
    @Column(name="addr", nullable=true, length=256) 
    private String addr;
    
    @PropertyDesc("电子邮箱")
    @Column(name="e_mail", nullable=true, length=32) 
    private String eMail;
    
    @PropertyDesc("联系人名称")
    @Column(name="cont_name", nullable=true, length=32) 
    private String contName;
    
    @PropertyDesc("手机号")
    @Column(name="phone", nullable=true, length=256) 
    private String phone;
    
    @PropertyDesc("邮政编码")
    @Column(name="postcode", nullable=true, length=8) 
    private String postcode;
    
    @PropertyDesc("用户id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("办公电话")
    @Column(name="office_phone", nullable=true, length=16) 
    private String officePhone;
    
    @PropertyDesc("职务")
    @Column(name="post", nullable=true, length=32) 
    private String post;
    
    public String getFax(){
        return fax;
    }
    
    public void setFax(String fax){
        this.fax = fax;
    }
    
    public String getAddr(){
        return addr;
    }
    
    public void setAddr(String addr){
        this.addr = addr;
    }
    
    public String geteMail(){
        return eMail;
    }
    
    public void seteMail(String eMail){
        this.eMail = eMail;
    }
    
    public String getContName(){
        return contName;
    }
    
    public void setContName(String contName){
        this.contName = contName;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    public String getPostcode(){
        return postcode;
    }
    
    public void setPostcode(String postcode){
        this.postcode = postcode;
    }
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
    public String getOfficePhone(){
        return officePhone;
    }
    
    public void setOfficePhone(String officePhone){
        this.officePhone = officePhone;
    }
    
    public String getPost(){
        return post;
    }
    
    public void setPost(String post){
        this.post = post;
    }
    
}