package co.com.demo.proxy.aop.config;

import co.com.demo.proxy.aop.interceptor.MvcLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcInterceptorConfig implements WebMvcConfigurer {

    private final MvcLogInterceptor mvcLogInterceptor;

    public MvcInterceptorConfig(MvcLogInterceptor mvcLogInterceptor) {
        this.mvcLogInterceptor = mvcLogInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mvcLogInterceptor);
    }
}
