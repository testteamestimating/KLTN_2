package com.estimating.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.estimating.service.core.IS3Service;

public class S3ServiceImpl implements IS3Service {

	@Override
	public AmazonS3 getAmazonS3() {
		AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJSNV4KWPOS6W3WMA", "Mq7h0B6hcOeqF4/VC5lxqS+9hounp1KG7PXGaiOf");
		AmazonS3 awsS3 = new AmazonS3Client(awsCredentials);
		return awsS3;
	}

	@Override
	public File convertMultipartFileToFile(MultipartFile multipart)
			throws IllegalStateException, IOException {
		if(!multipart.isEmpty()) {
			File file = new File(multipart.getOriginalFilename());
			multipart.transferTo(file);
			return file;
		}
		return null;
	}

	@Override
	public void createBucket(String bucketName) {
		AmazonS3 client = getAmazonS3();
		client.createBucket(bucketName);
	}

	@Override
	public void uploadToS3(String bucket, MultipartFile file)throws IllegalStateException, IOException {
		if (!file.isEmpty()) {
			System.out.println("Not empty"+ file.getName());
			AmazonS3 client = getAmazonS3();
			File fileToS3 = convertMultipartFileToFile(file);
			if(fileToS3 != null) {
			    // estimating-test must be change to projectId
			    String fileName = fileToS3.getName();
				client.putObject(bucket, fileName, fileToS3);
			}
			
		}
	}

}
