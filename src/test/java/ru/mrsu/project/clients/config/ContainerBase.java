package ru.mrsu.project.clients.config;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = "spring.jpa.properties.eclipselink.application-location=.")
public abstract class ContainerBase extends PostgreSqlContainerBase {

    static {
        JdbcDatabaseDelegate containerDelegate = new JdbcDatabaseDelegate(postgreDBContainer, "");
//        ScriptUtils.runInitScript(containerDelegate, "create_schema_and_user.sql");
    }
}