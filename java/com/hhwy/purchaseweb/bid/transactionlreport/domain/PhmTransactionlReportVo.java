package com.hhwy.purchaseweb.bid.transactionlreport.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.purchase.domain.PhmTransactionlReport;

//import com.hhwy.purchase.domain.PhmTransactionlReport;

public class PhmTransactionlReportVo {
    
    private List<PhmTransactionlReport> transactionlReportList = new ArrayList<>();

    public List<PhmTransactionlReport> getTransactionlReportList() {
        return transactionlReportList;
    }

    public void setTransactionlReportList(
            List<PhmTransactionlReport> transactionlReportList) {
        this.transactionlReportList = transactionlReportList;
    }
    
}
