package com.hhwy.purchaseweb.settlement.fujian.service.impl;

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
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchase.domain.PhmInvoiceInfo;
import com.hhwy.purchase.domain.SmCompanyCostDetail;
import com.hhwy.purchase.domain.SmConsumerProfit;
import com.hhwy.purchase.domain.SmPurchaseScheme;
import com.hhwy.purchase.domain.SmPurchaseSchemeDetail;
import com.hhwy.purchase.domain.SmSettlementMonth;
import com.hhwy.purchaseweb.arithmetic.balance.service.BalanceService;
import com.hhwy.purchaseweb.arithmetic.divide.service.PrcService;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.domain.PhmInvoiceInfoDetail;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.domain.PhmInvoiceInfoVo;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.service.PhmInvoiceInfoService;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureDetail;
import com.hhwy.purchaseweb.settlement.base.smcompanycostdetail.service.SmCompanyCostDetailService;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.service.SmConsumerProfitService;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.service.SmPurchaseSchemeDetailService;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain.SmSettlementMonthDetail;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.service.SmSettlementMonthService;
import com.hhwy.purchaseweb.settlement.fujian.domain.FormDataAndCostListFj;
import com.hhwy.purchaseweb.settlement.fujian.domain.RetailDetailFj;
import com.hhwy.purchaseweb.settlement.fujian.domain.SmPurchaseSchemeVoFj;
import com.hhwy.purchaseweb.settlement.fujian.service.SettlementServiceFj;

/**
 * <b>类 名 称：</b>SettlementServiceFj<br/>
 * <b>类 描 述：</b><br/>		福建的结算service实现
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年8月17日 下午5:39:57<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class SettlementServiceImplFj implements SettlementServiceFj {

	public static final Logger log = LoggerFactory.getLogger(SettlementServiceImplFj.class);
	
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
	 * 合同辅助设计ssAgreSchemeService
	 */
	@Autowired
	@Qualifier("JSPrcService")
	private PrcService jSPrcService;
	
	/**
	 * smSettlementMonthService 结算主表service
	 */
	@Autowired
	private SmSettlementMonthService smSettlementMonthService;
	
	/**
	 * smPurchaseSchemeDetailService
	 */
	@Autowired
	private SmPurchaseSchemeDetailService smPurchaseSchemeDetailService;
	
	/**
	 * smConsumerProfitService 月度结算中的用户收益信息
	 */
	@Autowired
	private SmConsumerProfitService smConsumerProfitService;
	
	/**
	 * smCompanyCostDetailService	批发市场购电支出明细service
	 */
	@Autowired
	private SmCompanyCostDetailService smCompanyCostDetailService;
	
	/**
	 * phmInvoiceInfoService	发票信息service
	 */
	@Autowired
	private PhmInvoiceInfoService phmInvoiceInfoService;
	
	/**
	 * @Title: getFormDataAndCostListFjByYm
	 * @Description: 通过年月（新增编辑页面，即add页面）获取结算方案列表页面上面的 form表单中的数据 和 “批发交易结算明细”列表数据
	 * 						当月无论有没有结算数据，都组装初始化的结算数据
	 * 				福建结算  新增或编辑 页面会调用此方法
	 * @param ym
	 * @return
	 * @throws Exception 
	 * FormDataAndCostListFj
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月23日 下午3:03:56
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月23日 下午3:03:56
	 * @since  1.0.0
	 */
	public FormDataAndCostListFj getFormDataAndCostListFjByYm(String ym) throws Exception{
		if(ym == null||"".equals(ym)){
			return null;
		}
		String settleId = null;
		ym = ym.replaceAll("-", "");
		String result = smSettlementMonthService.getSmSettlementMonthIdByYm(ym);
		if(result != null && !"".equals(result)) {
			settleId = result;
		}
		String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
		Map<String , String> params = new HashMap<>();
		params.put("ym", ym);
		params.put("loginUserId", loginUserId);
		//计算得到  用户数、委托电量、结算电量、 总购电量四个值
		FormDataAndCostListFj formDataDetailFj = (FormDataAndCostListFj)dao.getOneBySQL("settlementfj.sql.getSmSettlementMonthInfoByIdOrYm",params);
		
		if(settleId != null && !"".equals(settleId) ) {
			formDataDetailFj.setId(settleId);	//设置结算id
			
			//获取公司收益相关值
			Parameter.isFilterData.set(true);
			SmSettlementMonthDetail profit = (SmSettlementMonthDetail) dao.getOneBySQL("settlementfj.sql.getProfitInfoByYm",ym);
			Parameter.isFilterData.set(false);
			if(profit != null){
				formDataDetailFj.setCompanyProfitId(profit.getCompanyId());		//售电公司收益id
				formDataDetailFj.setDevDam(profit.getDevDam());					//偏差违约金(售电公司偏差考核)
				formDataDetailFj.setCompanyPro(profit.getCompanyPro());			//售电公司总利润 
				formDataDetailFj.setConsCheckedProTotal(profit.getConsCheckedProTotal());	//用户偏差考核总费用
			}
		}
		formDataDetailFj.setYm(ym.substring(0, 4) + "-" + ym.substring(4));  //yyyy-MM格式
		
		//获取福建省的电力交易规则中的  正偏差阈值和燃煤机组标杆电价。
			//用于：如果实际用电量大于购电量的3%，在批发交易结算明细加一行  “计划外电量”，超出的部分按福建标杆电价计算
		SmCalcConfigureDetail smCalcConfigureDetail = balanceService.getSmCalcConfigureDetail();
		if(smCalcConfigureDetail != null){
			String json = smCalcConfigureDetail.getCalcParam();
			if(json != null && !json.equals("")){
				Map<String, Object> calcConfigureJsonMap = JSON.parseObject(json);
				//燃煤标杆电价
				if(calcConfigureJsonMap.get("modelPrc") != null) {
					formDataDetailFj.setModelPrc(new BigDecimal(calcConfigureJsonMap.get("modelPrc").toString()));
				}
			}
		}
		//赋值 批发交易结算明细表list数据
		formDataDetailFj.setCostDetailList(this.getCostDetailListByYm(ym));
		
		//根据福建省的交易规则去计算每个“批发市场购电支出明细”的结算电量(保留3位小数)及结算电费(保留2位小数)
			//如果实际用电量大于购电量的3%，在批发交易结算明细加一行  “计划外电量”，超出的部分按福建标杆电价计算
		this.caculateSmCompanyCostDetail(formDataDetailFj);
		
		//计算 批发交易总电费
		List<SmCompanyCostDetail> smCompanyCostDetailListTotal = formDataDetailFj.getCostDetailList();
		BigDecimal purchaseAmtTotal = BigDecimal.ZERO;
		for (int i = 0; i < smCompanyCostDetailListTotal.size(); i++) {
			SmCompanyCostDetail smCompanyCostDetail = smCompanyCostDetailListTotal.get(i);
			if(smCompanyCostDetail.getDeliveryAmt() != null) {
				purchaseAmtTotal = purchaseAmtTotal.add(smCompanyCostDetail.getDeliveryAmt());
			}
		}
		formDataDetailFj.setCostAmt(purchaseAmtTotal);
		return formDataDetailFj;
	}
	/**
	 * @Title: getFormDataAndCostListFjBySettleId
	 * @Description: 通过结算id(detail页面)获取结算方案列表页面上面的 form表单中的数据 和 “批发交易结算明细”列表数据
	 * 						当月已有结算数据，取结算数据
	 * 				福建结算  详情页面会调用此方法
	 * @param settleId
	 * @return
	 * @throws Exception 
	 * FormDataAndCostListFj
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月23日 下午3:04:47
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月23日 下午3:04:47
	 * @since  1.0.0
	 */
	public FormDataAndCostListFj getFormDataAndCostListFjBySettleId(String settleId) throws Exception{
		if(settleId == null||"".equals(settleId)){
			return null;
		}
		SmSettlementMonth settlement = smSettlementMonthService.getSmSettlementMonthById(settleId);
		if(settlement == null){
			return null;
		}
		String ym = settlement.getYm();
		
		//获取除了“批发交易电价”和“零售交易平均电价”之外的所有form表单的值，例：  用户数、委托电量、结算电量、 总购电量等
		FormDataAndCostListFj formDataDetailFj = (FormDataAndCostListFj)dao.getOneBySQL("settlementfj.sql.getFormDataBySettleId",settleId);
		
		formDataDetailFj.setYm(ym.substring(0, 4) + "-" + ym.substring(4));  //yyyy-MM格式
		
		//赋值 批发交易结算明细表list数据
		List<SmCompanyCostDetail> costDetailList = smCompanyCostDetailService.getCostDetailListBySchemeId(settleId);
		formDataDetailFj.setCostDetailList(costDetailList);
		
		return formDataDetailFj;
	}
	
	
	/**
	 * @Title: getCostDetailListByYm
	 * @Description: 根据ym获取福建结算中批发交易结算明细表list数据
	 * @param ym
	 * @return 
	 * List<SmCompanyCostDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月21日 下午3:23:49
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月21日 下午3:23:49
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private List<SmCompanyCostDetail> getCostDetailListByYm(String ym) {
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
		
		//所有“批发市场购电支出明细”, 结算顺序为  年度挂牌>年度双边>年度竞价>月度挂牌>月度双边>月度竞价
		List<SmCompanyCostDetail> smCompanyCostDetailListTotal = new ArrayList<SmCompanyCostDetail>();
		smCompanyCostDetailListTotal.addAll(listedYearSmCompanyCostDetailList);
		smCompanyCostDetailListTotal.addAll(lcYearSmCompanyCostDetailList);
		smCompanyCostDetailListTotal.addAll(bidYearSmCompanyCostDetailList);
		smCompanyCostDetailListTotal.addAll(listedMonthSmCompanyCostDetailList);
		smCompanyCostDetailListTotal.addAll(lcMonthSmCompanyCostDetailList);
		smCompanyCostDetailListTotal.addAll(bidMonthSmCompanyCostDetailList);
		
		//售电公司的  总购电量   计算
//		BigDecimal purchasePqTotal = BigDecimal.ZERO;
		for (int i = 0; i < smCompanyCostDetailListTotal.size(); i++) {
			SmCompanyCostDetail smCompanyCostDetail = smCompanyCostDetailListTotal.get(i);
			smCompanyCostDetail.setSort(i); 		//对顺序号赋值
			/*if(smCompanyCostDetail.getMonthPq() != null) {
				purchasePqTotal = purchasePqTotal.add(smCompanyCostDetail.getMonthPq());
			}*/
		}
		return smCompanyCostDetailListTotal;
	}	
	/**
	 * @Title: caculateSmCompanyCostDetail
	 * @Description: 根据所在省份的交易规则去计算每个“批发市场购电支出明细”的结算电量(保留3位小数)及结算电费(保留2位小数)
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
	private void caculateSmCompanyCostDetail(FormDataAndCostListFj formDataDetailFj) throws Exception{
		BigDecimal purchasePqTotal = formDataDetailFj.getTotalPurchasePq();
		BigDecimal actualPqTotal = formDataDetailFj.getActualTotalPq();
		if(purchasePqTotal == null || actualPqTotal == null) {
			return;
		}
		List<SmCompanyCostDetail> smCompanyCostDetailListTotal = formDataDetailFj.getCostDetailList();
		if(smCompanyCostDetailListTotal == null || smCompanyCostDetailListTotal.size() == 0 
				|| purchasePqTotal == null || actualPqTotal == null 
				|| purchasePqTotal.compareTo(BigDecimal.ZERO) == 0 || actualPqTotal.compareTo(BigDecimal.ZERO) == 0) {
			return;
		}
		BigDecimal modelPrc = formDataDetailFj.getModelPrc();	//燃煤标杆电价
		if(modelPrc == null) {
			modelPrc = new BigDecimal(393.2);
		}
		//总偏差电量
		formDataDetailFj.setDevPq(actualPqTotal.subtract(purchasePqTotal));
		//总偏差率=(实际电量-总购电量)/总购电量 (保留6位小数) - 1   例：0.050021
		BigDecimal deviationPropTotal = actualPqTotal.subtract(purchasePqTotal).divide(purchasePqTotal,6,RoundingMode.HALF_UP);
		formDataDetailFj.setDevPqProp(deviationPropTotal.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));
		
		//分配剩余的实际用电量
		BigDecimal actualPq = actualPqTotal;
		//实际电量 > 总购电量
		if(deviationPropTotal.compareTo(BigDecimal.ZERO) == 1) {
			for (int i = 0; i < smCompanyCostDetailListTotal.size(); i ++ ) {
				SmCompanyCostDetail smCompanyCostDetail = smCompanyCostDetailListTotal.get(i);
				//本合同结算电量 = 本合同电量
				smCompanyCostDetail.setDeliveryPq(smCompanyCostDetail.getMonthPq().setScale(3, BigDecimal.ROUND_HALF_UP));
				//更新剩余分配电量
				actualPq = actualPq.subtract(smCompanyCostDetail.getDeliveryPq());
				//本合同的结算电费 = 本合同电价 × 本合同结算电量
				smCompanyCostDetail.setDeliveryAmt(smCompanyCostDetail.getDeliveryPq().multiply(smCompanyCostDetail.getMonthPrc()).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			SmCompanyCostDetail lastCostDetail = new SmCompanyCostDetail();
			lastCostDetail.setAgreName("计划外电量");
			lastCostDetail.setMonthPq(null);
			lastCostDetail.setMonthPrc(modelPrc);
			lastCostDetail.setSort(smCompanyCostDetailListTotal.size());
			lastCostDetail.setDeliveryPq(actualPq);
			//本合同的结算电费 = 本合同电价 × 本合同结算电量
			lastCostDetail.setDeliveryAmt(actualPq.multiply(modelPrc).setScale(2, BigDecimal.ROUND_HALF_UP));
			smCompanyCostDetailListTotal.add(lastCostDetail);
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
	 * @Title: getRetailDetailByYm
	 * @Description: 根据年月获取当月“零售市场售电收入明细”列表(若当月已有结算数据，则取结算数据；若当月没有结算数据，则组装初始化的结算数据)
	 * @param ym
	 * @return
	 * @throws Exception 
	 * List<RetailDetailFj>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月17日 下午5:35:18
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月17日 下午5:35:18
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<RetailDetailFj> getRetailDetailByYm(String ym) throws Exception{
		if(ym == null || "".equals(ym)) {
			return null;
		}
		ym = ym.replaceAll("-", "");
		
		String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
		Map<String , String> params = new HashMap<>();
		params.put("ym", ym);
		params.put("loginUserId", loginUserId);
		
		//Parameter.isFilterData.set(true);
		List<RetailDetailFj> list=(List<RetailDetailFj>) dao.getBySql("settlementfj.sql.getInitRetailDetailByYm", params);
		ConvertListUtil.convert(list);
		//Parameter.isFilterData.set(false);
		return list;
	}
	
	/**
	 * @Title: getRetailDetailBySettledId
	 * @Description: 根据结算id获取当月“零售市场售电收入明细”列表，是详情页面调用
	 * @param settleId
	 * @return
	 * @throws Exception 
	 * List<RetailDetailFj>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月22日 下午1:45:40
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月22日 下午1:45:40
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<RetailDetailFj> getRetailDetailBySettledId(String settleId) throws Exception{
		if(settleId == null || "".equals(settleId)) {
			return null;
		}
		Parameter.isFilterData.set(true);
		List<RetailDetailFj> list=(List<RetailDetailFj>) dao.getBySql("settlementfj.sql.getRetailDetailBySettledId", settleId);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(list);
		return list;
	}
	
	
	/**
	 * @Title: saveOrUpdateSmPurchaseSchemeVoFj
	 * @Description: 福建省月度收益结算的新增或修改
	 * @param smPurchaseSchemeVoFj 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月22日 下午5:06:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月22日 下午5:06:20
	 * @since  1.0.0
	 */
	@Override
	@Transactional
	public void saveOrUpdateSmPurchaseSchemeVoFj(SmPurchaseSchemeVoFj smPurchaseSchemeVoFj) throws Exception{
		if(smPurchaseSchemeVoFj.getYm() == null || "".equals(smPurchaseSchemeVoFj.getYm())
				|| smPurchaseSchemeVoFj.getRetailDetailFjList() == null || smPurchaseSchemeVoFj.getRetailDetailFjList().size() == 0) {
			throw new BusinessException("参数错误，保存失败");
		}
		String settleId = smPurchaseSchemeVoFj.getSettleId();
		String ym = smPurchaseSchemeVoFj.getYm();
		if(ym == null || "".equals(ym)) {
			throw new BusinessException("年月为空，保存失败。请刷新重试！");
		}
		ym = ym.replaceAll("-", "");
		
		boolean addFlag = false;
		//如果没有本月的结算，则新增一个结算主表信息
		if(settleId == null || "".equals(settleId)) {
			addFlag = true;
			String result = smSettlementMonthService.getSmSettlementMonthIdByYm(ym);
			if(result != null && !"".equals(result)) {
				throw new BusinessException("已有本月结算数据！");
			}
			/*这里可以直接把result设置为新的settleId，看需求需不需要直接覆盖后台数据*/
			SmSettlementMonth settlement = new SmSettlementMonth();
			settlement.setYm(ym);
			smSettlementMonthService.saveSmSettlementMonth(settlement);
			settleId = settlement.getId();
			smPurchaseSchemeVoFj.setSettleId(settleId);
		}
		//月度购电方案信息 保存或更新
		SmPurchaseScheme smPurchaseScheme = smPurchaseSchemeVoFj.getSmPurchaseScheme();
		smPurchaseScheme.setId(settleId);		//福建省一个结算只有一个方案，所以设置方案id为结算id
		smPurchaseScheme.setSettleId(settleId);
		smPurchaseScheme.setYm(ym);
		smPurchaseScheme.setSchemeStatus(1);	//每月一条记录，设置为“归档”状态
		
		//售电公司收益信息对象保存/更新
		smPurchaseSchemeVoFj.getSmCompanyProfit().setSchemeId(smPurchaseScheme.getId());
		smPurchaseSchemeVoFj.getSmCompanyProfit().setYm(ym);
		if(smPurchaseSchemeVoFj.getSmCompanyProfit().getId() == null || "".equals(smPurchaseSchemeVoFj.getSmCompanyProfit().getId())) {
			smPurchaseSchemeVoFj.getSmCompanyProfit().setId(settleId);//福建省一个结算只有一个方案，所以设置公司收益id为结算id
		}
		
		if(addFlag) {	//新增结算, 即使不判断新增还是更新都用save，因为id确定了而且save包含update效果，所以最终效果一样
			dao.save(smPurchaseScheme);
			dao.save(smPurchaseSchemeVoFj.getSmCompanyProfit());
		}else {		//更新结算
			dao.update(smPurchaseScheme);
			dao.update(smPurchaseSchemeVoFj.getSmCompanyProfit());
		}
		
		//把purchaseSchemeVoFj中的retailDetailFjList转换为consProfitList和schemeDetailList
		this.convertRetailDetailFjList(smPurchaseSchemeVoFj);
		
		//根据schemeId方案id删除 用户受益信息 和 月度结算明细信息、批发交易结算明细数据
		smPurchaseSchemeDetailService.deleteSchemeDetailBySchemeId(settleId);
		smConsumerProfitService.deleteSmConsumerProfitBySchemeIds(new String[] {settleId});
		smCompanyCostDetailService.deleteSmCompanyCostDetailBySchemeId(new String[] {settleId});
		
		//用户受益信息对象；用于保存
		List<SmConsumerProfit> smConsumerProfitList = smPurchaseSchemeVoFj.getSmConsumerProfitList();
		dao.saveList(smConsumerProfitList);
		
		//月度结算明细信息 列表，用于保存
		List<SmPurchaseSchemeDetail> smPurchaseSchemeDetailList = smPurchaseSchemeVoFj.getSmPurchaseSchemeDetailList();
		dao.saveList(smPurchaseSchemeDetailList);
		
		//保存 批发交易结算明细 列表数据
	    List<SmCompanyCostDetail> costDetailList = smPurchaseSchemeVoFj.getCostDetailList();
		for (SmCompanyCostDetail costDetail : costDetailList) {
			costDetail.setSchemeId(settleId);
		}
		dao.saveList(costDetailList);
	    
		//判断该月是否存在发票登记信息，若不存在，生成发票登记信息，删除方案时记得删除相关的发票登记信息
		PhmInvoiceInfoVo phmInvoiceInfoVo = new PhmInvoiceInfoVo();
		phmInvoiceInfoVo.getPhmInvoiceInfo().setYm(ym);
		PhmInvoiceInfoDetail phmInvoiceInfo = phmInvoiceInfoService.getPhmInvoiceInfoOneByParams(phmInvoiceInfoVo);
		if(phmInvoiceInfo == null){
			PhmInvoiceInfo phmInvoice = new PhmInvoiceInfo();
			phmInvoice.setYm(ym);
			phmInvoice.setSettleId(settleId);
			dao.save(phmInvoice);
		}
	}
	
	/**
	 * @Title: deleteSettlementInfoFj
	 * @Description: 福建省结算删除，先验证是否存在发票登记信息，如果有则不允许删除
	 * @param settleId
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月23日 下午1:33:01
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月23日 下午1:33:01
	 * @since  1.0.0
	 */
	@Transactional
	public void deleteSettlementInfoFj(String settleId) throws Exception{
		PhmInvoiceInfoVo phmInvoiceInfoVo = new PhmInvoiceInfoVo();
		phmInvoiceInfoVo.getPhmInvoiceInfo().setSettleId(settleId);
		PhmInvoiceInfoDetail phmInvoiceInfo = phmInvoiceInfoService.getPhmInvoiceInfoOneByParams(phmInvoiceInfoVo);
		if(phmInvoiceInfo != null){
			if((phmInvoiceInfo.getFile() != null && !"".equals(phmInvoiceInfo.getFile()))
					|| (phmInvoiceInfo.getInvoiceNumber() != null && !"".equals(phmInvoiceInfo.getInvoiceNumber()))
					|| (phmInvoiceInfo.getInvoiceCode() != null && !"".equals(phmInvoiceInfo.getInvoiceCode()))
					|| (phmInvoiceInfo.getDrawer() != null && !"".equals(phmInvoiceInfo.getDrawer()))
					|| phmInvoiceInfo.getInvoiceDate() != null ){
				throw new BusinessException("该结算方案已录入发票信息，不可删除！");
			}
		}
		//根据结算id删除其下所有方案中的 用户收益信息、售电公司收益信息、批发市场购电支出明细 信息、用户结算偏差费用信息、
		//月度结算方案详情信息、所有方案、结算主表信息、发票登记信息
		smSettlementMonthService.deleteSettlementInfo(settleId);
	}
	
	/**
	 * @Title: convertRetailDetailFjList
	 * @Description: 把purchaseSchemeVoFj中的retailDetailFjList转换为consProfitList和schemeDetailList
	 * @param smPurchaseSchemeVoFj 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月22日 下午5:06:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月22日 下午5:06:20
	 * @since  1.0.0
	 */
	private void convertRetailDetailFjList(SmPurchaseSchemeVoFj purchaseSchemeVoFj) {
		List<RetailDetailFj> retailDetailFjList = purchaseSchemeVoFj.getRetailDetailFjList();
		//用户受益信息对象
		List<SmConsumerProfit> consProfitList = new ArrayList<>(retailDetailFjList.size());
		//月度结算明细信息
	    List<SmPurchaseSchemeDetail> schemeDetailList = new ArrayList<>(retailDetailFjList.size());
	    
		for (RetailDetailFj retailDetailFj : retailDetailFjList) {
			//给用户收益信息对象赋值
			SmConsumerProfit consPro = new SmConsumerProfit();
			consPro.setId(retailDetailFj.getConsProId());
			consPro.setSchemeId(purchaseSchemeVoFj.getSmPurchaseScheme().getId());
			consPro.setConsId(retailDetailFj.getConsId());
			consPro.setLcDistPq(retailDetailFj.getLcPq());
			consPro.setBidDistPq(retailDetailFj.getBidPq());
			consPro.setListedPq(retailDetailFj.getListedPq());
			consPro.setProxyPq(retailDetailFj.getProxyPq());
			consPro.setActualPq(retailDetailFj.getDeliveryPq());	//交割电量 = 实际用电量
			consPro.setDistTotalPq(retailDetailFj.getTotalPq());
			consPro.setConsDelPq(retailDetailFj.getDeliveryPq());	//交割电量(实际用电量) = 结算电量
//			consPro.settransTotalPro
//			consPro.setcompUncheckedPro
//			consPro.setconsUncheckedPro
			//用电偏差
			if(retailDetailFj.getProxyPq() != null && retailDetailFj.getDeliveryPq() != null) {
				consPro.setConsElecDev(retailDetailFj.getDeliveryPq().subtract(retailDetailFj.getProxyPq()));
			}
			consPro.setConsCheckedPro(retailDetailFj.getConsCheckedPro());
			consPro.setDeliveryPrc(retailDetailFj.getDeliveryPrc());
			consPro.setDeliveryCost(retailDetailFj.getDeliveryCost());
			consPro.setMarketizePq(retailDetailFj.getDeliveryPq());//市场化交易结算电量 = 结算电量
			consPro.setAgentProp(retailDetailFj.getAgentProp());
			consPro.setAgentAmt(retailDetailFj.getAgentAmt());
			
			consProfitList.add(consPro);
			
			//月度结算明细信息对象
			SmPurchaseSchemeDetail schemeDetail = new SmPurchaseSchemeDetail();
			schemeDetail.setId(retailDetailFj.getSchemeDetailId());
			schemeDetail.setConsId(retailDetailFj.getConsId());
			schemeDetail.setSchemeId(purchaseSchemeVoFj.getSmPurchaseScheme().getId());
			schemeDetail.setLcPq(retailDetailFj.getLcPq());
			schemeDetail.setBidPq(retailDetailFj.getBidPq());
			schemeDetail.setListedPq(retailDetailFj.getListedPq());
			schemeDetail.setProxyPq(retailDetailFj.getProxyPq());
			schemeDetail.setDeliveryPq(retailDetailFj.getDeliveryPq());
			schemeDetail.setDeliveryPrc(retailDetailFj.getDeliveryPrc());
			schemeDetail.setServiceAmt(retailDetailFj.getServiceAmt());
			schemeDetailList.add(schemeDetail);
		}
		purchaseSchemeVoFj.setSmConsumerProfitList(consProfitList);
		purchaseSchemeVoFj.setSmPurchaseSchemeDetailList(schemeDetailList);
	}
}