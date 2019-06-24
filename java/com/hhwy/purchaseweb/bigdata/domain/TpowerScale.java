package com.hhwy.purchaseweb.bigdata.domain;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.framework.annotation.PropertyDesc;


 /**
 * <b>类 名 称：</b>TpowerScale<br/>
 * <b>类 描 述：</b><br/> 历史电量数据表    大数据用
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年4月24日 下午4:53:41<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class TpowerScale{

	private String id;
	 
	private String createUser;
	 
	private String updateUser;
	 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp createTime;
	 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp updateTime;
	
    @PropertyDesc("逻辑删除标志")
    private String deleteFlag;
    
    @PropertyDesc("版本标记")
    private Integer version;
    
    @PropertyDesc("用电年月")
    private String rcvblYm;
    
    @PropertyDesc("用户编号")
    private String consNo;
    
    @PropertyDesc("实际电量")
    private BigDecimal tPq;
    
    @PropertyDesc("申报电量")
    private BigDecimal tDeclarePq;
    
    @PropertyDesc("生产计划（产能）")
    private BigDecimal consProducePlan;
    
    
    
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getDeleteFlag(){
        return deleteFlag;
    }
    
    public void setDeleteFlag(String deleteFlag){
        this.deleteFlag = deleteFlag;
    }
    
    public Integer getVersion(){
        return version;
    }
    
    public void setVersion(Integer version){
        this.version = version;
    }
    
    public String getRcvblYm(){
        return rcvblYm;
    }
    
    public void setRcvblYm(String rcvblYm){
        this.rcvblYm = rcvblYm;
    }
    
    public String getConsNo(){
        return consNo;
    }
    
    public void setConsNo(String consNo){
        this.consNo = consNo;
    }
    
    public BigDecimal gettPq(){
        return tPq;
    }
    
    public void settPq(BigDecimal tPq){
        this.tPq = tPq;
    }
    
    public BigDecimal gettDeclarePq(){
        return tDeclarePq;
    }
    
    public void settDeclarePq(BigDecimal tDeclarePq){
        this.tDeclarePq = tDeclarePq;
    }
    
    public BigDecimal getConsProducePlan(){
        return consProducePlan;
    }
    
    public void setConsProducePlan(BigDecimal consProducePlan){
        this.consProducePlan = consProducePlan;
    }
    
}