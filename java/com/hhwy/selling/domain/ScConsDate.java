package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * <b>类 名 称：</b>ScConsDate<br/>
 * <b>类 描 述：</b><br/>		用户例日维护
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月12日 上午10:58:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Entity(name="ScConsDate")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_c_cons_date")
public class ScConsDate extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("启用年月（yyyyMM格式）")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("日期（dd格式），若为'00'则是按自然月")
    @Column(name="date", nullable=true, length=2) 
    private String date;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    
    
    
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String date){
        this.date = date;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}