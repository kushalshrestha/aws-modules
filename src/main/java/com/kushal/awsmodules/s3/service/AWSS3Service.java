package com.kushal.awsmodules.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;

public class AWSS3Service {
    private final AmazonS3 s3client;

    public AWSS3Service() {
        this(new AmazonS3Client());
    }

    public AWSS3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public boolean doesBucketExist(String bucketName) {
        return s3client.doesBucketExist(bucketName);
    }

    //create a bucket
    public Bucket createBucket(String bucketName) {
        return s3client.createBucket(bucketName);
    }

    //list all buckets
    public List<Bucket> listBuckets() {
        return s3client.listBuckets();
    }
}
