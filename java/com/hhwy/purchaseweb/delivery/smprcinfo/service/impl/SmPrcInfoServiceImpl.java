package com.hhwy.purchaseweb.delivery.smprcinfo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchase.domain.SmPrcInfo;
import com.hhwy.purchaseweb.delivery.smfundprcinfo.service.SmFundPrcInfoService;
import com.hhwy.purchaseweb.delivery.smprcinfo.domain.SmPrcInfoDetail;
import com.hhwy.purchaseweb.delivery.smprcinfo.domain.SmPrcInfoVo;
import com.hhwy.purchaseweb.delivery.smprcinfo.service.SmPrcInfoService;

/**
 * SmPrcInfoService
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmPrcInfoServiceImpl implements SmPrcInfoService {

	public static final Logger log = LoggerFactory
			.getLogger(SmPrcInfoServiceImpl.class);

	@Autowired
	DAO<?> dao;

	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

	/**
	 * smFundPrcInfoService		政府性基金及附加的service
	 */
	@Autowired
	private SmFundPrcInfoService smFundPrcInfoService;
	
	/**
	 * 分页获取对象SmPrcInfo
	 * 
	 * @param ID
	 * @return SmPrcInfo
	 */
	public List<SmPrcInfoVo> getSmPrcInfoGroupByProvinceId(
			SmPrcInfoVo smPrcInfoVo) throws Exception {
		List<SmPrcInfoVo> result = new ArrayList<SmPrcInfoVo>();
		List<SmPrcInfoDetail> smPrcInfoList = getSmPrcInfoListByParams(smPrcInfoVo);
		int initSort = -1;
		for (int i = 0; i < smPrcInfoList.size(); i++) {
			if (null == smPrcInfoList.get(i).getId() || "".equals(smPrcInfoList.get(i).getId())) {
				smPrcInfoList.get(i).setId(PlatformTools.getID());
				smPrcInfoList.get(i).setListSort(0);
			}
			if(initSort != smPrcInfoList.get(i).getListSort()) {
				result.add(new SmPrcInfoVo());
				initSort = smPrcInfoList.get(i).getListSort();
			}
			result.get(result.size()-1).getSmPrcInfoDetailList().add(smPrcInfoList.get(i));
		}
		for (SmPrcInfoVo vo : result) {
			vo.setProvinceId(vo.getSmPrcInfoDetailList().get(0).getProvinceId());
			vo.setCityCodes(vo.getSmPrcInfoDetailList().get(0).getCityCodes());
			vo.setCountyCodes(vo.getSmPrcInfoDetailList().get(0).getCountyCodes());
			vo.setEffectMonths(vo.getSmPrcInfoDetailList().get(0).getEffectMonths());
			vo.setListSort(vo.getSmPrcInfoDetailList().get(0).getListSort());
		}
		return result;
	}

	/**
	 * 根据查询条件获取对象SmPrcInfo列表
	 * 
	 * @param SmPrcInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmPrcInfoDetail> getSmPrcInfoListByParams(
			SmPrcInfoVo smPrcInfoVo) throws Exception {
		// 当Vo为空时,初始化Vo对象,应用分页参数
		if (smPrcInfoVo == null) {
			smPrcInfoVo = new SmPrcInfoVo();
		}
		List<SmPrcInfoDetail> smPrcInfoList = (List<SmPrcInfoDetail>) dao
				.getBySql("smPrcInfo.sql.getSmPrcInfoListByParams", smPrcInfoVo);
		ConvertListUtil.convert(smPrcInfoList);
		return smPrcInfoList;
	}

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
	@SuppressWarnings("unchecked")
	public List<SmPrcInfoDetail> getInitSmPrcInfoList() throws Exception{
		List<SmPrcInfoDetail> smPrcInfoList = (List<SmPrcInfoDetail>) dao.getBySql("smPrcInfo.sql.getInitSmPrcInfoList", null);
		return smPrcInfoList;
	}
	/**
	 * 根据ID获取对象SmPrcInfo
	 * 
	 * @param ID
	 * @return SmPrcInfo
	 */
	public SmPrcInfo getSmPrcInfoById(String id) {
		return dao.findById(id, SmPrcInfo.class);
	}

	/**
	 * 添加对象SmPrcInfo
	 * 
	 * @param 实体对象
	 */
	public void saveSmPrcInfo(SmPrcInfo smPrcInfo) {
		dao.save(smPrcInfo);
	}

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
	@Transactional
	public String saveOrUpdateSmPrcInfoVo(SmPrcInfoVo smPrcInfoVo) {
		if(smPrcInfoVo.getSmPrcInfoList() == null || smPrcInfoVo.getSmPrcInfoList().size() == 0
				|| smPrcInfoVo.getProvinceId() == null || "".equals(smPrcInfoVo.getProvinceId())) {
			return "参数错误，请刷新页面重试";
		}
		List<SmPrcInfo> smPrcInfolList = smPrcInfoVo.getSmPrcInfoList();	//要保存的列表
		for (SmPrcInfo smPrcInfo : smPrcInfolList) {	//更新相应字段
			smPrcInfo.setProvinceId(smPrcInfoVo.getProvinceId());
			smPrcInfo.setCityCodes(smPrcInfoVo.getCityCodes());
			smPrcInfo.setCountyCodes(smPrcInfoVo.getCountyCodes());
			smPrcInfo.setEffectMonths(smPrcInfoVo.getEffectMonths());
			smPrcInfo.setListSort(smPrcInfoVo.getListSort());
		}
		List<SmPrcInfo> smPrcInfoEntitys = getSmPrcInfoListByVo(smPrcInfoVo);		//在数据库中同 省、市、区县、月份下的实体列表
		if(smPrcInfoEntitys != null && smPrcInfoEntitys.size() > 0) {
			//要新增或更新的数据在数据库中有相同的 省、市、区县、月份
			Set<String> idSet = new HashSet<>();
			for (SmPrcInfo smPrcInfo : smPrcInfolList) {
				idSet.add(smPrcInfo.getId());
			}
			int addCount = idSet.size();
			for(SmPrcInfo smPrcInfo : smPrcInfoEntitys) {
				idSet.remove(smPrcInfo.getId());
			}
			if(addCount == idSet.size()) {	//id不重复，推理出是新增操作，或者 是修改了市、区县、月份的更新操作 
				//则不保存数据，并返回数据库中已存在数据的信息
				return "数据库中存在此“市、区县、月份”的数据！";
			}else {	//id有重复，推理出是没有修改“市、区县、月份”的更新操作
				//则需要先删除对应数据，然后再保存（不能直接更新，防止用电类别或电压等级中增加或删除类别）
				deleteSmPrcInfoByParams(smPrcInfoVo);
				saveSmPrcInfoList(smPrcInfolList);
				return null;
			}
		}else {		//要新增或更新（修改了市、区县、月份字段）的数据在数据库中没有相同的 省、市、区县、月份
			saveSmPrcInfoList(smPrcInfolList);
			return null;
		}
	}
	
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
	@SuppressWarnings("unchecked")
	public List<SmPrcInfo> getSmPrcInfoListByVo(SmPrcInfoVo smPrcInfoVo) {
		List<SmPrcInfo> smPrcInfoList = (List<SmPrcInfo>) dao.getBySql("smPrcInfo.sql.getSmPrcInfoListByVo", smPrcInfoVo);
		return smPrcInfoList;
	}
	
	/**
	 * @Title: deleteSmPrcInfoByParams
	 * @Description: 根据 省、市、区县、月份 删除符合条件的实体列表
	 * @param smPrcInfoVo 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年11月22日 下午8:26:13
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年11月22日 下午8:26:13
	 * @since  1.0.0
	 */
	public void deleteSmPrcInfoByParams(SmPrcInfoVo smPrcInfoVo) {
		dao.deleteBySql("smPrcInfo.sql.deleteSmPrcInfoByParams", smPrcInfoVo);
	}
	
	/**
	 * 添加对象SmPrcInfo
	 * 
	 * @param 实体对象
	 */
	public void saveSmPrcInfoList(List<SmPrcInfo> smPrcInfoList) {
		dao.saveList(smPrcInfoList);
	}

	/**
	 * 更新对象SmPrcInfo
	 * 
	 * @param 实体对象
	 */
	public void updateSmPrcInfo(SmPrcInfo smPrcInfo) {
		dao.update(smPrcInfo);
	}

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
	@Transactional
	public void deleteSmPrcInfo(String[] ids) {
		smFundPrcInfoService.deleteSmFundPrcInfoByPrcIds(ids);
		dao.delete(ids, SmPrcInfo.class);
	}
	
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
	public SmPrcInfo getSmPrcInfoByConsId(String consId){
		if(consId == null){
			return null;
		}
		SmPrcInfo smPrcInfo = (SmPrcInfo)dao.getOneBySQL("smPrcInfo.sql.getSmPrcInfoByConsId", consId);
		return smPrcInfo;
	}
	
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
	@SuppressWarnings("unchecked")
	public List<SmPrcInfo> getSmPrcInfoListByParams(String provinceCode, String cityCode, String countyCode , String month, String voltCode) {
		Map<String, String> params = new HashMap<>();
		params.put("provinceCode", provinceCode);
		params.put("cityCode", cityCode);
		params.put("countyCode", countyCode);
		params.put("month", month);
		params.put("voltCode", voltCode);
		List<SmPrcInfo> smPrcInfoList = (List<SmPrcInfo>) dao.getBySql("smPrcInfo.sql.getSmPrcInfoListByMap", params);
		if(smPrcInfoList == null || smPrcInfoList.size() == 0) {
			params.put("month", null);
			smPrcInfoList = (List<SmPrcInfo>) dao.getBySql("smPrcInfo.sql.getSmPrcInfoListByMap", params);
		}
		if(smPrcInfoList == null || smPrcInfoList.size() == 0) {
			params.put("countyCode", null);
			smPrcInfoList = (List<SmPrcInfo>) dao.getBySql("smPrcInfo.sql.getSmPrcInfoListByMap", params);
		}
		if(smPrcInfoList == null || smPrcInfoList.size() == 0) {
			params.put("cityCode", null);
			smPrcInfoList = (List<SmPrcInfo>) dao.getBySql("smPrcInfo.sql.getSmPrcInfoListByMap", params);
		}
		return smPrcInfoList;
	}
}