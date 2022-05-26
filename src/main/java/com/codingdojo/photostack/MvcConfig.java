package com.codingdojo.photostack;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
 
 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/photos/**").addResourceLocations("file:/C:\\Users\\pastu\\Desktop\\coding dojo\\SpringProjects\\book\\PhotoStack\\./src/main/resources/static/photos/");
   }
}
