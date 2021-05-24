package com.gavin.foo.crud.bean.dto;

import java.util.Date;

import lombok.Data;

/**
 * 用户dto类
 *
 * @Author Gavin
 * @Description 文件说明
 * @Date 2021/4/7 17:05
 **/
@Data
public class UserRespDTO {
    private int id;
    private String name;
    private String email;
    private Date ctime;
    private Date utime;
}
