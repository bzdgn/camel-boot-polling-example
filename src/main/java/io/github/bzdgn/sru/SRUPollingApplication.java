package io.github.bzdgn.sru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SRUPollingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SRUPollingApplication.class, args);
    }
}
