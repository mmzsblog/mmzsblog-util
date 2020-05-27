package com.java.mmzsit.service;

import com.java.mmzsit.entity.TestDatas;

/**
 * @author ：mmzsit
 * @description：
 * @date ：2019/6/13 10:49
 */
public interface AddData {
    /**
     *  向数据库插入数据
     * @param datas
     * @return 更新条数
     */
    int add(TestDatas datas);

}
