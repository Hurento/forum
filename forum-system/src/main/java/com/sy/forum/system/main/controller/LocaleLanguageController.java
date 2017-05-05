package com.sy.forum.system.main.controller;

import com.sy.forum.system.main.utils.LocaleUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/localeLanguage")
public class LocaleLanguageController {
	
	/**
	 * 系统语言使用切换
	 */
	@RequestMapping(value = "/langType", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "langType", defaultValue = "zh_CN") String langType) {
		if (!model.containsAttribute("contentModel")) {
			// 语言环境切换
			LocaleUtil.exchangeLocale(request, langType);
		}
		return "redirect:/rest/index";
	}
	
}
