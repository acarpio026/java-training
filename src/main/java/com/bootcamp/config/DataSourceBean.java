package com.bootcamp.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DataSourceBean {

    private AppConfiguration appConfig;

    // @Autowired
    public DataSourceBean(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    // @Bean(name = "spring-datasource")
    // @Primary
    // @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties getSpringDatasourceProperties() {
        return new DataSourceProperties();
    }
    
    // @Bean(name = "hikari-config")
    // @Primary
    // @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig getHikariConfig() {
        return new HikariConfig();
    }

    //@Bean
    public DataSource getDataSource() {

        DataSourceProperties propertyConfig = getSpringDatasourceProperties();
        HikariConfig hikariConfig = getHikariConfig();       

        hikariConfig.setJdbcUrl(propertyConfig.getUrl());
        hikariConfig.setUsername(appConfig.getUsername());
        hikariConfig.setPassword(appConfig.getPassword());
                
        // hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        // hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        // hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(hikariConfig);
    }

}
