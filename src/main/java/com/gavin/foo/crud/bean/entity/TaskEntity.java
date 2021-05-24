package com.gavin.foo.crud.bean.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("task")
@ApiModel(value="Task对象", description="")
public class TaskEntity extends Model<TaskEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "_id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "任务名称")
    @TableField("_name")
    private String name;

    @ApiModelProperty(value = "任务状态")
    @TableField("_status")
    private Integer status;

}
