package com.kushal.s3.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    public static List<String> listFiles(String filePath) throws IOException {
        List<String> fileList = new ArrayList<>();
        Path directoryPath = Paths.get(filePath);
        if (Files.isDirectory(directoryPath)) {
            Files.list(directoryPath)
                 .forEach(file -> fileList.add(String.valueOf(file)));
        } else {
            System.out.println("Specified path is not a directory.");
        }
        return fileList;
    }
}
