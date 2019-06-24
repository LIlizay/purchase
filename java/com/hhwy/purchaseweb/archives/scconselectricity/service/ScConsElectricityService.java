package com.hhwy.purchaseweb.archives.scconselectricity.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ImportVo;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.MonthElecDetail;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ScConsElecTreeDetail;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ScConsElecTreeVo;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ScConsElectricityVo;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.YearElecDetail;
import com.hhwy.selling.domain.ScConsElectricity;
;

/**
 * IScConsElectricityService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface ScConsElectricityService{
	
	/**
	 * 分页获取对象ScConsElectricity
	 * @param ScConsElectricityVo
	 * @return QueryResult
	 */
	public QueryResult<ScConsElectricity> getScConsElectricityByPage(ScConsElectricityVo scConsElectricityVo) throws Exception;

	/**
	 * 根据查询条件获取对象ScConsElectricity列表
	 * @param ScConsElectricityVo
	 * @return List
	 */
	public List<ScConsElectricity> getScConsElectricityListByParams(ScConsElectricityVo scConsElectricityVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象ScConsElectricity数量
	 * @param ScConsElectricityVo
	 * @return Integer
	 */
	public Integer getScConsElectricityCountByParams(ScConsElectricityVo scConsElectricityVo);
	
	/**
	 * 根据查询条件获取单个对象ScConsElectricity
	 * @param ScConsElectricityVo
	 * @return ScConsElectricity
	 */
	public ScConsElectricity getScConsElectricityOneByParams(ScConsElectricityVo scConsElectricityVo) throws Exception;
	
	/**
	 * 根据ID获取对象ScConsElectricity
	 * @param ID
	 * @return ScConsElectricity
	 */
	public ScConsElectricity getScConsElectricityById(String id);
	
	/**
	 * 添加对象ScConsElectricity列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveScConsElectricityList(List<ScConsElectricity> scConsElectricityList);
	
	/**
	 * 删除对象ScConsElectricity
	 * @param id数据组
	 */
	public void deleteScConsElectricity(ScConsElectricityVo scConsElectricityVo);
	
	
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
	public void deleteScConsElecByConsIds(String[] consIds);

	/**
	 * 
	 * @Title: saveScConsElectricityList<br/>
	 * @Description: 保存用户历史用电信息列表<br/>
	 * @param scConsElectricityVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月23日 上午12:33:08
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月23日 上午12:33:08
	 * @return 
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public void saveScConsElectricityList(ScConsElectricityVo scConsElectricityVo);
	
	/**
	 * 
	 * @Title: getYearElecDetailList<br/>
	 * @Description: 查询年度历史用电<br/>
	 * @param scConsElectricityVo
	 * @return
	 * @throws Exception <br/>
	 * List<YearElecDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月23日 上午1:42:16
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月23日 上午1:42:16
	 * @since  1.0.0
	 */
	public List<YearElecDetail> getYearElecDetailList(ScConsElectricityVo scConsElectricityVo) throws Exception;
	
	/**
	 * 
	 * @Title: getMonthElecList<br/>
	 * @Description: 查询月份历史用电信息<br/>
	 * @param scConsElectricityVo
	 * @return <br/>
	 * List<ScConsElectricity><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月23日 上午1:52:11
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月23日 上午1:52:11
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public List<MonthElecDetail> getMonthElecList(ScConsElectricityVo scConsElectricityVo) throws Exception;
	
	/**
	 * 
	 * @Title: updateScConsElectricity<br/>
	 * @Description: 更新历史用电列表<br/>
	 * @param scConsElectricityVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月23日 上午1:59:08
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月23日 上午1:59:08
	 * @since  1.0.0
	 */
	public void updateScConsElectricity(ScConsElectricityVo scConsElectricityVo);

	/**
	  * @Title: getScConsElecTree
	  * @Description: 分页获取用户历史用电信息表格树
	  * @param scConsElecTreeVo
	  * @return QueryResult<ScConsElecTreeDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年1月31日 下午2:23:45
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年1月31日 下午2:23:45
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public QueryResult<ScConsElecTreeDetail> getScConsElecTree(ScConsElecTreeVo scConsElecTreeVo) throws Exception;

	/**
	  * @Title: exportExcel
	  * @Description: 导出选定的用户年份数据
	  * @param ids
	 * @param elecTypeCode 
	 * @param voltCode 
	  * @param request
	  * @param response
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月2日 下午8:33:15
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月2日 下午8:33:15
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public void exportExcel(String ids, String consName, String voltCode, String elecTypeCode, String year, String consId, HttpServletRequest request,HttpServletResponse response) throws Exception;


	/**
	 * 
	 * @return 
	 * @Title: checkOutCll<br/>
	 * @Description:历史用电量Excel导入
	 * void
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年4月13日 下午2:12:11
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年4月13日 下午2:12:11
	 * @since  1.0.0
	 */
	public void importElecFromExcel(List<ImportVo> consElecList) throws Exception;
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
	public QueryResult<ScConsElecTreeDetail> practicalPowerPage(ScConsElectricityVo scConsElectricityVo);
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
	public ScConsElecTreeDetail getPraPqTranYm(String ym);
	/**
	 * @Title: pqDataGrid<br/>
	 * @Description:用户月度实际用电量列表
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月21日 上午10:19:02
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月21日 上午10:19:02
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public QueryResult<ScConsElecTreeDetail> pqDataGrid(ScConsElectricityVo scConsElectricityVo) throws Exception;
	
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
	public void savePracticalPqJs(ScConsElectricityVo scConsElectricityVo);
	
	/**
	  * @Title:savePracticalPq
	  * @Description: 实际用电量录入 新增/编辑
	  * @param scConsElectricityList
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年8月13日 下午5:28:13
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年8月13日 下午5:28:13
	  * @since  1.0.0
	 */
	public void savePracticalPq(List<ScConsElectricity> scConsElectricityList);
	/**
	 * @Title: deleteElectricity<br/>
	 * @Description:实际用电量删除方法
	 * @param ym
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月22日 上午9:46:54
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月22日 上午9:46:54
	 * @since  1.0.0
	 */
	public void deleteElectricity(ScConsElectricityVo scConsElectricityVo);
}