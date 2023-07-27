package com.Youtube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Youtube", "com.amazonaws.services.s3"})
public class YoutubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutubeApplication.class, args);
	}

}
