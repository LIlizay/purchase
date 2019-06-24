package com.hhwy.purchaseweb.forecast.phfworkcondition.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.forecast.phfworkcondition.service.PhfWorkConditionService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfWorkCondition;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchaseweb.forecast.phfworkcondition.domain.PhfWorkConditionVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhfWorkConditionService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhfWorkConditionServiceImpl implements PhfWorkConditionService {

	public static final Logger log = LoggerFactory.getLogger(PhfWorkConditionServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhfWorkCondition
	 * @param ID
	 * @return PhfWorkCondition
	 */
	public QueryResult<PhfWorkCondition> getPhfWorkConditionByPage(PhfWorkConditionVo phfWorkConditionVo) throws Exception{
		QueryResult<PhfWorkCondition> queryResult = new QueryResult<PhfWorkCondition>();
		long total = getPhfWorkConditionCountByParams(phfWorkConditionVo);
		List<PhfWorkCondition> phfWorkConditionList = getPhfWorkConditionListByParams(phfWorkConditionVo);
		queryResult.setTotal(total);
		queryResult.setData(phfWorkConditionList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhfWorkCondition数量
	 * @param PhfWorkConditionVo
	 * @return Integer
	 */
	public Integer getPhfWorkConditionCountByParams(PhfWorkConditionVo phfWorkConditionVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phfWorkCondition.sql.getPhfWorkConditionCountByParams",phfWorkConditionVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhfWorkCondition列表
	 * @param PhfWorkConditionVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhfWorkCondition> getPhfWorkConditionListByParams(PhfWorkConditionVo phfWorkConditionVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phfWorkConditionVo == null){
			phfWorkConditionVo = new PhfWorkConditionVo();
		}
		Parameter.isFilterData.set(true);
		List<PhfWorkCondition> phfWorkConditionList = (List<PhfWorkCondition>)dao.getBySql("phfWorkCondition.sql.getPhfWorkConditionListByParams",phfWorkConditionVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phfWorkConditionList);
		return phfWorkConditionList;
	}
	/**
	 * 根据查询条件获取单个对象PhfWorkCondition
	 * @param PhfWorkConditionVo
	 * @return PhfWorkCondition
	 */
	public PhfWorkCondition getPhfWorkConditionOneByParams(PhfWorkConditionVo phfWorkConditionVo) throws Exception{
		PhfWorkCondition phfWorkCondition = null;
		List<PhfWorkCondition> phfWorkConditionList = getPhfWorkConditionListByParams(phfWorkConditionVo);
		if(phfWorkConditionList != null && phfWorkConditionList.size() > 0){
			phfWorkCondition = phfWorkConditionList.get(0);
		}
		return phfWorkCondition;
	}
	
	/**
	 * 根据ID获取对象PhfWorkCondition
	 * @param ID
	 * @return PhfWorkCondition
	 */
	public PhfWorkCondition getPhfWorkConditionById(String id) {
		return dao.findById(id, PhfWorkCondition.class);
	}	
	
	/**
	 * 添加对象PhfWorkCondition
	 * @param 实体对象
	 */
	public void savePhfWorkCondition(PhfWorkCondition phfWorkCondition) {
		dao.save(phfWorkCondition);
	}
	
	/**
	 * 添加对象PhfWorkCondition
	 * @param 实体对象
	 */
	public void savePhfWorkConditionList(List<PhfWorkCondition> phfWorkConditionList) {
		dao.saveList(phfWorkConditionList);
	}
	
	/**
	 * 更新对象PhfWorkCondition
	 * @param 实体对象
	 */
	public void updatePhfWorkCondition(PhfWorkCondition phfWorkCondition) {
		dao.update(phfWorkCondition);
	}
	
	/**
	 * 删除对象PhfWorkCondition
	 * @param id数据组
	 */
	public void deletePhfWorkCondition(String[] ids) {
		dao.delete(ids, PhfWorkCondition.class);
	}

	/**
	 * 查询工况信息
	 * @throws Exception 
	 */
	@Override
	public List<PhfWorkCondition> getWorkInfo(String ym) throws Exception {
		PhfWorkConditionVo phfWorkConditionVo=new PhfWorkConditionVo();
		phfWorkConditionVo.getPhfWorkCondition().setYmd(ym);
		phfWorkConditionVo.setPagingFlag(false);
		List<PhfWorkCondition> list=getPhfWorkConditionListByParams(phfWorkConditionVo);
		List<PhfWorkCondition> newList=new ArrayList<PhfWorkCondition>();

		//判断当前年月有几天
		int year  = Integer.parseInt(ym.substring(0,4));
		int month = Integer.parseInt(ym.substring(4,6));;
		Calendar c = Calendar.getInstance();
		c.set(year, month, 0); 
		int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
		
		if(list!=null&&list.size()==dayOfMonth){
			return list;
		}
		
		//如果当前年月没有数据
		if(list!=null&&list.size()==0){
			for(int i=1;i<=dayOfMonth;i++){
				PhfWorkCondition phfWorkCondition=new PhfWorkCondition();
				String ymd=ym+(i<10?("0"+i):i);
				phfWorkCondition.setYmd(ymd);
				newList.add(phfWorkCondition);
			}
		}
		return newList;
	}

	/**
	 * 保存工况信息
	 */
	@Override
	public void saveWorkInfo(PhfWorkConditionVo phfWorkConditionVo) {
		List<PhfWorkCondition> list=phfWorkConditionVo.getList();
		for(PhfWorkCondition phfWorkCondition:list){
			dao.save(phfWorkCondition);
		}
	}	
}