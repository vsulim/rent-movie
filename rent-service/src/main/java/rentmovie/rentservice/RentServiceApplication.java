package rentmovie.rentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients("rentmovie.rentservice")
public class RentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentServiceApplication.class, args);
    }
}
