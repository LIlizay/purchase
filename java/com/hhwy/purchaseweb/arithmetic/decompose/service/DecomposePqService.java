package com.hhwy.purchaseweb.arithmetic.decompose.service;

import java.util.List;

import com.hhwy.purchaseweb.arithmetic.decompose.domain.DecomposeData;
import com.hhwy.purchaseweb.arithmetic.decompose.domain.DecomposeResult;

public interface DecomposePqService {

	/**
	 * 
	 * @Title: getDecomposePq
	 * @Description: 计算类别为购电成本进行电量分割
	 * voidl
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月5日 下午7:48:00
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月5日 下午7:48:00
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getDecomposePq(List<DecomposeData> params) throws Exception ;
	
}
