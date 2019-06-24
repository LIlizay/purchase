package com.hhwy.purchaseweb.login.swconsregister.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.login.swconsinfo.domain.RelaDetail;
import com.hhwy.purchaseweb.login.swconsinfo.domain.RelaVo;
import com.hhwy.purchaseweb.login.swconsregister.domain.SwConsRegisterDetail;
import com.hhwy.purchaseweb.login.swconsregister.domain.SwConsRegisterVo;
import com.hhwy.selling.domain.SwConsRegister;

/**
 * ISwConsRegisterService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SwConsRegisterService{
	
	/**
	 * 分页获取对象SwConsRegister
	 * @param SwConsRegisterVo
	 * @return QueryResult
	 */
	public QueryResult<SwConsRegisterDetail> getSwConsRegisterByPage(SwConsRegisterVo swConsRegisterVo) throws Exception;

	/**
	 * 根据查询条件获取对象SwConsRegister列表
	 * @param SwConsRegisterVo
	 * @return List
	 */
	public List<SwConsRegisterDetail> getSwConsRegisterListByParams(SwConsRegisterVo swConsRegisterVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SwConsRegister数量
	 * @param SwConsRegisterVo
	 * @return Integer
	 */
	public Integer getSwConsRegisterCountByParams(SwConsRegisterVo swConsRegisterVo);
	
	/**
     * 分页获取对象RelaDetail
     * @param RelaVo
     * @return QueryResult
     */
    public QueryResult<RelaDetail> getRelaDetailByPage(RelaVo relaVo) throws Exception;

    /**
     * 根据查询条件获取对象RelaDetail列表
     * @param RelaVo
     * @return List
     */
    public List<RelaDetail> getRelaDetailListByParams(RelaVo relaVo) throws Exception;
    
    /**
     * 根据查询条件获取对象RelaDetail数量
     * @param RelaVo
     * @return Integer
     */
    public Integer getRelaDetailCountByParams(RelaVo relaVo);
	
	/**
	 * 根据查询条件获取单个对象SwConsRegister
	 * @param SwConsRegisterVo
	 * @return SwConsRegister
	 */
	public SwConsRegisterDetail getSwConsRegisterOneByParams(SwConsRegisterVo swConsRegisterVo) throws Exception;
	
	/**
	 * 根据ID获取对象SwConsRegister
	 * @param ID
	 * @return SwConsRegister
	 */
	public SwConsRegister getSwConsRegisterById(String id);
	
	/**
	 * 审核通过
	 * approvalExamine(描述这个方法的作用)<br/>
	 * @param id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void approvalExamine(String id);
	
	/**
	 * 审核不通过
	 * unApprovalExamine(描述这个方法的作用)<br/>
	 * @param id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void unApprovalExamine(List<SwConsRegisterDetail> swConsRegisterDetailList) throws Exception;
	
	/**
	 * 添加对象SwConsRegister
	 * @param 实体对象
	 * @return null
	 */
	public void saveSwConsRegister(SwConsRegister swConsRegister);
	
	/**
	 * 添加对象SwConsRegister列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSwConsRegisterList(List<SwConsRegister> swConsRegisterList);
	
	/**
	 * 更新对象SwConsRegister
	 * @param 实体对象
	 * @return SwConsRegister
	 */
	public void updateSwConsRegister(SwConsRegister swConsRegister);
	
	/**
	 * 删除对象SwConsRegister
	 * @param id数据组
	 */
	public void deleteSwConsRegister(String[] ids);

}