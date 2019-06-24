package com.hhwy.purchaseweb.bid.phmeleclist.controller;

import java.util.List;

import com.hhwy.purchaseweb.bid.phmeleclist.service.PhmElecListService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmElecList;
import com.hhwy.purchaseweb.bid.phmeleclist.domain.ElecListDetail;
import com.hhwy.purchaseweb.bid.phmeleclist.domain.PhmElecListDetail;
import com.hhwy.purchaseweb.bid.phmeleclist.domain.PhmElecListVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmElecListController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmElecListController")
public class PhmElecListController {

	public static final Logger log = LoggerFactory.getLogger(PhmElecListController.class);
	
	/**
	 * phmElecListService
	 */
	@Autowired
	private PhmElecListService phmElecListService;
	
	
	/**
	 * 分页获取对象PhmElecList
	 * @param ID
	 * @return PhmElecList
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmElecListByPage(@RequestBody(required=false) PhmElecListVo phmElecListVo) {
		ExecutionResult result = new ExecutionResult();
		try {
		    phmElecListVo.setPagingFlag(false);
			QueryResult<PhmElecListDetail> queryResult = phmElecListService.getPhmElecListByPage(phmElecListVo);
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
     * 分页获取对象PhmElecList
     * @param ID
     * @return PhmElecList
     */
    @RequestMapping( value = "/phmElecListDetailPage", method = RequestMethod.POST)
    public Object getElecListDetailListByPage(@RequestBody(required=false) PhmElecListVo phmElecListVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<ElecListDetail> queryResult = phmElecListService.getElecListDetailListByPage(phmElecListVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setTotal(queryResult.getTotal());    //设置数据总条数
            result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());      //设置数据列表
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
	 * 根据ID获取对象PhmElecList
	 * @param ID
	 * @return PhmElecList
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmElecListById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmElecList phmElecList = phmElecListService.getPhmElecListById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmElecList);			//设置数据实体
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
	 * 添加对象PhmElecList
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmElecList(@RequestBody PhmElecList phmElecList) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmElecListService.savePhmElecList(phmElecList);
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
	
	/**
	 * 保存列表
	 * savePhmElecList(描述这个方法的作用)<br/>
	 * @param phmElecListList
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveList", method = RequestMethod.POST)
    public Object savePhmElecList(@RequestBody List<PhmElecList> phmElecListList) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmElecListService.checkList(phmElecListList.get(0));
            phmElecListService.savePhmElecListList(phmElecListList);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("保存成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("保存失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("保存失败！"+e.getMessage());                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
	
	@RequestMapping(value = "/saveList", method = RequestMethod.PUT)
    public Object updatePhmElecList(@RequestBody List<PhmElecList> phmElecListList) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmElecListService.savePhmElecListList(phmElecListList);
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
	 * 更新对象PhmElecList
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmElecList(@RequestBody PhmElecList phmElecList) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmElecListService.updatePhmElecList(phmElecList);
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
	 * 删除对象PhmElecList
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmElecList(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmElecListService.deletePhmElecList(new String[]{id});
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
     * 删除对象PhmElecList
     * @param id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Object deletePhmElecList(@RequestBody PhmElecList phmElecList) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmElecListService.delete(phmElecList);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("删除成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("删除失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("删除失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
    
    /**
     * 根据用户id获取用户表号
     * getMeterNo(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/getMeterNo/{id}",  method = RequestMethod.GET)
    public Object getMeterNo(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            String meterNo = phmElecListService.getMeterNo(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(meterNo);
            result.setMsg("查询表号成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("查询表号失败",e);                     //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("查询表号失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
}