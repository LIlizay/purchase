package com.hhwy.purchaseweb.settlement.other.service.impl;

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

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchase.domain.PhmInvoiceInfo;
import com.hhwy.purchase.domain.SmConsumerProfit;
import com.hhwy.purchase.domain.SmPurchaseScheme;
import com.hhwy.purchase.domain.SmPurchaseSchemeDetail;
import com.hhwy.purchase.domain.SmSettlementMonth;
import com.hhwy.purchaseweb.arithmetic.balance.service.BalanceService;
import com.hhwy.purchaseweb.arithmetic.divide.service.PrcService;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.domain.PhmInvoiceInfoDetail;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.domain.PhmInvoiceInfoVo;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.service.PhmInvoiceInfoService;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain.SmSettlementMonthDetail;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.service.SmSettlementMonthService;
import com.hhwy.purchaseweb.settlement.other.domain.RetailDetailOther;
import com.hhwy.purchaseweb.settlement.other.domain.SmPurchaseSchemeVoOther;
import com.hhwy.purchaseweb.settlement.other.service.SettlementServiceOther;

/**
 * <b>类 名 称：</b>SettlementServiceOther<br/>
 * <b>类 描 述：</b><br/>		江苏、福建以外其他省的结算service实现
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年8月17日 下午5:39:57<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class SettlementServiceImplOther implements SettlementServiceOther {

	public static final Logger log = LoggerFactory.getLogger(SettlementServiceImplOther.class);
	
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
	 * phmInvoiceInfoService	发票信息service
	 */
	@Autowired
	private PhmInvoiceInfoService phmInvoiceInfoService;
	
	/**
	 * @Title: getRetailDetailByYm
	 * @Description: 根据年月获取当月“零售市场售电收入明细”列表(若当月已有结算数据，则取结算数据；若当月没有结算数据，则组装初始化的结算数据)
	 * @param ym
	 * @return
	 * @throws Exception 
	 * List<RetailDetailOther>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月17日 下午5:35:18
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月17日 下午5:35:18
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<RetailDetailOther> getRetailDetailByYm(String ym) throws Exception{
		if(ym == null || "".equals(ym)) {
			return null;
		}
		ym = ym.replaceAll("-", "");
		
		String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
		Map<String , String> params = new HashMap<>();
		params.put("ym", ym);
		params.put("loginUserId", loginUserId);
		
		//Parameter.isFilterData.set(true);
		List<RetailDetailOther> list=(List<RetailDetailOther>) dao.getBySql("settlementother.sql.getInitRetailDetailByYm", params);
		ConvertListUtil.convert(list);
		//Parameter.isFilterData.set(false);
		return list;
	}
	
	
	/**
	 * @Title: getSmSettlementMonthInfoByIdOrYm
	 * @Description: 通过结算id或者年月获取结算综合数据（包括用户数、委托电量、实际用电量、总购电量等）
	 * @param settleId
	 * @param ym
	 * @return 
	 * SmSettlementMonthDetail
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月27日 上午9:59:31
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月27日 上午9:59:31
	 * @since  1.0.0
	 */
	public SmSettlementMonthDetail getSmSettlementMonthInfoByIdOrYm(String settleId, String ym) {
		if((settleId == null||"".equals(settleId)) && (ym == null || "".equals(ym))){
			return null;
		}
		if(settleId != null && !"".equals(settleId)) {
			SmSettlementMonth settlement = getSmSettlementMonthById(settleId);
			ym = settlement.getYm();
		}else {
			ym = ym.replaceAll("-", "");
			String result = smSettlementMonthService.getSmSettlementMonthIdByYm(ym);
			if(result != null && !"".equals(result)) {
				settleId = result;
			}
		}
		String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
		Map<String , String> params = new HashMap<>();
		params.put("ym", ym);
		params.put("loginUserId", loginUserId);
		SmSettlementMonthDetail smSettlementMonthDetail = (SmSettlementMonthDetail)dao.getOneBySQL("settlementother.sql.getSmSettlementMonthInfoByIdOrYm",params);
		Parameter.isFilterData.set(true);
		SmSettlementMonthDetail profit = (SmSettlementMonthDetail) dao.getOneBySQL("settlementother.sql.getProfitInfoByYm",ym);
		Parameter.isFilterData.set(false);
		if(profit != null){
			smSettlementMonthDetail.setCompanyId(profit.getCompanyId());
			smSettlementMonthDetail.setDevDam(profit.getDevDam());
			smSettlementMonthDetail.setCompanyPro(profit.getCompanyPro());
			smSettlementMonthDetail.setConsCheckedProTotal(profit.getConsCheckedProTotal());
		}
		if(settleId != null && !"".equals(settleId) ) {
			smSettlementMonthDetail.setId(settleId);
		}
		smSettlementMonthDetail.setYm(ym.substring(0, 4) + "-" + ym.substring(4));  //yyyy-MM格式
		return smSettlementMonthDetail;
	}
	
	/**
	 * 根据ID获取对象SmSettlementMonth
	 * @param ID
	 * @return SmSettlementMonth
	 */
	public SmSettlementMonth getSmSettlementMonthById(String id) {
		return dao.findById(id, SmSettlementMonth.class);
	}	
	
	/**
	 * @Title: convertRetailDetailOtherList
	 * @Description: 把purchaseSchemeVoOther中的RetailDetailOtherList转换为smConsumerProfitList和smPurchaseSchemeDetailList
	 * @param purchaseSchemeVoOther 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月20日 上午10:23:09
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月20日 上午10:23:09
	 * @since  1.0.0
	 */
	private void convertRetailDetailOtherList(SmPurchaseSchemeVoOther purchaseSchemeVoOther) {
		List<RetailDetailOther> retailDetailOtherList = purchaseSchemeVoOther.getRetailDetailOtherList();
		//用户受益信息对象
		List<SmConsumerProfit> consProfitList = new ArrayList<>(retailDetailOtherList.size());
		//月度结算明细信息
	    List<SmPurchaseSchemeDetail> schemeDetailList = new ArrayList<>(retailDetailOtherList.size());
	    
		for (RetailDetailOther retailDetailOther : retailDetailOtherList) {
			//给用户收益信息对象赋值
			SmConsumerProfit consPro = new SmConsumerProfit();
			consPro.setId(retailDetailOther.getConsProId());
			consPro.setSchemeId(purchaseSchemeVoOther.getSmPurchaseScheme().getId());
			consPro.setConsId(retailDetailOther.getConsId());
			consPro.setLcDistPq(retailDetailOther.getLcPq());
			consPro.setBidDistPq(retailDetailOther.getBidPq());
			consPro.setListedPq(retailDetailOther.getListedPq());
			consPro.setProxyPq(retailDetailOther.getProxyPq());
			consPro.setDistTotalPq(retailDetailOther.getTotalPq());
			consPro.setConsDelPq(retailDetailOther.getDeliveryPq());	//交割电量(实际用电量) = 结算电量
//			consPro.settransTotalPro
//			consPro.setcompUncheckedPro
//			consPro.setconsUncheckedPro
			//用电偏差
			if(retailDetailOther.getProxyPq() != null && retailDetailOther.getDeliveryPq() != null) {
				consPro.setConsElecDev(retailDetailOther.getDeliveryPq().subtract(retailDetailOther.getProxyPq()));
			}
			consPro.setConsCheckedPro(retailDetailOther.getConsCheckedPro());
			consPro.setDeliveryPrc(retailDetailOther.getDeliveryPrc());
			consPro.setDeliveryCost(retailDetailOther.getDeliveryCost());
			consPro.setMarketizePq(retailDetailOther.getDeliveryPq());//市场化交易结算电量 = 结算电量
			
			consProfitList.add(consPro);
			
			//月度结算明细信息对象
			SmPurchaseSchemeDetail schemeDetail = new SmPurchaseSchemeDetail();
			schemeDetail.setId(retailDetailOther.getSchemeDetailId());
			schemeDetail.setConsId(retailDetailOther.getConsId());
			schemeDetail.setSchemeId(purchaseSchemeVoOther.getSmPurchaseScheme().getId());
			schemeDetail.setLcPq(retailDetailOther.getLcPq());
			schemeDetail.setBidPq(retailDetailOther.getBidPq());
			schemeDetail.setListedPq(retailDetailOther.getListedPq());
			schemeDetail.setProxyPq(retailDetailOther.getProxyPq());
			schemeDetail.setDeliveryPq(retailDetailOther.getDeliveryPq());
			schemeDetail.setDeliveryPrc(retailDetailOther.getDeliveryPrc());
			schemeDetail.setServiceAmt(retailDetailOther.getServiceAmt());
			schemeDetailList.add(schemeDetail);
		}
		purchaseSchemeVoOther.setSmConsumerProfitList(consProfitList);
		purchaseSchemeVoOther.setSmPurchaseSchemeDetailList(schemeDetailList);
	}
	

	/**
	  * @Title: saveOrUpdateSmPurchaseSchemeVoOther
	  * @Description: 其他省月度收益结算方案新增或修改
	  * @param smPurchaseSchemeVoOther
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年9月6日 下午2:12:52
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年9月6日 下午2:12:52
	  * @since  1.0.0
	 */
	@Override
	@Transactional
	public void saveOrUpdateSmPurchaseSchemeVoOther(SmPurchaseSchemeVoOther smPurchaseSchemeVoOther) throws Exception{
		if(smPurchaseSchemeVoOther.getYm() == null || "".equals(smPurchaseSchemeVoOther.getYm())
				|| smPurchaseSchemeVoOther.getRetailDetailOtherList() == null || smPurchaseSchemeVoOther.getRetailDetailOtherList().size() == 0) {
			throw new BusinessException("参数错误，保存失败");
		}
		String settleId = smPurchaseSchemeVoOther.getSettleId();
		String ym = smPurchaseSchemeVoOther.getYm();
		if(ym == null || "".equals(ym)) {
			throw new BusinessException("年月为空，保存失败。请刷新重试！");
		}
		ym = ym.replaceAll("-", "");
		
		//如果没有本月的结算，则新增一个结算主表信息
		if(settleId == null || "".equals(settleId)) {
			SmSettlementMonth settlement = new SmSettlementMonth();
			settlement.setYm(ym);
			smSettlementMonthService.saveSmSettlementMonth(settlement);
			settleId = settlement.getId();
		}
		
		//月度购电方案信息保存
		SmPurchaseScheme smPurchaseScheme = smPurchaseSchemeVoOther.getSmPurchaseScheme();
		smPurchaseScheme.setId(settleId);
		smPurchaseScheme.setSettleId(settleId);
		smPurchaseScheme.setCompProfit(smPurchaseSchemeVoOther.getSmCompanyProfit().getCompanyPro());
		smPurchaseScheme.setYm(ym);
		smPurchaseScheme.setSchemeStatus(1);	//每月一条记录，设置为“归档”状态
		dao.save(smPurchaseScheme);
		
		//售电公司收益信息对象保存/更新
		if(smPurchaseSchemeVoOther.getSmCompanyProfit().getId() != null && !"".equals(smPurchaseSchemeVoOther.getSmCompanyProfit().getId())){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", smPurchaseSchemeVoOther.getSmCompanyProfit().getId());
			map.put("devDam", smPurchaseSchemeVoOther.getSmCompanyProfit().getDevDam());
			map.put("consCheckedProTotal", smPurchaseSchemeVoOther.getSmCompanyProfit().getConsCheckedProTotal());
			map.put("companyPro", smPurchaseSchemeVoOther.getSmCompanyProfit().getCompanyPro());
			dao.updateBySql("settlementother.sql.updateSmComponyProfitBySchemeId", map);
		}else{
			smPurchaseSchemeVoOther.getSmCompanyProfit().setSchemeId(smPurchaseScheme.getId());
			smPurchaseSchemeVoOther.getSmCompanyProfit().setYm(ym);
			dao.save(smPurchaseSchemeVoOther.getSmCompanyProfit());
		}
		
		this.convertRetailDetailOtherList(smPurchaseSchemeVoOther);
		
		//用户受益信息对象；用于保存
		List<SmConsumerProfit> smConsumerProfitList = smPurchaseSchemeVoOther.getSmConsumerProfitList();
		dao.saveList(smConsumerProfitList);
		
		//月度结算明细信息 列表，用于保存
		List<SmPurchaseSchemeDetail> smPurchaseSchemeDetailList = smPurchaseSchemeVoOther.getSmPurchaseSchemeDetailList();
		dao.saveList(smPurchaseSchemeDetailList);
		
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
}