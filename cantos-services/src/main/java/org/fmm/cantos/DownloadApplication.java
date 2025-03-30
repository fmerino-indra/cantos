package org.fmm.cantos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.fmm.cantos"})
@EntityScan(basePackages = {"org.fmm.cantos.model.entity"})
@EnableJpaRepositories(basePackages = {"org.fmm.cantos.model.repository"})
public class DownloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(DownloadApplication.class, args);
	}
}
