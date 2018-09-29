package rentmovie.punishmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("rentmovie.punishmentservice")
public class PunishmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PunishmentServiceApplication.class, args);
	}
}
