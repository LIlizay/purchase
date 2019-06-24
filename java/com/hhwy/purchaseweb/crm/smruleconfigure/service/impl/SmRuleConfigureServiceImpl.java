package com.hhwy.purchaseweb.crm.smruleconfigure.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmRuleConfigure;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.purchaseweb.contants.RuleConfigureContants;
import com.hhwy.purchaseweb.crm.smruleconfigure.domain.SmRuleConfigureVo;
import com.hhwy.purchaseweb.crm.smruleconfigure.service.SmRuleConfigureService;

/**
 * SmRuleConfigureService
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmRuleConfigureServiceImpl implements SmRuleConfigureService {

	public static final Logger log = LoggerFactory
			.getLogger(SmRuleConfigureServiceImpl.class);

	@Autowired
	DAO<?> dao;

	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

	/**
	 * 分页获取对象SmRuleConfigure
	 * 
	 * @param ID
	 * @return SmRuleConfigure
	 */
	public QueryResult<SmRuleConfigure> getSmRuleConfigureByPage(
			SmRuleConfigureVo smRuleConfigureVo) throws Exception {
		QueryResult<SmRuleConfigure> queryResult = new QueryResult<SmRuleConfigure>();
		long total = getSmRuleConfigureCountByParams(smRuleConfigureVo);
		List<SmRuleConfigure> smRuleConfigureList = getSmRuleConfigureListByParams(smRuleConfigureVo);
		queryResult.setTotal(total);
		queryResult.setData(smRuleConfigureList);
		return queryResult;
	}

	/**
	 * 根据查询条件获取对象SmRuleConfigure数量
	 * 
	 * @param SmRuleConfigureVo
	 * @return Integer
	 */
	public Integer getSmRuleConfigureCountByParams(
			SmRuleConfigureVo smRuleConfigureVo) {
		Object result = dao.getOneBySQL(
				"smRuleConfigure.sql.getSmRuleConfigureCountByParams",
				smRuleConfigureVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	/**
	 * 根据查询条件获取对象SmRuleConfigure列表
	 * 
	 * @param SmRuleConfigureVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmRuleConfigure> getSmRuleConfigureListByParams(
			SmRuleConfigureVo smRuleConfigureVo) throws Exception {
		// 当Vo为空时,初始化Vo对象,应用分页参数
		if (smRuleConfigureVo == null) {
			smRuleConfigureVo = new SmRuleConfigureVo();
		}
		List<SmRuleConfigure> smRuleConfigureList = (List<SmRuleConfigure>) dao
				.getBySql("smRuleConfigure.sql.getSmRuleConfigureListByParams",
						smRuleConfigureVo);
		ConvertListUtil.convert(smRuleConfigureList);
		return smRuleConfigureList;
	}

	/**
	 * 根据查询条件获取单个对象SmRuleConfigure
	 * 
	 * @param SmRuleConfigureVo
	 * @return SmRuleConfigure
	 */
	public SmRuleConfigure getSmRuleConfigureOneByParams(
			SmRuleConfigureVo smRuleConfigureVo) throws Exception {
		SmRuleConfigure smRuleConfigure = null;
		List<SmRuleConfigure> smRuleConfigureList = getSmRuleConfigureListByParams(smRuleConfigureVo);
		if (smRuleConfigureList != null && smRuleConfigureList.size() > 0) {
			smRuleConfigure = smRuleConfigureList.get(0);
		}
		return smRuleConfigure;
	}

	/**
	 * 根据ID获取对象SmRuleConfigure
	 * 
	 * @param ID
	 * @return SmRuleConfigure
	 */
	public SmRuleConfigure getSmRuleConfigureById(String id) {
		return dao.findById(id, SmRuleConfigure.class);
	}

	/**
	 * 添加对象SmRuleConfigure
	 * 
	 * @param 实体对象
	 */
	public void saveSmRuleConfigure(SmRuleConfigure smRuleConfigure) {
		smRuleConfigure.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(smRuleConfigure);
	}

	/**
	 * 添加对象SmRuleConfigure
	 * 
	 * @param 实体对象
	 */
	public void saveSmRuleConfigureList(
			List<SmRuleConfigure> smRuleConfigureList) {
		for (SmRuleConfigure smRuleConfigure : smRuleConfigureList) {
			smRuleConfigure.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(smRuleConfigureList);
	}

	/**
	 * 更新对象SmRuleConfigure
	 * 
	 * @param 实体对象
	 */
	public void updateSmRuleConfigure(SmRuleConfigure smRuleConfigure) {
		dao.update(smRuleConfigure);
	}

	/**
	 * 删除对象SmRuleConfigure
	 * 
	 * @param id数据组
	 */
	public void deleteSmRuleConfigure(String[] ids) {
		dao.delete(ids, SmRuleConfigure.class);
	}

	/**
	 * 获取树 getTree(描述这个方法的作用)<br/>
	 * 
	 * @param nodeType
	 * @param provinceId
	 * @return Object
	 * @exception
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getTree(String nodeType, String provinceId) {
		if ("root".equals(nodeType)) {
			Map<String, Object> root = new HashMap<>();
			root.put("text", "省份");
			root.put("nodeType", "root");
			root.put("state", "open");
			root.put("children",
					dao.getBySql("smRuleConfigure.sql.getProTree", ""));
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
			list.addAll((List<Map<String, Object>>) dao.getBySql(
					"smRuleConfigure.sql.getVersion", provinceId));
			return list;
		} else {
			return new Object[] {};
		}
	}

	/**
	 * 规则生效 effect(描述这个方法的作用)<br/>
	 * 
	 * @param id
	 * @return ExecutionResult
	 * @exception
	 * @since 1.0.0
	 */
	@Override
	public ExecutionResult effect(String provinceId, String id)
			throws Exception {
		ExecutionResult result = new ExecutionResult();
		SmRuleConfigureVo smRuleConfigureVo = new SmRuleConfigureVo();
		smRuleConfigureVo.getSmRuleConfigure().setProvinceId(provinceId);
		smRuleConfigureVo.getSmRuleConfigure().setRuleStatus(
				RuleConfigureContants.CONTRACT_RULE_EFFECT);
		int count = getSmRuleConfigureCountByParams(smRuleConfigureVo);
		if (count > 0) {
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setFlag(false); // 设置是否成功标识
			result.setMsg("存在已经生效的规则，请先失效该规则后再生效"); // 设置返回消息，根据实际情况修改
			return result;
		}
		SmRuleConfigure smRuleConfigure = new SmRuleConfigure();
		smRuleConfigure.setId(id);
		smRuleConfigure
				.setRuleStatus(RuleConfigureContants.CONTRACT_RULE_EFFECT);
		smRuleConfigure.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.saveExcludeNull(smRuleConfigure);

		result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
		result.setFlag(true); // 设置是否成功标识
		result.setMsg("生效成功"); // 设置返回消息，根据实际情况修改
		return result;
	}

	/**
	 * 规则失效 invalid(描述这个方法的作用)<br/>
	 * 
	 * @param id
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	@Override
	public void invalid(String id) throws Exception {
		SmRuleConfigure smRuleConfigure = new SmRuleConfigure();
		smRuleConfigure.setId(id);
		smRuleConfigure
				.setRuleStatus(RuleConfigureContants.CONTRACT_RULE_EXPIRE);
		smRuleConfigure.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.saveExcludeNull(smRuleConfigure);
	}

}