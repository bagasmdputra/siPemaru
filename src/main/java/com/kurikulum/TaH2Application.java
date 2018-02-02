package com.kurikulum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EntityScan(
//		basePackageClasses = { TaH2Application.class, Jsr310JpaConverters.class }
//)


@SpringBootApplication
public class TaH2Application {

	public static void main(String[] args) {
		SpringApplication.run(TaH2Application.class, args);
	}
}
