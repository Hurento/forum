package com.sy.forum.utils;

import com.sy.forum.core.entity.LocaleFinal;
import com.sy.forum.core.entity.SessionAttributeFinal;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @Author SY
 * @Description: 语言工具类
 * @Date 2017/5/5 15:27
 */
@Component
public class LocaleUtil {

	@Resource
	private MessageSource messageSource;
	
	/**
	 * @Title: exchangeLocale
	 * @Description: 设置和切换语言环境
	 * @param request
	 * @param lang 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void exchangeLocale(HttpServletRequest request, String lang) {
		if (LocaleFinal.LOCAL_ZH_CN.equals(lang)) {
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.SIMPLIFIED_CHINESE);
			LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
		} else if (LocaleFinal.LOCAL_EN_US.equals(lang)) {
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
			LocaleContextHolder.setLocale(Locale.US);
		} else {
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, LocaleContextHolder.getLocale());
		}
		request.getSession().setAttribute(SessionAttributeFinal.LOCALE, lang);
	}

	/**
	 * 获取键的值
	 * @param keyCode
	 * @return
	 */
	public String loadLocalString(String keyCode) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(keyCode, null, locale);
	}
}
