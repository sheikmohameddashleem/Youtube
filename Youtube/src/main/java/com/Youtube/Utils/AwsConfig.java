package com.Youtube.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {

	
	
	
	    @Bean
	    public AmazonS3 amazonS3() {
	    	
	    	  return AmazonS3ClientBuilder.standard()
//	                  .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
	                  .withRegion(Regions.AP_SOUTH_1) // Replace with the appropriate region
	                  .build();
	    }

}
