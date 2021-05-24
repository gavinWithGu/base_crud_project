package com.gavin.foo.crud.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.gavin.foo.crud.bean.entity.TaskEntity;
import com.gavin.foo.crud.dao.TaskDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @Author Gavin
 * @Description 分布式任务
 * @Date 2021/4/8 11:24
 **/
@Component
@Slf4j
public class DistributeJob implements SimpleJob {

	@Autowired
	private TaskDao taskDao;

	@Override
	public void execute(ShardingContext shardingContext) {
		log.debug("Start to execute ota push job");

		log.debug("------Thread ID: {}, 任务总片数: {}, 当前分片项: {}", Thread.currentThread().getId(),
				shardingContext.getShardingTotalCount(), shardingContext.getShardingItem());
		
		//按照分片获取任务
		List<TaskEntity> taskList = taskDao.selectPartitionByStatus(shardingContext.getShardingTotalCount(),shardingContext.getShardingItem(),0);
		
		//todo:处理具体的业务
		for (TaskEntity taskEntity : taskList) {
			log.debug(taskEntity.toString());
		}
		//将任务设置为完成状态
		
	}
}
