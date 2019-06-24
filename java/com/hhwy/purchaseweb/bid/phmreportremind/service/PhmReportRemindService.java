package com.hhwy.purchaseweb.bid.phmreportremind.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmReportRemind;
import com.hhwy.purchaseweb.bid.phmreportremind.domain.PhmReportRemindVo;

/**
 * IPhmReportRemindService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmReportRemindService{
	
	/**
	 * 分页获取对象PhmReportRemind
	 * @param PhmReportRemindVo
	 * @return QueryResult
	 */
	public QueryResult<PhmReportRemind> getPhmReportRemindByPage(PhmReportRemindVo phmReportRemindVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmReportRemind列表
	 * @param PhmReportRemindVo
	 * @return List
	 */
	public List<PhmReportRemind> getPhmReportRemindListByParams(PhmReportRemindVo phmReportRemindVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmReportRemind数量
	 * @param PhmReportRemindVo
	 * @return Integer
	 */
	public Integer getPhmReportRemindCountByParams(PhmReportRemindVo phmReportRemindVo);
	
	/**
	 * 根据查询条件获取单个对象PhmReportRemind
	 * @param PhmReportRemindVo
	 * @return PhmReportRemind
	 */
	public PhmReportRemind getPhmReportRemindOneByParams(PhmReportRemindVo phmReportRemindVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmReportRemind
	 * @param ID
	 * @return PhmReportRemind
	 */
	public PhmReportRemind getPhmReportRemindById(String id);
	
	/**
	 * 添加对象PhmReportRemind
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmReportRemind(PhmReportRemind phmReportRemind);
	
	/**
	 * 更新对象PhmReportRemind
	 * @param 实体对象
	 * @return PhmReportRemind
	 */
	public void updatePhmReportRemind(PhmReportRemind phmReportRemind);
	
	/**
	 * 删除对象PhmReportRemind
	 * @param id数据组
	 */
	public void deletePhmReportRemind(String[] ids);
	
	/**
	 * @Title: savePhmReportRemindList<br/>
	 * @Description: 保存提示信息<br/>
	 * @param phmReportRemindVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月19日 下午4:34:11
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月19日 下午4:34:11
	 * @since  1.0.0
	 */
	public void savePhmReportRemindList(PhmReportRemindVo phmReportRemindVo);
}