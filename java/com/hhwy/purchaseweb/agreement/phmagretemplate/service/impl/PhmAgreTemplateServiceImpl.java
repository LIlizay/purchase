package com.hhwy.purchaseweb.agreement.phmagretemplate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.StringUtils;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmAgreTemplate;
import com.hhwy.purchaseweb.agreement.phmagretemplate.constant.TemplateConstants;
import com.hhwy.purchaseweb.agreement.phmagretemplate.domain.PhmAgreTemplateDetail;
import com.hhwy.purchaseweb.agreement.phmagretemplate.domain.PhmAgreTemplateVo;
import com.hhwy.purchaseweb.agreement.phmagretemplate.service.PhmAgreTemplateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmAgreTemplateService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmAgreTemplateServiceImpl implements PhmAgreTemplateService {

	public static final Logger log = LoggerFactory.getLogger(PhmAgreTemplateServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmAgreTemplate
	 * @param ID
	 * @return PhmAgreTemplate
	 */
	public QueryResult<PhmAgreTemplateDetail> getPhmAgreTemplateByPage(PhmAgreTemplateVo phmAgreTemplateVo) throws Exception{
		QueryResult<PhmAgreTemplateDetail> queryResult = new QueryResult<PhmAgreTemplateDetail>();
		long total = getPhmAgreTemplateCountByParams(phmAgreTemplateVo);
		List<PhmAgreTemplateDetail> phmAgreTemplateList = getPhmAgreTemplateListByParams(phmAgreTemplateVo);
		queryResult.setTotal(total);
		queryResult.setData(phmAgreTemplateList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmAgreTemplate数量
	 * @param PhmAgreTemplateVo
	 * @return Integer
	 */
	public Integer getPhmAgreTemplateCountByParams(PhmAgreTemplateVo phmAgreTemplateVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmAgreTemplate.sql.getPhmAgreTemplateCountByParams",phmAgreTemplateVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmAgreTemplate列表
	 * @param PhmAgreTemplateVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmAgreTemplateDetail> getPhmAgreTemplateListByParams(PhmAgreTemplateVo phmAgreTemplateVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmAgreTemplateVo == null){
			phmAgreTemplateVo = new PhmAgreTemplateVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmAgreTemplateDetail> phmAgreTemplateList = (List<PhmAgreTemplateDetail>)dao.getBySql("phmAgreTemplate.sql.getPhmAgreTemplateListByParams",phmAgreTemplateVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmAgreTemplateList);
		return phmAgreTemplateList;
	}
	/**
	 * 根据查询条件获取单个对象PhmAgreTemplate
	 * @param PhmAgreTemplateVo
	 * @return PhmAgreTemplate
	 */
	public PhmAgreTemplateDetail getPhmAgreTemplateOneByParams(PhmAgreTemplateVo phmAgreTemplateVo) throws Exception{
	    PhmAgreTemplateDetail phmAgreTemplate = null;
		List<PhmAgreTemplateDetail> phmAgreTemplateList = getPhmAgreTemplateListByParams(phmAgreTemplateVo);
		if(phmAgreTemplateList != null && phmAgreTemplateList.size() > 0){
			phmAgreTemplate = phmAgreTemplateList.get(0);
		}
		return phmAgreTemplate;
	}
	
	/**
	 * 根据ID获取对象PhmAgreTemplate
	 * @param ID
	 * @return PhmAgreTemplate
	 */
	public PhmAgreTemplate getPhmAgreTemplateById(String id) {
		return dao.findById(id, PhmAgreTemplate.class);
	}	
	
	/**
	 * 添加对象PhmAgreTemplate
	 * @param 实体对象
	 */
	public void savePhmAgreTemplate(PhmAgreTemplate phmAgreTemplate) {
	    Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        String ym = df.format(now);
        Parameter.isFilterData.set(true);
        String sequence = (String) dao.getOneBySQL("phmAgreTemplate.sql.getSequence", ym);
        Parameter.isFilterData.set(false);
        if(StringUtils.isNull(sequence)){
            phmAgreTemplate.setTemplateVer(ym+"01");
        }else{
            if(sequence.length()<2){
                sequence = "0"+sequence;
            }
            phmAgreTemplate.setTemplateVer(ym+sequence);
        }
        phmAgreTemplate.setCreateDate(new Date());
        phmAgreTemplate.setTemplateStatus(TemplateConstants.AGRE_STATE_NEW);
        dao.save(phmAgreTemplate);
	}
	
	/**
	 * 添加对象PhmAgreTemplate
	 * @param 实体对象
	 */
	public void savePhmAgreTemplateList(List<PhmAgreTemplate> phmAgreTemplateList) {
		dao.saveList(phmAgreTemplateList);
	}
	
	/**
     * 模板生效
     * effect(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void effect(String id){
        PhmAgreTemplate phmAgreTemplate = new PhmAgreTemplate();
        phmAgreTemplate.setId(id);
        phmAgreTemplate.setTemplateStatus(TemplateConstants.AGRE_STATE_EFFECT);
        dao.updateExcludeNull(phmAgreTemplate);
    }
    
    /**
     * 模板失效
     * invalid(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void invalid(String id){
        PhmAgreTemplate phmAgreTemplate = new PhmAgreTemplate();
        phmAgreTemplate.setId(id);
        phmAgreTemplate.setTemplateStatus(TemplateConstants.AGRE_STATE_INVALID);
        dao.updateExcludeNull(phmAgreTemplate);
    }
    
	/**
	 * 更新对象PhmAgreTemplate
	 * @param 实体对象
	 */
	public void updatePhmAgreTemplate(PhmAgreTemplate phmAgreTemplate) {
		dao.update(phmAgreTemplate);
	}
	
	/**
	 * 删除对象PhmAgreTemplate
	 * @param id数据组
	 */
	public void deletePhmAgreTemplate(String[] ids) {
		dao.delete(ids, PhmAgreTemplate.class);
	}	
}