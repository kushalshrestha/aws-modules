# aws-modules

## Description
This project is a sample application built using Spring Boot 3 and AWS modules. It demonstrates the integration of Spring Boot with various AWS services for developing scalable and reliable applications.

## Features
* AWS S3 Integration

## Requirements
* Java 8 or higher
* Maven 3.6.3 or higher
* AWS SDK for Java

## Installation
1. Clone the repository: git clone https://github.com/your/repository.git
2. Navigate to the project directory: cd project-directory
3. Build the project using Maven: mvn clean install
4. Configure AWS credentials:
   * Create an AWS account if you don't have one already.
   * Set up AWS access key and secret key using AWS IAM (Identity and Access Management).
   * Run aws configure command and enter the required details.
   * Modify the AWS service configurations in the application.properties file.
   * Run the application: mvn spring-boot:run
5. Modify the AWS service configurations in the `application.properties` file.
6. Run the application `mvn spring-boot:run`

## Configuration
Modify the `application.properties` file:
```
# AWS S3 Configuration
aws.s3.accessKey=<AWS S3 Access Key>
aws.s3.secretKey=<AWS S3 Secret Key>
aws.s3.bucketName=<AWS S3 Bucket Name>
```