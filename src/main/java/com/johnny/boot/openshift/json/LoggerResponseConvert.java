package com.johnny.boot.openshift.json;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

import com.johnny.boot.openshift.web.LogbackLoggerService;
import com.johnny.boot.openshift.web.ResponseMessage;

public class LoggerResponseConvert extends
		AbstractMessageConverterMethodProcessor {

	public LoggerResponseConvert(List<HttpMessageConverter<?>> messageConverters) {
		super(messageConverters);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return false;
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {

		return (AnnotationUtils.findAnnotation(returnType.getMethod()
				.getDeclaringClass(), WarpResponse.class) != null
				|| AnnotationUtils.findAnnotation(
						returnType.getContainingClass(), WarpResponse.class) != null || returnType
					.getMethodAnnotation(WarpResponse.class) != null);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {

		return null;

	}

	@Override
	protected <T> Object readWithMessageConverters(NativeWebRequest webRequest,
			MethodParameter methodParam, Type paramType) throws IOException,
			HttpMediaTypeNotSupportedException {

		return null;
	}

	@Override
	public void handleReturnValue(Object returnValue,
			MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws IOException,
			HttpMediaTypeNotAcceptableException {

		Object response = webRequest.getNativeResponse();
		if (LogbackLoggerService.EMPTY.equals(returnValue)) {
			returnValue = "EMPTY";
		} else if (returnType.getMethod().getReturnType().equals(Void.TYPE)) {
			returnValue = "VOID";
		}
		mavContainer.setRequestHandled(true);
		if (!(response instanceof ResponseMessage)) {
			response = ResponseMessage.one().success().setEntity(returnValue);
		}
		writeWithMessageConverters(response, returnType, webRequest);

	}

}
