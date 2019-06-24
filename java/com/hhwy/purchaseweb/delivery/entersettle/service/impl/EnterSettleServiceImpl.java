package com.hhwy.purchaseweb.delivery.entersettle.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmConsSettleRela;
import com.hhwy.purchase.domain.PhmEnterSettle;
import com.hhwy.purchaseweb.delivery.entersettle.domain.EnterSettleDetail;
import com.hhwy.purchaseweb.delivery.entersettle.domain.EnterSettleVo;
import com.hhwy.purchaseweb.delivery.entersettle.domain.SettleDetailDetail;
import com.hhwy.purchaseweb.delivery.entersettle.service.EnterSettleService;

@Component
public class EnterSettleServiceImpl implements EnterSettleService{
	
	public static final Logger log = LoggerFactory.getLogger(EnterSettleServiceImpl.class);
	
	 @Autowired
	 DAO<?> dao;
	 
	 public void setDao(DAO<?> dao) {
		 this.dao = dao;
	 }

	/**
	  * @Title: getEnterSettleByPage
	  * @Description: 分页获取录入结算信息
	  * @param enterSettleVo
	  * @return QueryResult<EnterSettleDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月15日 上午10:38:52
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月15日 上午10:38:52
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@Override
	public QueryResult<EnterSettleDetail> getEnterSettleByPage(EnterSettleVo enterSettleVo) throws Exception {
		QueryResult<EnterSettleDetail> queryResult = new QueryResult<EnterSettleDetail>();
		long total = getEnterSettleCountByParams(enterSettleVo);
		List<EnterSettleDetail> enterSettleDetailList = getEnterSettleListByParams(enterSettleVo);
		queryResult.setTotal(total);
		queryResult.setData(enterSettleDetailList);
		return queryResult;
	}

	/**
	  * @Title: getEnterSettleListByParams
	  * @Description: 根据查询条件获取录入结算信息
	  * @param enterSettleVo
	  * @return List<EnterSettleDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月15日 上午11:18:59
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月15日 上午11:18:59
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private List<EnterSettleDetail> getEnterSettleListByParams(EnterSettleVo enterSettleVo) throws Exception {
		if(enterSettleVo == null){
			enterSettleVo = new EnterSettleVo();
		}
		Parameter.isFilterData.set(true);
		List<EnterSettleDetail> list = (List<EnterSettleDetail>)dao.getBySql("entersettle.sql.getEnterSettleList",enterSettleVo);
        Parameter.isFilterData.set(false);
        return list;
	}

	/**
	  * @Title: getEnterSettleCountByParams
	  * @Description: 查询录入结算数量
	  * @param enterSettleVo
	  * @return long
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月15日 上午10:57:38
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月15日 上午10:57:38
	  * @since  1.0.0
	 */
	private long getEnterSettleCountByParams(EnterSettleVo enterSettleVo) {
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("entersettle.sql.getEnterSettleCount",enterSettleVo);
        Parameter.isFilterData.set(false);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
	}

	/**
	  * @Title: getSettleDetailDetail
	  * @Description: 根据结算id获取用户结算详情
	  * @param id
	  * @return List<SettleDetailDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月15日 下午4:37:56
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月15日 下午4:37:56
	  * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SettleDetailDetail> getSettleDetailDetail(EnterSettleVo enterSettleVo) {
		List<SettleDetailDetail> list = (List<SettleDetailDetail>)dao.getBySql("entersettle.sql.getEnterSettleDetailList",enterSettleVo);
		return list;
	}

	/**
	  * @Title: checkRepeatByMonth
	  * @Description: 购电年月判重
	  * @param ym
	  * @return boolean
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月15日 下午6:30:45
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月15日 下午6:30:45
	  * @since  1.0.0
	 */
	@Override
	public boolean checkRepeatByMonth(String ym) {
		PhmEnterSettle settle = (PhmEnterSettle) dao.getOneBySQL("entersettle.sql.getPhmEnterSettleByYm", ym);
		if(settle != null){
			return false;
		}
		return true;
	}

	/**
	  * @Title: getEnterSettleDetailByYm
	  * @Description: 根据年月获取合同用户信息
	  * @param ym
	  * @return List<SettleDetailDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月16日 上午9:34:48
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月16日 上午9:34:48
	  * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SettleDetailDetail> getEnterSettleDetailByYm(String ym) {
		List<SettleDetailDetail> list = (List<SettleDetailDetail>) dao.getBySql("entersettle.sql.getSettleDetailByYm", ym);
		return list;
	}

	/**
	  * @Title: getPurchasePqByYm
	  * @Description: 根据年月获取该月总购电量（当月购电合同和竞价电量之和）
	  * @param ym
	  * @return Map<String,Object>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月16日 上午10:49:48
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月16日 上午10:49:48
	  * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getPurchasePqByYm(String ym) {
		Map<String,Object> map = (Map<String, Object>) dao.getOneBySQL("entersettle.sql.getPurchasePqByYm", ym);
		return map;
	}

	/**
	  * @Title: saveOrUpdateSettle
	  * @Description: 新增或更新结算数据
	  * @param enterSettleVo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月16日 下午5:06:51
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月16日 下午5:06:51
	  * @since  1.0.0
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveOrUpdateSettle(EnterSettleVo enterSettleVo) {
		PhmEnterSettle enterSettle = enterSettleVo.getPhmEnterSettle();
		String ym = enterSettle.getYm().replace("-", "");
		enterSettle.setYm(ym);
		List<PhmConsSettleRela> relaList = enterSettleVo.getPhmConsSettleRelaList();
		dao.save(enterSettle);
		for(PhmConsSettleRela rela : relaList){
			rela.setSettleId(enterSettle.getId());
		}
		dao.saveList(relaList);
	}

	/**
	  * @Title: deleteSettle
	  * @Description: 根据ids删除结算数据
	  * @param ids
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月17日 下午1:55:51
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月17日 下午1:55:51
	  * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteSettle(String[] ids) {
		List<PhmConsSettleRela> relaList = (List<PhmConsSettleRela>) dao.getBySql("entersettle.sql.getConsSettleRelaListByIds", ids);
		dao.deleteList(relaList);
		dao.delete(ids, PhmEnterSettle.class);
	}
}
