package com.example.retrofittest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {
		"controller",
		"service",
		"config"
})
public class RetrofitTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetrofitTestApplication.class, args);
	}

}
