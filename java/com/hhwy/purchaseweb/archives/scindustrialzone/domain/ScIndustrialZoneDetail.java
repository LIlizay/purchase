package com.hhwy.purchaseweb.archives.scindustrialzone.domain;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

/**
 * 
 * <b>类 名 称：</b>ScIndustrialZoneDetail<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年5月22日 下午1:52:54<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ScIndustrialZoneDetail {
    //id
    private String id;
    
    @PropertyDesc("省")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="provinceCode" ,field="provinceCodeName")
    private String provinceCode;
    private String provinceCodeName;
    
    @PropertyDesc("地级市")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="cityCode" ,field="cityCodeName")
    private String cityCode;
    private String cityCodeName;
    
    @PropertyDesc("区/县")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="countyCode" ,field="countyCodeName")
    private String countyCode;
    private String countyCodeName;
    
    @PropertyDesc("园区名称")
    private String izName;
    
    @PropertyDesc("园区描述")
    private String izContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCodeName() {
        return cityCodeName;
    }

    public void setCityCodeName(String cityCodeName) {
        this.cityCodeName = cityCodeName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyCodeName() {
        return countyCodeName;
    }

    public void setCountyCodeName(String countyCodeName) {
        this.countyCodeName = countyCodeName;
    }

    public String getIzName() {
        return izName;
    }

    public void setIzName(String izName) {
        this.izName = izName;
    }

    public String getIzContent() {
        return izContent;
    }

    public void setIzContent(String izContent) {
        this.izContent = izContent;
    }

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceCodeName() {
		return provinceCodeName;
	}

	public void setProvinceCodeName(String provinceCodeName) {
		this.provinceCodeName = provinceCodeName;
	}
}
