package com.hhwy.purchaseweb.monitor.service;

import java.util.List;
import java.util.Map;

import com.hhwy.purchaseweb.monitor.domain.Usermanager;

/**
 * IUsermanagerService
 * @author wangchaochao
 * @date 2017-08-24
 * @version 1.0
 */
public interface UsermanagerService{
	
	/**
	 * @Title: exportPower
	 * @Description: 导出表格数据
	 * @param param
	 * @return
	 * @throws Exception 
	 * List<Usermanager>
	 * <b>创 建 人：</b>wangchaochao<br/>
	 * <b>创建时间:</b>2017年8月25日 下午3:11:56
	 * <b>修 改 人：</b>wangchaochao<br/>
	 * <b>修改时间:</b>2017年8月25日 下午3:11:56
	 * @since  1.0.0
	 */
	public List<Usermanager> exportPower(Map<String, Object> param) throws Exception;

	/**
	 * @Title: getOnePpaConsumerID
	 * @Description: 查询一个ym当月为合同用户的用户
	 * @param ym    yyyy-MM 格式
	 * @return
	 * @throws Exception 
	 * String
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月23日 下午8:20:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月23日 下午8:20:20
	 * @since  1.0.0
	 */
	public String getOnePpaConsumerID(String ym) throws Exception;
}