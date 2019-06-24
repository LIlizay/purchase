package com.hhwy.purchaseweb.settlement.base.smcompanycostdetail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchase.domain.SmCompanyCostDetail;
import com.hhwy.purchaseweb.settlement.base.smcompanycostdetail.service.SmCompanyCostDetailService;

/**
 * SmCompanyCostDetailService	批发市场购电支出明细service
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmCompanyCostDetailServiceImpl implements SmCompanyCostDetailService {

	public static final Logger log = LoggerFactory.getLogger(SmCompanyCostDetailServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * @Title: getCostDetailListBySchemeId
	 * @Description: 通过方案id获取所有 批发市场购电支出明细
	 * @param schemeId
	 * @return
	 * @throws Exception 
	 * List<SmCompanyCostDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月29日 上午11:18:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月29日 上午11:18:00
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SmCompanyCostDetail> getCostDetailListBySchemeId(String schemeId) throws Exception{
		if(schemeId == null){
			return null;
		}
		Parameter.isFilterData.set(true);
		List<SmCompanyCostDetail> smCompanyCostDetailList = (List<SmCompanyCostDetail>)dao.getBySql("smCompanyCostDetail.sql.getCostDetailListBySchemeId",schemeId);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smCompanyCostDetailList);
		return smCompanyCostDetailList;
	}
	
	/**
	 * 添加对象SmCompanyCostDetail
	 * @param 实体对象
	 */
	public void saveSmCompanyCostDetailList(List<SmCompanyCostDetail> smCompanyCostDetailList) {
		dao.saveList(smCompanyCostDetailList);
	}
	
	/**
	 * @Title: deleteSmCompanyCostDetailBySchemeId
	 * @Description: 根据月度购电方案id 删除‘批发市场购电支出明细’信息
	 * @param schemeIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月30日 下午4:47:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月30日 下午4:47:36
	 * @since  1.0.0
	 */
	public void deleteSmCompanyCostDetailBySchemeId(String[] schemeIds) {
		Map<String,String[]> map=new HashMap<String,String[]>();
		map.put("schemeIds", schemeIds);
		dao.deleteBySql("smCompanyCostDetail.sql.deleteSmCompanyCostDetailBySchemeId", map);
	}
}