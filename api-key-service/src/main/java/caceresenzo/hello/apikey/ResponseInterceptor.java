package caceresenzo.hello.apikey;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ResponseInterceptor implements HandlerInterceptor {
	
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws IOException {
		System.out.println("ResponseInterceptor.postHandle()");
		System.out.println(response.getContentType());
		if (response.getContentType() == null || response.getContentType().equals("")) {
			response.setContentType("application/json");
		}
	}
	
}