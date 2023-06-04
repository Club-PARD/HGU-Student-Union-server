package com.server.HGUStudentUnion_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HguStudentUnionServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HguStudentUnionServerApplication.class, args);
	}

}
