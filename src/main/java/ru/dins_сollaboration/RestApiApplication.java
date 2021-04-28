package ru.dins_сollaboration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.dins_сollaboration.utils.TestDataUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RestApiApplication.class, args);

        TestDataUtil testPopulateDBDataUtil = context.getBean(TestDataUtil.class);
        testPopulateDBDataUtil.initializeData();// populate with init-data by hibernate
    }
}
