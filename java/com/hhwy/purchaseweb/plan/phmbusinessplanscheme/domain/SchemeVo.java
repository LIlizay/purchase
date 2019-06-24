package com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain;

import com.hhwy.purchase.domain.PhmBusinessPlanScheme;
import com.hhwy.purchase.domain.PhmForecastPrc;

public class SchemeVo {

    private PhmBusinessPlanScheme phmBusinessPlanScheme;
    
    private PhmForecastPrc phmForecastPrc;

    public PhmBusinessPlanScheme getPhmBusinessPlanScheme() {
        return phmBusinessPlanScheme;
    }

    public void setPhmBusinessPlanScheme(PhmBusinessPlanScheme phmBusinessPlanScheme) {
        this.phmBusinessPlanScheme = phmBusinessPlanScheme;
    }

    public PhmForecastPrc getPhmForecastPrc() {
        return phmForecastPrc;
    }

    public void setPhmForecastPrc(PhmForecastPrc phmForecastPrc) {
        this.phmForecastPrc = phmForecastPrc;
    }
}
