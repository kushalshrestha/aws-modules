package com.kushal.awsmodules.s3.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.util.Iterator;
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

    //delete a bucket
    public void deleteBucket(String bucketName) {
        try {
            System.out.println("Deleting S3 bucket : " + bucketName);
            ObjectListing object_listing = s3client.listObjects(bucketName);
            while(true) {
                for (Iterator<?> iterator =
                     object_listing.getObjectSummaries().iterator();
                     iterator.hasNext(); ) {
                    S3ObjectSummary summary = (S3ObjectSummary) iterator.next();
                    deleteObject(bucketName, summary.getKey());
                }
                // more object_listing to retrieve
                if (object_listing.isTruncated()) {
                    object_listing = s3client.listNextBatchOfObjects(object_listing);
                } else {
                    break;
                }
            }
            System.out.println(" - removing versions from bucket");
            VersionListing version_listing = s3client.listVersions(
                    new ListVersionsRequest().withBucketName(bucketName));
            while (true) {
                for (Iterator<?> iterator =
                     version_listing.getVersionSummaries().iterator();
                     iterator.hasNext(); ) {
                    S3VersionSummary vs = (S3VersionSummary) iterator.next();
                    s3client.deleteVersion(
                            bucketName, vs.getKey(), vs.getVersionId());
                }

                if (version_listing.isTruncated()) {
                    version_listing = s3client.listNextBatchOfVersions(
                            version_listing);
                } else {
                    break;
                }
            }
            System.out.println("Bucket ready to delete");
            s3client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            return;
        }
    }

    //uploading object
    public PutObjectResult putObject(String bucketName, String key, File file) {
        return s3client.putObject(bucketName, key, file);
    }

    //listing objects
    public ObjectListing listObjects(String bucketName) {
        return s3client.listObjects(bucketName);
    }

    //deleting an object
    public void deleteObject(String bucketName, String objectKey) {
        s3client.deleteObject(bucketName, objectKey);
    }

    //deleting multiple Objects
    public DeleteObjectsResult deleteObjects(DeleteObjectsRequest delObjReq) {
        return s3client.deleteObjects(delObjReq);
    }

    public String getBucketLocation(String bucketName) {
        return s3client.getBucketLocation(bucketName);
    }
}
