package com.hhwy.purchaseweb.deviationcheck.smconspowerview.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain.AgreConsInfoDetail;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain.SmConsPowerViewDetail;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain.SmConsPowerViewVo;
import com.hhwy.selling.domain.SmConsPowerView;

/**
 * <b>类 名 称：</b>SmConsPowerViewService<br/>
 * <b>类 描 述：</b><br/>		用电计划的service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月17日 下午5:28:37<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface SmConsPowerViewService{
	
	/**
	 * @Title: getSmConsPowerViewByPage
	 * @Description: list页面分页获取用户用电计划
	 * @param smConsPowerViewVo
	 * @return
	 * @throws Exception 
	 * QueryResult<SmConsPowerViewDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午7:15:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午7:15:48
	 * @since  1.0.0
	 */
	public QueryResult<SmConsPowerViewDetail> getSmConsPowerViewByPage(SmConsPowerViewVo smConsPowerViewVo) throws Exception;
	
	/**
	 * @Title: getAgreConsList
	 * @Description: 根据选择年月查询合同用户列表
	 * @param smConsPowerViewVo
	 * @return
	 * @throws Exception 
	 * QueryResult<AgreConsInfoDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午7:35:27
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午7:35:27
	 * @since  1.0.0
	 */
	public QueryResult<AgreConsInfoDetail> getAgreConsList(SmConsPowerViewVo smConsPowerViewVo) throws Exception;
	
	/**
	 * @Title: addSmConsPowerViewByYm
	 * @Description:  电量收集记录新增，即用电计划新增
	 * @param smConsPowerViewVo		用到： ym(yyyyMM格式) 、consIds 、agrePqs 
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午7:53:31
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午7:53:31
	 * @since  1.0.0
	 */
	public void addSmConsPowerViewByYm(SmConsPowerViewVo smConsPowerViewVo) throws Exception;
	
	/**
	 * @Title: getPowerViewByCondIdAndYm
	 * @Description: 根据consId和ym（抄表年月）获取用电计划列表,及偏差预警信息
	 * @param params	consId、ym(yyyyMM格式)
	 * @return
	 * @throws Exception 
	 * Map<String,Object>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午9:20:53
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午9:20:53
	 * @since  1.0.0
	 */
	public Map<String, Object> getPowerViewByCondIdAndYm(Map<String, String> params) throws Exception;
	
	/**
	 * @Title: getSmConsPowerViewDetailList<br/>
	 * @Description: 根据用户id ym(yyyyMM格式)、usuallyDateFlag（是否按抄表例日展示，可为null，默认按照抄表年月查询） 查询用户一个月的用电情况数据<br/>
	 * 		售电根据usuallyDateFlag来判断是根据smConsPowerView中的ymd字段还是ym字段去匹配传入的ym参数获取用户当月全部用电数据，
	 * 		然后获取采集平台数据，并融合每日用电量和累计用电量数据（本地为空取采集，本地不为空以本地为准）。
	 * 		如果是自然月获取的电量数据，则累加日用电量来更新累计用电量，累加日计划电量来更新累计计划电量。
	 * 		最终计算累计偏差率和日偏差率，返回结果
	 * @param params		ym (yyyyMM格式)
	 * @return <br/>
	 * List<SmConsPowerViewDetail><br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月9日 下午6:31:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月9日 下午6:31:00
	 * @throws Exception 
	 * @since  1.0.0
	 */
//	public List<SmConsPowerViewDetail> getSmConsPowerViewDetailList(Map<String, String> params) throws Exception;
	
	/**
	 * @Title: updateSmConsPowerViews
	 * @Description: 更新计划电量，是前台日历页面修改日计划电量（此日之后的所有累计电量会变，所以直接是保存的整月数据）的保存接口
	 * @param smConsPowerViews	整月计划电量信息，有效字段为id、planPq、dayPlanPq
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午10:41:10
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午10:41:10
	 * @since  1.0.0
	 */
	public void updatePlanPq(List<SmConsPowerView> smConsPowerViews) throws Exception;
	
	/**
	 * @Title: deletePlanDataByParams
	 * @Description: 根据用户id 年月删除数据，只是删除其累计计划和日计划电量，并把其isDelete置为1,ym设置为null
	 * 					并删除用户当月的所有预警信息
	 * @param delParams  	格式[{consId : "consId1", ym : "201811" },{consId : "consId2", ym : "201810" }....]
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午11:07:39
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午11:07:39
	 * @since  1.0.0
	 */
	public void deletePlanDataByParams(List<Map<String, String>> delParams) throws Exception;

	/**
	 * @Title: deletePlanDataByYmAndConsIds
	 * @Description: 根据用户id 年月删除数据，只是删除其累计计划和日计划电量，并把其isDelete置为1,ym设置为null
	 * 					并删除用户当月的所有预警信息
	 * @param ym	yyyyMM格式
	 * @param consIds
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月26日 下午6:00:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月26日 下午6:00:48
	 * @since  1.0.0
	 */
	public void deletePlanDataByYmAndConsIds(String ym, List<String> consIds) throws Exception;
	/**
	 * 
	 * sendMessage(用户上报实际用电量提醒),老版本遗留代码<br/>
	 * @param params
	 * @return
	 * @throws Exception 
	 * Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public String sendMessage(String consId) throws Exception;
	
	/**
	 * 添加对象SmConsPowerView列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmConsPowerViewList(List<SmConsPowerView> smConsPowerViewList);
	
	
	
	
	/**
	 * @Title: updateActualPqByMonth
	 * @Description: 	按月更新日用电量和累计用电量两个字段，不需要考虑更新一条数据是否影响之后的数据变化
	 * 					haveActualPqFlag为false. 即powerViewList中只有日用电量(可能为null)，累计用电量需要结合数据库中数据统一计算
	 * 						（从数据库获取powerViewList第一条数据时间的并且是本ym抄表年月的前一个累计电量，计算每日的累计用电量）
	 * 					haveActualPqFlag为true. 即powerViewList中有累计用电量(可能为null)和日用电量(可能为null)
	 * @param consId
	 * @param ym
	 * @param powerViewList 时间增序排列，ymd日期是连续的
	 * @param haveActualPqFlag 		数据类型，true：有累计用电量(可能为null)和日用电量(可能为null)；  false：没有累计用电量(为null)，需要重新计算累计用电量
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 下午5:32:49
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 下午5:32:49
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	public void updateActualPqByMonth(String consId, String ym, List<SmConsPowerView> powerViewList,boolean haveActualPqFlag) throws ParseException;
	
	/**
	 * @Title: updateActualPqByYmds
	 * @Description: 	按日更新日用电量和累计用电量两个字段，需要考虑更新一条数据是否影响之后的数据变化
	 * 					powerViewList中只有累计用电量(可能为null)，日用电量需要结合数据库中数据统一计算
	 * 						（根据ym从数据库获取本抄表年月所有用电计划，计算每日的日用电量）
	 * @param consId
	 * @param ym	yyyyMM格式
	 * @param startDate 本抄表月的开始日期
	 * @param powerViewList 时间增序排列，ymd日期是连续的
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 下午5:32:49
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 下午5:32:49
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	public void updateActualPqByYmds(String consId, String ym,String startDate, List<SmConsPowerView> powerViewList) throws ParseException ;
	
	/**
	 * @Title: rebuildPowerViewByMonth
	 * @Description: 	按月重建本月用电计划，并重新计算偏差预警
	 * 						若本月数据是删除状态（isdelete是1）或没有本月数据，则先根据ym删除本月数据及偏差预警，然后清除计划电量两字段值，
	 * 								变更powerViewList中的isDelete为1，保存存在实际用电量值的powerView，无需重新计算偏差预警
	 * 						若本月数据不是删除状态（isdelete是0），则先根据ym删除本月数据及偏差预警，保存powerViewList，并重新计算偏差预警
	 * 					注，为防止特殊情况的垃圾数据，在根据ym删除本月数据的基础上也会根据powerViewList的最大最小时间区间删除数据
	 * @param consId
	 * @param ym
	 * @param powerViewList 时间增序排列，ymd日期是连续的，并且是整月的数据，默认是未删除状态，以数据库状态为准
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 下午5:32:49
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 下午5:32:49
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	public void rebuildPowerViewByMonth(String consId, String ym, List<SmConsPowerView> powerViewList) throws ParseException;

	/**
	 * @Title: deletePowerViewByConsIds
	 * @Description: 根据用户id数组删除用电计划数据（物理删除），并删除用户当月的所有预警信息,删除用户时用到
	 * @param consIds	数组
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月26日 下午6:00:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月26日 下午6:00:48
	 * @since  1.0.0
	 */
	public void deletePowerViewByConsIds(String[] consIds);
	
	/**
	 * @Title: getSmConsPowerViewDetailList<br/>
	 * @Description: 根据用户id ym、usuallyDateFlag（是否按抄表例日展示） 查询用户一个月的用电情况数据，监控平台的用户电量页面用到
	 * 		售电根据usuallyDateFlag来判断是根据smConsPowerView中的ymd字段还是ym字段去匹配传入的ym参数获取用户当月全部用电数据，
	 * 		如果是自然月获取的电量数据，则累加日用电量来更新累计用电量，累加日计划电量来更新累计计划电量。
	 * 		最终计算累计偏差率和日偏差率，返回结果
	 * @param params	consId,ym（yyyyMM格式）,usuallyDateFlag (是否安装抄表年月查询)
	 * @return <br/>
	 * List<SmConsPowerViewDetail><br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月9日 下午6:31:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年01月08日 下午1:05:10
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public List<SmConsPowerViewDetail> getSmConsPowerViewDetailList(Map<String, String> params) throws Exception;
	
	/**
	 * @Title: getSmConsPowerViewByPage2<br/>
	 * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月所有合同用户用电情况<br/>
	 * @param SmConsPowerViewVo params 中 ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级
	 * @return <br/>
	 * QueryResult<SmConsPowerViewDetail><br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月12日 下午1:15:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月12日 下午1:15:00
	 * @since  1.0.0
	 */
	public QueryResult<SmConsPowerViewDetail> getSmConsPowerViewByPage2(SmConsPowerViewVo smConsPowerViewVo) throws Exception;
	
	/**
	 * @Title: getPowerViewDetailListByMonth<br/>
	 * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月合同用户用电情况<br/>
	 * @param SmConsPowerViewVo params 中 ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级
	 * @return <br/>
	 * List<SmConsPowerViewDetail><br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月12日 下午1:15:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月12日 下午1:15:00
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public List<SmConsPowerViewDetail> getPowerViewDetailListByMonth(SmConsPowerViewVo params) throws Exception;
	
	/**
	 * @Title: getTotalPqDataByMonth
	 * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月合同用户总的计划电量和总用电量
	 * @param smConsPowerViewVo
	 * @return planPqTotal:当月计划电量总和    actualPqTotal；当月用电量总和	forecastPq：预测全月电量（新增 ——by LiXinze）
	 * Map<String,String>
	 * @throws Exception 
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月23日 下午4:37:20
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年3月23日 下午4:37:20
	 * @since  1.0.0
	 */
	public Map<String, String> getTotalPqDataByMonth(SmConsPowerViewVo smConsPowerViewVo) throws Exception;
	/**
	 * @Title: getPowerViewDetailListForDayByMonth<br/>
	 * @Description: 根据用户ym(抄表年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月合同用户每天的总用电情况
	 * 							返回数据为每天一条，每条为所有用户当天的用电加和<br/>
	 * @param SmConsPowerViewVo params 中 ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级
	 * @return <br/>
	 * List<SmConsPowerViewDetail><br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月13日 下午1:15:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月13日 下午1:15:00
	 * @since  1.0.0
	 */
	public List<SmConsPowerViewDetail> getPowerViewDetailListForDayByMonth(SmConsPowerViewVo params);
	/**
	  * @Title: exportExcel
	  * @Description: 用电量导出
	  * @param voltCode
	  * @param elecTypeCode
	  * @param month
	  * @param request
	  * @param response
	  * void
	  * @throws Exception 
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年11月19日 上午9:28:44
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年11月19日 上午9:28:44
	  * @since  1.0.0
	 */
	public void exportExcel(String voltCode, String elecTypeCode, String month, HttpServletRequest request, HttpServletResponse response) throws Exception;
}