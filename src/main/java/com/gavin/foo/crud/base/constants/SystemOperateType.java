package com.gavin.foo.crud.base.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Enum - 产品操作类型
 *
 * @author lilh
 * @date 2017/7/20 16:29
 */
public enum SystemOperateType {

    // 用户
    USER_ADD(1, "添加用户"),
    USER_DELETE(2, "删除用户"),
    USER_UPDATE(3, "更新用户");
    
    private static Map<Integer, String> codeToDesc;
    private static Map<String, SystemOperateType> commandToOperateType = new HashMap<>();

    static {
        codeToDesc = Arrays.stream(SystemOperateType.values()).collect(Collectors.toMap(item -> item.code, item -> item.desc));
    }

    private Integer code;
    private String desc;

    SystemOperateType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Integer code) {
        return codeToDesc.get(code);
    }

    public static SystemOperateType getOperateType(String commandType) {
        return commandToOperateType.get(commandType);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
