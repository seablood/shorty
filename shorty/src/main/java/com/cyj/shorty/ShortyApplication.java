package com.cyj.shorty;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class ShortyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortyApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public ApplicationRunner runner(DataSource dataSource){ // 커넥션 풀 초기화
		return args -> {
			Connection connection = dataSource.getConnection();
		};
	}

}
