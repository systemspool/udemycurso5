package com.jesus.cursospringboot.app.interceptor.springbootinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig  implements WebMvcConfigurer{

    @Autowired
    @Qualifier("loadingTimeInterpcetor")
    private HandlerInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar", "/app/foo");  //aqui con addPathPatterns se le indicia en cual se ejecutara el interceptor
    //registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/bar", "/app/foo");  //con exclude es lo contrario se le indica en cuales ednpoint no se ejecutara el interceptor
    }
    

}
