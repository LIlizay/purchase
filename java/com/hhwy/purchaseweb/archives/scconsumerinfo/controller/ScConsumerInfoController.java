package com.hhwy.purchaseweb.archives.scconsumerinfo.controller;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hhwy.business.component.dataprocess.util.ExcelUtils;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ConsAgreDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ConsInfoDetailForImport;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ElecMonthDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ElecYearDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ScConsumerInfoDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ScConsumerInfoVo;
import com.hhwy.purchaseweb.archives.scconsumerinfo.service.ScConsumerInfoService;
import com.hhwy.purchaseweb.contract.smppa.domain.SmPpaVo;
import com.hhwy.purchaseweb.contract.smppa.service.SmPpaService;

/**
 * ScConsumerInfoController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/scConsumerInfoController")
public class ScConsumerInfoController {

	public static final Logger log = LoggerFactory.getLogger(ScConsumerInfoController.class);
	
	/**
	 * scConsumerInfoService
	 */
	@Autowired
	private ScConsumerInfoService scConsumerInfoService;
	
	/**
	 * smPpaService		售电合同的service
	 */
	@Autowired
	private SmPpaService smPpaService;
	
	/**
	 * 分页获取对象ScConsumerInfo
	 * @param ID
	 * @return ScConsumerInfo
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getScConsumerInfoByPage(@RequestBody(required=false) ScConsumerInfoVo scConsumerInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			//Object entityManagerFactory = AppContainer.getBean("entityManagerFactory");
			QueryResult<ScConsumerInfoDetail> queryResult = scConsumerInfoService.getScConsumerInfoByPage(scConsumerInfoVo);
			
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
	 * 根据ID获取对象ScConsumerInfo
	 * @param ID
	 * @return ScConsumerInfo
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getScConsumerInfoById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			ScConsumerInfoVo scConsumerInfoVo = scConsumerInfoService.getScConsumerInfoById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(scConsumerInfoVo);			//设置数据实体
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
	 * 添加对象ScConsumerInfo
	 * @param 实体对象
	 */
	@RequestMapping( value="/saveScConsumerInfo",method = RequestMethod.POST)
	public Object saveScConsumerInfo(@RequestBody ScConsumerInfoVo scConsumerInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			scConsumerInfoService.checkScConsumer(scConsumerInfoVo);
			ScConsumerInfoVo cons = scConsumerInfoService.saveScConsumerInfo(scConsumerInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setData(cons);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			if(e instanceof RuntimeException) {
				result.setMsg("保存失败!"+e.getMessage());				//设置返回消息，根据实际情况修改
			}else {
				result.setMsg("保存失败！请联系管理员");					//设置返回消息，根据实际情况修改
			}
		}
		return result;
		
	}
	
	/**
	 * 更新对象ScConsumerInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateScConsumerInfo(@RequestBody ScConsumerInfoVo scConsumerInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			scConsumerInfoService.checkScConsumer(scConsumerInfoVo);
			ScConsumerInfoVo info = scConsumerInfoService.updateScConsumerInfo(scConsumerInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(info);
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);
			if(e instanceof RuntimeException) {
				result.setMsg("保存失败!"+e.getMessage());				//设置返回消息，根据实际情况修改
			}else {
				result.setMsg("保存失败！请联系管理员");					//设置返回消息，根据实际情况修改
			}
		}
		return result;
	}
	
	/**
	 * 删除对象ScConsumerInfo
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteScConsumerInfo(@RequestBody String[] id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmPpaVo smPpaVo = new SmPpaVo();
			smPpaVo.setConsIds(id);
			Integer count = smPpaService.getSmPpaCountByParams(smPpaVo);		//判断用户是否签订了售电合同，若有签订则不允许删除
			if(count > 0) {
				result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
				result.setFlag(false);						//设置是否成功标识
				result.setMsg("用户有售电合同，不允许删除。请先删除售电合同数据!");					//设置返回消息，根据实际情况修改
				return result;
			}
			//判断用户是否绑定了用电监测的用户
			Integer relaCount = scConsumerInfoService.getScConsUserRelaCountByConsIds(id);
			if(relaCount > 0) {
				result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
				result.setFlag(false);						//设置是否成功标识
				result.setMsg("删除失败,用户已绑定用电监测用户！");					//设置返回消息，根据实际情况修改
				return result;
			}
			//判断用户是否已有子级用户
			ScConsumerInfoVo scConsumerInfoVo = new ScConsumerInfoVo();
			scConsumerInfoVo.setConsIds(id);
			List<ScConsumerInfoDetail> childrenCount = scConsumerInfoService.getScConsumerInfoListByParams(scConsumerInfoVo);
			for(ScConsumerInfoDetail scConsumerInfoDetail : childrenCount){
				if(scConsumerInfoDetail.getCount() != null && scConsumerInfoDetail.getCount() > 0) {
					result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
					result.setFlag(false);						//设置是否成功标识
					result.setMsg("删除失败,用户存在子用户！");					//设置返回消息，根据实际情况修改
					return result;
				}
			}
			scConsumerInfoService.deleteScConsumerInfo(id);
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
	 * @Title: getConsAgreList<br/>
	 * @Description: 查询合同列表<br/>
	 * @param map
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月19日 下午3:41:54
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月19日 下午3:41:54
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getAgre", method = RequestMethod.POST)
	public Object getConsAgreList(@RequestBody(required = true) Map<String, Object> map){
		ExecutionResult result = new ExecutionResult();
		try {
			List<ConsAgreDetail> list = scConsumerInfoService.getConsAgreList(map);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
			result.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setRows(new Object[]{});
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getElecYearList<br/>
	 * @Description: 查询年份用电信息<br/>
	 * @param map
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月19日 下午8:31:02
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月19日 下午8:31:02
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/elecYear", method = RequestMethod.POST)
	public Object getElecYearList(@RequestBody(required = true) Map<String, Object> map){
		ExecutionResult result = new ExecutionResult();
		try {
			List<ElecYearDetail> list = scConsumerInfoService.getElecYearList(map);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
			result.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setRows(new Object[]{});
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getElecMonthList<br/>
	 * @Description: 查询月份用电信息<br/>
	 * @param map
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月19日 下午8:38:15
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月19日 下午8:38:15
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/elecMonth", method = RequestMethod.POST)
	public Object getElecMonthList(@RequestBody(required = true) Map<String, Object> map){
		ExecutionResult result = new ExecutionResult();
		try {
			List<ElecMonthDetail> list = scConsumerInfoService.getElecMonthList(map);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
			result.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setRows(new Object[]{});
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getConsProfit<br/>
	 * @Description: 查询用户结算电费<br/>
	 * @param map
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月21日 下午8:47:56
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月21日 下午8:47:56
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/consProfit", method = RequestMethod.POST)
	public Object getConsProfit(@RequestBody(required = true) Map<String, Object> map){
		try {
			List<Map<String, Object>> list = scConsumerInfoService.getConsProfit(map);
			return list;
		} catch (Exception e) {
			log.error("查询失败",e);
			return null;
		}
	}
	
	
	/**
	 * @Title: importConsInfoFromExcel
	 * @Description: 由excel导入用户信息
	 * @param request
	 * @return 
	 * ExecutionResult
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月18日 下午4:48:27
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月18日 下午4:48:27
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public ExecutionResult importConsInfoFromExcel(HttpServletRequest request) {
		ExecutionResult result = new ExecutionResult();
		try {
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();
				ExcelUtils excelUtil = new ExcelUtils();
				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						// excel取数据
						InputStream in = file.getInputStream();
						// 根据ExcelUtils获取workbook对象
						Workbook workbook = excelUtil.getWorkbook(in,file.getOriginalFilename());
						int sheetNum = workbook.getNumberOfSheets();         
		          		if(sheetNum == 0) {
		          			continue;
		          		}
			            //遍历Excel中第一个sheet
			        	org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			        	if(sheet.getLastRowNum() < 4) {
			        		throw new BusinessException("导入模板中前三行不允许删除，请检查！");
			        	}
			            //取出第一列的前四行判断用户是否删除了前几行
			            Cell cell0 = sheet.getRow(0).getCell(0);
			            //Cell cell1 = sheet.getRow(1).getCell(0);
			            Cell cell2 = sheet.getRow(2).getCell(0);
			            Cell cell3 = sheet.getRow(3).getCell(0);
			            String value0 = cell0.getRichStringCellValue().getString().trim();
			            //String value1 = cell1.getRichStringCellValue().getString().trim();
			            String value2 = cell2.getRichStringCellValue().getString().trim();
			            String value3 = cell3.getRichStringCellValue().getString().trim();
			            if(!"consName".equals(value0) || !"*用户名称".equals(value2) || !"请勿删除此行，必填项".equals(value3)) {
			            	throw new BusinessException("导入模板中前三行不允许删除，请检查！");
			            }
			            if(sheet.getLastRowNum() == 4){
			            	throw new BusinessException("没有检测到数据，请检查！");
			        	}
						// 调用ExcelUtils中的方法,通过workbook获取list
			            List<ConsInfoDetailForImport> consInfoList = null;
			            try {
			            	consInfoList = ExcelUtils.getListBySheet(sheet, ConsInfoDetailForImport.class, ExcelUtils.getTitleListBySheet(sheet,0), 4);
						} catch (Exception e) {
							if(e instanceof org.apache.commons.beanutils.ConversionException && e.getMessage().equals("Error converting from 'String' to 'BigDecimal' null") ) {
								throw new BusinessException("表格中数据格式错误！数字列填写了普通字符！请检查！");
							}else {
								throw e;
							}
						}
						in.close();
						if(consInfoList != null && consInfoList.size()>0){
							scConsumerInfoService.importConsInfoFromExcel(consInfoList);
						}else{
							result.setMsg("导入内容为空，请确认！");
						}
					}	
				}
			}
			result.setFlag(true);
			result.setCode(ReturnCode.RES_SUCCESS);
			result.setMsg("导入成功");
		} catch (Exception e) {
			log.error("导入失败", e);
			e.printStackTrace();
			result.setFlag(false);
			result.setCode(ReturnCode.RES_FAILED);
			result.setMsg("导入失败！" + e.getMessage());
		}
		return result;
	}
	
}