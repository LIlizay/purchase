package com.hhwy.purchaseweb.contract.smagretemplate.domain;

import java.io.Serializable;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;


public class SmAgreTemplateDetail implements Serializable{
    
     /**
     * serialVersionUID
     * @return  the serialVersionUID
     * @since   1.0.0
    */
    private static final long serialVersionUID = 3755332860116657329L;
    
    /**  id  **/
    private String id;
    
    /** 合同模板号 **/
    private String templateVer;
    
    /** 合同模板名称 **/
    private String templateName;
    
    /** 合同类型 **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_sellElecContractType" ,field="templateTypeName")
    private String templateType;
    private String templateTypeName;
    
    /** 模板备注 **/
    private String remarks;
    
    /** 合同状态 **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_contractVersStatus" ,field="agreStatusName")
    private String agreStatus;
    private String agreStatusName;
    
    /** 合同模板附件 **/
    private String fileId;
    
    /** 部门id **/
    private String orgNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateVer() {
        return templateVer;
    }

    public void setTemplateVer(String templateVer) {
        this.templateVer = templateVer;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateTypeName() {
        return templateTypeName;
    }

    public void setTemplateTypeName(String templateTypeName) {
        this.templateTypeName = templateTypeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAgreStatus() {
        return agreStatus;
    }

    public void setAgreStatus(String agreStatus) {
        this.agreStatus = agreStatus;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getAgreStatusName() {
        return agreStatusName;
    }

    public void setAgreStatusName(String agreStatusName) {
        this.agreStatusName = agreStatusName;
    }
}
