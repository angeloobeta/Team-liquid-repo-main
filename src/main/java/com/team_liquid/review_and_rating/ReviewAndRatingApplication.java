package com.team_liquid.review_and_rating;

import io.sentry.Sentry;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Slf4j
@OpenAPIDefinition(
		info =
		@io.swagger.v3.oas.annotations.info.Info(
				description = "This app provides review and rating for the Zuri Market",
				title = "Review and Rating",
				version = "1.0"),

		servers = {
				@Server(
						url = "http://localhost:8080",
						description = "DEV Server"
				),
				@Server(
						url = "https://team-liquid-repo.onrender.com",
						description = "PROD server"
				)
		}
)

@EnableAsync
public class ReviewAndRatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewAndRatingApplication.class, args);
	}

}
