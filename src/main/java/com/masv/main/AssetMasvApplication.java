package com.masv.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.masv..entity")
@ComponentScan("com.masv.controller")
@ComponentScan("com.masv.service")
@EnableJpaRepositories("com.pitchbull.repository")
public class AssetMasvApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetMasvApplication.class, args);
	}

}
