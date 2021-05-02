package ru.dins_сollaboration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.dins_сollaboration.utils.TestDataUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@EnableSwagger2
@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
//        SpringApplication.run(RestApiApplication.class, args);

        // only one (from remote Heroku-deploy)
        ConfigurableApplicationContext context = SpringApplication.run(RestApiApplication.class, args);
        TestDataUtil testPopulateDBDataUtil = context.getBean(TestDataUtil.class);
        testPopulateDBDataUtil.initializeData();// populate with init-data by hibernate
    }
}
