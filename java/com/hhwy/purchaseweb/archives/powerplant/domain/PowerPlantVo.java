package com.hhwy.purchaseweb.archives.powerplant.domain;

import java.util.List;

import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.PhcContactsInfo;
import com.hhwy.purchase.domain.PhcElecInfo;
import com.hhwy.purchase.domain.PhcGeneratorSet;

public class PowerPlantVo extends PagingProperty{
    
    /** 电厂名称  **/
    private String elecName;
    
    /** 电厂类别  **/
    private String elecTypeCode;
    
    /** 所属发电集团  **/
    private String blocId;

    private PhcElecInfo phcElecInfo;
    
    private PhcContactsInfo phcContactsInfo;
    
    private List<PhcGeneratorSet> list;
    
    private List<PhcGeneratorSetDetail> detailList;
    
    private String[] deleteList;
    
    public String getElecName() {
        return elecName;
    }

    public void setElecName(String elecName) {
        this.elecName = elecName;
    }

    public String getElecTypeCode() {
        return elecTypeCode;
    }

    public void setElecTypeCode(String elecTypeCode) {
        this.elecTypeCode = elecTypeCode;
    }

    public String getBlocId() {
        return blocId;
    }

    public void setBlocId(String blocId) {
        this.blocId = blocId;
    }

    public PhcElecInfo getPhcElecInfo() {
        return phcElecInfo;
    }

    public void setPhcElecInfo(PhcElecInfo phcElecInfo) {
        this.phcElecInfo = phcElecInfo;
    }

    public PhcContactsInfo getPhcContactsInfo() {
        return phcContactsInfo;
    }

    public void setPhcContactsInfo(PhcContactsInfo phcContactsInfo) {
        this.phcContactsInfo = phcContactsInfo;
    }

    public List<PhcGeneratorSet> getList() {
        return list;
    }

    public void setList(List<PhcGeneratorSet> list) {
        this.list = list;
    }

    public List<PhcGeneratorSetDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<PhcGeneratorSetDetail> detailList) {
        this.detailList = detailList;
    }

    public String[] getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(String[] deleteList) {
        this.deleteList = deleteList;
    }
}
