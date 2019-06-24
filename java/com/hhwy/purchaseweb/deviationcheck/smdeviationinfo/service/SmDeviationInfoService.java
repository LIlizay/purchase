package com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.service;

import java.text.ParseException;
import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.domain.SmDeviationInfoDetail;
import com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.domain.SmDeviationInfoVo;
import com.hhwy.selling.domain.SmConsPowerView;
import com.hhwy.selling.domain.SmDeviationInfo;

/**
 * <b>类 名 称：</b>SmDeviationInfoService<br/>
 * <b>类 描 述：</b><br/>		偏差预警的service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月19日 下午4:21:03<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface SmDeviationInfoService{
	
	/**
	 * 分页获取对象SmDeviationInfo
	 * @param SmDeviationInfoVo
	 * @return QueryResult
	 */
	public QueryResult<SmDeviationInfoDetail> getSmDeviationInfoByPage(SmDeviationInfoVo smDeviationInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmDeviationInfo列表
	 * @param SmDeviationInfoVo
	 * @return List
	 */
	public List<SmDeviationInfoDetail> getSmDeviationInfoListByParams(SmDeviationInfoVo smDeviationInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmDeviationInfo数量
	 * @param SmDeviationInfoVo
	 * @return Integer
	 */
	public Integer getSmDeviationInfoCountByParams(SmDeviationInfoVo smDeviationInfoVo);
	
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
	public SmDeviationInfoDetail getSmDeviationInfoDetailById(String id);
	
	/**
	 * 添加对象SmDeviationInfo列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmDeviationInfoList(List<SmDeviationInfo> smDeviationInfoList);
	
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
	public List<SmDeviationInfoDetail> getDeviationInfoListByConsIdAndYm(String consId, String ym) throws Exception;
	
	/**
	 * @Title: calculateDeviationByPowerViewList
	 * @Description: 根据用电计划list计算偏差预警，并保存到数据库
	 * 						如果SmConsPowerView中actualPq或者planPq为null，则不计算偏差预警；若isDelete为1也不计算。
	 * @param viewList 同一用户的用电计划列表
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午4:46:44
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午4:46:44
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	public void calculateDeviationByPowerViewList(List<SmConsPowerView> viewList) throws ParseException;
	
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
	public void deleteByConsIdListAndDateRange(List<String> consIdList, String startDate, String endDate);
	
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
	public void deleteByConsIdAndDateRange(String consId, String startDate, String endDate);
	
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
	public void deleteByConsIdAndYmdList(String consId, List<String> ymdList);
	
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
	public void deleteDeviationInfoByConsIds(String[] consIds);

}