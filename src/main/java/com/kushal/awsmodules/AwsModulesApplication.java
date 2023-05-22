package com.kushal.awsmodules;


import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.kushal.awsmodules.s3.service.AWSS3Service;
import com.kushal.awsmodules.s3.service.FileService;
import com.kushal.awsmodules.s3.utils.PropertyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class AwsModulesApplication implements CommandLineRunner {
    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Autowired
    AWSS3Service awsService;

    @Autowired
    private PropertyReader propertyReader;

    public AwsModulesApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(AwsModulesApplication.class, args);
    }

    public static void listFiles(File directory) {
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    } else if (file.isDirectory()) {
                        listFiles(file);
                    }
                }
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {

        //creating a bucket
        System.out.println("------ Creating a bucket with name : " + bucketName + "---------");
        if (awsService.doesBucketExist(bucketName)) {
            System.out.println("Bucket already exist. Skipping bucket creation");
        } else {
            awsService.createBucket(bucketName);
            System.out.println("Bucket Created");
        }
        System.out.println("----");


        System.out.println("--------Uploading Objects-----------");
        String uploadFilePath = propertyReader.USER_DIR + propertyReader.getUploads();

        try {
            List<String> filePathList = FileService.listFiles(uploadFilePath);
            filePathList.stream()
                        .forEach(filePath -> {
                            awsService.putObject(bucketName, filePath.replaceFirst(uploadFilePath + "/", ""), new File(filePath));
                        });
            System.out.println("Objects uploaded successfully!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("--------End of Uploading Objects-----------");

        System.out.println("--------Listing Objects-------");
        ObjectListing objectListing = awsService.listObjects(bucketName);
        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
            System.out.println(os.getKey());
        }
        System.out.println("--------End of Listing Objects-------");

        //list all the buckets
        System.out.println("-------------Listing all buckets-------------");
        for (Bucket s : awsService.listBuckets()) {
            System.out.println(s.getName());
        }
        System.out.println("-------------End of Listing all buckets-------------");


        /*  Delete Operation

//        System.out.println("----------Deleting object/objects-----------");
//        //deleting an object
//        awsService.deleteObject(bucketName, "testfile1.txt");
//
//        //deleting multiple objects
//        String objkeyArr[] = {
//                "testfile1.txt",  //already deleted from previous operation
//                "testfile2.txt"
//        };
//
//        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucketName)
//                .withKeys(objkeyArr);
//        awsService.deleteObjects(delObjReq);
//        System.out.println("----------End of Deleting object/objects-----------");
//
//
//        //deleting bucket
//        awsService.deleteBucket(bucketName);

          Delete Operation */


    }


}
