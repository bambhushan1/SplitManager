package com.split.manager.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.split.manager.config.ApplicationRestAction;
import com.split.manager.vo.RestResponse;

public interface IApplicationServiceHandler {

	<T> ResponseEntity<RestResponse> process(ApplicationRestAction action,
			T param, Integer id, HttpServletRequest httpRequest, HttpServletResponse httpServletResponse);
}
