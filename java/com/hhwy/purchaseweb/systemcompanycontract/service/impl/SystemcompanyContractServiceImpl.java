package com.hhwy.purchaseweb.systemcompanycontract.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.systemcompanycontract.domain.SystemcompanyContractDetail;
import com.hhwy.purchaseweb.systemcompanycontract.domain.SystemcompanyContractVo;
import com.hhwy.purchaseweb.systemcompanycontract.service.SystemcompanyContractService;
import com.hhwy.selling.domain.SystemcompanyContract;

/**
 * SystemcompanyContractService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SystemcompanyContractServiceImpl implements SystemcompanyContractService {

	public static final Logger log = LoggerFactory.getLogger(SystemcompanyContractServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

	/**
	 * @Title: getSystemcompanyContractByPage<br/>
	 * @Description:平台用户管理主列表
	 * @param systemcompanyContractVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月30日 下午5:24:51
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月30日 下午5:24:51
	 * @since  1.0.0
	 */
	public QueryResult<SystemcompanyContractDetail> getSystemcompanyContractByPage(SystemcompanyContractVo systemcompanyContractVo) throws Exception{
		QueryResult<SystemcompanyContractDetail> queryResult = new QueryResult<SystemcompanyContractDetail>();
		long total = getSystemcompanyContractCountByParams(systemcompanyContractVo);
		List<SystemcompanyContractDetail> systemcompanyContractList = getSystemcompanyContractListByParams(systemcompanyContractVo);
		queryResult.setTotal(total);
		queryResult.setData(systemcompanyContractList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SystemcompanyContract列表
	 * by- zhangzhao 分页数据
	 * @param SystemcompanyContractVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SystemcompanyContractDetail> getSystemcompanyContractListByParams(SystemcompanyContractVo systemcompanyContractVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(systemcompanyContractVo == null){
			systemcompanyContractVo = new SystemcompanyContractVo();
		}
		List<SystemcompanyContractDetail> systemcompanyContractList = (List<SystemcompanyContractDetail>)dao.getBySql("systemcompanyContract.sql.getSystemcompanyContractListByParams",systemcompanyContractVo);
		ConvertListUtil.convert(systemcompanyContractList);
		//数据库版本字段存的是 多选的字符串数据，需要转成页面显示的样子   处理版本字段数据
		if(systemcompanyContractList != null && systemcompanyContractList.size() > 0){
			List<Map<String, Object>> bySql = (List<Map<String, Object>>) dao.getBySql("systemcompanyContract.sql.getVersionSPCodeNameSql", null);
			Map<String, Object> map = new HashMap<>();
			for(Map<String, Object> row : bySql){
				map.put(row.get("value").toString(), row.get("name"));
			}
			//数据格式： 01，02，03
			for(SystemcompanyContractDetail detail : systemcompanyContractList){
				String versionCode = detail.getVersionCode();
				if(versionCode != null && !"".equals(versionCode)){
					String[] split = versionCode.split(",");
					String versionName = "";
					for(String code : split){
						if("".equals(versionName)){
							versionName += map.get(code).toString();
						}else {
							versionName = versionName + "，" + map.get(code).toString();
						}
						
					}
					detail.setVersionCodeName(versionName);
				}
			}
		}
		
		return systemcompanyContractList;
	}
	
	/**
	 * 根据查询条件获取对象SystemcompanyContract数量
	 * @param SystemcompanyContractVo
	 * @return Integer
	 */
	public Integer getSystemcompanyContractCountByParams(SystemcompanyContractVo systemcompanyContractVo){
		Object result = dao.getOneBySQL("systemcompanyContract.sql.getSystemcompanyContractCountByParams",systemcompanyContractVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * @Title: saveSystemcompanyContractList<br/>
	 * @Description:平台用户管理编辑页面保存
	 * @param systemcompanyContractList
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年12月1日 下午5:13:35
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年12月1日 下午5:13:35
	 * @since  1.0.0
	 */
	public void saveSystemcompanyContractList(SystemcompanyContractVo SystemcompanyContractVo) {
		//保存或更新，平台用户管理表中数据
		dao.save(SystemcompanyContractVo.getSystemcompanyContract());
		//更新 system_company_domain表中的 省码、公司名称 字段
		dao.updateBySql("systemcompanyContract.sql.updateSystemCompanyDomainSql", SystemcompanyContractVo);
	}
	
	/**
	 * @Title: exportExcel<br/>
	 * @Description:平台用户明细导出，需求暂定导出全部数据
	 * @param request
	 * @param response
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年12月5日 上午11:10:19
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年12月5日 上午11:10:19
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@Override
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SystemcompanyContractVo systemcompanyContractVo = new SystemcompanyContractVo();
		systemcompanyContractVo.setPagingFlag(false);
		//获取导出数据
		List<SystemcompanyContractDetail> resultList = this.getSystemcompanyContractListByParams(systemcompanyContractVo);
		
		//获取模版文件
		String excelPath=request.getSession().getServletContext().getRealPath("/plugins/cloud-purchase-web/administrator-tools/cons-manage/平台用户管理导出.xlsx");//模板路径
		response.reset();//去除空白行
		response.setContentType("application/vnd.ms-excel;charset=gb2312");
		String newName = URLEncoder.encode("平台用户管理导出" + System.currentTimeMillis() + ".xlsx","UTF-8"); //设置导出的文件名称
		request.setCharacterEncoding("UTF-8");//设置request的编码方式，防止中文乱码
    	response.setHeader("Content-Disposition","attachment;filename=\"" + newName + "\"");  
    	InputStream fio = new FileInputStream(excelPath);//将模板读入流
    	ServletOutputStream out = response.getOutputStream();
    	try {  
        	XSSFWorkbook workbook=new XSSFWorkbook(fio);//创建excel(xlsx格式)
			XSSFSheet sheet=workbook.getSheetAt(0);     //创建工作薄sheet
			// 数据 Excel 所需字段
            String[] str = {"ProvinceName","CompanyName","ConsTypeCodeName","VersionCodeName","CompanyDomain","ManagerName",
            		"SystemEffectiveDate","SystemExpiryDate","EffectiveDate","ExpiryDate","AccountPassword","Remark"};
            
			int rowCount = 0; //第1行存数据
			int cellCount = 0;//第3列开始
			//表头
			String[] excelHead = {"省份","公司名称","用户类型","系统版本","访问地址","销售经理","系统开通时间","系统停止时间","合同开始时间","合同结束时间","账号密码","备注"};
			//创建表头
			for(int i=0; i < 1 ; i++){
				XSSFRow row = sheet.createRow(rowCount);						//创建行
				XSSFCellStyle borderStyle = workbook.createCellStyle();			//创建单元格样式对象
				//设置边框样式
				borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
				borderStyle.setBorderTop(CellStyle.BORDER_THIN);
				borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
				borderStyle.setBorderRight(CellStyle.BORDER_THIN);
				//设置单元格边框颜色
				borderStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.black));   
	            borderStyle.setTopBorderColor(new XSSFColor(java.awt.Color.black));   
	            borderStyle.setLeftBorderColor(new XSSFColor(java.awt.Color.black)); 
	            borderStyle.setRightBorderColor(new XSSFColor(java.awt.Color.black)); 
	            //居中   
	            borderStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	            borderStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
	            //自动换行
	            borderStyle.setWrapText(true);
	            //创建列
	            for(int j = 0 ; j < excelHead.length ; j++){
	            	Cell borderCell = row.createCell(cellCount++);					//创建列
	            	borderCell.setCellStyle(borderStyle);		  					//单元格样式	
            			borderCell.setCellValue(excelHead[j]);							//列数据
	            }
	            rowCount ++;
	            cellCount = 0;
			}
//			创建表格数据
			for(int i=0; i < resultList.size() ; i++){
				XSSFRow row = sheet.createRow(rowCount);						//创建行
				XSSFCellStyle borderStyle = workbook.createCellStyle();			//创建单元格样式对象
				//设置边框样式
				borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
				borderStyle.setBorderTop(CellStyle.BORDER_THIN);
				borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
				borderStyle.setBorderRight(CellStyle.BORDER_THIN);
				//设置单元格边框颜色
				borderStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.black));   
	            borderStyle.setTopBorderColor(new XSSFColor(java.awt.Color.black));   
	            borderStyle.setLeftBorderColor(new XSSFColor(java.awt.Color.black)); 
	            borderStyle.setRightBorderColor(new XSSFColor(java.awt.Color.black)); 
	            //居中   
	            borderStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	            borderStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
	            //自动换行
	            borderStyle.setWrapText(true);
	            
	            SystemcompanyContractDetail forecastPqDetail = resultList.get(i);
	            //创建列
	            for(int j = 0 ; j < str.length ; j++){
	            	Cell borderCell = row.createCell(cellCount++);					//创建列
	            	borderCell.setCellStyle(borderStyle);		  					//单元格样式	
	            	Class<? extends SystemcompanyContractDetail> importVoClass = forecastPqDetail.getClass();	//拼接get方法，取对象里的值
	            	Method method = importVoClass.getMethod("get"+str[j]);
	            	
            		if( method.invoke(forecastPqDetail) != null){
            			String value = method.invoke(forecastPqDetail).toString();
            			borderCell.setCellValue(value);							//列数据
            		}else{
            			borderCell.setCellValue("");							//列数据
            		}
	            }
	            rowCount ++;
	            cellCount = 0;
			}
			workbook.write(out);
            out.flush();  
        } catch (FileNotFoundException e) {
			System.out.println("平台用户管理模板没有找到");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("输出流异常");
			e.printStackTrace();
		}finally {
			try {
				fio.close();
				out.close();
			} catch (IOException e) {
				System.out.println("流关闭异常");
				e.printStackTrace();
			}
		}
		
		
	}	
	/**
	 * 根据查询条件获取单个对象SystemcompanyContract
	 * @param SystemcompanyContractVo
	 * @return SystemcompanyContract
	 */
	public SystemcompanyContractDetail getSystemcompanyContractOneByParams(SystemcompanyContractVo systemcompanyContractVo) throws Exception{
		SystemcompanyContractDetail systemcompanyContract = null;
		List<SystemcompanyContractDetail> systemcompanyContractList = getSystemcompanyContractListByParams(systemcompanyContractVo);
		if(systemcompanyContractList != null && systemcompanyContractList.size() > 0){
			systemcompanyContract = systemcompanyContractList.get(0);
		}
		return systemcompanyContract;
	}
	
	/**
	 * 根据ID获取对象SystemcompanyContract
	 * @param ID
	 * @return SystemcompanyContract
	 */
	public SystemcompanyContract getSystemcompanyContractById(String id) {
		return dao.findById(id, SystemcompanyContract.class);
	}	
	
	/**
	 * 添加对象SystemcompanyContract
	 * @param 实体对象
	 */
	public void saveSystemcompanyContract(SystemcompanyContract systemcompanyContract) {
		dao.save(systemcompanyContract);
	}
	
	/**
	 * 更新对象SystemcompanyContract
	 * @param 实体对象
	 */
	public void updateSystemcompanyContract(SystemcompanyContract systemcompanyContract) {
		dao.update(systemcompanyContract);
	}
	
	/**
	 * 删除对象SystemcompanyContract
	 * @param id数据组
	 */
	public void deleteSystemcompanyContract(String[] ids) {
		dao.delete(ids, SystemcompanyContract.class);
	}

}