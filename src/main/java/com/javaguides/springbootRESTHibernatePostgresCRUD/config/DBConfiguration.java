package com.javaguides.springbootRESTHibernatePostgresCRUD.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {
    private String url;
    private String username;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Profile("dev")
    @Bean
    public String devDatabaseConnection(){
        System.out.println("DB Connection For Development : H2");
        System.out.println(url);
        System.out.println(username);
        return "DB Connection For Development : H2 ";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection(){
        System.out.println("DB Connection For Production : PostGreSQL");
        System.out.println(url);
        System.out.println(username);
        return "DB Connection For Production : PostGreSQL";
    }


}
