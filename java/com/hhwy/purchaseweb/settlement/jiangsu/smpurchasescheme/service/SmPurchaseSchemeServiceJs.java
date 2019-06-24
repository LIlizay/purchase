package com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.domain.SmPurchaseSchemeVoJs;

/**
 * <b>类 名 称：</b>SmPurchaseSchemeServiceJs<br/>
 * <b>类 描 述：</b><br/>		江苏的月度购电结算的方案service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年3月30日 下午3:47:51<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface SmPurchaseSchemeServiceJs{
	/**
	 * @Title: getSmPurchaseSchemeVoBySchemeId
	 * @Description: 江苏的根据方案id获取结算结果（包括 方案详情实体类列表、售电公司结算信息、用户结算信息（零售市场售电收入明细）、批发市场购电支出明细）
	 * 				因为前台弹框的参数是“方案实体对象”，所以在这里就不取“方案实体对象”了
	 * @param schemeId
	 * @return 
	 * SmPurchaseSchemeVoJs
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月31日 下午8:36:17
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月31日 下午8:36:17
	 * @since  1.0.0
	 */
	public SmPurchaseSchemeVoJs getSmPurchaseSchemeVoBySchemeId(String schemeId)  throws Exception;
	/**
	 * @Title: saveOrUpdateSmPurchaseSchemeVoJs
	 * @Description: 江苏的新增或修改计划下的方案,
	 * 			包含：方案，方案详情列表，售电公司结算信息，用户结算信息列表（零售市场售电收入明细），批发市场购电支出明细
	 * @param smPurchaseSchemeVoJs 
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月30日 下午4:23:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月30日 下午4:23:20
	 * @since  1.0.0
	 */
	@Transactional
	public void saveOrUpdateSmPurchaseSchemeVoJs(SmPurchaseSchemeVoJs smPurchaseSchemeVoJs) throws Exception;
	/**
	 * @Title: deleteSchemeAndConsInfo
	 * @Description: 江苏结算中 根据方案id删除 方案、方案详情信息列表、公司收益信息、用户收益信息列表（零售市场售电收入明细），批发市场购电支出明细
	 * @param schemeIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月30日 下午4:36:38
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月30日 下午4:36:38
	 * @since  1.0.0
	 */
	@Transactional
	public void deleteAllSettlementInfo(String[] schemeIds);
}