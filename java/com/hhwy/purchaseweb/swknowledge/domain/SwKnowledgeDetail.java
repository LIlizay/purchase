package com.hhwy.purchaseweb.swknowledge.domain;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

/**
 * 
 * <b>类 名 称：</b>SwKnowledgeDetail<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>LiXinze<br/>
 * <b>修 改 人：</b>LiXinze<br/>
 * <b>修改时间：</b>2017年11月8日 下午2:32:39<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SwKnowledgeDetail {

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
    
    @PropertyDesc("信息内容")
    private String content;
    
    //编辑器内容
    private String ueditorConcent;
    
    @PropertyDesc("详细信息url")
    private String htmUrl;
    
    @PropertyDesc("背景图url")
    private String bgImage;

    @PropertyDesc("上传文件")
    private String fileId;
    
    @PropertyDesc("知识分类")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_knowledgeClass",field="knowledgeClassName")
    private String knowledgeClass;
    private String knowledgeClassName;
    
    //录入时间
    private String createdTime;
    
	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getKnowledgeClass() {
		return knowledgeClass;
	}

	public void setKnowledgeClass(String knowledgeClass) {
		this.knowledgeClass = knowledgeClass;
	}

	public String getKnowledgeClassName() {
		return knowledgeClassName;
	}

	public void setKnowledgeClassName(String knowledgeClassName) {
		this.knowledgeClassName = knowledgeClassName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getUeditorConcent() {
		return ueditorConcent;
	}

	public void setUeditorConcent(String ueditorConcent) {
		this.ueditorConcent = ueditorConcent;
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
