package com.hhwy.purchaseweb.archives.consdialog.service;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.consdialog.domain.ConsDialogDetail;
import com.hhwy.purchaseweb.archives.consdialog.domain.ConsDialogVo;
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoDetail;

/**
 * ConsDialogService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface ConsDialogService{
	
	/**
	 * 
	 * @Title: getConsInfo
	 * @Description: 公共组件回显根据用户id查询相关信息
	 * @param scConsumerInfoVo
	 * @return
	 * @throws Exception 
	 * ConsDialogDetail
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年1月19日 下午4:58:43
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年1月19日 下午4:58:43
	 * @since  1.0.0
	 */
	public ConsDialogDetail getConsInfo(String id) throws Exception;
	
	/**
	 * 分页获取用户下拉列表
	 * @param SmPpaVo
	 * @return QueryResult
	 */
	public QueryResult<ConsDialogDetail> getConsList(ConsDialogVo consDialogVo) throws Exception;
	
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
	public QueryResult<ConsDialogDetail> getConsListForMonitor(ConsDialogVo consDialogVo) throws Exception;
	
	/**
     * 分页获取对象ScOrgInfoDetail(供电公司信息detail类)
     * @param scOrgInfoVo       查询条件
     * @return QueryResult<ScOrgInfoDetail>
     */
    public QueryResult<ScOrgInfoDetail> getScOrgInfoByPage(ConsDialogVo consDialogVo) throws Exception;

}