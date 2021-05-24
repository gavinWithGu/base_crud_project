package com.gavin.foo.crud.util;

/**
 * 静态变量配置类
 * 
 * @author guangyin.gu
 *
 */
public class ConstantValue {

	// ip白名单:所有ip都通过
	public static final String IP_ALL_ALLOWED = "*";

	public static final int AUTH_CODE_NOT_ACTIVEATED = 2;
	public static final int AUTH_CODE_ACTIVEATED = 1;
	public static final int AUTH_CODE_NOT_EXISTED = 0;
	
	public static final String AUTH_CODE_SUFFIX = "00000000";

	/*数据库相关常量*/
	public static interface DATABASE_CONSTANT {
		public static final String TABLE_NAME_AUTH_CODE_INFO = "auth_code_info";
		public static final String COLUMN_NAME_AUTH_CODE_INFO = "_auth_code";

		public static final String OP_TYPE_INSERT = "INSERT";
		public static final String OP_TYPE_UPDATE = "UPDATE";
	}

	/**
	 * HTTP错误码static接口
	 * 
	 * @author guangyin.gu
	 *
	 */
	public static interface HTTP_RESULT_CODE {
		public static final int CODE_VARIABLE_NULL_ERROR = 410;
		public static final int CODE_ORDER_QUOTA_NOT_ENOUTH_ERROR = 411;
		public static final int CODE_TOKEN_NOT_EXIST_ERROR = 412;
		public static final int CODE_VENDER_SN_NOT_EXIST_ERROR = 413;
		public static final int CODE_MODEL_ID_NOT_CORRECT_ERROR = 414;
		public static final int CODE_CLIENT_IP_NOT_IN_WHITE_LIST_NOT_ERROR = 415;

		public static final int CODE_SERVER_INTERNAL_ERROR = 500;

	}

	/**
	 * 二级缓存key前缀
	 * 
	 * @author guangyin.gu
	 *
	 */
	public static interface SECONDARY_CACHE_CONSTANT {
		public static final int REDIS_VALUE_EXPIRE_DEFAULT = Integer.MAX_VALUE;
		
		public static final String PREFIX_AUTHCODE = "authcode_";
	}

}
