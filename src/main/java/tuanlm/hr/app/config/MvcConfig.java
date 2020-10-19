package tuanlm.hr.app.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The Class MvcConfig.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    
    /**
     * Device resolver handler interceptor.
     *
     * @return the device resolver handler interceptor
     */
    @Bean
    public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() { 
        return new DeviceResolverHandlerInterceptor(); 
    }
 
    /**
     * Device handler method argument resolver.
     *
     * @return the device handler method argument resolver
     */
    @Bean
    public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() { 
        return new DeviceHandlerMethodArgumentResolver(); 
    }
 
    /**
     * Adds the interceptors.
     *
     * @param registry the registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) { 
        registry.addInterceptor(deviceResolverHandlerInterceptor()); 
    }
 
    /**
     * Adds the argument resolvers.
     *
     * @param argumentResolvers the argument resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(deviceHandlerMethodArgumentResolver()); 
    }
}
