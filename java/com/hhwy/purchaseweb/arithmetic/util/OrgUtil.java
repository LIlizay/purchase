package com.hhwy.purchaseweb.arithmetic.util;

import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.util.StringUtils;

public class OrgUtil {
	
	/**
	 * 
	 * @Title: getOrgNoByUser
	 * @Description: 获取当前登陆人的组织机构，部门不为空用部门（省份下部门），部门为空用售电公司（相当于省份）
	 * @return 
	 * String
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年5月4日 下午8:33:48
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年5月4日 下午8:33:48
	 * @since  1.0.0
	 */
	public static String getOrgNoByUser(){
		String orgIdBm = SystemServiceUtil.getLoginUserInfo().getOrgIdBm();
		String orgId = SystemServiceUtil.getLoginUserInfo().getOrgId();
		if(StringUtils.isNull(orgIdBm)){
			return orgId;
		}else{
			return orgIdBm;
		}
	}

}
