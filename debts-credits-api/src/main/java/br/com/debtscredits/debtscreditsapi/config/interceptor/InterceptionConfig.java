package br.com.debtscredits.debtscreditsapi.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptionConfig implements WebMvcConfigurer {
    @Bean
    public AuthInterception authInterception() {
        return new AuthInterception();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(authInterception());
    }

   
}
