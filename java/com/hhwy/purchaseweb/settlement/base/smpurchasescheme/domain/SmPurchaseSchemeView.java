package com.hhwy.purchaseweb.settlement.base.smpurchasescheme.domain;
import java.math.BigDecimal;

/**
 * SmPurchaseScheme
 * @author hhwy			月度结算方案表detail类
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
public class SmPurchaseSchemeView  {

	private String id ;
	
    //结算id
    private String settleId;
    
    //年月
    private String ym;
    
    //方案状态（0：草稿，1：归档）
    private int schemeStatus;
    
    //方案名称
    private String schemeName;
    
    //公司收益
    private BigDecimal compProfit;
    
    //用户总收益
    private BigDecimal consProfit;
    
    //赔偿用户费用(元)
    private BigDecimal consCompensate;
    
    //结算电量
    private BigDecimal deliveryPq;
    
    //用户数
    private Integer consNum;
    
    //总委托电量
    private BigDecimal totalProxyPq;
    
    //总购电量（双边、竞价、挂牌电量和，兆瓦时）
    private BigDecimal totalPurchasePq;
    
    //双边总电量
    private BigDecimal totalLcPq;
    
    //竞价总电量
    private BigDecimal totalBidPq;
    
    //挂牌总电量
    private BigDecimal totalListedPq;
    
    //部门id
    private String orgNo;
    
    
    
    //附加属性
    
    //售电公司偏差考核	月度结算售电公司收益信息s_m_company_profit里的数据，用作方案列表展示
    private BigDecimal devDam;
    //零售市场购电收入总和
    private BigDecimal consAmtTotal;
    
    
    
    
    
    public BigDecimal getConsAmtTotal() {
		return consAmtTotal;
	}

	public void setConsAmtTotal(BigDecimal consAmtTotal) {
		this.consAmtTotal = consAmtTotal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getDevDam() {
		return devDam;
	}

	public void setDevDam(BigDecimal devDam) {
		this.devDam = devDam;
	}

	public String getSettleId(){
        return settleId;
    }
    
    public void setSettleId(String settleId){
        this.settleId = settleId;
    }
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public int getSchemeStatus() {
		return schemeStatus;
	}

	public void setSchemeStatus(int schemeStatus) {
		this.schemeStatus = schemeStatus;
	}

	public String getSchemeName(){
        return schemeName;
    }
    
    public void setSchemeName(String schemeName){
        this.schemeName = schemeName;
    }
    
    public BigDecimal getCompProfit(){
        return compProfit;
    }
    
    public void setCompProfit(BigDecimal compProfit){
        this.compProfit = compProfit;
    }
    
    public BigDecimal getConsProfit(){
        return consProfit;
    }
    
    public void setConsProfit(BigDecimal consProfit){
        this.consProfit = consProfit;
    }
    
    public BigDecimal getConsCompensate(){
        return consCompensate;
    }
    
    public void setConsCompensate(BigDecimal consCompensate){
        this.consCompensate = consCompensate;
    }
    
    public BigDecimal getDeliveryPq(){
        return deliveryPq;
    }
    
    public void setDeliveryPq(BigDecimal deliveryPq){
        this.deliveryPq = deliveryPq;
    }
    
    public Integer getConsNum(){
        return consNum;
    }
    
    public void setConsNum(Integer consNum){
        this.consNum = consNum;
    }
    
    public BigDecimal getTotalProxyPq(){
        return totalProxyPq;
    }
    
    public void setTotalProxyPq(BigDecimal totalProxyPq){
        this.totalProxyPq = totalProxyPq;
    }
    
    public BigDecimal getTotalPurchasePq(){
        return totalPurchasePq;
    }
    
    public void setTotalPurchasePq(BigDecimal totalPurchasePq){
        this.totalPurchasePq = totalPurchasePq;
    }
    
    public BigDecimal getTotalLcPq(){
        return totalLcPq;
    }
    
    public void setTotalLcPq(BigDecimal totalLcPq){
        this.totalLcPq = totalLcPq;
    }
    
    public BigDecimal getTotalBidPq(){
        return totalBidPq;
    }
    
    public void setTotalBidPq(BigDecimal totalBidPq){
        this.totalBidPq = totalBidPq;
    }
    
    public BigDecimal getTotalListedPq(){
        return totalListedPq;
    }
    
    public void setTotalListedPq(BigDecimal totalListedPq){
        this.totalListedPq = totalListedPq;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}