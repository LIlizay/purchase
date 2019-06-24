package com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.domain.SmPurchaseSchemeDetailDetail;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.service.SmPurchaseSchemeDetailService;

/**
 * SmPurchaseSchemeDetailController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smPurchaseSchemeDetailController")
public class SmPurchaseSchemeDetailController {

	public static final Logger log = LoggerFactory.getLogger(SmPurchaseSchemeDetailController.class);
	
	/**
	 * smPurchaseSchemeDetailService
	 */
	@Autowired
	private SmPurchaseSchemeDetailService smPurchaseSchemeDetailService;
	
	
	/**
	 * @Title: getConsInfoByYmOrSchemeId
	 * @Description: 根据计划id或年月获取当月所有用户电量分配信息
	 * 				用户返回结算方案新增或编辑页面的用户月度电量分配表数据
	 * @param ym
	 * @param schemeId
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午5:01:43
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午5:01:43
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getConsInfoByYmOrSchemeId", method = RequestMethod.GET)
	public Object getConsInfoByYmOrSchemeId(String ym, String schemeId) {
		ExecutionResult result = new ExecutionResult();
		try {
			List<SmPurchaseSchemeDetailDetail> list = null;
			if(schemeId != null && !"".equals(schemeId.trim())){
				list=smPurchaseSchemeDetailService.getSchemeDetailBySchemeId(schemeId);
			}else if(ym != null && !"".equals(ym.trim())){
				list=smPurchaseSchemeDetailService.getSchemeDetailListByYm(ym);
			}
			result.setRows(list);
			result.setCode(ReturnCode.RES_SUCCESS);		
			result.setFlag(true);					
			result.setMsg("方案详情信息获取成功！");					
		} catch (Exception e) {
			log.error("方案详情信息获取失败",e);						
			result.setCode(ReturnCode.RES_FAILED);		
			result.setFlag(false);						
			result.setMsg("方案详情信息获取失败！");					
		}
		return result;
	}
}