package com.human.pulgin.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Excel导如注解插件
 * @author HF-121093-01
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {
	
	String exportName();
}
