package com.kushal.s3.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PropertyReader {
    public final String USER_DIR = System.getProperty("user.dir");
    @Value("${uploads}")
    private String uploads;
}
