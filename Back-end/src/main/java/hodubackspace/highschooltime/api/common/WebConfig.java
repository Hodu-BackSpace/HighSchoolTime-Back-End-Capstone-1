package hodubackspace.highschooltime.api.common;

import hodubackspace.highschooltime.api.common.filter.LogFilter;
import hodubackspace.highschooltime.api.common.resolver.TokenArgumentResolverHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {


    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public TokenArgumentResolverHandler tokenArgumentResolverHandler() {
        return new TokenArgumentResolverHandler();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(tokenArgumentResolverHandler());
    }
}
