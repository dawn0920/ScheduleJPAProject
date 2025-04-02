package org.example.schedulejpaproject.config;

import jakarta.servlet.Filter;
import org.example.schedulejpaproject.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 파일이라는 의미
public class WebConfig {

    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter()); // Filter 등록
        filterRegistrationBean.setOrder(2); // Filter 가장 먼저 실행
        filterRegistrationBean.addUrlPatterns("/*"); // 전체 URL에 Filter 적용

        return filterRegistrationBean;
    }

}
