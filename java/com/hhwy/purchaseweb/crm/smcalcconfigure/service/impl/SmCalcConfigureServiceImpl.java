package com.hhwy.purchaseweb.crm.smcalcconfigure.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.contants.RuleConfigureContants;
import com.hhwy.purchaseweb.crm.smcalcconfigure.service.SmCalcConfigureService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.SmCalcConfigure;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureDetail;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureVo;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SmCalcConfigureService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmCalcConfigureServiceImpl implements SmCalcConfigureService {

	public static final Logger log = LoggerFactory.getLogger(SmCalcConfigureServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmCalcConfigure
	 * @param ID
	 * @return SmCalcConfigure
	 */
	public QueryResult<SmCalcConfigureDetail> getSmCalcConfigureByPage(SmCalcConfigureVo smCalcConfigureVo) throws Exception{
		QueryResult<SmCalcConfigureDetail> queryResult = new QueryResult<SmCalcConfigureDetail>();
		long total = getSmCalcConfigureCountByParams(smCalcConfigureVo);
		List<SmCalcConfigureDetail> smCalcConfigureList = getSmCalcConfigureListByParams(smCalcConfigureVo);
		queryResult.setTotal(total);
		queryResult.setData(smCalcConfigureList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmCalcConfigure数量
	 * @param SmCalcConfigureVo
	 * @return Integer
	 */
	public Integer getSmCalcConfigureCountByParams(SmCalcConfigureVo smCalcConfigureVo){
		Object result = dao.getOneBySQL("smCalcConfigure.sql.getSmCalcConfigureCountByParams",smCalcConfigureVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmCalcConfigure列表
	 * @param SmCalcConfigureVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmCalcConfigureDetail> getSmCalcConfigureListByParams(SmCalcConfigureVo smCalcConfigureVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smCalcConfigureVo == null){
			smCalcConfigureVo = new SmCalcConfigureVo();
		}
		List<SmCalcConfigureDetail> smCalcConfigureList = (List<SmCalcConfigureDetail>)dao.getBySql("smCalcConfigure.sql.getSmCalcConfigureListByParams",smCalcConfigureVo);
		ConvertListUtil.convert(smCalcConfigureList);
		return smCalcConfigureList;
	}
	/**
	 * 根据查询条件获取单个对象SmCalcConfigure
	 * @param SmCalcConfigureVo
	 * @return SmCalcConfigure
	 */
	public SmCalcConfigureDetail getSmCalcConfigureOneByParams(SmCalcConfigureVo smCalcConfigureVo) throws Exception{
		SmCalcConfigureDetail smCalcConfigure = null;
		List<SmCalcConfigureDetail> smCalcConfigureList = getSmCalcConfigureListByParams(smCalcConfigureVo);
		if(smCalcConfigureList != null && smCalcConfigureList.size() > 0){
			smCalcConfigure = smCalcConfigureList.get(0);
		}
		return smCalcConfigure;
	}
	
	/**
	 * 根据ID获取对象SmCalcConfigure
	 * @param ID
	 * @return SmCalcConfigure
	 */
	public SmCalcConfigure getSmCalcConfigureById(String id) {
		return dao.findById(id, SmCalcConfigure.class);
	}	
	
	/**
	 * 添加对象SmCalcConfigure
	 * @param 实体对象
	 */
	public void saveSmCalcConfigure(SmCalcConfigure smCalcConfigure) {
		smCalcConfigure.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(smCalcConfigure);
	}
	
	/**
	 * 添加对象SmCalcConfigure
	 * @param 实体对象
	 */
	public void saveSmCalcConfigureList(List<SmCalcConfigure> smCalcConfigureList) {
		for (SmCalcConfigure smCalcConfigure : smCalcConfigureList) {
			smCalcConfigure.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(smCalcConfigureList);
	}
	
	/**
	 * 更新对象SmCalcConfigure
	 * @param 实体对象
	 */
	public void updateSmCalcConfigure(SmCalcConfigure smCalcConfigure) {
		dao.update(smCalcConfigure);
	}
	
	/**
	 * 删除对象SmCalcConfigure
	 * @param id数据组
	 */
	public void deleteSmCalcConfigure(String[] ids) {
		dao.delete(ids, SmCalcConfigure.class);
	}	
	
	/**
	 * @Title: getRuleValueList<br/>
	 * @Description: 查询规则下拉框数据 <br/>
	 * @return <br/>
	 * List<Map<String,Object>><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月16日 下午3:43:37
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月16日 下午3:43:37
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRuleValueList(){
		List<Map<String, Object>> list = (List<Map<String, Object>>) dao.getBySql("smCalcConfigure.sql.getRuleValueList", new HashMap<String, Object>());
		return list;
	}
	
	/**
	 * @Title: getSellDbList<br/>
	 * @Description: 查询售电公司库名<br/>
	 * @return <br/>
	 * List<Map<String,Object>><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月16日 下午4:08:52
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月16日 下午4:08:52
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSellDbList(){
		List<Map<String, Object>> list = (List<Map<String, Object>>) dao.getBySql("smCalcConfigure.sql.getSellDbList", new HashMap<String, Object>());
		return list;
	}
	
	/**
	 * @Title: getRuleParamsList<br/>
	 * @Description: 查询规则参数列表<br/>
	 * @param ruleId
	 * @return <br/>
	 * List<Map<String,Object>><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月16日 下午4:23:16
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月16日 下午4:23:16
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRuleParamsList(String ruleCode){
		if(ruleCode == null || ruleCode.equals("")){
			throw new IllegalArgumentException("规则id为空！");
		}
		List<Map<String, Object>> list = (List<Map<String, Object>>) dao.getBySql("smCalcConfigure.sql.getRuleParamsList", ruleCode);
		return list;
	}
	
	/**
	 * @Title: getTree<br/>
	 * @Description: 查询树<br/>
	 * @param nodeType
	 * @param provinceId
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月17日 上午10:49:20
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月17日 上午10:49:20
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Object getTree(String nodeType, String provinceId) {
		if ("root".equals(nodeType)) {
			Map<String, Object> root = new HashMap<>();
			root.put("text", "省份");
			root.put("nodeType", "root");
			root.put("state", "open");
			root.put("children",dao.getBySql("smCalcConfigure.sql.getProTree", ""));
			List<Map<String, Object>> list = new ArrayList<>();
			list.add(root);
			return list;
		} else if ("province".equals(nodeType)) {
			if (StringUtils.isBlank(provinceId)) {
				return new Object[] {};
			}
			List<Map<String, Object>> list = new ArrayList<>();

			Map<String, Object> map = new HashMap<>();
		/*	map.put("text", "目录电价");
			map.put("nodeType", "catePrc");
			map.put("state", "open");
			map.put("id", "catPrc");
			list.add(map);
			map = new HashMap<>();
			map.put("text", "输配电价");
			map.put("nodeType", "transPrc");
			map.put("state", "open");
			map.put("id", "transPrc");
			list.add(map);*/
			map.put("text", "电价管理");
			map.put("nodeType", "managePrice");
			map.put("state", "open");
			map.put("id", "managePrice");
			list.add(map);
			list.addAll((List<Map<String, Object>>) dao.getBySql("smCalcConfigure.sql.getVersion", provinceId));
			return list;
		} else {
			return new Object[] {};
		}
	}
	
	/**
	 * @Title: effect<br/>
	 * @Description: 设置生效状态<br/>
	 * @param provinceId
	 * @param id
	 * @return
	 * @throws Exception <br/>
	 * ExecutionResult<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月17日 上午11:01:02
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月17日 上午11:01:02
	 * @since  1.0.0
	 */
	public ExecutionResult effect(String provinceId, String id) throws Exception {
		ExecutionResult result = new ExecutionResult();
		SmCalcConfigureVo smCalcConfigureVo = new SmCalcConfigureVo();
		smCalcConfigureVo.getSmCalcConfigure().setProvince(provinceId);
		smCalcConfigureVo.getSmCalcConfigure().setStatus(RuleConfigureContants.CONTRACT_RULE_EFFECT);
		int count = getSmCalcConfigureCountByParams(smCalcConfigureVo);
		if (count > 0) {
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setFlag(false); // 设置是否成功标识
			result.setMsg("存在已经生效的规则，请先失效该规则后再生效"); // 设置返回消息，根据实际情况修改
			return result;
		}
		SmCalcConfigure smCalcConfigure = new SmCalcConfigure();
		smCalcConfigure.setId(id);
		smCalcConfigure.setStatus(RuleConfigureContants.CONTRACT_RULE_EFFECT);
		smCalcConfigure.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.saveExcludeNull(smCalcConfigure);

		result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
		result.setFlag(true); // 设置是否成功标识
		result.setMsg("生效成功"); // 设置返回消息，根据实际情况修改
		return result;
	}
	
	/**
	 * @Title: invalid<br/>
	 * @Description: 规则失效<br/>
	 * @param id
	 * @throws Exception <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月17日 上午11:04:47
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月17日 上午11:04:47
	 * @since  1.0.0
	 */
	public void invalid(String id) throws Exception {
		SmCalcConfigure smCalcConfigure = new SmCalcConfigure();
		smCalcConfigure.setId(id);
		smCalcConfigure.setStatus(RuleConfigureContants.CONTRACT_RULE_EXPIRE);
		smCalcConfigure.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.saveExcludeNull(smCalcConfigure);
	}
}