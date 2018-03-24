package com.captain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.captain.mapper")
@SpringBootApplication
public class CaptainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaptainServiceApplication.class, args);
	}
}
