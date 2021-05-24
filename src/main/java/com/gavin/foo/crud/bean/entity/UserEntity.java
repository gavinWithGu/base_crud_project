package com.gavin.foo.crud.bean.entity;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gavin
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="UserEntity对象", description="")
public class UserEntity extends Model<UserEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "涓婚敭ID")
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @ApiModelProperty(value = "婵挸鎮?")
    @TableField("nickname")
    private String name;

    @ApiModelProperty(value = "閭")
    @TableField("email")
    private String email;

    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;

    @TableField("ctime")
    private Date ctime;

    @TableField("utime")
    private Date utime;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
