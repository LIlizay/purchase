package com.hhwy.purchaseweb.swhtmlinfo.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.swhtmlinfo.domain.SwHtmlInfo;
import com.hhwy.purchaseweb.swhtmlinfo.domain.SwHtmlInfoDetail;
import com.hhwy.purchaseweb.swhtmlinfo.domain.SwHtmlInfoVo;
import com.hhwy.purchaseweb.swhtmlinfo.service.SwHtmlInfoService;

/**
 * SwHtmlInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@SuppressWarnings("unchecked")
@Component
public class SwHtmlInfoServiceImpl implements SwHtmlInfoService {
	public static final Logger log = LoggerFactory.getLogger(SwHtmlInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;

	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

	@Override
	public QueryResult<SwHtmlInfoDetail> getSwHtmlInfoByPage(SwHtmlInfoVo swHtmlInfoVo) throws Exception {
		QueryResult<SwHtmlInfoDetail> queryResult = new QueryResult<SwHtmlInfoDetail>();
		long total = getSwHtmlInfoCountByParams(swHtmlInfoVo);
		List<SwHtmlInfoDetail> swHtmlInfoDetailList = getSwHtmlInfoListByParams(swHtmlInfoVo);
		for(int i = 0 ; i < swHtmlInfoDetailList.size() ; i++){
			SwHtmlInfoDetail swHtmlInfoDetail = swHtmlInfoDetailList.get(i);
			if(("000000").equals(swHtmlInfoDetail.getProvinceCode())){
				swHtmlInfoDetailList.get(i).setProvinceCodeName("全国");
			}
		}
		queryResult.setTotal(total);
		queryResult.setData(swHtmlInfoDetailList);
		return queryResult;
	}

	/**
	 * 分页获取对象SwHtmlInfo
	 * @param ID
	 * @return SwHtmlInfo
	 */
	@Override
	public List<SwHtmlInfoDetail> getSwHtmlInfoListByParams(SwHtmlInfoVo swHtmlInfoVo) throws Exception {
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(swHtmlInfoVo == null){
			swHtmlInfoVo = new SwHtmlInfoVo();
		}
		List<SwHtmlInfoDetail> swHtmlInfoDetailList = (List<SwHtmlInfoDetail>)dao.getBySql("swHtmlInfo.sql.getSwHtmlInfoListByParams",swHtmlInfoVo);
		ConvertListUtil.convert(swHtmlInfoDetailList);
		return swHtmlInfoDetailList;
	}

	/**
	 * 根据查询条件获取对象SwHtmlInfo数量
	 * @param SwHtmlInfoVo
	 * @return Integer
	 */
	@Override
	public Integer getSwHtmlInfoCountByParams(SwHtmlInfoVo swHtmlInfoVo) {
		Object result = dao.getOneBySQL("swHtmlInfo.sql.getSwHtmlInfoCountByParams",swHtmlInfoVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	@Override
	public SwHtmlInfoDetail getSwHtmlInfoOneByParams(SwHtmlInfoVo swHtmlInfoVo)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据ID获取对象SwHtmlInfo
	 * @param ID
	 * @return SwHtmlInfo
	 * @throws Exception 
	 */
	@Override
	public SwHtmlInfoDetail getSwHtmlInfoById(String id,HttpServletRequest request) throws Exception {
		SwHtmlInfoDetail swHtmlInfoDetail = new SwHtmlInfoDetail();
		//获取信息维护信息
		SwHtmlInfo swHtmlInfo = dao.findById(id, SwHtmlInfo.class);
		BeanUtils.copyProperties(swHtmlInfo, swHtmlInfoDetail);
		return swHtmlInfoDetail;
	}

	/**
	 * 添加对象SwHtmlInfo
	 * @param 实体对象
	 * @throws Exception 
	 */
	@Override
	public SwHtmlInfo saveSwHtmlInfo(SwHtmlInfoVo swHtmlInfoVo,HttpServletRequest request) throws Exception {
		SwHtmlInfo swHtmlInfo = swHtmlInfoVo.getSwHtmlInfo();//实体
		String id = PlatformTools.getID();//设置id
		swHtmlInfo.setId(id);
		//设置发布状态
		swHtmlInfo.setReleaseState("01");
		dao.save(swHtmlInfo);
		return swHtmlInfo;
	}


	/**
	 * 更新对象SwHtmlInfo
	 * @param 实体对象
	 */
	@Override
	public void updateSwHtmlInfo(SwHtmlInfoVo swHtmlInfoVo,HttpServletRequest request) throws Exception {
		//更新信息维护信息
		SwHtmlInfo swHtmlInfo = swHtmlInfoVo.getSwHtmlInfo();
		dao.update(swHtmlInfo);
	}

	/**
	 * 删除对象SwHtmlInfo
	 * @param id数据组
	 */
	@Override
	public void deleteSwHtmlInfo(String[] ids) {
		dao.delete(ids, SwHtmlInfo.class);		
	}

	/**
	 * 发布网站信息维护列表信息
	 * @param id数据组
	 */
	@Override
	@Transactional
	public void releaseseSwHtmlInfo(String[] ids) {
		Map<String,Object> param = new HashMap<>();
		param.put("ids", ids);
		param.put("releaseState", "02");
		param.put("releaseTime",new Timestamp(new Date().getTime()));
		//param.put("releaseTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
		dao.updateBySql("swHtmlInfo.sql.cancelOrRelease", param);
	}

	@Override
	public void cancelReleaseseSwHtmlInfo(String[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createHot(String[] ids) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Map<String, Object>> getWebsiteTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getWebsiteCompanyInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getWebsiteMainBusiness() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getWebsiteManagerWishs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Map<String, Object>>> getWebsitePowerDeal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getWebsitePowerService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Map<String, Object>>> getWebsiteSpecial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getWebsitePolicy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getWebsiteFriendshipLink() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getWebsiteColumnHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> gethtmlMessage(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getWebsiteColumnContent(String id, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSwHtmlInfoList(List<SwHtmlInfo> swHtmlInfoList) {
		// TODO Auto-generated method stub
		
	}


}