/**   
 * @Title: CommonConstant.java 
 * @Package: com.sitech.prm.iease.framework 
 * @CopyRright (c)2008-2020: si-tech 
 * @Project: 易企算 
 * @Description: 系统框架级常量定义
 * @author 人云 wanghwa@si-tech.com.cn
 * @date 2016-6-14 下午4:41:26 
 * @version V2.0   
 */
package com.zcxn.wp.cloud.common.constant;

/**
 * @Title: CommonConstant
 * @Description: 系统框架级常量定义
 * @author 人云 wanghwa@si-tech.com.cn
 */
public class CommonConstant {

	/**
	 * session存储当期那用户的key
	 */
	public static final String USER_CONTEXT = "currentuser";
	
	
	/**
	 * service层异常前缀
	 */
	public static final String BUSINESS_EXCEPTION_PREFIX = "SRV:";
	
	/**
	 * dao层异常前缀
	 */
	public static final String DAO_EXCEPTION_PREFIX = "DAO:";
	
	/**
	 * controller层异常前缀
	 */
	public static final String CONTROLLER_EXCEPTION_PREFIX = "CTL:";
	
	/**
	 * 有效标识
	 */
	public static final String VALID = "Y";
	
	/**
	 * 无效标识
	 */
	public static final String INVALID = "N";
	
}
