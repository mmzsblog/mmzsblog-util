package com.java.mmzsit.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分表规则
 * @author mmzsit
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TableSplitRule {

	public String tableName();
	
	//暂时只支持单参数
	public String paramName();
	
	public String targetName();
}
