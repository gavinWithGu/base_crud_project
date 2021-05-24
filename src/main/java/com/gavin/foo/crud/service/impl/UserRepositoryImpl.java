package com.gavin.foo.crud.service.impl;

import com.gavin.foo.crud.base.service.BaseService;
import com.gavin.foo.crud.bean.dto.UserRespDTO;
import com.gavin.foo.crud.bean.entity.UserEntity;
import com.gavin.foo.crud.dao.UserDao;
import com.gavin.foo.crud.service.UserRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gavin
 * @since 2020-08-18
 */
@Service
public class UserRepositoryImpl extends BaseService<UserEntity, UserRespDTO, UserDao> implements UserRepository {
}
