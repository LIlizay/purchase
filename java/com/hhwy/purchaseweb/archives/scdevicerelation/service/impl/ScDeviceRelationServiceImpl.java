package com.hhwy.purchaseweb.archives.scdevicerelation.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.core.util.RemoteProcedureCall;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.collectionplatform.all.domain.DeviceDetail;
import com.hhwy.collectionplatform.all.domain.EmbusinessDataCollectDetail;
import com.hhwy.collectionplatform.all.interfaces.PlatformInterface;
import com.hhwy.collectionplatform.all.interfaces.RealDataInterface;
import com.hhwy.collectionplatform.all.util.PlatformResult;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scconsdate.service.ScConsDateService;
import com.hhwy.purchaseweb.archives.scdevicerelation.domain.ScDeviceRelationDetail;
import com.hhwy.purchaseweb.archives.scdevicerelation.domain.ScDeviceRelationVo;
import com.hhwy.purchaseweb.archives.scdevicerelation.service.ScDeviceRelationService;
import com.hhwy.selling.domain.ScDeviceRelation;

/**
 * ScDeviceRelationService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class ScDeviceRelationServiceImpl implements ScDeviceRelationService {

	public static final Logger log = LoggerFactory.getLogger(ScDeviceRelationServiceImpl.class);

	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	/**
	 * scConsDateService		用户例日维护service
	 */
	@Autowired
	private ScConsDateService scConsDateService;
	
	/**
	 * 分页获取对象ScDeviceRelation
	 * @param ID
	 * @return ScDeviceRelation
	 */
	public QueryResult<ScDeviceRelationDetail> getScDeviceRelationByPage(ScDeviceRelationVo scDeviceRelationVo, String domain, String platformCode) throws Exception{
		if(scDeviceRelationVo.getVoltCode() != null && scDeviceRelationVo.getVoltCode() != ""){
			String[] voltList = scDeviceRelationVo.getVoltCode().split(",");
			scDeviceRelationVo.setVoltCodeList(voltList);
		}
		//调采集接口
		PlatformInterface p = RemoteProcedureCall.getInstancce().getService(PlatformInterface.class);
		
		com.hhwy.collectionplatform.all.util.Paging paging = new com.hhwy.collectionplatform.all.util.Paging();
		paging.setPaging(false);
		paging.setPage(1);
		paging.setRows(1);
		
		QueryResult<ScDeviceRelationDetail> queryResult = new QueryResult<ScDeviceRelationDetail>();
		List<ScDeviceRelationDetail> treeList;
		long total = getTreeCountByParams(scDeviceRelationVo);			//查询根节点数量
		//不是子节点查询
		if(scDeviceRelationVo.getId() == null || "".equals(scDeviceRelationVo.getId())){
			//父节点查询
			treeList = getParentListByParams(scDeviceRelationVo);
			if(treeList != null && treeList.size() > 0){
				for(ScDeviceRelationDetail scDeviceRelationDetail : treeList){
					//有子节点
					if(scDeviceRelationDetail.getDeviceCount() > 1){
						scDeviceRelationDetail.setState("closed");
					}else{
					//无子节点
						//有设备id
						if(scDeviceRelationDetail.getDeviceId() != null &&  scDeviceRelationDetail.getDeviceId() != "" ){
							//接口参数：				分页信息，平台编码，域名，设备编码，设备名，设备类型，List<String> deviceIds
							List<String> idList = new ArrayList<>();
							if(scDeviceRelationDetail.getDeviceId() != null){
								idList.add(scDeviceRelationDetail.getDeviceId());
							}
//							PlatformResult<DeviceDetail> deviceByPlatform = p.getDeviceByPlatform(paging, "cloudselling-platform-release", "ysd.hhwy.org", "", "", "", idList);
							PlatformResult<DeviceDetail> deviceByPlatform = p.getDeviceByPlatform(paging, platformCode, domain, "", "", "", idList);
							if(deviceByPlatform != null){
								if( deviceByPlatform.getData() != null){
									List<DeviceDetail> data = deviceByPlatform.getData();
									if(data != null && data.size() > 0){
										if(data.get(0).getDevTypeName() != null){
											scDeviceRelationDetail.setDevTypeName(data.get(0).getDevTypeName());
										}
										if(data.get(0).getPlatformAddress() != null){
											scDeviceRelationDetail.setPlatformAddress(data.get(0).getPlatformAddress());
										}
										if(data.get(0).getName() != null){
											scDeviceRelationDetail.setDeviceName(data.get(0).getName());
										}
										/*设备编码*/
										if(data.get(0).getCode() != null){
											scDeviceRelationDetail.setDeviceCode(data.get(0).getCode());
										}
									}
								}
							}
								
						}
						
					}
				}	
			}
		}else{
			//查子节点 用营销户号去取设备
			treeList = getChildListByConsId(scDeviceRelationVo.getId());
			if(treeList != null && treeList .size() > 0){
				for(ScDeviceRelationDetail scDeviceRelationDetail : treeList){
					//参数：				分页信息，平台编码，域名，设备编码，设备名，设备Id，List<String> deviceIds
					List<String> idList = new ArrayList<>();
					idList.add(scDeviceRelationDetail.getDeviceId());
//					PlatformResult<DeviceDetail> deviceByPlatform = p.getDeviceByPlatform(paging, "cloudselling-platform-release", "ysd.hhwy.org", "", "", "", idList);
					PlatformResult<DeviceDetail> deviceByPlatform = p.getDeviceByPlatform(paging, platformCode, domain, "", "", "", idList);
					
					if(deviceByPlatform.getData() != null && deviceByPlatform.getData().size() > 0){
						List<DeviceDetail> data = deviceByPlatform.getData();
						if(data != null && data.size() > 0){
							/*接口类型*/
							if(data.get(0).getDevTypeName() != null){
								scDeviceRelationDetail.setDevTypeName(data.get(0).getDevTypeName());
							}
							/*平台地址*/
							if(data.get(0).getPlatformAddress() != null){
								scDeviceRelationDetail.setPlatformAddress(data.get(0).getPlatformAddress());
							}
							/*设备名称*/
							if(data.get(0).getName() != null){
								scDeviceRelationDetail.setDeviceName(data.get(0).getName());
							}
							/*设备编码*/
							if(data.get(0).getCode() != null){
								scDeviceRelationDetail.setDeviceCode(data.get(0).getCode());
								scDeviceRelationDetail.setTreeId(scDeviceRelationDetail.get_parentId()+data.get(0).getDevice());
							}
						}
					}
					
				}
				
			}
		}
		queryResult.setTotal(total);
		queryResult.setData(treeList);
		return queryResult;
	}
	/**
	 * @Title: getChildListByConsId<br/>
	 * @Description:TODO(根据用户id获取子节点)<br/>
	 * @param id
	 * @return
	 * List<ScDeviceRelationDetail>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月20日 下午5:51:41
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月20日 下午5:51:41
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private List<ScDeviceRelationDetail> getChildListByConsId(String treeId) {
		//treeId: consId+"-"+营销户号
		String[] split = treeId.split("-");
		//截取营销户号做查询参数
		List<ScDeviceRelationDetail> scDeviceRelationList = new ArrayList<>();
		ScDeviceRelationVo scDeviceRelationVo = new ScDeviceRelationVo();
		//用户Id
		scDeviceRelationVo.setConsId(split[0]);
		//判断有无营销户号
		if(treeId.length() != (treeId.indexOf("-")) +1 ){
			scDeviceRelationVo.setMarketConsNo(split[1]);
		}
		Parameter.isFilterData.set(true);
		scDeviceRelationList = (List<ScDeviceRelationDetail>)dao.getBySql("scDeviceRelation.sql.getChildrenListByConsId",scDeviceRelationVo);
		Parameter.isFilterData.set(false);
		return scDeviceRelationList;
	}

	/**
	 * @Title: getParentListByParams<br/>
	 * @Description:TODO(查询设备列表根节点数据)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * List<ScDeviceRelationDetail>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月20日 下午5:33:30
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月20日 下午5:33:30
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private List<ScDeviceRelationDetail> getParentListByParams(ScDeviceRelationVo scDeviceRelationVo) throws Exception {
		if(scDeviceRelationVo == null){
			scDeviceRelationVo = new ScDeviceRelationVo();
		}
		Parameter.isFilterData.set(true);
		List<ScDeviceRelationDetail> scDeviceRelationList = (List<ScDeviceRelationDetail>)dao.getBySql("scDeviceRelation.sql.getScDeviceRelationListByParams",scDeviceRelationVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(scDeviceRelationList);
		return scDeviceRelationList;
	}

	/**
	 * @Title: getTreeCountByParams<br/>
	 * @Description:TODO(查询设备列表根节点数量)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * long
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月20日 下午5:29:35
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月20日 下午5:29:35
	 * @since  1.0.0
	 */
	private Integer getTreeCountByParams(ScDeviceRelationVo scDeviceRelationVo) {
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scDeviceRelation.sql.getScDeviceRelationCountByParams",scDeviceRelationVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	/**
	 * 根据查询条件获取对象ScDeviceRelation数量
	 * @param ScDeviceRelationVo
	 * @return Integer
	 */
	public Integer getScDeviceRelationCountByParams(ScDeviceRelationVo scDeviceRelationVo){
		Object result = dao.getOneBySQL("scDeviceRelation.sql.getScDeviceRelationCountByParams",scDeviceRelationVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象ScDeviceRelation列表
	 * @param ScDeviceRelationVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ScDeviceRelationDetail> getScDeviceRelationListByParams(ScDeviceRelationVo scDeviceRelationVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(scDeviceRelationVo == null){
			scDeviceRelationVo = new ScDeviceRelationVo();
		}
		List<ScDeviceRelationDetail> scDeviceRelationList = (List<ScDeviceRelationDetail>)dao.getBySql("scDeviceRelation.sql.getScDeviceRelationListByParams",scDeviceRelationVo);
		ConvertListUtil.convert(scDeviceRelationList);
		return scDeviceRelationList;
	}
	/**
	 * 根据查询条件获取单个对象ScDeviceRelation
	 * @param ScDeviceRelationVo
	 * @return ScDeviceRelation
	 */
	public ScDeviceRelationDetail getScDeviceRelationOneByParams(ScDeviceRelationVo scDeviceRelationVo) throws Exception{
		ScDeviceRelationDetail scDeviceRelation = null;
		List<ScDeviceRelationDetail> scDeviceRelationList = getScDeviceRelationListByParams(scDeviceRelationVo);
		if(scDeviceRelationList != null && scDeviceRelationList.size() > 0){
			scDeviceRelation = scDeviceRelationList.get(0);
		}
		return scDeviceRelation;
	}
	
	/**
	 * 根据ID获取对象ScDeviceRelation
	 * @param ID
	 * @return ScDeviceRelation
	 */
	public ScDeviceRelation getScDeviceRelationById(String id) {
		return dao.findById(id, ScDeviceRelation.class);
	}	
	
	/**
	 * 添加对象ScDeviceRelation
	 * @param 实体对象
	 */
	public void saveScDeviceRelation(ScDeviceRelation scDeviceRelation) {
		dao.save(scDeviceRelation);
	}
	
	/**
	 * 添加对象ScDeviceRelation
	 * @param 实体对象
	 */
	public void saveScDeviceRelationList(List<ScDeviceRelation> scDeviceRelation) {
		dao.saveList(scDeviceRelation);
	}
	
	/**
	 * 更新对象ScDeviceRelation
	 * @param 实体对象
	 */
	public void updateScDeviceRelation(ScDeviceRelation scDeviceRelation) {
		dao.update(scDeviceRelation);
	}
	
	/**
	 * 删除对象ScDeviceRelation
	 * @param id数据组
	 */
	public void deleteScDeviceRelation(Map<String, List<Map<String, String>>> paramsMap) {
		//删根节点
		if(paramsMap.get("parentList") != null ){
			dao.deleteBySql("scDeviceRelation.sql.deleteTreeParentList",paramsMap);
		}
		//删子节点
		if(paramsMap.get("childtenList") != null){
			dao.deleteBySql("scDeviceRelation.sql.deleteTreeChildrenList",paramsMap.get("childtenList"));
		}
	}
	
	/**
	 * @Title: deleteBymarketConsNo<br/>
	 * @Description:TODO(根据营销户号删除数据，用户档案那里可以直接删除营销户号)<br/>
	 * @param id
	 * void
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月25日 下午5:01:56
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年11月27日 下午5:01:56
	 * @since  1.0.0
	 */
	public void deleteByMarketConsNo(String id) {
		dao.deleteBySql("scDeviceRelation.sql.deleteByMarketConsNo", id);
	}
	/**
	 * 根据用户删除设备关系数据 删除用户档案时需要 by-zhangzhao 7M12 
	 */
	public void deleteByConsId(String[] consIds){
		dao.deleteBySql("scDeviceRelation.sql.deleteByConsId", consIds);
	}
	
	/**
	 * @Title: getScDeviceRelationAddByPage<br/>
	 * @Description:TODO(设备管理新增页面列表)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月20日 下午3:00:39
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月20日 下午3:00:39
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<DeviceDetail> getScDeviRelaAddByPage(ScDeviceRelationVo scDeviceRelationVo, String domain,String platformCode) {
		//调采集接口
		PlatformInterface p = RemoteProcedureCall.getInstancce().getService(PlatformInterface.class);
		
		QueryResult<DeviceDetail> queryResult = new QueryResult<DeviceDetail>();
		com.hhwy.collectionplatform.all.util.Paging paging = new com.hhwy.collectionplatform.all.util.Paging();
		paging.setPaging(true);
		paging.setPage(scDeviceRelationVo.getPage());
		paging.setRows(scDeviceRelationVo.getRows());
		//参数：				分页信息，平台编码，域名，设备编码，设备名，设备类型，List<String> deviceIds
//		PlatformResult<DeviceDetail> deviceByPlatform = p.getDeviceByPlatform(paging, "cloudselling-platform-release", "ysd.hhwy.org", scDeviceRelationVo.getDeviceCode(), scDeviceRelationVo.getName(), null, null);
		PlatformResult<DeviceDetail> deviceByPlatform = p.getDeviceByPlatform(paging, platformCode, domain, scDeviceRelationVo.getDeviceCode(), scDeviceRelationVo.getName(), null, null);
		//获取数据
		List<DeviceDetail> data = new ArrayList<>();
		Integer count = 0;
		if(deviceByPlatform != null){
			data = deviceByPlatform.getData();
			count = deviceByPlatform.getCount();
			
		}
		List<String> bySql = (List<String>) dao.getBySql("scDeviceRelation.sql.getTotalDeviceId", null);
		//设备关系表已有的设备
		Map<String,Object> totalDeviceIdMap = new HashMap<>();
		for(int i=0; i<bySql.size(); i++){
			totalDeviceIdMap.put(bySql.get(i), bySql.get(i));
		}
		//只显示没挂关系的设备
		List<DeviceDetail> result = new ArrayList<>();
		for(int i=0; i<data.size(); i++){
			String deviceId = data.get(i).getDevice();
			if(totalDeviceIdMap.get(deviceId) == null){
				//没挂关系的设备
				result.add(data.get(i));
			}
		}
		queryResult.setTotal(count.longValue());
		queryResult.setData(result);
		return queryResult;
	}

	
	/**
	 * @Title: getConsTree<br/>
	 * @Description:TODO(查询报表选择用户树)<br/>
	 * @return
	 * List<ScDeviceRelationDetail>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月22日 上午8:54:03
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月22日 上午8:54:03
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ScDeviceRelationDetail> getConsTree() {
		Parameter.isFilterData.set(true);
		List<ScDeviceRelationDetail> consTree = (List<ScDeviceRelationDetail>) dao.getBySql("scDeviceRelation.sql.getConsTree", null);
		Parameter.isFilterData.set(false);
		return consTree;
	}

	/**
	 * @Title: getRealTimePower<br/>
	 * @Description:TODO(查询实时电量)<br/>
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月22日 下午3:29:56
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月22日 下午3:29:56
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public Map<String, Object> getRealTimePower(ScDeviceRelationVo scDeviceRelationVo) throws Exception {
		//condId为空，sql中查询所有用户
		Parameter.isFilterData.set(true);
		List<ScDeviceRelationDetail> scConsDeviceInfoList = (List<ScDeviceRelationDetail>) dao.getBySql("scDeviceRelation.sql.getConsDeviceInfo", scDeviceRelationVo);
		Parameter.isFilterData.set(false);
		List<String> deviceList = new ArrayList<>();
		for(ScDeviceRelationDetail consDeviceInfo : scConsDeviceInfoList){
			deviceList.add(consDeviceInfo.getDeviceId());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = sdf.parse(scDeviceRelationVo.getYmd());
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(parse);
		calendar.add(calendar.DATE, 1);
		String tomorrow = sdf.format(calendar.getTime());
		
		//调采集接口 
		PlatformInterface p = RemoteProcedureCall.getInstancce().getService(PlatformInterface.class);
		//取设备对应的倍率                                                      未完成
		List<String> multipleRateByDeviceIdList = p.getMultipleRateByDeviceIdList(deviceList);
		Map<String, String> deviceMultipleRateMap = null;
		if(multipleRateByDeviceIdList != null && multipleRateByDeviceIdList.size() > 0 && multipleRateByDeviceIdList.get(0) != null){
			deviceMultipleRateMap = new HashMap<>();
			for(int i=0; i<deviceList.size(); i++){
				deviceMultipleRateMap.put(deviceList.get(i), multipleRateByDeviceIdList.get(i));
			}
		}
		
		//远程调用 
		RealDataInterface realDataInterface= RemoteProcedureCall.getInstancce().getService(RealDataInterface.class);
		//参数 设备idList,时间
		Map<String, List<String>> result =realDataInterface.getElectrivityByDeviceList(deviceList, scDeviceRelationVo.getYmd());
		//明天的 用于计算今天的最后一个点
		Map<String, List<String>> tomorrowResult =realDataInterface.getElectrivityByDeviceList(deviceList, tomorrow);
		//前台所需数据处理
		Map<String, Object> disposeResult = new HashMap<>();
		//判断是否选择的全部用户
		Boolean allConsId = false;
		if(scDeviceRelationVo.getConsId() == null || "".equals(scDeviceRelationVo.getConsId())){
			allConsId = true;
		}
		if(result != null){
			disposeResult = disposeResult(result, scConsDeviceInfoList, allConsId, tomorrowResult,deviceMultipleRateMap); 
		}
		return disposeResult;
	}

	/**
	 * @Title: disposeResu<br/>
	 * @Description:TODO(实时电量前台所需数据处理)<br/>
	 * @param result
	 * @param scConsDeviceInfoList 
	 * @return  map 时间轴：288点（5分钟的） ，营销户号：实时电量,vFor: 前台需要循环样式的次数，5分钟时间轴个：list, 15分钟时间轴： list
	 * List<Map<String,Object>>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月25日 上午9:49:41
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月25日 上午9:49:41
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> disposeResult(Map<String, List<String>> result, 
			List<ScDeviceRelationDetail> scConsDeviceInfoList, Boolean allConsId, Map<String, List<String>> tomorrowResult, Map<String, String> deviceMultipleRateMap) {//参数（接口返回数据，用于匹配属于哪个营销户号）页面按营销户号显示
		//将示数处理成电量
		for(int i=0; i<scConsDeviceInfoList.size(); i++){
					/*接口返回数据*/
			List<String> thisList = new ArrayList<>();
			List<String> tomorrowList =  new ArrayList<>();
			String rate = "1";
			//当前日期 ,设备id 对应的电量
			if(result != null){
				thisList = result.get(scConsDeviceInfoList.get(i).getDeviceId());
				if(deviceMultipleRateMap != null){
					if(deviceMultipleRateMap.get(scConsDeviceInfoList.get(i).getDeviceId()) != null){
						rate = deviceMultipleRateMap.get(scConsDeviceInfoList.get(i).getDeviceId()); //倍率
					}
				}
			}
			//明天 ,设备id 对应的电量
			if(tomorrowResult != null){
				tomorrowList = tomorrowResult.get(scConsDeviceInfoList.get(i).getDeviceId());
			}
			//今天有数据
			if(thisList != null && thisList.size() > 0){
				//处理的电量 某点电量=下一个点示数-当前点示数  其中一个示数没有就不计算 
				List<String> pqList = new ArrayList<>();
				for(int j=0 ; j < thisList.size(); j++){
						BigDecimal thisTimePq = null;										//当前点电量
						if(j+1 < 286 && j+1 < thisList.size()){ 											//第一个点 从 00：00（昨天的点）不要，所以电量List的第一个点应从00：05开始 最后一个点 应为明天00：00减23：55
							String thisPoint =  thisList.get(j+1); 							//当前点
							if(thisPoint != null && !"".equals(thisPoint) && !"*".equals(thisPoint)){				//当前点有数据 在计算电量
								String beforePoint =  thisList.get(j); 												//前一个点
								thisTimePq = pqDataDispose(thisPoint, beforePoint);									//示数处理
							}
						}else if(j == 287){ 																				//最后一个点的时候
							if(tomorrowList != null && tomorrowList.size() > 0){	
								String thisPoint = tomorrowList.get(0);												//当前点
								if(thisPoint != null && !"".equals(thisPoint) && !"*".equals(thisPoint)){					
									String beforePoint =  thisList.get(287);  										//前一个点
									thisTimePq = pqDataDispose(thisPoint, beforePoint);								//示数处理
								}
							}
						}
							
						if(thisTimePq != null){
							thisTimePq = thisTimePq.multiply(new BigDecimal(rate));
							pqList.add(thisTimePq.toString());
						}else{
							pqList.add(null);
						}
					result.put(scConsDeviceInfoList.get(i).getDeviceId(), pqList);									//将处理后的示数  替换原来的list
				}
			}
		}
		
		Map<String, Object> resultMap = new HashMap<>();										//设备营销户号 对 应电量
		List<String> timeList = new ArrayList<>();												//5分钟Echart时间轴
		List<String> date = result.get("timeList");												//接口返回时间： 格式 年月日 时分秒
		List<String> vFor = new ArrayList<>();													//用于前台循环div
		
		//没选择全部用户，全部用户只
			//组装数据  营销户号：电量数据
			for(int i=0; i<scConsDeviceInfoList.size(); i++){
				if(result.containsKey(scConsDeviceInfoList.get(i).getDeviceId())){ 				 //匹配有电量数据的设备
					String marketConsNo = scConsDeviceInfoList.get(i).getMarketConsNo();
					
					if(allConsId != true){		//单个用户 
						if(marketConsNo == null || "".equals(marketConsNo)){ //没有营销户号的情况 marketConsNo=总的
							marketConsNo = "总";
						}
					}else{						//全部用户
						marketConsNo = i + "";
					}
					if(resultMap.get(marketConsNo) == null){ 										//组装数据  map中没此营销户号  ， 一个营销户号对应多个设备
						resultMap.put(marketConsNo, result.get(scConsDeviceInfoList.get(i).getDeviceId()));			//今天数据
						vFor.add(marketConsNo);														// 用于确定vFor的次数
					}else{																			//处理同一营销户号下的数据，则加和,一个营销呼号下多个设备
						
						List<String> pq = (List<String>) resultMap.get(marketConsNo);				//获取已有电量集合
						List<String> noMapPq = result.get(scConsDeviceInfoList.get(i).getDeviceId());//同一营销呼号下 未存的电量结合
						//存加和的电量
						List<String> sumPq = new ArrayList<>();
						//同营销户号下的设备加和，一个营销呼号下多个设备
						for(int pqSize=0; pqSize<pq.size(); pqSize++){  //循环288次
							BigDecimal zero = BigDecimal.ZERO;
							if(null != pq.get(pqSize) && !"".equals( pq.get(pqSize)) && !"*".equals(pq.get(pqSize))){
								zero = zero.add(new BigDecimal(pq.get(pqSize)));
							}
							if(null != noMapPq.get(pqSize) && !"".equals(noMapPq.get(pqSize)) && !"*".equals(noMapPq.get(pqSize))){
								zero = zero.add(new BigDecimal(noMapPq.get(pqSize)));
							}
							sumPq.add(zero.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
						}
						resultMap.put(marketConsNo, sumPq);
					}
				}
			}
		if(resultMap.size() > 1){															//有一个营销户号以上，要加一个总的
			List<String> sumList = new ArrayList<>();
			int a = 1;
			for(String key : resultMap.keySet()){											//循环营销户号
				
				if(a == 1){		
					sumList.addAll((List<String>) resultMap.get(key));
				}else{
					List<String> meterNoPqList = (List<String>) resultMap.get(key);			//获取营销户号对应电量
					for(int i=0; i< meterNoPqList.size(); i++){								//分时 电量加和
						String pq = sumList.get(i);										//加和的电量		
						String pqMeterNo = meterNoPqList.get(i);							//当前营销户号下电量
						BigDecimal add =  null;									// 此点电量
						if(pq != null && !"*".equals(pq) && !"".equals(pq) ){
							add = BigDecimal.ZERO;
							add =add.add(new BigDecimal(pq));			//一个时刻电量和
						}
						if(pqMeterNo != null && !"*".equals(pqMeterNo) && !"".equals(pqMeterNo)){
							if(add == null){
								add = BigDecimal.ZERO;
							}
							add = add.add(new BigDecimal(pqMeterNo));
						}
						sumList.set(i, add == null? null:add.toString());
					}
				}
				a++;
			}
			resultMap.put("总", sumList);//分时 电量加和
			vFor.add(0, "总");//总的 放第一个位置
		}
		
		//组装页面列表、Echart图所需数据
		for(int i=0; i<vFor.size(); i++){
			String vF = vFor.get(i);
			//组装数据方法
			packageData(resultMap, timeList, date, vF, i);
			
		}
		resultMap.put("vFor", vFor);
		resultMap.put("fiveMinTimeList", timeList);//5分钟时间X轴
		return resultMap;
	}
	
	//组装列表所需数据  当天数据 resultMap营销号：电量List，timeList Echart时间轴，date电量数据时间List，vF营销户号, i:循环次数
	@SuppressWarnings("unchecked")
	private void  packageData(Map<String, Object> resultMap, List<String> timeList, List<String> date, String vF ,int i) {

		String[] fiveArr = {"time","fiveMin","tenMin","fifMin","twenMin","fwenFiveMin","thirMin","thirFiveMin","fortMin","fortFiveMin","fiftMin","fiftFiveMin","sixtMin"};
		String[] arr = { "time","fifMin","thirMin","forFiveMin","sixTM"};
		List<BigDecimal> thMinPqEchartlist = new ArrayList<>();//echart图用 15分钟电量数据
		List<String> thMinutesTimeList = new ArrayList<>();//echart图用 15分钟时间轴 
		List<Map<String, Object>> thMinutesTableList = new ArrayList<>();//15分钟列表数据
		
		List<BigDecimal> fiveMinPqEchartlist = new ArrayList<>();//echart图用 5分钟电量数据 
		List<Map<String, Object>> fiveTableList = new ArrayList<>();//5分钟列表数据
		Map<String, Object> tableMap = new HashMap<>();
		Map<String, Object> fiveTableMap = new HashMap<>();
		
		//当前营销户号对应数据
		List<String> pqList = (List<String>) resultMap.get(vF);   //今天
		int fiveArrIndex = 0;											//用来 控制5分钟列表一行数据的长度
		int arrIndex = 0;												//用来 控制15分钟列表一行数据的长度
		BigDecimal fiveMinTotal = null;									//5分钟合计            没值时是0 需要解决，还需增加判断 *
		BigDecimal fifMinTotal = null;									//15分钟合计
		BigDecimal fifPq = null;										//每隔15分钟的累计
		for(int thMinutes=0; thMinutes<pqList.size(); thMinutes++){		//15分钟数据 间隔3
			if(i == 0){
				if(thMinutes < 288 && thMinutes < date.size()){									//5分钟时间轴，需从00：05开始
					timeList.add((date.get(thMinutes)).substring(11, 16));
				}
			}
			 /*列表时间 整点字段*/
			if(thMinutes % 12 == 0){
				//15分钟
				tableMap.put(fiveArr[fiveArrIndex], (date.get(thMinutes).substring(11, 13))+ "时");
				fiveArrIndex++;
				//5分钟
				fiveTableMap.put(arr[arrIndex], (date.get(thMinutes).substring(11, 13))+ "时");
				arrIndex++;
			}
			/*处理间隔 15分钟数据*/
			if(thMinutes % 3 == 0){
				if(i == 0){
					//时间轴需从00：00开始
					if(thMinutes == 0){
						thMinutesTimeList.add(date.get(0).substring(11, 16));//Echart时间轴
					}else if(thMinutes <= 288 && thMinutes<date.size()){
						thMinutesTimeList.add(date.get(thMinutes).substring(11, 16));//Echart时间轴
					}
				}
			}
			if(pqList.get(thMinutes) != null && !"".equals(pqList.get(thMinutes)) && !"*".equals(pqList.get(thMinutes))){
				if(fifPq == null){
					fifPq = BigDecimal.ZERO;					//15分钟电量
				}
				fifPq = fifPq.add(new BigDecimal(pqList.get(thMinutes).toString()));
			}
			if((thMinutes+1) % 3 == 0){
				thMinPqEchartlist.add(fifPq);				//添加15分钟电量数据 Echart用
				tableMap.put(arr[arrIndex],fifPq);			//行 每隔15分钟数据
				
				if(fifMinTotal == null){
					fifMinTotal = BigDecimal.ZERO;				//行合计
				}
				if(fifPq != null){
					fifMinTotal = fifMinTotal.add(new BigDecimal(fifPq.toString()));
				}
				fifPq = null;								//清空15分钟数据
				arrIndex++;
			
			}
			/*15分钟数据*/
			if(tableMap.size() == 5){
				//合计数据
				BigDecimal rowTotalPq = null; 
				if(fifMinTotal != null){
					rowTotalPq = fifMinTotal.setScale(3, BigDecimal.ROUND_HALF_UP);
				}
				tableMap.put("total", rowTotalPq);
				//存一行
				thMinutesTableList.add(tableMap);
				tableMap = new HashMap<>();//清空之前行数据 避免下次数据累计
				arrIndex = 0;
				fifMinTotal = null;//合计清空
			}		
			
			/*处理间隔 5分钟数据*/
			if(fiveTableMap.size() < 14){ //一行电量数据
				fiveTableMap.put(fiveArr[fiveArrIndex],pqList.get(thMinutes));   //一行内 电量数据
				if(pqList.get(thMinutes) != null){
					if(fiveMinTotal == null){
						fiveMinTotal = BigDecimal.ZERO;
					}
					fiveMinTotal = fiveMinTotal.add(new BigDecimal(pqList.get(thMinutes).toString()));//合计
				}
				if(pqList.get(thMinutes) != null){
					fiveMinPqEchartlist.add(new BigDecimal(pqList.get(thMinutes).toString()));
				}else{
					fiveMinPqEchartlist.add(null);
				}
				fiveArrIndex++;
			}
			/*五分钟一行数据完成*/
			if(fiveTableMap.size() == 13){
				//合计
				BigDecimal rowfiveTotalPq = null; 
				if(fiveMinTotal != null){
					rowfiveTotalPq = fiveMinTotal.setScale(3, BigDecimal.ROUND_HALF_UP);
				}
				fiveTableMap.put("total", rowfiveTotalPq);
				fiveTableList.add(fiveTableMap);									//存一行
				fiveTableMap = new HashMap<>();										//清空之前行数据 避免下次数据累计
				fiveArrIndex = 0;													//列名下标初始												
				fiveMinTotal = null;												//合计清零
			}
			
		}
		
		if(i == 0){ //15分钟时间轴
			resultMap.put("fifMinTimeList", thMinutesTimeList);
		}
		//15分钟电量Echart
		resultMap.put(vF + "Echart", thMinPqEchartlist);
		//15分钟列表数据
		resultMap.put(vF + "Table", thMinutesTableList);
		//5分钟电量Echart
		resultMap.put(vF + "EchartFive", fiveMinPqEchartlist);
		//5分钟列表数据
		resultMap.put(vF + "TableFive", fiveTableList);
	}
	/**
	 * @Title: pqDataDispose<br/>
	 * @Description:TODO(实时电量列表数据处理)<br/>
	 * @param backPoint
	 * @param beforePoint
	 * @return
	 * BigDecimal
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年7月5日 下午3:23:55
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年7月5日 下午3:23:55
	 * @since  1.0.0
	 */
	private BigDecimal pqDataDispose(String backPoint, String beforePoint) {
		BigDecimal upFifPq = BigDecimal.ZERO;
		//前点无数据
		if(beforePoint == null || "".equals(beforePoint) || "*".equals(beforePoint)){
			return null;
		}
		//此点小于前点
		if(-1 == new BigDecimal(backPoint).compareTo(new BigDecimal(beforePoint))){
			String[] split = beforePoint.split(".");
			Boolean flag = false;
			//判断前点是不是整数
			if(!beforePoint.contains(".")){
				flag = true;
			}
			if(split.length > 0 || flag){
				String num = null;
				if(flag){
					num = new BigDecimal(beforePoint).toString();
				}else{
					num = split[0];
				}
				 
				String intNum = "1";
				for(int bit=0; bit < num.length(); bit++){
					intNum += "0";
				}
				//表反转  整数补零的-前点+当前点
				upFifPq = new BigDecimal(intNum).subtract(new BigDecimal(beforePoint)).add(new BigDecimal(backPoint));
			}
		}else{ //此点大于前点  直接减
			upFifPq = new BigDecimal(backPoint).subtract(new BigDecimal(beforePoint));
		}
		return upFifPq;
	}

	/**
	 * @Title: getdayPqForm<br/>
	 * @Description:TODO(日电量报表数据)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年7月2日 上午11:29:01
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年7月2日 上午11:29:01
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getdayPqForm(ScDeviceRelationVo scDeviceRelationVo) throws Exception {
		
		String yyyyMM = scDeviceRelationVo.getYm().replace("-", "");
		//返回map
		Map<String, Object> resultMap = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfMD = new SimpleDateFormat("MM-dd");
		
		//远程调用 
		RealDataInterface realDataInterface= RemoteProcedureCall.getInstancce().getService(RealDataInterface.class);
		//condId为空，sql中查询所有用户
		Parameter.isFilterData.set(true);
		List<ScDeviceRelationDetail> scConsDeviceInfoList = (List<ScDeviceRelationDetail>) dao.getBySql("scDeviceRelation.sql.getConsDeviceInfo", scDeviceRelationVo);
		Parameter.isFilterData.set(false);
		//接口参数，同一营销户号下设备id集合
		List<String> deviceIdList = new ArrayList<>();
		//所用设备id集合
		List<String> totalDeviceIdList = new ArrayList<>();
		//营销户号
		List<String> vFor = new ArrayList<>();
		//接口返回数据
		List<List<EmbusinessDataCollectDetail>> interData = new ArrayList<>();
		
		//例日 起止日期
		String startDate = null;
		String endDate =  null;
		//开始、结束日期 相差多少天
		int days = 0;
		Map<String, Map<String, Object>> treeMap = new TreeMap<>();
		
		//选择全部用户时
		if(scDeviceRelationVo.getConsId() == null || "".equals(scDeviceRelationVo.getConsId())){
			
			Map<String, List<String>> consDevideMap = new HashMap<>();
			//同一用户下设备组装到一起
			for(ScDeviceRelationDetail deviceRelationDetail : scConsDeviceInfoList){
				String consId = deviceRelationDetail.getConsId();		//用户id
				String deviceId = deviceRelationDetail.getDeviceId();	//设备id
				//未有用户
				if(consDevideMap.get(consId) == null || consDevideMap.get(consId).size() < 1){
					List<String> deviceList = new ArrayList<>();
					deviceList.add(deviceId);
					consDevideMap.put(consId, deviceList);
				}else{
				//已有用户
					List<String> list = consDevideMap.get(consId);
					list.add(deviceId);
					consDevideMap.put(consId, list);
				}
				
			}
			
			//每个用户例日对应的数据
			for(String key : consDevideMap.keySet()){
				//获取用户例日开始日期、结束日期
				//Map<String,Object> 包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate,
				// 				value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）<br/>
				Map<String, Object> consDay = scConsDateService.getDateAndDays(key, yyyyMM);
				String sDate = this.convertDate(consDay.get("startDate").toString());
				String eDate = this.convertDate(consDay.get("endDate").toString());
				//取最小日期
				if(startDate == null){
					startDate = sDate;
				}else{
					if(sDate.compareTo(startDate) < 0){
						startDate = sDate;
					}
				}
				//取最大日期
				if(endDate == null){
					endDate = eDate;
				}else{
					if(eDate.compareTo(endDate) > 0){
						endDate = eDate;
					}
				}
				
				List<EmbusinessDataCollectDetail> embusinessDataCollectDetailsByParams = realDataInterface.getEmbusinessDataCollectDetailsByParams(sDate, eDate, consDevideMap.get(key));
				//将千瓦时处理成兆瓦时 -Method
				List<Map<String, Object>> disposeDayPqFormData = disposeDayPqFormData(embusinessDataCollectDetailsByParams);
				//将数据处理成map
				for(Map<String, Object> dayMap : disposeDayPqFormData){ //每天数据
					String mmDdDate = sdfMD.format(dayMap.get("dataDate"));//日期
					if(treeMap.containsKey(mmDdDate)){
						Map<String, Object> existMap = treeMap.get(mmDdDate);
						//处理对应数据加和												 -Method
						Map<String, Object> hashMap = mapDataSum(existMap, dayMap);
						treeMap.put(mmDdDate,hashMap);
					}else{
						treeMap.put(mmDdDate, dayMap);
					}
				}
			}
			
		}else{//单个用户
			//获取用户例日开始日期、结束日期
			//Map<String,Object> 包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate,
			// 				value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）<br/>
			Map<String, Object> dateAndDays = scConsDateService.getDateAndDays(scDeviceRelationVo.getConsId(), yyyyMM);
			startDate = this.convertDate(dateAndDays.get("startDate").toString());
			endDate = this.convertDate(dateAndDays.get("endDate").toString());
			
			//开始、结束日期 相差多少天
			days = (int) dateAndDays.get("days");
			for(int i=0; i <= scConsDeviceInfoList.size(); i++){
				ScDeviceRelationDetail consDeviceInfo  = null;
				if(i == scConsDeviceInfoList.size()){
					consDeviceInfo = null;
				}else{
					consDeviceInfo = scConsDeviceInfoList.get(i);
				}
				
				if(vFor.size() != 0){
					//下一个营销户号
					if(i == scConsDeviceInfoList.size() || !vFor.contains(consDeviceInfo.getMarketConsNo())){
						//参数 设备idList,时间
//						List<EmbusinessDataCollectDetail> embusinessDataCollectDetailsByParams = realDataInterface.getEmbusinessDataCollectDetailsByParams("2018-07-01", "2018-07-28", deviceIdList);
						List<EmbusinessDataCollectDetail> embusinessDataCollectDetailsByParams = realDataInterface.getEmbusinessDataCollectDetailsByParams(startDate, endDate, deviceIdList);
						if(null != embusinessDataCollectDetailsByParams && embusinessDataCollectDetailsByParams.size() > 0){
							interData.add(embusinessDataCollectDetailsByParams);
						}else{
//							interData.add(new ArrayList<EmbusinessDataCollectDetail>());
							vFor.remove(vFor.size()-1);
						}
						//清空 上个营销户号的数据集合
						deviceIdList.clear();
					}
				}
				if(null != consDeviceInfo){
					if(!vFor.contains(consDeviceInfo.getMarketConsNo())){
						//用于判断，是否属于同一营销户号下
						vFor.add(consDeviceInfo.getMarketConsNo());
					}
					//添加此营销户号下的设备id
					deviceIdList.add(consDeviceInfo.getDeviceId());
					totalDeviceIdList.add(consDeviceInfo.getDeviceId());
				}
			}
			//两个个营销户号以上,加一个总的
			if(null != vFor && vFor.size() > 1){
				//参数 设备idList,时间
//				List<EmbusinessDataCollectDetail> sumDeailList = realDataInterface.getEmbusinessDataCollectDetailsByParams("2018-07-01", "2018-07-28", totalDeviceIdList);
				List<EmbusinessDataCollectDetail> sumDeailList = realDataInterface.getEmbusinessDataCollectDetailsByParams(startDate, endDate, totalDeviceIdList);
				interData.add(0, sumDeailList);
				vFor.add(0, "总");
			}
			
		}
			/*将返回数据处理成Echart所需数据*/
		List<List<Map<String, Object>>> arrayList = new ArrayList<>();
//		Map<String, Object> echartMap = new HashMap<>();
		List<Object> echartList = new ArrayList<>();
		//X轴数据
		List<String> echartXList =  new ArrayList<>();
		
		//时间对应下标：用于解决不同设备可能date不同
		Map<String, Integer> dateIndexMap = new HashMap<>();
		//一个月，天数长度的list
		List<String> monthDay = new ArrayList<>();
		//选择全部用户时， 与单个用户处理过程统一数据格式
		if(scConsDeviceInfoList != null && scConsDeviceInfoList.size() > 0){
			if(scDeviceRelationVo.getConsId() == null || "".equals(scDeviceRelationVo.getConsId())){
				List<Map<String, Object>> list = new ArrayList<>();
				for(String key : treeMap.keySet()){
					list.add(treeMap.get(key));
				}
				arrayList.add(list);
				vFor.add(0, "总");
				long d1 = sdf.parse(startDate).getTime();
				long d2 = sdf.parse(endDate).getTime();
				days = (int) ((d2 - d1)/(1000 * 60 * 60 * 24)) + 1; 
			}else{
			//单个用户时
				//将千瓦时 处理成兆瓦时   
				for(List<EmbusinessDataCollectDetail> unitDispose : interData){
					List<Map<String, Object>> arrayList2 = disposeDayPqFormData(unitDispose);
					arrayList.add(arrayList2);
				}
			}
			
			//开始、结束日期 相差多少天
//			int days= new Long((Long) dateAndDays.get("days")).intValue();
			Calendar c = Calendar.getInstance();
//			Date parse = sdf.parse("2018-07-01");
			Date parse = sdf.parse(startDate);
	        c.setTime(parse);
	        String format = null;
			for(int i=0; i<days; i++){
				if(i != 0){
					c.add(Calendar.DAY_OF_MONTH, 1);//+1天
				}
				format = sdf.format(c.getTime());
				dateIndexMap.put(format, i);
				//Echart X轴数据 mm-dd
				echartXList.add(sdfMD.format(c.getTime()));
				monthDay.add(null);
			}
			//处理每个列表数据
			this.listGridDataMethod(vFor, arrayList, monthDay, arrayList,dateIndexMap, echartList, sdf ,sdfMD);
		}
		
		resultMap.put("vFor", vFor);
		resultMap.put("table", arrayList);
		resultMap.put("echart", echartList);
		resultMap.put("echartXList", echartXList);
		return resultMap;
	}
	//处理每个列表数据
	private void listGridDataMethod(List<String> vFor, List<List<Map<String, Object>>> arrayList,
			List<String> monthDay, List<List<Map<String, Object>>> arrayList2, Map<String, Integer> dateIndexMap, List<Object> echartList, SimpleDateFormat sdf, SimpleDateFormat sdfMD) {
		
		for(int i=0; i<vFor.size(); i++){
			//一个营销户下的数据
			List<Map<String, Object>> oneMarketNoData = arrayList.get(i);
			//峰 平 谷 尖峰 低谷
			List<String> peakListEchart = new ArrayList<>();
			List<String> plainListEchart = new ArrayList<>();
			List<String> valleyListEchart = new ArrayList<>();
			List<String> topListEchart = new ArrayList<>();
			List<String> lowListEchart = new ArrayList<>();
			//天数长度的list
			peakListEchart.addAll(monthDay);
			plainListEchart.addAll(monthDay);
			valleyListEchart.addAll(monthDay);
			topListEchart.addAll(monthDay);
			lowListEchart.addAll(monthDay);
			//数据条数
			for(int j=0 ; j<oneMarketNoData.size(); j++){
				//一个营销户号下, 一天的数据
				Map<String, Object> embusinessDataCollectDetail = oneMarketNoData.get(j);
				Date rq= (Date)embusinessDataCollectDetail.get("dataDate");
				String day = sdf.format(rq);
				//比对时间，取index
					/*峰 平 谷 尖峰 低谷 数据*/				
				String timeinterval01 = (String) embusinessDataCollectDetail.get("timeinterval01");
				String timeinterval02 = (String) embusinessDataCollectDetail.get("timeinterval02");
				String timeinterval03 = (String) embusinessDataCollectDetail.get("timeinterval03");
				String timeinterval04 = (String) embusinessDataCollectDetail.get("timeinterval04");
				String timeinterval05 = (String) embusinessDataCollectDetail.get("timeinterval05");
				//时间字段数据修改  月日
				embusinessDataCollectDetail.put("device",sdfMD.format(rq));
				//Echart所需数据,存在对应天位置(设备不一定有每天的数据)
				peakListEchart.set(dateIndexMap.get(day), timeinterval01);
				plainListEchart.set(dateIndexMap.get(day), timeinterval02);
				valleyListEchart.set(dateIndexMap.get(day), timeinterval03);
				topListEchart.set(dateIndexMap.get(day), timeinterval04);
				lowListEchart.set(dateIndexMap.get(day), timeinterval05);
			}
			List<Object> oneMarketNoList =  new ArrayList<>();
			oneMarketNoList.add(peakListEchart);
			oneMarketNoList.add(plainListEchart);
			oneMarketNoList.add(valleyListEchart);
			oneMarketNoList.add(topListEchart);
			oneMarketNoList.add(lowListEchart);
			//一个营销户号下的Echart数据
//			echartMap.put(vFor.get(i), oneMarketNoList);
			//保证顺序  前台按顺序取
			echartList.add(oneMarketNoList);
		}
	}

	/**
	 * @Description:TODO(日电量报表：全部用户时把日期相同的数据加和)<br/>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 */
	public Map<String, Object> mapDataSum(Map<String, Object> existMap, Map<String, Object> dayMap){
//		SimpleDateFormat sdfMD = new SimpleDateFormat("MM-dd");
		Map<String, Object> hashMap = new HashMap<>();
		
		Object mapExistNum01 = existMap.get("timeinterval01");
		Object mapAt01 = dayMap.get("timeinterval01");
		String sameDayNumberSum01 = sameDayNumberSum(mapExistNum01,mapAt01);
		
		Object mapExistNum02 = existMap.get("timeinterval02");
		Object mapAt02 = dayMap.get("timeinterval02");
		String sameDayNumberSum02 = sameDayNumberSum(mapExistNum02,mapAt02);
		
		Object mapExistNum03 = existMap.get("timeinterval03");
		Object mapAt03 = dayMap.get("timeinterval03");
		String sameDayNumberSum03 = sameDayNumberSum(mapExistNum03,mapAt03);
		
		Object mapExistNum04 = existMap.get("timeinterval04");
		Object mapAt04 = dayMap.get("timeinterval04");
		String sameDayNumberSum04 = sameDayNumberSum(mapExistNum04,mapAt04);
		
		Object mapExistNum05 = existMap.get("timeinterval05");
		Object mapAt05 = dayMap.get("timeinterval05");
		String sameDayNumberSum05 = sameDayNumberSum(mapExistNum05,mapAt05);
		
		Object mapExistTotal = existMap.get("totalElectricQuantity");
		Object mapAtTotal = dayMap.get("totalElectricQuantity");
		String sameDayNumberSumTotal = sameDayNumberSum(mapExistTotal,mapAtTotal);
		
		hashMap.put("timeinterval01", sameDayNumberSum01);
		hashMap.put("timeinterval02", sameDayNumberSum02);
		hashMap.put("timeinterval03", sameDayNumberSum03);
		hashMap.put("timeinterval04", sameDayNumberSum04);
		hashMap.put("timeinterval05", sameDayNumberSum05);
		hashMap.put("totalElectricQuantity", sameDayNumberSumTotal);
		hashMap.put("dataDate",existMap.get("dataDate"));
		return hashMap;
	}
	/**
	 * @Description:TODO(日电量报表：全部用户时把日期相同的数据加和)<br/>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 */
	public String sameDayNumberSum(Object mapExist, Object mapAt){
		BigDecimal newNum = null;
		if(mapExist != null){
			BigDecimal numAt01 =  mapAt == null ? BigDecimal.ZERO : new BigDecimal(mapAt.toString());
			newNum = new BigDecimal(mapExist.toString()).add(numAt01);
		}else{
			if(mapAt != null){
				newNum = new BigDecimal(mapAt.toString());
			}
		}
		return newNum == null ? "" : newNum.toString();
	}
	
	/**
	 * @Description:TODO(将千瓦时 处理成兆瓦时  )<br/>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 */
	public List<Map<String , Object>> disposeDayPqFormData(List<EmbusinessDataCollectDetail> unitDispose){
		List<Map<String, Object>> arrayList2 = new ArrayList<>(); 
		//  将千瓦时 处理成兆瓦时  
		String mWH = null;
		for(int i=0; i<unitDispose.size(); i++){
			//实体类是Long类型  需要创建个Map存
			Map<String, Object> hashMap = new HashMap<>();
			if(unitDispose.get(i).getDataDate() != null){
				hashMap.put("dataDate", unitDispose.get(i).getDataDate());
			}
			if(unitDispose.get(i).getTimeinterval01() != null){
				mWH = new BigDecimal(unitDispose.get(i).getTimeinterval01()).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
				hashMap.put("timeinterval01", mWH);
			}
			if(unitDispose.get(i).getTimeinterval02() != null){
				mWH =  new BigDecimal(unitDispose.get(i).getTimeinterval02()).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
				hashMap.put("timeinterval02", mWH);
			}
			if(unitDispose.get(i).getTimeinterval03() != null){
				mWH =  new BigDecimal(unitDispose.get(i).getTimeinterval03()).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
				hashMap.put("timeinterval03", mWH);
			}
			if(unitDispose.get(i).getTimeinterval04() != null){
				mWH =  new BigDecimal(unitDispose.get(i).getTimeinterval04()).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
				hashMap.put("timeinterval04", mWH);
			}
			if(unitDispose.get(i).getTimeinterval05() != null){
				mWH =  new BigDecimal(unitDispose.get(i).getTimeinterval05()).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
				hashMap.put("timeinterval05", mWH);
			}
			if(unitDispose.get(i).getTotalElectricQuantity() != null){
				mWH =  new BigDecimal(unitDispose.get(i).getTotalElectricQuantity()).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
				hashMap.put("totalElectricQuantity", mWH);
			}
			arrayList2.add(hashMap);
		}
		return arrayList2;
		
	}
	
	/**
	 * @Title: getMonthPqForm<br/>
	 * @Description:TODO(月电量报表)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年7月3日 下午5:37:52
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年7月3日 下午5:37:52
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings({"unchecked" })
	@Override
	public Map<String, Object> getMonthPqForm(ScDeviceRelationVo scDeviceRelationVo) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		//选择年
		String year = scDeviceRelationVo.getYear();
		//远程调用 
		RealDataInterface realDataInterface= RemoteProcedureCall.getInstancce().getService(RealDataInterface.class);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sdfMD = new SimpleDateFormat("MM-dd");
		//存12个月日期区间
		List<Map<String, Object>> dateRange = new ArrayList<>();
		//Echart的X轴数据
		List<String> echartXList = new ArrayList<>();
		String ym = null;
		for(int i=1; i<=12; i++){
			if(i <= 9){
				ym = year + "0" +i;
			}else{
				ym = year +i;
			}
			//获取12个月，电量开始日期、结束日期
			//Map<String,Object> 包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate,
			// 				value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）<br/>
			Map<String, Object> dateAndDays = scConsDateService.getDateAndDays(scDeviceRelationVo.getConsId(), ym);

			dateRange.add(dateAndDays);
			//Echart ，X轴
			echartXList.add(i+"月");
		}
		
		//condId为空，sql中查询所有用户
		Parameter.isFilterData.set(true);
		List<ScDeviceRelationDetail> scConsDeviceInfoList = (List<ScDeviceRelationDetail>) dao.getBySql("scDeviceRelation.sql.getConsDeviceInfo", scDeviceRelationVo);
		Parameter.isFilterData.set(false);
		List<String> deviceIdList = new ArrayList<>();									//同一营销户号下，设备id集合
		List<String> totalDeviceIdList = new ArrayList<>();								//所用设备id集合
		List<String> vFor = new ArrayList<>();											//营销户号， 用于前台v-for显示用
		List<List<EmbusinessDataCollectDetail>> interData = new ArrayList<>();			//多个营销呼号 ，每月 
		List<Object> echarDatatList = new ArrayList<>();								//Echart所需数据		
		
		for(int i=0; i <= scConsDeviceInfoList.size(); i++){							//循环设备数量次数
			ScDeviceRelationDetail consDeviceInfo  = null;
			if(i == scConsDeviceInfoList.size()){
				consDeviceInfo = null;
			}else{
				consDeviceInfo = scConsDeviceInfoList.get(i);
			}
			if(vFor.size() != 0){
				if(i == scConsDeviceInfoList.size() || !vFor.contains(consDeviceInfo.getMarketConsNo())){  	//将同一营销户号下的设备id刚在deviceIdList 去查询
					List<EmbusinessDataCollectDetail> embusinessDataCollectDetailsByParams = new ArrayList<>();
					List<List<EmbusinessDataCollectDetail>> monthDataList = new ArrayList<>();				//未处理年数据。size:12
					Map<String, Object> totalMonthData =  new HashMap<>();									//处理后，一年数据，每月的‘尖峰平谷..’
					for(int month=0; month<12; month++){
						String sT = this.convertDate(dateRange.get(month).get("startDate").toString());
						String eT = this.convertDate(dateRange.get(month).get("endDate").toString());
						//调采集接口 12个月数据
						embusinessDataCollectDetailsByParams = realDataInterface.getEmbusinessDataCollectDetailsByParams(sT,eT, deviceIdList);
//						embusinessDataCollectDetailsByParams = realDataInterface.getEmbusinessDataCollectDetailsByParams("2018-08-01", "2018-08-31", deviceIdList);
						if(null != embusinessDataCollectDetailsByParams && embusinessDataCollectDetailsByParams.size() > 0){
							monthDataList.add(embusinessDataCollectDetailsByParams);
						}else{
							monthDataList.add(new ArrayList<EmbusinessDataCollectDetail>());
						}
					}
					//将一年每天数据处理成月数据,"everyMonGridList":列表所需数据 ,"everyMonEchartList":Echart所需数据
					totalMonthData =  totalMonthDataF(monthDataList);
					List<EmbusinessDataCollectDetail> everyMonGridList = (List<EmbusinessDataCollectDetail>) totalMonthData.get("everyMonGridList");
					echarDatatList.add(totalMonthData.get("everyMonEchartList"));
					if(null != everyMonGridList && everyMonGridList.size() > 0){
						interData.add(everyMonGridList);
					}else{
//								interData.add(new ArrayList<EmbusinessDataCollectDetail>());
						vFor.remove(vFor.size()-1);
					}
					deviceIdList.clear();										//清空 上个营销户号的设备id集合
				}
			}
			if(null != consDeviceInfo){
				if(!vFor.contains(consDeviceInfo.getMarketConsNo())){			//避免存重复营销户号
					if(scConsDeviceInfoList.size() == 1 && consDeviceInfo.getMarketConsNo() == null){		//用户下一个设备 无营销户号
						vFor.add("总");						
					}else if(scConsDeviceInfoList.size() > 1 && consDeviceInfo.getMarketConsNo() == null){  //用户下多设备 无营销户号
						vFor.add("总"+ i);	
					}else{																					//有营销呼号
						vFor.add(consDeviceInfo.getMarketConsNo());
					}
				}
				deviceIdList.add(consDeviceInfo.getDeviceId());					//将同一营销户号下的设备id添加到deviceIdList
				totalDeviceIdList.add(consDeviceInfo.getDeviceId());			//存所有设备id
			}
		}
		
		//两个个营销户号以上,加一个总的
		if(null != vFor && vFor.size() > 1){
			List<List<EmbusinessDataCollectDetail>> yearTotalList = new ArrayList<>();
			for(int month=0; month<12; month++){
				//参数 设备idList,时间

				List<EmbusinessDataCollectDetail> sumDeailList = realDataInterface.getEmbusinessDataCollectDetailsByParams(
						this.convertDate(dateRange.get(month).get("startDate").toString()),
						this.convertDate(dateRange.get(month).get("endDate").toString()), totalDeviceIdList);
//				List<EmbusinessDataCollectDetail> sumDeailList = realDataInterface.getEmbusinessDataCollectDetailsByParams("2018-08-01", "2018-08-31", totalDeviceIdList);
				yearTotalList.add(sumDeailList);
			}
			//将一年每天数据，处理成12个月数据					-Method
			Map<String, Object> sumTotalMonthData = totalMonthDataF(yearTotalList);
			interData.add(0, (List<EmbusinessDataCollectDetail>) sumTotalMonthData.get("everyMonGridList"));		//多个营销户号，总Grid数据
			echarDatatList.add(0, sumTotalMonthData.get("everyMonEchartList"));										//多个营销户号，总的echart所需数据
			vFor.add(0, "总");
		}
		resultMap.put("vFor", vFor);																				//用于前台v-for样式循环 list
		resultMap.put("table", interData);																			//列表数据 list
		resultMap.put("echart", echarDatatList);																	//echart数据 list
		resultMap.put("echartXList", echartXList);																	//X 轴数据
		return resultMap;
	}
	/**
	 * @Title: convertDate
	 * @Description: 把yyyyMMdd日期格式转换为yyyy-MM-dd格式
	 * @param date
	 * @return 
	 * String
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 下午5:47:26
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 下午5:47:26
	 * @since  1.0.0
	 */
	private String convertDate(String date) {
		return date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8);
	}
	/*
	 * 将一年每天数据，处理成12个月数据
	 */
	private Map<String, Object> totalMonthDataF(List<List<EmbusinessDataCollectDetail>> monthDataList) {
		Map<String, Object> resultMap = new HashMap<>();
		//年，每月数据
		List<Map<String, Object> >detail = new ArrayList<>();
		//年，每月Echrt所需数据
		List<List<BigDecimal>> everyMonEchartList = new ArrayList<>();
		/*峰 平 谷 尖峰 低谷 数据*/				
		List<BigDecimal> monEchartList01 = new ArrayList<>(); 
		List<BigDecimal> monEchartList02 = new ArrayList<>(); 
		List<BigDecimal> monEchartList03 = new ArrayList<>(); 
		List<BigDecimal> monEchartList04 = new ArrayList<>(); 
		List<BigDecimal> monEchartList05 = new ArrayList<>(); 
		//12个月
		for(int i=0; i<monthDataList.size(); i++){
			
			//峰 平 谷 尖峰 低谷 合计
			Long sumTimeinterval01 = 0L;
			Long sumTimeinterval02 = 0L;
			Long sumTimeinterval03 = 0L;
			Long sumTimeinterval04 = 0L;
			Long sumTimeinterval05 = 0L;
			Long sum = 0L;
			
			//每天数据
			List<EmbusinessDataCollectDetail> day = monthDataList.get(i);
			for(int j=0; j<day.size(); j++){
				Long timeinterval01 = day.get(j).getTimeinterval01();
					sumTimeinterval01 += timeinterval01;
				Long timeinterval02 = day.get(j).getTimeinterval02();
					sumTimeinterval02 += timeinterval02;
				Long timeinterval03 = day.get(j).getTimeinterval03();
					sumTimeinterval03 += timeinterval03;
				Long timeinterval04 = day.get(j).getTimeinterval04();
					sumTimeinterval04 += timeinterval04;
				Long timeinterval05 = day.get(j).getTimeinterval05();
					sumTimeinterval05 += timeinterval05;
				Long totalElectricQuantity = day.get(j).getTotalElectricQuantity();
				sum += totalElectricQuantity;
			}
			Map<String, Object> embusinessDataCollectDetail = new HashMap<>();
			embusinessDataCollectDetail.put("timeinterval01", new BigDecimal(sumTimeinterval01).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
			embusinessDataCollectDetail.put("timeinterval02", new BigDecimal(sumTimeinterval02).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
			embusinessDataCollectDetail.put("timeinterval03", new BigDecimal(sumTimeinterval03).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
			embusinessDataCollectDetail.put("timeinterval04", new BigDecimal(sumTimeinterval04).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
			embusinessDataCollectDetail.put("timeinterval05", new BigDecimal(sumTimeinterval05).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
			embusinessDataCollectDetail.put("totalElectricQuantity",new BigDecimal(sum).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
			embusinessDataCollectDetail.put("device",(i+1)+"月");
			
			detail.add(embusinessDataCollectDetail);
			//Echart所需数据：峰List，平List...
			monEchartList01.add(new BigDecimal(sumTimeinterval01).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
			monEchartList02.add(new BigDecimal(sumTimeinterval02).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
			monEchartList03.add(new BigDecimal(sumTimeinterval03).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
			monEchartList04.add(new BigDecimal(sumTimeinterval04).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
			monEchartList05.add(new BigDecimal(sumTimeinterval05).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP));
		}
		//一个营销呼号，峰。。。数据
		everyMonEchartList.add(monEchartList01);
		everyMonEchartList.add(monEchartList02);
		everyMonEchartList.add(monEchartList03);
		everyMonEchartList.add(monEchartList04);
		everyMonEchartList.add(monEchartList05);
		
		resultMap.put("everyMonGridList", detail);
		resultMap.put("everyMonEchartList", everyMonEchartList);
		return resultMap;
	}

	

	
}
























