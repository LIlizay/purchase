package com.hhwy.purchaseweb.crm.ssagrescheme.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hhwy.business.core.exception.BusinessException;
import com.hhwy.business.core.framework.service.SequenceTool;
import com.hhwy.business.core.sqlfilter.CompanyDomainInfoUtil;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmPrcInfo;
import com.hhwy.purchase.domain.SsAgreScheme;
import com.hhwy.purchaseweb.arithmetic.balance.service.BalanceService;
import com.hhwy.purchaseweb.arithmetic.common.service.CommonService;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureDetail;
import com.hhwy.purchaseweb.crm.ssagrescheme.domain.SmPpaDetail;
import com.hhwy.purchaseweb.crm.ssagrescheme.domain.SmPpaVo;
import com.hhwy.purchaseweb.crm.ssagrescheme.domain.SsAgreSchemeDetail;
import com.hhwy.purchaseweb.crm.ssagrescheme.domain.SsAgreSchemeVo;
import com.hhwy.purchaseweb.crm.ssagrescheme.service.SsAgreSchemeService;
import com.hhwy.purchaseweb.delivery.smprcinfo.service.SmPrcInfoService;
import com.hhwy.selling.domain.SmAgrePunishComp;
import com.hhwy.selling.domain.SmAgrePunishCons;
import com.hhwy.selling.domain.SmDistMode;

/**
 * （合同辅助设计）SsAgreSchemeService
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SsAgreSchemeServiceImpl implements SsAgreSchemeService {

	public static final Logger log = LoggerFactory.getLogger(SsAgreSchemeServiceImpl.class);

	@Autowired
	DAO<?> dao;

	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

	@Autowired
	@Qualifier("DValueBalanceService")
	private BalanceService dvalueBalanceService;

	@Autowired
	@Qualifier("cloudSellingCommonService")
	private CommonService commonService;
	
	@Autowired
	private SmPrcInfoService smPrcInfoService;
	
	
	
	/**
	 * @Title: getSsAgreSchemeByPage<br/>
	 * @Description: 分页获取合同辅助设计对象<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception
	 * <br/>
	 *             QueryResult<SsAgreSchemeDetail><br/>
	 *             <b>创 建 人：</b>chengpeng<br/>
	 *             <b>创建时间:</b>2017年5月23日 下午2:02:10 <b>修 改 人：</b>chengpeng<br/>
	 *             <b>修改时间:</b>2017年5月23日 下午2:02:10
	 * @since 1.0.0
	 */
	public QueryResult<SsAgreSchemeDetail> getSsAgreSchemeByPage(SsAgreSchemeVo ssAgreSchemeVo) throws Exception {
		QueryResult<SsAgreSchemeDetail> queryResult = new QueryResult<SsAgreSchemeDetail>();
		long total = getNewSchemeNameSsAgreSchemeCountByParams(ssAgreSchemeVo);
		List<SsAgreSchemeDetail> ssAgreSchemeList = getSByPage(ssAgreSchemeVo);
		queryResult.setTotal(total);
		queryResult.setData(ssAgreSchemeList);
		return queryResult;
	}

	/**
	 * @Title: getTreeSsAgreSchemeByPage<br/>
	 * @Description: 分页获取合同辅助设计对象(树表格)<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception
	 * <br/>
	 *             QueryResult<SsAgreSchemeDetail><br/>
	 *             <b>创 建 人：</b>chengpeng<br/>
	 *             <b>创建时间:</b>2017年5月23日 下午2:10:43 <b>修 改 人：</b>chengpeng<br/>
	 *             <b>修改时间:</b>2017年5月23日 下午2:10:43
	 * @since 1.0.0
	 */
	public QueryResult<SsAgreSchemeDetail> getTreeSsAgreSchemeByPage(SsAgreSchemeVo ssAgreSchemeVo) throws Exception {
		QueryResult<SsAgreSchemeDetail> queryResult = new QueryResult<SsAgreSchemeDetail>();
		List<SsAgreSchemeDetail> ssAgreSchemeList;
		long total = getNewSchemeNameSsAgreSchemeCountByParams(ssAgreSchemeVo); // 查询条数
		if (ssAgreSchemeVo.getId() == null || ssAgreSchemeVo.getId() == "") { // 查询根节点
			ssAgreSchemeList = getNewSchemeNameSsAgreSchemeListByParams(ssAgreSchemeVo);
			for (int i=0; i<ssAgreSchemeList.size(); i++) {
				SsAgreSchemeDetail ssAgreSchemeDetail = ssAgreSchemeList.get(i);
				Integer count = getCountByConsId(ssAgreSchemeDetail.getConsId());
				if (count.intValue() > 1) {
					ssAgreSchemeDetail.setState("closed");
				}
			}
		} else {// 查询子节点
			ssAgreSchemeList = getSsAgreSchemeById(ssAgreSchemeVo);
			for (SsAgreSchemeDetail ssAgreSchemeDetail : ssAgreSchemeList) {
				ssAgreSchemeDetail.set_parentId(ssAgreSchemeVo.getId());
			}
		}
		queryResult.setTotal(total);
		queryResult.setData(ssAgreSchemeList);
		return queryResult;
	}

	/**
	 * @Title: getSsAgreSchemeListByParams<br/>
	 * @Description: 根据查询条件获取对象SsAgreScheme列表<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception
	 * <br/>
	 *             List<SsAgreSchemeDetail><br/>
	 *             <b>创 建 人：</b>chengpeng<br/>
	 *             <b>创建时间:</b>2017年5月23日 下午2:32:13 <b>修 改 人：</b>chengpeng<br/>
	 *             <b>修改时间:</b>2017年5月23日 下午2:32:13
	 * @since 1.0.0
	 */
	public Integer getSsAgreSchemeCountByParams(SsAgreSchemeVo ssAgreSchemeVo) {
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("ssAgreScheme.sql.getSsAgreSchemeCountByParams",ssAgreSchemeVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	/**
	 * @Title: getSsAgreSchemeCountByParams<br/>
	 * @Description: 根据查询条件获取对象SsAgreScheme数量<br/>
	 * @param ssAgreSchemeVo
	 * @return <br/>
	 *         Integer<br/>
	 *         <b>创 建 人：</b>chengpeng<br/>
	 *         <b>创建时间:</b>2017年5月23日 下午2:32:30 <b>修 改 人：</b>chengpeng<br/>
	 *         <b>修改时间:</b>2017年5月23日 下午2:32:30
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SsAgreSchemeDetail> getSsAgreSchemeListByParams(SsAgreSchemeVo ssAgreSchemeVo) throws Exception {
		// 当Vo为空时,初始化Vo对象,应用分页参数
		if (ssAgreSchemeVo == null) {
			ssAgreSchemeVo = new SsAgreSchemeVo();
		}
		Parameter.isFilterData.set(true);
		List<SsAgreSchemeDetail> ssAgreSchemeList = (List<SsAgreSchemeDetail>) dao.getBySql("ssAgreScheme.sql.getSsAgreSchemeListByParams",ssAgreSchemeVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(ssAgreSchemeList);
		return ssAgreSchemeList;
	}

	/**
	 * @Title: getSsAgreSchemeOneByParams<br/>
	 * @Description: 根据查询条件获取单个对象SsAgreScheme<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception
	 * <br/>
	 *             SsAgreSchemeDetail<br/>
	 *             <b>创 建 人：</b>chengpeng<br/>
	 *             <b>创建时间:</b>2017年5月23日 下午2:32:47 <b>修 改 人：</b>chengpeng<br/>
	 *             <b>修改时间:</b>2017年5月23日 下午2:32:47
	 * @since 1.0.0
	 */
	public SsAgreSchemeDetail getSsAgreSchemeOneByParams(SsAgreSchemeVo ssAgreSchemeVo) throws Exception {
		SsAgreSchemeDetail ssAgreScheme = null;
		List<SsAgreSchemeDetail> ssAgreSchemeList = getSsAgreSchemeListByParams(ssAgreSchemeVo);
		if (ssAgreSchemeList != null && ssAgreSchemeList.size() > 0) {
			ssAgreScheme = ssAgreSchemeList.get(0);
		}
		return ssAgreScheme;
	}

	/**
	 * @Title: getSsAgreSchemeById<br/>
	 * @Description: 根据ID获取对象SsAgreScheme<br/>
	 * @param id
	 * @return <br/>
	 *         SsAgreScheme<br/>
	 *         <b>创 建 人：</b>chengpeng<br/>
	 *         <b>创建时间:</b>2017年5月23日 下午2:33:00 <b>修 改 人：</b>chengpeng<br/>
	 *         <b>修改时间:</b>2017年5月23日 下午2:33:00
	 * @since 1.0.0
	 */
	public SsAgreScheme getSsAgreSchemeById(String id) {
		return dao.findById(id, SsAgreScheme.class);
	}

	/**
	 * @Title: saveSsAgreScheme<br/>
	 * @Description: 添加对象SsAgreScheme<br/>
	 * @param ssAgreScheme
	 * <br/>
	 *            void<br/>
	 *            <b>创 建 人：</b>chengpeng<br/>
	 *            <b>创建时间:</b>2017年5月23日 下午2:33:31 <b>修 改 人：</b>chengpeng<br/>
	 *            <b>修改时间:</b>2017年5月23日 下午2:33:31
	 * @since 1.0.0
	 */
	public void saveSsAgreScheme(SsAgreScheme ssAgreScheme) {
		ssAgreScheme.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(ssAgreScheme);
	}

	/**
	 * @Title: saveSsAgreSchemeList<br/>
	 * @Description: 添加对象SsAgreScheme列表<br/>
	 * @param ssAgreSchemeList
	 * <br/>
	 *            void<br/>
	 *            <b>创 建 人：</b>chengpeng<br/>
	 *            <b>创建时间:</b>2017年5月23日 下午2:33:44 <b>修 改 人：</b>chengpeng<br/>
	 *            <b>修改时间:</b>2017年5月23日 下午2:33:44
	 * @since 1.0.0
	 */
	public void saveSsAgreSchemeList(List<SsAgreScheme> ssAgreSchemeList) {
		for (SsAgreScheme ssAgreScheme : ssAgreSchemeList) {
			ssAgreScheme.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(ssAgreSchemeList);
	}

	/**
	 * @Title: updateSsAgreScheme<br/>
	 * @Description: 更新对象SsAgreScheme<br/>
	 * @param ssAgreScheme
	 * <br/>
	 *            void<br/>
	 *            <b>创 建 人：</b>chengpeng<br/>
	 *            <b>创建时间:</b>2017年5月23日 下午2:33:57 <b>修 改 人：</b>chengpeng<br/>
	 *            <b>修改时间:</b>2017年5月23日 下午2:33:57
	 * @since 1.0.0
	 */
	public void updateSsAgreScheme(SsAgreScheme ssAgreScheme) {
		dao.update(ssAgreScheme);
	}

	/**
	 * @Title: deleteSsAgreScheme<br/>
	 * @Description: 删除对象SsAgreScheme<br/>
	 * @param ids
	 * <br/>
	 *            void<br/>
	 *            <b>创 建 人：</b>chengpeng<br/>
	 *            <b>创建时间:</b>2017年5月23日 下午2:34:09 <b>修 改 人：</b>chengpeng<br/>
	 *            <b>修改时间:</b>2017年5月23日 下午2:34:09
	 * @since 1.0.0
	 */
	public void deleteSsAgreScheme(String[] ids) {
		dao.delete(ids, SsAgreScheme.class);
	}

	/**
	  * @Title: calculate
	  * @Description: 计算方案服务
	  * @param ssAgreSchemeDetail
	  * @param transPrc
	  * @param cataPlainPrc
	  * @param kwhPlainPrc
	  * @throws Exception
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月24日 上午9:46:03
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月24日 上午9:46:03
	  * @since  1.0.0
	 */
	public void calculate(SsAgreSchemeDetail ssAgreSchemeDetail)throws Exception {
		//获取电价信息
		SmPrcInfo smPrc = smPrcInfoService.getSmPrcInfoByConsId(ssAgreSchemeDetail.getConsId());
		if(smPrc == null || smPrc.getCataPlainPrc() == null || smPrc.getTransPrc() == null || smPrc.getKwhPlainPrc() == null){
			throw new BusinessException("没有维护电价信息，请联系系统管理员！");
		}
		/* transPrc：输配电价		cataPlainPrc：目录平时电价		kwhPlainPrc 电度平时电价
		 * 分成方式 diviCode ：01——保底		03——分成		04——保底或分成		05——保底加分成
		 * 结算模式 settlementMode ： 01—— 价差		02 —— 电价	
		 * 收益模式：profitMode ： 01 —— 甲方最大（用户）	02 —— 乙方最大（售电公司）
		 */
		BigDecimal transPrc = smPrc.getTransPrc();
		BigDecimal cataPlainPrc = smPrc.getCataPlainPrc();
		BigDecimal delPq = ssAgreSchemeDetail.getDelPq();				//实际用电量
		BigDecimal lcPq = ssAgreSchemeDetail.getLcPq();					//分配双边电量
		BigDecimal lcPrc = ssAgreSchemeDetail.getLcPrc();				//双边电价
		BigDecimal bidPq = ssAgreSchemeDetail.getBidPq();				//竞价成交电量
		BigDecimal bidPrc = ssAgreSchemeDetail.getBidPrc(); 			//竞价电价
		BigDecimal ratio = delPq.divide(lcPq.add(bidPq),7,4);
		BigDecimal lcPqRatio = lcPq.min(lcPq.multiply(ratio));			//双边比例电量
		BigDecimal bidPqRatio = bidPq.min(bidPq.multiply(ratio));		//竞价比例电量
				
		if("01".equals(ssAgreSchemeDetail.getSettlementMode())){	//价差模式
			switch(ssAgreSchemeDetail.getDiviCode()){
				case "01" :{ /****保底+价差****/
					BigDecimal agrePrc = ssAgreSchemeDetail.getAgrePrc();			//保底协议价差
					// ============= 售电公司购电收益 ===========
					BigDecimal compDealProfit = compAgreProfit(cataPlainPrc,transPrc,agrePrc,lcPrc,lcPqRatio,bidPrc,bidPqRatio);
					ssAgreSchemeDetail.setTransTotalPro(compDealProfit);
					//============== 用户返还电费 ==============
					BigDecimal backPq = delPq.min(lcPq.add(bidPq));
					BigDecimal consPro = agrePrc.multiply(backPq);
					ssAgreSchemeDetail.setConsPro(consPro);
					break;
				}
				case "03" :{ /****分成+价差****/
					Map<String,BigDecimal> map = divPriceCalculate(ssAgreSchemeDetail, transPrc, cataPlainPrc);
					ssAgreSchemeDetail.setTransTotalPro(map.get("compDealProfit"));			//售电公司购电收益
					ssAgreSchemeDetail.setConsPro(map.get("consPro"));						//用户返还电费
					break;
				}
				case "04" :{ /****保底或分成+价差****/
					BigDecimal agrePrc = ssAgreSchemeDetail.getAgrePrc();								//保底协议价差
					Map<String,BigDecimal> map = divPriceCalculate(ssAgreSchemeDetail, transPrc, cataPlainPrc);
					if("01".equals(ssAgreSchemeDetail.getProfitMode())){ //甲方（用户）收益最大
						BigDecimal agreBack = agrePrc.multiply(delPq.min(lcPq.add(bidPq)));				//用户保底返还电费
						BigDecimal partyBack = map.get("consPro");										//用户分成返还电费
						//=============== 用户返还电费 ==============
						BigDecimal consPro = agreBack.max(partyBack);
						ssAgreSchemeDetail.setConsPro(consPro);
						// ============= 售电公司购电收益 ===========
						if(agreBack.compareTo(partyBack) == 1){ //采用保底模式
							BigDecimal compDealProfit = compAgreProfit(cataPlainPrc,transPrc,agrePrc,lcPrc,lcPqRatio,bidPrc,bidPqRatio);
							ssAgreSchemeDetail.setTransTotalPro(compDealProfit);
						}else{ //采用分成模式
							ssAgreSchemeDetail.setTransTotalPro(map.get("compDealProfit"));	
						}
					}else{ //乙方（售电公司）收益最大
						BigDecimal agreComProfit = compAgreProfit(cataPlainPrc,transPrc,agrePrc,lcPrc,lcPqRatio,bidPrc,bidPqRatio);		//售电公司保底购电收益
						BigDecimal partyComProfit = map.get("compDealProfit"); 							//售电公司分成购电收益
						// ============= 售电公司购电收益 ===========
						BigDecimal compDealProfit = agreComProfit.max(partyComProfit);
						ssAgreSchemeDetail.setTransTotalPro(compDealProfit);
						//=============== 用户返还电费 ==============
						if(agreComProfit.compareTo(partyComProfit) == 1){ //采用保底模式
							BigDecimal backPq = delPq.min(lcPq.add(bidPq));
							BigDecimal consPro = agrePrc.multiply(backPq);
							ssAgreSchemeDetail.setConsPro(consPro);
						}else{ //采用分成模式
							ssAgreSchemeDetail.setConsPro(map.get("consPro"));
						}
					}
					break;
				}
				case "05" :{ /****保底加分成+价差****/
					BigDecimal agrePrc = ssAgreSchemeDetail.getAgrePrc();					//保底协议价差
					BigDecimal partyBLcProp = ssAgreSchemeDetail.getPartyBLcProp().divide(BigDecimal.valueOf(100));					//售方双边分成比例
					BigDecimal partyBBidProp = ssAgreSchemeDetail.getPartyBBidProp().divide(BigDecimal.valueOf(100));				//售方竞价分成比例
					BigDecimal partyALcProp = ssAgreSchemeDetail.getPartyALcProp().divide(BigDecimal.valueOf(100));					//用户双边分成比例
					BigDecimal partyABidProp = ssAgreSchemeDetail.getPartyABidProp().divide(BigDecimal.valueOf(100));				//用户竞价分成比例
					BigDecimal lcProfit = cataPlainPrc.subtract(transPrc).subtract(lcPrc).multiply(lcPq);
					BigDecimal bdProfit = cataPlainPrc.subtract(transPrc).subtract(bidPrc).multiply(bidPq);
					BigDecimal profit = lcProfit.add(bdProfit).divide(lcPq.add(bidPq),2,4);
					if(profit.compareTo(agrePrc) == 1){
						// ============= 售电公司购电收益 ===========
						BigDecimal lcpProfit = cataPlainPrc.subtract(transPrc).subtract(lcPrc).subtract(agrePrc).multiply(lcPqRatio).multiply(partyBLcProp);	//双边比例电量收益
						BigDecimal bidProfit = cataPlainPrc.subtract(transPrc).subtract(bidPrc).subtract(agrePrc).multiply(bidPqRatio).multiply(partyBBidProp);	//竞价比例电量收益
						BigDecimal compDealProfit = lcpProfit.add(bidProfit);
						ssAgreSchemeDetail.setTransTotalPro(compDealProfit);
						//============= 用户返还电费 ===========
						BigDecimal agreBack = agrePrc.multiply(delPq.min(lcPq.add(bidPq)));			//用户保底返还电费
						BigDecimal lcpBack = cataPlainPrc.subtract(transPrc).subtract(lcPrc).subtract(agrePrc).multiply(lcPqRatio).multiply(partyALcProp);		//用户双边电量返还
						BigDecimal bidBack = cataPlainPrc.subtract(transPrc).subtract(bidPrc).subtract(agrePrc).multiply(bidPqRatio).multiply(partyABidProp);	//用户竞价电量返还
						BigDecimal consPro = agreBack.add(lcpBack).add(bidBack);
						ssAgreSchemeDetail.setConsPro(consPro);
					}else{
						// ============= 售电公司购电收益 ===========
						BigDecimal lcpProfit = cataPlainPrc.subtract(transPrc).subtract(lcPrc).subtract(agrePrc).multiply(lcPqRatio);	//双边比例电量收益
						BigDecimal bidProfit = cataPlainPrc.subtract(transPrc).subtract(bidPrc).subtract(agrePrc).multiply(bidPqRatio);	//竞价比例电量收益
						BigDecimal compDealProfit = lcpProfit.add(bidProfit);
						ssAgreSchemeDetail.setTransTotalPro(compDealProfit);
						//============= 用户返还电费 ===========
						BigDecimal agreBack = agrePrc.multiply(delPq.min(lcPq.add(bidPq)));			//用户保底返还电费
						ssAgreSchemeDetail.setConsPro(agreBack);
					}
					break;
				}
			}
		}else{	//电价模式
			switch(ssAgreSchemeDetail.getDiviCode()){
				case "01" :{ /****保底+电价****/
					BigDecimal agrePrc = ssAgreSchemeDetail.getAgrePrc();					//保底协议价差
					// ============= 售电公司购电收益 ===========
					BigDecimal lcpProfit = agrePrc.subtract(lcPrc).multiply(lcPqRatio);		//双边比例电量收益
					BigDecimal bidProfit = agrePrc.subtract(bidPrc).multiply(bidPqRatio);	//竞价比例电量收益
					BigDecimal compDealProfit = lcpProfit.add(bidProfit);
					ssAgreSchemeDetail.setTransTotalPro(compDealProfit);
					// ============== 用户返还电费 ==============
					BigDecimal backPq = delPq.min(lcPq.add(bidPq));							//用户返还电量
					BigDecimal consPro = cataPlainPrc.subtract(transPrc).subtract(agrePrc).multiply(backPq);
					ssAgreSchemeDetail.setConsPro(consPro);
					break;
				}
				case "03" :{ /****分成+电价****/
					Map<String,BigDecimal> map = divPriceCalculate(ssAgreSchemeDetail, transPrc, cataPlainPrc);
					ssAgreSchemeDetail.setTransTotalPro(map.get("compDealProfit"));			//售电公司购电收益
					ssAgreSchemeDetail.setConsPro(map.get("consPro"));						//用户返还电费
					break;
				}
				case "04" :{ /****保底或分成+电价****/
					BigDecimal agrePrc = ssAgreSchemeDetail.getAgrePrc();								//保底协议价差
					Map<String,BigDecimal> map = divPriceCalculate(ssAgreSchemeDetail, transPrc, cataPlainPrc);
					if("01".equals(ssAgreSchemeDetail.getProfitMode())){ //甲方（用户）收益最大
						BigDecimal backPq = delPq.min(lcPq.add(bidPq));	 //用户返还电量
						BigDecimal agreBack = cataPlainPrc.subtract(transPrc).subtract(agrePrc).multiply(backPq);		//用户保底返还电费
						BigDecimal partyBack = map.get("consPro");														//用户分成返还电费
						//=============== 用户返还电费 ==============
						BigDecimal consPro = agreBack.max(partyBack);
						ssAgreSchemeDetail.setConsPro(consPro);
						// ============= 售电公司购电收益 ===========
						if(agreBack.compareTo(partyBack) == 1){ //采用保底模式
							BigDecimal lcpProfit = agrePrc.subtract(lcPrc).multiply(lcPqRatio);		//双边比例电量收益
							BigDecimal bidProfit = agrePrc.subtract(bidPrc).multiply(bidPqRatio);	//竞价比例电量收益
							BigDecimal compDealProfit = lcpProfit.add(bidProfit);
							ssAgreSchemeDetail.setTransTotalPro(compDealProfit);
						}else{ //采用分成模式
							ssAgreSchemeDetail.setTransTotalPro(map.get("compDealProfit"));	
						}
					}else{ //乙方（售电公司）收益最大
						BigDecimal lcpProfit = agrePrc.subtract(lcPrc).multiply(lcPqRatio);		//双边比例电量收益
						BigDecimal bidProfit = agrePrc.subtract(bidPrc).multiply(bidPqRatio);	//竞价比例电量收益
						BigDecimal agreComProfit = lcpProfit.add(bidProfit);					//售电公司保底购电收益
						BigDecimal partyComProfit = map.get("compDealProfit"); 					//售电公司分成购电收益
						// ============= 售电公司购电收益 ===========
						BigDecimal compDealProfit = agreComProfit.max(partyComProfit);
						ssAgreSchemeDetail.setTransTotalPro(compDealProfit);
						//=============== 用户返还电费 ==============
						if(agreComProfit.compareTo(partyComProfit) == 1){ //采用保底模式
							BigDecimal backPq = delPq.min(lcPq.add(bidPq));		//用户返还电量
							BigDecimal consPro = cataPlainPrc.subtract(transPrc).subtract(agrePrc).multiply(backPq);
							ssAgreSchemeDetail.setConsPro(consPro);
						}else{ //采用分成模式
							ssAgreSchemeDetail.setConsPro(map.get("consPro"));
						}
					}
					break;
				}
				case "05" :{ /****保底加分成+电价****/
					BigDecimal agrePrc = ssAgreSchemeDetail.getAgrePrc();					//保底协议价差
					BigDecimal partyBLcProp = ssAgreSchemeDetail.getPartyBLcProp().divide(BigDecimal.valueOf(100));					//售方双边分成比例
					BigDecimal partyBBidProp = ssAgreSchemeDetail.getPartyBBidProp().divide(BigDecimal.valueOf(100));				//售方竞价分成比例
					BigDecimal partyALcProp = ssAgreSchemeDetail.getPartyALcProp().divide(BigDecimal.valueOf(100));					//用户双边分成比例
					BigDecimal partyABidProp = ssAgreSchemeDetail.getPartyABidProp().divide(BigDecimal.valueOf(100));				//用户竞价分成比例
					BigDecimal profit = lcPrc.multiply(lcPq).add(bidPrc.multiply(bidPq)).divide(lcPq.add(bidPq),2,4);
					if(profit.compareTo(agrePrc) == 1){
						// ============= 售电公司购电收益 ===========
						BigDecimal lcpProfit = agrePrc.subtract(lcPrc).multiply(lcPqRatio).multiply(partyBLcProp);		//双边比例电量收益
						BigDecimal bidProfit = agrePrc.subtract(bidPrc).multiply(bidPqRatio).multiply(partyBBidProp);	//竞价比例电量收益
						BigDecimal compDealProfit = lcpProfit.add(bidProfit);
						ssAgreSchemeDetail.setTransTotalPro(compDealProfit);
						//============= 用户返还电费 ===========
						BigDecimal agreBack = agrePrc.multiply(delPq.min(lcPq.add(bidPq)));								//用户保底返还电费
						BigDecimal lcpBack = agrePrc.subtract(lcPrc).multiply(lcPqRatio).multiply(partyALcProp);		//用户双边电量返还
						BigDecimal bidBack = agrePrc.subtract(bidPrc).multiply(bidPqRatio).multiply(partyABidProp);		//用户竞价电量返还
						BigDecimal consPro = agreBack.add(lcpBack).add(bidBack);
						ssAgreSchemeDetail.setConsPro(consPro);
					}else{
						// ============= 售电公司购电收益 ===========
						BigDecimal lcpProfit = agrePrc.subtract(lcPrc).multiply(lcPqRatio);			//双边比例电量收益
						BigDecimal bidProfit = agrePrc.subtract(bidPrc).multiply(bidPqRatio);		//竞价比例电量收益
						BigDecimal compDealProfit = lcpProfit.add(bidProfit);
						ssAgreSchemeDetail.setTransTotalPro(compDealProfit);
						//============= 用户返还电费 ===========
						BigDecimal agreBack = cataPlainPrc.subtract(transPrc).subtract(agrePrc).multiply(delPq.min(lcPq.add(bidPq)));			//用户保底返还电费
						ssAgreSchemeDetail.setConsPro(agreBack);
					}
					break;
				}
			}
			
		}
		//用户考核电费 + 售电公司赔偿费用
		getCheckedPro(ssAgreSchemeDetail);
		//售电公司代理收益: 售电公司购电收益 - 公司赔偿费用
		BigDecimal compProfit = ssAgreSchemeDetail.getTransTotalPro().subtract(ssAgreSchemeDetail.getCompensateAmt());
		ssAgreSchemeDetail.setSellTotalPro(compProfit);
	}

	/**
	  * @Title: divPriceCalculate
	  * @Description: 分成计算
	  * @param ssAgreSchemeDetail
	  * @param transPrc
	  * @param cataPlainPrc
	  * @return Map<String,BigDecimal>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月24日 下午6:22:33
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月24日 下午6:22:33
	  * @since  1.0.0
	 */
	private Map<String, BigDecimal> divPriceCalculate(SsAgreSchemeDetail ssAgreSchemeDetail, BigDecimal transPrc,BigDecimal cataPlainPrc) {
		Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
		BigDecimal delPq = ssAgreSchemeDetail.getDelPq();				//实际用电量
		BigDecimal lcPq = ssAgreSchemeDetail.getLcPq();					//分配双边电量
		BigDecimal lcPrc = ssAgreSchemeDetail.getLcPrc();				//双边电价
		BigDecimal bidPq = ssAgreSchemeDetail.getBidPq();				//竞价成交电量
		BigDecimal bidPrc = ssAgreSchemeDetail.getBidPrc(); 			//竞价电价
		BigDecimal ratio = delPq.divide(lcPq.add(bidPq),7,4);
		BigDecimal lcPqRatio = lcPq.min(lcPq.multiply(ratio));			//双边比例电量
		BigDecimal bidPqRatio = bidPq.min(bidPq.multiply(ratio));		//竞价比例电量
		BigDecimal lcProfit = cataPlainPrc.subtract(transPrc).subtract(lcPrc).multiply(lcPq);
		BigDecimal bdProfit = cataPlainPrc.subtract(transPrc).subtract(bidPrc).multiply(bidPq);
		BigDecimal profit = lcProfit.add(bdProfit).divide(lcPq.add(bidPq),2,4);
		if(profit.compareTo(BigDecimal.ZERO) == 1){
			BigDecimal partyBLcProp = ssAgreSchemeDetail.getPartyBLcProp().divide(BigDecimal.valueOf(100));					//售方双边分成比例
			BigDecimal partyBBidProp = ssAgreSchemeDetail.getPartyBBidProp().divide(BigDecimal.valueOf(100));				//售方竞价分成比例
			BigDecimal partyALcProp = ssAgreSchemeDetail.getPartyALcProp().divide(BigDecimal.valueOf(100));					//用户双边分成比例
			BigDecimal partyABidProp = ssAgreSchemeDetail.getPartyABidProp().divide(BigDecimal.valueOf(100));				//用户竞价分成比例
			// ============= 售电公司购电收益 ===========
			BigDecimal lcpProfit = cataPlainPrc.subtract(transPrc).subtract(lcPrc).multiply(lcPqRatio).multiply(partyBLcProp);			//双边比例电量收益
			BigDecimal bidProfit = cataPlainPrc.subtract(transPrc).subtract(bidPrc).multiply(bidPqRatio).multiply(partyBBidProp);		//竞价比例电量收益
			BigDecimal compDealProfit = lcpProfit.add(bidProfit);
			map.put("compDealProfit", compDealProfit);
			//============= 用户返还电费 ===========
			BigDecimal lcpBack = cataPlainPrc.subtract(transPrc).subtract(lcPrc).multiply(lcPqRatio).multiply(partyALcProp);			//用户双边电费返还
			BigDecimal bidBack = cataPlainPrc.subtract(transPrc).subtract(bidPrc).multiply(bidPqRatio).multiply(partyABidProp);			//用户竞价电费返还
			BigDecimal consPro = lcpBack.add(bidBack);
			map.put("consPro", consPro);
		}else{
			// ============= 售电公司购电收益 ===========
			BigDecimal lcpProfit = cataPlainPrc.subtract(transPrc).subtract(lcPrc).multiply(lcPqRatio);			//双边比例电量收益
			BigDecimal bidProfit = cataPlainPrc.subtract(transPrc).subtract(bidPrc).multiply(bidPqRatio);		//竞价比例电量收益
			BigDecimal compDealProfit = lcpProfit.add(bidProfit);
			map.put("compDealProfit", compDealProfit);
			//============= 用户返还电费 ===========
			map.put("consPro", BigDecimal.ZERO);
		}
		return map;
	}

	/**
	  * @Title: compAgreProfit
	  * @Description: 保底模式 公司购电收益计算
	  * @param transPrc
	  * @param agrePrc
	  * @param lcPrc
	  * @param lcPqRatio
	  * @param bidPrc
	  * @param bidPqRatio
	  * @return BigDecimal
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月24日 下午5:06:01
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月24日 下午5:06:01
	  * @since  1.0.0
	 */
	private BigDecimal compAgreProfit(BigDecimal cataPlainPrc, BigDecimal transPrc, BigDecimal agrePrc,BigDecimal lcPrc, BigDecimal lcPqRatio, BigDecimal bidPrc,BigDecimal bidPqRatio) {
		BigDecimal lcpProfit = cataPlainPrc.subtract(transPrc).subtract(lcPrc).subtract(agrePrc).multiply(lcPqRatio);		//双边比例电量收益
		BigDecimal bidProfit = cataPlainPrc.subtract(transPrc).subtract(bidPrc).subtract(agrePrc).multiply(bidPqRatio);		//竞价比例电量收益
		BigDecimal compDealProfit = lcpProfit.add(bidProfit);
		return compDealProfit;
	}

	/**
	  * @Title: getConsCheckedPro
	  * @Description: 用户考核电费、售电公司赔偿费计算
	  * @param ssAgreSchemeDetail
	  * (
	  *  consId 用户ID
	  *  delPq:实际用电量	 	bidPrc:竞价电价		reportPq:申报电量	 	bidPq:竞价成交电量 	lcPq:分配双边电量		
	  *  punishTypeUpper: 正偏差惩罚类型("1" 赔偿)  punishUpperType:正偏差惩罚电价基准 	punishUpperThreshold:正偏差允许范围 	punishUpperProp:正偏差惩罚比例 	upperPrc	正偏差惩罚协议价(人工录入)
	  *  punishTypeLower: 负偏差惩罚类型("1" 赔偿)  punishLowerType:负偏差惩罚电价基准	punishLowerThreshold:负偏差允许范围	punishLowerProp:负偏差惩罚比例  	lowerPrc    负偏差惩罚协议价(人工录入)
	  *  compensateFlag:  是否赔偿	("1" 赔偿)	 compensateType:售电公司惩罚电价基准	compensateThreshold: 赔偿域值			compensateProp: 赔偿比例值 		compensatePrc 赔偿协议价（人工录入）
	  *  
	  *  返回结果：ssAgreSchemeDetail.getConsCheckedPro 用户考核电费		sAgreSchemeDetail.getCompensateAmt 	售电公司赔偿金额
	  * )	
	  * @return void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月10日 下午6:57:22
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月10日 下午6:57:22
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@Override
	public void getCheckedPro(SsAgreSchemeDetail ssAgreSchemeDetail) throws Exception {
		SmPrcInfo smPrc = smPrcInfoService.getSmPrcInfoByConsId(ssAgreSchemeDetail.getConsId());
		if(smPrc == null || smPrc.getCataPlainPrc() == null || smPrc.getTransPrc() == null || smPrc.getKwhPlainPrc() == null){
			throw new BusinessException("没有维护电价信息，请联系系统管理员！");
		}
		
		/*
		 * 正偏差惩罚类型 punishTypeUpper ： 	1 —— 惩罚	0 —— 不惩罚
		 * 负偏差惩罚类型 punishTypeLower ： 1 —— 惩罚	0 —— 不惩罚
		 * 售电公司是否惩罚 compensateFlag ： 1 —— 赔偿		0 —— 不赔偿
		 * 赔偿基准 compensateType ：01 人工录入	03 电度电价	04 竞价电价	05 输配电价
		 */
		BigDecimal delPq = ssAgreSchemeDetail.getDelPq();				//实际用电量
		BigDecimal bidPrc = ssAgreSchemeDetail.getBidPrc(); 			//竞价电价
		BigDecimal reportPq = ssAgreSchemeDetail.getReportPq();			//申报电量
		BigDecimal bidPq = ssAgreSchemeDetail.getBidPq();				//竞价成交电量
		BigDecimal lcPq = ssAgreSchemeDetail.getLcPq();					//分配双边电量
		SmCalcConfigureDetail smCalcConfigureDetail = dvalueBalanceService.getSmCalcConfigureDetail();
		BigDecimal modelPrc = BigDecimal.ZERO;	//标杆电价
		if(smCalcConfigureDetail != null){
			String json = smCalcConfigureDetail.getCalcParam();
			if(json != null && !json.equals("")){
				Map<String, Object> jsonMap = JSON.parseObject(json);
				if(jsonMap.get("modelPrc") != null) {
					modelPrc = new BigDecimal(jsonMap.get("modelPrc").toString());
				}
			}
		}
		ssAgreSchemeDetail.setConsCheckedPro(BigDecimal.ZERO);
		// ==============正偏差惩罚============
		if("1".equals(ssAgreSchemeDetail.getPunishTypeUpper()) && delPq.compareTo(reportPq) == 1){ 
			BigDecimal punishUpperThreshold = ssAgreSchemeDetail.getPunishUpperThreshold().divide(BigDecimal.valueOf(100),2,4).abs(); 				//正偏差允许范围
			BigDecimal punishUpperProp = ssAgreSchemeDetail.getPunishUpperProp() == null ? null: ssAgreSchemeDetail.getPunishUpperProp().divide(BigDecimal.valueOf(100),2,4).abs(); //正偏差惩罚比例
			if(delPq.subtract(reportPq).divide(reportPq,2,4).compareTo(punishUpperThreshold) == 1){
				//计算用户考核电费
				BigDecimal punishPq = delPq.subtract(reportPq.multiply(BigDecimal.ONE.add(punishUpperThreshold)));	//惩罚部分电量
				switch(ssAgreSchemeDetail.getPunishUpperType()){
					case "01" :{//人工录入
						BigDecimal consCheckedPro = punishPq.multiply(ssAgreSchemeDetail.getUpperPrc());
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
					}
					case "03" :{//电度电价
						BigDecimal consCheckedPro = punishPq.multiply(smPrc.getKwhPlainPrc().multiply(punishUpperProp));
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
					}
					case "04" :{//竞价电价
						BigDecimal consCheckedPro = punishPq.multiply(bidPrc.multiply(punishUpperProp));
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
					}
					case "05" :{//输配电价
						BigDecimal consCheckedPro = punishPq.multiply(smPrc.getTransPrc().multiply(punishUpperProp));
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
					}
					case "06" :{//目录电价
						BigDecimal consCheckedPro = punishPq.multiply(smPrc.getCataPlainPrc().multiply(punishUpperProp));
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
						
					}
					case "07" :{//标杆电价
						BigDecimal consCheckedPro = punishPq.multiply(modelPrc.multiply(punishUpperProp));
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
					}
				}
			}
		}
		// ==============负偏差惩罚============
		if("1".equals(ssAgreSchemeDetail.getPunishTypeLower()) && delPq.compareTo(reportPq) == -1){ 
			BigDecimal punishLowerThreshold = ssAgreSchemeDetail.getPunishLowerThreshold().divide(BigDecimal.valueOf(100),2,4).abs(); 				//负偏差允许范围
			BigDecimal punishLowerProp = ssAgreSchemeDetail.getPunishLowerProp() == null? null: ssAgreSchemeDetail.getPunishLowerProp().divide(BigDecimal.valueOf(100),2,4).abs();//负偏差惩罚比例
			if(reportPq.subtract(delPq).divide(reportPq,2,4).compareTo(punishLowerThreshold) == 1){
				BigDecimal punishPq = reportPq.multiply(BigDecimal.ONE.subtract(punishLowerThreshold)).subtract(delPq);		//惩罚部分电量
				switch(ssAgreSchemeDetail.getPunishLowerType()){
					case "01" :{//人工录入
						BigDecimal consCheckedPro = punishPq.multiply(ssAgreSchemeDetail.getLowerPrc());
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
					}
					case "03" :{//电度电价
						BigDecimal consCheckedPro = punishPq.multiply(smPrc.getKwhPlainPrc().multiply(punishLowerProp));
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
					}
					case "04" :{//竞价电价
						BigDecimal consCheckedPro = punishPq.multiply(bidPrc.multiply(punishLowerProp));
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
					}
					case "05" :{//输配电价
						BigDecimal consCheckedPro = punishPq.multiply(smPrc.getTransPrc().multiply(punishLowerProp));
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
					}
					case "06" :{//目录电价
						BigDecimal consCheckedPro = punishPq.multiply(smPrc.getCataPlainPrc().multiply(punishLowerProp));
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
						
					}
					case "07" :{//标杆电价
						BigDecimal consCheckedPro = punishPq.multiply(modelPrc.multiply(punishLowerProp));
						ssAgreSchemeDetail.setConsCheckedPro(consCheckedPro);
						break;
					}
				}
			}
		}
		// ==============售电公司赔偿费用 ============
		ssAgreSchemeDetail.setCompensateAmt(BigDecimal.ZERO);
		if("1".equals(ssAgreSchemeDetail.getCompensateFlag())){
			BigDecimal ratio = reportPq.subtract(bidPq).subtract(lcPq).divide(reportPq,7,4);
			BigDecimal compensateThreshold = ssAgreSchemeDetail.getCompensateThreshold().divide(BigDecimal.valueOf(100),2,4).abs();			//赔偿阈值
			BigDecimal compensateProp = ssAgreSchemeDetail.getCompensateProp() == null? null: ssAgreSchemeDetail.getCompensateProp().divide(BigDecimal.valueOf(100),2,4).abs();//赔偿比例值
			if(ratio.compareTo(compensateThreshold) == 1){
				BigDecimal compensatePq = reportPq.multiply(BigDecimal.ONE.subtract(compensateThreshold)).subtract(lcPq).subtract(bidPq);	//赔偿部分电量
				switch(ssAgreSchemeDetail.getCompensateType()){
				case "01" :{//人工录入
					BigDecimal compensateAmt = compensatePq.multiply(ssAgreSchemeDetail.getCompensatePrc());
					ssAgreSchemeDetail.setCompensateAmt(compensateAmt);
					break;
				}
				case "03" :{//电度电价
					BigDecimal compensateAmt = compensatePq.multiply(smPrc.getKwhPlainPrc().multiply(compensateProp));
					ssAgreSchemeDetail.setCompensateAmt(compensateAmt);
					break;
				}
				case "04" :{//竞价电价
					BigDecimal compensateAmt = compensatePq.multiply(bidPrc.multiply(compensateProp));
					ssAgreSchemeDetail.setCompensateAmt(compensateAmt);
					break;
				}
				case "05" :{//输配电价
					BigDecimal compensateAmt = compensatePq.multiply(smPrc.getTransPrc().multiply(compensateProp));
					ssAgreSchemeDetail.setCompensateAmt(compensateAmt);
					break;
				}
				}
			}
		}
	}

	/**
	 * @Title: getSsAgreSchemeListByConsName<br/>
	 * @Description: 根据用户Id查询所有方案<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception
	 * <br/>
	 *             List<SsAgreSchemeDetail><br/>
	 *             <b>创 建 人：</b>chengpeng<br/>
	 *             <b>创建时间:</b>2017年5月23日 下午2:14:28 <b>修 改 人：</b>chengpeng<br/>
	 *             <b>修改时间:</b>2017年5月23日 下午2:14:28
	 * @since 1.0.0
	 */
	public List<SsAgreSchemeDetail> getSsAgreSchemeListByConsName(SsAgreSchemeVo ssAgreSchemeVo) throws Exception {
		ssAgreSchemeVo.setPagingFlag(false);
		List<SsAgreSchemeDetail> ssAgreSchemeList = getSsAgreSchemeListByParams(ssAgreSchemeVo);
		return ssAgreSchemeList;
	}

	/**
	 * @Title: commitSsAgreScheme<br/>
	 * @Description: 提交操作，包含添加修改删除<br/>
	 * @param ssAgreSchemeVo
	 * <br/>
	 *            void<br/>
	 *            <b>创 建 人：</b>chengpeng<br/>
	 *            <b>创建时间:</b>2017年5月23日 下午2:14:47 <b>修 改 人：</b>chengpeng<br/>
	 *            <b>修改时间:</b>2017年5月23日 下午2:14:47
	 * @since 1.0.0
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void commitSsAgreScheme(SsAgreSchemeVo ssAgreSchemeVo) {
		// 取List 区分添加 更新
		String[] removeIds = ssAgreSchemeVo.getRemoveIds();
		List<SsAgreScheme> ssAgreSchemeList = ssAgreSchemeVo.getSsAgreSchemeList();
		List<SsAgreScheme> updateList = new ArrayList<SsAgreScheme>();
		List<SsAgreScheme> saveList = new ArrayList<SsAgreScheme>();
		for (SsAgreScheme ssAgreScheme : ssAgreSchemeList) {
			if (ssAgreScheme.getId() == null || ssAgreScheme.getId() == "") {
				saveList.add(ssAgreScheme);
			} else {
				updateList.add(ssAgreScheme);
			}
		}
		// 调用saveList方法 del方法
		deleteSsAgreScheme(removeIds);
		saveSsAgreSchemeList(saveList);
		for (SsAgreScheme ssAgreScheme : updateList) {
			updateSsAgreScheme(ssAgreScheme);
		}

	}

	/**
	 * @Title: getNewSchemeNameSsAgreSchemeListByParams<br/>
	 * @Description: 根据查询条件获取客户最新的方案的对象SsAgreScheme列表<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception
	 * <br/>
	 *             List<SsAgreSchemeDetail><br/>
	 *             <b>创 建 人：</b>chengpeng<br/>
	 *             <b>创建时间:</b>2017年5月23日 下午2:15:10 <b>修 改 人：</b>chengpeng<br/>
	 *             <b>修改时间:</b>2017年5月23日 下午2:15:10
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SsAgreSchemeDetail> getNewSchemeNameSsAgreSchemeListByParams(SsAgreSchemeVo ssAgreSchemeVo) throws Exception {
		// 当Vo为空时,初始化Vo对象,应用分页参数
		if (ssAgreSchemeVo == null) {
			ssAgreSchemeVo = new SsAgreSchemeVo();
		}
		Parameter.isFilterData.set(true);
		List<SsAgreSchemeDetail> ssAgreSchemeList = (List<SsAgreSchemeDetail>) dao.getBySql("ssAgreScheme.sql.getNewSchemeNameSsAgreSchemeListByParams",ssAgreSchemeVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(ssAgreSchemeList);
		return ssAgreSchemeList;
	}

	/**
	 * @Title: getNewSchemeNameSsAgreSchemeCountByParams<br/>
	 * @Description: 根据查询条件获取每个客户的新方案的对象SsAgreScheme数量<br/>
	 * @param ssAgreSchemeVo
	 * @return <br/>
	 *         Integer<br/>
	 *         <b>创 建 人：</b>chengpeng<br/>
	 *         <b>创建时间:</b>2017年5月23日 下午2:15:27 <b>修 改 人：</b>chengpeng<br/>
	 *         <b>修改时间:</b>2017年5月23日 下午2:15:27
	 * @since 1.0.0
	 */
	public Integer getNewSchemeNameSsAgreSchemeCountByParams(SsAgreSchemeVo ssAgreSchemeVo) {
		ssAgreSchemeVo.setLoginUserId(SystemServiceUtil.getLoginUserInfo().getId());
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("ssAgreScheme.sql.getNewSchemeNameSsAgreSchemeCountByParams",ssAgreSchemeVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	/**
	 * @Title: getSByPage<br/>
	 * @Description: 查询分页数据<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception
	 * <br/>
	 *             List<SsAgreSchemeDetail><br/>
	 *             <b>创 建 人：</b>chengpeng<br/>
	 *             <b>创建时间:</b>2017年5月23日 下午2:15:44 <b>修 改 人：</b>chengpeng<br/>
	 *             <b>修改时间:</b>2017年5月23日 下午2:15:44
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SsAgreSchemeDetail> getSByPage(SsAgreSchemeVo ssAgreSchemeVo)throws Exception {

		// 当Vo为空时,初始化Vo对象,应用分页参数
		if (ssAgreSchemeVo == null) {
			ssAgreSchemeVo = new SsAgreSchemeVo();
		}
		String consName = ssAgreSchemeVo.getSsAgreScheme().getConsName();
		if (consName == "" || consName == null) {// 判断有无查询条件// 没有查询条件查询10个客户的所有方案
			Parameter.isFilterData.set(true);
			List<String> list = (List<String>) dao.getBySql("ssAgreScheme.sql.getConsIdsByLimit", ssAgreSchemeVo);
			Parameter.isFilterData.set(false);
			if (list.size() > 0) {
				ssAgreSchemeVo.setConsIdlist(list);
			}
		}
		Parameter.isFilterData.set(true);
		List<SsAgreSchemeDetail> ssAgreSchemeList = (List<SsAgreSchemeDetail>) dao.getBySql("ssAgreScheme.sql.getSsAgreSchemeByConsIdlist",ssAgreSchemeVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(ssAgreSchemeList);
		return ssAgreSchemeList;

	}

	/**
	 * @Title: getCountByConsId<br/>
	 * @Description: 根据consId查询此consId对应的数据条数<br/>
	 * @param consId
	 * @return <br/>
	 *         Integer<br/>
	 *         <b>创 建 人：</b>chengpeng<br/>
	 *         <b>创建时间:</b>2017年5月23日 下午2:18:18 <b>修 改 人：</b>chengpeng<br/>
	 *         <b>修改时间:</b>2017年5月23日 下午2:18:18
	 * @since 1.0.0
	 */
	public Integer getCountByConsId(String consId) {
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("ssAgreScheme.sql.getSsAgreSchemeCountByConsId", consId);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	/**
	 * @Title: getSsAgreSchemeById<br/>
	 * @Description: 根据父节点id（最新方案）查询子节点数据，并过滤不查询父节点数据,此方法是重载方法<br/>
	 * @param ssAgreSchemeVo
	 * @return
	 * @throws Exception
	 * <br/>
	 *             List<SsAgreSchemeDetail><br/>
	 *             <b>创 建 人：</b>chengpeng<br/>
	 *             <b>创建时间:</b>2017年5月23日 下午2:16:56 <b>修 改 人：</b>chengpeng<br/>
	 *             <b>修改时间:</b>2017年5月23日 下午2:16:56
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SsAgreSchemeDetail> getSsAgreSchemeById(SsAgreSchemeVo ssAgreSchemeVo) throws Exception {
		List<SsAgreSchemeDetail> ssAgreSchemeList = (List<SsAgreSchemeDetail>) dao.getBySql("ssAgreScheme.sql.getSsAgreSchemeById",ssAgreSchemeVo);
		ConvertListUtil.convert(ssAgreSchemeList);
		return ssAgreSchemeList;
	}

	/**
	 * 转换为正式合同
	 */
	@SuppressWarnings("unchecked")
	public SmPpaVo saveSmPpa(SmPpaVo smPpaVo) throws Exception {
		// 根据用户查询草稿状态的合同
		List<SmPpaDetail> smPpaDetailList = (List<SmPpaDetail>) dao.getBySql("ssAgreScheme.sql.getByCosId", smPpaVo);
		SmPpaDetail smPpaDetail = null;
		if (smPpaDetailList.size() > 0) {
			ConvertListUtil.convert(smPpaDetailList);
			smPpaDetail = smPpaDetailList.get(0);
		}
		// 设置合同ID
		if (smPpaDetail != null) {
			smPpaVo.getSmPpa().setId(smPpaDetail.getId());
		}
		// 转为正式合同时 设置合同名称 ：用户名称+合同草稿
		smPpaVo.getSmPpa().setAgreName(smPpaVo.getSsAgreScheme().getConsName()+"合同草稿");
		
		if (smPpaVo.getSmPpa().getId() == null || "".equals(smPpaVo.getSmPpa().getId().trim())) {
			// 新增
			// 代理方式 String proxyMode = smPpaVo.getSmPpa().getProxyMode();
			// 自动生成合同编号
			String dbName = "";
			try {
				dbName = CompanyDomainInfoUtil.getInstance().currentDatabase();
			} catch (Exception e) {

			}
			String agreNo = SequenceTool.getInstatnce().generateSerinalNumber("sellAgreNo", dbName);
			smPpaVo.getSmPpa().setAgreNo(agreNo);
			smPpaVo.getSmPpa().setOrgNo(OrgUtil.getOrgNoByUser());
			dao.save(smPpaVo.getSmPpa());
			String ppaId = smPpaVo.getSmPpa().getId();

			// 合同分成方式
			SmDistMode smDistMode = smPpaVo.getSmDistMode();
			smDistMode.setAgreId(ppaId);
			smDistMode.setOrgNo(OrgUtil.getOrgNoByUser());
			if("03".equals(smDistMode.getDiviCode()) || "04".equals(smDistMode.getDiviCode())){
				smDistMode.setDiviType("01");
			}
			if (smDistMode.getDiscountLowerFlag() == null || smDistMode.getDiscountLowerFlag().trim().equals("")) {
				smDistMode.setDiscountUpperFlag("0");
			}
			if (smDistMode.getDiscountLowerFlag() == null || smDistMode.getDiscountLowerFlag().trim().equals("")) {
				smDistMode.setDiscountLowerFlag("0");
			}
			dao.save(smDistMode);

			// 用户合同惩罚关系
			SmAgrePunishCons smAgrePunishCons = smPpaVo.getSmAgrePunishCons();
			if (smAgrePunishCons.getPunishTypeUpper() == null || smAgrePunishCons.getPunishTypeUpper().trim().equals("")) {
				smAgrePunishCons.setPunishTypeUpper("0");
			}
			if (smAgrePunishCons.getPunishTypeLower() == null || smAgrePunishCons.getPunishTypeLower().trim().equals("")) {
				smAgrePunishCons.setPunishTypeLower("0");
			}
			smAgrePunishCons.setAgreId(ppaId);
			smAgrePunishCons.setOrgNo(OrgUtil.getOrgNoByUser());
			dao.save(smAgrePunishCons);

			// 售电公司合同惩罚关系
			SmAgrePunishComp smAgrePunishComp = smPpaVo.getSmAgrePunishComp();
			if (smAgrePunishComp.getPunishFlag() == null || smAgrePunishComp.getPunishFlag().trim().equals("")) {
				smAgrePunishComp.setPunishFlag("0");
			}
			smAgrePunishComp.setAgreId(ppaId);
			smAgrePunishComp.setOrgNo(OrgUtil.getOrgNoByUser());
			dao.save(smAgrePunishComp);
		} else {
			// 更新
			// 合同主体信息
			dao.updateExcludeNull(smPpaVo.getSmPpa());
			String ppaId = smPpaVo.getSmPpa().getId();

			// 合同分成方式
			SmDistMode smDistMode = smPpaVo.getSmDistMode();
			if(smPpaDetail != null){
				smDistMode.setId(smPpaDetail.getModeId());
			}
			if (smDistMode.getDiscountLowerFlag() == null || smDistMode.getDiscountLowerFlag().trim().equals("")) {
				smDistMode.setDiscountUpperFlag("0");
			}
			if (smDistMode.getDiscountLowerFlag() == null || smDistMode.getDiscountLowerFlag().trim().equals("")) {
				smDistMode.setDiscountLowerFlag("0");
			}
			if("03".equals(smDistMode.getDiviCode()) || "04".equals(smDistMode.getDiviCode())){
				smDistMode.setDiviType("01");
			}
			smDistMode.setAgreId(ppaId);
			smDistMode.setOrgNo(OrgUtil.getOrgNoByUser());
			//dao.updateExcludeNull(smDistMode);
			//切换分成方式时  把无用字段至null
			dao.update(smDistMode);

			// 用户合同惩罚关系
			SmAgrePunishCons smAgrePunishCons = smPpaVo.getSmAgrePunishCons();
			smAgrePunishCons.setId(smPpaDetail.getConId());
			smAgrePunishCons.setAgreId(ppaId);
			smAgrePunishCons.setOrgNo(OrgUtil.getOrgNoByUser());
			if (smAgrePunishCons.getPunishTypeUpper() == null || smAgrePunishCons.getPunishTypeUpper().trim().equals("")) {
				smAgrePunishCons.setPunishTypeUpper("0");
			}
			if (smAgrePunishCons.getPunishTypeLower() == null || smAgrePunishCons.getPunishTypeLower().trim().equals("")) {
				smAgrePunishCons.setPunishTypeLower("0");
			}
			dao.updateExcludeNull(smAgrePunishCons);

			// 售电公司合同惩罚关系
			SmAgrePunishComp smAgrePunishComp = smPpaVo.getSmAgrePunishComp();
			smAgrePunishComp.setId(smPpaDetail.getComId());
			smAgrePunishComp.setAgreId(ppaId);
			smAgrePunishComp.setOrgNo(OrgUtil.getOrgNoByUser());
			if (smAgrePunishComp.getPunishFlag() == null || smAgrePunishComp.getPunishFlag().trim().equals("")) {
				smAgrePunishComp.setPunishFlag("0");
			}
			dao.updateExcludeNull(smAgrePunishComp);
		}
		//判断是否更新合同辅助设计信息
		SsAgreScheme ssAgreScheme = smPpaVo.getSsAgreScheme();
		if(ssAgreScheme.getSchemeName() != null && !"".equals(ssAgreScheme.getSchemeName())){
			if(ssAgreScheme.getId() != null && !"".equals(ssAgreScheme.getId())){
				//合同辅助设计信息
				updateSsAgreScheme(ssAgreScheme);
			}else{
				//保存合同辅助设计信息
				saveSsAgreScheme(ssAgreScheme);
			}
		}
		return smPpaVo;
	}
	
	/**
	 * 导出按钮
	 */
	@Override
	public void outExcel(String ids,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String[] id = ids.split(",");
		//获取模板文件存储目录（webapp目录下）
		String excelPath=request.getSession().getServletContext().getRealPath("/plugins/cloud-purchase-web/crm/ssagrescheme/合同辅助设计方案导出.xlsx");//模板路径
		response.reset();//去除空白行
		response.setContentType("application/vnd.ms-excel;charset=gb2312");
		//response.setContentType("application/x-msdownload");
		String newName = URLEncoder.encode("合同辅助设计方案导出" + System.currentTimeMillis() + ".xlsx","UTF-8"); //设置导出的文件名称
		request.setCharacterEncoding("UTF-8");//设置request的编码方式，防止中文乱码
    	response.setHeader("Content-Disposition","attachment;filename=\"" + newName + "\"");  
    	InputStream fio = new FileInputStream(excelPath);//将模板读入流
    	ServletOutputStream out = response.getOutputStream();
    	
        try {  
        	XSSFWorkbook workbook=new XSSFWorkbook(fio);//创建excel(xlsx格式)
			XSSFSheet sheet=workbook.getSheetAt(0);     //创建工作薄sheet
			
			// 数据 Excel 所需字段
            String[] str = {"consName","schemeName","transTotalPro","consCheckedPro","consPro","sellTotalPro","compCheckedPro","agrePrc",
					"partyBLcProp","partyALcProp","partyBBidProp","partyABidProp","punishTypeUpperName","punishUpperThreshold","punishUpperTypeName",
					"upperPrc","punishUpperProp","punishTypeLowerName","punishLowerThreshold","punishLowerTypeName","lowerPrc","punishLowerProp","compensateFlagName",
					"compensateThreshold","compensateTypeName","compensatePrc","compensateProp","reportPq","lcPq","lcPrc","bidPq","bidPrc","delPq"};
			
            Map<String,Object> params = new HashMap<>();
			params.put("ids",id); //导出数据的id
			List<Map<String,Object>> list=getExcelDataInfo(params);
			
			int rowCount=2; //第三行存数据
			int cellCount=0;//第一列开始
			Object obj; 
			
			List<String> indexList = new ArrayList<>();//所需合并的客户名
			Map<String,Integer> consMap = new HashMap<>();
			
			String name = list.get(0).get("consName").toString();//第一条用户名称
			int index = 0;
			boolean first = false;
			for(Map<String,Object> map :list){
				XSSFRow row=sheet.createRow(rowCount);//创建行
				//合并行的算法（同客户名称的）
				String nName = map.get("consName").toString();
				if(first && name.equals(nName)){
					index ++;
				}else{
					indexList.add(nName);
					consMap.put(name, index);
					index = 0;
					name = nName;
				}
				consMap.put(name, index);
				first = true;
				//Excel内
				for(int i=0; i<str.length; i++){
					XSSFCellStyle borderStyle = (XSSFCellStyle)workbook.createCellStyle();// 创建单元格样式对象   
					//边框线样式-实线
					borderStyle.setBorderBottom(CellStyle.BORDER_THIN);   
		            borderStyle.setBorderTop(CellStyle.BORDER_THIN);   
		            borderStyle.setBorderLeft(CellStyle.BORDER_THIN);   
		            borderStyle.setBorderRight(CellStyle.BORDER_THIN);
		            
		            // 设置单元格边框颜色   
		            borderStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.black));   
		            borderStyle.setTopBorderColor(new XSSFColor(java.awt.Color.black));   
		            borderStyle.setLeftBorderColor(new XSSFColor(java.awt.Color.black)); 
		            borderStyle.setRightBorderColor(new XSSFColor(java.awt.Color.black)); 
		            //居中   
		            borderStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		            borderStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		            String key = str[i];
					 obj = map.get(key);
					 Cell borderCell = null;
					if(obj != null){
						borderCell = row.createCell(cellCount++);	  //创建列
						borderCell.setCellValue(obj.toString());	  //列数据
						borderCell.setCellStyle(borderStyle);		  //单元格样式
					}else{
						borderCell = row.createCell(cellCount++);	  //创建列
						borderCell.setCellValue("");	  			  //列数据
						borderCell.setCellStyle(borderStyle);		  //单元格样式
					}
				}
				rowCount++;
				cellCount=0;
			}
			//合并
			int m = 2;
			for(int i=0; i<indexList.size(); i++){
				String consName = indexList.get(i);					//获取需合并的名称
				int count = consMap.get(consName);					//获取名称对应的需合并行数
				if(count > 0) {
					CellRangeAddress region=new CellRangeAddress(m, m+count, 0, 0); //合并行
					sheet.addMergedRegion(region);
				}
				m += count + 1;
			}
			workbook.write(out);
            out.flush();  
        } catch (FileNotFoundException e) {
			System.out.println("电量模板没有找到");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("输出流异常");
			e.printStackTrace();
		}finally {
			try {
				fio.close();
				out.close();
			} catch (IOException e) {
				System.out.println("流关闭异常");
				e.printStackTrace();
			}
		}  
		
	}
	
		//数据
		@Override
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> getExcelDataInfo(Map<String,Object> params) throws Exception {
			List<Map<String,Object>> list=(List<Map<String, Object>>) dao.getBySql("ssAgreScheme.sql.getExcelData", params);
			return list;
		}

}
