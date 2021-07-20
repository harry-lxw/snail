package com.winter.config;

import com.winter.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  //用于定义配置类，相当于applicationContext-mvc.xml文件；
// 定义一个拦截器，相当于之前的 mvc 里的配置
public class InterceptorConfig implements WebMvcConfigurer {

    //所要拦截的请求路径
    //拦截管理页中
    String[] addPathPatterns = {
            "/snail/admin/**",
            "/snail/article/toAdminHome",
            "/snail/article/article/toAddArticle","/snail/article/addArticle",
            "/snail/article/article/toUpdateArticle","/snail/article/updateArticle",
            "/snail/article/article/deleteArticle",
            "/snail/label/toLabelListAdmin",
            "/snail/label/toAddLabel","/snail/label/addLabel",
            "/snail/label/toUpdateLabel","/snail/label/updateLabel",
            "/snail/label/deleteLabel",
            "/snail/message/deleteMessage"
    };

    //不需要拦截的请求路径
    String[] excludePathPatterns = {
//            "/snail/login","/snail/checkLogin"
    };

    //mvc:interceptor class=""
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}

