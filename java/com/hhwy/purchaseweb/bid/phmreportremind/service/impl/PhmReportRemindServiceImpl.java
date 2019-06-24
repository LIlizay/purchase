package com.hhwy.purchaseweb.bid.phmreportremind.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmReportRemind;
import com.hhwy.purchaseweb.bid.phmreportremind.domain.PhmReportRemindVo;
import com.hhwy.purchaseweb.bid.phmreportremind.service.PhmReportRemindService;
import com.hhwy.purchaseweb.sms.service.SwSmsService;

/**
 * PhmReportRemindService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmReportRemindServiceImpl implements PhmReportRemindService {

	public static final Logger log = LoggerFactory.getLogger(PhmReportRemindServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 短信记录接口
	 */
	@Autowired
	SwSmsService swSmsService;
	
	/**
	 * 分页获取对象PhmReportRemind
	 * @param ID
	 * @return PhmReportRemind
	 */
	public QueryResult<PhmReportRemind> getPhmReportRemindByPage(PhmReportRemindVo phmReportRemindVo) throws Exception{
		QueryResult<PhmReportRemind> queryResult = new QueryResult<PhmReportRemind>();
		long total = getPhmReportRemindCountByParams(phmReportRemindVo);
		List<PhmReportRemind> phmReportRemindList = getPhmReportRemindListByParams(phmReportRemindVo);
		queryResult.setTotal(total);
		queryResult.setData(phmReportRemindList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmReportRemind数量
	 * @param PhmReportRemindVo
	 * @return Integer
	 */
	public Integer getPhmReportRemindCountByParams(PhmReportRemindVo phmReportRemindVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmReportRemind.sql.getPhmReportRemindCountByParams",phmReportRemindVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmReportRemind列表
	 * @param PhmReportRemindVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmReportRemind> getPhmReportRemindListByParams(PhmReportRemindVo phmReportRemindVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmReportRemindVo == null){
			phmReportRemindVo = new PhmReportRemindVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmReportRemind> phmReportRemindList = (List<PhmReportRemind>)dao.getBySql("phmReportRemind.sql.getPhmReportRemindListByParams",phmReportRemindVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmReportRemindList);
		return phmReportRemindList;
	}
	/**
	 * 根据查询条件获取单个对象PhmReportRemind
	 * @param PhmReportRemindVo
	 * @return PhmReportRemind
	 */
	public PhmReportRemind getPhmReportRemindOneByParams(PhmReportRemindVo phmReportRemindVo) throws Exception{
		PhmReportRemind phmReportRemind = null;
		List<PhmReportRemind> phmReportRemindList = getPhmReportRemindListByParams(phmReportRemindVo);
		if(phmReportRemindList != null && phmReportRemindList.size() > 0){
			phmReportRemind = phmReportRemindList.get(0);
		}
		return phmReportRemind;
	}
	
	/**
	 * 根据ID获取对象PhmReportRemind
	 * @param ID
	 * @return PhmReportRemind
	 */
	public PhmReportRemind getPhmReportRemindById(String id) {
		return dao.findById(id, PhmReportRemind.class);
	}	
	
	/**
	 * 添加对象PhmReportRemind
	 * @param 实体对象
	 */
	public void savePhmReportRemind(PhmReportRemind phmReportRemind) {
		dao.save(phmReportRemind);
	}
	
	/**
	 * 更新对象PhmReportRemind
	 * @param 实体对象
	 */
	public void updatePhmReportRemind(PhmReportRemind phmReportRemind) {
		dao.update(phmReportRemind);
	}
	
	/**
	 * 删除对象PhmReportRemind
	 * @param id数据组
	 */
	public void deletePhmReportRemind(String[] ids) {
		dao.delete(ids, PhmReportRemind.class);
	}

	/**
	 * @Title: savePhmReportRemindList<br/>
	 * @Description: 保存提示信息<br/>
	 * @param phmReportRemindVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月19日 下午4:34:11
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月19日 下午4:34:11
	 * @since  1.0.0
	 */
	public void savePhmReportRemindList(PhmReportRemindVo phmReportRemindVo) {
		PhmReportRemind phmReportRemind = new PhmReportRemind();
		List<PhmReportRemind> phmReportReminds = new ArrayList<PhmReportRemind>();
		List<String> consIds = phmReportRemindVo.getConsIds();
		for (String consId : consIds) {
			phmReportRemind.setPlanId(phmReportRemindVo.getPlanId());
			phmReportRemind.setConsId(consId);
			phmReportRemind.setContent(phmReportRemindVo.getRemindInfo());
			phmReportRemind.setSender(SystemServiceUtil.getLoginUserInfo().getId());
			phmReportReminds.add(phmReportRemind);
			phmReportRemind = new PhmReportRemind();
		}
		//savePhmReportRemindList(phmReportReminds);
	}
}