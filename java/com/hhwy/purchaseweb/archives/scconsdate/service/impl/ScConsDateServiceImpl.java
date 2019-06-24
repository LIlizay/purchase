package com.hhwy.purchaseweb.archives.scconsdate.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scconsdate.domain.ScConsDateDetail;
import com.hhwy.purchaseweb.archives.scconsdate.domain.ScConsDateVo;
import com.hhwy.purchaseweb.archives.scconsdate.service.ScConsDateService;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.service.ScMpCountService;
import com.hhwy.selling.domain.ScConsDate;

/**
 * <b>类 名 称：</b>ScConsDateServiceImpl<br/>
 * <b>类 描 述：</b><br/>			用户例日维护service实现类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月12日 上午11:02:25<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class ScConsDateServiceImpl implements ScConsDateService {

	public static final Logger log = LoggerFactory.getLogger(ScConsDateServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * smConsPowerViewService	用电计划service
	 */
	@Autowired
	private ScMpCountService scMpCountService;
	
	/**
	 * @Title: getScConsDateByPage
	 * @Description: 根据consId查询分页获取对象ScConsDate列表
	 * @param scConsDateVo	有效属性为consId和分页属性
	 * @return
	 * @throws Exception 
	 * QueryResult<ScConsDate>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 下午4:35:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 下午4:35:36
	 * @since  1.0.0
	 */
	public QueryResult<ScConsDate> getScConsDateByPage(ScConsDateVo scConsDateVo) throws Exception{
		QueryResult<ScConsDate> queryResult = new QueryResult<ScConsDate>();
		long total = this.getScConsDateCountByConsId(scConsDateVo);
		List<ScConsDate> scConsDateList = this.getScConsDateListByConsId(scConsDateVo);
		queryResult.setTotal(total);
		queryResult.setData(scConsDateList);
		return queryResult;
	}	
	
	/**
	 * @Title: getScConsDateCountByConsId
	 * @Description: 根据consId查询获取对象ScConsDate数量
	 * @param scConsDateVo	有效属性为consId和分页属性
	 * @return 
	 * Integer
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 下午4:35:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 下午4:35:20
	 * @since  1.0.0
	 */
	public Integer getScConsDateCountByConsId(ScConsDateVo scConsDateVo){
		Object result = dao.getOneBySQL("scConsDate.sql.getScConsDateCountByConsId",scConsDateVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * @Title: getScConsDateListByConsId
	 * @Description: 根据consId查询获取对象ScConsDate列表
	 * @param scConsDateVo	有效属性为consId和分页属性
	 * @return
	 * @throws Exception 
	 * List<ScConsDate>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 下午4:34:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 下午4:34:36
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<ScConsDate> getScConsDateListByConsId(ScConsDateVo scConsDateVo) throws Exception{
		List<ScConsDate> scConsDateList = (List<ScConsDate>)dao.getBySql("scConsDate.sql.getScConsDateListByConsId",scConsDateVo);
//		ConvertListUtil.convert(scConsDateList);
		return scConsDateList;
	}
	
	/**
	 * @Title: getScConsDateListByConsId
	 * @Description: 根据consId查询获取对象ScConsDate列表
	 * @param scConsDateVo	有效属性为consId和分页属性
	 * @return
	 * @throws Exception 
	 * List<ScConsDate>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 下午4:34:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 下午4:34:36
	 * @since  1.0.0
	 */
	public ScConsDateDetail getLastScConsDateByConsId(String consId) throws Exception{
		ScConsDateDetail scConsDate = (ScConsDateDetail)dao.getOneBySQL("scConsDate.sql.getLastScConsDateByConsId",consId);
		List<ScConsDateDetail> list = new ArrayList<>();
		list.add(scConsDate);
		//码表转换
		ConvertListUtil.convert(list);
		return scConsDate;
	}
	
	/**
	 * @Title: saveScConsDateList
	 * @Description: 添加对象ScConsDate集合，不触发更新用电计划
	 * 					仅提供给：新增用户时批量保存例日数据，因为新增用户没有示数，所以不需要修改用电计划
	 * @param scConsDateList
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午1:23:10
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午1:23:10
	 * @since  1.0.0
	 */
	public void saveScConsDateList(List<ScConsDate> scConsDateList){
		dao.saveList(scConsDateList);
	}
	
	/**
	 * @Title: saveScConsDate
	 * @Description: 添加对象ScConsDate，并更新用电计划
	 * @param scConsDate
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午1:44:25
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午1:44:25
	 * @since  1.0.0
	 */
	@Transactional
	public void saveScConsDate(ScConsDate scConsDate) throws Exception{
		Object result = dao.getOneBySQL("scConsDate.sql.validateRepeat",scConsDate);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		if(total > 0) {
			throw new BusinessException("已存在当月例日,不允许重复添加！");
		}
		dao.save(scConsDate);
		//更新用电计划
		this.changeUsuallyDate(scConsDate.getConsId(), scConsDate.getYm());
	}
	
	/**
	 * @Title: saveScConsDate
	 * @Description: 更新对象ScConsDate，并更新用电计划
	 * @param scConsDate
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午1:44:25
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午1:44:25
	 * @since  1.0.0
	 */
	@Transactional
	public void updateScConsDate(ScConsDate scConsDate) throws Exception{
		//不能更新consId和ym字段
		ScConsDate entity = dao.findById(scConsDate.getId(), ScConsDate.class);
		if(!entity.getYm().equals(scConsDate.getYm())) {
			throw new BusinessException("不允许修改生效年月！");
		}
		scConsDate.setConsId(entity.getConsId());
		dao.update(scConsDate);
		//更新用电计划
		this.changeUsuallyDate(scConsDate.getConsId(), scConsDate.getYm());
	}
	
	/**
	 * @Title: deleteScConsDate
	 * @Description:  删除对象ScConsDate，并更新用电计划
	 * @param id 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月12日 上午11:30:57
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月12日 上午11:30:57
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@Transactional
	public void deleteScConsDate(String id)  throws Exception {
		ScConsDate entity = dao.findById(id, ScConsDate.class);
		dao.delete(new String[]{id}, ScConsDate.class);
		//更新用电计划
		this.changeUsuallyDate(entity.getConsId(), entity.getYm());
	}
	
	/**
	 * @Title: deleteScConsDateByConsIds
	 * @Description: 根据consIds删除用户例日信息(物理删除)
	 * @param consIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年4月26日 下午2:17:34
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年4月26日 下午2:17:34
	 * @since  1.0.0
	 */
	public void deleteScConsDateByConsIds(String[] consIds){
		dao.deleteBySql("scConsDate.sql.deleteScConsDateByConsId", consIds);
	}
	
	/**
	 * @Title: changeUsuallyDate
	 * @Description: 添加编辑删除一个例日，需要修改本月至下个例日的年月（若没有下个例日，则更新到当前月的下两个月（允许最多新增后两个月的用电计划））的用电计划
	 * @param consId
	 * @param ym 	yyyyMM格式
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午1:38:06
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午1:38:06
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@Transactional
	private void changeUsuallyDate(String consId, String ym) throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("consId", consId);
		param.put("ym", ym);		//(yyyyMM格式)
		String nextYm = (String)dao.getOneBySQL("scConsDate.sql.getNextDateYm",param);
		
		SimpleDateFormat formatYm = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        
		//影响到的最后一个月
        String endYm = null;
        calendar.add(Calendar.MONTH, 2); //现在时间的后两个月
        endYm = formatYm.format(calendar.getTime());
        
		if(nextYm != null && !"".equals(nextYm)) {	//如果有下个例日，则结束时间为此例日ym
			//如果“现在时间的后两个月” > “下个例日的ym”, 则更新endYm
			if(endYm.compareTo(nextYm) > 0) {
				endYm = nextYm;
			}
		}
		
		//至此开始ym为ym，结束ym为endYm，据此得出需要重新计算用电计划的所有年月：List<ym>
		if(ym.compareTo(endYm) > 0) {	//无需修改用电计划
			return;
		}
		List<String> ymList = new ArrayList<>();
		calendar.setTime(formatYm.parse(ym));
		while(true) {
			ymList.add(ym);
			if(ym.compareTo(endYm) >= 0 || ymList.size() >= 24) {
				break;
			}
			calendar.add(Calendar.MONTH, 1); //下个月
			ym = formatYm.format(calendar.getTime());
		}
		//调用scMpCountService示数service中的修改例日的接口重建多个月用电计划
		scMpCountService.rebuildPowerViewByYmList(consId,ymList);
	}
	/**
	 * @Title: getDateAndDays<br/>
	 * @Description: 根据用户id、ym(yyyyMM格式)，返回用电年月的开始日期与结束日期和天数<br/>
	 * @param consId 用户id
	 * @param ym 用电年月
	 * @return Map<String,Object> 包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate、isNatureYm
	 * 				value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）、int(没用boolean，方便扩展。0:存在抄表例日，按抄表年月； 1：不存在抄表例日，按照自然月)<br/>
	 * @throws Exception 
	 * Map<String,Object>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月12日 下午1:33:43
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月12日 下午1:33:43
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getDateAndDays(String consId, String ym) throws Exception{
		Map<String, String> param = new HashMap<String, String>();
		param.put("consId", consId);
		param.put("ym", ym);		//(yyyyMM格式)
		//查询例日数据，查询的例日是用参数ym 之前和本月的各最近一次例日,最多两条数据。第一条是之前的例日，第二条是本月的例日
		List<ScConsDate> scConsDateList = (List<ScConsDate>) dao.getBySql("scConsDate.sql.getUsuallyDateByConsId", param);
		
		SimpleDateFormat formatYmd = new SimpleDateFormat("yyyyMMdd");
		Date beginDate = null;  //开始时间
		Date endDate = null;	//结束时间
		int days = 0; //天数
		//计算用电月最后一天
        Calendar calendar = Calendar.getInstance();
        
        Map<String, Object> result = new HashMap<String, Object>();
		//如果没有对应例日数据，则是自然年月
		if(scConsDateList == null || scConsDateList.size() == 0){
			return this.getDateAndDaysByNatureYm(ym);
		}else if(scConsDateList.size() == 1) {	//只有一条例日信息
			//数据库中的例日年月
			String entityYm = scConsDateList.get(0).getYm();
			//数据库中的例日日期(特殊：00代表自然年月)
			String usuallyDate = scConsDateList.get(0).getDate();
			//自然年月
			if("00".equals(usuallyDate)) {
	        	return this.getDateAndDaysByNatureYm(ym);
	        }
			//参数ym 不可能小于  数据库中ym
			if(ym.compareTo(entityYm) == 0){	//参数ym == 数据库中ym, 则开始时间是本月1号，结束时间是本月例日
	        	result.put("startDate", ym + "01");
	        	result.put("endDate", ym + usuallyDate);
	        	result.put("usuallyDate", usuallyDate);
	        	result.put("days", Integer.parseInt(usuallyDate));
	        	result.put("isNatureYm", 0);
				return result;
			}else{	//参数ym > 数据库中ym, 则开始时间是上月例日日期后一天，结束时间是本月例日日期
				endDate = formatYmd.parse(ym + usuallyDate); //结束时间
				calendar.setTime(endDate);
				calendar.add(Calendar.MONTH, -1);
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				beginDate = calendar.getTime();
				days = (int)((endDate.getTime() - beginDate.getTime()) / (1000*3600*24) +1);
				
		        result.put("startDate", formatYmd.format(beginDate));
		        result.put("endDate", ym + usuallyDate);
		        result.put("usuallyDate", usuallyDate);
		        result.put("days", days);
		        result.put("isNatureYm", 0);
				return result;
			}
		}else{	//有2条例日信息, 则 第一条的ym（date暂定为usuallyDatePre） < 参数ym = 第二条的ym（date暂定为usuallyDate），
					//则开始日期为上个月的usuallyDatePre后一天，结束日期为本月的usuallyDate
			//开始例日日期(特殊：00代表自然年月)
			String usuallyDatePre = scConsDateList.get(0).getDate();
			//结束例日日期(特殊：00代表自然年月)
			String usuallyDate = scConsDateList.get(1).getDate();
			
			//自然年月
			if("00".equals(usuallyDatePre)) {
				beginDate = formatYmd.parse(ym + "01"); 
	        }else {
	        	beginDate = formatYmd.parse(ym + "01"); 
	        	calendar.setTime(beginDate);
				calendar.add(Calendar.MONTH, -1);
	        	calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(usuallyDatePre));
	        	calendar.add(Calendar.DAY_OF_MONTH, 1);
				beginDate = calendar.getTime();
	        }
			
			//自然年月
			if("00".equals(usuallyDate)) {
				endDate = formatYmd.parse(ym + "01"); 
				calendar.setTime(endDate);
				days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //本月天数
				calendar.set(Calendar.DAY_OF_MONTH, days);
				endDate = calendar.getTime();
	        }else {
	        	endDate = formatYmd.parse(ym + usuallyDate); //结束时间
	        }
			days = (int)((endDate.getTime() - beginDate.getTime()) / (1000*3600*24) +1);
			
	        result.put("startDate", formatYmd.format(beginDate));
	        result.put("endDate",  formatYmd.format(endDate));
	        result.put("usuallyDate", formatYmd.format(endDate).substring(6, 8));
	        result.put("days", days);
	        result.put("isNatureYm", 0);
			return result;
		}
	}
	/**
	 * @Title: getDateAndDaysByNatureYm
	 * @Description: 获取自然年月的开始结束时间及天数
	 * @param ym
	 * @return
	 * @throws Exception 
	 * Map<String,Object>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月12日 下午2:22:51
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月12日 下午2:22:51
	 * @since  1.0.0
	 */
	private Map<String, Object> getDateAndDaysByNatureYm(String ym) throws Exception{
		SimpleDateFormat formatYmd = new SimpleDateFormat("yyyyMMdd");
		
		//计算用电月最后一天
        Calendar calendar = Calendar.getInstance();
		Date beginDate = formatYmd.parse(ym + "01"); //开始时间
        calendar.setTime(beginDate);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //天数
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("startDate", ym + "01");
        result.put("endDate", ym + days);
        result.put("usuallyDate", String.valueOf(days));
        result.put("days", days);
        result.put("isNatureYm", 1);
		return result;
	}
}