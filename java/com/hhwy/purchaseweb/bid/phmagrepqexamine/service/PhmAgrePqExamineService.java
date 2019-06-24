package com.hhwy.purchaseweb.bid.phmagrepqexamine.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.PhmAgrePqExamineDetail;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.PhmAgrePqExamineVo;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.YearPlanProxyPqDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanConsRelaDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmSubMonthDetail;

/**
 * IPhmAgrePqExamineService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmAgrePqExamineService{

	/**
	  * @Title: getExamineDetailListByPlanId
	  * @Description: 根据planId获取月交易电量审核用户
	  * @param phmAgrePqExamineVo
	  * @return QueryResult<PhmAgrePqExamineDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月23日 下午9:12:03
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月23日 下午9:12:03
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public QueryResult<PhmAgrePqExamineDetail> getExamineDetailListByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception;

	/**
	 * @Title: getYearPlanProxyPqDetailByParams
	 * @Description: 根据year和consId获取该用户的所有年交易电量审核分月电量信息，用于提供月购电计划的委托电量审核第二个表格“年度交易委托电量分月信息”的数据
	 * @param year
	 * @param consId
	 * @return
	 * @throws Exception 
	 * List<YearPlanProxyPqDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年3月1日 上午11:34:43
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年3月1日 上午11:34:43
	 * @since  1.0.0
	 */
	public List<YearPlanProxyPqDetail> getYearPlanProxyPqDetailByParams(String year, String consId) throws Exception;
	
	/**
	  * @Title: saveAgrePqs
	  * @Description: 保存页面填写的委托电量与计划
	  * @param phmAgrePqExamineVo
	  * @return String
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午1:53:45
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午1:53:45
	  * @since  1.0.0
	 */
	public String saveAgrePqs(PhmAgrePqExamineVo phmAgrePqExamineVo);

	/**
	 * 
	 * @Title: getExamineDetailIsNullByPlanId<br/>
	 * @Description: 查询计划下是否有未填写合同电量的用户<br/>
	 * @param planId
	 * @return <br/> String<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月19日 下午7:32:08
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月19日 下午7:32:08
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public String getExamineDetailIsNullByPlanId(String planId) throws Exception;

	/**
	  * @Title: getForecastResName
	  * @Description: 获取负荷预测权限
	  * @return String
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午3:38:22
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午3:38:22
	  * @since  1.0.0
	 */
	public String getForecastResName();

	/**
	 * 
	 * @Title: remindReportPq
	 * @Description:(重报信息提醒)
	 * @param phmAgrePqExamineVo
	 * @return  Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年9月5日 上午10:35:40
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年9月5日 上午10:35:40
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public void remindReportPq(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception;

	/**
	  * @Title: getExamineDetailYearListByPlanId
	  * @Description: 根据planId获取年交易电量审核用户
	  * @param phmAgrePqExamineVo
	  * @return
	  * QueryResult<PhmAgrePqExamineDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午6:10:38
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午6:10:38
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public QueryResult<PhmAgrePqExamineDetail> getExamineDetailYearListByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception;

	/**
	  * @Title: getPhmAgreExamineByPlanId
	  * @Description: 根据planId分页获取用户列表，计划制定编辑用
	  * @param phmPlanConsRelaVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月22日 下午3:18:25
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月22日 下午3:18:25
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public QueryResult<PhmPurchasePlanConsRelaDetail> getPhmAgreExamineByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception;
	
	/**
	  * @Title: getPurchasePlanConsCountByPlanId
	  * @Description: getphmPlanConsRelaCountByPlanId
	  * @param phmAgrePqExamineVo
	  * @return long
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月22日 下午3:42:22
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月22日 下午3:42:22
	  * @since  1.0.0
	 */
	public long getPurchasePlanConsCountByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo);
	
	/**
	 * @Title: getPlanConsListPageByPlanId
	 * @Description: 通过planId及pagingFlag等分页属性获取phmAgrePqExamine
	 * @param phmAgrePqExamineVo
	 * @return 
	 * List<PhmPurchasePlanConsRelaDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月16日 下午4:43:06
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月16日 下午4:43:06
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public List<PhmPurchasePlanConsRelaDetail> getPlanConsListPageByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception;
	
	/**
	  * @Title: getSubMonthDetailByPlanId
	  * @Description: 根据计划id获取用户年度分月信息列表
	  * @param phmAgrePqExamineVo
	  * @return List<PhmSubMonthDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月25日 下午3:33:53
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月25日 下午3:33:53
	  * @since  1.0.0
	 */
	public List<PhmSubMonthDetail> getSubMonthDetailByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo);

	
	/**
	 * @Title: saveMessage<br/>
	 * @Description: 保存提示信息<br/>
	 * @param phmAgrePqExamineVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月30日 下午7:29:28
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月30日 下午7:29:28
	 * @since  1.0.0
	 */
	public void saveMessage(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception;

}