package com.hhwy.purchaseweb.archives.scdevicerelation.domain;

import java.util.List;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.collectionplatform.all.domain.DeviceDetail;

public class ScDeviceRelationDetail {
	
	private String id;		//设备关系表 主键id
	
	private String state;		//树表格父节点字段 是否打开节点
	
	private String _parentId;	//子节点字段 对应父节点id
	
	private String consId;		//用户id
	
	private String consName;    //用户名称
	
	private String treeId; //根节点idField
	
	   //用电类别
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeName")
    private String elecTypeCode;
    private String elecTypeName;
    
    //电压等级
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName;
    
    //营销户号
    private String marketConsNo;
    
    //设备ID
    private String deviceId;
    
    //设备编号
    private String deviceCode;
    
    //设备名称
    private String deviceName;
    
    //平台地址
    private String platformAddress;
    
    //设备类型
    private String devTypeName;
    
    //节点数量
    private Integer deviceCount;
    
    //采集接口返回的详情数据
    private List<DeviceDetail> deviceDetail;
    
    /*报表页面 列表显示用*/
    //5分钟
    private String fiveMin;
    
    //10分钟
    private String tenMin;
    
    //15分钟
    private String fifMin;
    
    //20分钟
    private String twenMin;
    
    //25分钟
    private String fwenFiveMin;
    //30分钟
    private String thirMin;
    
    //35分钟
    private String thirFiveMin;
    
    //40分钟
    private String fortMin;
    
    //45分钟
    private String fortFiveMin;
    
    //50分钟
    private String fiftMin;
    
    //55分钟
    private String fiftFiveMin;
    
    //60分钟
    private String sixtMin;
    
    
	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getElecTypeName() {
		return elecTypeName;
	}

	public void setElecTypeName(String elecTypeName) {
		this.elecTypeName = elecTypeName;
	}

	public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}

	public String getVoltCodeName() {
		return voltCodeName;
	}

	public void setVoltCodeName(String voltCodeName) {
		this.voltCodeName = voltCodeName;
	}

	public String getMarketConsNo() {
		return marketConsNo;
	}

	public void setMarketConsNo(String marketConsNo) {
		this.marketConsNo = marketConsNo;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public List<DeviceDetail> getDeviceDetail() {
		return deviceDetail;
	}

	public void setDeviceDetail(List<DeviceDetail> deviceDetail) {
		this.deviceDetail = deviceDetail;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPlatformAddress() {
		return platformAddress;
	}

	public void setPlatformAddress(String platformAddress) {
		this.platformAddress = platformAddress;
	}

	public String getDevTypeName() {
		return devTypeName;
	}

	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}

	public Integer getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getFiveMin() {
		return fiveMin;
	}

	public void setFiveMin(String fiveMin) {
		this.fiveMin = fiveMin;
	}

	public String getTenMin() {
		return tenMin;
	}

	public void setTenMin(String tenMin) {
		this.tenMin = tenMin;
	}

	public String getFifMin() {
		return fifMin;
	}

	public void setFifMin(String fifMin) {
		this.fifMin = fifMin;
	}

	public String getTwenMin() {
		return twenMin;
	}

	public void setTwenMin(String twenMin) {
		this.twenMin = twenMin;
	}

	public String getFwenFiveMin() {
		return fwenFiveMin;
	}

	public void setFwenFiveMin(String fwenFiveMin) {
		this.fwenFiveMin = fwenFiveMin;
	}

	public String getThirMin() {
		return thirMin;
	}

	public void setThirMin(String thirMin) {
		this.thirMin = thirMin;
	}

	public String getThirFiveMin() {
		return thirFiveMin;
	}

	public void setThirFiveMin(String thirFiveMin) {
		this.thirFiveMin = thirFiveMin;
	}

	public String getFortMin() {
		return fortMin;
	}

	public void setFortMin(String fortMin) {
		this.fortMin = fortMin;
	}

	public String getFortFiveMin() {
		return fortFiveMin;
	}

	public void setFortFiveMin(String fortFiveMin) {
		this.fortFiveMin = fortFiveMin;
	}

	public String getFiftMin() {
		return fiftMin;
	}

	public void setFiftMin(String fiftMin) {
		this.fiftMin = fiftMin;
	}

	public String getFiftFiveMin() {
		return fiftFiveMin;
	}

	public void setFiftFiveMin(String fiftFiveMin) {
		this.fiftFiveMin = fiftFiveMin;
	}

	public String getSixtMin() {
		return sixtMin;
	}

	public void setSixtMin(String sixtMin) {
		this.sixtMin = sixtMin;
	}
	
	
}
