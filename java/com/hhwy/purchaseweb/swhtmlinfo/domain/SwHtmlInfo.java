package com.hhwy.purchaseweb.swhtmlinfo.domain;

import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SwHtmlInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SwHtmlInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_w_html_info")
public class SwHtmlInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("信息标题")
    @Column(name="title", nullable=true, length=128) 
    private String title;
    
    @PropertyDesc("发布人")
    @Column(name="release_person", nullable=true, length=32) 
    private String releasePerson;
    
    @PropertyDesc("发布时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="release_time", nullable=true, length=19) 
    private Timestamp releaseTime;
    
    @PropertyDesc("发布状态(01:未发布,02:已发布)")
    @Column(name="release_state", nullable=true, length=2) 
    private String releaseState;
    
    @PropertyDesc("省份标识")
    @Column(name="province_code", nullable=true, length=8) 
    private String provinceCode;
    
    @PropertyDesc("信息内容")
    @Column(name="content", nullable=true, length=2048) 
    private String content;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("热点状态")
    @Column(name="hot_status", nullable=true, length=8) 
    private String hotStatus;
    
    @PropertyDesc("详细信息url")
    @Column(name="htm_url", nullable=true, length=256) 
    private String htmUrl;
    
    @PropertyDesc("背景图url")
    @Column(name="bg_image", nullable=true, length=256) 
    private String bgImage;
    
    @PropertyDesc("上传文件id")
    @Column(name="file_id", nullable=true, length=256) 
    private String fileId;
    
    @PropertyDesc("生效时间")
    @Column(name="begin_time", nullable=true, length=256) 
    private Date beginTime;
    
    @PropertyDesc("失效时间")
    @Column(name="end_time", nullable=true, length=256) 
    private Date endTime;
    
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

	public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getReleasePerson(){
        return releasePerson;
    }
    
    public void setReleasePerson(String releasePerson){
        this.releasePerson = releasePerson;
    }
    
    public Timestamp getReleaseTime(){
        return releaseTime;
    }
    
    public void setReleaseTime(Timestamp releaseTime){
        this.releaseTime = releaseTime;
    }
    
    public String getReleaseState(){
        return releaseState;
    }
    
    public void setReleaseState(String releaseState){
        this.releaseState = releaseState;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public String getCompanyId(){
        return companyId;
    }
    
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
    public String getHotStatus(){
        return hotStatus;
    }
    
    public void setHotStatus(String hotStatus){
        this.hotStatus = hotStatus;
    }
    
    public String getHtmUrl(){
        return htmUrl;
    }
    
    public void setHtmUrl(String htmUrl){
        this.htmUrl = htmUrl;
    }
    
    public String getBgImage(){
        return bgImage;
    }
    
    public void setBgImage(String bgImage){
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
}