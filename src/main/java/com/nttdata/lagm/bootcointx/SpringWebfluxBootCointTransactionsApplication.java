package com.nttdata.lagm.bootcointx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringWebfluxBootCointTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxBootCointTransactionsApplication.class, args);
	}

}
