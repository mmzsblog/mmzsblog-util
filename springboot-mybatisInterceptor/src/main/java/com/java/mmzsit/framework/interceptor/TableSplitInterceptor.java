package com.java.mmzsit.framework.interceptor;

import com.java.mmzsit.framework.annotation.TableSplitRule;
import com.java.mmzsit.framework.annotation.TableSplitTarget;
import com.java.mmzsit.framework.mybatisStrategy.strategy.Strategy;
import com.java.mmzsit.framework.mybatisStrategy.StrategyManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * @author ：mmzsit
 * @description：策略分表拦截器
 * @date ：2019/6/14 10:10
 */
@Slf4j(topic="策略分表拦截器【TableSplitInterceptor】")

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class }) })
public class TableSplitInterceptor implements Interceptor {

    @Autowired
    StrategyManager strategyManager;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("进入mybatisSql拦截器：====================");
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler =
                MetaObject.forObject(statementHandler,SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
        doSplitTable(metaStatementHandler,parameterObject);
        // 传递给下一个拦截器处理
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object arg0) {
        //System.err.println(arg0.getClass());
        if (arg0 instanceof StatementHandler) {
            return Plugin.wrap(arg0, this);
        } else {
            return arg0;
        }
    }

    @Override
    public void setProperties(Properties arg0) {

    }

    private void doSplitTable(MetaObject metaStatementHandler,Object param) throws ClassNotFoundException{
        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        if (originalSql != null && !originalSql.equals("")) {
            log.info("分表前的SQL："+originalSql);
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            Class<?> classObj = Class.forName(className);
            // 根据配置自动生成分表SQL
            TableSplitTarget tableSplit = classObj.getAnnotation(TableSplitTarget.class);
            if(tableSplit==null||!tableSplit.interFale()) {
                return ;
            }
            TableSplitRule[] rules = tableSplit.rules();
            if (rules != null && rules.length>0) {

                String convertedSql= null;
                // StrategyManager可以使用ContextHelper策略帮助类获取，本次使用注入
                for(TableSplitRule rule : rules) {
                    Strategy strategy = null;

                    if(rule.targetName()!=null&&!rule.targetName().isEmpty()) {
                        strategy = strategyManager.getStrategy(rule.targetName());
                    }
                    if(!rule.paramName().isEmpty()&&!rule.tableName().isEmpty()) {

                        String paramValue = getParamValue(param, rule.paramName());
                        //System.err.println("paramValue:"+paramValue);
                        //获取 参数
                        String newTableName = strategy.returnTableName(rule.tableName(), paramValue);
                        try {
                            convertedSql = originalSql.replaceAll(rule.tableName(),newTableName );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                log.info("新sql是：" + convertedSql);
                metaStatementHandler.setValue("delegate.boundSql.sql",convertedSql);
            }
        }
    }

    public String getParamValue(Object obj,String paramName) {
        if(obj instanceof Map) {
            return (String) ((Map) obj).get(paramName);
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            //System.err.println(field.getName());
            if(field.getName().equalsIgnoreCase(paramName)) {
                try {
                    return (String) field.get(obj);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

}

