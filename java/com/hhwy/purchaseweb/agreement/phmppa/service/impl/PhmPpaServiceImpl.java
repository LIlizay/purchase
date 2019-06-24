package com.hhwy.purchaseweb.agreement.phmppa.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchase.domain.PhcElecInfo;
import com.hhwy.purchase.domain.PhmAgreDeviation;
import com.hhwy.purchase.domain.PhmGenePqDist;
import com.hhwy.purchase.domain.PhmGeneratorMonthPq;
import com.hhwy.purchase.domain.PhmPpa;
import com.hhwy.purchaseweb.agreement.phmagredeviation.domain.PhmAgreDeviationDetail;
import com.hhwy.purchaseweb.agreement.phmagredeviation.domain.PhmAgreDeviationVo;
import com.hhwy.purchaseweb.agreement.phmagredeviation.service.PhmAgreDeviationService;
import com.hhwy.purchaseweb.agreement.phmgenepqdist.service.PhmGenePqDistService;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain.PhmGeneratorMonthPqDetail;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.service.PhmGeneratorMonthPqService;
import com.hhwy.purchaseweb.agreement.phmppa.domain.PhmPpaDetail;
import com.hhwy.purchaseweb.agreement.phmppa.domain.PhmPpaVo;
import com.hhwy.purchaseweb.agreement.phmppa.service.PhmPpaService;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;

/**
 * PhmPpaService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmPpaServiceImpl implements PhmPpaService {

	public static final Logger log = LoggerFactory.getLogger(PhmPpaServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 合同机组电量分月信息PhmGeneratorMonthPqService
	 */
	@Autowired
	private PhmGeneratorMonthPqService phmGeneratorMonthPqService;
	
	/**
	 * 合同机组电量分配方式信息PhmGenePqDistService
	 */
	@Autowired
	private PhmGenePqDistService phmGenePqDistService;
	
	/**
	 * 合同偏差考核规则信息PhmAgreDeviationService
	 */
	@Autowired
	private PhmAgreDeviationService phmAgreDeviationService;
	
	/**
	 * 分页获取对象PhmPpa
	 * @param ID
	 * @return PhmPpa
	 */
	public QueryResult<PhmPpaDetail> getPhmPpaByPage(PhmPpaVo phmPpaVo) throws Exception{
		QueryResult<PhmPpaDetail> queryResult = new QueryResult<PhmPpaDetail>();
		long total = getPhmPpaCountByParams(phmPpaVo);
		List<PhmPpaDetail> phmPpaDetailList = getPhmPpaListByParams(phmPpaVo);
		queryResult.setTotal(total);
		queryResult.setData(phmPpaDetailList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmPpa数量
	 * @param PhmPpaVo
	 * @return Integer
	 */
	public Integer getPhmPpaCountByParams(PhmPpaVo phmPpaVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmPpa.sql.getPhmPpaCountByParams",phmPpaVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmPpa列表
	 * @param PhmPpaVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmPpaDetail> getPhmPpaListByParams(PhmPpaVo phmPpaVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmPpaVo == null){
			phmPpaVo = new PhmPpaVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmPpaDetail> phmPpaDetailList = (List<PhmPpaDetail>)dao.getBySql("phmPpa.sql.getPhmPpaListByParams",phmPpaVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmPpaDetailList);
		return phmPpaDetailList;
	}
	
	/**
	 * 根据查询条件获取单个对象PhmPpa
	 * @param PhmPpaVo
	 * @return PhmPpa
	 */
	public PhmPpaDetail getPhmPpaOneByParams(PhmPpaVo phmPpaVo) throws Exception{
		PhmPpaDetail phmPpaDetail = null;
		List<PhmPpaDetail> phmPpaDetailList = getPhmPpaListByParams(phmPpaVo);
		if(phmPpaDetailList != null && phmPpaDetailList.size() > 0){
			phmPpaDetail = phmPpaDetailList.get(0);
		}
		return phmPpaDetail;
	}
	
	/**
	 * 根据ID获取对象PhmPpa
	 * @param ID
	 * @return PhmPpa
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public PhmPpaDetail getPhmPpaById(String id) throws Exception {
		PhmPpaDetail phmPpaDetail = new PhmPpaDetail();
		//获取购电合同信息
		PhmPpa phmPpa = dao.findById(id, PhmPpa.class);
		//获取电厂名称
		if(phmPpa!=null&&phmPpa.getElecId()!=null){
		    PhcElecInfo phcElecInfo = dao.findById(phmPpa.getElecId(), PhcElecInfo.class);
		    phmPpaDetail.setElecName(phcElecInfo.getElecName());
		    phmPpaDetail.setElecTypeCode(phcElecInfo.getElecTypeCode());
		}
		BeanUtils.copyProperties(phmPpa, phmPpaDetail);
		//获取发电单元分月交易电量
		Map<String,String> params = new HashMap<>();
		params.put("agreId", id);
		Parameter.isFilterData.set(true);
		List<PhmGeneratorMonthPqDetail> phmGeneratorMonthPqDetailList = (List<PhmGeneratorMonthPqDetail>) dao.getBySql("phmGeneratorMonthPq.sql.getPhmGeneratorMonthPqList", params);
		Parameter.isFilterData.set(false);
		phmPpaDetail.setPhmGeneratorMonthPqDetailList(phmGeneratorMonthPqDetailList);
		//获取合同偏差信息
		PhmAgreDeviationVo phmAgreDeviationVo = new PhmAgreDeviationVo();
		phmAgreDeviationVo.getPhmAgreDeviation().setAgreId(id);
		PhmAgreDeviationDetail phmAgreDeviationDetail = phmAgreDeviationService.getPhmAgreDeviationOneByParams(phmAgreDeviationVo);
		phmPpaDetail.setPhmAgreDeviationDetail(phmAgreDeviationDetail);
		return phmPpaDetail;
	}	
	
	/**
     * 根据购电合同id获取合同机组电量分月信息
     * getImplementation(描述这个方法的作用)<br/>
     * @param agreId
     * @return 
     * List<Map<String,Object>>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
	public List<PhmGeneratorMonthPqDetail> getImplementation(String agreId){
    	List<PhmGeneratorMonthPqDetail> phmGeneratorMonthPqDetailList = (List<PhmGeneratorMonthPqDetail>) dao.getBySql("phmGeneratorMonthPq.sql.getGeneMonthPqDetailByAgreId", agreId);
    	PhmGeneratorMonthPqDetail detail1 = new PhmGeneratorMonthPqDetail();
    	detail1.setItemName("结算电量");
    	PhmGeneratorMonthPqDetail detail2 = new PhmGeneratorMonthPqDetail();
    	detail2.setItemName("偏差电量");
    	PhmGeneratorMonthPqDetail detail3 = new PhmGeneratorMonthPqDetail();
    	detail3.setItemName("偏差率");
    	phmGeneratorMonthPqDetailList.add(detail1);
    	phmGeneratorMonthPqDetailList.add(detail2);
    	phmGeneratorMonthPqDetailList.add(detail3);
        return phmGeneratorMonthPqDetailList;
    }
    
	/**
	 * 添加对象PhmPpa
	 * @param 实体对象
	 */
	@Transactional
	public PhmPpaVo savePhmPpa(PhmPpaVo phmPpaVo) {
		String id = PlatformTools.getID();
		PhmPpa phmPpa = phmPpaVo.getPhmPpa();
		phmPpa.setId(id);
		//自动生成合同编号  暂取 改成手填
//        Date now = new Date();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy");
//        String year = df.format(now);
//        Parameter.isFilterData.set(true);
//        String sequence = (String) dao.getOneBySQL("phmGeneratorMonthPq.sql.getAgreNoSequence", year);
//        Parameter.isFilterData.set(false);
//        if(StringUtils.isNull(sequence)){
//            phmPpa.setAgreNo(year+"001");
//        }else{
//            if(sequence.length()<3){
//                sequence = "0"+sequence;
//            }
//            phmPpa.setAgreNo(year+sequence);
//        }
//		phmPpa.setOrgNo(OrgUtil.getOrgNoByUser());
		System.out.println("==========================================起始日期："+phmPpa.getBgnDate()+"====结束日期："+phmPpa.getEndDate());
		dao.save(phmPpa);
		//保存合同机组电量分月信息
		List<PhmGeneratorMonthPq> phmGeneratorMonthPqList = phmPpaVo.getPhmGeneratorMonthPqList();
		for(PhmGeneratorMonthPq phmGeneratorMonthPq : phmGeneratorMonthPqList){
			phmGeneratorMonthPq.setAgreId(id);
			phmGeneratorMonthPq.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		phmGeneratorMonthPqService.savePhmGeneratorMonthPqList(phmGeneratorMonthPqList);
		//保存合同机组电量分配方式信息
		 List<PhmGenePqDist> phmGenePqDistList = phmPpaVo.getPhmGenePqDistList();
		 for(PhmGenePqDist phmGenePqDist : phmGenePqDistList){
			 phmGenePqDist.setAgreId(id);
			 phmGenePqDist.setOrgNo(OrgUtil.getOrgNoByUser());
		 }
		 phmGenePqDistService.savePhmGenePqDistList(phmGenePqDistList);
		 //保存合同偏差考核规则信息
		 PhmAgreDeviation phmAgreDeviation = phmPpaVo.getPhmAgreDeviation();
		 phmAgreDeviation.setAgreId(id);
		 phmAgreDeviation.setOrgNo(OrgUtil.getOrgNoByUser());
		 phmAgreDeviationService.savePhmAgreDeviation(phmAgreDeviation);
		 return phmPpaVo;
	}
	
	/**
	 * 添加对象PhmPpa
	 * @param 实体对象
	 */
	public void savePhmPpaList(List<PhmPpa> phmPpaList) {
		for(PhmPpa phmPpa : phmPpaList){
			phmPpa.setOrgNo(OrgUtil.getOrgNoByUser());
			System.out.println("==========================================起始日期："+phmPpa.getBgnDate()+"====结束日期："+phmPpa.getEndDate());
		}
		dao.saveList(phmPpaList);
	}
	
	/**
	 * 更新对象PhmPpa
	 * @param 实体对象
	 */
	@Transactional
	public void updatePhmPpa(PhmPpaVo phmPpaVo) {
		//更新购电合同信息
		PhmPpa phmPpa = phmPpaVo.getPhmPpa();
		System.out.println("==========================================起始日期："+phmPpa.getBgnDate()+"====结束日期："+phmPpa.getEndDate());
		dao.update(phmPpa);
		Map<String,Object> param = new HashMap<>();
        param.put("agreIds", new String[]{phmPpa.getId()});
		//删除合同机组电量信息
		dao.deleteBySql("phmGeneratorMonthPq.sql.deletePhmGeneratorMonthPqByAgreId", param);
		//保存合同机组电量分月信息
		phmGeneratorMonthPqService.savePhmGeneratorMonthPqList(phmPpaVo.getPhmGeneratorMonthPqList());
        //保存合同机组电量分月信息
        phmGenePqDistService.savePhmGenePqDistList(phmPpaVo.getPhmGenePqDistList());
        //删除合同机组电量分配方式信息
        dao.deleteBySql("phmGenePqDist.sql.deletePhmGenePqDistByAgreId", param);
        phmGenePqDistService.savePhmGenePqDistList(phmPpaVo.getPhmGenePqDistList());
		 //保存合同偏差考核规则信息
		PhmAgreDeviation phmAgreDeviation = phmPpaVo.getPhmAgreDeviation();
		phmAgreDeviationService.updatePhmAgreDeviation(phmAgreDeviation);
	}
	
	/**
	 * 删除对象PhmPpa
	 * @param id数据组
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void deletePhmPpa(Map<String, Object> params) throws Exception{
		List<String> ids = (List<String>) params.get("ids");
		String[] a = new String[ids.size()];
		ids.toArray(a);
		//第一次确定删除
//		if(!"true".equals((String)params.get("deleteflag"))){
//			//验证是否有月度结算数据
//			Integer oneBySQL = (Integer) dao.getOneBySQL("phmPpa.sql.check", a);
//			if(oneBySQL > 0 ){
//				throw new RuntimeException("删除合同信息将影响月度结算数据，是否确认删除？");
//			}
//		}
		//删除购电合同信息
		dao.delete(a , PhmPpa.class);
		Map<String,Object> param = new HashMap<>();
		param.put("agreIds", a);
		//删除合同机组电量分月信息
		dao.deleteBySql("phmGeneratorMonthPq.sql.deletePhmGeneratorMonthPqByAgreId", param);
		//删除合同机组电量分配方式信息
		dao.deleteBySql("phmGenePqDist.sql.deletePhmGenePqDistByAgreId", param);
		//删除合同偏差考核规则信息
		dao.deleteBySql("phmAgreDeviation.sql.deletePhmAgreDeviationByAgreId", param);
	}

	/**
	 * 
	 * @Title: getPhcElecInfoList
	 * @Description:(查询电厂信息)
	 * @return
	 * @throws Exception 
	 * List<PhmPpaDetail>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年8月7日 下午5:14:03
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年8月7日 下午5:14:03
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PhmPpaDetail> getPhcElecInfoList() throws Exception {
		Parameter.isFilterData.set(true);
		List<PhmPpaDetail> result = (List<PhmPpaDetail>) dao.getBySql("phmPpa.sql.getPhcElecInfoList", null);
		Parameter.isFilterData.set(false);
		return result;
	}

	/**
	 * 
	 * @Title: getElecCountByParams
	 * @Description:(获取电厂合同数量)
	 * @param phmPpaVo
	 * @return
	 * @throws Exception 
	 * int
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年8月7日 下午8:18:53
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年8月7日 下午8:18:53
	 * @since  1.0.0
	 */
	@Override
	public Integer getElecCountByParams(PhmPpaVo phmPpaVo) throws Exception {
		Parameter.isFilterData.set(true);
		Object obj = dao.getOneBySQL("phmPpa.sql.getElecCountByParams", phmPpaVo);
		Parameter.isFilterData.set(false);
		int total = obj == null ? 0 : Integer.valueOf(obj.toString());
		return total;
	}

	/**
	 * 
	 * @Title: getYearList
	 * @Description:(获取查询参数年)
	 * @return
	 * @throws Exception 
	 * List<Map<String,String>>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年8月8日 上午9:18:02
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年8月8日 上午9:18:02
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getYearList() throws Exception {
		List<Map<String, String>> result = new ArrayList<>();
		Parameter.isFilterData.set(true);
		Map<String,String> year = (Map<String, String>) dao.getOneBySQL("phmPpa.sql.getYearList", null);
		Parameter.isFilterData.set(false);
		int minYear = year.get("minyear") == null ? 0 : Integer.valueOf(year.get("minyear").toString());
        int maxYear = year.get("maxyear") == null ? 0 : Integer.valueOf(year.get("maxyear").toString());
        //获取当前年时间
        Calendar cal = Calendar.getInstance();
        int nowYear = cal.get(Calendar.YEAR);
        if(nowYear < minYear){
        	Map<String, String> map = new HashMap<>();
        	map.put("value", String.valueOf(nowYear));
        	map.put("name", String.valueOf(nowYear));
        	result.add(map);
        }
        for(int i= minYear; i < maxYear + 1;i++ ){
        	Map<String, String> map = new HashMap<>();
        	map.put("value", String.valueOf(i));
        	map.put("name", String.valueOf(i));
        	result.add(map);
        }
        if(nowYear > maxYear){
        	Map<String, String> map = new HashMap<>();
        	map.put("value", String.valueOf(nowYear));
        	map.put("name", String.valueOf(nowYear));
        	result.add(map);
        }
		return result;
	}	
}