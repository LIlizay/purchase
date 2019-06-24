package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SwConsRegister
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SwConsRegister")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_w_cons_register")
public class SwConsRegister extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户名称")
    @Column(name="cons_name", nullable=true, length=32) 
    private String consName;
    
    @PropertyDesc("电压等级")
    @Column(name="volt_code", nullable=true, length=8) 
    private String voltCode;
    
    @PropertyDesc("用电类别")
    @Column(name="elec_type_code", nullable=true, length=8) 
    private String elecTypeCode;
    
    @PropertyDesc("用户类型")
    @Column(name="cons_type", nullable=true, length=8) 
    private String consType;
    
    @PropertyDesc("企业营业执照注册号")
    @Column(name="register_no", nullable=true, length=32) 
    private String registerNo;
    
    @PropertyDesc("企业地址")
    @Column(name="addr", nullable=true, length=256) 
    private String addr;
    
    @PropertyDesc("联系人名称")
    @Column(name="cont_name", nullable=true, length=32) 
    private String contName;
    
    @PropertyDesc("联系电话")
    @Column(name="phone", nullable=true, length=32) 
    private String phone;
    
    @PropertyDesc("注册日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="register_date", nullable=true, length=10) 
    private Date registerDate;
    
    @PropertyDesc("审核状态")
    @Column(name="examine_status", nullable=true, length=8) 
    private String examineStatus;
    
    @PropertyDesc("附件")
    @Column(name="file_id", nullable=true, length=256) 
    private String fileId;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getConsName(){
        return consName;
    }
    
    public void setConsName(String consName){
        this.consName = consName;
    }
    
    public String getVoltCode(){
        return voltCode;
    }
    
    public void setVoltCode(String voltCode){
        this.voltCode = voltCode;
    }
    
    public String getElecTypeCode(){
        return elecTypeCode;
    }
    
    public void setElecTypeCode(String elecTypeCode){
        this.elecTypeCode = elecTypeCode;
    }
    
    public String getConsType(){
        return consType;
    }
    
    public void setConsType(String consType){
        this.consType = consType;
    }
    
    public String getRegisterNo(){
        return registerNo;
    }
    
    public void setRegisterNo(String registerNo){
        this.registerNo = registerNo;
    }
    
    public String getAddr(){
        return addr;
    }
    
    public void setAddr(String addr){
        this.addr = addr;
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
    
    public Date getRegisterDate(){
        return registerDate;
    }
    
    public void setRegisterDate(Date registerDate){
        this.registerDate = registerDate;
    }
    
    public String getExamineStatus(){
        return examineStatus;
    }
    
    public void setExamineStatus(String examineStatus){
        this.examineStatus = examineStatus;
    }
    
    public String getFileId(){
        return fileId;
    }
    
    public void setFileId(String fileId){
        this.fileId = fileId;
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