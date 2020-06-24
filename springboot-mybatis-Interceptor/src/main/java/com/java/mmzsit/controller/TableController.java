package com.java.mmzsit.controller;

import com.java.mmzsit.entity.TestDatas;
import com.java.mmzsit.service.AddData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ：mmzsit
 * @description：
 * @date ：2019/6/10 15:04
 */
@RestController
@RequestMapping()
@Slf4j(topic = "【TableController】Mybatis拦截器对数据库水平分表控制类")
@SuppressWarnings({"unchecked", "rawtypes"})
public class TableController {
    @Resource
    private AddData addData;

    @GetMapping("/add")
    public String uploadTripScan() {

        int result = 0;
        int j = 0;
        for (int i=0; i < 3; i++){
            TestDatas datas = new TestDatas();
            datas.setId(j++);
            datas.setName("数据"+j);
            datas.setAge(""+j);
            datas.setInformation("测试信息"+j++);
            datas.setUpdatedate((201905+i)+"01");
            result +=  addData.add(datas);
        }

        return "成功插入" + result + "条数据";
    }



    @PostMapping("/postadd")
    public String uploadTripScan(@RequestBody String reqdata) {

        return null;
    }
}
