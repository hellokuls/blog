package com.kuls.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author kuls
 * @Desc 公众号：JAVAandPython君
 * @date 2020/4/1 3:24 下午
 */
@Configuration
public class DruidConfig {

    @Bean(name = "default_datadatasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {

        return new DruidDataSource();
    }


    //后台监控:web.xml
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        HashMap<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");
//        initParameters.put("allow","");

        bean.setInitParameters(initParameters);

        return bean;


    }
}
