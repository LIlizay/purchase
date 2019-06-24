package com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchase.domain.SmPrcInfo;
import com.hhwy.purchase.domain.SmPurchaseSchemeDetail;
import com.hhwy.purchaseweb.delivery.smprcinfo.service.SmPrcInfoService;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.domain.SmPurchaseSchemeDetailDetail;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.service.SmPurchaseSchemeDetailService;
import com.hhwy.selling.domain.ScConsumerInfo;

/**
 * SmPurchaseSchemeDetailService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmPurchaseSchemeDetailServiceImpl implements SmPurchaseSchemeDetailService {

	public static final Logger log = LoggerFactory.getLogger(SmPurchaseSchemeDetailServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * smPrcInfoService		电价配置信息service
	 */
	@Autowired
	private SmPrcInfoService smPrcInfoService;
	
	/**
	 * 添加对象SmPurchaseSchemeDetail
	 * @param 实体对象
	 */
	public void saveSmPurchaseSchemeDetailList(List<SmPurchaseSchemeDetail> smPurchaseSchemeDetailList) {
		dao.saveList(smPurchaseSchemeDetailList);
	}
	
	/**
	 * @Title: deleteSchemeDetailBySchemeId
	 * @Description: 根据方案id删除其下所有方案详情信息
	 * @param schemeId 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月22日 下午5:47:50
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月22日 下午5:47:50
	 * @since  1.0.0
	 */
	public void deleteSchemeDetailBySchemeId(String schemeId) {
		dao.deleteBySql("smPurchaseSchemeDetail.sql.deleteSchemeDetailBySchemeId", schemeId);
	}
	
	/**
	 * @Title: getSchemeDetailBySchemeId
	 * @Description: 根据方案id获取方案详情信息
	 * @param schemeId
	 * @return 
	 * List<SmPurchaseSchemeDetailDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午5:18:40
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午5:18:40
	 * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SmPurchaseSchemeDetailDetail> getSchemeDetailBySchemeId(String schemeId) {
		List<SmPurchaseSchemeDetailDetail> list=(List<SmPurchaseSchemeDetailDetail>) dao.getBySql("smPurchaseSchemeDetail.sql.getSchemeDetailBySchemeId", schemeId);
		/*for(SmPurchaseSchemeDetailDetail temp:list){
			if(temp.getLcPq()!=null&&temp.getBidPq()!=null){
				temp.setTotalPq(temp.getLcPq().add(temp.getBidPq()));
			}
			if(temp.getActualPq()!=null&&temp.getProxyPq()!=null){
//				temp.setDeliveryPq(String.valueOf(temp.getActualPq().subtract(temp.getProxyPq())));
				if(!temp.getProxyPq().equals(BigDecimal.ZERO)){
					temp.setDevPqProp(String.valueOf((temp.getActualPq().subtract(temp.getProxyPq()).divide(temp.getProxyPq(), 2,BigDecimal.ROUND_HALF_DOWN))));
				}
			}
		}*/
		try {
			ConvertListUtil.convert(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @Title: getSchemeDetailListByYm
	 * @Description: 根据ym获取当月的所有合同用户信息（方案详情信息）
	 * @param ym	yyyyMM格式或者yyyy-MM格式
	 * @return 
	 * List<SmPurchaseSchemeDetailDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午5:21:03
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午5:21:03
	 * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SmPurchaseSchemeDetailDetail> getSchemeDetailListByYm(String ym) {
		if(ym == null || "".equals(ym)) {
			return null;
		}
		ym = ym.replaceAll("-", "");
		String userId = SystemServiceUtil.getLoginUserInfo().getId();
		Map<String , String> params = new HashMap<>();
		params.put("ym", ym);
		params.put("userId", userId);
		//Parameter.isFilterData.set(true);
		List<SmPurchaseSchemeDetailDetail> list=(List<SmPurchaseSchemeDetailDetail>) dao.getBySql("smPurchaseSchemeDetail.sql.getSchemeDetailListByYm", params);
		//Parameter.isFilterData.set(false);
		//这里去计算用户的服务费用  ，  计算分成基准值（元/兆瓦时）
		for (SmPurchaseSchemeDetailDetail detail : list) {
			//以下是去计算用户的服务费用
			BigDecimal serviceAmt = null;
			//如果双边的分成方式为：06：代理服务费    并且 双边的收益模式为：04一次性收取
			if("06".equals(detail.getDiviCode()) && "04".equals(detail.getProfitMode())) {		
				serviceAmt = detail.getAgent();
			}
			//如果竞价的分成方式为：06：代理服务费    并且 竞价的收益模式为：04一次性收取
			if("06".equals(detail.getBidDiviCode()) && "04".equals(detail.getBidProfitMode())) {		
				if(serviceAmt == null) {
					serviceAmt = detail.getBidAgent();
				}else if(detail.getBidAgent() != null){
					serviceAmt = detail.getBidAgent().add(serviceAmt);
				}
			}
			
			//以下是去计算 分成基准值（元/兆瓦时）
			
			BigDecimal marketDiviValue = null;
			
			//双边：   03:分成;  04:保底或分成;   05:保底加分成
			if("03".equals(detail.getDiviCode()) || "04".equals(detail.getDiviCode()) || "05".equals(detail.getDiviCode())) {
				//目录-(输配+代征)
				if("01".equals(detail.getDiviType())) {
					marketDiviValue = getMarketDiviValue(detail.getConsId(), ym.substring(ym.length()-2));
					detail.setDiviValue(marketDiviValue);
				}else if("03".equals(detail.getDiviType())){
					//人工录入
				}
			}else {
				detail.setDiviValue(null);
			}
			
			//竞价：   03:分成;  04:保底或分成;   05:保底加分成
			if("03".equals(detail.getBidDiviCode()) || "04".equals(detail.getBidDiviCode()) || "05".equals(detail.getBidDiviCode())) {
				//目录-(输配+代征)
				if("01".equals(detail.getBidDiviType())) {
					if(marketDiviValue == null) {
						marketDiviValue = getMarketDiviValue(detail.getConsId(), ym.substring(ym.length()-2));
					}
					detail.setBidDiviValue(marketDiviValue);
				}else if("03".equals(detail.getBidDiviType())){
					//人工录入
				}
			}else {
				detail.setBidDiviValue(null);
			}
		}
		try {
			ConvertListUtil.convert(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private BigDecimal getMarketDiviValue(String consId, String month) {
		//市场 分成基准值：= 目录-（输配+代征） = 电度电价-输配电价-政府性基金和代征；
		//首先获取用户电压等级的电价去计算，如果没有，则获取10kV的平时电价的值来计算
		//获取用户信息，用于获取用户地区的电价信息
		ScConsumerInfo cons = dao.findById(consId, ScConsumerInfo.class);
		String voltCode = cons.getVoltCode();
		for (int i = 0; i < 2; i++) {
			//第一次是获取当前用户所在地区的电价信息， 第二次是获取指定地区的10kV电价信息
			List<SmPrcInfo> prcInfoList = smPrcInfoService.getSmPrcInfoListByParams(cons.getProvinceCode(), cons.getCityCode(), cons.getCountyCode() , 
					month, voltCode);
			if(prcInfoList != null && prcInfoList.size() > 0) {
				SmPrcInfo info = prcInfoList.get(0);
				if(info.getCataPlainPrc() == null ) {
					voltCode = "AC00102";		//10kV  
					continue;
				}else {
					return info.getCataPlainPrc().subtract(info.getTransPrc() == null ? BigDecimal.ZERO : info.getTransPrc());
				}
			}
		}
		return null;
	}
}