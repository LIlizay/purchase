package com.hhwy.purchaseweb.plan.phmlcdist.controller;

import java.util.List;
import java.util.Map;

import com.hhwy.purchaseweb.plan.phmlcdist.service.PhmLcDistService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmLcDist;
import com.hhwy.purchaseweb.plan.phmlcdist.domain.PhmLcDistVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmLcDistController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmLcDistController")
public class PhmLcDistController {

	public static final Logger log = LoggerFactory.getLogger(PhmLcDistController.class);
	
	/**
	 * phmLcDistService
	 */
	@Autowired
	private PhmLcDistService phmLcDistService;
	
	
	/**
	 * 分页获取对象PhmLcDist
	 * @param ID
	 * @return PhmLcDist
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmLcDistByPage(@RequestBody(required=false) PhmLcDistVo phmLcDistVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmLcDist> queryResult = phmLcDistService.getPhmLcDistByPage(phmLcDistVo);
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
	 * 根据ID获取对象PhmLcDist
	 * @param ID
	 * @return PhmLcDist
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmLcDistById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmLcDist phmLcDist = phmLcDistService.getPhmLcDistById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmLcDist);			//设置数据实体
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
	 * 
	 * getPhmLcDistByPage(描述这个方法的作用)<br/>
	 * @param phmLcDistVo
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getAgreMonthPq", method = RequestMethod.GET)
    public Object getAgreMonthPq(@RequestParam String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            List<PhmLcDist> list= phmLcDistService.getAgreMonthPq(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setTotal(list.size());    //设置数据总条数
            result.setRows(list);      //设置数据列表
            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("分页查询列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setRows(new Object[]{});                     //设置返回结果为空
            result.setTotal(0);                         //设置数据总条数为0
            result.setMsg("分页查询列表失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
	
	/**
	 * 添加对象PhmLcDist数组
	 * @param 实体对象
	 */
	@RequestMapping( value = "/saveList", method = RequestMethod.POST)
	public Object savePhmLcDist(@RequestBody List<PhmLcDist> phmLcDistList) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmLcDistService.savePhmLcDistList(phmLcDistList);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	@RequestMapping( method = RequestMethod.POST)
    public Object savePhmLcDist(@RequestBody PhmLcDist phmLcDist) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmLcDistService.savePhmLcDist(phmLcDist);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("保存成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("保存失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("保存失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
        
    }
	
	/**
	 * 更新对象PhmLcDist
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmLcDist(@RequestBody PhmLcDist phmLcDist) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmLcDistService.updatePhmLcDist(phmLcDist);
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
	 * 删除对象PhmLcDist
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmLcDist(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmLcDistService.deletePhmLcDist(new String[]{id});
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
	
	/**
	 * 查询追踪页面
	 * getTrackInfo(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
    @RequestMapping( value = "/getTrackInfo", method = RequestMethod.GET)
    public Object getTrackInfo(@RequestParam String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            List<Map<String,Object>> list= phmLcDistService.getTrackInfo(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setTotal(list.size());    //设置数据总条数
            result.setRows(list);      //设置数据列表
            result.setMsg("查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("查询列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setRows(new Object[]{});                     //设置返回结果为空
            result.setTotal(0);                         //设置数据总条数为0
            result.setMsg("查询列表失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
}