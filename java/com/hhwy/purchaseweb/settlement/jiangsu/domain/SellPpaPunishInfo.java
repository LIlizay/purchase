package com.hhwy.purchaseweb.settlement.jiangsu.domain;
import java.math.BigDecimal;

 /**
 * <b>类 名 称：</b>SellPpaPunishInfo<br/>
 * <b>类 描 述：</b><br/>		售电合同中的  用户违约惩罚方式  和  售电公司违约惩罚方式  相关信息
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年3月28日 下午3:47:22<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SellPpaPunishInfo{
	
//	cons.punish_type_upper upperPunishType,
//	cons.punish_type_lower lowerPunishType,
//	cons.upper_threshold upperPqProp,
//	cons.upper_type upperCheckPrcType,
//	cons.upper_prop upperCheckPrcProp,
//	cons.lower_threshold lowerPqProp,
//	cons.lower_type lowerCheckPrcType,
//	cons.lower_prop lowerCheckPrcProp,
//	cons.upper_prc upperCheckPrc,
//	cons.lower_prc lowerCheckPrc,
	
	//用户ID
	private String consId;
	
	//合同ID
    private String agreId;
    
    
    //正偏差惩罚类型
    private String punishTypeUpper;
    
    //正偏差域值
    private BigDecimal upperThreshold;
    
    //正偏差惩罚电价基准
    private String upperType;
    
    //超出部分惩罚比例值
    private BigDecimal upperProp;
    
    //正偏差惩罚协议价
    private BigDecimal upperPrc;
    
    
    
    //负偏差惩罚类型
    private String punishTypeLower;

    //负偏差域值
    private BigDecimal lowerThreshold;
    
    //负偏差惩罚电价基准
    private String lowerType;
    
    //负偏差惩罚比例值
    private BigDecimal lowerProp;
    
    //负偏差惩罚协议价
    private BigDecimal lowerPrc;
    
    //惩罚附加项(惩罚时额外附加条件是否生效)
//    private String punishFlag;
    
    //------------------------------------以上是用户违约惩罚方式的字段，下面是售电公司的违约惩罚方式字段-------------
    
//    CAST(comp.punish_flag AS CHAR) compFlag,
//	comp.lower_threshold compPqProp,
//	comp.lower_type compPrcType,
//	comp.lower_prc compPrc,
//	comp.lower_prop compPrcProp
    
    //是否赔偿
//    private String compPunishFlag;
//    
//    //赔偿域值
//    private BigDecimal compLowerThreshold;
//    
//    //赔偿基准
//    private String compLowerType;
//    
//    //赔偿协议价
//    private BigDecimal compLowerPrc;
//    
//    //赔偿比例值
//    private BigDecimal compLowerProp;

    
    
    
    
    
    
    
	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getAgreId() {
		return agreId;
	}

	public void setAgreId(String agreId) {
		this.agreId = agreId;
	}

	public String getPunishTypeUpper() {
		return punishTypeUpper;
	}

	public void setPunishTypeUpper(String punishTypeUpper) {
		this.punishTypeUpper = punishTypeUpper;
	}

	public String getPunishTypeLower() {
		return punishTypeLower;
	}

	public void setPunishTypeLower(String punishTypeLower) {
		this.punishTypeLower = punishTypeLower;
	}

	public BigDecimal getUpperThreshold() {
		return upperThreshold;
	}

	public void setUpperThreshold(BigDecimal upperThreshold) {
		this.upperThreshold = upperThreshold;
	}

	public String getUpperType() {
		return upperType;
	}

	public void setUpperType(String upperType) {
		this.upperType = upperType;
	}

	public BigDecimal getUpperProp() {
		return upperProp;
	}

	public void setUpperProp(BigDecimal upperProp) {
		this.upperProp = upperProp;
	}

	public BigDecimal getLowerThreshold() {
		return lowerThreshold;
	}

	public void setLowerThreshold(BigDecimal lowerThreshold) {
		this.lowerThreshold = lowerThreshold;
	}

	public String getLowerType() {
		return lowerType;
	}

	public void setLowerType(String lowerType) {
		this.lowerType = lowerType;
	}

	public BigDecimal getLowerProp() {
		return lowerProp;
	}

	public void setLowerProp(BigDecimal lowerProp) {
		this.lowerProp = lowerProp;
	}

	public BigDecimal getUpperPrc() {
		return upperPrc;
	}

	public void setUpperPrc(BigDecimal upperPrc) {
		this.upperPrc = upperPrc;
	}

	public BigDecimal getLowerPrc() {
		return lowerPrc;
	}

	public void setLowerPrc(BigDecimal lowerPrc) {
		this.lowerPrc = lowerPrc;
	}

}