package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * PhmAgreTemplate
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmAgreTemplate")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_agre_template")
public class PhmAgreTemplate extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("合同模板号")
    @Column(name="template_ver", nullable=true, length=32) 
    private String templateVer;
    
    @PropertyDesc("合同模板名称")
    @Column(name="template_name", nullable=true, length=128) 
    private String templateName;
    
    @PropertyDesc("合同类型")
    @Column(name="template_type", nullable=true, length=8) 
    private String templateType;
    
    @PropertyDesc("模板备注")
    @Column(name="remarks", nullable=true, length=256) 
    private String remarks;
    
    @PropertyDesc("合同模板附件")
    @Column(name="file_id", nullable=true, length=128) 
    private String fileId;
    
    @PropertyDesc("模板状态")
    @Column(name="template_status", nullable=true, length=8) 
    private String templateStatus;
    
    @PropertyDesc("创建日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="create_date", nullable=true, length=10) 
    private Date createDate;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getTemplateVer(){
        return templateVer;
    }
    
    public void setTemplateVer(String templateVer){
        this.templateVer = templateVer;
    }
    
    public String getTemplateName(){
        return templateName;
    }
    
    public void setTemplateName(String templateName){
        this.templateName = templateName;
    }
    
    public String getTemplateType(){
        return templateType;
    }
    
    public void setTemplateType(String templateType){
        this.templateType = templateType;
    }
    
    public String getRemarks(){
        return remarks;
    }
    
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
    
    public String getFileId(){
        return fileId;
    }
    
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
    
    public String getTemplateStatus(){
        return templateStatus;
    }
    
    public void setTemplateStatus(String templateStatus){
        this.templateStatus = templateStatus;
    }
    
    public Date getCreateDate(){
        return createDate;
    }
    
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}