package ru.dins_сollaboration.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import javax.sql.DataSource;

/**
 * EmbeddedDatabaseBuilder().build(); скриптами формирует свою Embedded DB (in memory)
 *          НЕ ИСПОЛЬЗУЕТСЯ. В API уже поднимается H2 (@AutoConfigureMockMvc в тестах...)
 */

//@Configuration
//public class TestApplicationConfig {
//
//    @Bean
//    public DataSource testDataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
////                .addScript("schema.sql")// create with
////                .addScript("data.sql")
//                .build();
//    }
//}
