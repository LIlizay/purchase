package com.hhwy.purchaseweb.archives.consdialog.service.impl;

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
import com.hhwy.purchaseweb.archives.consdialog.domain.ConsDialogDetail;
import com.hhwy.purchaseweb.archives.consdialog.domain.ConsDialogVo;
import com.hhwy.purchaseweb.archives.consdialog.service.ConsDialogService;
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoDetail;

/**
 * ScConsumerInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhConsDialogServiceImpl implements ConsDialogService {

	public static final Logger log = LoggerFactory.getLogger(PhConsDialogServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

	/**
	 * 获取用户下拉列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ConsDialogDetail getConsInfo(String id) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id",id);
		List<ConsDialogDetail> smPpaList = (List<ConsDialogDetail>)dao.getBySql("phConsDialog.sql.getConsDialogById",map);
		ConvertListUtil.convert(smPpaList);
		return smPpaList.get(0);
	}

	/**
	 * 获取用户下拉列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<ConsDialogDetail> getConsList(ConsDialogVo consDialogVo) throws Exception {
		QueryResult<ConsDialogDetail> queryResult = new QueryResult<ConsDialogDetail>();
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(consDialogVo == null){
			consDialogVo = new ConsDialogVo();
		}
//		String provinceCode = SystemServiceUtil.getLoginUserOrgCode().substring(0, 2);
//		consDialogVo.setProvinceCode(provinceCode+"0000");
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phConsDialog.sql.getConsDialogCountByParams",consDialogVo);
		long total = result == null ? 0 : Integer.valueOf(result.toString());
		List<ConsDialogDetail> list = (List<ConsDialogDetail>)dao.getBySql("phConsDialog.sql.getConsDialogListByParams",consDialogVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(list);
		queryResult.setTotal(total);
		queryResult.setData(list);
		return queryResult;
	}
	
	/**
	 * @Title: getConsListForMonitor
	 * @Description: 监控平台->用户电量 页面的选择用户弹出框 中获取用户列表
	 * 				条件：用户合同结束时间月份 >= 查询年月 or 监测中间表中有关联关系的用户
	 * @param consDialogVo
	 * @return
	 * @throws Exception 
	 * QueryResult<ConsDialogDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月23日 下午9:22:01
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月23日 下午9:22:01
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<ConsDialogDetail> getConsListForMonitor(ConsDialogVo consDialogVo) throws Exception {
		QueryResult<ConsDialogDetail> queryResult = new QueryResult<ConsDialogDetail>();
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(consDialogVo == null){
			consDialogVo = new ConsDialogVo();
		}
//		String provinceCode = SystemServiceUtil.getLoginUserOrgCode().substring(0, 2);
//		consDialogVo.setProvinceCode(provinceCode+"0000");
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phConsDialog.sql.getConsCountForMonitorByParams",consDialogVo);
		long total = result == null ? 0 : Integer.valueOf(result.toString());
		List<ConsDialogDetail> list = (List<ConsDialogDetail>)dao.getBySql("phConsDialog.sql.getConsListForMonitorByParams",consDialogVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(list);
		queryResult.setTotal(total);
		queryResult.setData(list);
		return queryResult;
	}

	/**
     * 分页获取对象ScOrgInfoDetail(供电公司信息detail类)
     * @param scOrgInfoVo       查询条件
     * @return QueryResult<ScOrgInfoDetail>
     */
    public QueryResult<ScOrgInfoDetail> getScOrgInfoByPage(ConsDialogVo consDialogVo) throws Exception{
        QueryResult<ScOrgInfoDetail> queryResult = new QueryResult<ScOrgInfoDetail>();
        long total = getScOrgInfoCountByParams(consDialogVo);
        List<ScOrgInfoDetail> detailList = getOrgInfoDetailListByParams(consDialogVo);
        queryResult.setTotal(total);
        queryResult.setData(detailList);
        return queryResult;
    }   
    
    /**
     * 根据查询条件获取对象ScOrgInfo(供电公司信息)数量
     * @param ScOrgInfoVo       查询条件
     * @return Integer
     */
    public Integer getScOrgInfoCountByParams(ConsDialogVo consDialogVo){
        Parameter.isFilterData.set(true);
        Object result = dao.getOneBySQL("phConsDialog.sql.getScOrgInfoCountByParams",consDialogVo);
        Parameter.isFilterData.set(false);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
    /**
     * 根据查询条件获取对象ScOrgInfoDetail(供电公司信息detail类)列表
     * @param ScOrgInfoVo       查询条件
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ScOrgInfoDetail> getOrgInfoDetailListByParams(ConsDialogVo consDialogVo) throws Exception{
        //当Vo为空时,初始化Vo对象,应用分页参数
        if(consDialogVo == null){
        	consDialogVo = new ConsDialogVo();
        }
        Parameter.isFilterData.set(true);
        List<ScOrgInfoDetail> scOrgInfoList = (List<ScOrgInfoDetail>)dao.getBySql("phConsDialog.sql.getOrgInfoDetailListByParams",consDialogVo);
        Parameter.isFilterData.set(false);
        ConvertListUtil.convert(scOrgInfoList);
        return scOrgInfoList;
    }
}