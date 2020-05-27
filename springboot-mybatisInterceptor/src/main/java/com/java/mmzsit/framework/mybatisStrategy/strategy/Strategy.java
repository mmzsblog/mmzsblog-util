package com.java.mmzsit.framework.mybatisStrategy.strategy;

/**
 * 分表策略服务接口
 * @author mmzsit
 *
 */
public interface Strategy {

    /**
     * 传入表名 和分表参数
     * @param tableName
     * @param splitParam
     * @return
     */
    String returnTableName(String tableName,String splitParam);

}
