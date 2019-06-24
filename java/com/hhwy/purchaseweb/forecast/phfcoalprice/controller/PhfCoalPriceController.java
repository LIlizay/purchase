package com.hhwy.purchaseweb.forecast.phfcoalprice.controller;

import java.util.List;

import com.hhwy.purchaseweb.forecast.phfcoalprice.service.PhfCoalPriceService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhfCoalPrice;
import com.hhwy.purchaseweb.forecast.phfcoalprice.domain.PhfCoalPriceDetail;
import com.hhwy.purchaseweb.forecast.phfcoalprice.domain.PhfCoalPriceVo;
import com.hhwy.purchaseweb.forecast.phfcoalprice.domain.SaveCoalPriceVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhfCoalPriceController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phfCoalPriceController")
public class PhfCoalPriceController {

	public static final Logger log = LoggerFactory.getLogger(PhfCoalPriceController.class);
	
	/**
	 * phfCoalPriceService
	 */
	@Autowired
	private PhfCoalPriceService phfCoalPriceService;
	
	
	/**
	 * 分页获取对象PhfCoalPrice
	 * @param ID
	 * @return PhfCoalPrice
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhfCoalPriceByPage(@RequestBody(required=false) PhfCoalPriceVo phfCoalPriceVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhfCoalPriceDetail> queryResult = phfCoalPriceService.getPhfCoalPriceByPage(phfCoalPriceVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("分页查询列表失败！");			//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 获取列表
	 * getPhfCoalPriceListByParams(描述这个方法的作用)<br/>
	 * @param phfCoalPriceVo
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getList", method = RequestMethod.POST)
    public Object getPhfCoalPriceListByParams(@RequestBody(required=false) PhfCoalPriceVo phfCoalPriceVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            phfCoalPriceVo.setPagingFlag(false);
            List<PhfCoalPriceDetail> list = phfCoalPriceService.getPhfCoalPriceListByParams(phfCoalPriceVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(list);
            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("分页查询列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(new Object[]{});                     //设置返回结果为空
            result.setMsg("分页查询列表失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
	
	/**
	 * 根据ID获取对象PhfCoalPrice
	 * @param ID
	 * @return PhfCoalPrice
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhfCoalPriceById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhfCoalPrice phfCoalPrice = phfCoalPriceService.getPhfCoalPriceById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phfCoalPrice);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}	
	
	/**
	 * 添加对象PhfCoalPrice
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhfCoalPrice(@RequestBody SaveCoalPriceVo saveCoalPriceVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfCoalPriceService.savePhfCoalPrice(saveCoalPriceVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！"+e.getMessage());		//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象PhfCoalPrice
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhfCoalPrice(@RequestBody PhfCoalPrice phfCoalPrice) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfCoalPriceService.updatePhfCoalPrice(phfCoalPrice);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 删除对象PhfCoalPrice
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhfCoalPrice(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
		    String[] ids = id.split(",");
			phfCoalPriceService.deletePhfCoalPrice(ids);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}	
}