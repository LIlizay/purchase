package com.hhwy.purchaseweb.settlement.fujian.domain;

import java.math.BigDecimal;
import java.util.List;

import com.hhwy.purchase.domain.SmCompanyCostDetail;

 /**
 * <b>类 名 称：</b>FormDataDetailFj<br/>
 * <b>类 描 述：</b><br/> 福建结算编辑页面form表单的detail类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年11月21日 下午2:10:07<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class FormDataAndCostListFj{
	
	//结算id也是方案id ： settleId / schemeId	  //福建省一个结算只有一个方案，所以设置方案id为结算id
	private String id;
	 //年月
    private String ym;
    
    
    //以下为结算方案中的数据
    //用户数
    private Integer consNum;
    
    //方案状态（0：草稿，1：归档）
    private String schemeStatus;
    
    //总委托电量
    private BigDecimal totalProxyPq;
    
    //结算电量
//    private BigDecimal deliveryPq;
    
    //总购电量（双边、竞价、挂牌电量和，兆瓦时）
    private BigDecimal totalPurchasePq;
    

    //其他字段
    
    private BigDecimal actualTotalPq;	//总实际用电量,总结算电量
    
    private BigDecimal devPq;	//总偏差电量 = 总实际用电量 - 总购电量
    
    private BigDecimal devPqProp;	//偏差电量比例 = 总偏差电量/总购电量 × 100  （值是乘以100之后的数）
    
    
    private BigDecimal devDam;	//偏差违约金(售电公司偏差考核)
    
    private BigDecimal companyPro;	//售电公司总利润 
    
    private BigDecimal consCheckedProTotal;	//用户偏差考核总费用(元)
    
    private String companyProfitId;	//售电公司收益id
    
    
    //燃煤标杆电价
    private BigDecimal modelPrc;

    
    //批发交易电费
    private BigDecimal costAmt;

    //批发交易平均电价
    private BigDecimal costPrc;

	//零售交易电费
    private BigDecimal incomeTotalAmt;

	//零售交易平均电价
    private BigDecimal incomePrc;
    
    //批发交易结算明细 列表数据
    private List<SmCompanyCostDetail> costDetailList;
    
    
    
    
    
    
    
    
    
    
    
    
	public BigDecimal getDevPq() {
		return devPq;
	}

	public void setDevPq(BigDecimal devPq) {
		this.devPq = devPq;
	}

	public BigDecimal getDevPqProp() {
		return devPqProp;
	}

	public void setDevPqProp(BigDecimal devPqProp) {
		this.devPqProp = devPqProp;
	}

	public List<SmCompanyCostDetail> getCostDetailList() {
		return costDetailList;
	}

	public void setCostDetailList(List<SmCompanyCostDetail> costDetailList) {
		this.costDetailList = costDetailList;
	}

	public BigDecimal getModelPrc() {
		return modelPrc;
	}

	public void setModelPrc(BigDecimal modelPrc) {
		this.modelPrc = modelPrc;
	}

	public BigDecimal getCostAmt() {
		return costAmt;
	}

	public void setCostAmt(BigDecimal costAmt) {
		this.costAmt = costAmt;
	}

	public BigDecimal getCostPrc() {
		return costPrc;
	}

	public void setCostPrc(BigDecimal costPrc) {
		this.costPrc = costPrc;
	}

	public BigDecimal getIncomeTotalAmt() {
		return incomeTotalAmt;
	}

	public void setIncomeTotalAmt(BigDecimal incomeTotalAmt) {
		this.incomeTotalAmt = incomeTotalAmt;
	}

	public BigDecimal getIncomePrc() {
		return incomePrc;
	}

	public void setIncomePrc(BigDecimal incomePrc) {
		this.incomePrc = incomePrc;
	}

	public BigDecimal getActualTotalPq() {
		return actualTotalPq;
	}

	public void setActualTotalPq(BigDecimal actualTotalPq) {
		this.actualTotalPq = actualTotalPq;
	}

	public String getSchemeStatus() {
		return schemeStatus;
	}

	public void setSchemeStatus(String schemeStatus) {
		this.schemeStatus = schemeStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public Integer getConsNum() {
		return consNum;
	}

	public void setConsNum(Integer consNum) {
		this.consNum = consNum;
	}

	public BigDecimal getTotalProxyPq() {
		return totalProxyPq;
	}

	public void setTotalProxyPq(BigDecimal totalProxyPq) {
		this.totalProxyPq = totalProxyPq;
	}

	public BigDecimal getTotalPurchasePq() {
		return totalPurchasePq;
	}

	public void setTotalPurchasePq(BigDecimal totalPurchasePq) {
		this.totalPurchasePq = totalPurchasePq;
	}

	public BigDecimal getDevDam() {
		return devDam;
	}

	public void setDevDam(BigDecimal devDam) {
		this.devDam = devDam;
	}

	public BigDecimal getCompanyPro() {
		return companyPro;
	}

	public void setCompanyPro(BigDecimal companyPro) {
		this.companyPro = companyPro;
	}

	public BigDecimal getConsCheckedProTotal() {
		return consCheckedProTotal;
	}

	public void setConsCheckedProTotal(BigDecimal consCheckedProTotal) {
		this.consCheckedProTotal = consCheckedProTotal;
	}

	public String getCompanyProfitId() {
		return companyProfitId;
	}

	public void setCompanyProfitId(String companyProfitId) {
		this.companyProfitId = companyProfitId;
	}
}