package com.luxsoft.assignment.vowelscount.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileReaderService {

    /**
     * Input a MultipartFile and read the content of the file
     * @param inputFile which is of type MultipartFile
     * @return String which contains the whole content of the file
     */
    String readTextFile(MultipartFile inputFile) throws IOException;



}
