package com.hhwy.purchaseweb.settlement.jiangsu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchase.domain.SmCompanyCostDetail;
import com.hhwy.purchase.domain.SmCompanyProfit;
import com.hhwy.purchase.domain.SmPrcInfo;
import com.hhwy.purchase.domain.SmSettlementMonth;
import com.hhwy.purchaseweb.arithmetic.balance.service.BalanceService;
import com.hhwy.purchaseweb.arithmetic.divide.service.PrcService;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureDetail;
import com.hhwy.purchaseweb.delivery.smprcinfo.service.SmPrcInfoService;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.domain.SmConsumerProfitDetail;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.service.SmPurchaseSchemeService;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.domain.SmPurchaseSchemeDetailDetail;
import com.hhwy.purchaseweb.settlement.jiangsu.service.SettlementServiceJs;
import com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.domain.SmPurchaseSchemeVoJs;
import com.hhwy.selling.domain.ScConsumerInfo;

 /**
 * <b>类 名 称：</b>SmPurchaseSchemeDetailServiceImplJs<br/>
 * <b>类 描 述：</b><br/>		江苏的结算方案详情的service实现类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年3月27日 下午9:55:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class SettlementServiceImplJs implements SettlementServiceJs {

	public static final Logger log = LoggerFactory.getLogger(SettlementServiceImplJs.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 算法计算 BalanceService
	 */
	@Autowired  @Qualifier("DValueBalanceService") 
	private BalanceService balanceService;
	
	/**
	 * 合同辅助设计ssAgreSchemeService,  计算 售电公司赔偿用户费用 和 用户考核电费
	 */
//	@Autowired
//	private SsAgreSchemeService ssAgreSchemeService;
	
	
	/**
	 * 合同辅助设计ssAgreSchemeService
	 */
	@Autowired
	@Qualifier("JSPrcService")
	private PrcService jSPrcService;
	
	/**
	 * smPurchaseSchemeService	月度结算方案service
	 */
	@Autowired
	private SmPurchaseSchemeService smPurchaseSchemeService;
	
	/**
	 * smPrcInfoService		电价配置信息service
	 */
	@Autowired
	private SmPrcInfoService smPrcInfoService;
	
	
	/**
	 * @Title: calculateDeliveryPrc
	 * @Description: 计算单个用户的 结算电价	及  服务费用
	 * @param detail
	 * @return 
	 * BigDecimal
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月27日 下午10:06:07
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午10:22:12
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public BigDecimal calculateDeliveryPrc(SmPurchaseSchemeDetailDetail detail) throws Exception {
		if(detail.getYm() == null ||"".equals(detail.getYm())) {
			return null;
		}
		
		//计算服务费  = 长协服务费+竞价服务费
		BigDecimal serviceAmt = BigDecimal.ZERO;
		//如果双边的分成方式为：06：代理服务费 
		if("06".equals(detail.getDiviCode()) && detail.getAgent() != null) {	
			//双边的收益模式为：04一次性收取
			if("04".equals(detail.getProfitMode())) {
				serviceAmt = detail.getAgent();
			}else if("03".equals(detail.getProfitMode())){		//双边的收益模式为：03 按电量收取
				serviceAmt = detail.getAgent().multiply(detail.getLcPq());
			}
		}
		//如果竞价的分成方式为：06：代理服务费 
		if("06".equals(detail.getBidDiviCode()) && detail.getBidAgent() != null) {	
			//竞价的收益模式为：04一次性收取
			if("04".equals(detail.getBidProfitMode())) {
				serviceAmt = detail.getBidAgent().add(serviceAmt);
			}else if("03".equals(detail.getBidProfitMode())){	//竞价的收益模式为：03 按电量收取
				BigDecimal bidAddListedPq = BigDecimal.ZERO;
				if(detail.getBidPq() != null) {
					bidAddListedPq = detail.getBidPq();
				}
				if(detail.getListedPq() != null) {
					bidAddListedPq = bidAddListedPq.add(detail.getListedPq());
				}
				serviceAmt = detail.getBidAgent().multiply(bidAddListedPq).add(serviceAmt);
			}
		}
		detail.setServiceAmt(serviceAmt);
		
		
		//--------------------完成计算服务费，以下是计算加权平均价------------------------------
		
		Map<String, String> params = new HashMap<>();
		params.put("consId", detail.getConsId());
		params.put("ym", detail.getYm());
		//smDistMode.profitMode
		Parameter.isFilterData.set(true);
		String profitMode = (String)dao.getOneBySQL("settlement.js.sql.getSellPpaByConsIdAndYm", params);
		Parameter.isFilterData.set(false);
		if(profitMode == null || "".equals(profitMode)) {	//若合同中没有“收益模式”（PROFIT_FIRST_CODE01 = "01"：甲方收益最大，PROFIT_FIRST_CODE02 = "02"：乙方收益最大）
			profitMode = BusinessContants.PROFIT_FIRST_CODE01;	//默认甲方收益最大
		}
		
		//根据年月获取 双边、竞价、挂牌三个加权平均价
		//		 * @param ym	yyyyMM或yyyy-MM格式
		//		 * @return	bidPrcAvg:	 	竞价加权平均价
		//		 *			lcPrcAvg： 		双边加权平均价
		//		 *			listedPrcAvg：	挂牌加权平均价
		Map<String, BigDecimal> map =  smPurchaseSchemeService.getLcBidListedAvgPrcByYm(detail.getYm());
		
		if(map == null) {
			throw new BusinessException("获取成交信息失败，无法计算成交均价！");
		}
		
		//竞价加权平均价
		BigDecimal bidPrcAvg = map.get("bidPrcAvg");
		//双边加权平均价
		BigDecimal lcPrcAvg = map.get("lcPrcAvg");
		//挂牌加权平均价
		BigDecimal listedPrcAvg = map.get("listedPrcAvg");
		
		
		//市场 分成基准值：= 电度电价-输配电价-政府性基金和代征；
		//首先获取用户电压等级的电价去计算，如果没有，则获取10kV的平时电价的值来计算
		BigDecimal diviValue = null;
		//获取用户信息，用于获取用户地区的电价信息
		ScConsumerInfo cons = dao.findById(detail.getConsId(), ScConsumerInfo.class);
		String voltCode = cons.getVoltCode();
		for (int i = 0; i < 2; i++) {
			//第一次是获取当前用户所在地区的电价信息， 第二次是获取指定地区的10kV电价信息
			List<SmPrcInfo> prcInfoList = smPrcInfoService.getSmPrcInfoListByParams(cons.getProvinceCode(), cons.getCityCode(), cons.getCountyCode() , 
					detail.getYm().substring(detail.getYm().length()-2), voltCode);
			if(prcInfoList != null && prcInfoList.size() > 0) {
				SmPrcInfo info = prcInfoList.get(0);
				if(info.getCataPlainPrc() == null ) {
					voltCode = "AC00102";		//10kV  
					continue;
				}else {
					diviValue = info.getCataPlainPrc().subtract(info.getTransPrc() == null ? BigDecimal.ZERO : info.getTransPrc());
					break;
				}
			}
		}
		
		//获取交易规则配置，主要是获取  燃煤机组标杆电价
		/*SmCalcConfigureDetail smCalcConfigureDetail = balanceService.getSmCalcConfigureDetail();
		BigDecimal modelPrc = BigDecimal.ZERO;	//燃煤标杆电价
		if(smCalcConfigureDetail != null){
			String json = smCalcConfigureDetail.getCalcParam();
			if(json != null && !json.equals("")){
				Map<String, Object> calcConfigureJsonMap = JSON.parseObject(json);
				if(calcConfigureJsonMap.get("modelPrc") != null) {
					modelPrc = new BigDecimal(calcConfigureJsonMap.get("modelPrc").toString());
				}
			}
		}*/
		
		//实际用的分成基准值，（有 1.市场分成基准值，2.用户手填基准值  两种取值可能（ 3.标杆电价(去除) ））
		BigDecimal actualDiviPrc = null;
		
		
		//双边结算电价
		BigDecimal deliverylcPrc = BigDecimal.ZERO;
		if(detail.getLcPq() != null) {
			//如果双边的分成方式为：06：代理服务费 ,则双边的结算电价 = 双边加权平均价
			if("06".equals(detail.getDiviCode())) {
				deliverylcPrc = lcPrcAvg;
			}else if("03".equals(detail.getDiviCode()) || "04".equals(detail.getDiviCode())){		
				//如果双边的分成方式为：03：分成  或者  04：保底或分成  ,则 应该以标杆电价 当作 分成基准值 去加权结算电价
				actualDiviPrc = detail.getDiviValue();
				if(actualDiviPrc == null && diviValue != null) {
					actualDiviPrc = diviValue;
					detail.setDiviValue(actualDiviPrc);
				}
				if(actualDiviPrc == null) {
					throw new BusinessException("分成基准值获取失败！");
				}
				deliverylcPrc = jSPrcService.getPricePrc(detail.getDiviCode(), diviValue, actualDiviPrc, 
						detail.getAgrePrc(), detail.getPartyALcProp(), lcPrcAvg, profitMode);
			}else {		//'01'： '保底'；	'05'： '固定保底加分成'；  '07'： '联动保底加分成',
							//则 应该以 保底协议价 当作 分成基准值 去加权结算电价
				deliverylcPrc = jSPrcService.getPricePrc(detail.getDiviCode(), detail.getAgrePrc(), 
						detail.getAgrePrc(), detail.getPartyALcProp(), lcPrcAvg, profitMode);
			}
		}
		
		//竞价结算电价
		BigDecimal deliveryBidPrc = BigDecimal.ZERO;
		//挂牌结算电价
		BigDecimal deliveryListedPrc = BigDecimal.ZERO;
		//如果竞价的分成方式为：06：代理服务费  ,则竞价的结算电价 = 竞价加权平均价 , 挂牌的结算电价 = 挂牌加权平均价
		if("06".equals(detail.getBidDiviCode())) {
			if(detail.getBidPq() != null) {
				deliveryBidPrc = bidPrcAvg;
			}
			if(detail.getListedPq() != null) {
				deliveryListedPrc = listedPrcAvg;
			}
		}else if("03".equals(detail.getBidDiviCode()) || "04".equals(detail.getBidDiviCode())){		
			//如果竞价的分成方式为：03：分成  或者  04：保底或分成  ,则 应该以分成基准值 当作标杆电价去加权结算电价
			actualDiviPrc = detail.getBidDiviValue();
			if(actualDiviPrc == null && diviValue != null) {
				actualDiviPrc = diviValue;
				detail.setBidDiviValue(actualDiviPrc);
			}
			if(actualDiviPrc == null) {
				throw new BusinessException("分成基准值获取失败！");
			}
			if(detail.getBidPq() != null && detail.getBidPq().compareTo(BigDecimal.ZERO) > 0) {
				deliveryBidPrc = jSPrcService.getPricePrc(detail.getBidDiviCode(),diviValue, actualDiviPrc, 
						detail.getBidAgrePrc(), detail.getPartyABidProp(), bidPrcAvg, profitMode);
			}
			if(detail.getListedPq() != null && detail.getListedPq().compareTo(BigDecimal.ZERO) > 0) {
				deliveryListedPrc = jSPrcService.getPricePrc(detail.getBidDiviCode(),diviValue, actualDiviPrc, 
						detail.getBidAgrePrc(), detail.getPartyABidProp(), listedPrcAvg, profitMode);
			}
		}else {
			if(detail.getBidPq() != null && detail.getBidPq().compareTo(BigDecimal.ZERO) > 0) {
				deliveryBidPrc = jSPrcService.getPricePrc(detail.getBidDiviCode(), detail.getBidAgrePrc(), 
						detail.getBidAgrePrc(), detail.getPartyABidProp(), bidPrcAvg, profitMode);
			}
			if(detail.getListedPq() != null && detail.getListedPq().compareTo(BigDecimal.ZERO) > 0) {
				deliveryListedPrc = jSPrcService.getPricePrc(detail.getBidDiviCode(), detail.getBidAgrePrc(), 
						detail.getBidAgrePrc(), detail.getPartyABidProp(), listedPrcAvg, profitMode);
			}
		}		
			
		//这里计算双边、竞价、挂牌的加权平均价。。。。
		BigDecimal result = (detail.getBidPq() != null ? deliveryBidPrc.multiply(detail.getBidPq()) : BigDecimal.ZERO)
				.add((detail.getLcPq() != null ? deliverylcPrc.multiply(detail.getLcPq()) : BigDecimal.ZERO))
				.add(detail.getListedPq()!=null ? deliveryListedPrc.multiply(detail.getListedPq()) : BigDecimal.ZERO)
				.divide(
						(detail.getBidPq() != null ? detail.getBidPq() : BigDecimal.ZERO)
						.add(detail.getLcPq() != null ? detail.getLcPq() : BigDecimal.ZERO)
						.add(detail.getListedPq()!=null ? detail.getListedPq() : BigDecimal.ZERO) , 1, BigDecimal.ROUND_HALF_UP);
		detail.setDeliveryPrc(result);
		return result;
	}
	/**
	 * @Title: caculateProfit
	 * @Description: 计算用户结算数据和售电公司结算数据
	 * @param smPurchaseSchemeVoJs
	 * @return
	 * @throws Exception 
	 * SettlementVo
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月28日 下午3:09:21
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月28日 下午3:09:21
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public SmPurchaseSchemeVoJs caculateProfit(SmPurchaseSchemeVoJs smPurchaseSchemeVoJs) throws Exception{
		//首先判断参数是否完整
		List<SmConsumerProfitDetail> smConsumerProfitDetailList = smPurchaseSchemeVoJs.getSmConsumerProfitDetailList();
		if(smConsumerProfitDetailList == null || smConsumerProfitDetailList.size() == 0) {
			throw new BusinessException("结算中没有用户结算数据，结算失败！");
		}
		String ym = smPurchaseSchemeVoJs.getYm();		//年月 yyyy-MM格式
		if(ym == null || ym.length() == 0 ) {
			throw new BusinessException("结算中年月为空，结算失败！");
		}
		ym = ym.replaceAll("-", ""); 
		//获取交易规则配置，主要是获取  燃煤机组标杆电价
		SmCalcConfigureDetail smCalcConfigureDetail = balanceService.getSmCalcConfigureDetail();
		BigDecimal modelPrc = null;	//燃煤标杆电价（商业）
		Map<String, Object> calcConfigureJsonMap = null;
		if(smCalcConfigureDetail != null){
			String json = smCalcConfigureDetail.getCalcParam();
			if(json != null && !json.equals("")){
				calcConfigureJsonMap = JSON.parseObject(json);
				if(calcConfigureJsonMap.get("modelPrc") != null) {
					modelPrc = new BigDecimal(calcConfigureJsonMap.get("modelPrc").toString());
				}else {
					throw new BusinessException("结算省份的“燃煤机组标杆电价”为空，结算失败！");
				}
			}
		}
		
		/*//获取所有用户的合同中的 用户违约惩罚方式 和  售电公司违约惩罚方式
		 * 因用户的偏差考核费用在别处计算，并且江苏的售电公司赔偿用户金额为0 ，所以注释本段代码，但是对别的省的结算有参考意义
		List<String> consIds = new ArrayList<>();
		for (SmConsumerProfitDetail profitDetail : smConsumerProfitDetailList) {
			consIds.add(profitDetail.getConsId());
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("consIds", consIds);
		params.put("ym", ym);
		Parameter.isFilterData.set(true);
		@SuppressWarnings("unchecked")
		List<SellPpaPunishInfo> sellPpaPunishInfoList = (List<SellPpaPunishInfo>) dao.getBySql("settlement.js.sql.getSellPpaPunishInfo", params);
		Parameter.isFilterData.set(false);
		if(sellPpaPunishInfoList == null || sellPpaPunishInfoList.isEmpty()){
			throw new BusinessException("结算中某些用户售电合同信息错误，结算失败！");
		}
		//用户id-->此用户的用户违约惩罚方式 和  售电公司违约惩罚方式  的map
		Map<String,SellPpaPunishInfo> sellPpaPunishInfoMap = new HashMap<String, SellPpaPunishInfo>();
		for (SellPpaPunishInfo sellPpaPunishInfo : sellPpaPunishInfoList) {
			sellPpaPunishInfoMap.put(sellPpaPunishInfo.getConsId(), sellPpaPunishInfo);
		}
		//竞价加权平均价
		//BigDecimal bidPrcAvg = phmDealInfoService.getBidAvgPrcByPlanId(settleId);*/
		
		//循环所有用户，即此结算方案的所有详情 ,   计算用户相关的结算数据， 得到前台的“零售市场售电收入明细”表所有数据
		for (SmConsumerProfitDetail profitDetail : smConsumerProfitDetailList) {
			caculateConsDelivryInfo(modelPrc, profitDetail);
		}
		
		//以下是计算“批发市场购电支出明细”表中的数据
		Parameter.isFilterData.set(true);
		//获取所有当月的购电成交分段中  年度双边交易 的电量电价信息
		List<SmCompanyCostDetail> lcYearSmCompanyCostDetailList = (List<SmCompanyCostDetail>)dao.getBySql("smCompanyCostDetail.sql.getLcYearDealInfoListByYm",ym);
		//获取所有当月的购电成交分段中  月度双边交易 的电量电价信息
		List<SmCompanyCostDetail> lcMonthSmCompanyCostDetailList = (List<SmCompanyCostDetail>)dao.getBySql("smCompanyCostDetail.sql.getLcMonthDealInfoListByYm",ym);
		//获取所有当月的购电成交分段中 年度竞价交易 的电量电价信息
		List<SmCompanyCostDetail> bidYearSmCompanyCostDetailList = (List<SmCompanyCostDetail>)dao.getBySql("smCompanyCostDetail.sql.getBidYearDealInfoListByYm",ym);
		//获取所有当月的购电成交分段中 月度竞价交易 的电量电价信息
		List<SmCompanyCostDetail> bidMonthSmCompanyCostDetailList = (List<SmCompanyCostDetail>)dao.getBySql("smCompanyCostDetail.sql.getBidMonthDealInfoListByYm",ym);
		//获取所有当月的购电成交分段中 年度挂牌交易 的电量电价信息
		List<SmCompanyCostDetail> listedYearSmCompanyCostDetailList = (List<SmCompanyCostDetail>)dao.getBySql("smCompanyCostDetail.sql.getListedYearDealInfoListByYm",ym);
		//获取所有当月的购电成交分段中 月度挂牌交易 的电量电价信息
		List<SmCompanyCostDetail> listedMonthSmCompanyCostDetailList = (List<SmCompanyCostDetail>)dao.getBySql("smCompanyCostDetail.sql.getListedMonthDealInfoListByYm",ym);
		Parameter.isFilterData.set(false);
		
		//所有“批发市场购电支出明细”, 结算顺序为  月度挂牌>月度竞价>月度双边>年度竞价>年度挂牌>年度双边（月度双边>年度竞价>年度挂牌  江苏省暂时没有）
		List<SmCompanyCostDetail> smCompanyCostDetailListTotal = new ArrayList<SmCompanyCostDetail>();
		smCompanyCostDetailListTotal.addAll(listedMonthSmCompanyCostDetailList);
		smCompanyCostDetailListTotal.addAll(bidMonthSmCompanyCostDetailList);
		smCompanyCostDetailListTotal.addAll(lcMonthSmCompanyCostDetailList);
		smCompanyCostDetailListTotal.addAll(bidYearSmCompanyCostDetailList);
		smCompanyCostDetailListTotal.addAll(listedYearSmCompanyCostDetailList);
		smCompanyCostDetailListTotal.addAll(lcYearSmCompanyCostDetailList);
		
		smPurchaseSchemeVoJs.setSmCompanyCostDetailList(smCompanyCostDetailListTotal);
		
		//售电公司的  总购电量   计算
		BigDecimal purchasePqTotal = BigDecimal.ZERO;
		for (int i = 0; i < smCompanyCostDetailListTotal.size(); i++) {
			SmCompanyCostDetail smCompanyCostDetail = smCompanyCostDetailListTotal.get(i);
			smCompanyCostDetail.setSort(i); 		//对顺序号赋值
			if(smCompanyCostDetail.getMonthPq() != null) {
				purchasePqTotal = purchasePqTotal.add(smCompanyCostDetail.getMonthPq());
			}
		}
		
		//实际用电量（所有用户实际用电量加和）
		BigDecimal actualPqTotal = BigDecimal.ZERO;
		for (SmConsumerProfitDetail profitDetail : smConsumerProfitDetailList) {
			if(profitDetail.getConsDelPq() != null) {
				actualPqTotal = actualPqTotal.add(profitDetail.getConsDelPq());
			}
		}
		
		//代理总电量（所有用户代理（别名：申报、计划）电量加和）
		BigDecimal proxyPqTotal = BigDecimal.ZERO;
		//长协分配总量（所有用户长协电量加和）
	    BigDecimal lcTotalPq = BigDecimal.ZERO;
	    //竞价中标总量（所有用户竞价电量加和）
	    BigDecimal bidTotalPq = BigDecimal.ZERO;
	    //挂牌分配总量（所有用户挂牌电量加和）
	    BigDecimal listedTotalPq = BigDecimal.ZERO;
		for (SmConsumerProfitDetail profitDetail : smConsumerProfitDetailList) {
			if(profitDetail.getProxyPq() != null) {
				proxyPqTotal = proxyPqTotal.add(profitDetail.getProxyPq());
			}
			if(profitDetail.getLcDistPq() != null) {
				lcTotalPq = lcTotalPq.add(profitDetail.getLcDistPq());
			}
			if(profitDetail.getBidDistPq() != null) {
				bidTotalPq = bidTotalPq.add(profitDetail.getBidDistPq());
			}
			if(profitDetail.getListedPq() != null) {
				listedTotalPq = listedTotalPq.add(profitDetail.getListedPq());
			}
		}
		SmCompanyProfit companyProfit = smPurchaseSchemeVoJs.getSmCompanyProfit();
		companyProfit.setYm(ym);
		companyProfit.setProxyPq(proxyPqTotal);
		companyProfit.setLcTotalPq(lcTotalPq);
		companyProfit.setBidTotalPq(bidTotalPq);
		companyProfit.setListedTotalPq(listedTotalPq);
		companyProfit.setPurTotalPq(purchasePqTotal); 	//售电公司的  总购电量
		companyProfit.setDelTotalPq(actualPqTotal); 	//总交割电量，总实际用电量
		//计算“批发市场购电支出明细”表中的数,
		caculateSmCompanyCostDetail(smPurchaseSchemeVoJs.getSettleId(), companyProfit, calcConfigureJsonMap, smCompanyCostDetailListTotal);
		
		//计算售电公司偏差费用
		caculateCompanyDevAmt(companyProfit, calcConfigureJsonMap);
		
		//批发市场购电支出=∑售电公司结算电费
		BigDecimal companyCostTotal = BigDecimal.ZERO;
		for (SmCompanyCostDetail smCompanyCostDetail : smCompanyCostDetailListTotal) {
			companyCostTotal = companyCostTotal.add(smCompanyCostDetail.getDeliveryAmt());
		}
		//零售市场购电收入=∑用户结算电费
		BigDecimal consAmtTotal = BigDecimal.ZERO;
		//赔偿用户总费用，    江苏没有赔偿用户费用，暂时先注释掉此代码
//		BigDecimal consCompensateTotal = BigDecimal.ZERO;
		//用户总偏差考核费用（元）
		BigDecimal consCheckedProTotal = BigDecimal.ZERO;
		for (SmConsumerProfitDetail profitDetail : smConsumerProfitDetailList) {
			consAmtTotal = consAmtTotal.add(profitDetail.getDeliveryCost());
//			consCompensateTotal = consCompensateTotal.add(profitDetail.getConsCompensate());
			consCheckedProTotal = consCheckedProTotal.add(profitDetail.getConsCheckedPro());
		}
		companyProfit.setConsCheckedProTotal(consCheckedProTotal);	//用户总偏差考核费用（元）
//		companyProfit.setPayConsMoney(consCompensateTotal);	//赔偿用户总金额
		companyProfit.setCompanyCostTotal(companyCostTotal);	//批发市场购电总支出
		companyProfit.setConsAmtTotal(consAmtTotal);	//零售市场购电总收入
		//售电公司总利润=零售市场购电收入+用户偏差考核-批发市场购电支出-赔偿费用（江苏没有赔偿用户费用）-售电公司偏差考核费用
		BigDecimal companyPro = consAmtTotal.add(consCheckedProTotal).subtract(companyCostTotal)
					.subtract(companyProfit.getDevDam());		//.subtract(consCompensateTotal)
		companyProfit.setCompanyPro(companyPro);	//售电公司总利润
		return smPurchaseSchemeVoJs;
	}
	/**
	 * @Title: caculateSmCompanyCostDetail
	 * @Description: 根据所在省份的交易规则去计算每个“批发市场购电支出明细”的结算电量(保留3位小数)及结算电费(保留2位小数)
	 * @param settleId 结算id
	 * @param companyProfit 中  purTotalP(售电公司总购电量)    delTotalPq(用户总实际用电量)
	 * @param calcConfigureJsonMap  其中的参数有：
	 * 				modelPrc		燃煤标杆电价
			 		prcProp			正偏差阈值(大值)
			 		upperThreshold		正偏差阈值（小值）
			 		lowerThreshold		负偏差阈值（原值）
	 * @param smCompanyCostDetailListTotal		批发市场购电支出明细 列表
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月29日 下午6:53:25
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月29日 下午6:53:25
	 * @since  1.0.0
	 */
	private void caculateSmCompanyCostDetail(String settleId, SmCompanyProfit companyProfit, Map<String, Object> calcConfigureJsonMap,List<SmCompanyCostDetail> smCompanyCostDetailListTotal) throws Exception{
		BigDecimal purchasePqTotal = companyProfit.getPurTotalPq();
		BigDecimal actualPqTotal = companyProfit.getDelTotalPq();
		//验证参数是否合法
		if(calcConfigureJsonMap == null) {
			throw new BusinessException("结算省份的规则为空，结算失败！");
		}
		if(smCompanyCostDetailListTotal == null || smCompanyCostDetailListTotal.size() == 0 
				|| purchasePqTotal == null || actualPqTotal == null 
				|| purchasePqTotal.compareTo(BigDecimal.ZERO) == 0 || actualPqTotal.compareTo(BigDecimal.ZERO) == 0) {
			return;
		}
		BigDecimal modelPrc = null;	//燃煤标杆电价（商业）
		BigDecimal industryModelPrc = null;	//燃煤标杆电价（工业）
//		BigDecimal prcProp = null;	//正偏差阈值(大值)
		BigDecimal upperThreshold = null;	//正偏差阈值（小值）
//		BigDecimal lowerThreshold = null;	//负偏差阈值（原值）
		if(calcConfigureJsonMap.get("modelPrc") != null) {
			modelPrc = new BigDecimal(calcConfigureJsonMap.get("modelPrc").toString());
		}else {
			throw new BusinessException("结算省份的“燃煤机组标杆电价”为空，结算失败！");
		}
		if(calcConfigureJsonMap.get("industryModelPrc") != null) {
			industryModelPrc = new BigDecimal(calcConfigureJsonMap.get("industryModelPrc").toString());
		}else {
			industryModelPrc = new BigDecimal(395.2);
		}
		if(calcConfigureJsonMap.get("prcProp") != null) {
//			prcProp = new BigDecimal(calcConfigureJsonMap.get("prcProp").toString());
		}else {
			throw new BusinessException("结算省份的“正偏差阈值(大值)”为空，结算失败！");
		}
		if(calcConfigureJsonMap.get("upperThreshold") != null) {
			upperThreshold = new BigDecimal(calcConfigureJsonMap.get("upperThreshold").toString());
		}else {
			throw new BusinessException("结算省份的“正偏差阈值（小值）”为空，结算失败！");
		}
		if(calcConfigureJsonMap.get("lowerThreshold") != null) {
//			lowerThreshold = new BigDecimal(calcConfigureJsonMap.get("lowerThreshold").toString());
		}else {
			throw new BusinessException("结算省份的“负偏差阈值（原值）”为空，结算失败！");
		}
		
		//结算主表信息
		SmSettlementMonth settlementMonth = null;
		//工业电量(江苏专用,在实际用电量录入时存入)
	    BigDecimal industryPq = null;
	    
	    //商业电量(江苏专用,在实际用电量录入时存入)
	    BigDecimal businessPq = null;
	    if(settleId != null) {
	    	settlementMonth = dao.findById(settleId, SmSettlementMonth.class);
	    	if(settlementMonth != null) {
	    		industryPq = settlementMonth.getIndustryPq();
	    	    businessPq = settlementMonth.getBusinessPq();
	    	}
	    }
		
		//正偏差阈值（小值） 的偏差率 = （正偏差阈值（小值）-100	例：3
		BigDecimal upperThresholdProp = upperThreshold.subtract(new BigDecimal(100));
		//总偏差电量
		companyProfit.setDevPq(actualPqTotal.subtract(purchasePqTotal));
		//总偏差率=(实际电量-总购电量)/总购电量 (保留6位小数) - 1	例：0.050012
		BigDecimal deviationPropTotal = actualPqTotal.subtract(purchasePqTotal).divide(purchasePqTotal,6,RoundingMode.HALF_UP);
		companyProfit.setDevPqProp(deviationPropTotal.multiply(new BigDecimal(100)));
		//分配剩余的实际用电量
		BigDecimal actualPq = actualPqTotal;
		//实际电量 > 总购电量
		if(deviationPropTotal.compareTo(BigDecimal.ZERO) == 1) {
			//如果 总偏差率 <= 正偏差阈值（小值）
			if(deviationPropTotal.multiply(new BigDecimal(100)).compareTo(upperThresholdProp) != 1) {
				for (int i = 0; i < smCompanyCostDetailListTotal.size(); i ++ ) {
					SmCompanyCostDetail smCompanyCostDetail = smCompanyCostDetailListTotal.get(i);
					//如果是最后一条记录
					if(i == smCompanyCostDetailListTotal.size() - 1) {
						//本合同结算电量 = 分配剩余的实际用电量
						smCompanyCostDetail.setDeliveryPq(actualPq.setScale(3, BigDecimal.ROUND_HALF_UP));
					}else {
						//本合同结算电量 = 本合同电量 * (1+总偏差率)
						smCompanyCostDetail.setDeliveryPq(smCompanyCostDetail.getMonthPq().multiply(BigDecimal.ONE.add(deviationPropTotal)).setScale(3, BigDecimal.ROUND_HALF_UP));
						//更新剩余分配电量
						actualPq = actualPq.subtract(smCompanyCostDetail.getDeliveryPq());
					}
					//本合同的结算电费 = 本合同电价 × 本合同结算电量
					smCompanyCostDetail.setDeliveryAmt(smCompanyCostDetail.getDeliveryPq().multiply(smCompanyCostDetail.getMonthPrc()).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}else {  //如果 总偏差率 > 正偏差阈值（小值）
				//计算各合同的 结算电量 及 结算电费
				for (int i = 0; i < smCompanyCostDetailListTotal.size(); i ++ ) {
					SmCompanyCostDetail smCompanyCostDetail = smCompanyCostDetailListTotal.get(i);
					//本月按 本合同电价 结算的电量(3位小数)，即（本合分月电量×（1+ 正偏差阈值（小值）/100））
					BigDecimal upperThresholdPq = smCompanyCostDetail.getMonthPq().multiply(BigDecimal.ONE.add(upperThresholdProp.divide(new BigDecimal(100)))).setScale(3, BigDecimal.ROUND_HALF_UP);
					//本合同结算电量 = 本月按 本合同电价 结算的电量
					smCompanyCostDetail.setDeliveryPq(upperThresholdPq);
					//更新剩余分配电量
					actualPq = actualPq.subtract(smCompanyCostDetail.getDeliveryPq());
					//本合同的结算电费 = 本合同电价 × 本合同结算电量 
					smCompanyCostDetail.setDeliveryAmt(upperThresholdPq.multiply(smCompanyCostDetail.getMonthPrc()));
				}
				//若工业或商业电量为null，或者商业电量为0，则所有超出都按“工业标杆电价”计算
				if(industryPq == null || businessPq == null || businessPq.compareTo(BigDecimal.ZERO) == 0) {
					//按工业标杆电价结算的支出明细 （批发市场购电支出明细最后一条）
					SmCompanyCostDetail modelPrcRow = new SmCompanyCostDetail();
					modelPrcRow.setAgreName("按标杆电价结算(工业)");
					modelPrcRow.setMonthPq(null);
					modelPrcRow.setMonthPrc(industryModelPrc);
					modelPrcRow.setSort(smCompanyCostDetailListTotal.size());
					//按标杆电价结算的结算电量 = 分配剩余的实际用电量
					modelPrcRow.setDeliveryPq(actualPq);
					//按标杆电价结算的结算电费 = 标杆电价 × 按标杆电价结算的结算电量
					modelPrcRow.setDeliveryAmt(actualPq.multiply(industryModelPrc).setScale(2, BigDecimal.ROUND_HALF_UP));
					smCompanyCostDetailListTotal.add(modelPrcRow);
				}else if( industryPq.compareTo(BigDecimal.ZERO) == 0){	//如果工业电量为0，则所有超出都按“商业标杆电价”计算
					//按商业标杆电价结算的支出明细 （批发市场购电支出明细最后一条）
					SmCompanyCostDetail modelPrcRow = new SmCompanyCostDetail();
					modelPrcRow.setAgreName("按标杆电价结算(商业)");
					modelPrcRow.setMonthPq(null);
					modelPrcRow.setMonthPrc(modelPrc);
					modelPrcRow.setSort(smCompanyCostDetailListTotal.size());
					//按标杆电价结算的结算电量 = 分配剩余的实际用电量
					modelPrcRow.setDeliveryPq(actualPq);
					//按标杆电价结算的结算电费 = 标杆电价 × 按标杆电价结算的结算电量
					modelPrcRow.setDeliveryAmt(actualPq.multiply(modelPrc).setScale(2, BigDecimal.ROUND_HALF_UP));
					smCompanyCostDetailListTotal.add(modelPrcRow);
				}else {		//如果两者都不为0，则按比例分成两部分结算
					//按商业标杆电价结算的支出明细 （批发市场购电支出明细最后一条）
					SmCompanyCostDetail modelPrcRow = new SmCompanyCostDetail();
					modelPrcRow.setAgreName("按标杆电价结算(商业)");
					modelPrcRow.setMonthPq(null);
					modelPrcRow.setMonthPrc(modelPrc);
					modelPrcRow.setSort(smCompanyCostDetailListTotal.size());
					//按商业标杆电价结算的结算电量 = 分配剩余的实际用电量 × businessPq /（industryPq + businessPq）
					BigDecimal businessDeliveryPq = actualPq.multiply(businessPq).divide(industryPq.add(businessPq), 3, BigDecimal.ROUND_HALF_UP);
					modelPrcRow.setDeliveryPq(businessDeliveryPq);
					//按标杆电价结算的结算电费 = 标杆电价 × 按标杆电价结算的结算电量
					modelPrcRow.setDeliveryAmt(businessDeliveryPq.multiply(modelPrc).setScale(2, BigDecimal.ROUND_HALF_UP));
					smCompanyCostDetailListTotal.add(modelPrcRow);
					
					//按工业标杆电价结算的支出明细 （批发市场购电支出明细最后一条）
					SmCompanyCostDetail industryModelPrcRow = new SmCompanyCostDetail();
					industryModelPrcRow.setAgreName("按标杆电价结算(工业)");
					industryModelPrcRow.setMonthPq(null);
					industryModelPrcRow.setMonthPrc(industryModelPrc);
					industryModelPrcRow.setSort(smCompanyCostDetailListTotal.size());
					//按工业标杆电价结算的结算电量 = 分配剩余的实际用电量 - 按商业标杆电价结算的结算电量
					BigDecimal industryDeliveryPq = actualPq.subtract(businessDeliveryPq);
					industryModelPrcRow.setDeliveryPq(industryDeliveryPq);
					//按标杆电价结算的结算电费 = 标杆电价 × 按标杆电价结算的结算电量
					industryModelPrcRow.setDeliveryAmt(industryDeliveryPq.multiply(industryModelPrc).setScale(2, BigDecimal.ROUND_HALF_UP));
					smCompanyCostDetailListTotal.add(industryModelPrcRow);
				}
			}
		}else {	//实际电量 <= 总购电量
			for (SmCompanyCostDetail smCompanyCostDetail : smCompanyCostDetailListTotal) {
				//本合同电量 >= 剩余分配的实际用电量
				if(smCompanyCostDetail.getMonthPq().compareTo(actualPq) != -1) {
					//本合同结算电量 = 剩余分配的实际用电量
					smCompanyCostDetail.setDeliveryPq(actualPq.setScale(3, BigDecimal.ROUND_HALF_UP));
					actualPq = BigDecimal.ZERO;
				}else {	//本合同电量 < 剩余分配的实际用电量
					//本合同结算电量 = 本合同电量
					smCompanyCostDetail.setDeliveryPq(smCompanyCostDetail.getMonthPq().setScale(3, BigDecimal.ROUND_HALF_UP));
					//更新剩余分配电量
					actualPq = actualPq.subtract(smCompanyCostDetail.getDeliveryPq());
				}
				//本合同的结算电费 = 本合同电价 × 本合同结算电量
				smCompanyCostDetail.setDeliveryAmt(smCompanyCostDetail.getDeliveryPq().multiply(smCompanyCostDetail.getMonthPrc()).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
	}
	/**
	 * @Title: caculateCompanyDevAmt
	 * @Description: 根据所在省份的交易规则去计算售电公司偏差费用
	 * 				售电公司偏差费用计算方法：
						1、97%（lowerThreshold，不含）以下电量：标杆电价的10%；
						2、103%（upperThreshold，不含）至110%（含）电量：标杆电价的10%；
						3、110%（prcProp，不含）以上：标杆电价的20%
						仅计算季偏差，即在2018年3月，计算18年1、2、3月三个月的电量的偏差；2018年6月，计算4、5、6月三个月的电量的偏差，以此类推。
	 * @param companyProfit 中  ym 年月 yyyyMM格式
	 * @param calcConfigureJsonMap  其中的参数有：
	 * 				modelPrc		燃煤标杆电价
			 		prcProp			正偏差阈值(大值)
			 		upperThreshold		正偏差阈值（小值）
			 		lowerThreshold		负偏差阈值（原值）
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月30日 下午5:12:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月30日 下午5:12:48
	 * @since  1.0.0
	 */
	private void caculateCompanyDevAmt(SmCompanyProfit companyProfit, Map<String, Object> calcConfigureJsonMap) throws Exception{
		String ym = companyProfit.getYm().replaceAll("-", "");
		String year = ym.substring(0,4);
		int month = Integer.valueOf(ym.substring(4));
		//判断是否是季度最后一个月
		int n = month%3;
		if(n != 0) {
			//非季度月末 售电公司偏差考核费用(偏差违约金)为0
			companyProfit.setDevDam(BigDecimal.ZERO);	//公司偏差违约金
			return;
		}else {
			Map<String, String> params = new HashMap<>();
			//根据ym计算本季度开始年月和结束年月
			int minMon = month-2;
			int maxMon = month;	
			params.put("minYm", year + (minMon > 9 ? (""+minMon) : ("0"+minMon)));
			params.put("maxYm", year + (maxMon > 9 ? (""+maxMon) : ("0"+maxMon)));
			//根据偏差考核方式计算不同月份区间的总委托电量和实际用电量
			Parameter.isFilterData.set(true);
			@SuppressWarnings("unchecked")
			Map<String, BigDecimal> result = (Map<String, BigDecimal>) dao.getOneBySQL("smCompanyCostDetail.sql.getDealAndActualPqByParams", params);
			Parameter.isFilterData.set(false);
			BigDecimal purchasePqTotal = result.get("dealPq");
			BigDecimal actualPqTotal = result.get("actualPq");
			
			BigDecimal modelPrc = null;	//燃煤标杆电价
			BigDecimal prcProp = null;	//正偏差阈值(大值)
			BigDecimal upperThreshold = null;	//正偏差阈值（小值）
			BigDecimal lowerThreshold = null;	//负偏差阈值（原值）
			BigDecimal minModelProp = null;		//标杆电价惩罚比例1
			BigDecimal maxModelProp = null;		//标杆电价惩罚比例2
			if(calcConfigureJsonMap.get("modelPrc") != null) {
				modelPrc = new BigDecimal(calcConfigureJsonMap.get("modelPrc").toString());
			}else {
				throw new BusinessException("结算省份的“燃煤机组标杆电价”为空，结算失败！");
			}
			if(calcConfigureJsonMap.get("prcProp") != null) {
				prcProp = new BigDecimal(calcConfigureJsonMap.get("prcProp").toString());
			}else {
				throw new BusinessException("结算省份的“正偏差阈值(大值)”为空，结算失败！");
			}
			if(calcConfigureJsonMap.get("upperThreshold") != null) {
				upperThreshold = new BigDecimal(calcConfigureJsonMap.get("upperThreshold").toString());
			}else {
				throw new BusinessException("结算省份的“正偏差阈值（小值）”为空，结算失败！");
			}
			if(calcConfigureJsonMap.get("lowerThreshold") != null) {
				lowerThreshold = new BigDecimal(calcConfigureJsonMap.get("lowerThreshold").toString());
			}else {
				throw new BusinessException("结算省份的“负偏差阈值（原值）”为空，结算失败！");
			}
			if(calcConfigureJsonMap.get("minModelProp") != null) {
				minModelProp = new BigDecimal(calcConfigureJsonMap.get("minModelProp").toString());
			}else {
				throw new BusinessException("结算省份的“标杆电价惩罚比例1”为空，结算失败！");
			}
			if(calcConfigureJsonMap.get("maxModelProp") != null) {
				maxModelProp = new BigDecimal(calcConfigureJsonMap.get("maxModelProp").toString());
			}else {
				throw new BusinessException("结算省份的“标杆电价惩罚比例2”为空，结算失败！");
			}
			
			//正偏差阈值(大值) 的偏差率 = 正偏差阈值（大值）-100
			BigDecimal upperThresholdProp2 = prcProp.subtract(new BigDecimal(100)).divide(new BigDecimal(100));	
			//正偏差阈值（小值） 的偏差率 = 正偏差阈值（小值）-100
			BigDecimal upperThresholdProp = upperThreshold.subtract(new BigDecimal(100)).divide(new BigDecimal(100));
			//负偏差阈值（原值）的偏差率 = 负偏差阈值（原值）-100
			BigDecimal lowerThresholdProp = lowerThreshold.subtract(new BigDecimal(100)).divide(new BigDecimal(100));		
			
			//总偏差率=(实际电量-总购电量)/总购电量 (保留6位小数)
			BigDecimal deviationPropTotal = actualPqTotal.subtract(purchasePqTotal).divide(purchasePqTotal,6,RoundingMode.HALF_UP);
			
			//实际电量 > 正偏差阈值(大值)
			if(deviationPropTotal.compareTo(upperThresholdProp2) == 1) {
				BigDecimal devDam1 = actualPqTotal.subtract(purchasePqTotal.multiply(upperThresholdProp2.add(BigDecimal.ONE)))
						.multiply(modelPrc.multiply(maxModelProp.divide(new BigDecimal(100))));
				BigDecimal devDam2 = upperThresholdProp2.subtract(upperThresholdProp).multiply(purchasePqTotal)
						.multiply(modelPrc.multiply(minModelProp.divide(new BigDecimal(100))));
				companyProfit.setDevDam(devDam1.add(devDam2).setScale(2, RoundingMode.HALF_UP));	//公司偏差违约金
			}
			// 正偏差阈值（小值） < 实际电量 <= 正偏差阈值(大值)
			else if(deviationPropTotal.compareTo(upperThresholdProp2) != 1 && deviationPropTotal.compareTo(upperThresholdProp) == 1) {
				BigDecimal devDam = actualPqTotal.subtract(purchasePqTotal.multiply(upperThresholdProp.add(BigDecimal.ONE)))
						.multiply(modelPrc.multiply(minModelProp.divide(new BigDecimal(100))));
				companyProfit.setDevDam(devDam.setScale(2, RoundingMode.HALF_UP));	//公司偏差违约金
			}
			// 负偏差阈值（原值） <= 实际电量 <= 正偏差阈值（小值）
			else if(deviationPropTotal.compareTo(upperThresholdProp) != 1 && deviationPropTotal.compareTo(lowerThresholdProp) != -1) {
				companyProfit.setDevDam(BigDecimal.ZERO);	//公司偏差违约金
			}
			//实际电量 < 负偏差阈值（原值）
			//（总购电量×负偏差阈值（一般是0.97）- 实际用电量）× 标杆电价×惩罚比例
			else if(deviationPropTotal.compareTo(lowerThresholdProp) == -1) {
				BigDecimal devDam = purchasePqTotal.multiply(lowerThresholdProp.add(BigDecimal.ONE)).subtract(actualPqTotal)
						.multiply(modelPrc.multiply(minModelProp.divide(new BigDecimal(100))));
				companyProfit.setDevDam(devDam.setScale(2, RoundingMode.HALF_UP));	//公司偏差违约金
			}
		}
	}
	/**
	 * @Title: caculateConsDelivryInfo
	 * @Description: 由用户实际用电量和申报电量根据用户合同中的用户违约惩罚方式 计算
	 * 		1.市场化交易结算电量（兆瓦时）
			2.按非市场电价结算电量(兆瓦时）
			3.结算电费（元）
			4.用户未考核收益
			5.用户偏差考核费用 （暂时按0计算）
			6.净收益
			7.赔偿用户费用
	 * @param modelPrc 	燃煤机组标杆电价
	 * @param bidPrcAvg 	竞价加权平均价
	 * @param profitDetail	中用到proxyPq申报电量和consDelPq实际用电量、deliveryPrc结算电价、upperThreshold1正偏差1段域值
												 * @param sellPpaPunishInfo 中有  ： 用户违约惩罚方式 和 售电公司违约惩罚方式
												 * ,BigDecimal bidPrcAvg,SellPpaPunishInfo sellPpaPunishInfo   本方法以前的参数,
												 * 因本方法无需计算用户考核费用和售电公司赔偿用户费用，所以删除这两个参数。包括代码都只是注释掉，方便以后别的省份结算参考
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月28日 下午4:36:55
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月28日 下午4:36:55
	 * @since  1.0.0
	 */
	private void caculateConsDelivryInfo(BigDecimal modelPrc,SmConsumerProfitDetail profitDetail) throws Exception{
		if(profitDetail == null || profitDetail.getProxyPq() == null || profitDetail.getConsDelPq() == null) {
			throw new BusinessException("结算中有用户实际用电量或委托电量为空，结算失败！");
		}
		BigDecimal upperThreshold1 = profitDetail.getUpperThreshold1();
		//如果“正偏差1段域值 ”为空
		if(upperThreshold1 == null ) {
			//则市场化交易结算电量 = 实际用电量
			profitDetail.setMarketizePq(profitDetail.getConsDelPq());
			profitDetail.setNmarketizePq(BigDecimal.ZERO);
		}else {			//如果“正偏差1段域值 ”不为空
			upperThreshold1 = upperThreshold1.abs();
			//电量上限值 = 委托电量 × （1 + 正偏差域值）,保存3位小数
			BigDecimal upperThresholdPq = profitDetail.getProxyPq().multiply(BigDecimal.ONE.add(
					upperThreshold1.divide(new BigDecimal("100")))).setScale(3, BigDecimal.ROUND_HALF_UP);
			
			if(upperThresholdPq.compareTo(profitDetail.getConsDelPq()) == 1) {
				//实际用电量 < 电量上限值  ,   则市场化交易结算电量 = 实际用电量
				//则市场化交易结算电量 = 实际用电量
				profitDetail.setMarketizePq(profitDetail.getConsDelPq());
				profitDetail.setNmarketizePq(BigDecimal.ZERO);
			}else {		//实际用电量 > 电量上限值
				//则“市场化交易结算电量” = 电量上限值
				profitDetail.setMarketizePq(upperThresholdPq);
				//“非市场电价结算电量” = 实际用电量 - 电量上限值
				profitDetail.setNmarketizePq(profitDetail.getConsDelPq().subtract(upperThresholdPq));
			}
		}
		//以上是计算 市场化交易结算电量 和 按非市场电价结算电量
		
		//以下是计算结算电费,保存3位小数
		BigDecimal deliveryCost = profitDetail.getDeliveryPrc().multiply(profitDetail.getMarketizePq()).setScale(3, BigDecimal.ROUND_HALF_UP);
		if(profitDetail.getNmarketizePq() != null) {
			//保存2位小数
			deliveryCost = deliveryCost.add(profitDetail.getNmarketizePq().multiply(modelPrc)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		profitDetail.setDeliveryCost(deliveryCost); 		//设置“结算电费”
		
		
		//以下是计算未考核收益(即差额电费，保留2位小数)   未考核收益=市场化结算电量*（标杆电价-结算电价）
		BigDecimal consUncheckedPro = profitDetail.getMarketizePq().multiply(modelPrc.subtract(profitDetail.getDeliveryPrc())).setScale(2, BigDecimal.ROUND_HALF_UP);
		profitDetail.setConsUncheckedPro(consUncheckedPro);
		
		//以下是计算“赔偿用户费用”
		/*if(sellPpaPunishInfo.getCompPunishFlag() == null || "".equals(sellPpaPunishInfo.getCompPunishFlag().trim()) 
				|| "0".equals(sellPpaPunishInfo.getCompPunishFlag().trim())) {
			//如果不需要赔偿
			profitDetail.setConsCompensate(BigDecimal.ZERO);
		}else {		//如果需要赔偿
			//用户的分配总电量
			if(profitDetail.getDistTotalPq() == null) {
				//用户的分配总电量 = 竞价分配量 + 长协分配量
				profitDetail.setDistTotalPq(profitDetail.getBidDistPq().add(profitDetail.getLcDistPq()));
			}
			SsAgreSchemeDetail ssAgreSchemeDetail = new SsAgreSchemeDetail();
			ssAgreSchemeDetail.setConsId(profitDetail.getConsId());		//用户id
			ssAgreSchemeDetail.setDelPq(profitDetail.getConsDelPq());	//实际用电量
			ssAgreSchemeDetail.setBidPrc(bidPrcAvg);					//竞价加权平均电价
			ssAgreSchemeDetail.setReportPq(profitDetail.getProxyPq());	//申报电量
			ssAgreSchemeDetail.setBidPq(profitDetail.getBidDistPq());				//竞价成交电量 (即分配竞价电量)
			ssAgreSchemeDetail.setLcPq(profitDetail.getLcDistPq());				//分配双边电量
			
			ssAgreSchemeDetail.setPunishTypeUpper(sellPpaPunishInfo.getPunishTypeUpper());				// 正偏差惩罚类型("1" 赔偿)
			ssAgreSchemeDetail.setPunishUpperThreshold(sellPpaPunishInfo.getUpperThreshold());				//正偏差允许范围
			ssAgreSchemeDetail.setPunishUpperProp(sellPpaPunishInfo.getUpperProp());				//正偏差惩罚比例
			ssAgreSchemeDetail.setPunishUpperType(sellPpaPunishInfo.getUpperType());				//正偏差惩罚电价基准
			ssAgreSchemeDetail.setUpperPrc(sellPpaPunishInfo.getUpperPrc());				//正偏差惩罚协议价
			
			ssAgreSchemeDetail.setPunishTypeLower(sellPpaPunishInfo.getPunishTypeLower());				// 负偏差惩罚类型("1" 赔偿)
			ssAgreSchemeDetail.setPunishLowerThreshold(sellPpaPunishInfo.getLowerThreshold());				//负偏差允许范围
			ssAgreSchemeDetail.setPunishLowerProp(sellPpaPunishInfo.getLowerProp());				//负偏差惩罚比例
			ssAgreSchemeDetail.setPunishLowerType(sellPpaPunishInfo.getLowerType());				//负偏差惩罚电价基准
			ssAgreSchemeDetail.setLowerPrc(sellPpaPunishInfo.getLowerPrc());				//负偏差惩罚协议价
			
			ssAgreSchemeDetail.setCompensateFlag(sellPpaPunishInfo.getCompPunishFlag());				//  是否赔偿	("1" 赔偿)
			ssAgreSchemeDetail.setCompensateThreshold(sellPpaPunishInfo.getCompLowerThreshold());				// 赔偿域值
			ssAgreSchemeDetail.setCompensateType(sellPpaPunishInfo.getCompLowerType());				// 赔偿基准
			ssAgreSchemeDetail.setCompensatePrc(sellPpaPunishInfo.getCompLowerPrc());				// 赔偿协议价
			ssAgreSchemeDetail.setCompensateProp(sellPpaPunishInfo.getCompLowerProp());				// 赔偿比例值
			
			//调欣泽的接口实现获取用户的 售电公司赔偿用户费用
			//合同辅助设计ssAgreSchemeService, 计算 售电公司赔偿用户费用 和 用户考核电费
			ssAgreSchemeService.getCheckedPro(ssAgreSchemeDetail);
			profitDetail.setConsCompensate(ssAgreSchemeDetail.getCompensateAmt());	//售电公司赔偿用户费用
			profitDetail.setConsCheckedPro(BigDecimal.ZERO);	//单个用户的偏差考核费用（元）（暂时按0计算）
		}*/
		//偏差电费，即单个用户的偏差考核费用（元），在前台已经赋值，若为null，则赋值0
		if(profitDetail.getConsCheckedPro() == null) {
			profitDetail.setConsCheckedPro(BigDecimal.ZERO);
		}
		profitDetail.setConsCompensate(BigDecimal.ZERO);	//售电公司赔偿用户费用，江苏结算中为0
		//以下是计算用户"净收益"(保留2位小数)   净收益 = 未考核收益 - 偏差电费 + 售电公司赔偿用户费用
		profitDetail.setConsTotalPro(consUncheckedPro.subtract(profitDetail.getConsCheckedPro()).add(profitDetail.getConsCompensate()).setScale(2, BigDecimal.ROUND_HALF_UP));
	}
}