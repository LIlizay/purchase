package com.hhwy.purchaseweb.arithmetic.bidarithmetic.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain.BidResultDetail;
import com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain.BidSearchVo;
import com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain.Section;
import com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain.SectionResult;
import com.hhwy.purchaseweb.arithmetic.bidarithmetic.service.BidArithmeticService;


@Component
public class BidArithmeticServiceImpl implements BidArithmeticService{
	
	public static final Logger log = LoggerFactory.getLogger(BidArithmeticServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * @Title: getBidListInfo
	 * @Description: TODO(根据省份获取不同算法结果)
	 * @param bidSearchVo
	 * @return 
	 * QueryResult<BidResultDetail>
	 * <b>创 建 人：</b>lilei<br/>
	 * <b>创建时间:</b>2017年4月9日 上午10:27:46
	 * <b>修 改 人：</b>lilei<br/>
	 * <b>修改时间:</b>2017年4月9日 上午10:27:46
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public QueryResult<BidResultDetail> getBidListInfo(BidSearchVo bidSearchVo) throws Exception{
		QueryResult<BidResultDetail> queryResult = new QueryResult<BidResultDetail>();
		
		String provinceCode = bidSearchVo.getProvinceCode();
		switch (provinceCode){
		case "140000": //山西省
			queryResult = this.getShanXiOne(bidSearchVo);
			break;
		case "370000"://山东省
			break;
		case "420000"://湖北省
			break;
		case "440000"://广东省
			queryResult = this.getGuangDong(bidSearchVo);
			break;
		default:
			return queryResult;
		}
		return queryResult;
	}
	
	/**
	 * @Title: getConsAndEnteData
	 * @Description: 获取进行匹配出清的数据集合，包括电价-电量及明细
	 * @param bidSearchVo
	 * @return 
	 * Map<String,Object>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年4月13日 上午10:01:46
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年4月13日 上午10:01:46
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private List<SectionResult> getConsAndEnteData(BidSearchVo bidSearchVo){
		Parameter.isFilterData.set(true);
		//查询用户电价单元数据
		List<Section> consDetailList = (List<Section>)dao.getBySql("arithmetic.sql.getConsSectionDetailData", bidSearchVo);
		//查询发电企业电价单元数据
		List<Section> enteDetailList = (List<Section>)dao.getBySql("arithmetic.sql.getEnteSectionDetailData", bidSearchVo);
		Parameter.isFilterData.set(false);
		
		//一个数据集为空另一个无法单独匹配  
		if(consDetailList==null||enteDetailList==null){
			return null;
		}
		
		//用户list转换成map方便数据调用
		Map<String,List<Section>> consDetailMap = new HashMap<String, List<Section>>();
		while(consDetailList!=null&&!consDetailList.isEmpty()){
			//转化为电价-电量组成键值对
			Section section = consDetailList.remove(0);
			String key = section.getDeclarePrc().toString();
			List<Section> consPrcList = consDetailMap.get(key);
			if(consPrcList==null){
				consPrcList = new ArrayList<Section>();
				consDetailMap.put(key, consPrcList);
			}
			consPrcList.add(section);
		}		
		
		//发电企业list转换成map方便数据调用
		Map<String,List<Section>> enteDetailMap = new HashMap<String, List<Section>>();
		while(enteDetailList!=null&&!enteDetailList.isEmpty()){
			//转化为电价-电量组成键值对
			Section section = enteDetailList.remove(0);
			String key = section.getDeclarePrc().toString();
			List<Section> entePrcList = enteDetailMap.get(key);
			if(entePrcList==null){
				entePrcList = new ArrayList<Section>();
				enteDetailMap.put(key, entePrcList);
			}
			entePrcList.add(section);
		}
		Parameter.isFilterData.set(true);
		//查询用户电价段数据
		List<Map<String,BigDecimal>> consList = (List<Map<String,BigDecimal>>)dao.getBySql("arithmetic.sql.getConsSectionData", bidSearchVo);
		//查询发电企业电价段数据
		List<Map<String,BigDecimal>> enteList = (List<Map<String,BigDecimal>>)dao.getBySql("arithmetic.sql.getEnteSectionData", bidSearchVo);
		Parameter.isFilterData.set(false);

		//电价段数据组装
		List<SectionResult> sectionList = new ArrayList<SectionResult>();
		while(!consList.isEmpty()&&!enteList.isEmpty()){
			Map<String,BigDecimal> enteMap = enteList.remove(0);
			Map<String,BigDecimal> consMap = consList.remove(0);
			BigDecimal consDeclarePrc = consMap.get("consDeclarePrc");//用户申报电价
			BigDecimal enteDeclarePrc = enteMap.get("enteDeclarePrc");//发电企业申报电价
			if(consDeclarePrc.compareTo(enteDeclarePrc)<0){
				//用户申报电价小于发电企业申报电价，说明匹配结束
				break;
			}
			BigDecimal consDeclarePq = consMap.get("consDeclarePq");//用户申报电量
			BigDecimal enteDeclarePq = enteMap.get("enteDeclarePq");//发电企业申报电量
			BigDecimal calculateConsPq = 
					consMap.get("calculateConsPq")==null?consDeclarePq:consMap.get("calculateConsPq");//用户剩余未分配申报电量
			BigDecimal calculateEntePq = 
					enteMap.get("calculateEntePq")==null?enteDeclarePq:enteMap.get("calculateEntePq");//发电企业剩余未分配申报电量
			//匹配结果组装
			SectionResult sectionResult = new SectionResult();
			sectionResult.setConsDeclarePq(consDeclarePq);//用户电价段总电量
			sectionResult.setConsDeclarePrc(consDeclarePrc);//用户电价段电价
			sectionResult.setConsDetails(consDetailMap.get(consDeclarePrc.toString()));//用户电价段电量组成
			sectionResult.setEnteDeclarePq(enteDeclarePq);//发电企业电价段总电量
			sectionResult.setEnteDeclarePrc(enteDeclarePrc);//发电企业电价段电价
			sectionResult.setEnteDetails(enteDetailMap.get(enteDeclarePrc.toString()));//发电企业电价段电量组成
			if(calculateConsPq.compareTo(calculateEntePq)>0){
				//当前交易段用户申报电量大于发电企业申报电量时成交电量
				sectionResult.setBidPq(calculateEntePq);
				consMap.put("calculateConsPq", calculateConsPq.subtract(calculateEntePq));
				consList.add(0,consMap);
			}else if(calculateConsPq.compareTo(calculateEntePq)<0){
				//当前交易段用户申报电量小于发电企业申报电量时成交电量
				sectionResult.setBidPq(calculateConsPq);
				enteMap.put("calculateEntePq", calculateEntePq.subtract(calculateConsPq));
				enteList.add(0,enteMap);
			}else{
				//当前交易段用户申报电量等于发电企业申报电量时成交电量
				sectionResult.setBidPq(enteDeclarePq);
			}
			sectionList.add(sectionResult);
		}
		
		return sectionList;
	}
	
	/**
	 * 
	 * @Title: getSectionInfoByProp
	 * @Description: 按比例匹配用户与发电企业
	 * @param sectionResult 电价段信息
	 * @param bidPrc 中标电价
	 * @param avgPrcFlag 电价计算标识true为边际出清，false为高低匹配出清
	 * @return 
	 * List<BidResultDetail>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月2日 下午8:13:50
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月2日 下午8:13:50
	 * @since  1.0.0
	 */
	private List<BidResultDetail> getSectionInfoByProp(SectionResult sectionResult,BigDecimal bidPrc,boolean avgPrcFlag){
		BigDecimal consDeclarePq = sectionResult.getConsDeclarePq();//电价匹配段用户总电量
		BigDecimal enteDeclarePq = sectionResult.getEnteDeclarePq();//电价匹配段发电企业总电量
		BigDecimal totalBidPq = sectionResult.getBidPq();//电价匹配段总成交电量
		List<Section> consList = sectionResult.getConsDetails();//用户电价段电量组成信息
		List<Section> enteList = sectionResult.getEnteDetails();//发电企业电价段电量组成信息
		List<BidResultDetail> resultList = new ArrayList<BidResultDetail>(); 
		if(consDeclarePq.compareTo(totalBidPq)==0&&enteDeclarePq.compareTo(totalBidPq)==0){
			//匹配电量相等时
			while(!enteList.isEmpty()&&!consList.isEmpty()){
				Section consSection = consList.remove(0);
				Section enteSection = enteList.remove(0);
				//发电企业参与匹配电量 
				BigDecimal entePq = enteSection.getDeclarePq();
				//用户参与匹配电量
				BigDecimal consPq = consSection.getDeclarePq();
				BidResultDetail bidResultDetail = new BidResultDetail();
				if(!avgPrcFlag){
					bidPrc = enteSection.getDeclarePrc().add(consSection.getDeclarePrc())
							.divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
				}
				bidResultDetail.setBidPrc(bidPrc);
				bidResultDetail.setConsDeclarePrc(consSection.getDeclarePrc());
				bidResultDetail.setConsName(consSection.getEnteName());
				bidResultDetail.setEnteDeclarePrc(enteSection.getDeclarePrc());
				bidResultDetail.setEnteName(enteSection.getEnteName());
				bidResultDetail.setSectionName(consSection.getSectionName()); 
				if(entePq.compareTo(consPq)>0){
					bidResultDetail.setBidPq(consPq);
					enteSection.setDeclarePq(entePq.subtract(consPq));
					enteList.add(0,enteSection);
				}else{
					bidResultDetail.setBidPq(entePq);
					consSection.setDeclarePq(consPq.subtract(entePq));
					consList.add(0,enteSection);
				}
				resultList.add(bidResultDetail);
			}
		}else{
			for(Section enteSection:enteList){
				//发电企业参与匹配电量 = 成交总电量/电价段总电量*电价段电量
				BigDecimal entePq = enteSection.getDeclarePq().multiply(totalBidPq).divide(enteDeclarePq,2,BigDecimal.ROUND_HALF_UP);
				for(Section consSection:consList){
					//用户参与匹配电量 = 成交总电量/电价段总电量*电价段电量
					BigDecimal consPq = consSection.getDeclarePq().multiply(totalBidPq).divide(consDeclarePq,2,BigDecimal.ROUND_HALF_UP);
					//用户成交电量 = 用户参与匹配电量 /成交总电量*发电企业参与匹配电量
					BigDecimal bidPq = consPq.multiply(entePq).divide(totalBidPq,2,BigDecimal.ROUND_HALF_UP);
					BidResultDetail bidResultDetail = new BidResultDetail();
					bidResultDetail.setBidPq(bidPq);
					if(!avgPrcFlag){
						bidPrc = enteSection.getDeclarePrc().add(consSection.getDeclarePrc())
								.divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
					}
					bidResultDetail.setBidPrc(bidPrc);
					bidResultDetail.setConsDeclarePrc(consSection.getDeclarePrc());
					bidResultDetail.setConsName(consSection.getEnteName());
					bidResultDetail.setEnteDeclarePrc(enteSection.getDeclarePrc());
					bidResultDetail.setEnteName(enteSection.getEnteName());
					bidResultDetail.setSectionName(consSection.getSectionName());
					resultList.add(bidResultDetail);
				}
			}
		}
		return resultList;
	}
	
	/**
	 *  广东省中标匹配规则
	 */
	public QueryResult<BidResultDetail> getGuangDong(BidSearchVo bidSearchVo) throws Exception{
		QueryResult<BidResultDetail> queryResult = new QueryResult<BidResultDetail>();
		List<BidResultDetail> resultList = new ArrayList<BidResultDetail>();
		//查询申报信息，并进行初步拆分匹配
		List<SectionResult> sectionList = getConsAndEnteData(bidSearchVo);  
		//拆分结果不为空，根据省份匹配规则进行用户-发电企业匹配
		if(!sectionList.isEmpty()){
			//计算中标电价
			SectionResult result = sectionList.get(sectionList.size()-1);
			BigDecimal bidPrc = result.getConsDeclarePrc().add(result.getEnteDeclarePrc())
					.divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
			//对匹配结果进行细致匹配
			for(SectionResult sectionResult:sectionList){
				List<BidResultDetail> bidList = getSectionInfoByProp(sectionResult,bidPrc,true);
				if(bidList.isEmpty()){
					continue;
				}
				resultList.addAll(bidList);
			}
		}
		queryResult.setTotal(Long.parseLong(resultList.size()+""));
		queryResult.setData(resultList);
		return queryResult;
	}
	
	/**
	 *  山西省中标匹配规则
	 */
	public QueryResult<BidResultDetail> getShanXiOne(BidSearchVo bidSearchVo) throws Exception{
		QueryResult<BidResultDetail> queryResult = new QueryResult<BidResultDetail>();
		List<BidResultDetail> resultList = new ArrayList<BidResultDetail>();
		//查询申报信息，并进行初步拆分匹配
		List<SectionResult> sectionList = getConsAndEnteData(bidSearchVo);  
		//拆分结果不为空，根据省份匹配规则进行用户-发电企业匹配
		if(!sectionList.isEmpty()){
			//对匹配结果进行细致匹配
			for(SectionResult sectionResult:sectionList){
				List<BidResultDetail> bidList = getSectionInfoByProp(sectionResult,BigDecimal.ZERO,false);
				if(bidList.isEmpty()){
					continue;
				}
				resultList.addAll(bidList);
			}
		}
		queryResult.setTotal(Long.parseLong(resultList.size()+""));
		queryResult.setData(resultList);
		return queryResult;
	}
	
}
