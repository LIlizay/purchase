package com.hhwy.purchaseweb.ssfeedback.domain;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class SsFeedbackDetail {

		private String id;
		
		//售电公司名称
	    private String company;
	    
	    //反馈用户名称
	    private String consFeedback;
	    
	    //用户反馈意见
	    private String consIdea;
	    
	    //用户反馈时间
	    private String feedbackTime;
	    
	    //用户满意程度0：非常满意，1：基本满意，2：一般，3：不满意，4：非常不满意
	    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_satisfaction" ,field="consSatisfactionName")
	    private String consSatisfaction;
	    private String consSatisfactionName;
	    
	    //管理员回复
	    private String adminAnswer;
	    
	    //是否解决0：否，1：是
	    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_flag" ,field="solveName")
	    private String solve;
	    private String solveName;
	    
	    //附件
	    private String fileIds;
	    
	    
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getConsSatisfactionName() {
			return consSatisfactionName;
		}

		public void setConsSatisfactionName(String consSatisfactionName) {
			this.consSatisfactionName = consSatisfactionName;
		}

		public String getSolveName() {
			return solveName;
		}

		public void setSolveName(String solveName) {
			this.solveName = solveName;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getConsFeedback() {
			return consFeedback;
		}

		public void setConsFeedback(String consFeedback) {
			this.consFeedback = consFeedback;
		}

		public String getConsIdea() {
			return consIdea;
		}

		public void setConsIdea(String consIdea) {
			this.consIdea = consIdea;
		}

		public String getFeedbackTime() {
			return feedbackTime;
		}

		public void setFeedbackTime(String feedbackTime) {
			this.feedbackTime = feedbackTime;
		}

		public String getConsSatisfaction() {
			return consSatisfaction;
		}

		public void setConsSatisfaction(String consSatisfaction) {
			this.consSatisfaction = consSatisfaction;
		}

		public String getAdminAnswer() {
			return adminAnswer;
		}

		public void setAdminAnswer(String adminAnswer) {
			this.adminAnswer = adminAnswer;
		}

		public String getSolve() {
			return solve;
		}

		public void setSolve(String solve) {
			this.solve = solve;
		}

		public String getFileIds() {
			return fileIds;
		}

		public void setFileIds(String fileIds) {
			this.fileIds = fileIds;
		}
	    
	
}
