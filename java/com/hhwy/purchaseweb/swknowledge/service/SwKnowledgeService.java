package com.hhwy.purchaseweb.swknowledge.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.swknowledge.domain.SwKnowledge;
import com.hhwy.purchaseweb.swknowledge.domain.SwKnowledgeDetail;
import com.hhwy.purchaseweb.swknowledge.domain.SwKnowledgeVo;

/**
 * IKnowledgeService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SwKnowledgeService{
	
	/**
	  * @Title: getKnowledgeByPage
	  * @Description: 分页获取对象Knowledge
	  * @param SwKnowledgeVo
	  * @return QueryResult<KnowledgeDetail>
	  * @throws Exception
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午3:30:57
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午3:30:57
	  * @since  1.0.0
	 */
	public QueryResult<SwKnowledgeDetail> getSwKnowledgeByPage(SwKnowledgeVo swKnowledgeVo) throws Exception;

	/**
	 * 根据查询条件获取对象swKnowledge列表
	 * @param SwKnowledgeVo
	 * @return List
	 */
	public List<SwKnowledgeDetail> getSwKnowledgeListByParams(SwKnowledgeVo swKnowledgeVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象swKnowledge数量
	 * @param SwKnowledgeVo
	 * @return Integer
	 */
	public Integer getSwKnowledgeCountByParams(SwKnowledgeVo swKnowledgeVo);
	
	/**
	 * 根据查询条件获取单个对象swKnowledge
	 * @param SwKnowledgeVo
	 * @return Knowledge
	 */
	public SwKnowledgeDetail getSwKnowledgeOneByParams(SwKnowledgeVo swKnowledgeVo) throws Exception;
	
	/**
	  * @Title: getKnowledgeById
	  * @Description: 根据ID获取对象swKnowledge
	  * @param id
	  * @param request
	  * @return SwKnowledgeDetail
	  * @throws Exception
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午5:05:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午5:05:31
	  * @since  1.0.0
	 */
	public SwKnowledgeDetail getSwKnowledgeById(String id,HttpServletRequest request) throws Exception;
	
	/**
	  * @Title: saveKnowledge
	  * @Description: 添加对象Knowledge
	  * @param SwKnowledgeVo
	  * @param request
	  * @return Knowledge
	  * @throws Exception
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午3:30:20
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午3:30:20
	  * @since  1.0.0
	 */
	public SwKnowledge saveSwKnowledge(SwKnowledgeVo swKnowledgeVo,HttpServletRequest request) throws Exception;
	
	/**
	 * 添加对象Knowledge列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSwKnowledgeList(List<SwKnowledge> knowledgeList);
	
	/**
	  * @Title: updateKnowledge
	  * @Description: 更新对象Knowledge
	  * @param SwKnowledgeVo
	  * @param void
	  * @throws Exception
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午5:30:09
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午5:30:09
	  * @since  1.0.0
	 */
	public void updateSwKnowledge(SwKnowledgeVo swKnowledgeVo,HttpServletRequest request) throws Exception;
	
	/**
	  * @Title: deleteKnowledge
	  * @Description: 删除对象Knowledge
	  * @param ids
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午5:48:15
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午5:48:15
	  * @since  1.0.0
	 */
	public void deleteSwKnowledge(String[] ids);
	
}