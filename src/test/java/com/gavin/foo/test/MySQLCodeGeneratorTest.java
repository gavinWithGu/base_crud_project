package com.gavin.foo.test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.gavin.foo.crud.util.DataBaseGeneratorUtils;
import org.junit.Test;


/**
 *
 * @Author Gavin
 * @Description 苞米豆自动生成代码工具
 * @Date 2021/4/9 10:41
 **/
public class MySQLCodeGeneratorTest {
    private static final String DB_URL = "jdbc:mysql://192.168.1.227:3306/mp?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    private static final String USER_NAME = "******";
    private static final String PASSWORD = "******";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String PACKAGE_NAME = "com.simon.cloud.iot.ota";  // 基础包名

    @Test
    public void generatorTest() {
        DbType dbType = DbType.MYSQL;
        // 表前缀，生成的实体类，不含前缀
        String[] tablePrefixes = {};
        // 表名，为空，生成所有的表
        String[] tableNames = {};
        // 字段前缀
        String[] fieldPrefixes = {};

        DataBaseGeneratorUtils.execute(dbType, DB_URL, USER_NAME, PASSWORD, DB_DRIVER, tablePrefixes, tableNames, PACKAGE_NAME, fieldPrefixes);
    }
}
