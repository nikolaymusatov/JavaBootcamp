package edu.school21.Service.config;

import edu.school21.Service.repositories.UsersRepository;
import edu.school21.Service.repositories.UsersRepositoryJdbcImpl;
import edu.school21.Service.services.UsersService;
import edu.school21.Service.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
@ComponentScan("edu.school21.Service.services")
public class TestApplicationConfig {
    @Bean
    public EmbeddedDatabase EmbeddedDatabaseDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }
    
    @Bean
    public UsersRepository usersRepositoryJdbc() {
        return new UsersRepositoryJdbcImpl(EmbeddedDatabaseDataSource());
    }
    
    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepositoryJdbc());
    }
}