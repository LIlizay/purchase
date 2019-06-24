package com.hhwy.purchaseweb.swhtmlinfo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.swhtmlinfo.domain.SwHtmlInfo;
import com.hhwy.purchaseweb.swhtmlinfo.domain.SwHtmlInfoDetail;
import com.hhwy.purchaseweb.swhtmlinfo.domain.SwHtmlInfoVo;

/**
 * ISwHtmlInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SwHtmlInfoService{
	
	/**
	  * @Title: getSwHtmlInfoByPage
	  * @Description: 分页获取对象SwHtmlInfo
	  * @param swHtmlInfoVo
	  * @return QueryResult<SwHtmlInfoDetail>
	  * @throws Exception
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午3:30:57
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午3:30:57
	  * @since  1.0.0
	 */
	public QueryResult<SwHtmlInfoDetail> getSwHtmlInfoByPage(SwHtmlInfoVo swHtmlInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象SwHtmlInfo列表
	 * @param SwHtmlInfoVo
	 * @return List
	 */
	public List<SwHtmlInfoDetail> getSwHtmlInfoListByParams(SwHtmlInfoVo swHtmlInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SwHtmlInfo数量
	 * @param SwHtmlInfoVo
	 * @return Integer
	 */
	public Integer getSwHtmlInfoCountByParams(SwHtmlInfoVo swHtmlInfoVo);
	
	/**
	 * 根据查询条件获取单个对象SwHtmlInfo
	 * @param SwHtmlInfoVo
	 * @return SwHtmlInfo
	 */
	public SwHtmlInfoDetail getSwHtmlInfoOneByParams(SwHtmlInfoVo swHtmlInfoVo) throws Exception;
	
	/**
	  * @Title: getSwHtmlInfoById
	  * @Description: 根据ID获取对象SwHtmlInfo
	  * @param id
	  * @param request
	  * @return SwHtmlInfoDetail
	  * @throws Exception
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午5:05:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午5:05:31
	  * @since  1.0.0
	 */
	public SwHtmlInfoDetail getSwHtmlInfoById(String id,HttpServletRequest request) throws Exception;
	
	/**
	  * @Title: saveSwHtmlInfo
	  * @Description: 添加对象SwHtmlInfo
	  * @param swHtmlInfoVo
	  * @param request
	  * @return SwHtmlInfo
	  * @throws Exception
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午3:30:20
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午3:30:20
	  * @since  1.0.0
	 */
	public SwHtmlInfo saveSwHtmlInfo(SwHtmlInfoVo swHtmlInfoVo,HttpServletRequest request) throws Exception;
	
	/**
	 * 添加对象SwHtmlInfo列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSwHtmlInfoList(List<SwHtmlInfo> swHtmlInfoList);
	
	/**
	  * @Title: updateSwHtmlInfo
	  * @Description: 更新对象SwHtmlInfo
	  * @param swHtmlInfoVo
	  * @param void
	  * @throws Exception
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午5:30:09
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午5:30:09
	  * @since  1.0.0
	 */
	public void updateSwHtmlInfo(SwHtmlInfoVo swHtmlInfoVo,HttpServletRequest request) throws Exception;
	
	/**
	  * @Title: deleteSwHtmlInfo
	  * @Description: 删除对象SwHtmlInfo
	  * @param ids
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午5:48:15
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午5:48:15
	  * @since  1.0.0
	 */
	public void deleteSwHtmlInfo(String[] ids);
	
	/**
	  * @Title: releaseseSwHtmlInfo
	  * @Description: 发布网站信息维护列表信息
	  * @param ids
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月7日 下午6:40:47
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月7日 下午6:40:47
	  * @since  1.0.0
	 */
	public void releaseseSwHtmlInfo(String[] ids);
	
	/**
	 * 
	 * @Title: cancelReleaseseSwHtmlInfo
	 * @Description:(取消发布网站信息维护列表信息)
	 * @param ids 
	 * void
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年6月8日 下午1:57:40
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年6月8日 下午1:57:40
	 * @since  1.0.0
	 */
	public void cancelReleaseseSwHtmlInfo(String[] ids);
	
	/**
	 * 
	 * @Title: createHot
	 * @Description:(设置热点)
	 * @param ids 
	 * void
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年6月14日 下午7:48:52
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年6月14日 下午7:48:52
	 * @since  1.0.0
	 */
	public void createHot(String[] ids);

	/**
	 * 获取网站首页标题信息 getWebsiteTitle
	 */
	public List<Map<String, Object>> getWebsiteTitle();
	
	/**
	 * 获取网站首页公司资讯
	 */
	public List<Map<String, Object>> getWebsiteCompanyInfo();
	
	/**
	 * 获取网站首页主营业务
	 */
	public List<Map<String, Object>> getWebsiteMainBusiness();
	
	/**
	 * 获取网站首页总经理寄语
	 */
	public Map<String, Object> getWebsiteManagerWishs();
	
	/**
	 * 获取网站首页电力交易
	 */
	public List<List<Map<String, Object>>> getWebsitePowerDeal();
	
	/**
	 * 获取网站首页电力服务
	 */
	public List<Map<String, Object>> getWebsitePowerService();
	
	/**
	 * 获取网站首页专题专栏
	 */
	public List<List<Map<String, Object>>> getWebsiteSpecial();
	
	/**
	 * 获取网站首页政策法规
	 */
	public List<Map<String, Object>> getWebsitePolicy();
	
	/**
	 * 获取网站首页友情链接
	 */
	public List<Map<String, Object>> getWebsiteFriendshipLink();
	
	/**
	 * 获取网站首页栏目头列表
	 */
	public List<Map<String, Object>> getWebsiteColumnHeader();
	
	/**
	 * 
	 * @Title: gethtmlMessage
	 * @Description:(获取文章信息)
	 * @param id
	 * @return 
	 * Map<String,Object>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年6月19日 上午10:25:37
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年6月19日 上午10:25:37
	 * @since  1.0.0
	 */
	public Map<String,Object> gethtmlMessage(String id);
	
	
	/**
	 * 
	 * @Title: getWebsiteColumnContent
	 * @Description:(根据id获取菜单栏目下的信息)
	 * @param id
	 * @param type
	 * @return 
	 * Map<String,Object>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年6月20日 下午7:37:04
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年6月20日 下午7:37:04
	 * @since  1.0.0
	 */
	public Map<String,Object> getWebsiteColumnContent(String id,String type);
}