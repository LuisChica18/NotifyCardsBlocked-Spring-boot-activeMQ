package com.example.producercardsmanager.config;

//import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * - This is a configuration class to create  different  beans to configure the Context Config of the application
 * - Working with spring boot we will have almost everything like set up a EntityManagerFactory,  Declared here is the
 *   JpaTransactionManager or  finally activate Spring Data JPA repositories.
 * - In this case just we will configuration a embedded H2 database using the ServletRegistrationBean to execute the demo.
 *
 */
@Configuration
public class ContextConfig {

    /**
     * - Create a DataSource using an embedded H2 database through  the ServletRegistrationBean class
     * @return ServletRegistrationBean
     */
/*
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.setName("testdb");
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
 */
}