package com.java.mmzsit.service.impl;

import com.java.mmzsit.dao.TestdatasDao;
import com.java.mmzsit.entity.TestDatas;
import com.java.mmzsit.service.AddData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：mmzsit
 * @description：
 * @date ：2019/6/13 10:57
 */
@Service
@Slf4j(topic="数据插入分表【AddDataImpl】")
public class AddDataImpl implements AddData {

    @Resource
    TestdatasDao testdatasDao;

    /**
     *
     * @param datas
     * @return 影响数据条数
     */
    @Override
    public int add(TestDatas datas) {

        int flunceNum = testdatasDao.insert(datas);
        if(0 != flunceNum){
            log.info("插入数据成功");
        }else {
            log.info("插入数据失败");
        }

        return flunceNum;
    }

}
