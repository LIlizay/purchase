/**
 * <b>项目名称：</b>营销一体化平台<br/>
 * <b>包    名：</b>com.hhwy.purchaseweb.arithmetic.common.service.impl<br/>
 * <b>文 件 名：</b>CommonServiceImpl.java<br/>
 * <b>版本信息：</b><br/>
 * <b>日    期：</b>2017年7月27日-下午5:29:09<br/>
 * <b>Copyright (c)</b> 2017恒华伟业科技股份有限公司-版权所有<br/>
 * 
 */
package com.hhwy.purchaseweb.arithmetic.common.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.mainframe.utils.SystemConfigUtil;
import com.hhwy.purchase.domain.SmRuleConfigure;
import com.hhwy.purchaseweb.arithmetic.common.domain.AmtScale;
import com.hhwy.purchaseweb.arithmetic.common.domain.TypeForPrc;
import com.hhwy.purchaseweb.arithmetic.common.service.CommonService;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.crm.smruleconfigure.domain.SmRuleConfigureVo;

 /**
 * <b>类 名 称：</b>CommonServiceImpl<br/>
 * <b>类 描 述：</b>结算公共查询接口实现<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年7月27日 下午5:29:09<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Service("cloudSellingCommonService")
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 
	 * @Title: getRule
	 * @Description: 获取当前登陆人所在省份配置规则或指定省份配置规则
	 * @param province
	 * @return 
	 * SmRuleConfigure
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月15日 上午9:01:45
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月15日 上午9:01:45
	 * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public SmRuleConfigure getRule(String province,String ruleId) throws Exception {
		if(!StringUtils .isEmpty(ruleId)){
			return dao.findById(ruleId, SmRuleConfigure.class);
		}
		if(StringUtils.isEmpty(province)){
			province = SystemConfigUtil.getConfig("cloudselling.province");
			if(StringUtils.isEmpty(province)){
				String orgCode = SystemServiceUtil.getLoginUserInfo().getOrgCode().substring(0,2);
				if(StringUtils.isNumeric(orgCode)){
					province = orgCode+"0000";
				}
			}
		}
		SmRuleConfigureVo smRuleConfigureVo = new SmRuleConfigureVo();
		smRuleConfigureVo.getSmRuleConfigure().setProvinceId(province);
		smRuleConfigureVo.getSmRuleConfigure().setRuleStatus(BusinessContants.RULE_EFFECT02);
		List<SmRuleConfigure> list = (List<SmRuleConfigure>)dao.getBySql("smRuleConfigure.sql.getSmRuleConfigureListByParams",smRuleConfigureVo);
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getCatPrc
	 * @Description: 获取客户对应目录电价
	 * @param custId 客户id，目录电价根据客户id关联查询
	 * @return 
	 * BigDecimal
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年5月3日 下午7:10:29
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年5月3日 下午7:10:29
	 * @since  1.0.0
	 */
	public BigDecimal getCatPrc(String custId) throws Exception{
		return new BigDecimal(0.4304);
	}
	
	/**
	 * 
	 * @Title: getTypeFotPrc
	 * @Description: 获取对应电价类型的电价
	 * @param 
	 * @return 
	 * BigDecimal
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年5月4日 下午1:09:01
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年5月4日 下午1:09:01
	 * @since  1.0.0
	 */
	public BigDecimal getTypeFotPrc(TypeForPrc typeForPrc) throws Exception{
		BigDecimal hundred = new BigDecimal(100);//计算常量
		BigDecimal prcProp = typeForPrc.getPrcProp();//电价比例
		if(prcProp==null){
			prcProp = hundred;
		}else{
			prcProp = prcProp.add(hundred);
		}
		if(BusinessContants.PRCTYPE01.equals(typeForPrc.getPrcType())){
			return typeForPrc.getCustomPrc().abs();
		}else if(BusinessContants.PRCTYPE02.equals(typeForPrc.getPrcType())){
			BigDecimal lcPq = typeForPrc.getLcPq();//长协电量
			BigDecimal bidPq = typeForPrc.getBidPq();//竞价电量
			BigDecimal totalPq = lcPq.add(bidPq);//总电量
			if(typeForPrc.getLcPrc()==null){
				if(typeForPrc.getLcAgrePq()==null||typeForPrc.getLcAgrePq().compareTo(BigDecimal.ZERO)==0
						||totalPq.compareTo(BigDecimal.ZERO)==0){
					return BigDecimal.ZERO;
				}else{
					return lcPq.multiply(typeForPrc.getLcAgreAmt()).divide(typeForPrc.getLcAgrePq(),4,BigDecimal.ROUND_HALF_UP)
							.add(bidPq.multiply(typeForPrc.getBidPrc().abs())).divide(totalPq,4,BigDecimal.ROUND_HALF_UP)
							.multiply(prcProp).divide(hundred,2,BigDecimal.ROUND_HALF_UP);
				}
			}else{
				return lcPq.multiply(typeForPrc.getLcPrc().abs()).add(bidPq.multiply(typeForPrc.getBidPrc().abs()))
						.divide(totalPq,4,BigDecimal.ROUND_HALF_UP).multiply(prcProp)
						.divide(hundred,2,BigDecimal.ROUND_HALF_UP);
			}
		}else if(BusinessContants.PRCTYPE03.equals(typeForPrc.getPrcType())){
			return getCatPrc(typeForPrc.getConsId()).multiply(typeForPrc.getPrcProp()).abs().divide(hundred,2,BigDecimal.ROUND_HALF_UP);
		}else if(BusinessContants.PRCTYPE04.equals(typeForPrc.getPrcType())){
			return typeForPrc.getBidPrc().multiply(typeForPrc.getPrcProp()).abs().divide(hundred,2,BigDecimal.ROUND_HALF_UP);
		}else{
			return BigDecimal.ZERO;
		}
	}
	
	/**
	 * 
	 * @Title: getLcAgrePqAndPrc
	 * @Description: 获取当前年内长协合同总电量及总金额
	 * @return
	 * @throws Exception 
	 * Map<String,Object>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年5月5日 下午9:24:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月23日 下午4:24:20
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> getLcAgrePqAndPrc(String year) throws Exception{
		AmtScale amtScale = getScaleAmt();//电价*电量后的换算精度
		Map<String,String> param = new HashMap<>();
		param.put("year", year);
		Parameter.isFilterData.set(true);
		List<Map<String,Object>> list = (List<Map<String,Object>>)dao.getBySql("arithmetic.sql.getLcAgrePqAndPrc",param);
		Parameter.isFilterData.set(false);
		if(list!=null&&list.size()>0){
			Map<String,Object> map = list.get(0);
			if(map == null || map.get("lcAgreAmt") == null) {
				return null;
			}
			BigDecimal lcAgreAmt = new BigDecimal(map.get("lcAgreAmt").toString());
			map.put("lcAgreAmt", lcAgreAmt.multiply(amtScale.getAmtScale()).abs());
			return map;
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getScaleAmt
	 * @Description: 根据客户id获取金额精度
	 * @return 
	 * BigDecimal
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年5月4日 下午7:31:06
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年5月4日 下午7:31:06
	 * @since  1.0.0
	 */
	public AmtScale getScaleAmt() throws Exception {
		//根据当前登陆人所在省获取配置的数据精度
		AmtScale amtScale = new AmtScale();
		amtScale.setAmtScale(new BigDecimal(1));
		amtScale.setPqScale(new BigDecimal(1000));
		amtScale.setPrcScale(new BigDecimal(0.001));
		return amtScale;//10000*0.001
	}
	
}
