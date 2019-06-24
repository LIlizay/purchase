package com.hhwy.purchaseweb.deviationcheck.scmpcount.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.domain.ScMpCountDetail;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.domain.ScMpCountVo;

/**
 * <b>类 名 称：</b>ScMpCountService<br/>
 * <b>类 描 述：</b><br/>		表计示数的service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月13日 下午3:33:07<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface ScMpCountService{
	
	/**
	 * @Title: getScMpCountByPage
	 * @Description: 分页获取对象ScMpCount
	 * @param scMpCountVo
	 * @return
	 * @throws Exception 
	 * QueryResult<ScMpCount>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午3:35:49
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午3:35:49
	 * @since  1.0.0
	 */
	public QueryResult<ScMpCountDetail> getScMpCountByPage(ScMpCountVo scMpCountVo) throws Exception;

	/**
	 * 根据查询条件获取对象ScMpCount列表
	 * @param ScMpCountVo
	 * @return List
	 */
	public List<ScMpCountDetail> getScMpCountListByParams(ScMpCountVo scMpCountVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象ScMpCount数量
	 * @param ScMpCountVo
	 * @return Integer
	 */
	public Integer getScMpCountCountByParams(ScMpCountVo scMpCountVo);
	
	/**
	 * @Title: getScMpCountListById
	 * @Description: 根据 电表id(即计量点id) 分页获取表计示数列表, 在edit和detail的 可编辑/展示 表格处用到
	 * @param scMpCountVo
	 * @return 
	 * QueryResult<ScMpCount>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午5:14:23
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午5:14:23
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public QueryResult<ScMpCountDetail> getScMpCountListById(ScMpCountVo scMpCountVo) throws Exception;
	/**
	 * @Title: saveChangeCountVo
	 * @Description: 批量增删改ScMpCount， 然后修改影响到的用电计划
	 * @param changeList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午3:59:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午3:59:48
	 * @since  1.0.0
	 */
	public void saveChangeCountVo(ScMpCountVo changeListVo) throws Exception ;
	
	/**
	 * @Title: rebuildPowerViewByYmList
	 * @Description: 重新构建多个月的用电计划，并计算偏差预警
	 * 					方案：
	 * @param consId
	 * @param ymList
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 上午10:34:02
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 上午10:34:02
	 * @since  1.0.0
	 */
	public void rebuildPowerViewByYmList(String consId, List<String> ymList) throws Exception;
	
	/**
	 * @Title: deleteScMpCountAndOthersByConsId
	 * @Description: 根据用户id数组删除所有表计示数、删除用电计划、删除偏差预警，删除用户时调用
	 * @param consIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月10日 下午7:04:23
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月10日 下午7:04:23
	 * @since  1.0.0
	 */
	public void deleteScMpCountAndOthersByConsId(String[] consIds);
	/**
	 * @Title: getScMpCountCountByMpInfoId
	 * @Description: 根据计量点id获取其下的抄表示数数据行数,在删除计量点时判断是否有表计示数时用
	 * @param mpInfoId
	 * @return 
	 * long
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月20日 下午6:59:03
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月20日 下午6:59:03
	 * @since  1.0.0
	 */
	public long getScMpCountCountByMpInfoId(String mpInfoId);
}