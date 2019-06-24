package com.hhwy.purchaseweb.bid.phmeleclist.domain;

import com.hhwy.purchase.domain.PhmElecList;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmElecListVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmElecListVo extends PagingProperty{

    private String consName;
    
    private String ym;
    
    private String voltCode;
    
	private PhmElecList phmElecList = BaseModel.getModel(PhmElecList.class);
	
	public PhmElecList getPhmElecList(){
		return phmElecList;
	}
	
	public void setPhmElecList(PhmElecList phmElecList){
		this.phmElecList = phmElecList;
	}

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public String getVoltCode() {
        return voltCode;
    }

    public void setVoltCode(String voltCode) {
        this.voltCode = voltCode;
    }
}