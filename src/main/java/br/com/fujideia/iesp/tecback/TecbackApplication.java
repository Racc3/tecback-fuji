package br.com.fujideia.iesp.tecback;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class TecbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecbackApplication.class, args);
	}

}
