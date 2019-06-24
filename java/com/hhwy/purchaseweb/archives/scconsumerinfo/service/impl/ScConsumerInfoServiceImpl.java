package com.hhwy.purchaseweb.archives.scconsumerinfo.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import com.hhwy.business.core.framework.service.SequenceTool;
import com.hhwy.business.core.region.constant.RegionConstant;
import com.hhwy.business.market.pcode.domain.PCode;
import com.hhwy.business.market.util.CodeUtil;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.archives.scconsdate.domain.ScConsDateDetail;
import com.hhwy.purchaseweb.archives.scconsdate.service.ScConsDateService;
import com.hhwy.purchaseweb.archives.scconselectricity.service.ScConsElectricityService;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ConsAgreDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ConsInfoDetailForImport;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ElecMonthDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ElecYearDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ScConsumerInfoDetail;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ScConsumerInfoVo;
import com.hhwy.purchaseweb.archives.scconsumerinfo.service.ScConsumerInfoService;
import com.hhwy.purchaseweb.archives.scdevicerelation.service.ScDeviceRelationService;
import com.hhwy.purchaseweb.archives.scindustrialzone.domain.ScIndustrialZoneDetail;
import com.hhwy.purchaseweb.archives.scindustrialzone.service.ScIndustrialZoneService;
import com.hhwy.purchaseweb.archives.scmpinfo.domain.ScMpInfoVo;
import com.hhwy.purchaseweb.archives.scmpinfo.service.ScMpInfoService;
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoDetail;
import com.hhwy.purchaseweb.archives.scorginfo.service.ScOrgInfoService;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.purchaseweb.bigdata.service.BigDataService;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.service.ScMpCountService;
import com.hhwy.selling.domain.ScConsDate;
import com.hhwy.selling.domain.ScConsumerInfo;
import com.hhwy.selling.domain.ScContactsInfo;
import com.hhwy.selling.domain.ScMpInfo;

/**
 * ScConsumerInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class ScConsumerInfoServiceImpl implements ScConsumerInfoService {

	public static final Logger log = LoggerFactory.getLogger(ScConsumerInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	@Autowired
	private ScMpInfoService scMpInfoService;
	
	/**
	 * scOrgInfoService 供电公司的service
	 */
	@Autowired
	private ScOrgInfoService scOrgInfoService;
	
	/**
	 * 园区维护scIndustrialZoneService
	 */
	@Autowired
	private ScIndustrialZoneService scIndustrialZoneService;
	
	/**
	 * bigDataService		大数据service
	 */
	@Autowired
	private BigDataService bigDataService;
	
	/**
	 * scConsElectricityService	历史用电量信息service
	 */
	@Autowired
	private ScConsElectricityService scConsElectricityService;
	
	/**
	 * scMpCountService		抄表示数service
	 */
	@Autowired
	private ScMpCountService scMpCountService;
	
	/**
	 * scDeviceRelationService 设备关系 by-zhangzhao
	 */
	@Autowired
	private ScDeviceRelationService scDeviceRelationService;
	
	/**
	 * scConsDateService		用户例日维护service
	 */
	@Autowired
	private ScConsDateService scConsDateService;
	
	/**
	 * 分页获取对象ScConsumerInfo
	 * @param ID
	 * @return ScConsumerInfo
	 */
	@SuppressWarnings("unchecked")
	public QueryResult<ScConsumerInfoDetail> getScConsumerInfoByPage(ScConsumerInfoVo scConsumerInfoVo) throws Exception{
		QueryResult<ScConsumerInfoDetail> queryResult = new QueryResult<ScConsumerInfoDetail>();
		
		List<ScConsumerInfoDetail> scConsumerInfoList = new ArrayList<>();
		
		if((scConsumerInfoVo.getId() == null || "".equals(scConsumerInfoVo.getId())) && (scConsumerInfoVo.getScConsumerInfo().getConsName() == null || "".equals(scConsumerInfoVo.getScConsumerInfo().getConsName())) ){ //查询根节点
			Parameter.isFilterData.set(true);
			scConsumerInfoList = getScConsumerInfoListByParams(scConsumerInfoVo);
			Parameter.isFilterData.set(false);
			
			for (ScConsumerInfoDetail scConsumerInfoDetail : scConsumerInfoList) {	
				Integer parentId = scConsumerInfoDetail.getCount();
				if(parentId != null && parentId > 0){
					scConsumerInfoDetail.setState("closed");
				}
			}
		}else{//查询子节点
			Parameter.isFilterData.set(true);
			scConsumerInfoList = (List<ScConsumerInfoDetail>) dao.getBySql("scConsumerInfo.sql.getScconsumerinfoChild", scConsumerInfoVo);
			Parameter.isFilterData.set(false);
			ConvertListUtil.convert(scConsumerInfoList);
			for (ScConsumerInfoDetail scConsumerInfoDetail : scConsumerInfoList) {
				Integer parentId = scConsumerInfoDetail.getCount();
				if(parentId != null && parentId > 0){
					scConsumerInfoDetail.setState("closed");
				}
				scConsumerInfoDetail.set_parentId(scConsumerInfoVo.getId());
			}
		}
		
		long total = getScConsumerInfoCountByParams(scConsumerInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(scConsumerInfoList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象ScConsumerInfo数量
	 * @param ScConsumerInfoVo
	 * @return Integer
	 */
	public Integer getScConsumerInfoCountByParams(ScConsumerInfoVo scConsumerInfoVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scConsumerInfo.sql.getScConsumerInfoCountByParams",scConsumerInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象ScConsumerInfo列表
	 * @param ScConsumerInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ScConsumerInfoDetail> getScConsumerInfoListByParams(ScConsumerInfoVo scConsumerInfoVo) throws Exception{
		//根据现在用户的合同状态更新用户的状态（意向用户和合同用户）
		dao.updateBySql("scConsumerInfo.sql.updateAllConsType", null);
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(scConsumerInfoVo == null){
			scConsumerInfoVo = new ScConsumerInfoVo();
		}
		//对电压等级参数，进行分割，用做查询
		String voltCode = scConsumerInfoVo.getScConsumerInfo().getVoltCode();
		List<String> splitVoltCode = new ArrayList<>();
		if(voltCode != null && !"".equals(voltCode) && voltCode.indexOf(",") >= 0){
			String[] splitVoltCodeArr = voltCode.split(",");
			splitVoltCode.addAll(Arrays.asList(splitVoltCodeArr));
			if("".equals(splitVoltCode.get(0))){
				splitVoltCode.remove(0);
			}
			scConsumerInfoVo.setSplitVoltCode(splitVoltCode);
		}else if(voltCode != null && !"".equals(voltCode)){
			splitVoltCode.add(voltCode);
			scConsumerInfoVo.setSplitVoltCode(splitVoltCode);
		}
		Parameter.isFilterData.set(true);
		List<ScConsumerInfoDetail> scConsumerInfoList = (List<ScConsumerInfoDetail>)dao.getBySql("scConsumerInfo.sql.getScConsumerInfoDetailListByParams",scConsumerInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(scConsumerInfoList);
		return scConsumerInfoList;
	}
	
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
	public Integer getScConsUserRelaCountByConsIds(String[] consIds) {
		Object result = dao.getOneBySQL("scConsumerInfo.sql.getScConsUserRelaCountByConsIds",consIds);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	/**
	 * 根据查询条件获取单个对象ScConsumerInfo
	 * @param ScConsumerInfoVo
	 * @return ScConsumerInfo
	 */
	public ScConsumerInfoDetail getScConsumerInfoOneByParams(ScConsumerInfoVo scConsumerInfoVo) throws Exception{
		ScConsumerInfoDetail scConsumerInfoDetail = null;
		List<ScConsumerInfoDetail> scConsumerInfoList = getScConsumerInfoListByParams(scConsumerInfoVo);
		if(scConsumerInfoList != null && scConsumerInfoList.size() > 0){
			scConsumerInfoDetail = scConsumerInfoList.get(0);
		}
		return scConsumerInfoDetail;
	}
	
	/**
	 * 根据ID获取对象ScConsumerInfo
	 * @param ID
	 * @return ScConsumerInfo
	 */
	@SuppressWarnings("unchecked")
	public ScConsumerInfoVo getScConsumerInfoById(String id) throws Exception {
		//查询用电用户基本信息
		ScConsumerInfoVo scConsumerInfoVo = new ScConsumerInfoVo();
		scConsumerInfoVo.setScConsumerInfo(dao.findById(id, ScConsumerInfo.class));
		
		scConsumerInfoVo.getScContactsInfo().setConsId(id);
		List<ScContactsInfo> scContactsInfoList = (List<ScContactsInfo>) dao.getBySql("scContactsInfo.sql.getScContactsInfoListByParams", scConsumerInfoVo);
		if(scContactsInfoList != null && scContactsInfoList.size()>0){
			scConsumerInfoVo.setScContactsInfo(scContactsInfoList.get(0));
		}
		//查询最新的例日信息
		ScConsDateDetail  consDate = scConsDateService.getLastScConsDateByConsId(id);
		if(consDate != null) {
			scConsumerInfoVo.setUsuallyDateName(consDate.getDateName());
		}
		
		//查询用户计量点信息
		ScMpInfoVo scMpInfoVo = new ScMpInfoVo();
		scMpInfoVo.getScMpInfo().setConsId(id);
		List<ScMpInfo> list = scMpInfoService.getScMpInfoListByParams(scMpInfoVo);
		//List<ScMpInfoDetail> scMpInfoDetailList = scMpInfoService.getScMpInfoDetailListByParams(scMpInfoVo);
		scConsumerInfoVo.setMpList(list);
/*		//记录计量点id
		if(list!=null && list.size()>0){
			String ids = "";
			for(ScMpInfo scMpInfo:list){
				ids = ids + "," + scMpInfo.getId();
			}
			scConsumerInfoVo.setIds(ids.substring(1));
		}
*/		//获取其父用户
		if(scConsumerInfoVo.getScConsumerInfo()!=null && scConsumerInfoVo.getScConsumerInfo().getParentId() != null && !"".equals(scConsumerInfoVo.getScConsumerInfo().getParentId()) ) {
			Map<String, String> params = new HashMap<>();
			params.put("id", scConsumerInfoVo.getScConsumerInfo().getParentId());
			List<ScConsumerInfoDetail> scConsumerInfoList = (List<ScConsumerInfoDetail>)dao.getBySql("scConsumerInfo.sql.getScConsumerInfoDetailById",params);
			ConvertListUtil.convert(scConsumerInfoList);
			if(scConsumerInfoList != null && scConsumerInfoList.size() > 0) {
				scConsumerInfoVo.setParentConsInfoDetail(scConsumerInfoList.get(0));
			}
		}
		return scConsumerInfoVo;
	}	
	
	/**
	 * 添加对象ScConsumerInfo
	 * @param 实体对象
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ScConsumerInfoVo saveScConsumerInfo(ScConsumerInfoVo scConsumerInfoVo) {
		String id = scConsumerInfoVo.getScConsumerInfo().getId();
		if(StringUtils.isEmpty(id)) {
			id = PlatformTools.getID();
		}
	    //自动生成用户编号，   用户编号不再使用
//	    String consNo = SequenceTool.getInstatnce().generateSerinalNumber("sellConsNo");
//	    scConsumerInfoVo.getScConsumerInfo().setConsNo(consNo);
		//保存用电用户信息
	    scConsumerInfoVo.getScConsumerInfo().setOrgNo(OrgUtil.getOrgNoByUser());
	    scConsumerInfoVo.getScConsumerInfo().setId(id);
		dao.save(scConsumerInfoVo.getScConsumerInfo());
		//联系人信息
		scConsumerInfoVo.getScContactsInfo().setConsId(id);
		scConsumerInfoVo.getScContactsInfo().setOrgNo(OrgUtil.getOrgNoByUser());		
		dao.save(scConsumerInfoVo.getScContactsInfo());
		
		//保存用户计量点信息
		for(ScMpInfo scMpInfo:scConsumerInfoVo.getMpList()){
		    //获取每行的执行电价
		   /* Object obj = dao.getOneBySQL("scMpInfo.sql.getCatKwhPrc", scMpInfo.getCatPrcCode());
		    scMpInfo.setCatPrc(new BigDecimal(obj.toString()));*/
			scMpInfo.setConsId(id);
		}
		scMpInfoService.saveScMpInfoList(scConsumerInfoVo.getMpList());
		
		//新增维护的用户例日信息
		List<ScConsDate> usuallyDateList = scConsumerInfoVo.getUsuallyDateList();
		if(usuallyDateList != null && usuallyDateList.size() != 0) {
			for (ScConsDate scConsDate : usuallyDateList) {
				scConsDate.setConsId(id);
			}
			scConsDateService.saveScConsDateList(usuallyDateList);
		}
		
		//更新大数据表中数据
		List<ScConsumerInfo> list = new ArrayList<>();
		list.add(scConsumerInfoVo.getScConsumerInfo());
		bigDataService.saveOrUpdateConsInfoList(list);
		
		return scConsumerInfoVo;
	}
	
	/**
	 * 更新对象ScConsumerInfo
	 * @param 实体对象
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ScConsumerInfoVo updateScConsumerInfo(ScConsumerInfoVo scConsumerInfoVo) {
		String consId = scConsumerInfoVo.getScConsumerInfo().getId();
		//修改用电用户信息
		dao.update(scConsumerInfoVo.getScConsumerInfo());
		if(scConsumerInfoVo.getScContactsInfo() != null) {
			scConsumerInfoVo.getScContactsInfo().setConsId(consId);
			if(StringUtils.isEmpty(scConsumerInfoVo.getScContactsInfo().getId())) {
				dao.deleteBySql("scConsumerInfo.sql.deleteScContactsInfo", new String[] {consId});
				dao.save(scConsumerInfoVo.getScContactsInfo());
			}else {
				dao.update(scConsumerInfoVo.getScContactsInfo());
			}
		}
		//保存用户计量点信息
		for(ScMpInfo scMpInfo:scConsumerInfoVo.getMpList()){
			scMpInfo.setConsId(consId);
		}
		scMpInfoService.saveScMpInfoList(scConsumerInfoVo.getMpList());
		
		//更新大数据表中数据
		List<ScConsumerInfo> list = new ArrayList<>();
		list.add(scConsumerInfoVo.getScConsumerInfo());
		bigDataService.updateConsInfoList(list);
		
		return scConsumerInfoVo;
		//根据计量点信息变化增加、修改、删除机组信息
		/*List<ScMpInfo> scMpInfoList = scConsumerInfoVo.getMpList();
		//拆解计量点信息集合为需要不同操作的三个集合
		if(scMpInfoList!=null&&scMpInfoList.size()>0){
			List<ScMpInfo> addList = new ArrayList<ScMpInfo>();
			String ids = scConsumerInfoVo.getIds();
			List<String> deleteIds = StringUtils.isNull(ids)?null:Arrays.asList(ids.split(","));
			for(ScMpInfo scMpInfo:scConsumerInfoVo.getMpList()){
				//ids为空代表全部机组信息都是新增；ids不为空，机组id不在范围内代表机组为新增
				if((!StringUtils.isNull(ids)&&ids.indexOf(scMpInfo.getId())<0)||StringUtils.isNull(ids)){
					//设置执行电价
				    Object obj = dao.getOneBySQL("scMpInfo.sql.getCatKwhPrc", scMpInfo.getCatPrcCode());
				    scMpInfo.setCatPrc(new BigDecimal(obj.toString()));
				    scMpInfo.setConsId(scConsumerInfoVo.getScConsumerInfo().getId());
					addList.add(scMpInfo);
				}else if(deleteIds!=null){
					deleteIds.remove(scMpInfo.getId());
				}
			}
			//添加操作
			if(addList.size()>0){
				scMpInfoService.saveScMpInfoList(addList);
			}
			//修改操作
			if(scConsumerInfoVo.getMpList().size()>0){
				scConsumerInfoVo.getMpList().removeAll(addList);
				scMpInfoService.updateScMpInfoList(scConsumerInfoVo.getMpList());
			}
			//删除操作	
			if(deleteIds!=null&&deleteIds.size()>0){
				String[] str = new String[deleteIds.size()];
				scMpInfoService.deleteScMpInfo(deleteIds.toArray(str));
			}
		}else if(scConsumerInfoVo.getIds() !=null && !scConsumerInfoVo.getIds().equals("")){
			String ids = scConsumerInfoVo.getIds();
			List<String> deleteIds = StringUtils.isNull(ids)?null:Arrays.asList(ids.split(","));
			//删除操作
			if(deleteIds!=null&&deleteIds.size()>0){
				String[] str = new String[deleteIds.size()];
				scMpInfoService.deleteScMpInfo(deleteIds.toArray(str));
			}
		}*/
	}
	
	/**
	 * 删除对象ScConsumerInfo
	 * @param id数据组
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteScConsumerInfo(String[] ids) {
		//删除用电用户基本信息
		dao.delete(ids, ScConsumerInfo.class);
		//删除联系人信息
		dao.deleteBySql("scConsumerInfo.sql.deleteScContactsInfo", ids);
		
		//删除表计示数、删除用电计划、删除偏差预警
		scMpCountService.deleteScMpCountAndOthersByConsId(ids);
		//删除用电用户计量点信息
		scMpInfoService.deleteScMpInfoByConsId(ids);
		//删除历史用电信息
		scConsElectricityService.deleteScConsElecByConsIds(ids);
		//by-zhangzhao 6M25 删除用户设备关系
		scDeviceRelationService.deleteByConsId(ids);
		
		//删除用户例日信息
		scConsDateService.deleteScConsDateByConsIds(ids);
	}
	
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
	@SuppressWarnings("unchecked")
	public List<ConsAgreDetail> getConsAgreList(Map<String, Object> map) throws Exception{
		if(map == null ){
			map = new HashMap<String, Object>();
		}
		List<ConsAgreDetail> list = (List<ConsAgreDetail>) dao.getBySql("scConsumerInfo.sql.getAgreList", map);
		ConvertListUtil.convert(list);
		return list;
	}
	
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
	@SuppressWarnings("unchecked")
	public List<ElecYearDetail> getElecYearList(Map<String, Object> map) throws Exception{
		if(map == null ){
			map = new HashMap<String, Object>();
		}
		List<ElecYearDetail> list = (List<ElecYearDetail>) dao.getBySql("scConsumerInfo.sql.getElecYear", map);
		return list;
	}
	
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
	@SuppressWarnings("unchecked")
	public List<ElecMonthDetail> getElecMonthList(Map<String, Object> map){
		if(map == null ){
			map = new HashMap<String, Object>();
		}
		List<ElecMonthDetail> list = (List<ElecMonthDetail>) dao.getBySql("scConsumerInfo.sql.getElecMonth", map);
		return list;
	}
	
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
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getConsProfit(Map< String, Object> map){
		if(map == null){
			map = new HashMap<String, Object>();
		}
		List<Map<String, Object>> list = (List<Map<String, Object>>) dao.getBySql("scConsumerInfo.sql.getConsProfit", map);
		return list;
	}

	/**
	 * 重复性验证 用户名、电能表编号
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void checkScConsumer(ScConsumerInfoVo scConsumerInfoVo) {
		
		Parameter.isFilterData.set(true);
		List<String> list = (List<String>) dao.getBySql("scConsumerInfo.sql.checkScConsumerInfo", scConsumerInfoVo);
		Parameter.isFilterData.set(false);
		if(list.size()>0){
            throw new RuntimeException("用户名称重复");
        }
		List<ScMpInfo> mpInfos = scConsumerInfoVo.getMpList();
		String consId = scConsumerInfoVo.getScConsumerInfo().getId();
		for (ScMpInfo scMpInfo : mpInfos) {
			scMpInfo.setConsId(consId);
			Parameter.isFilterData.set(true);
			List<Map<String,Object>> list2 = (List<Map<String, Object>>) dao.getBySql("scConsumerInfo.sql.checkScMpInfoMeterNo", scMpInfo);
			Parameter.isFilterData.set(false);
			if(list2 != null && list2.size() > 0){
				throw new RuntimeException("电能表编号重复");
			}
		}
	}
	
	/**
	 * 用户档案导出
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConsInfoDetailForImport> exportExcel(ScConsumerInfoVo scConsumerInfoVo,boolean flag) throws Exception {
		if(scConsumerInfoVo.getConsIds() == null || scConsumerInfoVo.getConsIds().length == 0){
			//对电压等级参数，进行分割，用做查询
			String voltCode = scConsumerInfoVo.getScConsumerInfo().getVoltCode();
			List<String> splitVoltCode = new ArrayList<>();
			if(voltCode != null && !"".equals(voltCode) && voltCode.indexOf(",") >= 0){
				String[] splitVoltCodeArr = voltCode.split(",");
				splitVoltCode.addAll(Arrays.asList(splitVoltCodeArr));
				if("".equals(splitVoltCode.get(0))){
					splitVoltCode.remove(0);
				}
				scConsumerInfoVo.setSplitVoltCode(splitVoltCode);
			}else if(voltCode != null && !"".equals(voltCode)){
				splitVoltCode.add(voltCode);
				scConsumerInfoVo.setSplitVoltCode(splitVoltCode);
			}
		}
		
		//根据查询条件获取用户
		Parameter.isFilterData.set(true);
		List<ConsInfoDetailForImport> list = (List<ConsInfoDetailForImport>) dao.getBySql("scConsumerInfo.sql.getConsInfoDetailByParams", scConsumerInfoVo);
		Parameter.isFilterData.set(false);
		List<ConsInfoDetailForImport> resultList = new ArrayList<ConsInfoDetailForImport>();
		//根据consId对查询结果分类，完善档案信息
		if(list != null && list.size() > 0){
			Map<String,List<ConsInfoDetailForImport>> consMap = new HashMap<String,List<ConsInfoDetailForImport>>();	
			List<String> idList = new ArrayList<String>();				 //用户id
			List<String> parentIdList = new ArrayList<String>();		 //父级用户id
			Map<String,String> parentMap = new HashMap<String,String>(); //key:父级用户id，value：用户id,为方便信息完善进行信息存储
			for(ConsInfoDetailForImport detail : list){
				idList.add(detail.getId());
				if(detail.getParentId() != null && !"".equals(detail.getParentId())){
					parentMap.put(detail.getParentId(), detail.getId());
					parentIdList.add(detail.getParentId());
				}
				List<ConsInfoDetailForImport> consList = new ArrayList<ConsInfoDetailForImport>();
				consList.add(detail);
				consMap.put(detail.getId(), consList);
			}
			//获取上级单位信息
			if(parentIdList != null && parentIdList.size() > 0 ){
				List<ConsInfoDetailForImport> parentList = (List<ConsInfoDetailForImport>) dao.getBySql("scConsumerInfo.sql.getParentInfoDetailByIds", parentIdList);
				for(ConsInfoDetailForImport parent : parentList){
					String id = parentMap.get(parent.getId()); //用户id
					consMap.get(id).get(0).setParentName(parent.getParentName());
					consMap.get(id).get(0).setParentElecTypeCode(parent.getParentElecTypeCode());
					consMap.get(id).get(0).setParentVoltCode(parent.getParentVoltCode());
				}
			}
			if(flag){//获取计量点信息
				List<ScMpInfo> mpInfoList = (List<ScMpInfo>) dao.getBySql("scMpInfo.sql.getScMpInfoByConsIds", idList);
				if(mpInfoList != null && mpInfoList.size() > 0){
					Map<String,List<ScMpInfo>> mpInfoMap = new HashMap<String,List<ScMpInfo>>();
					for(ScMpInfo info : mpInfoList){
						if(mpInfoMap.containsKey(info.getConsId())){
							mpInfoMap.get(info.getConsId()).add(info);
						}else{
							List<ScMpInfo> infoList = new ArrayList<ScMpInfo>();
							infoList.add(info);
							mpInfoMap.put(info.getConsId(), infoList);
						}
					}
					//处理用户计量点信息
					for(Entry<String, List<ScMpInfo>> map : mpInfoMap.entrySet()){
						List<ScMpInfo> mpList = map.getValue();
						consMap.get(map.getKey()).get(0).setMpName(mpList.get(0).getMpName());
						consMap.get(map.getKey()).get(0).setMpNo(mpList.get(0).getMpNo());
						consMap.get(map.getKey()).get(0).setMeterDigits(mpList.get(0).getMeterDigits());
						consMap.get(map.getKey()).get(0).setMeterRate(mpList.get(0).getMeterRate());
						consMap.get(map.getKey()).get(0).setMeterNo(mpList.get(0).getMeterNo());
						if(mpList.size()>1){
							for(int i = 1 ; i < mpList.size() ; i++){
								ConsInfoDetailForImport consInfo = new ConsInfoDetailForImport();
								consInfo.setMpName(mpList.get(i).getMpName());
								consInfo.setMpNo(mpList.get(i).getMpNo());
								consInfo.setMeterDigits(mpList.get(i).getMeterDigits());
								consInfo.setMeterRate(mpList.get(i).getMeterRate());
								consInfo.setMeterNo(mpList.get(i).getMeterNo());
								consMap.get(map.getKey()).add(consInfo);
							}
						}
					}
				}
			}
			//返回结果处理
			for(List<ConsInfoDetailForImport> consInfoList : consMap.values()){
				resultList.addAll(consInfoList);
			}
		}
		ConvertListUtil.convert(resultList);
		return resultList;
	}
	
	/**
	 * @Title: importConsInfoFromExcel
	 * @Description: 由excel导入用户信息， 包括用户基本信息，多个计量点信息，联系人信息
	 * 				因如果维护上级单位，则需要维护parentId和conspath字段，conspath的维护算法比较繁琐，暂时先去掉上级单位的导入功能，注释掉了相关代码。
	 * @param consInfoList
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月18日 下午5:23:18
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月18日 下午5:23:18
	 * @since  1.0.0
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void importConsInfoFromExcel(List<ConsInfoDetailForImport> consInfoList) throws Exception{
		//全字段去掉属性值两边的空格
		trimAllFields(consInfoList);
		//逆向转换一下码表
		ConvertListUtil.reverseConvert(consInfoList);
		//验证必填项是否都有值,并且保存

		
		//导入文件中 电能表编号->用户信息 的map，验证consInfoList中电能表编号是否重复，并且记录此电能表属于的用户信息
		//用于去验证 一个电能表编号，在系统中已经维护到某个用户下，而导入模板中维护到另一个用户下。
		Map<String,String> meterNoConsInfoMap = new HashMap<>();//----------------------去数据库查重
		//验证导入文件中有用户名称、电压等级重复的用户
			//此set中存：用户名称:电压等级   格式的字符串，用于查重用户,也用于查上级单位是否直接指向本列表中的数据
		Set<String> consInfoSet = new HashSet<>();
		
		/*//上级单位信息（上级单位名称:上级单位电压等级 格式字符串）对列表行数的map，
			//用于判断上级单位是否是数据库或者consInfoList中的用户
		Map<String,String> parentInfoIndexMap = new HashMap<>(); */
		
		
		//所属供电公司名称->list下标  的map，用于校验所属供电公司在数据库中是否已存在
		Map<String,String> orgNameMap = new HashMap<>();
		//所属园区->list下标  的map，用于校验所属园区在数据库中是否已存在
		Map<String,String> industrialZoneNameMap = new HashMap<>();
		
		//循环到的最后一条用户信息（用户名称:电压等级   格式的字符串）
		String lastConsInfo = null;
		for (int i = 0; i < consInfoList.size(); i ++) {
			ConsInfoDetailForImport consInfo = consInfoList.get(i);
			String errorMsg = null;
			//如果是第一行，则一定是用户行，即用户必填项不为空
			if(i == 0) {
				if(StringUtils.isEmpty(consInfo.getConsName())) {
					throw new BusinessException("第5行的‘用户名称’为必填项！请检查！");
				}else {
					errorMsg = validateConsInfoOrMpInfo(consInfo);
				}
			}else {
				//验证此行是用户行还是计量点行，并简单验证用户和计量点信息的必填项和数据类型
					//(当含有计量点信息，则要往meterNoSet中放meterNo用于查重；
					//当含有上级单位信息，则要往parentNameList中放parentName用于验证父级单位是否存在)
				errorMsg = validateConsInfoOrMpInfo(consInfo);
			}
			if("0".equals(errorMsg)) {	//纯用户
				lastConsInfo = consInfo.getConsName() + ":" + consInfo.getVoltCode();
				if(consInfoSet.contains(lastConsInfo)) {
					throw new BusinessException("第" + (5+i) + "行用户信息重复！请检查！");
				}
				consInfoSet.add(lastConsInfo);
				//如果上级单位信息部位空，则添加到parentInfoIndexMap
				/*if(!StringUtils.isEmpty(consInfo.getParentName())) {
					String parentHash = consInfo.getParentName() + ":" + consInfo.getParentVoltCode();
					if(parentInfoIndexMap.get(parentHash) != null) {
						parentInfoIndexMap.put(parentHash, parentInfoIndexMap.get(parentHash) + "," + (i+5));
					}else {
						parentInfoIndexMap.put(parentHash, String.valueOf(i+5));
					}
				}*/
				
				//如果存在所属供电公司
				if(!StringUtils.isEmpty(consInfo.getOrgName())) {
					if(orgNameMap.get(consInfo.getOrgName()) != null) {
						orgNameMap.put(consInfo.getOrgName(), orgNameMap.get(consInfo.getOrgName()) + "," + (i+5));
					}else {
						orgNameMap.put(consInfo.getOrgName(), String.valueOf(i+5));
					}
				}
				//如果存在所属园区
				if(!StringUtils.isEmpty(consInfo.getIndustrialZoneName())) {
					if(industrialZoneNameMap.get(consInfo.getIndustrialZoneName()) != null) {
						industrialZoneNameMap.put(consInfo.getIndustrialZoneName(), industrialZoneNameMap.get(consInfo.getIndustrialZoneName()) + "," + (i+5));
					}else {
						industrialZoneNameMap.put(consInfo.getIndustrialZoneName(), String.valueOf(i+5));
					}
				}
			}else if("1".equals(errorMsg)) {	//纯计量点
				String meterNo = consInfo.getMeterNo();
				if(meterNoConsInfoMap.get(meterNo) != null) {
					throw new BusinessException("第" + (5+i) + "行‘电能表编号’重复！请检查！");
				}
				meterNoConsInfoMap.put(meterNo,lastConsInfo);
			}else if("2".equals(errorMsg)) {	//既有用户，也有计量点
				lastConsInfo = consInfo.getConsName() + ":" + consInfo.getVoltCode();
				if(consInfoSet.contains(lastConsInfo)) {
					throw new BusinessException("第" + (5+i) + "行用户信息重复！请检查！");
				}
				consInfoSet.add(lastConsInfo);
				String meterNo = consInfo.getMeterNo();
				if(meterNoConsInfoMap.get(meterNo) != null) {
					throw new BusinessException("第" + (5+i) + "行‘电能表编号’重复！请检查！");
				}
				meterNoConsInfoMap.put(meterNo,lastConsInfo);
				//如果上级单位信息部位空，则添加到parentInfoIndexMap
				/*if(!StringUtils.isEmpty(consInfo.getParentName())) {
					String parentHash = consInfo.getParentName() + ":" + consInfo.getParentVoltCode();
					if(parentInfoIndexMap.get(parentHash) != null) {
						parentInfoIndexMap.put(parentHash, parentInfoIndexMap.get(parentHash) + "," + i);
					}else {
						parentInfoIndexMap.put(parentHash, String.valueOf(i));
					}
				}*/
				
				//如果存在所属供电公司
				if(!StringUtils.isEmpty(consInfo.getOrgName())) {
					if(orgNameMap.get(consInfo.getOrgName()) != null) {
						orgNameMap.put(consInfo.getOrgName(), orgNameMap.get(consInfo.getOrgName()) + "," + (i+5));
					}else {
						orgNameMap.put(consInfo.getOrgName(), String.valueOf(i+5));
					}
				}
				//如果存在所属园区
				if(!StringUtils.isEmpty(consInfo.getIndustrialZoneName())) {
					if(industrialZoneNameMap.get(consInfo.getIndustrialZoneName()) != null) {
						industrialZoneNameMap.put(consInfo.getIndustrialZoneName(), industrialZoneNameMap.get(consInfo.getIndustrialZoneName()) + "," + (i+5));
					}else {
						industrialZoneNameMap.put(consInfo.getIndustrialZoneName(), String.valueOf(i+5));
					}
				}
			}else {			//验证失败抛出异常
				throw new BusinessException("第" + (5+i) + errorMsg);
			}
			
		}
		
		//供电公司name->id 的map，用于后面维护到用户信息中
		Map<String,String> orgNameIdMap = new HashMap<>();
		//校验所属供电公司在数据库中是否已存在
		if(orgNameMap.size() > 0) {
			List<ScOrgInfoDetail> orgInfoList =  scOrgInfoService.getOrgInfoListByNames( new ArrayList<>(orgNameMap.keySet()));
			for (ScOrgInfoDetail scOrgInfoDetail : orgInfoList) {
				orgNameIdMap.put(scOrgInfoDetail.getName(), scOrgInfoDetail.getId());
				orgNameMap.remove(scOrgInfoDetail.getName());
			}
			if (!orgNameMap.isEmpty()) {
				String errorRowIndexs = "";
				for (Entry<String, String> entry : orgNameMap.entrySet()) {
					errorRowIndexs = errorRowIndexs + entry.getValue() + ",";
				}
				throw new BusinessException("第" + errorRowIndexs.substring(0, errorRowIndexs.length()-1) + "行的所属供电公司不存在！请检查！");
			}
		}
		//所属园区name->id 的map，用于后面维护到用户信息中
		Map<String,String> zoneNameIdMap = new HashMap<>();
		//校验所属园区在数据库中是否已存在
		if(industrialZoneNameMap.size() > 0) {
			List<ScIndustrialZoneDetail>  zoneInfoList =  scIndustrialZoneService.getZoneInfoListByNames( new ArrayList<>(industrialZoneNameMap.keySet()));
			for (ScIndustrialZoneDetail zoneInfoDetail : zoneInfoList) {
				zoneNameIdMap.put(zoneInfoDetail.getIzName(), zoneInfoDetail.getId());
				industrialZoneNameMap.remove(zoneInfoDetail.getIzName());
			}
			if (!industrialZoneNameMap.isEmpty()) {
				String errorRowIndexs = "";
				for (Entry<String, String> entry : industrialZoneNameMap.entrySet()) {
					errorRowIndexs = errorRowIndexs + entry.getValue() + ",";
				}
				throw new BusinessException("第" + errorRowIndexs.substring(0, errorRowIndexs.length()-1) + "行的所属园区不存在！请检查！");
			}
		}
		//通过consInfoSet存的用户信息，获取这些用户信息在数据库中的id，获取到id的就是更新操作，获取不到的就是新增操作
		List<Map<String, String>> consQueryParams = new ArrayList<>();
		for(String consInfo : consInfoSet) {
			Map<String, String> param = new HashMap<>();
			param.put("consName", consInfo.substring(0, consInfo.lastIndexOf(":")));
			param.put("voltCode", consInfo.substring(consInfo.lastIndexOf(":")+1, consInfo.length()));
			consQueryParams.add(param);
		}
		//在这里加上上级单位信息一起去数据库查询
		/*for (String parentConsInfo : parentInfoIndexMap.keySet()) {
			Map<String, String> param = new HashMap<>();
			param.put("consName", parentConsInfo.substring(0, parentConsInfo.lastIndexOf(":")));
			param.put("voltCode", parentConsInfo.substring(parentConsInfo.lastIndexOf(":"), parentConsInfo.length()));
			consQueryParams.add(param);
		}*/
		
		Parameter.isFilterData.set(true);
		@SuppressWarnings("unchecked")
		List<ScConsumerInfo> scConsumerInfoList = (List<ScConsumerInfo>)dao.getBySql("scConsumerInfo.sql.getConsInfoListByParams",consQueryParams);
		Parameter.isFilterData.set(false);
		//用户 name:voltCode->ScConsumerInfo 的map，用于后面维护到用户信息中
		Map<String,ScConsumerInfo> consNameIdMap = new HashMap<>();
		for (ScConsumerInfo scConsumerInfo : scConsumerInfoList) {
			String consInfo = scConsumerInfo.getConsName() + ":" + scConsumerInfo.getVoltCode();
			consNameIdMap.put(consInfo, scConsumerInfo);
			/*parentInfoIndexMap.remove(consInfo);*/
		}
		//新增用户 name:voltCode->ConsInfoDetailForImport 的map
		Map<String,ConsInfoDetailForImport> newConsNameIdMap = new HashMap<>();
		//需要新增的用户数据列表
		List<ConsInfoDetailForImport> addList = new ArrayList<>();
		//需要更新的用户数据列表
		List<ConsInfoDetailForImport> updateList = new ArrayList<>();
		//把consInfoList分类为新增数据还是更新数据
		for (ConsInfoDetailForImport consInfo : consInfoList) {
			if(!StringUtils.isEmpty(consInfo.getConsName())) {
				String consHash = consInfo.getConsName() + ":" + consInfo.getVoltCode();
				ScConsumerInfo scConsInfo = consNameIdMap.get(consHash);
				if(scConsInfo != null) {
					consInfo.setId(scConsInfo.getId());
					consInfo.setConsNo(scConsInfo.getConsNo()); //更新时保留其用户编号
					consInfo.setConsPath(consInfo.getConsPath());//赋值上级单位路径字段
					updateList.add(consInfo);
				}else {
					consInfo.setId(PlatformTools.getID());
					addList.add(consInfo);
					newConsNameIdMap.put(consHash, consInfo);
				}
				//如果有上级用电单位，则判断是否有上级用电单位的id，然后补全
				/*if(!StringUtils.isEmpty(consInfo.getParentName())) {
					consHash = consInfo.getParentName() + ":" + consInfo.getParentVoltCode();
					scConsInfo = consNameIdMap.get(consHash);
					if(scConsInfo != null) {
						consInfo.setParentId(scConsInfo.getId());
						consInfo.setConsPath(StringUtils.isEmpty(scConsInfo.getConsPath()) ? 
								scConsInfo.getId() : (scConsInfo.getConsPath() + "," + scConsInfo.getId())); //更新时保留其用户编号
					}
				}*/
			}
		}
		//用于判断上级单位是否 在数据库中或者在添加的列表中
		/*if(!parentInfoIndexMap.isEmpty()) {
			//parentInfoIndexMap-数据库查的 上级单位信息 = consInfoSet里新增的用户，如果consInfoSet没有则报错
			for (String consHash : newConsNameIdMap.keySet()) {
				parentInfoIndexMap.remove(consHash);
			}
			if(!parentInfoIndexMap.isEmpty()) {
				String errorRowIndexs = "";
				for (Entry<String, String> entry : parentInfoIndexMap.entrySet()) {
					errorRowIndexs = errorRowIndexs + entry.getValue() + ",";
				}
				throw new BusinessException("第" + errorRowIndexs.substring(0, errorRowIndexs.length()-1) + "行的上级单位不存在！请检查！");
			}
		}*/
		
		//去验证 一个电能表编号，在系统中已经维护到某个用户下，而导入模板中维护到另一个用户下。
		//查询参数，用于查询计量点表是否有 一个电能表编号在系统中已经维护到另外一个用户下
		List<Map<String, String>> mpQueryParams = new ArrayList<>();
		//转换meterNoConsInfoMap为 meterNo->consId 的map
		for (String meterNo : meterNoConsInfoMap.keySet()) {
			String consHash = meterNoConsInfoMap.get(meterNo);
			ScConsumerInfo scConsInfo = consNameIdMap.get(consHash);
			if(scConsInfo != null) {
				meterNoConsInfoMap.put(meterNo, scConsInfo.getId());
			}else {
				meterNoConsInfoMap.put(meterNo, newConsNameIdMap.get(consHash).getId());
			}
			Map<String, String> param = new HashMap<>();
			param.put("meterNo", meterNo);
			param.put("consId", meterNoConsInfoMap.get(meterNo));
			mpQueryParams.add(param);
		}
		//数据库中电能表编号->计量点id 的map
		Map<String, String> meterNoToIdMap = new HashMap<>();
		if(mpQueryParams.size() > 0) {
			//获取电能表编号一样但是所属用户与给的参数不同的计量点信息
			List<ScMpInfo>  mpList = scMpInfoService.getMpInfoListByMeterNoAndConsId(mpQueryParams);
			if(mpList != null && mpList.size() > 0) {
				String errorMsg = "";
				for (ScMpInfo scMpInfo : mpList) {
					errorMsg = errorMsg + "电能表编号‘" + scMpInfo.getMeterNo() + "’已存在于用户‘" + scMpInfo.getMpName() + "’中！";
				}
				throw new BusinessException(errorMsg + "请检查！");
			}
			mpList = scMpInfoService.getMpInfoListByMeterNos( new ArrayList<>(meterNoConsInfoMap.keySet()) );
			if(mpList != null && mpList.size() > 0) {
				for (ScMpInfo scMpInfo : mpList) {
					meterNoToIdMap.put(scMpInfo.getMeterNo(), scMpInfo.getId());
				}
			}
		}
		
		ScConsumerInfoVo consumerVo = null;
		//供电公司（orgNameIdMap）、所属园区转换id（zoneNameIdMap），根据电能表编号获取电能表id(meterNoToIdMap)，并维护到列表中
		for (ConsInfoDetailForImport consInfo : consInfoList) {
			if(!StringUtils.isEmpty(consInfo.getConsName())) {	//如果是用户数据
				if(consumerVo != null) {	//先保存上一个用户信息
					String consHash = consumerVo.getScConsumerInfo().getConsName() + ":" + consumerVo.getScConsumerInfo().getVoltCode();
					//判断此用户信息是新增还是更新
					ScConsumerInfo scConsInfo = consNameIdMap.get(consHash);
					if(scConsInfo != null) {	//更新操作
						this.updateScConsumerInfo(consumerVo);
					}else {			//新增操作
						this.saveScConsumerInfo(consumerVo);
					}
				}
				consumerVo = new ScConsumerInfoVo();
				if(!StringUtils.isEmpty(consInfo.getOrgName())) {	//如果存在所属供电公司
					consInfo.setOrgId(orgNameIdMap.get(consInfo.getOrgName()));
				}
				if(!StringUtils.isEmpty(consInfo.getIndustrialZoneName())) {	//如果存在所属园区
					consInfo.setIndustrialZone(zoneNameIdMap.get(consInfo.getIndustrialZoneName()));
				}
				BeanUtils.copyProperties(consInfo, consumerVo.getScConsumerInfo());
				BeanUtils.copyProperties(consInfo, consumerVo.getScContactsInfo());
				consumerVo.getScContactsInfo().setOfficePhone(consInfo.getOfficePhone2()); 		//设置因命名重复而更改的属性名值
				consumerVo.getScContactsInfo().setId(null);
				if(!StringUtils.isEmpty(consInfo.getMeterNo())) {	//如果含有计量点信息
					ScMpInfo mpInfo = new ScMpInfo();
					BeanUtils.copyProperties(consInfo, mpInfo);
					String mpInfoId = meterNoToIdMap.get(consInfo.getMeterNo());
					if(mpInfoId == null) {
						mpInfo.setId(PlatformTools.getID());
					}else {
						mpInfo.setId(mpInfoId);
					}
					consumerVo.getMpList().add(mpInfo);
				}
			}else if(!StringUtils.isEmpty(consInfo.getMeterNo())){		//纯计量点数据
				ScMpInfo mpInfo = new ScMpInfo();
				BeanUtils.copyProperties(consInfo, mpInfo);
				String mpInfoId = meterNoToIdMap.get(consInfo.getMeterNo());
				if(mpInfoId == null) {
					mpInfo.setId(PlatformTools.getID());
				}else {
					mpInfo.setId(mpInfoId);
				}
				consumerVo.getMpList().add(mpInfo);
			}
		}
		if(consumerVo != null) {	//保存最后一个用户信息
			String consHash = consumerVo.getScConsumerInfo().getConsName() + ":" + consumerVo.getScConsumerInfo().getVoltCode();
			//判断此用户信息是新增还是更新
			ScConsumerInfo scConsInfo = consNameIdMap.get(consHash);
			if(scConsInfo != null) {	//更新操作
				this.updateScConsumerInfo(consumerVo);
			}else {			//新增操作
				this.saveScConsumerInfo(consumerVo);
			}
		}
		//导入后统一刷新用户类型
		//根据现在用户的合同状态更新用户的状态（意向用户和合同用户）
		dao.updateBySql("scConsumerInfo.sql.updateAllConsType", null);
	}
	
	
	/**
	 * @Title: validateConsInfoOrMpInfo
	 * @Description: 验证此行（即ConsInfoDetailForImport对象）是用户数据行还是计量点数据行, 
	 * 					若含有计量点数据，则验证计量点必填字段是否已填, 
	 * @param consInfo
	 * @return 	0：纯用户数据行； 1：纯计量点数据行 ；2：既有用户数据，也有计量点数据； 其他：错误信息
	 * String
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月19日 下午4:11:28
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月19日 下午4:11:28
	 * @since  1.0.0
	 */
	private String validateConsInfoOrMpInfo(ConsInfoDetailForImport consInfo){
		String errorMsg = null;
		//若用户名为空，则暂定其是计量点数据行
		if(StringUtils.isEmpty(consInfo.getConsName())) {
			//验证计量点的必填属性及数据类型
			errorMsg = validateMpInfo(consInfo);
			if(errorMsg == null) {
				return "1";
			}else {
				return errorMsg;
			}
		}else {		//若用户名不为空，则是用户数据行
			//如果计量点字段全为null或""，则其为纯用户数据行
			if(StringUtils.isEmpty(consInfo.getMarketConsNo()) && StringUtils.isEmpty(consInfo.getMpNo())
					&& StringUtils.isEmpty(consInfo.getMpName()) && StringUtils.isEmpty(consInfo.getMeterNo())
					&& StringUtils.isEmpty(consInfo.getMeterRate()) && StringUtils.isEmpty(consInfo.getMeterDigits())) {
				//验证用户的必填属性，及下拉数据是否逆转换成功
				errorMsg = validateConsInfo(consInfo);
				if(errorMsg == null) {
					return "0";
				}else {
					return errorMsg;
				}
			}else {		//若既有用户数据，也有计量点数据
				//验证用户的必填属性，及下拉数据是否逆转换成功
				errorMsg = validateConsInfo(consInfo);
				if(errorMsg == null) {
					//验证计量点的必填属性及数据类型
					errorMsg = validateMpInfo(consInfo);
					if(errorMsg == null) {
						return "2";
					}else {
						return errorMsg;
					}
				}else {
					return errorMsg;
				}
			}
		}
	}
	
	/**
	 * @Title: validateConsInfo
	 * @Description: 验证用户信息行必填项是否完整,码表逆转换是否成功
	 * @param consInfo
	 * @return 
	 * String
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月19日 下午3:35:47
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月19日 下午3:35:47
	 * @since  1.0.0
	 */
	private String validateConsInfo(ConsInfoDetailForImport consInfo){
		//判断用户名称、用电行业分类、省、市、区县、用电容量、用电类别、电压等级不能为空
		if(StringUtils.isEmpty(consInfo.getConsName())) {
			return "行‘用户名称’是必填项！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getIndustryTypeName())) {
			return "行‘用电行业分类’是必填项！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getProvinceName())) {
			return "行‘省’是必填项！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getCityName())) {
			return "行‘市’是必填项！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getCountyName())) {
			return "行‘区县’是必填项！请检查！";
		}
		if(consInfo.getElectricCapacity() == null) {
			return "行‘用电容量’是必填项！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getElecTypeName())) {
			return "行‘用电类别’是必填项！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getVoltCodeName())) {
			return "行‘电压等级’是必填项！请检查！";
		}
		//验证下拉数据是否逆转换成功
		//判断 用电行业分类、省、市、区县、用电类别、电压等级不能为空
		if(StringUtils.isEmpty(consInfo.getIndustryType())) {
			return "行‘用电行业分类’必须与系统中字段保持一致！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getProvinceCode())) {
			return "行‘省’必须与系统中字段保持一致！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getCityCode())) {
			return "行‘市’必须与系统中字段保持一致！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getCountyCode())) {
			return "行‘区县’必须与系统中字段保持一致！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getElecTypeCode())) {
			return "行‘用电类别’必须与系统中字段保持一致！请检查！";
		}
		if(StringUtils.isEmpty(consInfo.getVoltCode())) {
			return "行‘电压等级’必须与系统中字段保持一致！请检查！";
		}
		//判断 是否完成信息采集、是否按园区打包、负荷性质、上级单位用电类别、上级单位电压等级 是否转换成功
		if(!StringUtils.isEmpty(consInfo.getScadaFlagName()) && StringUtils.isEmpty(consInfo.getScadaFlag())) {
			return "行‘是否完成信息采集’必须与系统中字段保持一致！请检查！";
		}
		if(!StringUtils.isEmpty(consInfo.getScPackageName()) && StringUtils.isEmpty(consInfo.getScPackage())) {
			return "行‘是否按园区打包’必须与系统中字段保持一致！请检查！";
		}
		if(!StringUtils.isEmpty(consInfo.getLodeAttrName()) && StringUtils.isEmpty(consInfo.getLodeAttrCode())) {
			return "行‘负荷性质’必须与系统中字段保持一致！请检查！";
		}
		/*if(!StringUtils.isEmpty(consInfo.getParentElecTypeName()) && StringUtils.isEmpty(consInfo.getParentElecTypeCode())) {
			return "行‘上级单位用电类别’必须与系统中字段保持一致！请检查！";
		}
		if(!StringUtils.isEmpty(consInfo.getParentVoltCodeName()) && StringUtils.isEmpty(consInfo.getParentVoltCode())) {
			return "行‘上级单位电压等级’必须与系统中字段保持一致！请检查！";
		}*/
		//如果有上级单位存在信息，则单位其他信息是必填的
		/*if((StringUtils.isEmpty(consInfo.getParentElecTypeCode()) || StringUtils.isEmpty(consInfo.getParentName())
				|| StringUtils.isEmpty(consInfo.getParentVoltCode())) &&  
				(!StringUtils.isEmpty(consInfo.getParentElecTypeCode()) || !StringUtils.isEmpty(consInfo.getParentName())
						|| !StringUtils.isEmpty(consInfo.getParentVoltCode()))) {
			if(StringUtils.isEmpty(consInfo.getParentName())) {
				return "行若有上级单位，则‘上级单位名称’是必填项！请检查！";
			}
			if(StringUtils.isEmpty(consInfo.getParentElecTypeCode())) {
				return "行若有上级单位，则‘上级单位用电类别’是必填项！请检查！";
			}
			if(StringUtils.isEmpty(consInfo.getParentVoltCode())) {
				return "行若有上级单位，则‘上级单位电压等级’是必填项！请检查！";
			}
		}*/
		//验证下拉级联是否符合要求，即 用电类别->电压等级（还有上级单位的 用电类别->电压等级 也需要验证）和 省市区县的级联
		try {
			//电压等级PCode
			PCode voltCodePcode = CodeUtil.queryPCodeByCodeTypeAndVal(ConstantsStatus.PCODE_DOMAIN_SELLING, "sell_psVoltCode", consInfo.getVoltCode());
			if(!consInfo.getElecTypeCode().equals( voltCodePcode.getContent5())) {
				return "行用电类别和电压等级关联关系错误，请检查！";
			}
			//上级单位电压等级PCode
			/*PCode parentVoltCodePcode = CodeUtil.queryPCodeByCodeTypeAndVal(ConstantsStatus.PCODE_DOMAIN_SELLING, "sell_psVoltCode", consInfo.getParentVoltCode());
			if(!consInfo.getParentElecTypeCode().equals( parentVoltCodePcode.getContent5())) {
				return "行上级单位用电类别和电压等级关联关系错误，请检查！";
			}*/
			//市PCode
			List<PCode> cityList = CodeUtil.queryRegionList(RegionConstant.CITYCODE, consInfo.getProvinceCode());
			boolean flag = true;
			for (PCode pCode : cityList) {
				if(consInfo.getCityName().equals(pCode.getName())){
					consInfo.setCityCode(pCode.getValue());
					flag= false;
					break;
				}
			}
			if(flag) {
				return "行省、市、区（县）关联关系错误，" + consInfo.getCityName() + "不属于" + consInfo.getProvinceName() + "，请检查！";
			}
			//区县PCode  
			List<PCode> countylist = CodeUtil.queryRegionList(RegionConstant.COUNTYCODE, consInfo.getCityCode());
			for (PCode pCode : countylist) {
				if (consInfo.getCountyName().equals(pCode.getName())) {
					consInfo.setCountyCode(pCode.getValue());
					flag = false;
					break;
				}
			}
			if(flag) {
				return "行省、市、区（县）关联关系错误，" + consInfo.getCountyName() + "不属于" + consInfo.getCityName() + "，请检查！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "行" + e.getMessage();
		}
		return null;
	}
	
	/**
	 * @Title: validateMpInfo
	 * @Description: 验证计量点数据，验证计量点必填字段是否已填, 
	 * 				电能表倍率（正整数）是不是正整数格式， 电能表位数（*.*或者*格式）不是一位整数一位小数或一位正整数格式
	 * @param consInfo
	 * @return 错误信息
	 * String
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月19日 下午4:58:21
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月19日 下午4:58:21
	 * @since  1.0.0
	 */
	private String validateMpInfo(ConsInfoDetailForImport consInfo){
		//若是计量点数据行，验证计量点三个必填项（电能表编号、电能表倍率、电能表位数）
		if(StringUtils.isEmpty(consInfo.getMeterNo())
					|| StringUtils.isEmpty(consInfo.getMeterRate()) || StringUtils.isEmpty(consInfo.getMeterDigits())){
			if(StringUtils.isEmpty(consInfo.getMeterNo())) {
				return "行计量点没有维护‘电能表编号’，请检查！";
			}
			if(StringUtils.isEmpty(consInfo.getMeterRate())) {
				return "行计量点没有维护‘电能表倍率’，请检查！";
			}
			if(StringUtils.isEmpty(consInfo.getMeterDigits())) {
				return "行计量点没有维护‘电能表位数’，请检查！";
			}
		}else {		//必填项已填，则验证数据类型
			// 电能表倍率 正则表达式（非0正整数）
		    Pattern pattern = Pattern.compile("^[1-9]\\d*$");
		    Matcher matcher = pattern.matcher(consInfo.getMeterRate());
		    if(!matcher.matches()) {
		    	return "行‘电能表倍率’不是正整数，请检查！";
		    }
		    // 电能表位数 正则表达式（一位整数一位小数或一位正整数）
		    Pattern pattern1 = Pattern.compile("(0.\\d)|(^[1-9](\\.\\d?)?$)");
		    Matcher matcher1 = pattern1.matcher(consInfo.getMeterDigits());
		    if(!matcher1.matches()) {
		    	return "行‘电能表位数’不是一位整数一位小数或一位正整数格式，请检查！";
		    }
		}
		return null;
	}
	
	/**
	 * @Title: trimAllFields
	 * @Description: 删除所有属性值两边的空格
	 * @param consInfoList
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月19日 下午2:06:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月19日 下午2:06:36
	 * @since  1.0.0
	 */
	private void trimAllFields(List<ConsInfoDetailForImport> consInfoList) throws Exception {
		if(consInfoList!=null && consInfoList.size()>0 && consInfoList.get(0)!=null){
            Field[] fields = ConsInfoDetailForImport.class.getDeclaredFields();//获取类中的所有可访问的公共字段
            for (Field field : fields) {
            	if(field.getType() == String.class) {
            		field.setAccessible(true);
            		for (ConsInfoDetailForImport consInfo : consInfoList) {
            			Object obj = field.get(consInfo);
            			if(obj != null) {
            				String value = obj.toString();
            				if(value != null && !"".equals(value)) {
            					field.set(consInfo, value.trim());
            				}
            			}
            		}
            	}
            }
        }
	}
}