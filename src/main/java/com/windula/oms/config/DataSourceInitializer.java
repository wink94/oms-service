package com.windula.oms.config;

import com.windula.oms.exception.ConfigurationException;
import com.windula.oms.exception.ExceptionEnum;
import com.zaxxer.hikari.HikariDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

import static com.windula.oms.common.Constants.SystemStatus.ACTIVE;
import static java.util.concurrent.TimeUnit.MINUTES;
@PropertySource("classpath:application-${SERVER_ENVIRONMENT_VARIABLE}.properties")
public abstract class DataSourceInitializer {

    private static final String[] entityManagerPackages = {"com.windula.oms.dao", "com.windula.oms.model"};
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceInitializer.class);

    private static final int MAXIMUM_POOL_SIZE = 20;
    private static final int MINIMUM_POOL_SIZE = 10;
    private static final long MAX_LIFE_TIME = MINUTES.toMillis(30);
    private static final long MINIMUM_IDLE_TIME = MINUTES.toMillis(5);

    @Value("${mysql.db.dialect}")
    private String dbDialect;
    @Value("${hibernate.format_sql}")
    private String propertyNameHibernateFmtSql;
    @Value("${hibernate.show_sql}")
    private String propertyNameHibernateShowSql;
    @Value("${hibernate.jdbc.batch_size}")
    private String propertyNameHibernateJdbcBatchSize;
    @Value("${DEBUG:false}")
    private boolean debug;


    @Bean
    public AnnotationMBeanExporter annotationMBeanExporter() {
        AnnotationMBeanExporter annotationMBeanExporter = new AnnotationMBeanExporter();
        annotationMBeanExporter.addExcludedBean("dataSource");
        annotationMBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
        return annotationMBeanExporter;
    }

    DataSource getDataSource(String host, String database, String user, String password, int databasePort, boolean readOnly) {

        try {
            final String url = "jdbc:mysql://" + host + ":" + databasePort + "/" + database+"?useSSL=false";
            final HikariDataSource hikariDataSource = DataSourceBuilder
                    .create()
                    .type(HikariDataSource.class)
                    .username(user)
                    .password(password)
                    .url(url)
                    .build();

            hikariDataSource.setMaxLifetime(MAX_LIFE_TIME);
            hikariDataSource.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
            hikariDataSource.setMinimumIdle(MINIMUM_POOL_SIZE);
            hikariDataSource.setIdleTimeout(MINIMUM_IDLE_TIME);
            hikariDataSource.setReadOnly(readOnly);
            SystemStatusProvider.setSystemStatus(ACTIVE);
            LOGGER.info("Successfully created datasource with url {}", url);
            if (debug) {
                return ProxyDataSourceBuilder.create(hikariDataSource).name("Debug-Logger").asJson().countQuery().logQueryToSysOut().build();
            }

            if (!isDBConnectionValid(hikariDataSource)) {
                LOGGER.error("error: Connection to the database failed");
                throw new ConfigurationException(ExceptionEnum.DATABASE_CONNECTION_FAILURE);
            }

            return hikariDataSource;

        } catch (Exception e) {
            LOGGER.error(" error: Connection to the Database failed",  e);
            throw new ConfigurationException(ExceptionEnum.DATABASE_CONNECTION_FAILURE);
        }
    }

    JpaTransactionManager getJpaTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }

    LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(String dbName, DefaultPersistenceUnitManager defaultPersistenceUnitManager, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setPersistenceUnitName(dbName);
        entityManagerFactoryBean.setPersistenceUnitManager(defaultPersistenceUnitManager);
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(entityManagerPackages);
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
        return entityManagerFactoryBean;
    }

    DefaultPersistenceUnitManager getDefaultPersistenceUnitManager(DataSource dataSource, String persistenceUnitName) {
        DefaultPersistenceUnitManager defaultPersistenceUnitManager = new DefaultPersistenceUnitManager();
        defaultPersistenceUnitManager.setPackagesToScan("com.windula.oms");
        defaultPersistenceUnitManager.setDefaultPersistenceUnitName(persistenceUnitName);
        defaultPersistenceUnitManager.setDefaultDataSource(dataSource);
        return defaultPersistenceUnitManager;
    }

    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(dbDialect);
        vendorAdapter.setShowSql(false);
        return vendorAdapter;
    }

    private Properties jpaHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.format_sql", propertyNameHibernateFmtSql);
        properties.put("hibernate.jdbc.batch_size", propertyNameHibernateJdbcBatchSize);
        properties.put("hibernate.show_sql", propertyNameHibernateShowSql);
        return properties;
    }

    private boolean isDBConnectionValid(HikariDataSource hikariDataSource) {
        try {
            Connection connection = hikariDataSource.getConnection();
            boolean isValid = connection.isValid(0);
            connection.close();
            return isValid;
        } catch (Exception e) {
            return false;
        }
    }
}
