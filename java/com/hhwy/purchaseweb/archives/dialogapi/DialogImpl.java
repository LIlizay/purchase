package com.hhwy.purchaseweb.archives.dialogapi;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseapi.scconsumerinfo.domain.ConsDialogDetail;
import com.hhwy.purchaseapi.scconsumerinfo.domain.ConsDialogVo;
import com.hhwy.purchaseapi.scconsumerinfo.service.DialogInterface;
import com.hhwy.purchaseapi.util.PlatformResult;
import com.hhwy.rest.service.annotation.RestService;


/**
 * ScConsumerInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestService(DialogInterface.class)
public class DialogImpl implements DialogInterface {

	public static final Logger log = LoggerFactory.getLogger(DialogImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PlatformResult<ConsDialogDetail> getConsList(ConsDialogVo consDialogVo) throws Exception {
		PlatformResult<ConsDialogDetail> platformResult = new PlatformResult<ConsDialogDetail>();
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(consDialogVo == null){
			consDialogVo = new ConsDialogVo();
		}
		//Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phConsDialog.sql.getConsDialogCountByParams",consDialogVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		List<ConsDetail> consList = (List<ConsDetail>)dao.getBySql("phConsDialog.sql.getConsDialogListForAPI",consDialogVo);
		//Parameter.isFilterData.set(false);
		ConvertListUtil.convert(consList);
		List<ConsDialogDetail> list = new ArrayList<ConsDialogDetail>();
		for(ConsDetail cons : consList){
			ConsDialogDetail consDetail = new ConsDialogDetail();
			BeanUtils.copyProperties(cons, consDetail);
			list.add(consDetail);
		}
		platformResult.setCount(total);
		platformResult.setData(list);
		return platformResult;
	}


	
}