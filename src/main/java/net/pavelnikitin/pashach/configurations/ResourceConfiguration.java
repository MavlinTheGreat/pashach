package net.pavelnikitin.pashach.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/styles/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/scripts/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/static/site/**").addResourceLocations("classpath:/static/images/");
    }
}
