package com.hhwy.purchaseweb.delivery.smfundprcinfo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.delivery.smfundprcinfo.domain.SmFundPrcInfoDetail;
import com.hhwy.purchaseweb.delivery.smfundprcinfo.domain.SmFundPrcInfoVo;
import com.hhwy.purchaseweb.delivery.smfundprcinfo.service.SmFundPrcInfoService;
import com.hhwy.selling.domain.SmFundPrcInfo;

/**
 * SmFundPrcInfoService
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmFundPrcInfoServiceImpl implements SmFundPrcInfoService {

	public static final Logger log = LoggerFactory
			.getLogger(SmFundPrcInfoServiceImpl.class);

	@Autowired
	DAO<?> dao;

	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

	/**
	 * 分页获取对象SmFundPrcInfo
	 * 
	 * @param ID
	 * @return SmFundPrcInfo
	 */
	public QueryResult<SmFundPrcInfoDetail> getSmFundPrcInfoByPage(
			SmFundPrcInfoVo smFundPrcInfoVo) throws Exception {
		QueryResult<SmFundPrcInfoDetail> queryResult = new QueryResult<SmFundPrcInfoDetail>();
		long total = getSmFundPrcInfoCountByParams(smFundPrcInfoVo);
		List<SmFundPrcInfoDetail> smFundPrcInfoList = getSmFundPrcInfoListByParams(smFundPrcInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(smFundPrcInfoList);
		return queryResult;
	}

	/**
	 * 根据查询条件获取对象SmFundPrcInfo数量
	 * 
	 * @param SmFundPrcInfoVo
	 * @return Integer
	 */
	public Integer getSmFundPrcInfoCountByParams(SmFundPrcInfoVo smFundPrcInfoVo) {
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL(
				"smFundPrcInfo.sql.getSmFundPrcInfoCountByParams",
				smFundPrcInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	/**
	 * 根据查询条件获取对象SmFundPrcInfo列表
	 * 
	 * @param SmFundPrcInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmFundPrcInfoDetail> getSmFundPrcInfoListByParams(
			SmFundPrcInfoVo smFundPrcInfoVo) throws Exception {
		// 当Vo为空时,初始化Vo对象,应用分页参数
		if (smFundPrcInfoVo == null) {
			smFundPrcInfoVo = new SmFundPrcInfoVo();
		}
		Parameter.isFilterData.set(true);
		List<SmFundPrcInfoDetail> smFundPrcInfoList = (List<SmFundPrcInfoDetail>) dao
				.getBySql("smFundPrcInfo.sql.getSmFundPrcInfoListByParams",
						smFundPrcInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smFundPrcInfoList);
		return smFundPrcInfoList;
	}

	/**
	 * 根据查询条件获取单个对象SmFundPrcInfo
	 * 
	 * @param SmFundPrcInfoVo
	 * @return SmFundPrcInfo
	 */
	public SmFundPrcInfoDetail getSmFundPrcInfoOneByParams(
			SmFundPrcInfoVo smFundPrcInfoVo) throws Exception {
		SmFundPrcInfoDetail smFundPrcInfo = null;
		List<SmFundPrcInfoDetail> smFundPrcInfoList = getSmFundPrcInfoListByParams(smFundPrcInfoVo);
		if (smFundPrcInfoList != null && smFundPrcInfoList.size() > 0) {
			smFundPrcInfo = smFundPrcInfoList.get(0);
		}
		return smFundPrcInfo;
	}

	/**
	 * 根据ID获取对象SmFundPrcInfo
	 * 
	 * @param ID
	 * @return SmFundPrcInfo
	 */
	public SmFundPrcInfo getSmFundPrcInfoById(String id) {
		return dao.findById(id, SmFundPrcInfo.class);
	}

	/**
	 * 添加对象SmFundPrcInfo
	 * 
	 * @param 实体对象
	 */
	public void saveSmFundPrcInfo(SmFundPrcInfo smFundPrcInfo) {
		dao.save(smFundPrcInfo);
	}

	/**
	 * 添加对象SmFundPrcInfo
	 * 
	 * @param 实体对象
	 */
	@Transactional
	public void saveSmFundPrcInfoList(List<SmFundPrcInfo> smFundPrcInfoList) {
		if(smFundPrcInfoList == null || smFundPrcInfoList.size() == 0) {
			return;
		}
		Set<String> prcIds = new HashSet<String>();
		for (SmFundPrcInfo smFundPrcInfo : smFundPrcInfoList) {
			prcIds.add(smFundPrcInfo.getPrcId());
		}
		String[] prcIdArray = new String[prcIds.size()];
		this.deleteSmFundPrcInfoByPrcIds(prcIds.toArray(prcIdArray));
		dao.saveList(smFundPrcInfoList);
	}

	/**
	 * 更新对象SmFundPrcInfo
	 * 
	 * @param 实体对象
	 */
	public void updateSmFundPrcInfo(SmFundPrcInfo smFundPrcInfo) {
		dao.update(smFundPrcInfo);
	}

	/**
	 * 删除对象SmFundPrcInfo
	 * 
	 * @param id数据组
	 */
	public void deleteSmFundPrcInfo(String[] ids) {
		dao.delete(ids, SmFundPrcInfo.class);
	}
	
	/**
	 * @Title: deleteSmFundPrcInfoByPrcIds
	 * @Description: 根据电价信息（SmPrcInfo）id数组删除对象SmFundPrcInfo
	 * @param prcIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年2月27日 下午3:10:10
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年2月27日 下午3:10:10
	 * @since  1.0.0
	 */
	public void deleteSmFundPrcInfoByPrcIds(String[] prcIds) {
		dao.deleteBySql("smFundPrcInfo.sql.deletePhmAgreDeviationByAgreIds", prcIds);
	}
}