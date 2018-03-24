package com.captain.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。「多数据源配置的时候注意，必须要有一个主数据源，用 @Primary 标志该 Bean」
 * @MapperScan 扫描 Mapper 接口并容器管理，包路径精确到 master，为了和下面 transport 数据源做到精确区分
 * @Value 获取全局配置文件 application.properties 的 kv 配置,并自动装配
 * sqlSessionFactoryRef 表示定义了 key ，表示一个唯一 SqlSessionFactory 实例
 */

@Configuration
@MapperScan(basePackages = DataSource2Config.PACKAGE,sqlSessionFactoryRef ="db2SqlSessionFactory")
public class DataSource2Config {


    public static final String PACKAGE = "com.captain.mapper.cht";

    public static final String MAPPER_LOCATION = "classpath:mybatis/mapper/*.xml";

    @Value("${jdbc.database2.driverClassName}")
    public String driverClassName;

    @Value("${jdbc.database2.url}")
    public String url;

    @Value("${jdbc.database2.username}")
    public String username;

    @Value("${jdbc.database2.password}")
    public String password;

    //初始化数据库连接
    @Bean("db2DataSource")
    public DataSource db2DataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    //数据源事务管理器
    @Bean(name="db2DataSourceTransactionManager")
    public DataSourceTransactionManager db2DataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(db2DataSource());
        return dataSourceTransactionManager;
    }

    //创建Session
    @Bean(name="db2SqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource db2DataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db2DataSource);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(DataSource2Config.MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(resource);
        return sqlSessionFactoryBean.getObject();
    }
}
