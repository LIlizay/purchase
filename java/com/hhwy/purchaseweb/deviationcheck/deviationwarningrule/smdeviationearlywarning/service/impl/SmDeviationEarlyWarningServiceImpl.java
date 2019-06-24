package com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.domain.SmDeviationEarlyWarningDetail;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.domain.SmDeviationEarlyWarningVo;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.service.SmDeviationEarlyWarningService;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smwarninginfo.domain.SmWarningInfoVo;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smwarninginfo.service.SmWarningInfoService;
import com.hhwy.selling.domain.SmDeviationEarlyWarning;
import com.hhwy.selling.domain.SmWarningInfo;

/**
 * SmDeviationEarlyWarningService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmDeviationEarlyWarningServiceImpl implements SmDeviationEarlyWarningService {

	public static final Logger log = LoggerFactory.getLogger(SmDeviationEarlyWarningServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	@Autowired
	private SmWarningInfoService smWarningInfoService;
	
	/**
	 * 分页获取对象SmDeviationEarlyWarning
	 * @param ID
	 * @return SmDeviationEarlyWarning
	 */
	public QueryResult<SmDeviationEarlyWarningDetail> getSmDeviationEarlyWarningByPage(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo) throws Exception{
		QueryResult<SmDeviationEarlyWarningDetail> queryResult = new QueryResult<SmDeviationEarlyWarningDetail>();
		long total = getSmDeviationEarlyWarningCountByParams(smDeviationEarlyWarningVo);
		List<SmDeviationEarlyWarningDetail> smDeviationEarlyWarningList = getSmDeviationEarlyWarningListByParams(smDeviationEarlyWarningVo);
		queryResult.setTotal(total);
		queryResult.setData(smDeviationEarlyWarningList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmDeviationEarlyWarning数量
	 * @param SmDeviationEarlyWarningVo
	 * @return Integer
	 */
	public Integer getSmDeviationEarlyWarningCountByParams(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smDeviationEarlyWarning.sql.getSmDeviationEarlyWarningCountByParams",smDeviationEarlyWarningVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmDeviationEarlyWarning列表
	 * @param SmDeviationEarlyWarningVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmDeviationEarlyWarningDetail> getSmDeviationEarlyWarningListByParams(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smDeviationEarlyWarningVo == null){
			smDeviationEarlyWarningVo = new SmDeviationEarlyWarningVo();
		}
		Parameter.isFilterData.set(true);
		List<SmDeviationEarlyWarningDetail> smDeviationEarlyWarningList = (List<SmDeviationEarlyWarningDetail>)dao.getBySql("smDeviationEarlyWarning.sql.getSmDeviationEarlyWarningListByParams",smDeviationEarlyWarningVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smDeviationEarlyWarningList);
		return smDeviationEarlyWarningList;
	}
	/**
	 * 根据查询条件获取单个对象SmDeviationEarlyWarning
	 * @param SmDeviationEarlyWarningVo
	 * @return SmDeviationEarlyWarning
	 */
	public SmDeviationEarlyWarningDetail getSmDeviationEarlyWarningOneByParams(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo) throws Exception{
		SmDeviationEarlyWarningDetail smDeviationEarlyWarning = null;
		List<SmDeviationEarlyWarningDetail> smDeviationEarlyWarningList = getSmDeviationEarlyWarningListByParams(smDeviationEarlyWarningVo);
		if(smDeviationEarlyWarningList != null && smDeviationEarlyWarningList.size() > 0){
			smDeviationEarlyWarning = smDeviationEarlyWarningList.get(0);
		}
		return smDeviationEarlyWarning;
	}
	
	/**
	 * 根据ID获取对象SmDeviationEarlyWarning
	 * @param ID
	 * @return SmDeviationEarlyWarning
	 * @throws Exception 
	 */
	public SmDeviationEarlyWarningVo getSmDeviationEarlyWarningById(String id) throws Exception {
		SmDeviationEarlyWarningVo smDeviationEarlyWarningVo = new SmDeviationEarlyWarningVo();
		SmWarningInfoVo smWarningInfoVo = new SmWarningInfoVo();
		smWarningInfoVo.getSmWarningInfo().setRuleId(id);
		smWarningInfoVo.setPagingFlag(false);
		SmDeviationEarlyWarning smDeviationEarlyWarning = dao.findById(id, SmDeviationEarlyWarning.class);
		
		List<SmWarningInfo> list = smWarningInfoService.getSmWarningInfoListByParams(smWarningInfoVo);
		
		smDeviationEarlyWarningVo.setSmDeviationEarlyWarning(smDeviationEarlyWarning);
		smDeviationEarlyWarningVo.setSmWarningInfoList(list);
		return smDeviationEarlyWarningVo;
	}	
	
	/**
	 * 添加对象SmDeviationEarlyWarning
	 * @param 实体对象
	 */
	public void saveSmDeviationEarlyWarning(SmDeviationEarlyWarning smDeviationEarlyWarning) {
		dao.save(smDeviationEarlyWarning);
	}
	
	/**
	 * 添加对象SmDeviationEarlyWarning
	 * @param 实体对象
	 */
	public void saveSmDeviationEarlyWarningList(List<SmDeviationEarlyWarning> smDeviationEarlyWarningList) {
		dao.saveList(smDeviationEarlyWarningList);
	}
	
	/**
	 * 更新对象SmDeviationEarlyWarning
	 * @param 实体对象
	 */
	public void updateSmDeviationEarlyWarning(SmDeviationEarlyWarning smDeviationEarlyWarning) {
		dao.update(smDeviationEarlyWarning);
	}
	
	/**
	 * 删除对象SmDeviationEarlyWarning
	 * @param id数据组
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteSmDeviationEarlyWarning(String[] ids) {
		dao.delete(ids, SmDeviationEarlyWarning.class);
		dao.deleteBySql("smDeviationEarlyWarning.sql.deleteSmWarningInfo", ids);
	}	
	
	/**
	 * 
	 * @Title: saveDeviationRuleList<br/>
	 * @Description: 保存预警规则<br/>
	 * @param smDeviationEarlyWarningVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月5日 下午6:04:16
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月5日 下午6:04:16
	 * @since  1.0.0
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveDeviationRuleList(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo){
		String id = PlatformTools.getID();
		smDeviationEarlyWarningVo.getSmDeviationEarlyWarning().setId(id);
		List<SmWarningInfo> list = smDeviationEarlyWarningVo.getSmWarningInfoList();
		for (SmWarningInfo smWarningInfo : list) {
			smWarningInfo.setRuleId(id);
		}
		saveSmDeviationEarlyWarning(smDeviationEarlyWarningVo.getSmDeviationEarlyWarning());
		smWarningInfoService.saveSmWarningInfoList(list);
	}
	
	/**
	 * @Title: updateDeviationRuleList<br/>
	 * @Description: 更新预警规则<br/>
	 * @param smDeviationEarlyWarningVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月5日 下午9:40:44
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月5日 下午9:40:44
	 * @since  1.0.0
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateDeviationRuleList(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo){
		List<SmWarningInfo> addList = smDeviationEarlyWarningVo.getAddList();
		List<SmWarningInfo> updateList = smDeviationEarlyWarningVo.getUpdateList();
		String[] delIds = smDeviationEarlyWarningVo.getDelIds();
		for (SmWarningInfo smWarningInfo : addList) {
			smWarningInfo.setRuleId(smDeviationEarlyWarningVo.getSmDeviationEarlyWarning().getId());
		}
		updateSmDeviationEarlyWarning(smDeviationEarlyWarningVo.getSmDeviationEarlyWarning());
		smWarningInfoService.saveSmWarningInfoList(addList);
		for (SmWarningInfo smWarningInfo : updateList) {			
			smWarningInfoService.updateSmWarningInfo(smWarningInfo);
		}
		if(delIds.length>0){			
			smWarningInfoService.deleteSmWarningInfo(delIds);
		}
		
	}
	
	/**
	 * 
	 * @Title: getCountByStatus<br/>
	 * @Description: 查询规则状态<br/>
	 * @return <br/>
	 * Integer<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月5日 下午7:22:29
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月5日 下午7:22:29
	 * @since  1.0.0
	 */
	public Integer getCountByStatus(){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smDeviationEarlyWarning.sql.getCountByStatus", new Object());
		Parameter.isFilterData.set(false);
		int count = result == null ? 0 : Integer.valueOf(result.toString());
		return count;
	}
}