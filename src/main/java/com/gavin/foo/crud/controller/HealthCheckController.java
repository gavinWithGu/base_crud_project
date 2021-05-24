package com.gavin.foo.crud.controller;

import com.gavin.foo.crud.bean.vo.UserDtoForQuery;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "健康检查contorller", tags = "Consul健康检查")
public class HealthCheckController extends BaseController<UserDtoForQuery> {

    /**
     * 健康检查，给consul服务发现组件调用的
     *
     * @return
     */
    @GetMapping("/check")
    public String health() {
        return "Response from consul-health-server";
    }
}
