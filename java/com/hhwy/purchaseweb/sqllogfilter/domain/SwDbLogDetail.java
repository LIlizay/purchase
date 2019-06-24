package com.hhwy.purchaseweb.sqllogfilter.domain;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * <b>类 名 称：</b>SwDbLog<br/>
 * <b>类 描 述：</b><br/>  mysql中执行的增删改sql的日志类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年7月26日 下午5:56:57<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SwDbLogDetail{

	private String id;
	
	private String createUser;
	private String updateUser;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp createTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp updateTime;
	
    //操作人id
    private String operator;
    
    //操作类型：新增，更新，删除
    private String type;
    
    //操作时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Date operateTime;
    
    //sql语句
    private String sql;
    
    //sql的参数
    private String params;
    
    //部门id
    private String orgNo;
    
    
    //售电公司域名
    private String companyDomain;
    //售电公司名称
    private String companyName;
    //售电公司域名
    private String companyDatabase;
    
    
    
    
    
    
    
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyDatabase() {
		return companyDatabase;
	}

	public void setCompanyDatabase(String companyDatabase) {
		this.companyDatabase = companyDatabase;
	}

	public String getOperator(){
        return operator;
    }
    
    public void setOperator(String operator){
        this.operator = operator;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getSql(){
        return sql;
    }
    
    public void setSql(String sql){
        this.sql = sql;
    }
    
    public String getParams(){
        return params;
    }
    
    public void setParams(String params){
        this.params = params;
    }
    
    public String getCompanyDomain(){
        return companyDomain;
    }
    
    public void setCompanyDomain(String companyDomain){
        this.companyDomain = companyDomain;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}