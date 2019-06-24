package com.hhwy.purchaseweb.contract.smppa.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.contract.smppa.domain.SmPpaDetail;
import com.hhwy.purchaseweb.contract.smppa.domain.SmPpaVo;
import com.hhwy.selling.domain.SmPpa;

/**
 * ISmPpaService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmPpaService{
	
	/**
	 * 分页获取对象SmPpa
	 * @param SmPpaVo
	 * @return QueryResult
	 */
	public QueryResult<SmPpaDetail> getSmPpaByPage(SmPpaVo smPpaVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmPpa列表
	 * @param SmPpaVo
	 * @return List
	 */
	public List<SmPpaDetail> getSmPpaListByParams(SmPpaVo smPpaVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmPpa数量
	 * @param SmPpaVo
	 * @return Integer
	 */
	public Integer getSmPpaCountByParams(SmPpaVo smPpaVo);
	
	/**
	 * 根据查询条件获取单个对象SmPpa
	 * @param SmPpaVo
	 * @return SmPpa
	 */
	public SmPpaDetail getSmPpaOneByParams(SmPpaVo smPpaVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmPpa
	 * @param ID
	 * @return SmPpa
	 */
	public SmPpaVo getSmPpaById(String id) throws Exception ;
	
	/**
	 * 添加对象SmPpa
	 * @param 实体对象
	 * @return null
	 */
	public SmPpaVo saveSmPpa(SmPpaVo smPpaVo);
	
	/**
	 * 更新对象SmPpa
	 * @param 实体对象
	 * @return SmPpa
	 */
	public void updateSmPpa(SmPpaVo smPpaVo) throws Exception ;
	
	/**
	 * 删除对象SmPpa
	 * @param id数据组
	 */
	public void deleteSmPpa(String[] ids);
	
	/**
	 * 
	 * @Title: getPpaConsId
	 * @Description: 获取当前年月签订购电合同的用户
	 * @return 
	 * String[]
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年2月24日 下午3:12:08
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年2月24日 下午3:12:08
	 * @since  1.0.0
	 */
	public List<SmPpa> getPpaConsId(String ym);

	/**
	 * 
	 * @Title: getLcConsId
	 * @Description: TODO(获取当前年月签订购电合同的用户)
	 * @param year
	 * @return 
	 * List<Map>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年3月8日 上午9:53:13
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年3月8日 上午9:53:13
	 * @since  1.0.0
	 */
/*	@SuppressWarnings("rawtypes")
	public List<Map> getLcConsId(String year);*/
	
	/**
	 * 根据 year 查询 新增长协购电计划页面 表单，表格数据
	 */
	public Map<String, Object> perMonthData(int year);
	
	/**
	 * 提交SmPpa
	 */
	public void approvalSmPpa(SmPpa smPpa);

	/**
	  * @Title: getSmPpaOneByParams2
	  * @Description: 用户合同有效期判断
	  * @param param
	  * @return
	  * SmPpaDetail
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月10日 下午8:28:59
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月10日 下午8:28:59
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public SmPpaDetail getSmPpaOneByParams2(SmPpaVo param) throws Exception;

	/**
	  * @Title: getImplementation
	  * @Description: 根据ID获取对象SmPpa
	  * @param id
	  * @return
	  * List<SmPpaDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月17日 下午1:51:35
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月17日 下午1:51:35
	  * @since  1.0.0
	 */
	public List<SmPpaDetail> getImplementation(String id);

	/**
	  * @Title: getSmPpaByIds
	  * @Description: 根据id数组获取SmPpa列表
	  * @param ids
	  * @return List<SmPpa>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年12月15日 下午1:25:33
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年12月15日 下午1:25:33
	  * @since  1.0.0
	 */
	public List<SmPpa> getSmPpaByIds(String[] ids);
		/**
		 * 
		 * @Title: getForecast<br/>
		 * @Description:TODO(负荷预测)<br/>
		 * @param smPpa
		 * @return
		 * @throws Exception
		 * List<Map<String,Object>>
		 * @throws Exception <br/>
		 * QueryResult<TreeGridDetail><br/>
		 * <b>创 建 人：</b>zhagnzhao<br/>
		 * <b>创建时间:</b>2018年3月12日 下午5:01:24
		 * <b>修 改 人：</b>zhangzhao<br/>
		 * <b>修改时间:</b>2018年3月12日 下午5:01:24
		 * @since  1.0.0
		 */
	public Map<String, Object> getForecast(SmPpa smPpa) throws Exception;

	/**
	 * @Title: forecast<br/>
	 * @Description:TODO(查询当前用户有无负荷预测权限)<br/>
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年4月10日 下午5:31:31
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年4月10日 下午5:31:31
	 * @since  1.0.0
	 */
	public String getForecastRes();

	/**
	  * @Title: getDeleteRelatedById
	  * @Description: 根据合同id获取月度购电管理、实际用电量录入、月度收益结算的关联关系，为数据删除做提醒
	  * @param id
	  * @return String
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年4月14日 下午1:57:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年4月14日 下午1:57:31
	  * @since  1.0.0
	 */
	public String getDeleteRelatedById(String id);

	/**
	  * @Title: deleteSmPpaById
	  * @Description: 根据合同id删除非草稿状态的合同
	  * @param id
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年4月14日 下午3:55:37
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年4月14日 下午3:55:37
	  * @since  1.0.0
	 */
	public void deleteSmPpaById(String id);

}