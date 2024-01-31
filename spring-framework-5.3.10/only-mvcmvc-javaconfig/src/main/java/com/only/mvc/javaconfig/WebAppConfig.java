package com.only.mvc.javaconfig;


import com.only.mvc.interceptor.OnlyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * IOC子容器，web子容器
 */
@Configuration
@ComponentScan(basePackages = "com.only.mvc.controller", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {RestController.class, Controller.class})}, useDefaultFilters = false)
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {

	/**
	 * 配置拦截器
	 */
	@Bean
	public OnlyInterceptor onlyInterceptor() {
		return new OnlyInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(onlyInterceptor()).addPathPatterns("/*");
	}

	/**
	 * 配置文件上传下载组件
	 *
	 * @return
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(1024 * 1024 * 10);
		return multipartResolver;
	}

	/**
	 * 注册处理国际化资源的组件
	 *
	 * @return
	 */
/*	@Bean
	public AcceptHeaderLocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		return acceptHeaderLocaleResolver;
	}*/

	/**
	 * 配置试图解析器
	 *
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");
		viewResolver.setPrefix("/WEB-INF/jsp/");
		return viewResolver;
	}

	/**
	 * 配置消息转换器
	 *不需要配置
	 * @param converters
	 */

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
	}

}
