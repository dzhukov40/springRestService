package ru.doneathome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * # H2
 *   - у нас эта база конфигурится поумолчанию так как мы добавили эту зависимость
 *     и у нас есть [application.properties] там устанавливаем параметры для H2
 *   - у нас есть два файлика для создания схемы БД и первоначальных данных, это
 *     файлы в ресурсах: [data.sql] и [schema.sql]
 */
@SpringBootApplication
@SpringBootConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * в этот бин при создании прокидываются
     * параметры с префиксом [spring.datasource] из
     * [application.properties]
     */
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSourceH2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplateH2(@Qualifier("dataSourceH2") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
