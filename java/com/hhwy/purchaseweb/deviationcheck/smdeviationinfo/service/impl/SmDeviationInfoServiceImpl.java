package com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.domain.SmDeviationInfoDetail;
import com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.domain.SmDeviationInfoVo;
import com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.service.SmDeviationInfoService;
import com.hhwy.selling.domain.SmConsPowerView;
import com.hhwy.selling.domain.SmDeviationInfo;

 /**
 * <b>类 名 称：</b>SmDeviationInfoServiceImpl<br/>
 * <b>类 描 述：</b><br/>		偏差预警的service实现类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月19日 下午4:20:34<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class SmDeviationInfoServiceImpl implements SmDeviationInfoService {

	public static final Logger log = LoggerFactory.getLogger(SmDeviationInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * @Title: getSmDeviationInfoByPage
	 * @Description: 分页获取偏差预警对象SmDeviationInfo
	 * @param smDeviationInfoVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午11:25:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午11:25:48
	 * @since  1.0.0
	 */
	public QueryResult<SmDeviationInfoDetail> getSmDeviationInfoByPage(SmDeviationInfoVo smDeviationInfoVo) throws Exception{
		String ym = smDeviationInfoVo.getYm();
		if(ym != null && !"".equals(ym)){
			ym = ym.replace("-", "");
			smDeviationInfoVo.setYm(ym);
		}
		QueryResult<SmDeviationInfoDetail> queryResult = new QueryResult<SmDeviationInfoDetail>();
		long total = getSmDeviationInfoCountByParams(smDeviationInfoVo);
		List<SmDeviationInfoDetail> smDeviationInfoDetailList = getSmDeviationInfoListByParams(smDeviationInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(smDeviationInfoDetailList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmDeviationInfo数量
	 * @param SmDeviationInfoVo
	 * @return Integer
	 */
	public Integer getSmDeviationInfoCountByParams(SmDeviationInfoVo smDeviationInfoVo){
		Object result = dao.getOneBySQL("smDeviationInfo.sql.getSmDeviationInfoCountByParams",smDeviationInfoVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmDeviationInfo列表
	 * @param SmDeviationInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmDeviationInfoDetail> getSmDeviationInfoListByParams(SmDeviationInfoVo smDeviationInfoVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smDeviationInfoVo == null){
			smDeviationInfoVo = new SmDeviationInfoVo();
		}
		Parameter.isFilterData.set(true);
		List<SmDeviationInfoDetail> smDeviationInfoDetailList = (List<SmDeviationInfoDetail>)dao.getBySql("smDeviationInfo.sql.getSmDeviationInfoListByParams",smDeviationInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smDeviationInfoDetailList);
		return smDeviationInfoDetailList;
	}
	
	/**
	 * @Title: getSmDeviationInfoDetailById
	 * @Description: 根据ID获取对象SmDeviationInfoDetail详情，是前台点击列表中查看调用的接口
	 * @param id
	 * @return 
	 * SmDeviationInfoDetail
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 下午2:11:39
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 下午2:11:39
	 * @since  1.0.0
	 */
	public SmDeviationInfoDetail getSmDeviationInfoDetailById(String id) {
		SmDeviationInfoDetail smDeviationInfoDetail = (SmDeviationInfoDetail) dao.getOneBySQL("smDeviationInfo.sql.getSmDeviationInfoDetailById", id);
		BigDecimal agrePq = smDeviationInfoDetail.getAgrePq();
		BigDecimal actualPq = smDeviationInfoDetail.getActualPq();
		//计算剩余电量，如果合同电量与实际用电量不为空则计算
		if(actualPq != null && agrePq != null){
			BigDecimal surplusPq = BigDecimal.ZERO;
			if(agrePq.subtract(actualPq).compareTo(BigDecimal.ZERO) == 1){ //合同 - 实际 > 0 才是有剩余电量，否则剩余电量为0
				surplusPq = agrePq.subtract(actualPq);
			}
			smDeviationInfoDetail.setSurplusPq(surplusPq);
		}
		return smDeviationInfoDetail;
	}	
	
	/**
	 * 添加对象SmDeviationInfo
	 * @param 实体对象
	 */
	public void saveSmDeviationInfoList(List<SmDeviationInfo> smDeviationInfoList) {
		dao.saveList(smDeviationInfoList);
	}
	/**
	 * @Title: getDeviationInfoListByConsIdAndYm
	 * @Description: 根据用户id列表和抄表年月ym获取此超表年月内的偏差预警
	 * @param consId	用户id
	 * @param ym		超表年月  yyyyMM格式
	 * @return
	 * @throws Exception 
	 * List<SmDeviationInfoDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午10:11:16
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午10:11:16
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SmDeviationInfoDetail> getDeviationInfoListByConsIdAndYm(String consId, String ym) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("consId", consId);
		params.put("ym", ym);
		List<SmDeviationInfoDetail> smDeviationInfoDetailList = (List<SmDeviationInfoDetail>)dao.getBySql("smDeviationInfo.sql.getDeviationInfoListByConsIdAndYm",params);
//		ConvertListUtil.convert(smDeviationInfoDetailList);
		return smDeviationInfoDetailList;
	}
	/**
	 * @Title: calculateDeviationByPowerViewList
	 * @Description: 根据用电计划list计算偏差预警，并保存到数据库
	 * 						如果SmConsPowerView中actualPq或者planPq为null，则不计算偏差预警；若isDelete为1也不计算。
	 * @param viewList 同一用户的用电计划列表,不要求顺序
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午4:46:44
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午4:46:44
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public void calculateDeviationByPowerViewList(List<SmConsPowerView> viewList) throws ParseException {
		if(viewList == null || viewList.size() == 0) {
			return;
		}
		SimpleDateFormat formatYmd = new SimpleDateFormat("yyyyMMdd");
		String consId = viewList.get(0).getConsId();
		
		//查询用户偏差预警规则
		List<Map< String, Object >> ruleList = (List<Map<String, Object>>) dao.getBySql("smDeviationInfo.sql.getConsRule",consId);
		if(ruleList == null || ruleList.size() == 0){
			//查询默认偏差预警规则
			Parameter.isFilterData.set(true);
			ruleList = (List<Map<String, Object>>) dao.getBySql("smDeviationInfo.sql.getDefaultRuleSql",null);
			Parameter.isFilterData.set(false);
		}
		if(ruleList == null || ruleList.size() == 0){
			return;
		}
		
		//最终要保存的偏差预警信息
		List<SmDeviationInfo> infoList = new ArrayList<>(); 
		for (SmConsPowerView smConsPowerView : viewList) {
			BigDecimal actualPq = smConsPowerView.getActualPq();
			BigDecimal planPq = smConsPowerView.getPlanPq();
			String isDelete = smConsPowerView.getIsDelete();
			if(actualPq == null || planPq == null || "1".equals(isDelete)) {
				continue;
			}
			
			BigDecimal deviationPq = null;
			BigDecimal deviationProp = BigDecimal.ZERO;
			deviationPq = actualPq.subtract(planPq);
			String warnPrompt = null;
			if(deviationPq.compareTo(BigDecimal.ZERO) == 1 && planPq.doubleValue() != 0){	//正偏差
				deviationProp = deviationPq.multiply(new BigDecimal("100")).divide(planPq, 2, BigDecimal.ROUND_HALF_UP);
				warnPrompt = "01";
			}else if(deviationPq.compareTo(BigDecimal.ZERO) == -1 && planPq.doubleValue() != 0){
				deviationPq = deviationPq.abs();
				deviationProp = deviationPq.multiply(new BigDecimal("100")).divide(planPq, 2, BigDecimal.ROUND_HALF_UP);
				warnPrompt = "02";
			}
			//生效的规则
			Map<String, Object> ruleEffect = null;
			//计算符合偏差预警规则
			for (Map<String, Object> rule : ruleList) {
				//区分正负偏差阈值
				if(warnPrompt.equals(rule.get("warnPrompt").toString()) ) {
					BigDecimal minProp = BigDecimal.ZERO;
					if(rule.get("minProp") != null && rule.get("minProp").toString() != ""){
						minProp = new BigDecimal(rule.get("minProp").toString());
					}
					//阈值倒序排序，区分正负偏差阈值依次做比较
					if(deviationProp.compareTo(minProp) >= 0){
						ruleEffect = rule;
						break;
					}
				}
			}
			
			//如果有相匹配的规则,生成预警信息
			if(ruleEffect != null){
				SmDeviationInfo smDeviationInfo = new SmDeviationInfo();
				smDeviationInfo.setId(PlatformTools.getID());
				smDeviationInfo.setConsId(consId);
				smDeviationInfo.setCompStatus("01");
				smDeviationInfo.setStatus("01");
				Date ymd = formatYmd.parse(smConsPowerView.getYmd());
				smDeviationInfo.setWarningDate(new Timestamp(ymd.getTime()));
				smDeviationInfo.setWarningGrade(ruleEffect.get("warnType").toString());
				smDeviationInfo.setWarningType(ruleEffect.get("warnPrompt").toString());
				infoList.add(smDeviationInfo);
			}
		}
		if(infoList.size() != 0) {
			String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
			String loginUserOrgCode = SystemServiceUtil.getLoginUserInfo().getOrgId();	//4a4caba00e884cbe966e1bd4239dc7e7
			Map<String, Object> addParams = new HashMap<>();
			addParams.put("loginUserId", loginUserId);
			addParams.put("orgNo", loginUserOrgCode);
			addParams.put("addList", infoList);
			//批量保存
			dao.saveBySql("smDeviationInfo.sql.addDeviationInfoList", addParams);
//			dao.saveListBatch(infoList);
		}
	}

	/**
	 * @Title: deleteByConsIdListAndDateRange
	 * @Description: 根据用户id列表和时间区间删除偏差预警
	 * @param consIdList	用户id的List
	 * @param startDate		yyyyMMdd格式
	 * @param endDate 		yyyyMMdd格式
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午4:28:44
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午4:28:44
	 * @since  1.0.0
	 */
	public void deleteByConsIdListAndDateRange(List<String> consIdList, String startDate, String endDate) {
		Map<String, Object> params = new HashMap<>();
		params.put("consIdList", consIdList);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		dao.deleteBySql("smDeviationInfo.sql.deleteByConsIdListAndDateRange", params);
	}
	/**
	 * @Title: deleteByConsIdAndDateRange
	 * @Description: 根据用户id和时间区间删除偏差预警
	 * @param consId	用户id
	 * @param startDate		yyyyMMdd格式
	 * @param endDate 		yyyyMMdd格式
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午4:28:44
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午4:28:44
	 * @since  1.0.0
	 */
	public void deleteByConsIdAndDateRange(String consId, String startDate, String endDate) {
		List<String> consIdList = new ArrayList<>();
		consIdList.add(consId);
		this.deleteByConsIdListAndDateRange(consIdList, startDate, endDate);
	}
	
	/**
	 * @Title: deleteByConsIdAndYmdList
	 * @Description: 根据用户id和时间list删除偏差预警
	 * @param consId	用户id
	 * @param ymdList		yyyyMMdd格式
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午4:28:44
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午4:28:44
	 * @since  1.0.0
	 */
	public void deleteByConsIdAndYmdList(String consId, List<String> ymdList) {
		if(ymdList == null || ymdList.size() == 0) {
			return;
		}
		Map<String, Object> params = new HashMap<>();
		params.put("consId", consId);
		params.put("ymdList", ymdList);
		dao.deleteBySql("smDeviationInfo.sql.deleteByConsIdAndYmdList", params);
	}
	/**
	 * @Title: deletePowerViewByConsIds
	 * @Description: 根据用户id数组删除用户所有预警信息数据（物理删除）,删除用户时用到
	 * @param consIds	数组
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月26日 下午6:00:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月26日 下午6:00:48
	 * @since  1.0.0
	 */
	public void deleteDeviationInfoByConsIds(String[] consIds) {
		dao.deleteBySql("smDeviationInfo.sql.deleteDeviationInfoByConsIds", consIds);
	}
}