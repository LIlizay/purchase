package com.hhwy.purchaseweb.arithmetic.decompose.util;

import java.util.Comparator;

import com.hhwy.purchaseweb.arithmetic.decompose.domain.DecomposeData;

/**
 * 
 * <b>类 名 称：</b>DecomposeComparator<br/>
 * <b>类 描 述：</b>分割信息排序工具<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年6月13日 上午1:12:46<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class DecomposeComparator implements Comparator<DecomposeData> {

	@Override
	public int compare(DecomposeData arg0, DecomposeData arg1) {
		// TODO 自动生成的方法存根
		return arg0.getPq()-arg1.getPq();
	}

}
