package com.java.mmzsit.dao;

import com.java.mmzsit.framework.annotation.TableSplitRule;
import com.java.mmzsit.framework.annotation.TableSplitTarget;
import com.java.mmzsit.framework.mybatisStrategy.StrategyManager;
import com.java.mmzsit.entity.TestDatas;

@TableSplitTarget(rules={@TableSplitRule(tableName="TESTDATAS",paramName="updatedate",targetName=StrategyManager._YYYYMM01)})
public interface TestdatasDao {

    /**
     * 插入数据
     * @param record
     * @return
     */
    int insert(TestDatas record);
}