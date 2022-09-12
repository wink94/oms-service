package com.windula.oms.config;


import com.windula.oms.exception.ConfigurationException;
import com.windula.oms.exception.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "omsEntityManager",
        transactionManagerRef = "omsTransactionManager",
        basePackages = "com.windula.oms.dao"
)
@PropertySource("classpath:application-${SERVER_ENVIRONMENT_VARIABLE}.properties")
public class OMSDataSourceInitializer extends DataSourceInitializer{

    @Value("${databasePort}")
    private int databasePort;
    private static final String persistenceUnitName = "omsMysql";
    private static final Logger LOGGER = LoggerFactory.getLogger(OMSDataSourceInitializer.class);

    public OMSDataSourceInitializer() {
    }

    @Bean(name = "omsDataSource", destroyMethod = "close")
    @Primary
    @DependsOn({"configurationProvider"})
    public DataSource dataSource(@Qualifier("configurationProvider")ConfigurationProvider configurationProvider) {

        try {

            return getDataSource(
                    configurationProvider.getHost(),
                    configurationProvider.getDatabase(),
                    configurationProvider.getUser(),
                    configurationProvider.getPassword(),
                    databasePort,
                    false);

        } catch (NullPointerException npe) {

            LOGGER.error("error: OMSDataSourceInitializer database parameters fetch failed", npe);
            throw new ConfigurationException(ExceptionEnum.DATABASE_PROPERTIES_FETCH);
        }

    }


    @Bean(name = "omsPersistenceUnitManager")
    @Primary
    @DependsOn({"omsDataSource"})
    public DefaultPersistenceUnitManager persistenceUnitManager(@Qualifier("omsDataSource") DataSource dataSource) {
        return getDefaultPersistenceUnitManager(dataSource, persistenceUnitName);
    }

    @Bean(name = "omsEntityManager")
    @Primary
    @DependsOn({"omsPersistenceUnitManager"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("omsDataSource") DataSource dataSource,
                                                                           @Qualifier("omsPersistenceUnitManager") DefaultPersistenceUnitManager defaultPersistenceUnitManager) {
        return getEntityManagerFactoryBean(persistenceUnitName, defaultPersistenceUnitManager, dataSource);
    }

    @Bean(name = "omsTransactionManager")
    @Primary
    @DependsOn({"omsEntityManager"})
    public JpaTransactionManager jpaTransactionManager(@Qualifier("omsEntityManager") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return getJpaTransactionManager(localContainerEntityManagerFactoryBean);
    }
}
