package io.core.app.springbase2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBase2022Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBase2022Application.class, args);
    }

}
