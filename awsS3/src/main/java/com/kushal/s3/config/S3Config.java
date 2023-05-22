package com.kushal.s3.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${aws.s3.bucketName}")
    private String bucketName;
    @Value("${aws.accesskey}")
    private String accessKey;
    @Value("${aws.secretkey}")
    private String secretKey;

    @Bean
    public AmazonS3 amazonS3() {
        /* AmazonS3 client setup */
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                                                 .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
                                                 .withRegion(Regions.US_EAST_1)
                                                 .build();
        return s3client;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }


}
