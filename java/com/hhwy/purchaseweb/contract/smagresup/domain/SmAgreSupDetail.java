package com.hhwy.purchaseweb.contract.smagresup.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

/**
 * 
 * <b>类 名 称：</b>SmAgreSupDetail<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年3月20日 下午3:04:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmAgreSupDetail {
    
    private String id;
    
    @PropertyDesc("购电合同实体id")
    private String ppaId;
    
    @PropertyDesc("补充协议编号")
    private String agreNo;
    
    @PropertyDesc("补充协议名称")
    private String agreName;
    
    @PropertyDesc("签订日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date signDate;
    
    @PropertyDesc("售电方签订人")
    private String sellParty;
    
    @PropertyDesc("客户签订人")
    private String custParty;
    
    @PropertyDesc("协议状态")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_contractStatusCode" ,field="agreStatusName")
    private String agreStatus;
    private String agreStatusName;
    
    @PropertyDesc("生效日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date effectiveDate;
    
    @PropertyDesc("失效日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date expiryDate;
    
    @PropertyDesc("协议附件")
    private String fileId;
    
    @PropertyDesc("协议文本内容")
    private String agreContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPpaId() {
        return ppaId;
    }

    public void setPpaId(String ppaId) {
        this.ppaId = ppaId;
    }

    public String getAgreNo() {
        return agreNo;
    }

    public void setAgreNo(String agreNo) {
        this.agreNo = agreNo;
    }

    public String getAgreName() {
        return agreName;
    }

    public void setAgreName(String agreName) {
        this.agreName = agreName;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSellParty() {
        return sellParty;
    }

    public void setSellParty(String sellParty) {
        this.sellParty = sellParty;
    }

    public String getCustParty() {
        return custParty;
    }

    public void setCustParty(String custParty) {
        this.custParty = custParty;
    }

    public String getAgreStatus() {
        return agreStatus;
    }

    public void setAgreStatus(String agreStatus) {
        this.agreStatus = agreStatus;
    }

    public String getAgreStatusName() {
        return agreStatusName;
    }

    public void setAgreStatusName(String agreStatusName) {
        this.agreStatusName = agreStatusName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getAgreContent() {
        return agreContent;
    }

    public void setAgreContent(String agreContent) {
        this.agreContent = agreContent;
    }
}
