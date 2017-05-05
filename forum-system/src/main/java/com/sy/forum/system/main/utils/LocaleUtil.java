package com.sy.forum.system.main.utils;

import com.sy.forum.core.entity.SessionAttribute;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @Author SY
 * @Description: 语言工具类
 * @Date 2017/5/5 15:27
 */
public class LocaleUtil {
	private static MessageSource	messageSource;
	
	/**
	 * @Title: exchangeLocale
	 * @Description: 设置和切换语言环境
	 * @param @param request
	 * @param @param langType 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void exchangeLocale(HttpServletRequest request, String langType) {
		if (langType.equals("zh_CN")) {
			Locale locale = new Locale("zh", "CN");
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					locale);
			LocaleContextHolder.setLocale(locale);
			request.getSession().setAttribute(SessionAttribute.LOCALE, langType);
		} else if (langType.equals("en")) {
			Locale locale = new Locale("en", "US");
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					locale);
			LocaleContextHolder.setLocale(locale);
			request.getSession().setAttribute(SessionAttribute.LOCALE, langType);
		} else {
			request.getSession().setAttribute(SessionAttribute.LOCALE, langType);
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					LocaleContextHolder.getLocale());
		}
	}
	
	public static String loadLocalString(String baseName, String keyCode) {
		Locale locale = LocaleContextHolder.getLocale();
		// 获得资源文件
		// ResourceBundle rb = ResourceBundle.getBundle(baseName, locale);
		// return rb.getString(keyCode);
		return messageSource.getMessage(keyCode, null, locale);
	}
	
	public MessageSource getMessageSource() {
		return messageSource;
	}
	
	public static void setMessageSource(MessageSource messageSource) {
		LocaleUtil.messageSource = messageSource;
	}
	
}
