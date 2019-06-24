package com.hhwy.purchaseweb.bid.transactionlreport.domain;

import com.hhwy.business.core.modelutil.PagingProperty;

public class TransactionlReportVo extends PagingProperty{
    
    private String planId;
    
    private String consId;
    
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getConsId() {
        return consId;
    }

    public void setConsId(String consId) {
        this.consId = consId;
    }
    
}
