/**   
 * @Title: BaseController.java 
 * @Package: com.sitech.prm.iease.framework 
 * @CopyRright (c)2008-2020: si-tech 
 * @Project: 易企算 webserver Maven Webapp
 * @Description: 所有controller类的基类
 * @author 人云 wanghwa@si-tech.com.cn
 * @date 2016-6-14 下午4:06:55 
 * @version V2.0   
 */
package com.zcxn.wp.cloud.common.web;

import com.zcxn.wp.cloud.common.constant.FrontConstant;
import com.zcxn.wp.cloud.common.vo.CurrentUser;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Title: BaseController
 * @Description: 实现controller类的一些公用的方法，供子类直接调用
 * @author 人云 wanghwa@si-tech.com.cn
 */
public class BaseController {

	/**
	 * 日志对象，提供给子类使用
	 */
	protected Logger logger;
	
	protected static final String RESPONSE_FAIL="fail";
	
	
	protected static final String RESPONSE_SUCCESS="success";

	/**
	 * 国际化
	 */
	@Autowired
	protected MessageSource messageSource;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * 
	 * @Title: getMessage
	 * @Description:获得国际化信息，只能子类调用
	 * @param @param key
	 * @param @param locale
	 * @param @return 参数说明
	 * @return String 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	protected String getMessage(String key, Locale locale) {
		try {
			return messageSource.getMessage(key, null, locale);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @Title: getMessage
	 * @Description: 获得国际化信息，只能子类调用
	 * @param @param key
	 * @param @param locale
	 * @param @param args
	 * @param @return 参数说明
	 * @return String 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	protected String getMessage(String key, Locale locale, String... args) {
		try {
			return messageSource.getMessage(key, args, null, locale);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @Description: 构造方法，初始化logger属性。
	 * @coustructor 方法.
	 */
	public BaseController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * 
	 * @Title: getCurrentUser
	 * @Description: 获得session信息，如果不存在抛出session过期异常
	 * @param @param request
	 * @param @return 参数说明
	 * @return Users 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	final protected CurrentUser getCurrentUser(HttpServletRequest request) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal != null && principal instanceof  CurrentUser)
		{
			return (CurrentUser)principal;
		}
		else
			throw new RuntimeException("登录已失效,请重新登陆！");
	}

	/**
	 *
	 * @Title: getCurrentUserNotReturnNull
	 * @Description: 获得session信息，如果不存在返回null
	 * @param @param request
	 * @param @return 参数说明
	 * @return CurrentUser 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	final protected CurrentUser getCurrentUserNotReturnNull(
			HttpServletRequest request) {
		return getCurrentUser(request);
	}

	/**
	 *
	 * @Title: isLogin
	 * @Description: 判断是否登陆
	 * @param @param request
	 * @param @return 参数说明
	 * @return boolean 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	final protected boolean isLogin(HttpServletRequest request) {

		try {
			CurrentUser user = getCurrentUser(request);

			if (null != user) {
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @Title: getRequestParam
	 * @Description: 获得请求参数的JSONObject对象
	 * @param @param request
	 * @param @return 参数说明
	 * @return JSONObject 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	@Deprecated
	final protected JSONObject getRequestParam(HttpServletRequest request) {
		System.out.println("ddddd"+request
				.getParameter(FrontConstant.JSON_PARAM_KEY));

		JSONObject params = JSONObject.fromObject(request
				.getParameter(FrontConstant.JSON_PARAM_KEY));
		return params;
	}
	
	
	/**
	 * 
	 * @Title: getRequestParam 
	 * @Description: 获得request的请求参数并转换为指定类型（TODO 未考虑日期转换、JSON空值处理，这里应该有JSONUtil等来支撑，暂时用不到先空着 sunyz）
	 * @param @param request
	 * @param @param classToConvert
	 * @param @return  参数说明 
	 * @return T    返回类型 
	 * @throws 
	 * @author:14K sunyz@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	@Deprecated
	final protected <T> T getRequestParam(HttpServletRequest request,Locale locale,Class<T> classToConvert) {
		try {
			Object jsonParam = request.getAttribute(FrontConstant.JSON_PARAM_KEY);
			if(null == jsonParam) return null;
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			mapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
			return mapper.readValue(jsonParam.toString(), classToConvert);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(getMessage("system.analyze_request_param_failed", locale));
		}
	}
	

	/**
	 * 
	 * @Title: getRequestParam 
	 * @Description: 获得request的请求参数并转换为指定类型（TODO 未考虑日期转换、JSON空值处理，这里应该有JSONUtil等来支撑，暂时用不到先空着 sunyz）
	 * @param @param request
	 * @param @param typeToConvert
	 * @param @return  参数说明 
	 * @return T    返回类型 
	 * @throws 
	 * @author:14K sunyz@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	@Deprecated
	final protected <T> T getRequestParam(HttpServletRequest request,Locale locale,TypeReference<T> typeToConvert) {
		try {
			Object jsonParam = request.getAttribute(FrontConstant.JSON_PARAM_KEY);
			if(null == jsonParam) return null;
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			mapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
			return mapper.readValue(jsonParam.toString(), typeToConvert);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(getMessage("system.analyze_request_param_failed", locale));
		}
	}
}
