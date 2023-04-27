package com.multimodule.api.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(value="com.multimodule.api.mapper.*", sqlSessionFactoryRef="dbMybatisSqlSessionFactory")
@Slf4j
public class MybatisConfig {
    @Autowired
    private Environment env;

    private static final String prefix = "spring.db.datasource.hikari.";

    @Bean(name = "dbMybatisSource", destroyMethod = "close")
    @Primary
    public HikariDataSource dbMybatisSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(env.getProperty(prefix+"username"));
        config.setPassword(env.getProperty(prefix+"password"));
//    	config.setDriverClassName(env.getProperty(prefix+"driverClassName"));
        config.setJdbcUrl( env.getProperty(prefix+"jdbc-url") );
        config.setMaxLifetime( Long.parseLong(env.getProperty(prefix+"max-lifetime")) );
        config.setConnectionTimeout(Long.parseLong( env.getProperty(prefix+"connection-timeout")));
        config.setValidationTimeout(Long.parseLong( env.getProperty(prefix+"validation-timeout")));

        config.addDataSourceProperty( "cachePrepStmts" ,  env.getProperty(prefix+"data-source-properties.cachePrepStmts"));
        config.addDataSourceProperty( "prepStmtCacheSize" , env.getProperty(prefix+"data-source-properties.prepStmtCacheSize"));
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , env.getProperty(prefix+"data-source-properties.prepStmtCacheSqlLimit") );
        config.addDataSourceProperty( "useServerPrepStmts" , env.getProperty(prefix+"data-source-properties.useServerPrepStmts") );

        HikariDataSource dataSource = new HikariDataSource( config );

        return dataSource;
    }

    @Bean(name = "dbMybatisSqlSessionFactory")
    @Primary
    public SqlSessionFactory dbMybatisSqlSessionFactory(@Qualifier("dbMybatisSource") DataSource dbMybatisSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dbMybatisSource);
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sql/read/*.xml"));
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sql/**/*.xml"));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "dbMybatisSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate dbMybatisSqlSessionTemplate(SqlSessionFactory dbMybatisSqlSessionTemplate) throws Exception {
        return new SqlSessionTemplate(dbMybatisSqlSessionTemplate);
    }

    @Bean(name="dbMybatisTransactionManager")
    @Primary
    public PlatformTransactionManager dbMybatisTransactionManager(@Qualifier("dbMybatisSource") DataSource dbMybatisSource) {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dbMybatisSource);
        return transactionManager;
    }
}