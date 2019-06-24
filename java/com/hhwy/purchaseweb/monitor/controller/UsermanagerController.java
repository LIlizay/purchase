package com.hhwy.purchaseweb.monitor.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.purchaseweb.monitor.domain.Usermanager;
import com.hhwy.purchaseweb.monitor.service.UsermanagerService;


/**
 * UsermanagerController
 * @author wangchaochao
 * @date 2017-08-24
 * @version 1.0
 */
@RestController
@RequestMapping("/usermanager")
public class UsermanagerController{

	public static final Logger log = LoggerFactory.getLogger(UsermanagerController.class);
	
	/**
	 * usermanagerService
	 */
	@Autowired
	private UsermanagerService usermanagerService;
	
	/**
	 * @Title: exportPower
	 * @Description: 导出表格数据
	 * @param param
	 * @return
	 * @throws Exception 
	 * List<Usermanager>
	 * <b>创 建 人：</b>wangchaochao<br/>
	 * <b>创建时间:</b>2017年8月25日 下午3:11:32
	 * <b>修 改 人：</b>wangchaochao<br/>
	 * <b>修改时间:</b>2017年8月25日 下午3:11:32
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/exportPower", method = RequestMethod.GET)
	public List<Usermanager> exportPower(@RequestParam Map<String, Object> param) throws Exception {
		return  usermanagerService.exportPower(param);
	}
	
	/**
	 * @Title: getOnePpaConsumerID
	 * @Description: 查询一个ym当月为合同用户的用户
	 * @param ym    yyyy-MM 格式
	 * @return
	 * @throws Exception 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月23日 下午8:22:59
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月23日 下午8:22:59
	 * @since  1.0.0
	 */
	@RequestMapping(value="/getOnePpaConsumerID",method = RequestMethod.GET)
	public Object getOnePpaConsumerID(String ym) throws Exception{
		return usermanagerService.getOnePpaConsumerID(ym);
	}
}