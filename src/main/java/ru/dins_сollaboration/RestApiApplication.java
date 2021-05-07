package ru.dins_сollaboration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import ru.dins_сollaboration.utils.Bot;
import ru.dins_сollaboration.utils.TestDataUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@EnableSwagger2
@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);

        // ---------------- Telegram init -------------------
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

//        # handle DB_population..

//        ConfigurableApplicationContext context = SpringApplication.run(RestApiApplication.class, args);
//        TestDataUtil testPopulateDBDataUtil = context.getBean(TestDataUtil.class);
//        testPopulateDBDataUtil.initializeData();// populate with init-data by hibernate
    }
}
