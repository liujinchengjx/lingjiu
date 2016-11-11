package com.liu.qinziyou.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	private String encoding;

	private boolean forceEncoding = false;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if (this.forceEncoding) {
			request.setCharacterEncoding(this.encoding);
			//response.setCharacterEncoding(this.encoding);
		}

		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");

		if (filterConfig.getInitParameter("forceEncoding") != null) {
			this.forceEncoding = new Boolean(filterConfig
					.getInitParameter("forceEncoding")).booleanValue();
		}

	}

}