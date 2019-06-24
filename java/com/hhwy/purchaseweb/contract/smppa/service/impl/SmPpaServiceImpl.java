package com.hhwy.purchaseweb.contract.smppa.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.purchaseweb.bigdata.service.BigDataService;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.contract.smagremp.domain.SmAgreMpVo;
import com.hhwy.purchaseweb.contract.smagremp.service.SmAgreMpService;
import com.hhwy.purchaseweb.contract.smagrepunishcomp.domain.SmAgrePunishCompVo;
import com.hhwy.purchaseweb.contract.smagrepunishcomp.service.SmAgrePunishCompService;
import com.hhwy.purchaseweb.contract.smagrepunishcons.domain.SmAgrePunishConsVo;
import com.hhwy.purchaseweb.contract.smagrepunishcons.service.SmAgrePunishConsService;
import com.hhwy.purchaseweb.contract.smagresup.service.SmAgreSupService;
import com.hhwy.purchaseweb.contract.smdistmode.domain.SmDistModeVo;
import com.hhwy.purchaseweb.contract.smdistmode.service.SmDistModeService;
import com.hhwy.purchaseweb.contract.smppa.domain.SmPpaDetail;
import com.hhwy.purchaseweb.contract.smppa.domain.SmPpaVo;
import com.hhwy.purchaseweb.contract.smppa.service.SmPpaService;
import com.hhwy.selling.domain.SmAgreMp;
import com.hhwy.selling.domain.SmAgrePunishComp;
import com.hhwy.selling.domain.SmAgrePunishCons;
import com.hhwy.selling.domain.SmDistMode;
import com.hhwy.selling.domain.SmPpa;

/**
 * SmPpaService
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmPpaServiceImpl implements SmPpaService {

	public static final Logger log = LoggerFactory.getLogger(SmPpaServiceImpl.class);

	@Autowired
	DAO<?> dao;

	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

	/**
	 * 合同计量点关系
	 */
	@Autowired
	private SmAgreMpService smAgreMpService;

	/**
	 * 合同服务关系
	 */
	/*
	 * @Autowired private SmAgreServService smAgreServService;
	 */

	/**
	 * 用户合同惩罚关系
	 */
	@Autowired
	private SmAgrePunishConsService smAgrePunishConsService;

	/**
	 * 售电公司合同惩罚关系
	 */
	@Autowired
	private SmAgrePunishCompService smAgrePunishCompService;

	/**
	 * 合同分成关系
	 */
	@Autowired
	private SmDistModeService smDistModeService;
	
	/**
	 * bigDataService		大数据service
	 */
	@Autowired
	private BigDataService bigDataService;
	
	/**
	 * 售电合同补充协议 
	 */
	@Autowired
	private SmAgreSupService smAgreSupService;
	
	
	/**
	 * 分页获取对象SmPpa
	 * 
	 * @param ID
	 * @return SmPpa
	 */
	public QueryResult<SmPpaDetail> getSmPpaByPage(SmPpaVo smPpaVo)throws Exception {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()).toString();
		dao.updateBySql("smPpa.sql.expiredApply",date );
		QueryResult<SmPpaDetail> queryResult = new QueryResult<SmPpaDetail>();
		long total = getSmPpaCountByParams(smPpaVo);
		List<SmPpaDetail> smPpaList = getSmPpaListByParams(smPpaVo);
		queryResult.setTotal(total);
		queryResult.setData(smPpaList);
		return queryResult;
	}

	/**
	 * 根据查询条件获取对象SmPpa数量
	 * 
	 * @param SmPpaVo
	 * @return Integer
	 */
	public Integer getSmPpaCountByParams(SmPpaVo smPpaVo) {
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smPpa.sql.getSmPpaCountByParams",smPpaVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	/**
	 * 根据查询条件获取对象SmPpa列表
	 * 
	 * @param SmPpaVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmPpaDetail> getSmPpaListByParams(SmPpaVo smPpaVo) throws Exception {
		// 当Vo为空时,初始化Vo对象,应用分页参数
		if (smPpaVo == null) {
			smPpaVo = new SmPpaVo();
		}
		Parameter.isFilterData.set(true);
		List<SmPpaDetail> smPpaList = (List<SmPpaDetail>) dao.getBySql("smPpa.sql.getSmPpaListByParams", smPpaVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smPpaList);
		return smPpaList;
	}

	/**
	 * 根据查询条件获取单个对象SmPpa
	 * 
	 * @param SmPpaVo
	 * @return SmPpa
	 */
	@SuppressWarnings("unchecked")
	public SmPpaDetail getSmPpaOneByParams(SmPpaVo smPpaVo) throws Exception {
		SmPpaDetail smPpaDetail = null;
		List<SmPpaDetail> smPpaList = (List<SmPpaDetail>) dao.getBySql("smPpa.sql.getSmPpaList", smPpaVo);
		ConvertListUtil.convert(smPpaList);
		//List<SmPpaDetail> smPpaList = getSmPpaListByParams(smPpaVo);
		if (smPpaList != null && smPpaList.size() > 0) {
			smPpaDetail = smPpaList.get(0);
		}
		return smPpaDetail;
	}
	
	
	@Override
	public SmPpaDetail getSmPpaOneByParams2(SmPpaVo smPpaVo) throws Exception {
		SmPpaDetail smPpaDetail = null;
		List<SmPpaDetail> smPpaList = getSmPpaListByParams(smPpaVo);
		if (smPpaList != null && smPpaList.size() > 0) {
			smPpaDetail = smPpaList.get(0);
		}
		return smPpaDetail;
	}

	/**
	 * 根据ID获取对象SmPpa
	 * 
	 * @param ID
	 * @return SmPpa
	 */
	public SmPpaVo getSmPpaById(String id) throws Exception {
		SmPpaVo smPpaVo = new SmPpaVo();
		// 合同主体信息
		SmPpa smPpa = dao.findById(id, SmPpa.class);
		smPpaVo.setSmPpa(smPpa);
		// 合同计量点信息
		if (smPpa.getProxyMode() != null && smPpa.getProxyMode().equals(BusinessContants.PROXY_MODE_MP)) {
			SmAgreMpVo smAgreMpVo = new SmAgreMpVo();
			smAgreMpVo.getSmAgreMp().setAgreId(id);
			smAgreMpService.getSmAgreMpCountByParams(smAgreMpVo);
		}
		// 合同分成方式
		SmDistModeVo smDistModeVo = new SmDistModeVo();
		smDistModeVo.getSmDistMode().setAgreId(id);
		SmDistMode smDistMode = smDistModeService.getSmDistModeOneByParams(smDistModeVo);
		smPpaVo.setSmDistMode(smDistMode);
		// 用户合同惩罚关系
		SmAgrePunishConsVo smAgrePunishConsVo = new SmAgrePunishConsVo();
		smAgrePunishConsVo.getSmAgrePunishCons().setAgreId(id);
		SmAgrePunishCons smAgrePunishCons = smAgrePunishConsService.getSmAgrePunishConsOneByParams(smAgrePunishConsVo);
		smPpaVo.setSmAgrePunishCons(smAgrePunishCons);
		// 售电公司合同惩罚关系
		SmAgrePunishCompVo smAgrePunishCompVo = new SmAgrePunishCompVo();
		smAgrePunishCompVo.getSmAgrePunishComp().setAgreId(id);
		SmAgrePunishComp smAgrePunishComp = smAgrePunishCompService.getSmAgrePunishCompOneByParams(smAgrePunishCompVo);
		smPpaVo.setSmAgrePunishComp(smAgrePunishComp);
		// 合同服务关系
		// SmAgreServVo smAgreServVo = new SmAgreServVo();
		// smAgreServVo.getSmAgreServ().setAgreId(id);
		// List<SmAgreServDetail> servList =
		// smAgreServService.getSmAgreServListByParams(smAgreServVo);
		// smPpaVo.setSmAgreServDetail(servList);
		return smPpaVo;
	}

	/**
	 * 添加对象SmPpa
	 * 
	 * @param 实体对象
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public SmPpaVo saveSmPpa(SmPpaVo smPpaVo) {
		// 代理方式
		String proxyMode = smPpaVo.getSmPpa().getProxyMode();
		// 自动生成合同编号 暂去现人工填
//		String dbName = CompanyDomainInfoUtil.getInstance().currentDatabase();
//		String agreNo = SequenceTool.getInstatnce().generateSerinalNumber("sellAgreNo", dbName);
//		smPpaVo.getSmPpa().setAgreNo(agreNo);
		smPpaVo.getSmPpa().setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(smPpaVo.getSmPpa());
		String ppaId = smPpaVo.getSmPpa().getId();
		// 合同计量点关系
		if (proxyMode.equals(BusinessContants.PROXY_MODE_MP)) {
			List<SmAgreMp> mpList = smPpaVo.getSmAgreMp();
			if (mpList.size() > 0) {
				for (SmAgreMp sSmAgreMp : mpList) {
					sSmAgreMp.setAgreId(ppaId);
				}
				smAgreMpService.saveSmAgreMpList(mpList);
			}
		}
		// 合同分成方式
		smPpaVo.getSmDistMode().setAgreId(ppaId);
		smDistModeService.saveSmDistMode(smPpaVo.getSmDistMode());
		// 用户合同惩罚关系
		smPpaVo.getSmAgrePunishCons().setAgreId(ppaId);
		smAgrePunishConsService.saveSmAgrePunishCons(smPpaVo.getSmAgrePunishCons());
		// 售电公司合同惩罚关系
		smPpaVo.getSmAgrePunishComp().setAgreId(ppaId);
		smAgrePunishCompService.saveSmAgrePunishComp(smPpaVo.getSmAgrePunishComp());
		// 合同服务关系
		/*
		 * List<SmAgreServ> servList = smPpaVo.getSmAgreServ(); if
		 * (servList.size() > 0) { for (SmAgreServ smAgreServ : servList) {
		 * smAgreServ.setAgreId(ppaId); }
		 * smAgreServService.saveSmAgreServList(smPpaVo.getSmAgreServ()); }
		 */
		
		//根据现在用户的合同状态更新用户的状态（意向用户和合同用户）
		dao.updateBySql("scConsumerInfo.sql.updateAllConsType", null);
		
		//更新大数据表中数据
		bigDataService.saveOrUpdatePlanPqBySmPpa(smPpaVo.getSmPpa());

		return smPpaVo;
	}

	/**
	 * 更新对象SmPpa
	 * 
	 * @param 实体对象
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSmPpa(SmPpaVo smPpaVo) throws Exception {
		SmPpa smPpa = smPpaVo.getSmPpa();
		// 合同主体信息
		dao.update(smPpa);
		// 合同计量点关系
		List<SmAgreMp> mpList = smPpaVo.getSmAgreMp();
		if (BusinessContants.PROXY_MODE_MP.equals(smPpa.getProxyMode())) {
			for (SmAgreMp smAgreMp : mpList) {
				smAgreMpService.updateSmAgreMp(smAgreMp);
			}
		} else {
			List<String> idList = new ArrayList<String>();
			String[] ids = new String[idList.size()];
			for (SmAgreMp smAgreMp : mpList) {
				idList.add(smAgreMp.getId());
			}
			smAgreMpService.deleteSmAgreMp(idList.toArray(ids));
		}
		// 合同分成方式
		smDistModeService.updateSmDistMode(smPpaVo.getSmDistMode());
		// 用户合同惩罚关系
		smAgrePunishConsService.updateSmAgrePunishCons(smPpaVo.getSmAgrePunishCons());
		// 售电公司合同惩罚关系
		smAgrePunishCompService.updateSmAgrePunishComp(smPpaVo.getSmAgrePunishComp());
		// 合同服务关系
		/*
		 * SmAgreServVo smAgreServVo = new SmAgreServVo();
		 * smAgreServVo.getSmAgreServ().setAgreId(smPpaVo.getSmPpa().getId());
		 * List<SmAgreServDetail> smAgreServList =
		 * smAgreServService.getSmAgreServListByParams(smAgreServVo);
		 * if(smAgreServList != null && smAgreServList.size() != 0){ String[]
		 * ids = {smPpaVo.getSmPpa().getId()};
		 * smAgreServService.deleteSmAgreServByAgreIds(ids); } List<SmAgreServ>
		 * servList = smPpaVo.getSmAgreServ(); if (servList.size() > 0) { for
		 * (SmAgreServ smAgreServ : servList) {
		 * smAgreServ.setAgreId(smPpaVo.getSmPpa().getId()); }
		 * smAgreServService.saveSmAgreServList(servList); }
		 */
		//根据现在用户的合同状态更新用户的状态（意向用户和合同用户）
		dao.updateBySql("scConsumerInfo.sql.updateAllConsType", null);
		
		//更新大数据表中数据
		bigDataService.saveOrUpdatePlanPqBySmPpa(smPpaVo.getSmPpa());
	}

	/**
	 * 删除对象SmPpa
	 * 
	 * @param id数据组
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteSmPpa(String[] ids) {
		if(ids == null || ids.length == 0) {
			return;
		}
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			SmPpa smPpaEntity = dao.findById(id, SmPpa.class);
			if(smPpaEntity == null || smPpaEntity.getId() == null || smPpaEntity.getAgreStatus() != "0") { //合同辅助设计转过来的售电合同，是没有下面get的数据的
				continue;
			}
			SmPpa smPpa = new SmPpa();
			BeanUtils.copyProperties(smPpaEntity, smPpa);
			smPpa.setJan(null);
			smPpa.setFeb(null);
			smPpa.setMar(null);
			smPpa.setApr(null);
			smPpa.setMay(null);
			smPpa.setJun(null);
			smPpa.setJul(null);
			smPpa.setAug(null);
			smPpa.setSept(null);
			smPpa.setOct(null);
			smPpa.setNov(null);
			smPpa.setDece(null);
			//更新大数据表中数据---删除其合同有效期内的月度计划用电量
			bigDataService.saveOrUpdatePlanPqBySmPpa(smPpa);
			
//			  consId 用户id、effectiveYm 开始年月（yyyyMM格式）、expiryYm 结束年月（yyyyMM格式）
			Map<String, String> params = new HashMap<>();
			params.put("consId", smPpa.getConsId());
			params.put("effectiveYm", smPpa.getEffectiveDate().substring(0, 7).replace("-", ""));
			params.put("expiryYm", smPpa.getExpiryDate().substring(0, 7).replace("-", ""));
			
			//删除该合同用户相关的月度购电数据
			dao.deleteBySql("smPpa.sql.deleteSmppaRelated", params);
		}
		//售电合同补充协议s_m_agre_sup表   
		smAgreSupService.deleteSmAgreSup(ids);
		// 合同分成方式
		smDistModeService.deleteSmDistModeByAgreIds(ids);
		// 用户合同惩罚关系
		smAgrePunishConsService.deleteSmAgrePunishConsByAgreId(ids);
		// 售电公司合同惩罚关系
		smAgrePunishCompService.deleteSmAgrePunishCompByAgreId(ids);
		// 合同主体信息
		dao.delete(ids, SmPpa.class);
		
		//根据现在用户的合同状态更新用户的状态（意向用户和合同用户）
		dao.updateBySql("scConsumerInfo.sql.updateAllConsType", null);
	}

	/**
	 * 获取当前年月签订购电合同的用户
	 * 
	 * @param 查询年月
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SmPpa> getPpaConsId(String ym) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("year", ym.substring(0, 4));
		map.put("ym", ym);
		Parameter.isFilterData.set(true);
		List<SmPpa> consIdList = (List<SmPpa>) dao.getBySql("smPpa.sql.getPpaConsId", map);
		Parameter.isFilterData.set(false);
		return consIdList;
	}


	/**
	 * 根据 year 查询 新增长协购电计划页面 表单，表格数据
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> perMonthData(int year) {

		// 保存结果
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// mybatis 参数
		Map<String, Object> paramMap = new HashMap<String, Object>();

		// 传入明年
		int nextYear = year + 1;
		paramMap.put("nextYear", nextYear);
		paramMap.put("currYear", year);
		Parameter.isFilterData.set(true);
		List<Map<String, Object>> listSmPpa = (List<Map<String, Object>>) dao.getBySql("smPpa.sql.perMonthData", paramMap);
		Parameter.isFilterData.set(false);

		// 查询结果不为空
		if (listSmPpa != null && listSmPpa.size() > 0) {

			// 存放表格数据的结果集
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

			// 预测代理用户总购电量
			BigDecimal yearSum = new BigDecimal(0);

			// 1 到 12 月的 预测总代理点量
			BigDecimal janSum = new BigDecimal(0);
			BigDecimal febSum = new BigDecimal(0);
			BigDecimal marSum = new BigDecimal(0);
			BigDecimal aprSum = new BigDecimal(0);
			BigDecimal maySum = new BigDecimal(0);
			BigDecimal junSum = new BigDecimal(0);
			BigDecimal julSum = new BigDecimal(0);
			BigDecimal augSum = new BigDecimal(0);
			BigDecimal septSum = new BigDecimal(0);
			BigDecimal octSum = new BigDecimal(0);
			BigDecimal novSum = new BigDecimal(0);
			BigDecimal deceSum = new BigDecimal(0);

			List<BigDecimal> monthSumList = Arrays.asList(new BigDecimal[] {
					janSum, febSum, marSum, aprSum, maySum, junSum, julSum,
					augSum, septSum, octSum, novSum, deceSum });

			// 用于方便从 数据库 行里取数据
			List<String> monthStrList = Arrays.asList(new String[] { "jan","feb", "mar", "apr", "may", "jun", "jul", "aug", "sept","oct", "nov", "dece" });

			List<String> monthChinStrList = Arrays.asList(new String[] { "01","02", "03", "04", "05", "06", "07", "08", "09", "10", "11","12" });
			monthChinStrList = formatMonthStr(monthChinStrList, year + "");

			Iterator<Map<String, Object>> it = listSmPpa.iterator();

			while (it.hasNext()) {

				// 对应数据量一行数据
				Map<String, Object> map = (Map<String, Object>) it.next();
				// 生效日期
				String effectiveDateStr = map.get("effectiveDate").toString();
				// 失效日期
				String expiryDateStr = map.get("expiryDate").toString();

				// 生效年
				int effectiveYear = Integer.parseInt(effectiveDateStr.substring(0, 4));
				// 生效月
				int effectiveMonth = Integer.parseInt(effectiveDateStr.substring(5, 7));
				// 失效年
				int expiryYear = Integer.parseInt(expiryDateStr.substring(0, 4));
				// 失效月
				int expiryMonth = Integer.parseInt(expiryDateStr.substring(5, 7));

				int beginIndex = 0;
				int endIndex = monthSumList.size();

				// 1. 如果生效年 < year, 失效年 > year, 取全部的数据
				if (effectiveYear < year && expiryYear > year) {
					// 1. 使用默认的开始结束索引
				} else if (effectiveYear < year && expiryYear == year) {
					// 2. 如果生效年 < year, 失效年 = year, 取 1 月到 失效月的数据
					endIndex = expiryMonth;
				} else if (effectiveYear == year && expiryYear > year) {
					// 3. 如果生效年 = year, 失效年 > year, 取 生效月 到 12 月的数据
					// 从索引为 （生效月 - 1） 位置开始遍历
					beginIndex = effectiveMonth - 1;
				} else if (effectiveYear == year && expiryYear == year) {
					// 4. 如果生效年 = year, 失效年 = year, 取 生效月 到 是失效月的数据
					// 从索引为 （生效月 - 1） 位置开始遍历, 到 （失效月）截止
					beginIndex = effectiveMonth - 1;
					endIndex = expiryMonth;
				}
				for (int i = beginIndex; i < endIndex; i++) {

					BigDecimal monthSum = monthSumList.get(i);
					// 作为 map 里的 key
					String monthStr = monthStrList.get(i);
					// 取出每行当月的数据
					BigDecimal monthVal = new BigDecimal(map.get(monthStr)
							.toString());
					// 和当月总值相加
					monthSum = monthSum.add(monthVal);
					// 重新给 list 赋值
					monthSumList.set(i, monthSum);
				}
			}
			for (int i = 0; i < monthSumList.size(); i++) {

				BigDecimal val = monthSumList.get(i);

				// 计算预测代理用户总购电量
				yearSum = yearSum.add(val);

				// 创建页面表格用数据,对应一行
				Map<String, Object> rowMap = new HashMap<String, Object>();
				rowMap.put("ym", monthChinStrList.get(i));
				rowMap.put("contractPq", val);

				// 放入结果集合
				resultList.add(rowMap);
			}
			resultMap.put("gridData", resultList);
			resultMap.put("yearSum", yearSum);
		} else {

			// 查询结果集合为空
			resultMap.put("gridData", null);
			resultMap.put("yearSum", 0);
		}
		return resultMap;
	}

	// 渲染月份列，用于前台展示
	private List<String> formatMonthStr(List<String> monthChinStrList,
			String year) {
		if (monthChinStrList != null && monthChinStrList.size() > 0) {
			for (int i = 0; i < monthChinStrList.size(); i++) {
				String month = monthChinStrList.get(i);
				month = year + month;
				monthChinStrList.set(i, month);
			}
		}
		return monthChinStrList;
	}

	/**
	 * 提交SmPpa
	 */
	@Override
	public void approvalSmPpa(SmPpa smPpa) {
		smPpa.setAgreStatus("01");
		dao.update(smPpa);
	}

	/**
	 * 根据ID获取对象SmPpa
	 */
	@Override
	public List<SmPpaDetail> getImplementation(String id) {
		SmPpaDetail agrePq	= (SmPpaDetail) dao.getOneBySQL("smPpa.sql.getAgrePqByAgreId", id);			//月度委托电量
		SmPpaDetail actualPq = (SmPpaDetail) dao.getOneBySQL("smPpa.sql.getActualPqByAgreId", id);		//实际用电量
		SmPpaDetail detail = new SmPpaDetail();															//结算电量
		detail.setItemName("结算电量");
		detail.setProxyPq(agrePq.getProxyPq());
		agrePq.setItemName("月度委托电量");
		actualPq.setItemName("实际用电量");
		actualPq.setProxyPq(agrePq.getProxyPq());
		
		List<SmPpaDetail> SmPpaDetailList = new ArrayList<SmPpaDetail>();
		SmPpaDetailList.add(agrePq);
		SmPpaDetailList.add(actualPq);
		SmPpaDetailList.add(detail);
		return SmPpaDetailList;
	}

	/**
	 * 根据id数组获取SmPpa对象列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SmPpa> getSmPpaByIds(String[] ids) {
		List<SmPpa> list= (List<SmPpa>) dao.getBySql("smPpa.sql.getSmPpaByIds", ids);
		return list;
	}
	
	/**
	 * 根据consId 合同开始、结束日期 查预测电量
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getForecast(SmPpa smPpa) throws Exception {
		List<Map<String, Object>> result = (List<Map<String, Object>>) dao.getBySql("smPpa.sql.getForecast", smPpa);
		Map<String, Object> hashMap = null;
		BigDecimal totalPq = BigDecimal.ZERO;
		if(result != null && result.size() > 0){
			hashMap = new HashMap<>(); 
			HashMap<String, Object> mapKey = new HashMap<>();
			mapKey.put("1", "jan");
			mapKey.put("2", "feb");
			mapKey.put("3", "mar");
			mapKey.put("4", "apr");
			mapKey.put("5", "may");
			mapKey.put("6", "jun");
			mapKey.put("7", "jul");
			mapKey.put("8", "aug");
			mapKey.put("9", "sept");
			mapKey.put("10", "oct");
			mapKey.put("11", "nov");
			mapKey.put("12", "dece");
			for(int i=0 ;i<result.size() ;i++){
				Object personForecastPq = result.get(i).get("personForecastPq");
				hashMap.put(mapKey.get(result.get(i).get("MONTH").toString()).toString(), personForecastPq);
				totalPq = totalPq.add(personForecastPq == null ? BigDecimal.ZERO : new BigDecimal(personForecastPq.toString()));
			}
			hashMap.put("total", totalPq);
		}
		return hashMap;
	}

	/**
	 * @Title: forecast<br/>
	 * @Description:TODO(查询当前用户有无负荷预测权限)<br/>
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年4月10日 下午5:31:31
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年4月10日 下午5:31:31
	 * @since  1.0.0
	 */
	@Override
	public String getForecastRes() {
		String userId = SystemServiceUtil.getLoginUserInfo().getId();
		Object oneBySQL = dao.getOneBySQL("smPpa.sql.getForecastRes", userId);
		String forecastResName = oneBySQL == null  ? null : oneBySQL.toString();
		return forecastResName;
	}

	/**
	 * 根据合同id获取月度购电管理、实际用电量录入、月度收益结算的关联关系，为数据删除做提醒
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getDeleteRelatedById(String id) {
		String msg = "true";
		List<String> ymList = (List<String>) dao.getBySql("smPpa.sql.getAccountsYmBySmppaId", id);
		if(ymList != null && ymList.size() > 0){
			String yms = null;
			if(ymList.size() > 3) {
				yms = ymList.get(0) + "," + ymList.get(1) + ","  + ymList.get(2) + "...";
			}else {
				yms = ymList.toString().replaceAll("\\[", "\"").replaceAll("\\]", "\"");
			}
			msg = "删除合同将导致" + yms + "月份的月度结算数据错误，是否确认删除？";
		}else{
			ymList = (List<String>) dao.getBySql("smPpa.sql.getPhmPlanConsRelaBySmppaId", id);
			if(ymList != null && ymList.size() > 0){
				String yms = null;
				if(ymList.size() > 3) {
					yms = ymList.get(0) + "," + ymList.get(1) + ","  + ymList.get(2) + "...";
				}else {
					yms = ymList.toString().replaceAll("\\[", "\"").replaceAll("\\]", "\"");
				}
				msg = "删除合同将影响" + yms + "月份的月度购电数据，是否确认删除？";
			}
		}
		return msg;
	}

	/**
	 * 根据合同ID删除非草稿状态的合同
	 * 非草稿状态的合同删除时应级联删除月度购电及结算中的数据
	 * (包括：ph_m_plan_cons_rela、ph_m_agre_pq_examine、s_m_purchase_scheme_detail、s_m_consumer_profit表数据的删除)
	 */
	@Override
	@Transactional
	public void deleteSmPpaById(String id) {
		String[] ids = {id};
		deleteSmPpa(ids);
	}
}