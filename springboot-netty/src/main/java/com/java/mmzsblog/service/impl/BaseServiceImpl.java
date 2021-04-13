package com.java.mmzsblog.service.impl;

import com.java.mmzsblog.dao.IReceiveRecordMapper;
import com.java.mmzsblog.domain.entity.ReceiveRecord;
import com.puwang.cloud.socket.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：created by mmzsblog.cn
 * @description：
 * @date ：created at 2021/04/12 14:13
 */
@Slf4j
@Service
public class BaseServiceImpl implements BaseService {
    @Resource
    private IReceiveRecordMapper receiveRecordMapper;

    @Override
    public void doSomeThing(String content) {
        if (Strings.isNotBlank(content)) {
            log.info("调用service服务，接收到数据是：" + content);
            // 此处做数据处理
            receiveRecordMapper.insert(ReceiveRecord.builder().receiveContent(content).build());
        }
    }

}