package com.johnny.boot.openshift.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.johnny.boot.openshift.web.session.SessionException;

@ControllerAdvice
@Controller
public class ExceptionHandlerController implements ErrorController {
	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResponseMessage errorJson(HttpServletRequest request,
			HttpServletResponse response) {

		return ResponseMessage.one().error("" + response.getStatus());

	}

	@ExceptionHandler(SessionException.class)
	public String handleIOException(SessionException ex) {
		return "redirect:/sample_error_403.html";
	}

	// SessionException

	@RequestMapping(value = ERROR_PATH)
	public String errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		return "redirect:/sample_error_" + response.getStatus() + ".html";
	}

	@Override
	public String getErrorPath() {

		return ERROR_PATH;
	}

}
