package net.pavelnikitin.pashach.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {

    @Value("${app.posts.imagepath}")
    private String postImages;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/styles/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/scripts/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/static/site/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/media/images/**").addResourceLocations("file:" + postImages);
    }
}
