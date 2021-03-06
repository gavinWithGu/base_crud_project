package com.gavin.foo.crud.util;

/**
 * @author Erwin Feng
 * @since 2019-04-17 12:04
 */
public class DataBaseGeneratorConfig {

	/**
     * ========
     * ==包名===
     * ========
     */
    /** 包名：controller */
    public static final String PACKAGE_NAME_CONTROLLER = "controller";

    /** 包名：service */
    public static final String PACKAGE_NAME_SERVICE = "service";

    /** 包名：service.impl */
    public static final String PACKAGE_NAME_SERVICE_IMPL = "service.impl";

    /** 包名：model */
    public static final String PACKAGE_NAME_MODEL = "bean.entity";

    /** 包名：dao */
    public static final String PACKAGE_NAME_DAO = "dao";

    /** 包名：xml */
    public static final String PACKAGE_NAME_XML = "mapper";

    /**
     * ========
     * ==后缀名==
     * ========
     */
    /** 文件名后缀：Model */
    public static final String FILE_NAME_MODEL = "%sEntity";

    /** 文件名后缀：Dao */
    public static final String FILE_NAME_DAO = "%sDao";

    /** 文件名后缀：Mapper */
    public static final String FILE_NAME_XML = "%sMapper";

    /** MP开头，Service结尾 */
    public static final String FILE_NAME_SERVICE = "%sService";

    /** 文件名后缀：ServiceImpl */
    public static final String FILE_NAME_SERVICE_IMPL = "%sServiceImpl";

    /** 文件名后缀：Controller */
    public static final String FILE_NAME_CONTROLLER = "%sController";
    
    

    /** 逻辑删除字段 */
    public static final String FIELD_LOGIC_DELETE_NAME = "deleted";

    /** 作者 */
    public static final String AUTHOR = "Gavin";

    /** 生成文件的输出目录 */
    public static String projectPath = System.getProperty("user.dir");

    /** 输出目录 */
//    public static final String outputDir = projectPath + "/src/main/java";
    public static final String outputDir = "/Users/code-generator";

    /** 模板引擎。velocity / freemarker / beetl */
    
    public static interface TEMPLATE_ENGINE{
    	public static final String EMPLATE_ENGINE_VELOCITY = "velocity";
    	public static final String EMPLATE_ENGINE_FREEMARK = "freemarker";
    	public static final String EMPLATE_ENGINE_BEETL = "beetl";
    }

    /** 是否支持Swagger，默认不支持 */
    public static final Boolean SWAGGER_SUPPORT = true;

}
