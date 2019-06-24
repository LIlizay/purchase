package com.hhwy.purchaseweb.arithmetic.bidarithmetic.service;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain.BidResultDetail;
import com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain.BidSearchVo;

public interface BidArithmeticService{
	
	/**
	 * @Title: getBidListInfo
	 * @Description: TODO(根据省份获取不同算法结果)
	 * @param bidSearchVo
	 * @return 
	 * QueryResult<BidResultDetail>
	 * <b>创 建 人：</b>lilei<br/>
	 * <b>创建时间:</b>2017年4月9日 上午10:27:46
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年4月9日 上午10:27:46
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public QueryResult<BidResultDetail> getBidListInfo(BidSearchVo bidSearchVo) throws Exception;

}
