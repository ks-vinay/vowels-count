package com.luxsoft.assignment.vowelscount.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

class FileReaderServiceImplTest {

    MockMultipartFile multipartFile;
    FileReaderServiceImpl fileReaderService;

    @BeforeEach
    void setUp() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("sample.txt");
        File file = new File(url.getFile());
        InputStream targetStream = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file","sample.txt","multipart/form-data",targetStream);
        fileReaderService = new FileReaderServiceImpl();

    }

    @Test
    void readTextFile() throws IOException {

        String expectedStr = "Platon made bamboo boats";
        String actualStr = fileReaderService.readTextFile(multipartFile);
        assertEquals (expectedStr,actualStr);
        assertThat(actualStr, containsString(expectedStr));
    }
}