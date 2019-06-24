package com.hhwy.purchaseweb.login.swconsregister.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SwConsRegister;

/**
 * SwConsRegisterVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SwConsRegisterVo extends PagingProperty{

    private String startDate;
    
    private String endDate;
    
	private SwConsRegister swConsRegister = BaseModel.getModel(SwConsRegister.class);
	
	public SwConsRegister getSwConsRegister(){
		return swConsRegister;
	}
	
	public void setSwConsRegister(SwConsRegister swConsRegister){
		this.swConsRegister = swConsRegister;
	}

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}