package com.hhwy.purchaseweb.forecast.phfcoalprice.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfCoalPrice;
import com.hhwy.purchaseweb.forecast.phfcoalprice.domain.PhfCoalPriceDetail;
import com.hhwy.purchaseweb.forecast.phfcoalprice.domain.PhfCoalPriceVo;
import com.hhwy.purchaseweb.forecast.phfcoalprice.domain.SaveCoalPriceVo;

/**
 * IPhfCoalPriceService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhfCoalPriceService{
	
	/**
	 * 分页获取对象PhfCoalPrice
	 * @param PhfCoalPriceVo
	 * @return QueryResult
	 */
	public QueryResult<PhfCoalPriceDetail> getPhfCoalPriceByPage(PhfCoalPriceVo phfCoalPriceVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhfCoalPrice列表
	 * @param PhfCoalPriceVo
	 * @return List
	 */
	public List<PhfCoalPriceDetail> getPhfCoalPriceListByParams(PhfCoalPriceVo phfCoalPriceVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhfCoalPrice数量
	 * @param PhfCoalPriceVo
	 * @return Integer
	 */
	public Integer getPhfCoalPriceCountByParams(PhfCoalPriceVo phfCoalPriceVo);
	
	/**
	 * 根据查询条件获取单个对象PhfCoalPrice
	 * @param PhfCoalPriceVo
	 * @return PhfCoalPrice
	 */
	public PhfCoalPriceDetail getPhfCoalPriceOneByParams(PhfCoalPriceVo phfCoalPriceVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhfCoalPrice
	 * @param ID
	 * @return PhfCoalPrice
	 */
	public PhfCoalPrice getPhfCoalPriceById(String id);
	
	/**
     * 校验年月是否重复
     * checkYm(描述这个方法的作用)<br/>
     * @param ym 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void checkYm(String ym);
    
	/**
	 * 添加对象PhfCoalPrice
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfCoalPrice(SaveCoalPriceVo saveCoalPriceVo);
	
	/**
	 * 添加对象PhfCoalPrice列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfCoalPriceList(List<PhfCoalPrice> phfCoalPriceList);
	
	/**
	 * 更新对象PhfCoalPrice
	 * @param 实体对象
	 * @return PhfCoalPrice
	 */
	public void updatePhfCoalPrice(PhfCoalPrice phfCoalPrice);
	
	/**
	 * 删除对象PhfCoalPrice
	 * @param id数据组
	 */
	public void deletePhfCoalPrice(String[] ids);

}