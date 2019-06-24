package com.hhwy.purchaseweb.archives.sccompanyinfo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.sccompanyinfo.domain.ScCompanyDetail;
import com.hhwy.purchaseweb.archives.sccompanyinfo.domain.ScCompanyInfoVo;
import com.hhwy.purchaseweb.archives.sccompanyinfo.service.ScCompanyInfoService;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.selling.domain.ScCompanyInfo;

/**
 * ScCompanyInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class ScCompanyInfoServiceImpl implements ScCompanyInfoService {

	public static final Logger log = LoggerFactory.getLogger(ScCompanyInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象ScCompanyInfo
	 * @param ID
	 * @return ScCompanyInfo
	 */
	public QueryResult<ScCompanyDetail> getScCompanyInfoByPage(ScCompanyInfoVo scCompanyInfoVo) throws Exception{
		QueryResult<ScCompanyDetail> queryResult = new QueryResult<ScCompanyDetail>();
		long total = getScCompanyInfoCountByParams(scCompanyInfoVo);
		List<ScCompanyDetail> scCompanyInfoList = getScCompanyInfoListByParams(scCompanyInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(scCompanyInfoList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象ScCompanyInfo数量
	 * @param ScCompanyInfoVo
	 * @return Integer
	 */
	public Integer getScCompanyInfoCountByParams(ScCompanyInfoVo scCompanyInfoVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scCompanyInfo.sql.getScCompanyInfoCountByParams",scCompanyInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象ScCompanyInfo列表
	 * @param ScCompanyInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ScCompanyDetail> getScCompanyInfoListByParams(ScCompanyInfoVo scCompanyInfoVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(scCompanyInfoVo == null){
			scCompanyInfoVo = new ScCompanyInfoVo();
		}
		Parameter.isFilterData.set(true);
		List<ScCompanyDetail> scCompanyInfoList = 
				(List<ScCompanyDetail>)dao.getBySql("scCompanyInfo.sql.getScCompanyInfoListByParams",scCompanyInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(scCompanyInfoList);
		return scCompanyInfoList;
	}
	/**
	 * 根据查询条件获取单个对象ScCompanyInfo
	 * @param ScCompanyInfoVo
	 * @return ScCompanyInfo
	 */
	public ScCompanyDetail getScCompanyInfoOneByParams(ScCompanyInfoVo scCompanyInfoVo) throws Exception{
		ScCompanyDetail scCompanyDetail = null;
		List<ScCompanyDetail> scCompanyInfoList = getScCompanyInfoListByParams(scCompanyInfoVo);
		if(scCompanyInfoList != null && scCompanyInfoList.size() > 0){
			scCompanyDetail = scCompanyInfoList.get(0);
		}
		return scCompanyDetail;
	}
	
	/**
	 * 根据ID获取对象ScCompanyInfo
	 * @param ID
	 * @return ScCompanyInfo
	 */
	public ScCompanyInfo getScCompanyInfoById(String id) {
		return dao.findById(id, ScCompanyInfo.class);
	}	
	
	/**
	 * 添加对象ScCompanyInfo
	 * @param 实体对象
	 */
	public void saveScCompanyInfo(ScCompanyInfo scCompanyInfo) {
		scCompanyInfo.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(scCompanyInfo);
	}
	
	/**
	 * 添加对象ScCompanyInfo
	 * @param 实体对象
	 */
	public void saveScCompanyInfoList(List<ScCompanyInfo> scCompanyInfoList) {
		for(ScCompanyInfo scCompanyInfo : scCompanyInfoList){
			scCompanyInfo.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(scCompanyInfoList);
	}
	
	/**
	 * 更新对象ScCompanyInfo
	 * @param 实体对象
	 */
	public void updateScCompanyInfo(ScCompanyInfo scCompanyInfo) {
		dao.update(scCompanyInfo);
	}
	
	/**
	 * 删除对象ScCompanyInfo
	 * @param id数据组
	 */
	public void deleteScCompanyInfo(String[] ids) {
		dao.delete(ids, ScCompanyInfo.class);
	}	
}