package com.kushal.awsmodules;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.kushal.awsmodules.s3.service.AWSS3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsModulesApplication implements CommandLineRunner {
    @Value("${aws.s3.bucketName}")
    private String bucketName;
    @Value("${aws.accesskey}")
    private String accessKey;
    @Value("${aws.secretkey}")
    private String secretKey;

    public static void main(String[] args) {
        SpringApplication.run(AwsModulesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        //AmazonS3 client setup
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                                                 .withCredentials(new AWSStaticCredentialsProvider(credentials))
                                                 .withRegion(Regions.US_EAST_2)
                                                 .build();
        AWSS3Service awsService = new AWSS3Service(s3client);

        //creating a bucket
        if (awsService.doesBucketExist(bucketName)) {
            System.out.println("Bucket already exist. Skipping bucket creation");
        } else {
            awsService.createBucket(bucketName);
        }

        //list all the buckets
        System.out.println("-------------Listing all bucket-------------");
        for (Bucket s : awsService.listBuckets()) {
            System.out.println(s.getName());
        }


    }
}
