package com.hhwy.purchaseweb.settlement.base.smsettlementmonth.service.impl;

import java.math.BigDecimal;
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
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmDealInfo;
import com.hhwy.purchase.domain.SmPrcInfo;
import com.hhwy.purchase.domain.SmPurchaseScheme;
import com.hhwy.purchase.domain.SmSettlementMonth;
import com.hhwy.purchaseweb.arithmetic.balance.service.BalanceService;
import com.hhwy.purchaseweb.arithmetic.divide.service.PrcService;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.contract.smdistmode.service.SmDistModeService;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureDetail;
import com.hhwy.purchaseweb.delivery.smprcinfo.service.SmPrcInfoService;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.domain.SmPurchaseSchemeVo;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.service.SmPurchaseSchemeService;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain.SmSettlementMonthDetail;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain.SmSettlementMonthVo;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.service.SmSettlementMonthService;
import com.hhwy.selling.domain.ScConsumerInfo;
import com.hhwy.selling.domain.SmDistMode;

 /**
 * <b>类 名 称：</b>SmSettlementMonthServiceImpl<br/>
 * <b>类 描 述：</b><br/>		月度收益结算service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年5月26日 下午4:54:39<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class SmSettlementMonthServiceImpl implements SmSettlementMonthService {

	public static final Logger log = LoggerFactory.getLogger(SmSettlementMonthServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * smPurchaseSchemeService	月度结算方案service
	 */
	@Autowired
	private SmPurchaseSchemeService smPurchaseSchemeService;
	
	/**
	 * smDistModeService	售电合同中的分成方式service
	 */
	@Autowired
	private SmDistModeService smDistModeService;
	
	/**
	 * smPrcInfoService		电价配置信息service
	 */
	@Autowired
	private SmPrcInfoService smPrcInfoService;
	
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
	 * 分页获取对象SmSettlementMonth
	 * @param ID
	 * @return SmSettlementMonth
	 */
	public QueryResult<SmSettlementMonthDetail> getSmSettlementMonthByPage(SmSettlementMonthVo smSettlementMonthVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smSettlementMonthVo == null){
			smSettlementMonthVo = new SmSettlementMonthVo();
		}
		QueryResult<SmSettlementMonthDetail> queryResult = new QueryResult<SmSettlementMonthDetail>();
		long total = this.getSmSettlementMonthCountByParams(smSettlementMonthVo);
		List<SmSettlementMonthDetail> smSettlementMonthList = this.getSmSettlementMonthListByParams(smSettlementMonthVo);
		queryResult.setTotal(total);
		queryResult.setData(smSettlementMonthList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmSettlementMonth数量
	 * @param SmSettlementMonthVo
	 * @return Integer
	 */
	public Integer getSmSettlementMonthCountByParams(SmSettlementMonthVo smSettlementMonthVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smSettlementMonth.sql.getSmSettlementMonthCountByParams",smSettlementMonthVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmSettlementMonth列表
	 * @param SmSettlementMonthVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmSettlementMonthDetail> getSmSettlementMonthListByParams(SmSettlementMonthVo smSettlementMonthVo) throws Exception{
		Parameter.isFilterData.set(true);
		List<SmSettlementMonthDetail> smSettlementMonthList = (List<SmSettlementMonthDetail>)dao.getBySql("smSettlementMonth.sql.getSmSettlementMonthListByParams",smSettlementMonthVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smSettlementMonthList);
		return smSettlementMonthList;
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
	 * @Title: getSmSettlementMonthByYm
	 * @Description: 根据ym年月获取结算主表信息
	 * @param ym	yyyyMM格式
	 * @return 
	 * SmSettlementMonth
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年4月23日 下午2:09:34
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年4月23日 下午2:09:34
	 * @since  1.0.0
	 */
	public SmSettlementMonth getSmSettlementMonthByYm(String ym) {
		Parameter.isFilterData.set(true);
		SmSettlementMonth result = (SmSettlementMonth)dao.getOneBySQL("smSettlementMonth.sql.getSettleByYm",ym);
		Parameter.isFilterData.set(false);
		return result;
	}
	
	/**
	 * @Title: getSmSettlementMonthIdByYm
	 * @Description: 根据ym年月获取结算主表id
	 * @param ym	yyyyMM格式
	 * @return 
	 * SmSettlementMonth
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月30日 下午9:53:31
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月30日 下午9:53:31
	 * @since  1.0.0
	 */
	public String getSmSettlementMonthIdByYm(String ym) {
		SmSettlementMonth settlementMonth = this.getSmSettlementMonthByYm(ym);
		return settlementMonth == null ? null : settlementMonth.getId();
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
			SmSettlementMonth settlement = this.getSmSettlementMonthById(settleId);
			ym = settlement.getYm();
		}else {
			ym = ym.replaceAll("-", "");
			String result = this.getSmSettlementMonthIdByYm(ym);
			if(result != null && !"".equals(result)) {
				settleId = result;
			}
		}
		Parameter.isFilterData.set(true);
		SmSettlementMonthDetail smSettlementMonthDetail = (SmSettlementMonthDetail)dao.getOneBySQL("smSettlementMonth.sql.getSmSettlementMonthInfoByIdOrYm",ym);
		Parameter.isFilterData.set(false);
		if(settleId != null && !"".equals(settleId) ) {
			smSettlementMonthDetail.setId(settleId);
		}
		smSettlementMonthDetail.setYm(ym.substring(0, 4) + "-" + ym.substring(4));  //yyyy-MM格式
		return smSettlementMonthDetail;
	}
	
	/**
	 * 添加对象SmSettlementMonth
	 * @param 实体对象
	 */
	public void saveSmSettlementMonth(SmSettlementMonth smSettlementMonth) {
		dao.save(smSettlementMonth);
	}
	
	/**
	 * 添加对象SmSettlementMonth
	 * @param 实体对象
	 */
	public void saveSmSettlementMonthList(List<SmSettlementMonth> smSettlementMonthList) {
		dao.saveList(smSettlementMonthList);
	}
	
	/**
	 * 更新对象SmSettlementMonth
	 * @param 实体对象
	 */
	public void updateSmSettlementMonth(SmSettlementMonth smSettlementMonth) {
		dao.update(smSettlementMonth);
	}
	
	/**
	 * @Title: validateDdeleteSettle
	 * @Description: 先验证是否含有已归档的结算方案，如果没有则删除一个月度结算的所有相关信息(江苏)
	 * @param settleId
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月26日 下午5:49:05
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年5月26日 下午5:49:05
	 * @since  1.0.0
	 */
	@Transactional
	public void validateDeleteSettle(String settleId) throws Exception{
		//首先验证其是否含有已归档的方案，如果有则抛异常
		SmPurchaseSchemeVo params = new SmPurchaseSchemeVo();
		SmPurchaseScheme purchaseScheme = params.getSmPurchaseScheme();
		purchaseScheme.setSettleId(settleId);
		purchaseScheme.setSchemeStatus(1);
		Integer count = smPurchaseSchemeService.getSmPurchaseSchemeCountByParams(params);
		if(count > 0) {
			throw new BusinessException("此结算存在已归档方案，不能删除！");
		}
		this.deleteSettlementInfo(settleId);
	}	
	/**
	 * @Title: deleteSettlementInfo
	 * @Description: 直接根据结算id删除其下所有方案中的用户收益信息、售电公司收益信息、批发市场购电支出明细 信息、用户结算偏差费用信息、月度结算方案详情信息、所有方案、结算主表信息、
	 * 				发票登记信息（福建省可直接删，江苏省已归档方案走不到此步）
	 * @param settleId
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月26日 下午5:49:05
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年5月26日 下午5:49:05
	 * @since  1.0.0
	 */
	@Transactional
	public void deleteSettlementInfo(String settleId){
		dao.deleteBySql("smSettlementMonth.sql.deleteSettlementInfoBySettleId", settleId);
		SmSettlementMonth settlementMonth =dao.findById(settleId, SmSettlementMonth.class);
		if(settlementMonth != null && settlementMonth.getBusinessPq() == null  && settlementMonth.getIndustryPq() == null) {
			dao.delete(settlementMonth);
		}
	}	
	
	/**
	 * @Title: calculateDeliveryPrc
	 * @Description: 计算单个用户的 结算电价（山西辽宁 的成交信息录入页面用到）,以后需要提到山西辽宁的结算中
	 * @param List<PhmDealInfo> ,其中consId, ym, dealPrc三个属性有用，结果存在 deliveryPrc属性中
	 * 				tradeMode  交易方式(01：集中竞价，02：双边协商，03：挂牌)
	 * @return  void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年7月2日 下午4:30:07
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年7月2日 下午4:30:07
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public void calculateDeliveryPrc(List<PhmDealInfo> dealInfoList, String tradeMode) throws Exception {
		String ym = dealInfoList.get(0).getYm();
		
		//获取交易规则配置，主要是获取  燃煤机组标杆电价
		SmCalcConfigureDetail smCalcConfigureDetail = balanceService.getSmCalcConfigureDetail();
		BigDecimal modelPrc = BigDecimal.ZERO;	//燃煤标杆电价
		if(smCalcConfigureDetail != null){
			String json = smCalcConfigureDetail.getCalcParam();
			if(json != null && !json.equals("")){
				Map<String, Object> calcConfigureJsonMap = JSON.parseObject(json);
				if(calcConfigureJsonMap.get("modelPrc") != null) {
					modelPrc = new BigDecimal(calcConfigureJsonMap.get("modelPrc").toString());
				}
			}
		}
		
		//实际用的分成基准值，（有 1.市场分成基准值，2.用户手填基准值，3.标杆电价 三种取值可能）
		BigDecimal actualDiviPrc = modelPrc;
		
		
		for (PhmDealInfo info : dealInfoList) {
			Parameter.isFilterData.set(true);
			SmDistMode distMode = smDistModeService.getSmDistModeOneByConsIdAndYm(info.getConsId(), ym);
			Parameter.isFilterData.set(false);
			if(distMode == null){
				info.setDeliveryPrc(BigDecimal.ZERO);
				continue;
			}
			String profitMode = distMode.getProfitMode();
			if(profitMode == null || "".equals(profitMode)) {	//若合同中没有“收益模式”（PROFIT_FIRST_CODE01 = "01"：甲方收益最大，PROFIT_FIRST_CODE02 = "02"：乙方收益最大）
				profitMode = BusinessContants.PROFIT_FIRST_CODE01;	//默认甲方收益最大
			}
			if(ym.length() == 4) {
				ym = ym + "01";
			}
			
			//市场 分成基准值：= 目录-（输配+代征） = 电度电价-输配电价-政府性基金和代征；
			//首先获取用户电压等级的电价去计算，如果没有，则获取10kV的平时电价的值来计算
			BigDecimal diviValue = null;
			//获取用户信息，用于获取用户地区的电价信息
			ScConsumerInfo cons = dao.findById(info.getConsId(), ScConsumerInfo.class);
			//获取当前用户所在地区的电价信息
			List<SmPrcInfo> prcInfoList = smPrcInfoService.getSmPrcInfoListByParams(cons.getProvinceCode(), cons.getCityCode(), cons.getCountyCode() , 
					ym.substring(ym.length()-2), cons.getVoltCode());
			if(prcInfoList != null && prcInfoList.size() > 0) {
				SmPrcInfo prcInfo = prcInfoList.get(0);
				if(prcInfo.getCataPlainPrc() == null ) {
					prcInfoList = smPrcInfoService.getSmPrcInfoListByParams(cons.getProvinceCode(), cons.getCityCode(), cons.getCountyCode() , 
							ym.substring(ym.length()-2), "AC00102");//获取指定地区的10kV电价信息
					if(prcInfoList != null && prcInfoList.size() > 0) {
						prcInfo = prcInfoList.get(0);
						if(prcInfo.getCataPlainPrc() != null ) {
							diviValue = prcInfo.getCataPlainPrc().subtract(prcInfo.getTransPrc() == null ? BigDecimal.ZERO : prcInfo.getTransPrc());
						}
					}
				}else {
					diviValue = prcInfo.getCataPlainPrc().subtract(prcInfo.getTransPrc() == null ? BigDecimal.ZERO : prcInfo.getTransPrc());
				}
			}
			
			//结算电价
			BigDecimal deliveryPrc = null;
			
			//tradeMode  交易方式(01：集中竞价，02：双边协商，03：挂牌)
			if("02".equals(tradeMode)) {	//双边
				if(info.getDealPrc() != null) {
					//如果双边的分成方式为：06：代理服务费 ,则双边的结算电价 = 双边加权平均价
					if("06".equals(distMode.getDiviCode())) {
						deliveryPrc = info.getDealPrc();
					}else if("03".equals(distMode.getDiviCode()) || "04".equals(distMode.getDiviCode())){		
						//如果双边的分成方式为：03：分成  或者  04：保底或分成  ,则 应该以分成基准值 当作标杆电价去加权结算电价
						actualDiviPrc = distMode.getDiviValue();
						if(actualDiviPrc == null && diviValue != null) {
							actualDiviPrc = diviValue;
						}else {
							actualDiviPrc = modelPrc;
						}
						deliveryPrc = jSPrcService.getPricePrc(distMode.getDiviCode(), actualDiviPrc, 
								distMode.getAgrePrc(), distMode.getPartyALcProp(), info.getDealPrc(), profitMode);
					}else {
						deliveryPrc = jSPrcService.getPricePrc(distMode.getDiviCode(), modelPrc, 
								distMode.getAgrePrc(), distMode.getPartyALcProp(), info.getDealPrc(), profitMode);
					}
				}
			}else {		//竞价或者挂牌
				//如果竞价的分成方式为：06：代理服务费  ,则竞价的结算电价 = 竞价加权平均价 , 挂牌的结算电价 = 挂牌加权平均价
				if("06".equals(distMode.getBidDiviCode())) {
					deliveryPrc = info.getDealPrc();
				}else if("03".equals(distMode.getBidDiviCode()) || "04".equals(distMode.getBidDiviCode())){		
					//如果竞价的分成方式为：03：分成  或者  04：保底或分成  ,则 应该以分成基准值 当作标杆电价去甲酸结算电价
					actualDiviPrc = distMode.getBidDiviValue();
					if(actualDiviPrc == null && diviValue != null) {
						actualDiviPrc = diviValue;
					}else {
						actualDiviPrc = modelPrc;
					}
					
					deliveryPrc = jSPrcService.getPricePrc(distMode.getBidDiviCode(), actualDiviPrc, 
							distMode.getBidAgrePrc(), distMode.getPartyABidProp(), info.getDealPrc(), profitMode);
				}else {
					deliveryPrc = jSPrcService.getPricePrc(distMode.getBidDiviCode(), modelPrc, 
							distMode.getBidAgrePrc(), distMode.getPartyABidProp(), info.getDealPrc(), profitMode);
				}	
			}
			info.setDeliveryPrc(deliveryPrc);
		}
	}
}