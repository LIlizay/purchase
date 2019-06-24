package com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.service.impl;

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
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.domain.SmConsEarlyWarningDetail;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.domain.SmConsEarlyWarningVo;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.service.SmConsEarlyWarningService;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconswarninginfo.domain.SmConsWarningInfoVo;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconswarninginfo.service.SmConsWarningInfoService;
import com.hhwy.selling.domain.SmConsEarlyWarning;
import com.hhwy.selling.domain.SmConsWarningInfo;

/**
 * SmConsEarlyWarningService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmConsEarlyWarningServiceImpl implements SmConsEarlyWarningService {

	public static final Logger log = LoggerFactory.getLogger(SmConsEarlyWarningServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	@Autowired
	private SmConsWarningInfoService smConsWarningInfoService;
	
	/**
	 * 分页获取对象SmConsEarlyWarning
	 * @param ID
	 * @return SmConsEarlyWarning
	 */
	public QueryResult<SmConsEarlyWarningDetail> getSmConsEarlyWarningByPage(SmConsEarlyWarningVo smConsEarlyWarningVo) throws Exception{
		QueryResult<SmConsEarlyWarningDetail> queryResult = new QueryResult<SmConsEarlyWarningDetail>();
		long total = getSmConsEarlyWarningCountByParams(smConsEarlyWarningVo);
		List<SmConsEarlyWarningDetail> smConsEarlyWarningList = getSmConsEarlyWarningListByParams(smConsEarlyWarningVo);
		queryResult.setTotal(total);
		queryResult.setData(smConsEarlyWarningList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmConsEarlyWarning数量
	 * @param SmConsEarlyWarningVo
	 * @return Integer
	 */
	public Integer getSmConsEarlyWarningCountByParams(SmConsEarlyWarningVo smConsEarlyWarningVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smConsEarlyWarning.sql.getSmConsEarlyWarningCountByParams",smConsEarlyWarningVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmConsEarlyWarning列表
	 * @param SmConsEarlyWarningVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmConsEarlyWarningDetail> getSmConsEarlyWarningListByParams(SmConsEarlyWarningVo smConsEarlyWarningVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smConsEarlyWarningVo == null){
			smConsEarlyWarningVo = new SmConsEarlyWarningVo();
		}
		Parameter.isFilterData.set(true);
		List<SmConsEarlyWarningDetail> smConsEarlyWarningList = (List<SmConsEarlyWarningDetail>)dao.getBySql("smConsEarlyWarning.sql.getSmConsEarlyWarningListByParams",smConsEarlyWarningVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smConsEarlyWarningList);
		return smConsEarlyWarningList;
	}
	/**
	 * 根据查询条件获取单个对象SmConsEarlyWarning
	 * @param SmConsEarlyWarningVo
	 * @return SmConsEarlyWarning
	 */
	public SmConsEarlyWarningDetail getSmConsEarlyWarningOneByParams(SmConsEarlyWarningVo smConsEarlyWarningVo) throws Exception{
		SmConsEarlyWarningDetail smConsEarlyWarning = null;
		List<SmConsEarlyWarningDetail> smConsEarlyWarningList = getSmConsEarlyWarningListByParams(smConsEarlyWarningVo);
		if(smConsEarlyWarningList != null && smConsEarlyWarningList.size() > 0){
			smConsEarlyWarning = smConsEarlyWarningList.get(0);
		}
		return smConsEarlyWarning;
	}
	
	/**
	 * 
	 * @Title: getConsEarlyWarningByConsId<br/>
	 * @Description: 判断用户是否已经存在规则<br/>
	 * @param consId
	 * @return
	 * @throws Exception <br/>
	 * SmConsEarlyWarningVo<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年9月22日 下午3:16:13
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年9月22日 下午3:16:13
	 * @since  1.0.0
	 */
	public SmConsEarlyWarningVo getConsEarlyWarningByConsId(String consId) throws Exception{
		if(consId == null || consId.equals("")){
			throw new Exception("用户id为空");
		}
		SmConsEarlyWarningVo smConsEarlyWarningVo = new SmConsEarlyWarningVo();
		smConsEarlyWarningVo.getSmConsEarlyWarning().setConsId(consId);
		SmConsEarlyWarningDetail smConsEarlyWarningDetail = getSmConsEarlyWarningOneByParams(smConsEarlyWarningVo);
		if(smConsEarlyWarningDetail == null){
			return null;
		}
		SmConsEarlyWarningVo resultSmConsEarlyWarningVo = getSmConsEarlyWarningById(smConsEarlyWarningDetail.getId());
		return resultSmConsEarlyWarningVo;
	}
	
	/**
	 * 根据ID获取对象SmConsEarlyWarning
	 * @param ID
	 * @return SmConsEarlyWarning
	 * @throws Exception 
	 */
	public SmConsEarlyWarningVo getSmConsEarlyWarningById(String id) throws Exception {
		SmConsEarlyWarningVo smConsEarlyWarningVo = new SmConsEarlyWarningVo();
		SmConsWarningInfoVo smConsWarningInfoVo = new SmConsWarningInfoVo();
		smConsWarningInfoVo.getSmConsWarningInfo().setRuleId(id);
		smConsWarningInfoVo.setPagingFlag(false);
		List<SmConsWarningInfo> list = smConsWarningInfoService.getSmConsWarningInfoListByParams(smConsWarningInfoVo);
		SmConsEarlyWarning smConsEarlyWarning = dao.findById(id, SmConsEarlyWarning.class);
		
		smConsEarlyWarningVo.setSmConsEarlyWarning(smConsEarlyWarning);
		smConsEarlyWarningVo.setSmConsWarningInfoList(list);
		
		return smConsEarlyWarningVo;
	}	
	
	/**
	 * 添加对象SmConsEarlyWarning
	 * @param 实体对象
	 */
	public void saveSmConsEarlyWarning(SmConsEarlyWarning smConsEarlyWarning) {
		dao.save(smConsEarlyWarning);
	}
	
	/**
	 * 添加对象SmConsEarlyWarning
	 * @param 实体对象
	 */
	public void saveSmConsEarlyWarningList(List<SmConsEarlyWarning> smConsEarlyWarningList) {
		dao.saveList(smConsEarlyWarningList);
	}
	
	/**
	 * 更新对象SmConsEarlyWarning
	 * @param 实体对象
	 */
	public void updateSmConsEarlyWarning(SmConsEarlyWarning smConsEarlyWarning) {
		dao.update(smConsEarlyWarning);
	}
	
	/**
	 * 删除对象SmConsEarlyWarning
	 * @param id数据组
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteSmConsEarlyWarning(String[] ids) {
		dao.delete(ids, SmConsEarlyWarning.class);
		dao.deleteBySql("smConsEarlyWarning.sql.deleteSmConsWarningInfo", ids);
	}	
	
	/**
	 * 
	 * @Title: saveConsRule<br/>
	 * @Description: 保存用户规则<br/>
	 * @param smConsEarlyWarningVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月7日 下午5:16:31
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月7日 下午5:16:31
	 * @since  1.0.0
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveConsRule(SmConsEarlyWarningVo smConsEarlyWarningVo){
		String id = PlatformTools.getID();
		smConsEarlyWarningVo.getSmConsEarlyWarning().setId(id);
		List<SmConsWarningInfo> list = smConsEarlyWarningVo.getSmConsWarningInfoList();
		for (SmConsWarningInfo smConsWarningInfo : list) {
			smConsWarningInfo.setRuleId(id);
		}
		saveSmConsEarlyWarning(smConsEarlyWarningVo.getSmConsEarlyWarning());
		smConsWarningInfoService.saveSmConsWarningInfoList(list);
	}
	
	/**
	 * 
	 * @Title: updateConsRule<br/>
	 * @Description: 更新用户规则<br/>
	 * @param smConsEarlyWarningVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月7日 下午5:21:43
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月7日 下午5:21:43
	 * @since  1.0.0
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateConsRule(SmConsEarlyWarningVo smConsEarlyWarningVo){
	    updateSmConsEarlyWarning(smConsEarlyWarningVo.getSmConsEarlyWarning());
		List<SmConsWarningInfo> addList = smConsEarlyWarningVo.getAddList();
		for (SmConsWarningInfo smConsWarningInfo : addList) {
			smConsWarningInfo.setRuleId(smConsEarlyWarningVo.getSmConsEarlyWarning().getId());
		}
		smConsWarningInfoService.saveSmConsWarningInfoList(addList);
		List<SmConsWarningInfo> updateList = smConsEarlyWarningVo.getUpdateList();
		for (SmConsWarningInfo smConsWarningInfo : updateList) {
			smConsWarningInfoService.updateSmConsWarningInfo(smConsWarningInfo);
		}
		String[] delIds = smConsEarlyWarningVo.getDelIds();
		if(delIds.length > 0){
			smConsWarningInfoService.deleteSmConsWarningInfo(delIds);
		}
	}
	
	/**
	 * @Title: getDefaultRule<br/>
	 * @Description: 查询默认规则<br/>
	 * @return
	 * @throws Exception <br/>
	 * SmConsEarlyWarningVo<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月31日 下午4:33:03
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月31日 下午4:33:03
	 * @since  1.0.0
	 */
	public SmConsEarlyWarningVo getDefaultRule() throws Exception{
		SmConsEarlyWarningVo smConsEarlyWarningVo = new SmConsEarlyWarningVo();
		smConsEarlyWarningVo.getSmConsEarlyWarning().setRuleFlag("1");;
		SmConsEarlyWarningDetail smConsEarlyWarningDetail = getSmConsEarlyWarningOneByParams(smConsEarlyWarningVo);
		if(smConsEarlyWarningDetail == null){
			return null;
		}
		SmConsEarlyWarningVo resultSmConsEarlyWarningVo = getSmConsEarlyWarningById(smConsEarlyWarningDetail.getId());
		return resultSmConsEarlyWarningVo;
	}
}