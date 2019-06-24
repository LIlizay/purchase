package com.hhwy.purchaseweb.contract.smagretemplate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hhwy.framework.util.StringUtils;
import com.hhwy.purchaseweb.contract.smagretemplate.contant.TemplateConstants;
import com.hhwy.purchaseweb.contract.smagretemplate.domain.SmAgreTemplateDetail;
import com.hhwy.purchaseweb.contract.smagretemplate.domain.SmAgreTemplateVo;
import com.hhwy.purchaseweb.contract.smagretemplate.service.SmAgreTemplateService;
import com.hhwy.selling.domain.SmAgreTemplate;

/**
 * SmAgreTemplateService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmAgreTemplateServiceImpl implements SmAgreTemplateService {

	public static final Logger log = LoggerFactory.getLogger(SmAgreTemplateServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmAgreTemplate
	 * @param ID
	 * @return SmAgreTemplate
	 */
	@Override
	public QueryResult<SmAgreTemplateDetail> getSmAgreTemplateByPage(SmAgreTemplateVo smAgreTemplateVo) throws Exception{
		QueryResult<SmAgreTemplateDetail> queryResult = new QueryResult<SmAgreTemplateDetail>();
		long total = getSmAgreTemplateCountByParams(smAgreTemplateVo);
		List<SmAgreTemplateDetail> smAgreTemplateList = getSmAgreTemplateListByParams(smAgreTemplateVo);
		queryResult.setTotal(total);
		queryResult.setData(smAgreTemplateList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmAgreTemplate数量
	 * @param SmAgreTemplateVo
	 * @return Integer
	 */
	@Override
	public Integer getSmAgreTemplateCountByParams(SmAgreTemplateVo smAgreTemplateVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smAgreTemplate.sql.getSmAgreTemplateCountByParams",smAgreTemplateVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmAgreTemplate列表
	 * @param SmAgreTemplateVo
	 * @return List
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SmAgreTemplateDetail> getSmAgreTemplateListByParams(SmAgreTemplateVo smAgreTemplateVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smAgreTemplateVo == null){
			smAgreTemplateVo = new SmAgreTemplateVo();
		}
		Parameter.isFilterData.set(true);
		List<SmAgreTemplateDetail> smAgreTemplateList = (List<SmAgreTemplateDetail>)dao.getBySql("smAgreTemplate.sql.getSmAgreTemplateListByParams",smAgreTemplateVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smAgreTemplateList);
		return smAgreTemplateList;
	}
	/**
	 * 根据查询条件获取单个对象SmAgreTemplate
	 * @param SmAgreTemplateVo
	 * @return SmAgreTemplate
	 */
	@Override
	public SmAgreTemplateDetail getSmAgreTemplateOneByParams(SmAgreTemplateVo smAgreTemplateVo) throws Exception{
	    SmAgreTemplateDetail smAgreTemplate = null;
		List<SmAgreTemplateDetail> smAgreTemplateList = getSmAgreTemplateListByParams(smAgreTemplateVo);
		if(smAgreTemplateList != null && smAgreTemplateList.size() > 0){
			smAgreTemplate = smAgreTemplateList.get(0);
		}
		return smAgreTemplate;
	}
	
	/**
	 * 根据ID获取对象SmAgreTemplate
	 * @param ID
	 * @return SmAgreTemplate
	 */
	@Override
	public SmAgreTemplate getSmAgreTemplateById(String id) {
		return dao.findById(id, SmAgreTemplate.class);
	}	
	
	/**
     * 获取下拉list
     * getSelect(描述这个方法的作用)<br/>
     * @return 
     * List<Map<String,Object>>
     * @exception 
     * @since  1.0.0
     */
	@Override
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> getSelect(String templateType){
	    Map<String,Object> map = new HashMap<>();
	    map.put("templateType", templateType);
	    Parameter.isFilterData.set(true);
	    List<Map<String, Object>> list = (List<Map<String, Object>>) dao.getBySql("smAgreTemplate.sql.getSelect", map);
	    Parameter.isFilterData.set(false);
        return list;
    }
    
	/**
	 * 添加对象SmAgreTemplate
	 * @param 实体对象
	 */
	@Override
	public void saveSmAgreTemplate(SmAgreTemplate smAgreTemplate) {
	    Date now = new Date();
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
	    String ym = df.format(now);
	    Parameter.isFilterData.set(true);
	    String sequence = (String) dao.getOneBySQL("smAgreTemplate.sql.getSequence", ym);
	    Parameter.isFilterData.set(false);
	    if(StringUtils.isNull(sequence)){
	        smAgreTemplate.setTemplateVer(ym+"01");
	    }else{
	        if(sequence.length()<2){
	            sequence = "0"+sequence;
	        }
	        smAgreTemplate.setTemplateVer(ym+sequence);
	    }
	    smAgreTemplate.setAgreStatus(TemplateConstants.AGRE_STATE_NEW);
		dao.save(smAgreTemplate);
	}
	
	/**
	 * 添加对象SmAgreTemplate
	 * @param 实体对象
	 */
	@Override
	public void saveSmAgreTemplateList(List<SmAgreTemplate> smAgreTemplateList) {
		dao.saveList(smAgreTemplateList);
	}
	
	/**
     * 生效
     * effect(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void effect(String id){
        SmAgreTemplate smAgreTemplate = new SmAgreTemplate();
        smAgreTemplate.setId(id);
        smAgreTemplate.setAgreStatus(TemplateConstants.AGRE_STATE_EFFECT);
        dao.updateExcludeNull(smAgreTemplate);
    }
    
    /**
     * 失效
     * invalid(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void invalid(String id){
        SmAgreTemplate smAgreTemplate = new SmAgreTemplate();
        smAgreTemplate.setId(id);
        smAgreTemplate.setAgreStatus(TemplateConstants.AGRE_STATE_INVALID);
        dao.updateExcludeNull(smAgreTemplate);
    }
    
	/**
	 * 更新对象SmAgreTemplate
	 * @param 实体对象
	 */
	@Override
	public void updateSmAgreTemplate(SmAgreTemplate smAgreTemplate) {
		dao.update(smAgreTemplate);
	}
	
	/**
	 * 删除对象SmAgreTemplate
	 * @param id数据组
	 */
	@Override
	public void deleteSmAgreTemplate(String[] ids) {
		dao.delete(ids, SmAgreTemplate.class);
	}	
}