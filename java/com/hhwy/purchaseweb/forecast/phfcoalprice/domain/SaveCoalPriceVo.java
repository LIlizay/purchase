package com.hhwy.purchaseweb.forecast.phfcoalprice.domain;

import java.util.List;

import com.hhwy.purchase.domain.PhfCoalPrice;

public class SaveCoalPriceVo {
    
    public String ym;
    
    public List<PhfCoalPrice> list;

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public List<PhfCoalPrice> getList() {
        return list;
    }

    public void setList(List<PhfCoalPrice> list) {
        this.list = list;
    }
}
