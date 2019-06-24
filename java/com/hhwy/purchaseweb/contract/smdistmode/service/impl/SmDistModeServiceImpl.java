package com.hhwy.purchaseweb.contract.smdistmode.service.impl;
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
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.purchaseweb.contract.smdistmode.domain.SmDistModeVo;
import com.hhwy.purchaseweb.contract.smdistmode.service.SmDistModeService;
import com.hhwy.selling.domain.SmDistMode;
;
;


/**
 * SmDistModeService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmDistModeServiceImpl implements SmDistModeService {

	public static final Logger log = LoggerFactory.getLogger(SmDistModeServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmDistMode
	 * @param ID
	 * @return SmDistMode
	 */
	public QueryResult<SmDistMode> getSmDistModeByPage(SmDistModeVo smDistModeVo) throws Exception{
		QueryResult<SmDistMode> queryResult = new QueryResult<SmDistMode>();
		long total = getSmDistModeCountByParams(smDistModeVo);
		List<SmDistMode> smDistModeList = getSmDistModeListByParams(smDistModeVo);
		queryResult.setTotal(total);
		queryResult.setData(smDistModeList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmDistMode数量
	 * @param SmDistModeVo
	 * @return Integer
	 */
	public Integer getSmDistModeCountByParams(SmDistModeVo smDistModeVo){
		
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smDistMode.sql.getSmDistModeCountByParams",smDistModeVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmDistMode列表
	 * @param SmDistModeVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmDistMode> getSmDistModeListByParams(SmDistModeVo smDistModeVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smDistModeVo == null){
			smDistModeVo = new SmDistModeVo();
		}
		Parameter.isFilterData.set(true);
		List<SmDistMode> smDistModeList = (List<SmDistMode>)dao.getBySql("smDistMode.sql.getSmDistModeListByParams",smDistModeVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smDistModeList);
		return smDistModeList;
	}
	/**
	 * 根据查询条件获取单个对象SmDistMode
	 * @param SmDistModeVo
	 * @return SmDistMode
	 */
	public SmDistMode getSmDistModeOneByParams(SmDistModeVo smDistModeVo) throws Exception{
		SmDistMode smDistMode = null;
		List<SmDistMode> smDistModeList = getSmDistModeListByParams(smDistModeVo);
		if(smDistModeList != null && smDistModeList.size() > 0){
			smDistMode = smDistModeList.get(0);
		}
		return smDistMode;
	}
	
	/**
	 * 根据ID获取对象SmDistMode
	 * @param ID
	 * @return SmDistMode
	 */
	public SmDistMode getSmDistModeById(String id) {
		return dao.findById(id, SmDistMode.class);
	}	
	
	/**
	 * 添加对象SmDistMode
	 * @param 实体对象
	 */
	public void saveSmDistMode(SmDistMode smDistMode) {
		smDistMode.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(smDistMode);
	}
	
	/**
	 * 添加对象SmDistMode
	 * @param 实体对象
	 */
	public void saveSmDistModeList(List<SmDistMode> smDistModeList) {
		for(SmDistMode smDistMode : smDistModeList){
			smDistMode.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(smDistModeList);
	}
	
	/**
	 * 更新对象SmDistMode
	 * @param 实体对象
	 */
	public void updateSmDistMode(SmDistMode smDistMode) {
		dao.update(smDistMode);
	}
	
	/**
	 * 删除对象SmDistMode
	 * @param id数据组
	 */
	public void deleteSmDistMode(String[] ids) {
		dao.delete(ids, SmDistMode.class);
	}	
	
	/**
	 * 删除对象SmAgreServ
	 * @param id数据组
	 */
	public void deleteSmDistModeByAgreIds(String[] agreId) {
		dao.deleteBySql("smDistMode.sql.deleteSmDistModeByAgreIds",agreId);
	}
	
	
	/**
	 * @Title: getSmDistModeOneByConsIdAndYm
	 * @Description: 根据用户id和年月获取其售电合同 分配方式信息,山西辽宁成交信息录入时计算结算电价时用到
	 * @param consId
	 * @param ym
	 * @return 
	 * SmDistMode
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年6月29日 下午5:03:58
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年6月29日 下午5:03:58
	 * @since  1.0.0
	 */
	public SmDistMode getSmDistModeOneByConsIdAndYm(String consId, String ym) {
		Map<String, String> params = new HashMap<>();
		params.put("consId", consId);
		params.put("ym", ym);
		SmDistMode mode = (SmDistMode)dao.getOneBySQL("smDistMode.sql.getSmDistModeOneByConsIdAndYm",params);
		if((mode == null || mode.getId() == null) && ym.length() == 6) {
			params.put("ym", ym.substring(0, 4));
			mode = (SmDistMode)dao.getOneBySQL("smDistMode.sql.getSmDistModeOneByConsIdAndYm",params);
		}
		return mode;
	}
}