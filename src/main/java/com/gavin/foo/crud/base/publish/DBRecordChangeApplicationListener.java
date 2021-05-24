package com.gavin.foo.crud.base.publish;

import java.util.Date;

import com.gavin.foo.crud.bean.entity.OperationHistoryEntity;
import com.gavin.foo.crud.dao.OperationHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Listener - 信息修改记录
 *
 * @author lilh
 * @date 2017/7/20 17:31
 */
@Component
@Slf4j
public class DBRecordChangeApplicationListener implements ApplicationListener<CommonChangeEvent> {

    @Autowired
    private OperationHistoryDao historyDao;

    @Override
    @Async
    public void onApplicationEvent(CommonChangeEvent event) {
        log.info("记录一条操作记录：{}", event.toString());
        OperationHistoryEntity history = resolve(event);
        historyDao.insert(history);
    }

    private OperationHistoryEntity resolve(CommonChangeEvent event) {
        OperationHistoryEntity history = new OperationHistoryEntity();
        history.setCtime(new Date());
        history.setType(event.getType().getCode());
        history.setIp(event.getIp());
        history.setContent(event.getContent());
        history.setOperator(event.getOperator());
        
        return history;
    }


}
