package com.hhwy.purchaseweb.plan.phmbusinessconsrela.domain;

import com.hhwy.purchase.domain.PhmBusinessConsRela;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmBusinessConsRelaVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmBusinessConsRelaVo extends PagingProperty{

    private String year;
    
	private PhmBusinessConsRela phmBusinessConsRela = BaseModel.getModel(PhmBusinessConsRela.class);
	
	public PhmBusinessConsRela getPhmBusinessConsRela(){
		return phmBusinessConsRela;
	}
	
	public void setPhmBusinessConsRela(PhmBusinessConsRela phmBusinessConsRela){
		this.phmBusinessConsRela = phmBusinessConsRela;
	}

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
	
	
}