package com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmConsDeviationAmt;
import com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.domain.SmConsDeviationAmtDetail;
import com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.domain.SmConsDeviationAmtVo;
import com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.service.SmConsDeviationAmtService;

/**
 * SmConsDeviationAmtService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmConsDeviationAmtServiceImpl implements SmConsDeviationAmtService {

	public static final Logger log = LoggerFactory.getLogger(SmConsDeviationAmtServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmConsDeviationAmt
	 * @param ID
	 * @return SmConsDeviationAmt
	 */
	public QueryResult<SmConsDeviationAmt> getSmConsDeviationAmtByPage(SmConsDeviationAmtVo smConsDeviationAmtVo) throws Exception{
		QueryResult<SmConsDeviationAmt> queryResult = new QueryResult<SmConsDeviationAmt>();
		long total = getSmConsDeviationAmtCountByParams(smConsDeviationAmtVo);
		List<SmConsDeviationAmt> smConsDeviationAmtList = getSmConsDeviationAmtListByParams(smConsDeviationAmtVo);
		queryResult.setTotal(total);
		queryResult.setData(smConsDeviationAmtList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmConsDeviationAmt数量
	 * @param SmConsDeviationAmtVo
	 * @return Integer
	 */
	public Integer getSmConsDeviationAmtCountByParams(SmConsDeviationAmtVo smConsDeviationAmtVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smConsDeviationAmt.sql.getSmConsDeviationAmtCountByParams",smConsDeviationAmtVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmConsDeviationAmt列表
	 * @param SmConsDeviationAmtVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmConsDeviationAmt> getSmConsDeviationAmtListByParams(SmConsDeviationAmtVo smConsDeviationAmtVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smConsDeviationAmtVo == null){
			smConsDeviationAmtVo = new SmConsDeviationAmtVo();
		}
		Parameter.isFilterData.set(true);
		List<SmConsDeviationAmt> smConsDeviationAmtList = (List<SmConsDeviationAmt>)dao.getBySql("smConsDeviationAmt.sql.getSmConsDeviationAmtListByParams",smConsDeviationAmtVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smConsDeviationAmtList);
		return smConsDeviationAmtList;
	}
	/**
	 * 根据查询条件获取单个对象SmConsDeviationAmt
	 * @param SmConsDeviationAmtVo
	 * @return SmConsDeviationAmt
	 */
	public SmConsDeviationAmt getSmConsDeviationAmtOneByParams(SmConsDeviationAmtVo smConsDeviationAmtVo) throws Exception{
		SmConsDeviationAmt smConsDeviationAmt = null;
		List<SmConsDeviationAmt> smConsDeviationAmtList = getSmConsDeviationAmtListByParams(smConsDeviationAmtVo);
		if(smConsDeviationAmtList != null && smConsDeviationAmtList.size() > 0){
			smConsDeviationAmt = smConsDeviationAmtList.get(0);
		}
		return smConsDeviationAmt;
	}
	
	/**
	 * 根据ID获取对象SmConsDeviationAmt
	 * @param ID
	 * @return SmConsDeviationAmt
	 */
	public SmConsDeviationAmt getSmConsDeviationAmtById(String id) {
		return dao.findById(id, SmConsDeviationAmt.class);
	}	
	
	/**
	 * 添加对象SmConsDeviationAmt
	 * @param 实体对象
	 */
	public void saveSmConsDeviationAmt(SmConsDeviationAmt smConsDeviationAmt) {
		dao.save(smConsDeviationAmt);
	}
	
	/**
	 * 添加对象SmConsDeviationAmt
	 * @param 实体对象
	 */
	public void saveSmConsDeviationAmtList(List<SmConsDeviationAmt> smConsDeviationAmtList) {
		dao.saveList(smConsDeviationAmtList);
	}
	
	/**
	 * 更新对象SmConsDeviationAmt
	 * @param 实体对象
	 */
	public void updateSmConsDeviationAmt(SmConsDeviationAmt smConsDeviationAmt) {
		dao.update(smConsDeviationAmt);
	}
	
	/**
	 * 删除对象SmConsDeviationAmt
	 * @param id数据组
	 */
	public void deleteSmConsDeviationAmt(String[] ids) {
		dao.delete(ids, SmConsDeviationAmt.class);
	}	
	/**
	 * @Title: deleteConsDevAmtBySchemeIds
	 * @Description: 根据方案id删除月度结算用户偏差惩罚费用信息表信息
	 * @param schemeIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月30日 下午10:21:26
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月30日 下午10:21:26
	 * @since  1.0.0
	 */
	public void deleteConsDevAmtBySchemeIds(String[] schemeIds) {
		Map<String,String[]> map=new HashMap<String,String[]>();
		map.put("schemeIds", schemeIds);
		dao.deleteBySql("smConsDeviationAmt.sql.deleteConsDevAmtBySchemeIds", map);
	}	
	/**
	 * @Title: getConsDeviAmtBySchemeId
	 * @Description: 根据方案id获取偏差费用计算相关信息
	 * @param schemeId
	 * @return 
	 * List<SmConsDeviationAmtDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月29日 下午3:04:12
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月29日 下午3:04:12
	 * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SmConsDeviationAmtDetail> getConsDeviAmtBySchemeId(String schemeId)  throws Exception{
		List<SmConsDeviationAmtDetail> list=(List<SmConsDeviationAmtDetail>) dao.getBySql("smConsDeviationAmt.sql.getConsDeviAmtBySchemeId", schemeId);
		//计算偏差电量比例
		for (SmConsDeviationAmtDetail detail : list) {
			if(detail.getActualPq() != null && detail.getProxyPq() != null) {
				if(detail.getProxyPq() != null && detail.getProxyPq().compareTo(BigDecimal.ZERO) != 0){
					BigDecimal devPqProp = detail.getActualPq().subtract(detail.getProxyPq())
							.divide(detail.getProxyPq(), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
					detail.setDevPqProp(devPqProp);
				}
			}
		}
		ConvertListUtil.convert(list);
		return list;
	}
	
	/**
	 * @Title: getConsDeviAmtByYm
	 * @Description: 根据ym获取当月的所有合同用户"偏差费用计算相关信息"
	 * @param ym	yyyyMM格式或者yyyy-MM格式
	 * @return 
	 * List<SmPurchaseSchemeDetailDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月29日 下午3:04:27
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月29日 下午3:04:27
	 * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SmConsDeviationAmtDetail> getConsDeviAmtByYm(String ym) throws Exception{
		if(ym == null || "".equals(ym)) {
			return null;
		}
		ym = ym.replaceAll("-", "");
		String userId = SystemServiceUtil.getLoginUserInfo().getId();
		Map<String , String> params = new HashMap<>();
		params.put("ym", ym);
		params.put("userId", userId);
//		Parameter.isFilterData.set(true);
		List<SmConsDeviationAmtDetail> list=(List<SmConsDeviationAmtDetail>) dao.getBySql("smConsDeviationAmt.sql.getDevAmtDetailListByYm", params);
//		Parameter.isFilterData.set(false);
		//计算偏差电量比例
		for (SmConsDeviationAmtDetail detail : list) {
			if(detail.getActualPq() != null && detail.getProxyPq() != null) {
				BigDecimal devPqProp = new BigDecimal(100);
				if(detail.getProxyPq().compareTo(BigDecimal.ZERO) != 0) {
					devPqProp = detail.getActualPq().subtract(detail.getProxyPq())
							.divide(detail.getProxyPq(), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
				}
				detail.setDevPqProp(devPqProp);
			}
		}
		ConvertListUtil.convert(list);
		return list;
	}
	
	/**
	 * @Title: calculateDeviationCheckAmt
	 * @Description: 计算单个用户的 考核费用
	 * @param detail
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月29日 下午4:59:06
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月29日 下午4:59:06
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public void calculateDeviationCheckAmt(SmConsDeviationAmtDetail detail) throws Exception {
		//初始化考核金额
		detail.setLowerDevAmt(BigDecimal.ZERO);
		detail.setUpperDevAmt1(BigDecimal.ZERO);
		detail.setUpperDevAmt2(BigDecimal.ZERO);
		//规范参数
		if(detail.getLowerThreshold() != null) {
			detail.setLowerThreshold(detail.getLowerThreshold().abs());
		}
		if(detail.getLowerPrc() != null) {
			detail.setLowerPrc(detail.getLowerPrc().abs());
		}
		if(detail.getUpperPrc1() != null) {
			detail.setUpperPrc1(detail.getUpperPrc1().abs());
		}
		if(detail.getUpperPrc2() != null) {
			detail.setUpperPrc2(detail.getUpperPrc2().abs());
		}
		if(detail.getUpperThreshold1() != null) {
			detail.setUpperThreshold1(detail.getUpperThreshold1().abs());
		}
		if(detail.getUpperThreshold2() != null) {
			detail.setUpperThreshold2(detail.getUpperThreshold2().abs());
		}
		
		
		if(detail.getYm() == null || "".equals(detail.getYm()) || detail.getPunishType() == null || "".equals(detail.getPunishType())) {
			return;
		}
		Map<String, String> params = new HashMap<>();
		params.put("consId", detail.getConsId());
		//首先根据  偏差考核方式（01：否，02：月偏差，03：季偏差，04：年偏差）、ym（yyyy-MM格式）获取用户的委托电量和实际用电量
		String ym = detail.getYm().replaceAll("-", "");
		String year = ym.substring(0,4);
		params.put("year", year);
		int month = Integer.valueOf(ym.substring(4));
		String punishType = detail.getPunishType();
		//如果偏差考核方式为：01：否 或 02：月偏差，则委托电量和实际用电量为月度的
		if("01".equals(punishType) || "02".equals(punishType)){
			params.put("minMon", ym.substring(4));
			params.put("maxMon", ym.substring(4));
		}else if("03".equals(punishType)){
			//如果偏差考核方式为：03：季偏差，则委托电量和实际用电量为季度的
			int n = month%3;
			int minMon = 0;
			int maxMon = 0;
			switch(n) {
				case 0:
					minMon = month - 2;
					maxMon = month;
					break;
				case 1:
					minMon = month;
					maxMon = month + 2;
					break;
				case 2:
					minMon = month - 1;
					maxMon = month + 1;
					break;
			}
			params.put("minMon", minMon > 9 ? (""+minMon) : ("0"+minMon));
			params.put("maxMon", maxMon > 9 ? (""+maxMon) : ("0"+maxMon));
		}else {	//年偏差
			params.put("minMon", "01");
			params.put("maxMon", "12");
		}
		
		//如无需计算偏差考核金额，则赋值0后返回
		
		//如果偏差考核方式为：01：否 
		if("01".equals(punishType)){
			return;
		}else if("03".equals(punishType)){
			//如果偏差考核方式为：03：季偏差，则委托电量和实际用电量为季度的
			int n = month%3;
			if(n != 0) {
				return;
			}
		}else if("04".equals(punishType) && month != 12){	//年偏差
			return;
		}
		
		//根据偏差考核方式计算不同月份区间的总委托电量和实际用电量
		Map<String, BigDecimal> result = (Map<String, BigDecimal>) dao.getOneBySQL("smConsDeviationAmt.sql.getProxyAndActualPqByParams", params);
		detail.setActualPq(result.get("actualPq"));
		detail.setProxyPq(result.get("proxyPq"));
		//计算偏差电量比例
		if(detail.getActualPq() != null &&  detail.getProxyPq() != null) {
			detail.setDevPq(detail.getActualPq().subtract(detail.getProxyPq()));
			if(BigDecimal.ZERO.compareTo(detail.getProxyPq()) == 0) {	//若委托电量为0
				detail.setDevPqProp(new BigDecimal(100));
			}else {
				detail.setDevPqProp(detail.getActualPq().subtract(detail.getProxyPq()).divide(detail.getProxyPq(), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
			}
		}else {
			detail.setDevPq(BigDecimal.ZERO);
			detail.setDevPqProp(BigDecimal.ZERO);
			return;
		}
		
		
		
		//计算用户偏差考核金额
		//无偏差
		if(detail.getDevPqProp().compareTo(BigDecimal.ZERO) == 0) {
			return;
		}else if(detail.getDevPqProp().compareTo(BigDecimal.ZERO) < 0){
			//负偏差
			if(detail.getLowerThreshold() == null || detail.getLowerPrc() == null) {
				throw new BusinessException("负偏差时少用考核比例和少用考核价格为必填！");
			}
			//当少用考核比例小于用户偏差比例时
			if(detail.getLowerThreshold().compareTo(detail.getDevPqProp().abs()) < 0) {
				
				BigDecimal lowerDevAmt = detail.getProxyPq().subtract(detail.getActualPq()).subtract(
									detail.getProxyPq().multiply(detail.getLowerThreshold().abs().divide(new BigDecimal(100)))
								).multiply(detail.getLowerPrc()).setScale(2, RoundingMode.HALF_UP);
				detail.setLowerDevAmt(lowerDevAmt);
			}
		}else {
			//正偏差
			if(detail.getUpperThreshold1() == null || detail.getUpperPrc1() == null) {
				throw new BusinessException("正偏差时多用1段考核比例和多用1段考核价格为必填！");
			}
			//当多用1段考核比例小于用户偏差比例时
			if(detail.getUpperThreshold1().compareTo(detail.getDevPqProp()) < 0) {
				BigDecimal upperDevAmt1 = null;
				if(detail.getUpperThreshold2() != null && detail.getUpperPrc2() != null) {
					//当多用2段考核比例小于用户偏差比例时,需要计算2段考核金额
					if(detail.getUpperThreshold2().compareTo(detail.getDevPqProp()) < 0) {
						upperDevAmt1 = detail.getUpperThreshold2().subtract(detail.getUpperThreshold1()).
								multiply(detail.getProxyPq()).multiply(detail.getUpperPrc1()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
						
						BigDecimal upperDevAmt2 = detail.getActualPq().subtract(detail.getProxyPq()).subtract(
								detail.getProxyPq().multiply(detail.getUpperThreshold2().abs().divide(new BigDecimal(100)))
							).multiply(detail.getUpperPrc2()).setScale(2, RoundingMode.HALF_UP);
						detail.setUpperDevAmt1(upperDevAmt1);
						detail.setUpperDevAmt2(upperDevAmt2);
					}else {//当多用2段考核比例>=用户偏差比例时
						upperDevAmt1 = detail.getDevPqProp().subtract(detail.getUpperThreshold1()).
								multiply(detail.getProxyPq()).multiply(detail.getUpperPrc1()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
						detail.setUpperDevAmt1(upperDevAmt1);
					}
				}else {
					upperDevAmt1 = detail.getActualPq().subtract(detail.getProxyPq()).subtract(
								detail.getProxyPq().multiply(detail.getUpperThreshold1().abs().divide(new BigDecimal(100)))
							).multiply(detail.getUpperPrc1()).setScale(2, RoundingMode.HALF_UP);
					detail.setUpperDevAmt1(upperDevAmt1);
				}
			}
		}
	}
}