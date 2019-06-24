package com.hhwy.selling.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
 /**
 * <b>类 名 称：</b>SwDbLog<br/>
 * <b>类 描 述：</b><br/>  mysql中执行的增删改sql的日志类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年7月26日 下午5:56:57<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Entity(name="SwDbLog")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_w_db_log")
public class SwDbLog extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("操作人id")
    @Column(name="operator", nullable=true, length=32) 
    private String operator;
    
    @PropertyDesc("操作类型：新增，更新，删除")
    @Column(name="type", nullable=true, length=16) 
    private String type;
    
    @PropertyDesc("操作时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @Column(name="operate_time", nullable=true, length=19) 
    private Date operateTime;
    
    @PropertyDesc("sql语句")
    @Column(name="sql", nullable=true, length=2048) 
    private String sql;
    
    @PropertyDesc("sql的参数")
    @Column(name="params", nullable=true, length=2048) 
    private String params;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_domain", nullable=true, length=32) 
    private String companyDomain;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
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