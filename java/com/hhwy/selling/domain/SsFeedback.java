package com.hhwy.selling.domain;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
/**
 * SsFeedback
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SsFeedback")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_s_feedback")
public class SsFeedback extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("售电公司名称")
    @Column(name="company", nullable=true, length=256) 
    private String company;
    
    @PropertyDesc("反馈用户名称")
    @Column(name="cons_feedback", nullable=true, length=256) 
    private String consFeedback;
    
    @PropertyDesc("用户反馈意见")
    @Column(name="cons_idea", nullable=true, length=1000) 
    private String consIdea;
    
    @PropertyDesc("用户反馈时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @Column(name="feedback_time", nullable=true, length=19) 
    private Timestamp feedbackTime;
    
    @PropertyDesc("用户满意程度0：非常满意，1：基本满意，2：一般，3：不满意，4：非常不满意")
    @Column(name="cons_satisfaction", nullable=true, length=3) 
    private String consSatisfaction;
    
    @PropertyDesc("管理员回复")
    @Column(name="admin_answer", nullable=true, length=2000) 
    private String adminAnswer;
    
    @PropertyDesc("是否解决0：否，1：是")
    @Column(name="solve", nullable=true, length=3) 
    private String solve;
    
    @PropertyDesc("附件")
    @Column(name="file_ids", nullable=true, length=2000) 
    private String fileIds;
    
    @PropertyDesc("数据库库名")
    @Column(name="url", nullable=true, length=1000) 
    private String url;
    
    
    public String getCompany(){
        return company;
    }
    
    public void setCompany(String company){
        this.company = company;
    }
    
    public String getConsFeedback(){
        return consFeedback;
    }
    
    public void setConsFeedback(String consFeedback){
        this.consFeedback = consFeedback;
    }
    
    public String getConsIdea(){
        return consIdea;
    }
    
    public void setConsIdea(String consIdea){
        this.consIdea = consIdea;
    }
    
    public Timestamp getFeedbackTime(){
        return feedbackTime;
    }
    
    public void setFeedbackTime(Timestamp feedbackTime){
        this.feedbackTime = feedbackTime;
    }
    
    public String getConsSatisfaction(){
        return consSatisfaction;
    }
    
    public void setConsSatisfaction(String consSatisfaction){
        this.consSatisfaction = consSatisfaction;
    }
    
    public String getAdminAnswer(){
        return adminAnswer;
    }
    
    public void setAdminAnswer(String adminAnswer){
        this.adminAnswer = adminAnswer;
    }
    
    public String getSolve(){
        return solve;
    }
    
    public void setSolve(String solve){
        this.solve = solve;
    }
    
    public String getFileIds(){
        return fileIds;
    }
    
    public void setFileIds(String fileIds){
        this.fileIds = fileIds;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}