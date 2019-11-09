package net.usermanager.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "net.usermanager")
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")
public class DBService {
    private Environment environment; //чтоб получать свойство из проперти файла

    @Autowired
    public DBService(Environment environment) {
        this.environment = environment;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("db.hibernate.dialect", environment.getRequiredProperty("db.hibernate.dialect"));
        properties.put("db.hibernate.show_sql", environment.getRequiredProperty("db.hibernate.show_sql"));
        properties.put("db.hibernate.hbm2ddl.auto", environment.getRequiredProperty("db.hibernate.hbm2ddl.auto"));

        return properties;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSource.setUrl(environment.getRequiredProperty("db.url"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("net.usermanager.model");
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
}
