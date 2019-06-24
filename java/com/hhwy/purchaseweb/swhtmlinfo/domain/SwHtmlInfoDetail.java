package com.hhwy.purchaseweb.swhtmlinfo.domain;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

/**
 * 
 * <b>类 名 称：</b>SwHtmlInfoDetail<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年6月8日 下午2:32:39<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SwHtmlInfoDetail {

	/**
	 * id
	 */
	private String id;
	
    @PropertyDesc("信息标题")
    private String title;
    
    @PropertyDesc("省份标识")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="provinceCode" ,field="provinceCodeName")
    private String provinceCode;
    private String provinceCodeName;
    
	private Date beginTime;
    
	private Date endTime;
    
    @PropertyDesc("发布人")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_USER,field="releasePersonName")
    private String releasePerson;
    private String releasePersonName;
    
    @PropertyDesc("发布时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Timestamp releaseTime;
    
    @PropertyDesc("发布状态(01:未发布,02:已发布)")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_releaseState" ,field="releaseStateName")
    private String releaseState;
    private String releaseStateName;
    
    @PropertyDesc("信息内容")
    private String content;
    
    @PropertyDesc("售电公司id")
    private String companyId;
    
    @PropertyDesc("部门id")
    private String orgNo;
    
    //编辑器内容
    private String ueditorConcent;
    
    @PropertyDesc("热点状态")
    private String hotStatus;
    
    @PropertyDesc("详细信息url")
    private String htmUrl;
    
    @PropertyDesc("背景图url")
    private String bgImage;

    @PropertyDesc("上传文件")
    private String fileId;
    
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleasePerson() {
		return releasePerson;
	}

	public void setReleasePerson(String releasePerson) {
		this.releasePerson = releasePerson;
	}

	public String getReleasePersonName() {
		return releasePersonName;
	}

	public void setReleasePersonName(String releasePersonName) {
		this.releasePersonName = releasePersonName;
	}

	public Timestamp getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Timestamp releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseState() {
		return releaseState;
	}

	public void setReleaseState(String releaseState) {
		this.releaseState = releaseState;
	}

	public String getReleaseStateName() {
		return releaseStateName;
	}

	public void setReleaseStateName(String releaseStateName) {
		this.releaseStateName = releaseStateName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getUeditorConcent() {
		return ueditorConcent;
	}

	public void setUeditorConcent(String ueditorConcent) {
		this.ueditorConcent = ueditorConcent;
	}

	public String getHotStatus() {
		return hotStatus;
	}

	public void setHotStatus(String hotStatus) {
		this.hotStatus = hotStatus;
	}

	public String getHtmUrl() {
		return htmUrl;
	}

	public void setHtmUrl(String htmUrl) {
		this.htmUrl = htmUrl;
	}

	public String getBgImage() {
		return bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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
