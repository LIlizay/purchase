package com.hhwy.purchaseweb.swknowledge.service.imp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.swknowledge.domain.SwKnowledge;
import com.hhwy.purchaseweb.swknowledge.domain.SwKnowledgeDetail;
import com.hhwy.purchaseweb.swknowledge.domain.SwKnowledgeVo;
import com.hhwy.purchaseweb.swknowledge.service.SwKnowledgeService;

/**
 * KnowledgeService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@SuppressWarnings("unchecked")
@Component
public class SwKnowledgeServiceImpl implements SwKnowledgeService {
	public static final Logger log = LoggerFactory.getLogger(SwKnowledgeServiceImpl.class);
	
	@Autowired
	DAO<?> dao;

	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

	@Override
	public QueryResult<SwKnowledgeDetail> getSwKnowledgeByPage(SwKnowledgeVo swKnowledgeVo) throws Exception {
		QueryResult<SwKnowledgeDetail> queryResult = new QueryResult<SwKnowledgeDetail>();
		long total = getSwKnowledgeCountByParams(swKnowledgeVo);
		List<SwKnowledgeDetail> knowledgeDetailList = getSwKnowledgeListByParams(swKnowledgeVo);
		for(int i = 0 ; i < knowledgeDetailList.size() ; i++){
			if("000000".equals(knowledgeDetailList.get(i).getProvinceCode())){
				knowledgeDetailList.get(i).setProvinceCodeName("全国");
			}
		}
		queryResult.setTotal(total);
		queryResult.setData(knowledgeDetailList);
		return queryResult;
	}

	/**
	 * 分页获取对象Knowledge
	 * @param ID
	 * @return Knowledge
	 */
	@Override
	public List<SwKnowledgeDetail> getSwKnowledgeListByParams(SwKnowledgeVo swKnowledgeVo) throws Exception {
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(swKnowledgeVo == null){
			swKnowledgeVo = new SwKnowledgeVo();
		}
		List<SwKnowledgeDetail> knowledgeDetailList = (List<SwKnowledgeDetail>)dao.getBySql("swKnowledge.sql.getSwKnowledgeListByParams",swKnowledgeVo);
		ConvertListUtil.convert(knowledgeDetailList);
		return knowledgeDetailList;
	}

	/**
	 * 根据查询条件获取对象Knowledge数量
	 * @param SwKnowledgeVo
	 * @return Integer
	 */
	@Override
	public Integer getSwKnowledgeCountByParams(SwKnowledgeVo swKnowledgeVo) {
		Object result = dao.getOneBySQL("swKnowledge.sql.getSwKnowledgeCountByParams",swKnowledgeVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}


	/**
	 * 根据ID获取对象Knowledge
	 * @param ID
	 * @return Knowledge
	 * @throws Exception 
	 */
	@Override
	public SwKnowledgeDetail getSwKnowledgeById(String id,HttpServletRequest request) throws Exception {
		SwKnowledgeDetail swKnowledgeDetail = new SwKnowledgeDetail();
		//获取信息维护信息
		SwKnowledge swKnowledge = dao.findById(id, SwKnowledge.class);
		BeanUtils.copyProperties(swKnowledge, swKnowledgeDetail);
		return swKnowledgeDetail;
	}

	/**
	 * 添加对象Knowledge
	 * @param 实体对象
	 * @throws Exception 
	 */
	@Override
	public SwKnowledge saveSwKnowledge(SwKnowledgeVo swKnowledgeVo,HttpServletRequest request) throws Exception {
		SwKnowledge swKnowledge = swKnowledgeVo.getSwKnowledge();//实体
		String id = PlatformTools.getID();//设置id
		swKnowledge.setId(id);
		dao.save(swKnowledge);
		return swKnowledge;
	}


	/**
	 * 更新对象Knowledge
	 * @param 实体对象
	 */
	@Override
	public void updateSwKnowledge(SwKnowledgeVo swKnowledgeVo,HttpServletRequest request) throws Exception {
		//更新信息维护信息
		SwKnowledge swKnowledge = swKnowledgeVo.getSwKnowledge();
		dao.update(swKnowledge);
	}

	/**
	 * 删除对象Knowledge
	 * @param id数据组
	 */
	@Override
	public void deleteSwKnowledge(String[] ids) {
		dao.delete(ids, SwKnowledge.class);		
	}

	@Override
	public SwKnowledgeDetail getSwKnowledgeOneByParams(SwKnowledgeVo swKnowledgeVo)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSwKnowledgeList(List<SwKnowledge> swKnowledgeList) {
		// TODO Auto-generated method stub
		
	}


}