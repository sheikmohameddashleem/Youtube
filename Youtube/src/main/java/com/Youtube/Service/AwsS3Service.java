package com.Youtube.Service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

//import com.amazonaws.util.StringUtils;

@Service
public class AwsS3Service implements AWS {

	
	@Autowired
	private  AmazonS3 awsS3Client;
	
	

	
//	public AwsS3Service(AmazonS3Client awsS3Client) {
//		super();
//		this.awsS3Client = awsS3Client;
//	}



	public String getUrl(MultipartFile file) {
		
		String filenameExtension=StringUtils.getFilenameExtension(file.getOriginalFilename());
		
		
		String key=UUID.randomUUID().toString()+"."+filenameExtension;
		
		ObjectMetadata metadata=new ObjectMetadata();
		
		metadata.setContentLength(file.getSize());
		metadata.setContentType(file.getContentType());
		
		try {
			awsS3Client.putObject("springboot-youtube", key, file.getInputStream(), metadata);
		} catch (IOException e) {
			
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Exception Occured while Uploading the file");
		}
		
		awsS3Client.setObjectAcl("springboot-youtube", key, CannedAccessControlList.PublicRead);
		
		String resourceUrl = "https://springboot-youtube.s3.amazonaws.com/" + key;
		return  resourceUrl;
	}

}
