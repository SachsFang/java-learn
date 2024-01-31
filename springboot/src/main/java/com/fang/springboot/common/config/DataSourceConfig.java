package com.fang.springboot.common.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author shaobin
 * @date 2024/1/22 22:00
 */
@Configuration
public class DataSourceConfig {
    // todo 分库分表+读写分离liuqiubase启动时还只能读写一个库写库（读库可以通过数据库主从复制从主库同步数据），后续得看源码优化成可读写多个写库
//    @Bean
//    @ConfigurationProperties(prefix = "datasource.secondary.liquibase")
//    public LiquibaseProperties secondaryLiquibaseProperties() {
//        return new LiquibaseProperties();
//    }
//
//    @Bean
//    public SpringLiquibase secondaryLiquibase(ShardingSphereDataSource shardingSphereDataSource) {
//        return springLiquibase(secondaryDataSource(), secondaryLiquibaseProperties());
//    }
//
//    private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setDataSource(dataSource);
//        liquibase.setChangeLog(properties.getChangeLog());
//        liquibase.setContexts(properties.getContexts());
//        liquibase.setDefaultSchema(properties.getDefaultSchema());
//        liquibase.setDropFirst(properties.isDropFirst());
//        liquibase.setShouldRun(properties.isEnabled());
//        liquibase.setLabels(properties.getLabels());
//        liquibase.setChangeLogParameters(properties.getParameters());
//        liquibase.setRollbackFile(properties.getRollbackFile());
//        return liquibase;
//    }



}
