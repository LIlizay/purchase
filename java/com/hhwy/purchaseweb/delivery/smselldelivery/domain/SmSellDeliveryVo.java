package com.hhwy.purchaseweb.delivery.smselldelivery.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmSellDelivery;

/**
 * SmSellDeliveryVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmSellDeliveryVo extends PagingProperty{
	
    private String startYm;
    
    private String endYm;
    
    private String consName;
    
    private String status;
    
    private String ym;
    
	private SmSellDelivery smSellDelivery = BaseModel.getModel(SmSellDelivery.class);
	
	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SmSellDelivery getSmSellDelivery(){
		return smSellDelivery;
	}
	
	public void setSmSellDelivery(SmSellDelivery smSellDelivery){
		this.smSellDelivery = smSellDelivery;
	}

    public String getStartYm() {
        return startYm;
    }

    public void setStartYm(String startYm) {
        this.startYm = startYm;
    }

    public String getEndYm() {
        return endYm;
    }

    public void setEndYm(String endYm) {
        this.endYm = endYm;
    }

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }
	
	
}