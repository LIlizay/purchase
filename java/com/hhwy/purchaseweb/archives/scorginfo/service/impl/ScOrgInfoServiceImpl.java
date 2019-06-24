package com.hhwy.purchaseweb.archives.scorginfo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoDetail;
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoVo;
import com.hhwy.purchaseweb.archives.scorginfo.service.ScOrgInfoService;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.selling.domain.ScOrgInfo;

 /**
 * <b>类 名 称：</b>ScOrgInfoServiceImpl<br/>
 * <b>类 描 述：</b><br/>		供电公司信息的service实现类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2016年11月22日 下午4:00:21<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
@SuppressWarnings("unchecked")
public class ScOrgInfoServiceImpl implements ScOrgInfoService {

	public static final Logger log = LoggerFactory.getLogger(ScOrgInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象ScOrgInfoDetail(供电公司信息detail类)
	 * @param scOrgInfoVo		查询条件
	 * @return QueryResult<ScOrgInfoDetail>
	 */
	public QueryResult<ScOrgInfoDetail> getScOrgInfoByPage(ScOrgInfoVo scOrgInfoVo) throws Exception{
		QueryResult<ScOrgInfoDetail> queryResult = new QueryResult<ScOrgInfoDetail>();
		long total = getScOrgInfoCountByParams(scOrgInfoVo);
		List<ScOrgInfoDetail> detailList = getOrgInfoDetailListByParams(scOrgInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(detailList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象ScOrgInfo(供电公司信息)数量
	 * @param ScOrgInfoVo		查询条件
	 * @return Integer
	 */
	public Integer getScOrgInfoCountByParams(ScOrgInfoVo scOrgInfoVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scOrgInfo.sql.getScOrgInfoCountByParams",scOrgInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象ScOrgInfoDetail(供电公司信息detail类)列表
	 * @param ScOrgInfoVo		查询条件
	 * @return List
	 */
	public List<ScOrgInfoDetail> getOrgInfoDetailListByParams(ScOrgInfoVo scOrgInfoVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(scOrgInfoVo == null){
			scOrgInfoVo = new ScOrgInfoVo();
		}
		Parameter.isFilterData.set(true);
		List<ScOrgInfoDetail> scOrgInfoList = (List<ScOrgInfoDetail>)dao.getBySql("scOrgInfo.sql.getOrgInfoDetailListByParams",scOrgInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(scOrgInfoList);
		return scOrgInfoList;
	}
	/**
	 * 根据查询条件获取单个对象ScOrgInfoDetail(供电公司信息detail类)
	 * @param ScOrgInfoVo		查询条件
	 * @return ScOrgInfoDetail
	 */
	public ScOrgInfoDetail getScOrgInfoOneByParams(ScOrgInfoVo scOrgInfoVo) throws Exception{
		ScOrgInfoDetail orgInfoDetail = null;
		List<ScOrgInfoDetail> scOrgInfoList = getOrgInfoDetailListByParams(scOrgInfoVo);
		if(scOrgInfoList != null && scOrgInfoList.size() > 0){
			orgInfoDetail = scOrgInfoList.get(0);
		}
		return orgInfoDetail;
	}
	
	/**
	 * 根据ID获取对象ScOrgInfo(供电公司信息)
	 * @param ID
	 * @return ScOrgInfo
	 */
	public ScOrgInfo getScOrgInfoById(String id) {
		return dao.findById(id, ScOrgInfo.class);
	}	
	
	/**
	 * 添加对象ScOrgInfo(供电公司信息)
	 * @param 实体对象
	 */
	public void saveScOrgInfo(ScOrgInfo scOrgInfo) {
		scOrgInfo.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(scOrgInfo);
	}
	
	/**
	 * 添加对象ScOrgInfo(供电公司信息)
	 * @param 实体对象
	 */
	public void saveScOrgInfoList(List<ScOrgInfo> scOrgInfoList) {
		for (ScOrgInfo scOrgInfo : scOrgInfoList) {
			scOrgInfo.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(scOrgInfoList);
	}
	
	/**
	 * 更新对象ScOrgInfo(供电公司信息)
	 * @param 实体对象
	 */
	public void updateScOrgInfo(ScOrgInfo scOrgInfo) {
		dao.update(scOrgInfo);
	}
	
	/**
	 * 删除对象ScOrgInfo(供电公司信息)
	 * @param id数据组
	 */
	public void deleteScOrgInfo(String[] ids) {
		dao.delete(ids, ScOrgInfo.class);
	}	
	

	
	/**
	 * 供电单位信息下拉列表查询
	 * @return List<ScOrgInfo>
	 */
	public List<ScOrgInfo> getSelectOrgList() throws Exception{
		Parameter.isFilterData.set(true);
		List<ScOrgInfo> list = (List<ScOrgInfo>)dao.getBySql("scOrgInfo.sql.getSelectOrgList",null);
		Parameter.isFilterData.set(false);
		return list;
	}

	/**
	 * 供电公司重复性校验
	 */
	@Override
	public void checkScOrgInfo(ScOrgInfo scOrgInfo) {
		Parameter.isFilterData.set(true);
		List<String> list = (List<String>) dao.getBySql("scOrgInfo.sql.checkOrgList", scOrgInfo);
		Parameter.isFilterData.set(false);
		if(list.size()>0){
            throw new RuntimeException("供电公司名称重复");
        }
		
	}
	
	/**
	 * @Title: getOrgInfoListByNames
	 * @Description: 根据供电公司名称获取供电公司id，     用户信息导入时用到
	 * @param orgNames
	 * @return 
	 * List<ScOrgInfoDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月20日 下午2:16:07
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月20日 下午2:16:07
	 * @since  1.0.0
	 */
	public List<ScOrgInfoDetail> getOrgInfoListByNames(List<String> orgNames) {
		Parameter.isFilterData.set(true);
		List<ScOrgInfoDetail> list = (List<ScOrgInfoDetail>) dao.getBySql("scOrgInfo.sql.getOrgInfoListByNames", orgNames);
		Parameter.isFilterData.set(false);
		return list;
	}
}