package com.hhwy.purchaseweb.arithmetic.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ArithmentUtil {
	
	public static void main(String[] agrs){
//		SsAgreScheme ssAgreScheme = new SsAgreScheme();
//		ssAgreScheme.setLcPrc(BigDecimal.TEN);
//		Map<String,Object> map = transBeanToMap(ssAgreScheme);
//		TypeForPrc typeForPrc = new TypeForPrc();
//		typeForPrc = (TypeForPrc)transMapToBean(map, typeForPrc);
//		System.out.println(typeForPrc.getLcPrc());
	}
	  
    /**
     * 
     * @Title: transMapToBean
     * @Description: Map 转   bean
     * @param map
     * @param obj 
     * void
     * <b>创 建 人：</b>xucong<br/>
     * <b>创建时间:</b>2017年5月4日 下午3:47:11
     * <b>修 改 人：</b>xucong<br/>
     * <b>修改时间:</b>2017年5月4日 下午3:47:11
     * @since  1.0.0
     */
    public static Object transMapToBean(Map<String, Object> map, Object obj) { 
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                if (map.containsKey(key)) {  
                    Object value = map.get(key);  
                    // 得到property对应的setter方法  
                    Method setter = property.getWriteMethod();  
                    setter.invoke(obj, value);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("transMapToBean Error " + e);  
        }  
        return obj;  
    }  
  
    /**
     * 
     * @Title: transBeanToMap
     * @Description: bean 转   Map
     * @param obj
     * @return 
     * Map<String,Object>
     * <b>创 建 人：</b>xucong<br/>
     * <b>创建时间:</b>2017年5月4日 下午3:50:05
     * <b>修 改 人：</b>xucong<br/>
     * <b>修改时间:</b>2017年5月4日 下午3:50:05
     * @since  1.0.0
     */
    public static Map<String, Object> transBeanToMap(Object obj) {  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
                    map.put(key, value);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("transBeanToMap Error " + e);  
        }  
        return map;  
    } 

}
