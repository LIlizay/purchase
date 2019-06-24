package com.hhwy.purchaseweb.ssfeedback.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SsFeedback;

/**
 * SsFeedbackVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SsFeedbackVo extends PagingProperty{

	private SsFeedback ssFeedback = BaseModel.getModel(SsFeedback.class);
	
	//用户起始反馈时间 YYYY-mm-dd
	private String feedbackTimeS;
	
	//用户截至反馈时间 YYYY-mm-dd
	private String feedbackTimeE;
	
	//满意程度
	private String consSatisfaction;
	
	//是否解决
	private String solve;
	
	//售电公司
	private String company;
	
	//导出用id
	private String id;
	
	//用于查看反馈记录权限过滤
	private String url;
	
	//用于多行导出
	String[] ids;
	
	public SsFeedback getSsFeedback(){
		return ssFeedback;
	}
	
	public void setSsFeedback(SsFeedback ssFeedback){
		this.ssFeedback = ssFeedback;
	}
	
	
	public String getFeedbackTimeS() {
		return feedbackTimeS;
	}

	public void setFeedbackTimeS(String feedbackTimeS) {
		this.feedbackTimeS = feedbackTimeS;
	}

	public String getFeedbackTimeE() {
		return feedbackTimeE;
	}

	public void setFeedbackTimeE(String feedbackTimeE) {
		this.feedbackTimeE = feedbackTimeE;
	}

	public String getConsSatisfaction() {
		return consSatisfaction;
	}

	public void setConsSatisfaction(String consSatisfaction) {
		this.consSatisfaction = consSatisfaction;
	}

	public String getSolve() {
		return solve;
	}

	public void setSolve(String solve) {
		this.solve = solve;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	
	
}