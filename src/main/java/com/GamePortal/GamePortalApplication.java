package com.GamePortal;

import com.GamePortal.Component.ImportExcelToDBArchiveProcess;
import com.GamePortal.Repository.IUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = "com.GamePortal")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = IUserRepository.class)
@EnableScheduling
public class GamePortalApplication {


    public static void main(String[] args) throws SQLException {
        SpringApplication.run(GamePortalApplication.class, args);

    }
}