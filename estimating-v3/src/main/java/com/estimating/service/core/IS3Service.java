package com.estimating.service.core;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

public interface IS3Service {
	public AmazonS3 getAmazonS3();
	public File convertMultipartFileToFile(MultipartFile multipart) throws IllegalStateException, IOException;
	public void createBucket(String bucketName);
	public void uploadToS3(String bucket, MultipartFile file ) throws IllegalStateException, IOException;
}
