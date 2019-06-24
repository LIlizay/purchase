package com.hhwy.purchaseweb.delivery.smprcinfo.service;

import java.util.List;

import com.hhwy.purchase.domain.SmPrcInfo;
import com.hhwy.purchaseweb.delivery.smprcinfo.domain.SmPrcInfoDetail;
import com.hhwy.purchaseweb.delivery.smprcinfo.domain.SmPrcInfoVo;

/**
 * ISmPrcInfoService
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmPrcInfoService {

	/**
	 * 分页获取对象SmPrcInfo
	 * 
	 * @param SmPrcInfoVo
	 * @return QueryResult
	 */
	public List<SmPrcInfoVo> getSmPrcInfoGroupByProvinceId(
			SmPrcInfoVo smPrcInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmPrcInfo列表
	 * 
	 * @param SmPrcInfoVo
	 * @return List
	 */
	public List<SmPrcInfoDetail> getSmPrcInfoListByParams(
			SmPrcInfoVo smPrcInfoVo) throws Exception;
	
	
	/**
	 * @Title: getInitSmPrcInfoList
	 * @Description: 获取初始的电价列表（主要获取初始的 用电类别、电压等级）
	 * @return
	 * @throws Exception 
	 * List<SmPrcInfoDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年11月21日 下午7:16:03
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年11月21日 下午7:16:03
	 * @since  1.0.0
	 */
	public List<SmPrcInfoDetail> getInitSmPrcInfoList() throws Exception;
	
	

	/**
	 * 根据ID获取对象SmPrcInfo
	 * 
	 * @param ID
	 * @return SmPrcInfo
	 */
	public SmPrcInfo getSmPrcInfoById(String id);

	/**
	 * 添加对象SmPrcInfo
	 * 
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmPrcInfo(SmPrcInfo smPrcInfo);
	
	/**
	 * @Title: saveOrUpdateSmPrcInfoVo
	 * @Description: 保存一个电价列表（以市、区县、月份区分的），新增或更新，里面都有id，所以不能以有无id判断是新增还是更新
	 * 	因为同一省份下不能有 省、市、区县、月份 四个字段同时相等的一组电价列表，所以在保存前需要验证是否符合保存条件
	 * @param smPrcInfoVo 
	 * String
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年11月22日 下午6:27:05
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年11月22日 下午6:27:05
	 * @since  1.0.0
	 */
	public String saveOrUpdateSmPrcInfoVo(SmPrcInfoVo smPrcInfoVo);
	
	/**
	 * @Title: getSmPrcInfoListByVo
	 * @Description: 根据 省、市、区县、月份 查询符合条件的实体列表
	 * @param smPrcInfoVo
	 * @return  List<SmPrcInfo>
	 * Integer
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年11月22日 下午6:54:28
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年11月22日 下午6:54:28
	 * @since  1.0.0
	 */
	public List<SmPrcInfo> getSmPrcInfoListByVo(SmPrcInfoVo smPrcInfoVo);
	

	/**
	 * 添加对象SmPrcInfo列表
	 * 
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmPrcInfoList(List<SmPrcInfo> smPrcInfoList);

	/**
	 * 更新对象SmPrcInfo
	 * 
	 * @param 实体对象
	 * @return SmPrcInfo
	 */
	public void updateSmPrcInfo(SmPrcInfo smPrcInfo);

	/**
	 * @Title: deleteSmPrcInfo
	 * @Description: 删除对象SmPrcInfo和其下政府性基金及附加smFundPrcInfo
	 * @param ids 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年2月27日 下午3:07:06
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年2月27日 下午3:07:06
	 * @since  1.0.0
	 */
	public void deleteSmPrcInfo(String[] ids);

	/**
	 * @Title: getSmPrcInfoByConsId
	 * @Description: 根据用户id获取其对应的电价信息
	 * @param consId
	 * @return SmPrcInfo
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月23日 下午7:42:39
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月23日 下午7:42:39
	 * @since  1.0.0
	 */
	public SmPrcInfo getSmPrcInfoByConsId(String consId);
	
	/**
	 * @Title: getSmPrcInfoListByParams
	 * @Description: 根据 省、市、区县、月份 查询符合条件的实体列表,若没有完全符合的，则获取和参数最相近的列表返回
	 * 					条件优先级为provinceCode, cityCode, countyCode , month依次降低
	 * @param provinceCode	单个省码，必填
	 * @param cityCode		单个市码，非必填
	 * @param countyCode	单个区县码，非必填
	 * @param month			单个月份（例："05"），非必填
	 * @param voltCode		单个电压等级，非必填
	 * @return 
	 * List<SmPrcInfo>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月29日 上午10:14:02
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月29日 上午10:14:02
	 * @since  1.0.0
	 */
	public List<SmPrcInfo> getSmPrcInfoListByParams(String provinceCode, String cityCode, String countyCode , String month, String voltCode);
}