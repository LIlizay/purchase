package com.hhwy.purchaseweb.ssfeedback.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.ssfeedback.domain.SsFeedbackDetail;
import com.hhwy.purchaseweb.ssfeedback.domain.SsFeedbackVo;
import com.hhwy.purchaseweb.ssfeedback.service.SsFeedbackService;
import com.hhwy.selling.domain.SsFeedback;

/**
 * 
 * 
 * <b>类 名 称：</b>SsFeedbackServiceImpl<br/>
 * <b>类 描 述：</b>意见反馈<br/>
 * <b>创 建 人：</b>zhangzhao<br/>
 * <b>修 改 人：</b>zhangzhao<br/>
 * <b>修改时间：</b>2018年3月12日 下午2:10:00<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class SsFeedbackServiceImpl implements SsFeedbackService {

	public static final Logger log = LoggerFactory.getLogger(SsFeedbackServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 
	 * @Title: getConsSatisfaction<br/>
	 * @Description:TODO(查用户满意程度码表)<br/>
	 * @return
	 * @throws Exception
	 * List<Map<String,Object>>
	 * @throws Exception <br/>
	 * QueryResult<TreeGridDetail><br/>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年3月12日 下午2:13:45
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年3月12日 下午2:13:45
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getConsSatisfaction() throws Exception {
		List<Map<String, Object>> getConsSatisfaction = (List<Map<String, Object>>) dao.getBySql("ssFeedback.sql.getConsSatisfaction", null);
		return getConsSatisfaction;
	}
	
	/**
	 * 添加对象SsFeedback（提交按钮）
	 * @param 实体对象
	 * @throws  
	 */
	public void saveSsFeedback(SsFeedback ssFeedback, String url) {
		//去主库查售电公司名称
		String company = dao.getOneBySQL("ssFeedback.sql.getCompany", url).toString();
		Date date = new Date();
		//保存 当前时间
		ssFeedback.setFeedbackTime(new Timestamp(date.getTime()));
		//保存 当前登陆人
		ssFeedback.setConsFeedback(SystemServiceUtil.getLoginUserName());
		//保存 当前登录公司
		ssFeedback.setCompany(company);
		//保存url  用于查看看反馈记录权限过滤
		ssFeedback.setUrl(url);
		dao.save(ssFeedback);
	}
	
	/**
	 * 分页获取对象SsFeedbackDetail
	 * @param ID 
	 * @return SsFeedbackDetail 日期格式 YYYY-mm-dd
	 */
	public QueryResult<SsFeedbackDetail> getSsFeedbackByPage(SsFeedbackVo ssFeedbackVo, String url) throws Exception{
		QueryResult<SsFeedbackDetail> queryResult = new QueryResult<SsFeedbackDetail>();
		//获取用户ID 是root显示所有的 不是 匹配url取数据
		String orgId = SystemServiceUtil.getLoginUserInfo().getOrgId();
		long total = getSsFeedbackCountByParams(ssFeedbackVo, url, orgId);
		List<SsFeedbackDetail> ssFeedbackList = getSsFeedbackListByParams(ssFeedbackVo, url, orgId);
		ConvertListUtil.convert(ssFeedbackList);
		queryResult.setTotal(total);
		queryResult.setData(ssFeedbackList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SsFeedbackDetail数量
	 * @param SsFeedbackVo
	 * @return Integer
	 */
	public Integer getSsFeedbackCountByParams(SsFeedbackVo ssFeedbackVo, String url, String orgId){
		if ("root".equals(orgId)) {
			ssFeedbackVo.setUrl(null);
		}else{
			ssFeedbackVo.setUrl(url);
		}
		Object result = dao.getOneBySQL("ssFeedback.sql.getSsFeedbackCountByParams",ssFeedbackVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SsFeedback列表
	 * @param SsFeedbackVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SsFeedbackDetail> getSsFeedbackListByParams(SsFeedbackVo ssFeedbackVo, String url, String orgId) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(ssFeedbackVo == null){
			ssFeedbackVo = new SsFeedbackVo();
		}
		if("root".equals(orgId)){
			ssFeedbackVo.setUrl(null);
		}else{
			ssFeedbackVo.setUrl(url);
		}
		List<SsFeedbackDetail> ssFeedbackList = (List<SsFeedbackDetail>)dao.getBySql("ssFeedback.sql.getSsFeedbackListByParams",ssFeedbackVo);
		ConvertListUtil.convert(ssFeedbackList);
		return ssFeedbackList;
	}
	/**
	 * 根据查询条件获取单个对象SsFeedback
	 * @param SsFeedbackVo
	 * @return SsFeedback
	 */
	public SsFeedbackDetail getSsFeedbackOneByParams(SsFeedbackVo ssFeedbackVo) throws Exception{
		SsFeedbackDetail ssFeedback = null;
		List<SsFeedbackDetail> ssFeedbackList = (List<SsFeedbackDetail>) getSsFeedbackListByParams(ssFeedbackVo);
		if(ssFeedbackList != null && ssFeedbackList.size() > 0){
			ssFeedback = ssFeedbackList.get(0);
		}
		return ssFeedback;
	}
	
	/**
	 * 根据ID获取对象SsFeedback
	 * @param ID
	 * @return SsFeedback
	 * @throws Exception 
	 */
	public SsFeedback getSsFeedbackById(String id) throws Exception {
		SsFeedback SsFeedback = dao.findById(id, SsFeedback.class);
//		SsFeedbackDetail ssFeedbackDetail = new SsFeedbackDetail();
		//需要码表转换
//		BeanUtils.copyProperties(ssFeedback, ssFeedbackDetail);
//		String feedbackTime = ssFeedback.getFeedbackTime().toString();
//		ssFeedbackDetail.setFeedbackTime(feedbackTime);
//		
//		List<SsFeedbackDetail> list = new ArrayList<>();
//	
//		list.add(ssFeedbackDetail);
//		ConvertListUtil.convert(list);
		return SsFeedback;
	}	
	
	
	
	/**
	 * 添加对象SsFeedback
	 * @param 实体对象
	 */
	public void saveSsFeedbackList(List<SsFeedback> ssFeedbackList) {
		dao.saveList(ssFeedbackList);
	}
	
	/**
	 * 更新对象SsFeedback
	 * @param 实体对象
	 */
	public void updateSsFeedback(SsFeedback ssFeedback) {
		dao.update(ssFeedback);
	}
	
	/**
	 * 删除对象SsFeedback
	 * @param id数据组
	 */
	public void deleteSsFeedback(String[] ids) {
		dao.delete(ids, SsFeedback.class);
	}

	@Override
	public List<SsFeedbackDetail> getSsFeedbackListByParams(SsFeedbackVo ssFeedbackVo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSsFeedbackCountByParams(SsFeedbackVo ssFeedbackVo) {
		// TODO Auto-generated method stub
		return null;
	}	
}