package com.hhwy.purchaseweb.forecast.phfcoalprice.domain;

import com.hhwy.purchase.domain.PhfCoalPrice;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhfCoalPriceVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhfCoalPriceVo extends PagingProperty{

    private String startYm;
    
    private String endYm;
    
	private PhfCoalPrice phfCoalPrice = BaseModel.getModel(PhfCoalPrice.class);
	
	public PhfCoalPrice getPhfCoalPrice(){
		return phfCoalPrice;
	}
	
	public void setPhfCoalPrice(PhfCoalPrice phfCoalPrice){
		this.phfCoalPrice = phfCoalPrice;
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
	
}