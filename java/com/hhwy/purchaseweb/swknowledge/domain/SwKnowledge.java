package com.hhwy.purchaseweb.swknowledge.domain;

import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;

import java.io.Serializable;

import javax.persistence.*;
/**
 * SwKnowledge
 * @author LiXinze
 * @date 2017-11-8 13:45:53
 * @version 1.0
 */
@Entity(name="SwKnowledge")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_w_knowledge")
public class SwKnowledge extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("知识标题")
    @Column(name="title", nullable=true, length=128) 
    private String title;
    
    @PropertyDesc("省份标识")
    @Column(name="province_code", nullable=true, length=8) 
    private String provinceCode;
    
    @PropertyDesc("信息内容")
    @Column(name="content", nullable=true, length=2048) 
    private String content;
    
    @PropertyDesc("详细信息url")
    @Column(name="htm_url", nullable=true, length=256) 
    private String htmUrl;
    
    @PropertyDesc("背景图url")
    @Column(name="bg_image", nullable=true, length=256) 
    private String bgImage;
    
    @PropertyDesc("上传文件id")
    @Column(name="file_id", nullable=true, length=256) 
    private String fileId;
    
    @PropertyDesc("知识分类")
    @Column(name="knowledge_class", nullable=true, length=90) 
    private String knowledgeClass;
    

	public String getKnowledgeClass() {
		return knowledgeClass;
	}

	public void setKnowledgeClass(String knowledgeClass) {
		this.knowledgeClass = knowledgeClass;
	}

	public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    
    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
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