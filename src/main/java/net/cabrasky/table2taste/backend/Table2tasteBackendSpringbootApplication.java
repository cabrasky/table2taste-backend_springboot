package net.cabrasky.table2taste.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Table2tasteBackendSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Table2tasteBackendSpringbootApplication.class, args);
	}

}
