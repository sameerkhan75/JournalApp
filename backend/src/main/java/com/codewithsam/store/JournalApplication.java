package com.codewithsam.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//main pr hi lagate h
@SpringBootApplication
@EntityScan("com.codewithsam.store.entity")
@EnableJpaRepositories("com.codewithsam.store.repository")
public class JournalApplication {

    public static void main(String[] args) {

        SpringApplication.run(JournalApplication.class, args);
    }

}
