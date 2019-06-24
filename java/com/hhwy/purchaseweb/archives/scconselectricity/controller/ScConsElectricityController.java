package com.hhwy.purchaseweb.archives.scconselectricity.controller;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hhwy.business.component.dataprocess.util.ExcelUtils;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ImportVo;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.MonthElecDetail;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ScConsElecTreeDetail;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ScConsElecTreeVo;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ScConsElectricityVo;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.YearElecDetail;
import com.hhwy.purchaseweb.archives.scconselectricity.service.ScConsElectricityService;
import com.hhwy.selling.domain.ScConsElectricity;

/**
 * ScConsElectricityController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/scConsElectricityController")
public class ScConsElectricityController {

	public static final Logger log = LoggerFactory.getLogger(ScConsElectricityController.class);
	
	/**
	 * scConsElectricityService
	 */
	@Autowired
	private ScConsElectricityService scConsElectricityService;
	
	
	@RequestMapping( value = "/getTree", method = RequestMethod.POST)
	public Object getTreeGridByPage(@RequestBody(required=false) ScConsElecTreeVo scConsElecTreeVo){
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ScConsElecTreeDetail> queryResult = scConsElectricityService.getScConsElecTree(scConsElecTreeVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("查询列表成功！");			//设置返回消息，根据实际情况修改
		}catch (Exception e) {
			log.error("树表格查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 分页获取对象ScConsElectricity
	 * @param ID
	 * @return ScConsElectricity
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getScConsElectricityByPage(@RequestBody(required=false) ScConsElectricityVo scConsElectricityVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ScConsElectricity> queryResult = scConsElectricityService.getScConsElectricityByPage(scConsElectricityVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("分页查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 根据ID获取对象ScConsElectricity
	 * @param ID
	 * @return ScConsElectricity
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getScConsElectricityById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			ScConsElectricity scConsElectricity = scConsElectricityService.getScConsElectricityById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(scConsElectricity);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}	
	
	/**
	 * 删除对象ScConsElectricity
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteScConsElectricity(@RequestBody ScConsElectricityVo scConsElectricityVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			scConsElectricityService.deleteScConsElectricity(scConsElectricityVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}	
	
	/**
	 * 
	 * @Title: saveScConsElectricityList<br/>
	 * @Description: 保存历史用电信息列表<br/>
	 * @param scConsElectricityVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月23日 上午1:55:50
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月23日 上午1:55:50
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveList", method = RequestMethod.POST)
	public Object saveScConsElectricityList(@RequestBody(required=false) ScConsElectricityVo scConsElectricityVo){
		ExecutionResult result = new ExecutionResult();
		try {
			scConsElectricityService.saveScConsElectricityList(scConsElectricityVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	@RequestMapping(value = "/updateList", method = RequestMethod.POST)
	public Object updateScConsElectricity(@RequestBody(required=false) ScConsElectricityVo scConsElectricityVo){
		ExecutionResult result = new ExecutionResult();
		try {
			scConsElectricityService.updateScConsElectricity(scConsElectricityVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * @Title: getYearElecDetailList<br/>
	 * @Description: 查询年份历史用电<br/>
	 * @param scConsElectricityVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月23日 上午1:55:09
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月23日 上午1:55:09
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getYearList", method = RequestMethod.POST)
	public Object getYearElecDetailList(@RequestBody(required=false) ScConsElectricityVo scConsElectricityVo){
		ExecutionResult result = new ExecutionResult();
		try {
			List<YearElecDetail> list = scConsElectricityService.getYearElecDetailList(scConsElectricityVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(list);						//设置数据列表
			result.setMsg("查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getMonthElecList<br/>
	 * @Description: 查询月份历史用电<br/>
	 * @param scConsElectricityVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月23日 上午1:54:55
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月23日 上午1:54:55
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getMonthList", method = RequestMethod.POST)
	public Object getMonthElecList(@RequestBody(required=false) ScConsElectricityVo scConsElectricityVo){
		ExecutionResult result = new ExecutionResult();
		try {
			List<MonthElecDetail> list = scConsElectricityService.getMonthElecList(scConsElectricityVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(list);						//设置数据列表
			result.setMsg("查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: importElecFromExcel
	  * @Description: 由Excel导入历史用电
	  * @param request
	  * @return ExecutionResult
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年12月22日 下午3:09:43
	  * <b>修 改 人：</b>zhangzhao<br/>
	  * <b>修改时间:</b>2018年4月13日 下午2:12:11
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public ExecutionResult importElecFromExcel(HttpServletRequest request) {
		ExecutionResult result = new ExecutionResult();
		try{
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();
				// 存储数据list
				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						// excel取数据
						InputStream in = file.getInputStream();
						ExcelUtils excelUtil = new ExcelUtils();
						// 根据ExcelUtils获取workbook对象
						Workbook workbook = excelUtil.getWorkbook(in,file.getOriginalFilename());
						// 调用ExcelUtils中的方法,通过workbook获取list
						List<ImportVo> consElecList;
						try {
							//调用公共方法一个sheet页 返回list
							consElecList = ExcelUtils.getFirstSheetListByWorkBook(workbook, ImportVo.class, 0, 3);
						} catch (Exception e) {
							String exception = e.toString();
							Pattern pattern = Pattern.compile(".*\\d?行\\d.*?列.*");
						    Matcher matcher = pattern.matcher(exception);
							if(matcher.matches()) {
								int index =  exception.indexOf("行");
								String x = exception.substring(index-1, index);
								int yIndex = exception.indexOf("列");
								String y = exception.substring(index+1,yIndex);
								char column = (char) (Integer.valueOf(y) + 64);
								throw new RuntimeException(x + "行" + column + "列 数据格式错误!");
							}else {
								e.printStackTrace();
								throw new RuntimeException("请您下载本公司提供的模板，按模板要求填写数据，感谢配合！");
							}
						}
						in.close();
						if(consElecList.size()>0){
							scConsElectricityService.importElecFromExcel(consElecList);
						}else{
							throw new RuntimeException("导入内容为空，请确认！");
						}
					}else{
						throw new RuntimeException("文件不存在！");
					}
				}
			}
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("导入成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("导入失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("导入失败！"+e.getMessage());					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: exportExcel
	  * @Description: 导出选定的用户年份数据
	  * @param ids
	  * @param request
	  * @param response
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月2日 下午8:34:13
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月2日 下午8:34:13
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public Object exportExcel(@RequestParam String ids, @RequestParam String voltCode, @RequestParam String consName,  @RequestParam String elecTypeCode,
			@RequestParam String year, @RequestParam String consId,HttpServletRequest request,HttpServletResponse response){
		ExecutionResult result = new ExecutionResult();
		try{
			scConsElectricityService.exportExcel(ids, consName, voltCode, elecTypeCode, year, consId, request,response);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("导出成功！");					//设置返回消息，根据实际情况修改
		}catch (Exception e) {
			log.error("导出失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("导出失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 分页获取对象 实际用电信息列表
	 * @param ID
	 * @return ScConsElectricity
	 */
	@RequestMapping( value = "/practicalPowerPage", method = RequestMethod.POST)
	public Object practicalPowerPage(@RequestBody(required=false) ScConsElectricityVo scConsElectricityVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ScConsElecTreeDetail> queryResult = scConsElectricityService.practicalPowerPage(scConsElectricityVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("分页查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * @Title: getScConsElectricityById<br/>
	 * @Description:根据交易年月查实际用电量录入信息
	 * @param id
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月19日 下午2:52:11
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月19日 下午2:52:11
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getPraPqTranYm/{ym}", method = RequestMethod.GET)
	public Object getPraPqTranYm(@PathVariable String ym) {
		ExecutionResult result = new ExecutionResult();
		try {
			ScConsElecTreeDetail data = scConsElectricityService.getPraPqTranYm(ym);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(data);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * @Title: pqDataGrid<br/>
	 * @Description:用户月度实际用电量列表
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月21日 上午10:19:02
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月21日 上午10:19:02
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/pqDataGrid",  method = RequestMethod.POST)
	public Object pqDataGrid(@RequestBody(required=false) ScConsElectricityVo scConsElectricityVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ScConsElecTreeDetail> queryResult = scConsElectricityService.pqDataGrid(scConsElectricityVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("分页查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: savePracticalPq
	  * @Description: 新增/编辑实际用电量录入信息保存
	  * @param scConsElectricityList
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年8月13日 下午5:26:56
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年8月13日 下午5:26:56
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/savePracticalPq", method = RequestMethod.PUT)
	public Object savePracticalPq(@RequestBody(required=false) List<ScConsElectricity> scConsElectricityList){
		ExecutionResult result = new ExecutionResult();
		try {
			scConsElectricityService.savePracticalPq(scConsElectricityList);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	/**
	 * @Title: savePracticalPqJs
	 * @Description: 江苏的 新增/编辑实际用电量录入信息保存
	 * @param scConsElectricityVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年4月24日 下午4:45:12
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年4月24日 下午4:45:12
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/savePracticalPqJs", method = RequestMethod.PUT)
	public Object savePracticalPqJs(@RequestBody(required=false) ScConsElectricityVo scConsElectricityVo){
		ExecutionResult result = new ExecutionResult();
		try {
			scConsElectricityService.savePracticalPqJs(scConsElectricityVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * @Title: deleteElectricity<br/>
	 * @Description: 实际用电量删除方法
	 * @param ym
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月22日 上午9:46:54
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月22日 上午9:46:54
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/deleteElectricity", method = RequestMethod.DELETE)
	public Object deleteElectricity(@RequestBody(required=false) ScConsElectricityVo scConsElectricityVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			scConsElectricityService.deleteElectricity(scConsElectricityVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}	
}