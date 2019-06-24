package com.hhwy.purchaseweb.archives.scconsumerinfo.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ConsAgreDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ConsInfoDetailForImport;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ElecMonthDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ElecYearDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ScConsumerInfoDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ScConsumerInfoVo;

/**
 * IScConsumerInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface ScConsumerInfoService{
	
	/**
	 * 分页获取对象ScConsumerInfo
	 * @param ScConsumerInfoVo
	 * @return QueryResult
	 */
	public QueryResult<ScConsumerInfoDetail> getScConsumerInfoByPage(ScConsumerInfoVo scConsumerInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象ScConsumerInfo列表
	 * @param ScConsumerInfoVo
	 * @return List
	 */
	public List<ScConsumerInfoDetail> getScConsumerInfoListByParams(ScConsumerInfoVo scConsumerInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象ScConsumerInfo数量
	 * @param ScConsumerInfoVo
	 * @return Integer
	 */
	public Integer getScConsumerInfoCountByParams(ScConsumerInfoVo scConsumerInfoVo);
	
	/**
	 * @Title: getScConsUserRelaCountByConsIds
	 * @Description: 根据用户id数组获取其中绑定了用电监测用户的数量
	 * @param consIds
	 * @return 
	 * Integer
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年11月8日 下午2:01:46
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年11月8日 下午2:01:46
	 * @since  1.0.0
	 */
	public Integer getScConsUserRelaCountByConsIds(String[] consIds);
	
	/**
	 * 根据查询条件获取单个对象ScConsumerInfo
	 * @param ScConsumerInfoVo
	 * @return ScConsumerInfo
	 */
	public ScConsumerInfoDetail getScConsumerInfoOneByParams(ScConsumerInfoVo scConsumerInfoVo) throws Exception;
	
	/**
	 * 根据ID获取对象ScConsumerInfo
	 * @param ID
	 * @return ScConsumerInfo
	 */
	public ScConsumerInfoVo getScConsumerInfoById(String id) throws Exception;
	
	/**
	 * 添加对象ScConsumerInfo
	 * @param 实体对象
	 * @return null
	 */
	public ScConsumerInfoVo saveScConsumerInfo(ScConsumerInfoVo scConsumerInfoVo);
	
	/**
	 * 更新对象ScConsumerInfo
	 * @param 实体对象
	 * @return ScConsumerInfo
	 */
	public ScConsumerInfoVo updateScConsumerInfo(ScConsumerInfoVo scConsumerInfoVo);
	
	/**
	 * 删除对象ScConsumerInfo
	 * @param id数据组
	 */
	public void deleteScConsumerInfo(String[] ids);
	
	/**
	 * 
	 * @Title: getConsAgreList<br/>
	 * @Description: 查询合同列表<br/>
	 * @param map
	 * @return
	 * @throws Exception <br/>
	 * List<ConsAgreDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月19日 下午3:21:15
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月19日 下午3:21:15
	 * @since  1.0.0
	 */
	public List<ConsAgreDetail> getConsAgreList(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: getElecYearList<br/>
	 * @Description: 查询年份用电信息<br/>
	 * @param map
	 * @return
	 * @throws Exception <br/>
	 * List<ElecYearDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月19日 下午8:28:03
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月19日 下午8:28:03
	 * @since  1.0.0
	 */
	public List<ElecYearDetail> getElecYearList(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: getElecMonthList<br/>
	 * @Description: 查询月份用电信息<br/>
	 * @param map
	 * @return <br/>
	 * List<ElecMonthDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月19日 下午8:34:11
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月19日 下午8:34:11
	 * @since  1.0.0
	 */
	public List<ElecMonthDetail> getElecMonthList(Map<String, Object> map);
	
	/**
	 * 
	 * @Title: getConsProfit<br/>
	 * @Description: 查询用户结算电费<br/>
	 * @param map
	 * @return <br/>
	 * List<Map<String,Object>><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月21日 下午8:41:50
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月21日 下午8:41:50
	 * @since  1.0.0
	 */
	public List<Map<String, Object>> getConsProfit(Map< String, Object> map);

	/**
	  * @Title: checkScConsumer
	  * @Description: 重复性验证 : 验证用户名称 和 电能表编号 
	  * @param scConsumerInfo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月9日 下午6:21:50
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月9日 下午6:21:50
	  * @since  1.0.0
	 */
	public void checkScConsumer(ScConsumerInfoVo scConsumerInfoVo);
	
	/**
	  * @Title: exportExcel
	  * @Description: 用户档案导出
	  * @param id
	  * @param request
	  * @param response
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年4月18日 下午1:12:13
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年4月18日 下午1:12:13
	 * @throws UnsupportedEncodingException 
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public List<ConsInfoDetailForImport> exportExcel(ScConsumerInfoVo scConsumerInfoVo,boolean flag) throws Exception;

	
	/**
	 * @Title: importConsInfoFromExcel
	 * @Description: 由excel导入用户信息， 包括用户基本信息，多个计量点信息，联系人信息
	 * @param consInfoList
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月18日 下午5:23:18
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月18日 下午5:23:18
	 * @since  1.0.0
	 */
	public void importConsInfoFromExcel(List<ConsInfoDetailForImport> consInfoList) throws Exception;
}