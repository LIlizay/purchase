package com.hhwy.purchaseweb.archives.scconselectricity.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmSettlementMonth;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ImportVo;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.MonthElecDetail;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ScConsElecTreeDetail;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ScConsElecTreeVo;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ScConsElectricityVo;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.YearElecDetail;
import com.hhwy.purchaseweb.archives.scconselectricity.service.ScConsElectricityService;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.purchaseweb.bigdata.service.BigDataService;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.service.SmSettlementMonthService;
import com.hhwy.selling.domain.ScConsElectricity;

/**
 * ScConsElectricityService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class ScConsElectricityServiceImpl implements ScConsElectricityService {

	public static final Logger log = LoggerFactory.getLogger(ScConsElectricityServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * bigDataService		大数据service
	 */
	@Autowired
	private BigDataService bigDataService;
	
	/**
	 * smSettlementMonthService 结算主表service
	 */
	@Autowired
	private SmSettlementMonthService smSettlementMonthService;
	
	/**
	 * 分页获取对象ScConsElectricity
	 * @param ID
	 * @return ScConsElectricity
	 */
	public QueryResult<ScConsElectricity> getScConsElectricityByPage(ScConsElectricityVo scConsElectricityVo) throws Exception{
		QueryResult<ScConsElectricity> queryResult = new QueryResult<ScConsElectricity>();
		long total = getScConsElectricityCountByParams(scConsElectricityVo);
		List<ScConsElectricity> scConsElectricityList = getScConsElectricityListByParams(scConsElectricityVo);
		queryResult.setTotal(total);
		queryResult.setData(scConsElectricityList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象ScConsElectricity数量
	 * @param ScConsElectricityVo
	 * @return Integer
	 */
	public Integer getScConsElectricityCountByParams(ScConsElectricityVo scConsElectricityVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scConsElectricity.sql.getScConsElectricityCountByParams",scConsElectricityVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象ScConsElectricity列表
	 * @param ScConsElectricityVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ScConsElectricity> getScConsElectricityListByParams(ScConsElectricityVo scConsElectricityVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(scConsElectricityVo == null){
			scConsElectricityVo = new ScConsElectricityVo();
		}
		Parameter.isFilterData.set(true);
		List<ScConsElectricity> scConsElectricityList = (List<ScConsElectricity>)dao.getBySql("scConsElectricity.sql.getScConsElectricityListByParams",scConsElectricityVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(scConsElectricityList);
		return scConsElectricityList;
	}
	/**
	 * 根据查询条件获取单个对象ScConsElectricity
	 * @param ScConsElectricityVo
	 * @return ScConsElectricity
	 */
	public ScConsElectricity getScConsElectricityOneByParams(ScConsElectricityVo scConsElectricityVo) throws Exception{
		ScConsElectricity scConsElectricity = null;
		List<ScConsElectricity> scConsElectricityList = getScConsElectricityListByParams(scConsElectricityVo);
		if(scConsElectricityList != null && scConsElectricityList.size() > 0){
			scConsElectricity = scConsElectricityList.get(0);
		}
		return scConsElectricity;
	}
	
	/**
	 * 根据ID获取对象ScConsElectricity
	 * @param ID
	 * @return ScConsElectricity
	 */
	public ScConsElectricity getScConsElectricityById(String id) {
		return dao.findById(id, ScConsElectricity.class);
	}	
	
	/**
	 * 添加对象ScConsElectricity
	 * @param 实体对象
	 */
	public void saveScConsElectricityList(List<ScConsElectricity> scConsElectricityList) {
		for (ScConsElectricity scConsElectricity : scConsElectricityList) {
			scConsElectricity.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(scConsElectricityList);
		
		//更新大数据表中实际用电量数据
		bigDataService.saveOrUpdatePowerPqByHistoryElecs(scConsElectricityList);
	}
	
	/**
	 * 删除对象ScConsElectricity
	 * @param id数据组
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteScConsElectricity(ScConsElectricityVo scConsElectricityVo) {
		List<ScConsElecTreeDetail> list = scConsElectricityVo.getTreeList();
		if(list != null && list.size() > 0){
			List<String> consIds = new ArrayList<String>();
			List<ScConsElecTreeDetail> childList = new ArrayList<ScConsElecTreeDetail>();
			List<String> childIds = new ArrayList<String>();
			for(ScConsElecTreeDetail detail : list){
				if(detail.get_parentId() == null || "".equals(detail.get_parentId())){
					consIds.add(detail.getConsId());
				}else{
					childList.add(detail);
				}
			}
			for(ScConsElecTreeDetail child : childList){
				childIds.add(child.getConsId());
				for(String parentId : consIds){
					if(child.get_parentId().equals(parentId)){
						childIds.remove(child.getConsId());
					}
				}
			}
			if(consIds != null && consIds.size() > 0){
				this.deleteScConsElecByConsIds((String[]) consIds.toArray());
			}
			if(childIds != null && childIds.size() > 0){
				dao.deleteBySql("scConsElectricity.sql.deleteScConsElectricityByChildConsId", childIds.toArray());
			}
		}else{
			dao.deleteBySql("scConsElectricity.sql.deleteScConsElectricityByConsIdAndYear", scConsElectricityVo);
		}
		//删除历史用电信息，先不删除大数据的实际用电量信息
//		bigDataService
	}

	/**
	 * @Title: deleteScConsElecByConsIds
	 * @Description: 根据用户id数组删除历史用电信息类表
	 * @param consIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月10日 下午7:11:01
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月10日 下午7:11:01
	 * @since  1.0.0
	 */
	public void deleteScConsElecByConsIds(String[] consIds) {
		dao.deleteBySql("scConsElectricity.sql.deleteScConsElectricityByConsId", consIds);
	}
	/**
	 * 添加对象ScConsElectricity
	 * @param 实体对象
	 */
	@Override
	public void saveScConsElectricityList(ScConsElectricityVo scConsElectricityVo){
		saveScConsElectricityList(scConsElectricityVo.getScConsElectricitieList());
	}

	@SuppressWarnings("unchecked")
	public List<YearElecDetail> getYearElecDetailList(ScConsElectricityVo scConsElectricityVo) throws Exception {
		if(scConsElectricityVo.getConsId() != null && !scConsElectricityVo.getConsId().equals("")){
			Map<String, String> map = new HashMap<String, String>();
			map.put("consId", scConsElectricityVo.getConsId());
			map.put("year", scConsElectricityVo.getYear());
			List<YearElecDetail> list = (List<YearElecDetail>) dao.getBySql("scConsElectricity.sql.getYearElecList", map);
			return list;
		}else{
			throw new Exception("用户id为空");
		}
	}

	@SuppressWarnings("unchecked")
	public List<MonthElecDetail> getMonthElecList(ScConsElectricityVo scConsElectricityVo) throws Exception {
		if(scConsElectricityVo.getConsId() != null && !scConsElectricityVo.getConsId().equals("")){
			Map<String, String> map = new HashMap<String, String>();
			map.put("consId", scConsElectricityVo.getConsId());
			map.put("year", scConsElectricityVo.getYear());
			List<MonthElecDetail> list = (List<MonthElecDetail>) dao.getBySql("scConsElectricity.sql.getMonthElecList", map);
			if(list != null && list.size()>0){
				for (MonthElecDetail monthElecDetail : list) {
					String m = monthElecDetail.getYm().substring(4);
					m = Integer.toString(Integer.parseInt(m))+"月";
					monthElecDetail.setMonth(m);
				}
				List<MonthElecDetail> monthElecCount = (List<MonthElecDetail>) dao.getBySql("scConsElectricity.sql.getMonthElecCount", map);
				if(monthElecCount != null && monthElecCount.size()>0){
					MonthElecDetail monthElecDetail = monthElecCount.get(0);
					if(monthElecDetail != null){						
						monthElecDetail.setMonth("总计");
					}else{
						monthElecDetail = new MonthElecDetail();
						monthElecDetail.setMonth("总计");
					}
					
					list.add(monthElecDetail);
				}
			}else{
				MonthElecDetail monthElecDetail;
				list = new ArrayList<MonthElecDetail>();
				for(int a = 1; a<14; a++){
					monthElecDetail = new MonthElecDetail();
					if(a == 13){
						monthElecDetail.setMonth("总计");
					}else{
						monthElecDetail.setMonth(a+"月");
					}
					list.add(monthElecDetail);
					
				}
			}
			return list;
		}else{
			throw new Exception("用户id为空");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateScConsElectricity(ScConsElectricityVo scConsElectricityVo) {
		
		List<ScConsElectricity> list = scConsElectricityVo.getScConsElectricitieList();
		if(list != null && list.size()>0){
			for (ScConsElectricity scConsElectricity : list) {
				dao.updateBySql("scConsElectricity.sql.updateScConsElectricity", scConsElectricity);
			}
		}
		
		//更新大数据表中实际用电量数据
		bigDataService.saveOrUpdatePowerPqByHistoryElecs(list);
	}

	/**
	  * @Title: getScConsElecTree
	  * @Description: 分页获取用户历史用电信息表格树
	  * @param scConsElecTreeVo
	  * @return QueryResult<ScConsElecTreeDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年1月31日 下午2:32:15
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年1月31日 下午2:32:15
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@Override
	public QueryResult<ScConsElecTreeDetail> getScConsElecTree(ScConsElecTreeVo scConsElecTreeVo) throws Exception {
		QueryResult<ScConsElecTreeDetail> queryResult = new QueryResult<ScConsElecTreeDetail>();
		List<ScConsElecTreeDetail> treeList;
		long total = getTreeCountByParams(scConsElecTreeVo);			//查询根节点数量
		//判断是否为子节点查询
		if(scConsElecTreeVo.getId() == null || "".equals(scConsElecTreeVo.getId())){
			//父节点查询
			treeList = getParentListByParams(scConsElecTreeVo);
		}else{
			treeList = getChildListByConsId(scConsElecTreeVo.getId());
		}
		queryResult.setTotal(total);
		queryResult.setData(treeList);
		return queryResult;
	}

	/**
	  * @Title: getChildListByConsId
	  * @Description: 根据用户id获取子节点
	  * @param consId
	  * @return List<ScConsElecTreeDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年1月31日 下午2:52:57
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年1月31日 下午2:52:57
	  * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private List<ScConsElecTreeDetail> getChildListByConsId(String consId) {
		List<ScConsElecTreeDetail> list = (List<ScConsElecTreeDetail>)dao.getBySql("scConsElectricity.sql.getChildrenListByConsId",consId);
		return list;
	}

	/**
	  * @Title: getParentListByParams
	  * @Description: 获取用户历史用电表格树根节点
	  * @param scConsElecTreeVo
	  * @return List<ScConsElecTreeDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年1月31日 下午2:46:28
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年1月31日 下午2:46:28
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private List<ScConsElecTreeDetail> getParentListByParams(ScConsElecTreeVo scConsElecTreeVo) throws Exception {
		Parameter.isFilterData.set(true);
		List<ScConsElecTreeDetail> list = (List<ScConsElecTreeDetail>)dao.getBySql("scConsElectricity.sql.getParentListByParams",scConsElecTreeVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(list);
		return list;
	}

	/**
	  * @Title: getTreeCountByParams
	  * @Description: 查询历史用电信息根节点数量
	  * @param scConsElecTreeVo
	  * @return Integer
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年1月31日 下午2:32:15
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年1月31日 下午2:32:15
	  * @since  1.0.0
	 */
	private Integer getTreeCountByParams(ScConsElecTreeVo scConsElecTreeVo) {
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scConsElectricity.sql.getTreeCountByParams",scConsElecTreeVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	/**
	 * 导出选定的用户年份数据 
	 * 结算管理-历史用电信息、用户档案-历史用电信息  共用此导出方法
	 */
	@Override
	public void exportExcel(String ids, String consName, String voltCode, String elecTypeCode, String year, String consId, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//处理ids字符串，获取导出数据
		List<List<ImportVo>> resultList = getExportElec(ids, consName, voltCode, elecTypeCode, year, consId);
		//获取模版文件
		String excelPath=request.getSession().getServletContext().getRealPath("/plugins/cloud-purchase-web/archives/elec/用户历史用电量信息导出.xlsx");//模板路径
		response.reset();//去除空白行
		response.setContentType("application/vnd.ms-excel;charset=gb2312");
		String newName = URLEncoder.encode("历史用电量信息导出" + System.currentTimeMillis() + ".xlsx","UTF-8"); //设置导出的文件名称
		request.setCharacterEncoding("UTF-8");//设置request的编码方式，防止中文乱码
    	response.setHeader("Content-Disposition","attachment;filename=\"" + newName + "\"");  
    	InputStream fio = new FileInputStream(excelPath);//将模板读入流
    	ServletOutputStream out = response.getOutputStream();
    	try {  
        	XSSFWorkbook workbook=new XSSFWorkbook(fio);//创建excel(xlsx格式)
			XSSFSheet sheet=workbook.getSheetAt(0);     //创建工作薄sheet
			// 数据 Excel 所需字段
            String[] str = {"Name","ElecTypeCodeName","VoltCodeName","Ym","MonthPq","PeakPq","PlainPq","ValleyPq","OverPeakPq","DoublePq",
							"MonthAmt","MonthAvgPrc","PeakAmt","PlainAmt","ValleyAmt","OverPeakAmt","DoubleAmt","BaseAmt","ForceAmt","LevyAmt","OtherAmt"};
			int rowCount = 1; //第二行存数据
			int cellCount = 0;//第一列开始		getName（）；
			int combine = 1;  //合并起始行号
			//用户行合并,根据用户年份合并
			for( List<ImportVo> monthList : resultList){
				for(int i = 0 ; i < monthList.size() ; i++){
					ImportVo importVo = monthList.get(i);
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
		            for(int j = 0 ; j < str.length ; j++){
		            	Cell borderCell = row.createCell(cellCount++);					//创建列
		            	borderCell.setCellStyle(borderStyle);		  					//单元格样式	
		            	
		            	Class<? extends ImportVo> importVoClass = importVo.getClass();	//拼接get方法，取对象里的值
		            	Method method = importVoClass.getMethod("get"+str[j]);
		            	
	            		if( method.invoke(importVo) != null){
	            			String value = method.invoke(importVo).toString();
	            			borderCell.setCellValue(value);							//列数据
	            		}else{
	            			borderCell.setCellValue("");							//列数据
	            		}
		            }
		            rowCount ++;
		            cellCount = 0;
				}
				CellRangeAddress region = new CellRangeAddress(combine,combine+monthList.size()-1,0,0);
				CellRangeAddress region1 = new CellRangeAddress(combine,combine+monthList.size()-1,1,1);
				CellRangeAddress region2 = new CellRangeAddress(combine,combine+monthList.size()-1,2,2);
				sheet.addMergedRegion(region);
				sheet.addMergedRegion(region1);
				sheet.addMergedRegion(region2);
				combine += monthList.size();
			}
			workbook.write(out);
            out.flush();  
        } catch (FileNotFoundException e) {
			System.out.println("历史用电量模板没有找到");
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
	  * @Title: getExportElec
	  * @Description: 根据ids获取勾选的用户历史用电信息   导出调用的方法
	  * @param ids
	  * @return Map<String,List<MonthElecDetail>>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月4日 下午2:27:29
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月4日 下午2:27:29
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private List<List<ImportVo>> getExportElec(String ids, String consName, String voltCode, String elecTypeCode ,String year, String consId) throws Exception {
		List<String> parentIds = new ArrayList<String>();
		List<String> childIds = new ArrayList<String>();
		Map<String,List<ImportVo>> map = new HashMap<String,List<ImportVo>>();
		List<List<ImportVo>> resultList = new ArrayList<List<ImportVo>>();
		if(!"".equals(ids)){
			String[] idArray = ids.split("\\*");
			if(idArray[0].length() > 0){
				parentIds.addAll(Arrays.asList(idArray[0].split(",")));
			}
			if(!ids.endsWith("*")){
				if(idArray[1].length() > 0){
					childIds.addAll(Arrays.asList(idArray[1].split(",")));
				}
			}
			
			if(parentIds != null && childIds != null){
				for(int i = childIds.size() ; i > 0 ; i--){
					for(String parentId : parentIds){
						if (childIds.get(i-1).startsWith(parentId)){
							childIds.remove(i-1);
							break;
						}
					}
				}
			}
			//根据用户获取用户历史用电量信息
			if(parentIds != null && parentIds.size() > 0){
				//通过cons_id数组，批量获取MonthElecDetail，MonthElecDetail.getYm()中保存“用户名+年份”
				List<ImportVo> list = (List<ImportVo>) dao.getBySql("scConsElectricity.sql.getElecDetailByConsId", parentIds.toArray());
				//码表转换
				ConvertListUtil.convert(list);
				//根据MonthElecDetail.getYm()对查询结果list进行分类处理，存放于map中
				for(ImportVo obj : list){
					String name = obj.getConsId().toString() + obj.getVoltCode().toString();
					if(map.containsKey(name)){
						
						List<ImportVo> list2 = map.get(name);
						
						list2.add(obj);
					}else{
						List<ImportVo> detailList = new ArrayList<ImportVo>();
						detailList.add(obj);
						map.put(name, detailList);
						resultList.add(detailList);
					}
				}
			}
			
		}
		
		//根据用户及具体年份获取用户历史用电信息
		if("".equals(ids) || childIds != null && childIds.size() > 0){
			List<ImportVo> list = new ArrayList<>();
			Map<String, Object> hashMap = new HashMap<>();
			if("".equals(ids)){
				hashMap.put("consName", consName);
				hashMap.put("voltCode", voltCode);
				hashMap.put("elecTypeCode", elecTypeCode);
				hashMap.put("childIds", "");
				hashMap.put("year", year);
				hashMap.put("consId", consId);
			}else{
				hashMap.put("childIds", childIds.toArray());
			}
			Parameter.isFilterData.set(true);
			list = (List<ImportVo>) dao.getBySql("scConsElectricity.sql.getElecDetailByYears", hashMap);
			Parameter.isFilterData.set(false);
			ConvertListUtil.convert(list);
			for(ImportVo obj : list){
				String name = obj.getConsId().toString() + obj.getVoltCode().toString();
				if(map.containsKey(name)){
					List<ImportVo> list2 = map.get(name);
					
					list2.add(obj);
				}else{
					List<ImportVo> detailList = new ArrayList<ImportVo>();
					detailList.add(obj);
					map.put(name, detailList);
					resultList.add(detailList);
				}
			}
		}
		return resultList;
	}

	/**
	 * 
	 * @return 
	 * @Title: checkOutCll<br/>
	 * @Description:历史用电量Excel导入<br/>
	 * void
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年4月13日 下午2:12:11
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年11月30日 下午2:12:11
	 * @since  1.0.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional()
	public void importElecFromExcel(List<ImportVo> consElecList) throws Exception {
		List<ScConsElectricity> addList = new ArrayList<ScConsElectricity>();		//新增数据的集合
		List<ScConsElectricity> updateList = new ArrayList<ScConsElectricity>();	//修改数据的集合
		
		//数据码表逆转换
		ConvertListUtil.reverseConvert(consElecList);
		
		//根据用户名，查询用户id、电压等级、用电类型 (excel存的用户名，放在了实体类的consId字段)		—— by LiXinze改
		Parameter.isFilterData.set(false);
		List<Map<String, String>> consList = (List<Map<String, String>>) dao.getBySql("scConsElectricity.sql.getImportConsId", consElecList);
		Parameter.isFilterData.set(true);
		
		//consMap 保存用户信息，数据校验用， Map<用户名, Map<用户名+电压等级 , 用户Id>>		—— by LiXinze改
		Map<String, Map<String,String>> consMap = new HashMap<String, Map<String,String>>();
		for (Map<String, String> map : consList) {
			if(consMap.containsKey(map.get("consName"))){
				consMap.get(map.get("consName")).put(map.get("consName") + map.get("voltCode"),  map.get("consId"));
			}else{
				Map<String,String> voltMap = new HashMap<String,String>();
				voltMap.put(map.get("consName") + map.get("voltCode") , map.get("consId"));
				consMap.put(map.get("consName"), voltMap);
			}
		}
		
		//校验单元格
		Map<String, Object> checkOutCllData = checkOutCll(consElecList, consMap);
		//去空数据后的 数据集合
		consElecList = (List<ImportVo>) checkOutCllData.get("elecList");
		
		//查所有数据
		Parameter.isFilterData.set(false);
		List<ScConsElectricity> allData = (List<ScConsElectricity>) dao.getBySql("scConsElectricity.sql.getAllData", null);
		Parameter.isFilterData.set(true);
		
		//遍历 数据库所有数据拼接 idym : bean
		Map<String, Object> allDataMap = new HashMap<>();
		for (ScConsElectricity scConsElectricity : allData) {
			String cId = scConsElectricity.getConsId() == null ? "" : (scConsElectricity.getConsId()).toString();
			String cYm = scConsElectricity.getYm() == null ? "" : (scConsElectricity.getYm().toString());
			allDataMap.put(cId+cYm, scConsElectricity);
		}
		Map<String, ScConsElectricity> addMap = new HashMap<String, ScConsElectricity>();
		for (ImportVo cellData : consElecList) {
			ScConsElectricity scConsElectricity = new ScConsElectricity();
			BeanUtils.copyProperties(cellData, scConsElectricity);
			
			//数据导入时需要计算实际用电量、总电费，一并保存	——修改 byLixinze
			BigDecimal overPeakPq = scConsElectricity.getOverPeakPq() != null ? scConsElectricity.getOverPeakPq() : BigDecimal.ZERO;
			BigDecimal peakPq = scConsElectricity.getPeakPq() != null ? scConsElectricity.getPeakPq() : BigDecimal.ZERO;
			BigDecimal doublePq = scConsElectricity.getDoublePq() != null ? scConsElectricity.getDoublePq() : BigDecimal.ZERO;
			BigDecimal valleyPq = scConsElectricity.getValleyPq() != null ? scConsElectricity.getValleyPq() : BigDecimal.ZERO;
			BigDecimal plainPq = scConsElectricity.getPlainPq() != null ? scConsElectricity.getPlainPq() : BigDecimal.ZERO;
			BigDecimal practicalPq = overPeakPq.add(peakPq).add(doublePq).add(valleyPq).add(plainPq);
			
			BigDecimal peakAmt = scConsElectricity.getPeakAmt() != null ? scConsElectricity.getPeakAmt() : BigDecimal.ZERO;
			BigDecimal plainAmt = scConsElectricity.getPlainAmt() != null ? scConsElectricity.getPlainAmt() : BigDecimal.ZERO;
			BigDecimal valleyAmt = scConsElectricity.getValleyAmt() != null ? scConsElectricity.getValleyAmt() : BigDecimal.ZERO;
			BigDecimal overPeakAmt = scConsElectricity.getOverPeakAmt() != null ? scConsElectricity.getOverPeakAmt() : BigDecimal.ZERO;
			BigDecimal doubleAmt = scConsElectricity.getDoubleAmt() != null ? scConsElectricity.getDoubleAmt() : BigDecimal.ZERO;
			BigDecimal baseAmt = scConsElectricity.getBaseAmt() != null ? scConsElectricity.getBaseAmt() : BigDecimal.ZERO;
			BigDecimal forceAmt = scConsElectricity.getForceAmt() != null ? scConsElectricity.getForceAmt() : BigDecimal.ZERO;
			BigDecimal levyAmt = scConsElectricity.getLevyAmt() != null ? scConsElectricity.getLevyAmt() : BigDecimal.ZERO;
			BigDecimal otherAmt = scConsElectricity.getOtherAmt() != null ? scConsElectricity.getOtherAmt() : BigDecimal.ZERO;
			BigDecimal totalAmt = peakAmt.add(plainAmt).add(valleyAmt).add(overPeakAmt).add(doubleAmt).add(baseAmt).add(forceAmt).add(levyAmt).add(otherAmt);
			
			scConsElectricity.setPracticalPq(practicalPq);
			scConsElectricity.setTotalAmt(totalAmt);
			
			//遍历 表格数据 idym : bean
			String idYm = (cellData.getConsId().toString()) + (cellData.getYm().toString());
			if(!allDataMap.containsKey(idYm)){//表格idYm 匹配数据库的 idYm  false就插入
				if(addMap.containsKey(idYm)){
					addMap.remove(idYm);
				}else{
					//新增时，不足12条数据，补全12条 .去除，导入数据月。数据库只有一个 月份的数据时 需要补全月份1-12 不然导出出错
 					Map<String, ScConsElectricity> addMapChild = fillMonth(cellData);
					addMap.putAll(addMapChild);
				}
				//插入
				addList.add(scConsElectricity);
			}else{
				//更新
				updateList.add(scConsElectricity);
			}
		}  
		//遍历 添加新增对象
		Iterator<Entry<String, ScConsElectricity>> it = addMap.entrySet().iterator();  
		while (it.hasNext()) {  
		   Map.Entry entry = (Map.Entry) it.next();  
		   addList.add((ScConsElectricity)entry.getValue());
		}
		//插入
		if(addList != null && addList.size() > 0){
			dao.saveList(addList);
		}
		//更新
		if(updateList != null && updateList.size() > 0){
			dao.updateBySql("scConsElectricity.sql.updataScConsElec", updateList);
		}
		
		//更新大数据表中实际用电量数据
		bigDataService.saveOrUpdatePowerPqByHistoryElecs(consElecList);
	}
	
	/**
	 * @Title: checkOutCll<br/>
	 * @Description:导入：校验单月格内容<br/>
	 * @return void
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年4月13日 下午2:12:11
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年11月30日 下午2:12:11
	 * @since  1.0.0
	 */
	private Map<String ,Object> checkOutCll(List<ImportVo> elecList, Map<String, Map<String, String>> consMap){
		//数字正则表达式 
		Pattern patternNumber = Pattern.compile("^\\d{1,9}(\\.\\d*)?$");
		//Pattern patternNumber = Pattern.compile("^\\d{1,9}(\\.\\d*)?$");
	    // 编译正则表达式 电量最多精确到小数点后3位
	    Pattern pattern = Pattern.compile("^\\d{1,9}(?:\\.\\d{4,})?$");
	    // 编译正则表达式 电费最多精确到小数点后2位
	    Pattern patternM = Pattern.compile("^\\d{1,9}(?:\\.\\d{3,})?$");
	    //验证 年月格式
	    Pattern patternD = Pattern.compile("[1-9]\\d{3}(0[1-9]|1[0-2])");
		Matcher matcher;
	   
		//每行单元格 校验
		for(int i = elecList.size()-1; i >= 0; i--){
			int flagNum = 0;									//用于标记所有电量电费单元格是否填写，都没填：remove
			ImportVo scConsElectricity = elecList.get(i);		//行数据
			String name = scConsElectricity.getConsId();		//模板的用户名单元格，放在了实体类的id字段上
			String voltCode = scConsElectricity.getVoltCode();	//电压等级
			String ym = scConsElectricity.getYm();				//年月
		
			//验证用户名必填
			if(name == null || "".equals(name)){
				throw new RuntimeException("</br>用户名称不能为空，请您填写！");				
			}
			//验证电压等级必填
			if(voltCode == null || "".equals(voltCode)){
				throw new RuntimeException("</br>\"" + name + "\"的电压等级为空，请您填写！");	
			}
			//验证年月必填
			if(ym == null || "".equals(ym)){
				throw new RuntimeException("</br>“" + name + "”用户，电压等级为：" + scConsElectricity.getVoltCodeName() + "</br>年月未填，请您填写！");	
			}
			
			Matcher matcherD = patternD.matcher(ym);
			if(!matcherD.matches()){
				if(!matcherD.matches()){
					throw new RuntimeException("</br>“" + name + "”用户，电压等级为：" + scConsElectricity.getVoltCodeName() + "</br>年月格式不正确，应为6位数字：4位年2位月份！");	
				}
			}
			//验证 用户名
			if(!consMap.containsKey(name)){
				throw new RuntimeException("</br>不存在“" + name + "”用户</br>用户名称需与用户档案保持一致！");	
			}else{
				//赋值对应的用户id
				if(!consMap.get(name).containsKey(name + voltCode)){
					throw new RuntimeException("</br>“" + name + "”用户不存在" + scConsElectricity.getVoltCodeName() + "的电压等级</br>用户的电压等级需与用户档案保持一致！");
				}else{
					scConsElectricity.setConsId(consMap.get(name).get(name + voltCode));
				}
			}
			
			if(scConsElectricity.getPeakPq() == null){
				flagNum++;
			}else{
//				matcher = patternNumber.matcher((scConsElectricity.getPeakPq()).toString());
//				if(!matcher.matches()){
//					throw new RuntimeException("</br>“" + name + "”用户，电压等级为：" + scConsElectricity.getVoltCodeName() + "</br>" + 
//						ym.substring(0,4) + "年" + ym.substring(4) + "月的峰时电量应为三位小数的纯数字！");	
//				}
				matcher = pattern.matcher((scConsElectricity.getPeakPq()).toString());
				if(matcher.matches()){
					scConsElectricity.setPeakPq(scConsElectricity.getPeakPq().setScale(3, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getPlainPq() == null){
				flagNum++;
			}else{
//				matcher = patternNumber.matcher((scConsElectricity.getPlainPq()).toString());
//				if(!matcher.matches()){
//					throw new RuntimeException("</br>“" + name + "”用户，电压等级为：" + scConsElectricity.getVoltCodeName() + "</br>" + 
//							ym.substring(0,4) + "年" + ym.substring(4) + "月的峰时电量应为三位小数的纯数字！");	
//					//throw new RuntimeException("Excel第"+ +"行:平时电量仅支持三位小数纯数字！");	
//				}
				matcher = pattern.matcher((scConsElectricity.getPlainPq()).toString());
				if(matcher.matches()){
					scConsElectricity.setPlainPq(scConsElectricity.getPlainPq().setScale(3, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getValleyPq() == null){
				flagNum++;
			}else {
//				matcher = patternNumber.matcher((scConsElectricity.getValleyPq()).toString());
//				if(!matcher.matches()){
//					throw new RuntimeException("Excel第"+(i+4)+"行:谷时电量仅支持三位小数纯数字！");	
//				}
				matcher = pattern.matcher((scConsElectricity.getValleyPq()).toString());
				if(matcher.matches()){
					scConsElectricity.setValleyPq(scConsElectricity.getValleyPq().setScale(3, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getOverPeakPq() == null){
				flagNum++;
			}else{
//				matcher = patternNumber.matcher((scConsElectricity.getOverPeakPq()).toString());
//				if(!matcher.matches()){
//					throw new RuntimeException("Excel第"+(i+4)+"行:尖峰电量仅支持三位小数纯数字！");	
//				}
				matcher = pattern.matcher((scConsElectricity.getOverPeakPq()).toString());
				if(matcher.matches()){
					scConsElectricity.setOverPeakPq(scConsElectricity.getOverPeakPq().setScale(3, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getDoublePq() == null){
				flagNum++;
			}else{
//				matcher = patternNumber.matcher((scConsElectricity.getDoublePq()).toString());
//				if(!matcher.matches()){
//					throw new RuntimeException("Excel第"+(i+4)+"行:双蓄电量仅支持三位小数纯数字！");	
//				}
				matcher = pattern.matcher((scConsElectricity.getDoublePq()).toString());
				if(matcher.matches()){
					scConsElectricity.setDoublePq(scConsElectricity.getDoublePq().setScale(3, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getPeakAmt() == null){
				flagNum++;
			}else{
				matcher = patternNumber.matcher((scConsElectricity.getPeakAmt()).toString());
				if(!matcher.matches()){
					throw new RuntimeException("Excel第"+(i+4)+"行:峰时电费仅支持两位小数纯数字！");	
				}
				matcher = patternM.matcher((scConsElectricity.getPeakAmt()).toString());
				if(matcher.matches()){
					scConsElectricity.setPeakAmt(scConsElectricity.getPeakAmt().setScale(2, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getPlainAmt() == null){
				flagNum++;
			}else{
				matcher = patternNumber.matcher((scConsElectricity.getPlainAmt()).toString());
				if(!matcher.matches()){
					throw new RuntimeException("Excel第"+(i+4)+"行:平时电费仅支持两位小数纯数字！");	
				}
				matcher = patternM.matcher((scConsElectricity.getPlainAmt()).toString());
				if(matcher.matches()){
					scConsElectricity.setPlainAmt(scConsElectricity.getPlainAmt().setScale(2, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getValleyAmt() == null){
				flagNum++;
			}else{
				matcher = patternNumber.matcher((scConsElectricity.getValleyAmt()).toString());
				if(!matcher.matches()){
					throw new RuntimeException("Excel第"+(i+4)+"行:谷时电费仅支持两位小数纯数字！");	
				}
				matcher = patternM.matcher((scConsElectricity.getValleyAmt()).toString());
				if(matcher.matches()){
					scConsElectricity.setValleyAmt(scConsElectricity.getValleyAmt().setScale(2, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getOverPeakAmt() == null){
				flagNum++;
			}else{
				matcher = patternNumber.matcher((scConsElectricity.getOverPeakAmt()).toString());
				if(!matcher.matches()){
					throw new RuntimeException("Excel第"+(i+4)+"行:尖峰电费仅支持两位小数纯数字！");	
				}
				matcher = patternM.matcher((scConsElectricity.getOverPeakAmt()).toString());
				if(matcher.matches()){
					scConsElectricity.setOverPeakAmt(scConsElectricity.getOverPeakAmt().setScale(2, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getDoubleAmt() == null){
				flagNum++;
			}else{
				matcher = patternNumber.matcher((scConsElectricity.getDoubleAmt()).toString());
				if(!matcher.matches()){
					throw new RuntimeException("Excel第"+(i+4)+"行:双蓄电费仅支持两位小数纯数字！");	
				}
				matcher = patternM.matcher((scConsElectricity.getDoubleAmt()).toString());
				if(matcher.matches()){
					scConsElectricity.setDoubleAmt(scConsElectricity.getDoubleAmt().setScale(2, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getBaseAmt() == null){
				flagNum++;
			}else{
				matcher = patternNumber.matcher((scConsElectricity.getBaseAmt()).toString());
				if(!matcher.matches()){
					throw new RuntimeException("Excel第"+(i+4)+"行:基本电费仅支持两位小数纯数字！");	
				}
				matcher = patternM.matcher((scConsElectricity.getBaseAmt()).toString());
				if(matcher.matches()){
					scConsElectricity.setBaseAmt(scConsElectricity.getBaseAmt().setScale(2, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getForceAmt() == null){
				flagNum++;
			}else{
				matcher = patternNumber.matcher((scConsElectricity.getForceAmt()).toString());
				if(!matcher.matches()){
					throw new RuntimeException("Excel第"+(i+4)+"行:力调电费仅支持两位小数纯数字！");	
				}
				matcher = patternM.matcher((scConsElectricity.getForceAmt()).toString());
				if(matcher.matches()){
					scConsElectricity.setForceAmt(scConsElectricity.getForceAmt().setScale(2, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getLevyAmt() == null){
				flagNum++;
			}else{
				matcher = patternNumber.matcher((scConsElectricity.getLevyAmt()).toString());
				if(!matcher.matches()){
					throw new RuntimeException("Excel第"+(i+4)+"行:代征电费仅支持两位小数纯数字！");	
				}
				matcher = patternM.matcher((scConsElectricity.getLevyAmt()).toString());
				if(matcher.matches()){
					scConsElectricity.setLevyAmt(scConsElectricity.getLevyAmt().setScale(2, RoundingMode.HALF_UP));
				}
			}
			
			if(scConsElectricity.getOtherAmt() == null){
				flagNum++;
			}else{
				matcher = patternNumber.matcher((scConsElectricity.getOtherAmt()).toString());
				if(!matcher.matches()){
					throw new RuntimeException("Excel第"+(i+4)+"行:其他电费仅支持两位小数纯数字！");	
				}
				matcher = patternM.matcher((scConsElectricity.getOtherAmt()).toString());
				if(matcher.matches()){
					scConsElectricity.setOtherAmt(scConsElectricity.getOtherAmt().setScale(2, RoundingMode.HALF_UP));
				}
			}
			//去除 空数据的集合
			if(flagNum == 14){
				elecList.remove(i);
			}
		}
		Map<String, Object> result = new HashMap<>();
		//去空后的集合
		result.put("elecList", elecList);
		return result;
		
	}
	/**
	 * 
	 * @Title: fillMonth<br/>
	 * @Description:新增时，不足12条数据，补全12条 .去除，导入数据月<br/>
	 * @param cellData
	 * @return
	 * ScConsElectricity
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年4月14日 下午3:51:51
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年4月14日 下午3:51:51
	 * @since  1.0.0
	 */
	private Map<String, ScConsElectricity> fillMonth(ImportVo cellData) {
		Map<String, ScConsElectricity> map = new HashMap<String,ScConsElectricity>();
		String consId = cellData.getConsId();
		String year = cellData.getYm().substring(0,4);
		String month = cellData.getYm().substring(4);
		String ym;
		int mon = Integer.parseInt(month);
		for (int i = 0;i<12;i++) {
			if((i+1)==mon){
				continue;
			}
			ScConsElectricity scConsElectricity = new ScConsElectricity();
			scConsElectricity.setConsId(consId);
			if((i+1)<10){
				ym = year+"0"+(i+1);
				scConsElectricity.setYm(ym);
			}else{
				ym = year+(i+1);
				scConsElectricity.setYm(year+(i+1));
			}
			map.put(consId+ym, scConsElectricity);
		}		
		return map;
	}
	/**
	 * @Title: practicalPowerPage<br/>
	 * @Description:实际用电信息列表
	 * @param scConsElectricityVo
	 * @return
	 * QueryResult<ScConsElectricity>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月19日 下午12:28:22
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月19日 下午12:28:22
	 * @since  1.0.0
	 */
	@Override
	public QueryResult<ScConsElecTreeDetail> practicalPowerPage(ScConsElectricityVo scConsElectricityVo) {
		QueryResult<ScConsElecTreeDetail> queryResult = new QueryResult<ScConsElecTreeDetail>();
		long total = practicalPowerPageCount(scConsElectricityVo);
		List<ScConsElecTreeDetail> scConsElectricityList = practicalPowerPageData(scConsElectricityVo);
		queryResult.setTotal(total);
		queryResult.setData(scConsElectricityList);
		return queryResult;
	}
	//实际用电量数据
	@SuppressWarnings("unchecked")
	private List<ScConsElecTreeDetail> practicalPowerPageData(ScConsElectricityVo scConsElectricityVo) {
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(scConsElectricityVo == null){
			scConsElectricityVo = new ScConsElectricityVo();
		}
		Parameter.isFilterData.set(true);
		List<ScConsElecTreeDetail> bySql = (List<ScConsElecTreeDetail>) dao.getBySql("scConsElectricity.sql.practicalPowerPageData",scConsElectricityVo);
		if(bySql != null && bySql.size() > 0){
			//根据查询的月份获取该月份下有效合同用户的委托电量
			List<Map<String,Object>> reportList = (List<Map<String, Object>>) dao.getBySql("scConsElectricity.sql.getReporotPqByYmList", bySql);
			Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
			for(Map<String,Object> reportMap : reportList){
				map.put(reportMap.get("ym").toString(), BigDecimal.valueOf(Double.valueOf(reportMap.get("reportPq").toString())));
			}
			for(ScConsElecTreeDetail detail : bySql){
				detail.setReportPq(map.get(detail.getYm()));
			}
		}
		Parameter.isFilterData.set(false);
		
		return bySql;
	}
	//实际用电量数据条数
	private Integer practicalPowerPageCount(ScConsElectricityVo scConsElectricityVo) {
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scConsElectricity.sql.practicalPowerPageCount",scConsElectricityVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	/**
	 * @Title: getScConsElectricityById<br/>
	 * @Description: 根据交易年月查实际用电量录入信息
	 * @param id
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月19日 下午2:52:11
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月19日 下午2:52:11
	 * @since  1.0.0
	 */
	@Override
	public ScConsElecTreeDetail getPraPqTranYm(String ym) {
		Map<String,String> param = new HashMap<>();
		param.put("ym", ym);
		Parameter.isFilterData.set(true);
		ScConsElecTreeDetail data = (ScConsElecTreeDetail) dao.getOneBySQL("scConsElectricity.sql.getPraPqTranYm",param);
		Parameter.isFilterData.set(false);
		//江苏结算专用，若不是江苏结算此代码无用
		SmSettlementMonth settlementMonth = smSettlementMonthService.getSmSettlementMonthByYm(ym.replace("-", ""));
		if(settlementMonth != null) {
			if(data == null) {
				data = new ScConsElecTreeDetail();
			}
			data.setSettlementId(settlementMonth.getId());
			data.setIndustryPq(settlementMonth.getIndustryPq());
			data.setBusinessPq(settlementMonth.getBusinessPq());
		}
		return data;
	}
	/**
	 * @Title: pqDataGrid<br/>
	 * @Description: 用户月度实际用电量列表
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月21日 上午10:19:02
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月21日 上午10:19:02
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@Override
	public QueryResult<ScConsElecTreeDetail> pqDataGrid(ScConsElectricityVo scConsElectricityVo) throws Exception {
		QueryResult<ScConsElecTreeDetail> queryResult = new QueryResult<ScConsElecTreeDetail>();
		long total = getPqDataGridCount(scConsElectricityVo);
		List<ScConsElecTreeDetail> data = getPqDataGridData(scConsElectricityVo);
		ConvertListUtil.convert(data);
		queryResult.setTotal(total);
		queryResult.setData(data);
		return queryResult;
	}
	
	private Integer getPqDataGridCount(ScConsElectricityVo scConsElectricityVo) {
		Parameter.isFilterData.set(true);
		Integer data = (Integer) dao.getOneBySQL("scConsElectricity.sql.getPqDataGridCount",scConsElectricityVo);
		Parameter.isFilterData.set(false);
		return data;
	}
	
	@SuppressWarnings("unchecked")
	private List<ScConsElecTreeDetail> getPqDataGridData(ScConsElectricityVo scConsElectricityVo) {
		Parameter.isFilterData.set(true);
		List<ScConsElecTreeDetail> data = (List<ScConsElecTreeDetail>) dao.getBySql("scConsElectricity.sql.getPqDataGrid",scConsElectricityVo);
		Parameter.isFilterData.set(false);
		return data;
	}
	
	/**
	 * @Title: savePracticalPqJs
	 * @Description: 江苏的实际用电量录入的保存，比savePracticalPq方法多了保存结算主表功能，目的是保存工业电量和商业电量
	 * @param scConsElectricityVo 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年4月23日 下午5:04:34
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年4月23日 下午5:04:34
	 * @since  1.0.0
	 */
	@Override
	@Transactional
	public void savePracticalPqJs(ScConsElectricityVo scConsElectricityVo) {
		//保存结算主表信息，即保存工业电量和商业电量
		if(scConsElectricityVo.getSmSettlementMonth() != null && scConsElectricityVo.getSmSettlementMonth().getYm() != null) {
			SmSettlementMonth settlementMonthEntity = smSettlementMonthService.getSmSettlementMonthByYm(scConsElectricityVo.getSmSettlementMonth().getYm());
			if(settlementMonthEntity != null ||  scConsElectricityVo.getSmSettlementMonth().getIndustryPq() != null) {
				if(settlementMonthEntity != null) {
					scConsElectricityVo.getSmSettlementMonth().setId(settlementMonthEntity.getId());
					dao.update(scConsElectricityVo.getSmSettlementMonth());
				}else {
					dao.save(scConsElectricityVo.getSmSettlementMonth());
				}
			}
		}
		this.savePracticalPq(scConsElectricityVo.getScConsElectricitieList());
	}
	
	/**
	 * @Title: savePracticalPq<br/>
	 * @Description:实际用电量录入 新增/编辑
	 * @param scConsElectricityList
	 * @return Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月21日 下午4:08:30
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月21日 下午4:08:30
	 * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void savePracticalPq(List<ScConsElectricity> scConsElectricityList) {
		if(scConsElectricityList != null && scConsElectricityList.size() >0){
			//获取涉及的用户在此年份的所有记录
			String year = scConsElectricityList.get(0).getYm().substring(0,4);
			List<String> consIds = new ArrayList<String>();	//涉及数据变更的用户ids
			Map<String,List<ScConsElectricity>> map = new HashMap<String,List<ScConsElectricity>>();	//根据用户id对查询结果分类
			for(ScConsElectricity elec : scConsElectricityList){
				consIds.add(elec.getConsId());
			}
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("consIds", consIds);
			queryMap.put("year", year);
			List<ScConsElectricity> list = (List<ScConsElectricity>) dao.getBySql("scConsElectricity.sql.getScConsElectricityByConsIds", queryMap);
			for(ScConsElectricity elec : list){
				if(map.containsKey(elec.getConsId())){
					map.get(elec.getConsId()).add(elec);
				}else{
					List<ScConsElectricity> elecList = new ArrayList<ScConsElectricity>();
					elecList.add(elec);
					map.put(elec.getConsId(), elecList);
				}
			}
			//判断是否存在用户记录，若为为新增用户，进行月份补全，为历史用电量服务
			List<ScConsElectricity> saveList = new ArrayList<ScConsElectricity>();	//最终进行保存的数据List
			for(ScConsElectricity elec : scConsElectricityList){
				if(map.containsKey(elec.getConsId())){
					saveList.add(elec);
				}else{
					for(int i = 1 ; i < 13 ; i++){ //保存用户1-12个月的用电记录，为历史用电量服务
						String ym = year + (i < 10 ? "0"+i : i);
						if(elec.getYm().equals(ym)){
							saveList.add(elec);
						}else{
							ScConsElectricity consElectricity = new ScConsElectricity();
							consElectricity.setConsId(elec.getConsId());
							consElectricity.setYm(ym);
							saveList.add(consElectricity);
						}
					}
				}
			}
			dao.saveList(saveList);
		}
	}
	
	/**
	 * @Title: deleteElectricity<br/>
	 * @Description:实际用电量删除方法
	 * @param ym :yyyyMM
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月22日 上午9:46:54
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月22日 上午9:46:54
	 * @since  1.0.0
	 */
	@Override
	public void deleteElectricity(ScConsElectricityVo scConsElectricityVo) {
		dao.deleteBySql("scConsElectricity.sql.deleteElectricity",scConsElectricityVo);
	}

}