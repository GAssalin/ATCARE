package br.com.atcare.ms_at;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "br.com.atcare")
@EnableDiscoveryClient
@EnableFeignClients
public class MsAtApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAtApplication.class, args);
	}

}
