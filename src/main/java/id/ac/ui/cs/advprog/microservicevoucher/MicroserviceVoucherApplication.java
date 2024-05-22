package id.ac.ui.cs.advprog.microservicevoucher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MicroserviceVoucherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceVoucherApplication.class, args);
	}

}
