package com.gavin.foo.crud.config.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.gavin.foo.crud.job.DistributeJob;

import lombok.Data;

/**
 * Springboot config类：配置中心，esjob实例
 * 
 * @author guangyin.gu
 *
 */
@Component
@Data
@Configuration
@ConfigurationProperties(prefix = "esjob")
public class ElasticJobConfig {

	@Value("${esjob.serverlists}")
	private String serverlists;
	@Value("${esjob.namespace}")
	private String namespace;

	@Value("${esjob.cron}")
	private String cron;
	
	@Value("${esjob.sharding-total-count}")
	private int shardingTotalCount;
	
	@Value("${esjob.sharding-item-parameters}")
	private String shardingItemParameters;

	@Value("${esjob.job-description}")
	private String jobDescription;
	
	@Value("${esjob.job-parameter}")
	private String jobParameter;

	@Autowired
	private DistributeJob otajob;

	@Autowired
	private ZookeeperRegistryCenter regCenter;

	@Autowired
	private LiteJobConfiguration liteJobConfiguration;

	@Bean(initMethod = "init")
	public JobScheduler simpleJobScheduler() {
		return new SpringJobScheduler(otajob, regCenter, liteJobConfiguration);
	}

	@Bean
	public LiteJobConfiguration liteJobConfiguration() {
		JobCoreConfiguration.Builder builder = JobCoreConfiguration.newBuilder(otajob.getClass().getName(), cron,
				shardingTotalCount);
		JobCoreConfiguration jobCoreConfiguration = builder.shardingItemParameters(shardingItemParameters)
				.description(jobDescription).jobParameter(jobParameter).build();
		SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration,
				otajob.getClass().getCanonicalName());
		return LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build();
	}

	@Bean
	public ZookeeperConfiguration zkConfig() {
		return new ZookeeperConfiguration(serverlists, namespace);
	}

	@Bean(initMethod = "init")
	public ZookeeperRegistryCenter regCenter(ZookeeperConfiguration config) {
		return new ZookeeperRegistryCenter(config);
	}
}
