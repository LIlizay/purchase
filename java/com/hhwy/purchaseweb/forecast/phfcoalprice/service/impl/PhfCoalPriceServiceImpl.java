package com.hhwy.purchaseweb.forecast.phfcoalprice.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.forecast.phfcoalprice.service.PhfCoalPriceService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhfCoalPrice;
import com.hhwy.purchaseweb.forecast.phfcoalprice.domain.PhfCoalPriceDetail;
import com.hhwy.purchaseweb.forecast.phfcoalprice.domain.PhfCoalPriceVo;
import com.hhwy.purchaseweb.forecast.phfcoalprice.domain.SaveCoalPriceVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhfCoalPriceService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhfCoalPriceServiceImpl implements PhfCoalPriceService {

	public static final Logger log = LoggerFactory.getLogger(PhfCoalPriceServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhfCoalPrice
	 * @param ID
	 * @return PhfCoalPrice
	 */
	public QueryResult<PhfCoalPriceDetail> getPhfCoalPriceByPage(PhfCoalPriceVo phfCoalPriceVo) throws Exception{
		QueryResult<PhfCoalPriceDetail> queryResult = new QueryResult<PhfCoalPriceDetail>();
		long total = getPhfCoalPriceCountByParams(phfCoalPriceVo);
		List<PhfCoalPriceDetail> phfCoalPriceList = getPhfCoalPriceListByParams(phfCoalPriceVo);
		queryResult.setTotal(total);
		queryResult.setData(phfCoalPriceList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhfCoalPrice数量
	 * @param PhfCoalPriceVo
	 * @return Integer
	 */
	public Integer getPhfCoalPriceCountByParams(PhfCoalPriceVo phfCoalPriceVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phfCoalPrice.sql.getPhfCoalPriceCountByParams",phfCoalPriceVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhfCoalPrice列表
	 * @param PhfCoalPriceVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhfCoalPriceDetail> getPhfCoalPriceListByParams(PhfCoalPriceVo phfCoalPriceVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phfCoalPriceVo == null){
			phfCoalPriceVo = new PhfCoalPriceVo();
		}
		Parameter.isFilterData.set(true);
		List<PhfCoalPriceDetail> phfCoalPriceList = (List<PhfCoalPriceDetail>)dao.getBySql("phfCoalPrice.sql.getPhfCoalPriceListByParams",phfCoalPriceVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phfCoalPriceList);
		return phfCoalPriceList;
	}
	/**
	 * 根据查询条件获取单个对象PhfCoalPrice
	 * @param PhfCoalPriceVo
	 * @return PhfCoalPrice
	 */
	public PhfCoalPriceDetail getPhfCoalPriceOneByParams(PhfCoalPriceVo phfCoalPriceVo) throws Exception{
	    PhfCoalPriceDetail phfCoalPrice = null;
		List<PhfCoalPriceDetail> phfCoalPriceList = getPhfCoalPriceListByParams(phfCoalPriceVo);
		if(phfCoalPriceList != null && phfCoalPriceList.size() > 0){
			phfCoalPrice = phfCoalPriceList.get(0);
		}
		return phfCoalPrice;
	}
	
	/**
	 * 根据ID获取对象PhfCoalPrice
	 * @param ID
	 * @return PhfCoalPrice
	 */
	public PhfCoalPrice getPhfCoalPriceById(String id) {
		return dao.findById(id, PhfCoalPrice.class);
	}	
	
	/**
	 * 校验年月是否重复
	 * checkYm(描述这个方法的作用)<br/>
	 * @param ym 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
    public void checkYm(String ym){
	    PhfCoalPriceVo phfCoalPriceVo = new PhfCoalPriceVo();
	    phfCoalPriceVo.getPhfCoalPrice().setYm(ym);
	    int total = getPhfCoalPriceCountByParams(phfCoalPriceVo);
	    if(total>0){
	        throw new RuntimeException("年月重复！");
	    }
	}
	/**
	 * 添加对象PhfCoalPrice
	 * @param 实体对象
	 */
	public void savePhfCoalPrice(SaveCoalPriceVo saveCoalPriceVo) {
	    checkYm(saveCoalPriceVo.getYm());
		dao.saveList(saveCoalPriceVo.getList());
	}
	
	/**
	 * 添加对象PhfCoalPrice
	 * @param 实体对象
	 */
	public void savePhfCoalPriceList(List<PhfCoalPrice> phfCoalPriceList) {
		dao.saveList(phfCoalPriceList);
	}
	
	/**
	 * 更新对象PhfCoalPrice
	 * @param 实体对象
	 */
	public void updatePhfCoalPrice(PhfCoalPrice phfCoalPrice) {
		dao.update(phfCoalPrice);
	}
	
	/**
	 * 删除对象PhfCoalPrice
	 * @param id数据组
	 */
	public void deletePhfCoalPrice(String[] ids) {
		dao.delete(ids, PhfCoalPrice.class);
	}	
}