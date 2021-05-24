package com.gavin.foo.crud.enumeration;

public enum SampleEnum {
    /** 软件包初始上传状态 */
    UPLOAD(1,"未验证"),
    /** 软件包测试中 */
    TESTING(2,"验证中"),
    /** 软件包校验通过 */
    CHECK_PASS(3,"验证不通过"),
    /** 软件包校验不通过 */
    UPLOAD_NO_PASS(4,"验证通过"),
    /** 软件包下架 */
    PUT_ON(5,"上架"),
    /** 软件包下架 */
    PUT_OFF(6,"下架"),
    /** 未定义异常信息 */
    UNDEFINE(-1, "未定义异常信息");

    /** 标识值 */
    private int code;
    /** 描述 */
    private String des;

    SampleEnum(int code, String des) {
        this.code = code;
        this.des = des;
    }

    /**
     * 根据枚举code，获取对应的文字描述
     *
     * @date: 2021/4/8 10:38
     * @param:
     * @param code
     * @return:
     * @return java.lang.String
     */
    public static String msg(int code) {
        for (SampleEnum m : SampleEnum.values()) {
            if (m.getCode() == code) {
                return m.getDes();
            }
        }
        return UNDEFINE.getDes();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
