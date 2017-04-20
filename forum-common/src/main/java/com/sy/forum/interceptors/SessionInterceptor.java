package com.sy.forum.interceptors;

import com.sy.forum.core.entity.SessionAttribute;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		System.err.println("SessionInterceptor preHandle");
		
		Object obj = request.getSession().getAttribute(SessionAttribute.USERINFO);
		if (null == obj) { // 未登录
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // 如果是ajax请求响应头会有，x-requested-with
				response.setHeader("sessionstatus", "timeout");// 在响应头设置session状态
			} else {
				response.sendRedirect(request.getContextPath() + "/index.html");
			}
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
