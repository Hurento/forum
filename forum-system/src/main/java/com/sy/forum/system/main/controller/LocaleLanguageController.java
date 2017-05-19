package com.sy.forum.system.main.controller;

import com.sy.forum.core.entity.GenericFinal;
import com.sy.forum.core.entity.Result;
import com.sy.forum.utils.LocaleUtil;
import com.sy.forum.utils.Utils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author SY
 * @Description: 语言切换
 * @Date 2017/5/18 16:46
 */
@Controller
@RequestMapping(value = "/localeLanguage")
public class LocaleLanguageController {
	
	/**
	 * 系统语言使用切换
	 */
	@RequestMapping(value = "/langType", method = RequestMethod.GET)
	public @ResponseBody Result login(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "lang") String lang) {
		Result result = new Result();
		if (!model.containsAttribute("contentModel")) {
			// 语言环境切换
			LocaleUtil.exchangeLocale(request, lang);
		}
		result.setMessage("/system/login?lang=" + (Utils.isEmpty(lang) ? LocaleContextHolder.getLocale() : lang));
		result.setResultCode(GenericFinal.MSG_SUCCESS_CODE);
		return result;
	}
	
}
