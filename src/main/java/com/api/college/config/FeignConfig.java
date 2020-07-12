package com.api.college.config;

import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * This class is used to add Basic Auth header for Auth Service.
 * Exclude this class from auto component scan on the application. e.g.
 *
 * @ComponentScan(excludeFilters = @ComponentScan.Filter(value = FeignConfig.class, type = FilterType.ASSIGNABLE_TYPE))
 */
@Configuration
public class FeignConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("b4s", "B$S!#");
    }

    @Bean
    @Primary
    @Scope("prototype")
    public Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new FormEncoder(new SpringEncoder(messageConverters));
    }
}
