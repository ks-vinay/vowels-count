package com.luxsoft.assignment.vowelscount.service.impl;

import com.luxsoft.assignment.vowelscount.service.FileReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileReaderServiceImpl implements FileReaderService {

    private static final Logger logger = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    /**
     * Input a MultipartFile and read the content of the file
     * @param inputFile which is of type MultipartFile
     * @return String which contains the whole content of the file
     */
    @Override
    public String readTextFile(MultipartFile inputFile) throws IOException {
        logger.info("Reading data from text file");
        return new String(inputFile.getBytes());
    }
}
