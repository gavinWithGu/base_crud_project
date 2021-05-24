package com.gavin.foo.crud.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gavin.foo.crud.base.service.IBaseService;
import com.gavin.foo.crud.bean.dto.UserRespDTO;
import com.gavin.foo.crud.bean.entity.UserEntity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Gavin
 * @since 2020-08-18
 */
public interface UserRepository extends IBaseService<UserEntity, UserRespDTO, BaseMapper<UserEntity>> {
}
