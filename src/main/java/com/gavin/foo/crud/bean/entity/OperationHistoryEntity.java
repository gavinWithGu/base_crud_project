package com.gavin.foo.crud.bean.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
@TableName("operation_history")
@ApiModel(value="OperationHistoryEntity对象", description="")
public class OperationHistoryEntity extends Model<OperationHistoryEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    @TableField("_type")
    private Integer type;

    @TableField("_content")
    private String content;
    
    @TableField("_ctime")
    private Date ctime;

    @TableField("_ip")
    private String ip;
    
    @TableField("_operator")
    private String operator;
    
    @Override
    protected Serializable pkVal() {
        return null;
    }

}
