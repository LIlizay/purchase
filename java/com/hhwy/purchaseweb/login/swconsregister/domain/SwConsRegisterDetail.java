package com.hhwy.purchaseweb.login.swconsregister.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class SwConsRegisterDetail implements Serializable{
    
     /**
     * serialVersionUID
     * @return  the serialVersionUID
     * @since   1.0.0
    */
    private static final long serialVersionUID = -1946984742879799968L;

    private String id;
    
    /**用户名称**/
    private String consName;
    
    /**电压等级**/
    private String voltCode;
    
    /**用电类别**/
    private String elecTypeCode;
    
    /**用户类型**/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_registerConsType" ,field="consTypeName")
    private String consType;
    private String consTypeName;
    
    /**企业营业执照注册号**/
    private String registerNo;
    
    /**企业地址**/
    private String addr;
    
    /**联系人名称**/
    private String contName;
    
    /**联系电话**/
    private String phone;
    
    /**注册日期**/
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date registerDate;
    
    /**审核状态**/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_examineStatus" ,field="examineStatusName")
    private String examineStatus;
    private String examineStatusName;
    
    /**附件**/
    private String fileId;
    
    /**售电公司id**/
    private String companyId;
    
    /**部门id**/
    private String orgNo;

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }

    public String getVoltCode() {
        return voltCode;
    }

    public void setVoltCode(String voltCode) {
        this.voltCode = voltCode;
    }

    public String getElecTypeCode() {
        return elecTypeCode;
    }

    public void setElecTypeCode(String elecTypeCode) {
        this.elecTypeCode = elecTypeCode;
    }

    public String getConsType() {
        return consType;
    }

    public void setConsType(String consType) {
        this.consType = consType;
    }

    public String getConsTypeName() {
        return consTypeName;
    }

    public void setConsTypeName(String consTypeName) {
        this.consTypeName = consTypeName;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(String examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getExamineStatusName() {
        return examineStatusName;
    }

    public void setExamineStatusName(String examineStatusName) {
        this.examineStatusName = examineStatusName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
