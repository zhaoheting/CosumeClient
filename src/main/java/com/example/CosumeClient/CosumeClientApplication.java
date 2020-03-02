package com.example.CosumeClient;

import com.example.GrandWorldMSpec.generated.client.template.RuleManagementApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CosumeClientApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public RuleManagementApi ruleManagementApi(){
		return new RuleManagementApi();
	}
	public static void main(String[] args) {
		SpringApplication.run(CosumeClientApplication.class, args);
	}

}
