package com.gavin.foo.crud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gavin.foo.crud.bean.entity.TaskEntity;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Gavin
 * @since 2020-08-18
 */
public interface TaskDao extends BaseMapper<TaskEntity> {

	/**
	 * 获取分区数据
	 * 
	 * @param name
	 * @return
	 */
	@Select("select * from task where _id % #{total} = #{partition} and _status = #{status}")
	List<TaskEntity> selectPartitionByStatus(@Param("total") int total, @Param("partition") int partition,
                                             @Param("status") int status);
}
