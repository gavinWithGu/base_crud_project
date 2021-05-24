package com.gavin.foo.crud.controller;

import com.gavin.foo.crud.base.Constants;
import com.gavin.foo.crud.base.constants.SystemOperateType;
import com.gavin.foo.crud.base.db.page.PageDto;
import com.gavin.foo.crud.base.db.page.PageVo;
import com.gavin.foo.crud.base.vo.BaseDtoForMultiConditionQuery;
import com.gavin.foo.crud.base.vo.BaseDtoForQuery;
import com.gavin.foo.crud.base.web.RequestObject;
import com.gavin.foo.crud.base.web.ResponseObject;
import com.gavin.foo.crud.bean.dto.UserRespDTO;
import com.gavin.foo.crud.bean.entity.UserEntity;
import com.gavin.foo.crud.bean.vo.RedisObject;
import com.gavin.foo.crud.bean.vo.UserDtoForAdd;
import com.gavin.foo.crud.bean.vo.UserDtoForQuery;
import com.gavin.foo.crud.config.UriConfig;
import com.gavin.foo.crud.redis.RedisTemplateUtil;
import com.gavin.foo.crud.service.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@Api(value = "示例Controller", tags = "示例Controller")
public class SampleController extends BaseController<UserDtoForQuery> {

    @Autowired
    private UriConfig uriConfig;
    @Autowired
    private RedisTemplateUtil redisTemplate;

    /**
     * Service层
     */
    @Autowired
    private UserRepository userRepository;

    @ApiImplicitParam(paramType = "header", name = Constants.TOKEN_HEADER_NAME)
    @ApiOperation(value = "用户分页查询", notes = "分页查询", consumes = "application/json")
    @PostMapping(value = "/mysql/userPage")
    public ResponseObject<PageVo<UserRespDTO>> getListByPage(
            @RequestBody @Valid RequestObject<PageDto<BaseDtoForQuery>> body) {
        return ResponseObject.ok(userRepository.pageFind(body.getData(), UserEntity::getEmail, UserEntity::getName));
    }

    @ApiImplicitParam(paramType = "header", name = Constants.TOKEN_HEADER_NAME)
    @ApiOperation(value = "用户分页查询", notes = "分页查询", consumes = "application/json")
    @PostMapping(value = "/mysql/userPageV2")
    public ResponseObject<PageVo<UserRespDTO>> getListByPageV2(
            @RequestBody @Valid RequestObject<PageDto<BaseDtoForMultiConditionQuery>> body) {
        PageVo<UserRespDTO> result = userRepository.pageFindMultiCondition(body.getData());
        return ResponseObject.ok(result);
    }

    @ApiImplicitParam(paramType = "header", name = Constants.TOKEN_HEADER_NAME)
    @ApiOperation(value = "添加用户", notes = "添加", consumes = "application/json")
    @PostMapping(value = "/mysql/userAdd")
    public ResponseObject add(@RequestBody @Valid RequestObject<UserDtoForAdd> body) {

        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(body.getData(), user);
        userRepository.save(user);

        //记录操作记录
        int userId = user.getId();
        if (0 != userId) {
            this.publish(user.toString(), SystemOperateType.USER_ADD);
        }

        return ResponseObject.ok();
    }

    @ApiImplicitParam(paramType = "header", name = Constants.TOKEN_HEADER_NAME)
    @ApiOperation(value = "删除用户", notes = "批量删除用户", consumes = "application/json")
    @PostMapping(value = "/mysql/delete")
    public ResponseObject<String> delete(@RequestBody RequestObject<List<Long>> body) {
        List<Long> productIds = body.getData();
        userRepository.removeByIds(productIds);
        //记录操作记录
        this.publish(productIds.toString(), SystemOperateType.USER_DELETE);
        return ResponseObject.ok();
    }

    @ApiImplicitParam(paramType = "header", name = Constants.TOKEN_HEADER_NAME)
    @ApiOperation(value = "更新用户", notes = "更新用户", consumes = "application/json")
    @PostMapping(value = "/mysql/update")
    public ResponseObject<String> update(@RequestBody @Valid RequestObject<UserDtoForAdd> body) {
        UserDtoForAdd dto = body.getData();

        UserEntity po = new UserEntity();
        BeanUtils.copyProperties(dto, po);

        // 更新入库
        po.setUtime(new Date());
        userRepository.updateById(po);

		//记录操作记录
        this.publish(dto.toString(), SystemOperateType.USER_UPDATE);
        return ResponseObject.ok();
    }

    @ApiOperation(value = "获取配置中心配置", notes = "获取配置中心配置")
    @PostMapping(value = "/consul/keys")
    @RequestMapping(value = "/consul/keys", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseObject deivceData() {
        return ResponseObject.ok(uriConfig);
    }

    @ApiOperation(value = "设置Redis内容", notes = "设置Redis内容")
    @PostMapping(value = "/redis/set")
    public ResponseObject redisset(@RequestBody @Valid RequestObject<RedisObject> body) {
        RedisObject req = body.getData();
        redisTemplate.set(req.getKey(), req.getValue(), 10000);
        return ResponseObject.ok(true);
    }

    @ApiOperation(value = "获取Redis内容", notes = "获取Redis内容")
    @PostMapping(value = "/redis/get")
    public ResponseObject redisget(@RequestBody @Valid RequestObject<String> key) {
        String value = redisTemplate.get(key.getData());
        return ResponseObject.ok(value);
    }
}
