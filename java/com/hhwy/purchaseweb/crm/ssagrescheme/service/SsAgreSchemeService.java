package com.hhwy.purchaseweb.crm.ssagrescheme.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SsAgreScheme;
import com.hhwy.purchaseweb.crm.ssagrescheme.domain.SmPpaVo;
import com.hhwy.purchaseweb.crm.ssagrescheme.domain.SsAgreSchemeDetail;
import com.hhwy.purchaseweb.crm.ssagrescheme.domain.SsAgreSchemeVo;

/**
 * <b>类 名 称：</b>SsAgreSchemeService<br/>
 * <b>类 描 述：</b>合同辅助设计<br/>
 * <b>创 建 人：</b>chengpeng<br/>
 * <b>修 改 人：</b>chengpeng<br/>
 * <b>修改时间：</b>2017年5月23日 下午2:09:34<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface SsAgreSchemeService{

	/**
	 * @Title: getSsAgreSchemeByPage<br/>
	 * @Description: 分页获取合同辅助设计对象<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception <br/>
	 * QueryResult<SsAgreSchemeDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:02:10
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:02:10
	 * @since  1.0.0
	 */
	public QueryResult<SsAgreSchemeDetail> getSsAgreSchemeByPage(SsAgreSchemeVo ssAgreSchemeVo) throws Exception;
	
	/**
	 * @Title: getTreeSsAgreSchemeByPage<br/>
	 * @Description: 分页获取合同辅助设计对象(树表格)<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception <br/>
	 * QueryResult<SsAgreSchemeDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:10:43
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:10:43
	 * @since  1.0.0
	 */
	public QueryResult<SsAgreSchemeDetail> getTreeSsAgreSchemeByPage(SsAgreSchemeVo ssAgreSchemeVo) throws Exception;

	/**
	 * @Title: getSsAgreSchemeListByParams<br/>
	 * @Description: 根据查询条件获取对象SsAgreScheme列表<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception <br/>
	 * List<SsAgreSchemeDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:32:13
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:32:13
	 * @since  1.0.0
	 */
	public List<SsAgreSchemeDetail> getSsAgreSchemeListByParams(SsAgreSchemeVo ssAgreSchemeVo) throws Exception;

	/**
	 * @Title: getSsAgreSchemeCountByParams<br/>
	 * @Description: 根据查询条件获取对象SsAgreScheme数量<br/>
	 * @param ssAgreSchemeVo
	 * @return <br/>
	 * Integer<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:32:30
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:32:30
	 * @since  1.0.0
	 */
	public Integer getSsAgreSchemeCountByParams(SsAgreSchemeVo ssAgreSchemeVo);
	
	/**
	 * @Title: getSsAgreSchemeOneByParams<br/>
	 * @Description: 根据查询条件获取单个对象SsAgreScheme<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception <br/>
	 * SsAgreSchemeDetail<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:32:47
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:32:47
	 * @since  1.0.0
	 */
	public SsAgreSchemeDetail getSsAgreSchemeOneByParams(SsAgreSchemeVo ssAgreSchemeVo) throws Exception;
	
	/**
	 * @Title: getSsAgreSchemeById<br/>
	 * @Description: 根据ID获取对象SsAgreScheme<br/>
	 * @param id
	 * @return <br/>
	 * SsAgreScheme<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:33:00
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:33:00
	 * @since  1.0.0
	 */
	public SsAgreScheme getSsAgreSchemeById(String id);
	
	/**
	 * @Title: saveSsAgreScheme<br/>
	 * @Description: 添加对象SsAgreScheme<br/>
	 * @param ssAgreScheme <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:33:31
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:33:31
	 * @since  1.0.0
	 */
	public void saveSsAgreScheme(SsAgreScheme ssAgreScheme);
	
	/**
	 * @Title: saveSsAgreSchemeList<br/>
	 * @Description: 添加对象SsAgreScheme列表<br/>
	 * @param ssAgreSchemeList <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:33:44
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:33:44
	 * @since  1.0.0
	 */
	public void saveSsAgreSchemeList(List<SsAgreScheme> ssAgreSchemeList);
	
	/**
	 * @Title: updateSsAgreScheme<br/>
	 * @Description: 更新对象SsAgreScheme<br/>
	 * @param ssAgreScheme <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:33:57
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:33:57
	 * @since  1.0.0
	 */
	public void updateSsAgreScheme(SsAgreScheme ssAgreScheme);
	
	/**
	 * @Title: deleteSsAgreScheme<br/>
	 * @Description: 删除对象SsAgreScheme<br/>
	 * @param ids <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:34:09
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:34:09
	 * @since  1.0.0
	 */
	public void deleteSsAgreScheme(String[] ids);
	
	/**
	  * @Title: calculate
	  * @Description: 计算方案服务
	  * @param ssAgreSchemeDetail
	  * @param transPrc
	  * @param cataPlainPrc
	  * @param kwhPlainPrc
	  * @throws Exception
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月24日 上午9:46:03
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月24日 上午9:46:03
	  * @since  1.0.0
	 */
	public void calculate(SsAgreSchemeDetail ssAgreSchemeDetail) throws Exception;
	
	/**
	 * @Title: getSsAgreSchemeListByConsName<br/>
	 * @Description: 根据客户Id查询所有方案<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception <br/>
	 * List<SsAgreSchemeDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:14:28
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:14:28
	 * @since  1.0.0
	 */
	public List<SsAgreSchemeDetail> getSsAgreSchemeListByConsName(SsAgreSchemeVo ssAgreSchemeVo) throws Exception;
	
	/**
	 * @Title: commitSsAgreScheme<br/>
	 * @Description: 提交操作，包含添加修改删除<br/>
	 * @param ssAgreSchemeVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:14:47
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:14:47
	 * @since  1.0.0
	 */
	public void commitSsAgreScheme(SsAgreSchemeVo ssAgreSchemeVo);
	
	/**
	 * @Title: getNewSchemeNameSsAgreSchemeListByParams<br/>
	 * @Description: 根据查询条件获取客户最新的方案的对象SsAgreScheme列表<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception <br/>
	 * List<SsAgreSchemeDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:15:10
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:15:10
	 * @since  1.0.0
	 */
	public List<SsAgreSchemeDetail> getNewSchemeNameSsAgreSchemeListByParams(SsAgreSchemeVo ssAgreSchemeVo) throws Exception;
	
	/**
	 * @Title: getNewSchemeNameSsAgreSchemeCountByParams<br/>
	 * @Description: 根据查询条件获取每个客户的新方案的对象SsAgreScheme数量<br/>
	 * @param ssAgreSchemeVo
	 * @return <br/>
	 * Integer<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:15:27
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:15:27
	 * @since  1.0.0
	 */
	public Integer getNewSchemeNameSsAgreSchemeCountByParams(SsAgreSchemeVo ssAgreSchemeVo);
	
	/**
	 * @Title: getSByPage<br/>
	 * @Description: 查询分页数据<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception <br/>
	 * List<SsAgreSchemeDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:15:44
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:15:44
	 * @since  1.0.0
	 */
	public List<SsAgreSchemeDetail> getSByPage(SsAgreSchemeVo ssAgreSchemeVo) throws Exception;
	
	/**
	 * @Title: getCountByConsId<br/>
	 * @Description: 根据consId查询此consId对应的数据条数<br/>
	 * @param consId
	 * @return <br/>
	 * Integer<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:18:18
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:18:18
	 * @since  1.0.0
	 */
	public Integer getCountByConsId(String consId);
	
	/**
	 * @Title: getSsAgreSchemeById<br/>
	 * @Description: 根据父节点id（最新方案）查询子节点数据，并过滤不查询父节点数据,此方法是重载方法<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception <br/>
	 * List<SsAgreSchemeDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:16:56
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:16:56
	 * @since  1.0.0
	 */
	public List<SsAgreSchemeDetail> getSsAgreSchemeById(SsAgreSchemeVo ssAgreSchemeVo) throws Exception;

	public SmPpaVo saveSmPpa(SmPpaVo smPpaVo)throws Exception;
	
	/**
	 * 
	 * @Title: outExcel
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception 
	 * List<SsAgreSchemeVo>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2017年12月4日 下午6:01:34
	 * <b>修 改 人：</b>sunqi<br/>
	 * <b>修改时间:</b>2017年12月4日 下午6:01:34
	 * @since  1.0.0
	 */
	public void outExcel(String ids,HttpServletRequest request,HttpServletResponse response) throws Exception;

	List<Map<String, Object>> getExcelDataInfo(Map<String, Object> params) throws Exception;
	
	/**
	  * @Title: getConsCheckedPro
	  * @Description: 用户考核电费、售电公司赔偿费计算
	  * @param ssAgreSchemeDetail
	  * (
	  *  consId 用户ID，用于获取此用户所属区域的电价信息
	  *  delPq:实际用电量	 	bidPrc:竞价电价		reportPq:申报电量	 	bidPq:竞价成交电量 	lcPq:分配双边电量		
	  *  punishTypeUpper: 正偏差惩罚类型("1" 赔偿)  punishUpperType:正偏差惩罚电价基准	punishUpperThreshold:正偏差允许范围 	punishUpperProp:正偏差惩罚比例 	upperPrc	正偏差惩罚协议价(人工录入)
	  *  punishTypeLower: 负偏差惩罚类型("1" 赔偿)  punishLowerType:负偏差惩罚电价基准	punishLowerThreshold:负偏差允许范围	punishLowerProp:负偏差惩罚比例  	lowerPrc    负偏差惩罚协议价(人工录入)
	  *  compensateFlag:  是否赔偿	("1" 赔偿)	 compensateType:售电公司惩罚电价基准	compensateThreshold: 赔偿域值			compensateProp: 赔偿比例值 		compensatePrc 赔偿协议价（人工录入）
	  *  
	  *  返回结果：ssAgreSchemeDetail.getConsCheckedPro 用户考核电费		sAgreSchemeDetail.getCompensateAmt 	售电公司赔偿金额
	  * )	
	  * @return void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月10日 下午6:57:22
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月10日 下午6:57:22
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public void getCheckedPro(SsAgreSchemeDetail ssAgreSchemeDetail) throws Exception;
	
}