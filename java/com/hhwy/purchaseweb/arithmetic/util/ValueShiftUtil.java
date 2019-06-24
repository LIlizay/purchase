package com.hhwy.purchaseweb.arithmetic.util;

import java.lang.reflect.Field;

public class ValueShiftUtil {

	public static <O, I> I valueToValue(O output, I input){
		Class<? extends Object> outputClass = output.getClass();
		Class<? extends Object> inputClass = input.getClass();
		Field[] fields1 = outputClass.getDeclaredFields();
		Field[] fields2 = inputClass.getDeclaredFields();
		for (Field field1 : fields1) {
			try {
				field1.setAccessible(true);
				if(field1.get(output) == null){
					continue;
				}
			} catch (IllegalArgumentException e1) {
				System.err.println(e1);
				break;
			} catch (IllegalAccessException e1) {
				System.err.println(e1);
				break;
			}
			for (Field field2 : fields2) {
				if(field1.getName().equals(field2.getName()) && field1.getType().getName().equals(field2.getType().getName())){
					try {
						field2.setAccessible(true);
						field2.set(input, field1.get(output));
						field1.setAccessible(false);
						field2.setAccessible(false);
						break;
					} catch (IllegalArgumentException e) {
						System.err.println(e);
						break;
					} catch (IllegalAccessException e) {
						System.err.println(e);
						break;
					}
				}
			}
		}
		return input;
	}
	
}
