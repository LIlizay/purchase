package com.hhwy.purchaseweb.archives.powerplant.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhcGeneratorSet;
import com.hhwy.purchaseweb.archives.powerplant.domain.PhcGeneratorSetDetail;
import com.hhwy.purchaseweb.archives.powerplant.domain.PowerPlantDetail;
import com.hhwy.purchaseweb.archives.powerplant.domain.PowerPlantVo;
import com.hhwy.purchaseweb.archives.powerplant.service.PowerPlantService;

@RestController
@RequestMapping("/powerPlantController")
public class PowerPlantController {

    public static final Logger log = LoggerFactory.getLogger(PowerPlantController.class);
    
    @Autowired
    PowerPlantService powerPlantService;
    
    /**
     * 分页查询电厂信息
     * getPowerPlantByPage(描述这个方法的作用)<br/>
     * @param powerPlantVo
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/page", method = RequestMethod.POST)
    public Object getPowerPlantByPage(@RequestBody(required=false) PowerPlantVo powerPlantVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<PowerPlantDetail> queryResult = powerPlantService.getPowerPlantByPage(powerPlantVo);
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
     * 根据电厂id获取电厂详细信息
     * getPowerPlant(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getPowerPlant(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            PowerPlantVo powerPlantVo = powerPlantService.getPowerPlant(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(powerPlantVo);            //设置数据实体
            result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);                       //设置返回结果为空
            result.setMsg("查询失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    } 
    
    /**
     * 保存电厂信息
     * savePowerPlant(描述这个方法的作用)<br/>
     * @param powerPlantVo
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( method = RequestMethod.POST)
    public Object savePowerPlant(@RequestBody PowerPlantVo powerPlantVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            powerPlantService.checkPowerPlant(powerPlantVo);
            powerPlantService.savePowerPlant(powerPlantVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setData(powerPlantVo);
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("保存成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("保存失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);
            result.setMsg("保存失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
    
    /**
     * 删除电厂信息
     * deletePowerPlant(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deletePowerPlant(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            powerPlantService.deletePowerPlant(id);
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
     * 
     * @Title: getPhcElecInfoList
     * @Description:(查询机组信息)
     * @return 
     * List<PhmPpaDetail>
     * <b>创 建 人：</b>zhouqi<br/>
     * <b>创建时间:</b>2017年8月7日 下午5:16:12
     * <b>修 改 人：</b>zhouqi<br/>
     * <b>修改时间:</b>2017年8月7日 下午5:16:12
     * @since  1.0.0
     */
    @RequestMapping( value = "/getPhcElecInfoList", method = RequestMethod.GET)
	public List<Map<String,String>> getPhcElecInfoList(String elecId) {
		try {
			List<Map<String,String>> queryResult = powerPlantService.getPhcGeneratorSetList(elecId);
			if(queryResult != null && !queryResult.isEmpty()){
				return queryResult;
			}else{
				return null;
			}
		} catch (Exception e) {
			log.error("查询失败",e);				
			return null;
		}
	}
    
    @RequestMapping(value = "/getPhcElecInfoById/{id}", method = RequestMethod.GET)
    public Object getPhcElecInfoById(@PathVariable String id){
    	 ExecutionResult result = new ExecutionResult();
         try {
        	 List<PhcGeneratorSetDetail> PGSetDetail = powerPlantService.getPhcElecInfoById(id);
             result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
             result.setData(PGSetDetail);
             result.setFlag(true);                       //设置是否成功标识
             result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
         } catch (Exception e) {
             // TODO: handle exception
             log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
             result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
             result.setFlag(false);                      //设置是否成功标识
             result.setData(null);
             result.setMsg("查询失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
             return result;
         }
         return result;
    }
    
    /**
      * @Title: getGeneratorByPowerId
      * @Description: 根据电厂id获取机组列表
      * @param id
      * @return Object
      * <b>创 建 人：</b>LiXinze<br/>
      * <b>创建时间:</b>2018年6月20日 下午4:14:57
      * <b>修 改 人：</b>LiXinze<br/>
      * <b>修改时间:</b>2018年6月20日 下午4:14:57
      * @since  1.0.0
     */
    @RequestMapping(value = "/getGenerator/{id}", method = RequestMethod.GET)
    public Object getGeneratorByPowerId(@PathVariable String id){
   	 ExecutionResult result = new ExecutionResult();
        try {
       	 	List<PhcGeneratorSet> PGSetDetail = powerPlantService.getGeneratorByPowerId(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setData(PGSetDetail);
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);
            result.setMsg("查询失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
   }
}
