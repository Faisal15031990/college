package com.api.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.api.college.config.FeignConfig;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackages = {"com.api.college.repository"})
@EntityScan(basePackages = {"com.api.college.entity"})
@EnableFeignClients
@EnableCaching
@ComponentScan(excludeFilters = @ComponentScan.Filter(value = FeignConfig.class, type = FilterType.ASSIGNABLE_TYPE))
public class CollegemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegemsApplication.class, args);
	}

}
