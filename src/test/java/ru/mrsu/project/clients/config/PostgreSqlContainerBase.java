package ru.mrsu.project.clients.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@TestPropertySource(
        properties = {"spring.flyway.enabled=true", "spring.jpa.properties.eclipselink.ddl-generation=none"}
)
@ContextConfiguration(
        classes = {PostgreSqlContainerBase.DatabaseConfig.class}
)
@Testcontainers
public abstract class PostgreSqlContainerBase {
    protected static final PostgreSQLContainer<?> postgreDBContainer = new PostgreSQLContainer("postgres:12.6");

    protected PostgreSqlContainerBase() {
    }

    static {
        postgreDBContainer.start();
    }

    @TestConfiguration
    static class DatabaseConfig {
        DatabaseConfig() {
        }

        @Bean
        @FlywayDataSource
        public DataSource acqDataSource() {
            HikariDataSource hikariDataSource = new HikariDataSource();
            hikariDataSource.setJdbcUrl(PostgreSqlContainerBase.postgreDBContainer.getJdbcUrl());
            hikariDataSource.setUsername(PostgreSqlContainerBase.postgreDBContainer.getUsername());
            hikariDataSource.setPassword(PostgreSqlContainerBase.postgreDBContainer.getPassword());
            hikariDataSource.setDriverClassName("org.postgresql.Driver");
            return hikariDataSource;
        }
    }
}
