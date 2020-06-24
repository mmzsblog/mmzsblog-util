package com.java.mmzsit.framework.mybatisStrategy;

import com.java.mmzsit.framework.mybatisStrategy.strategy.Strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author ：mmzsit
 * @description：
 * @date ：2019/6/13 10:28
 */
public class StrategyManager {

    public static final String _YYYYMM01 = "YYYYMM01"; //策略名称

    public static final String _YYYYMMDD = "YYYYMMDD";

    public static final String _YYYYMM = "YYYYMM";

    private Map<String,Strategy> strategies = new ConcurrentHashMap<String,Strategy>(10);

    /**
     * 向管理器中添加策略
     * @param strategyName
     * @param strategy
     */
    public void addStrategy(String strategyName,Strategy strategy) {
        strategies.put(strategyName, strategy);
    }

    public  Strategy getStrategy(String key){
        return strategies.get(key);
    }

    public Map<String, Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Map<String, String> strategies) {
        for(Map.Entry<String, String> entry : strategies.entrySet()){
            try {
                this.strategies.put(entry.getKey(),(Strategy)Class.forName(entry.getValue()).newInstance());
            } catch (Exception e) {
                System.out.println("实例化策略出错"+e);
            }
        }
    }

}

