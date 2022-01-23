package com.luxsoft.assignment.controller;

import com.luxsoft.assignment.vowelscount.controller.VowelsCountController;
import com.luxsoft.assignment.vowelscount.service.ProcessFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class VowelsCountControllerTest {

    ProcessFile processFile;
    VowelsCountController vowelsCountController;
    MockMultipartFile multipartFile;

    @BeforeEach
    void setUp() throws IOException {
        processFile = mock(ProcessFile.class);
        vowelsCountController = new VowelsCountController(processFile);

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("sample.txt");
        File file = new File(url.getFile());
        InputStream targetStream = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file","sample.txt","multipart/form-data",targetStream);


    }

    @Test
    void readTextFile() {

        String responseStr = "Successfully Processed";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(responseStr, HttpStatus.OK);

        Mockito.when(processFile.processTextFile(multipartFile)).thenReturn(responseStr);

        ResponseEntity<String> actualResponse = vowelsCountController.readTextFile(multipartFile);

        assertEquals(actualResponse.getStatusCode(),expectedResponse.getStatusCode());
        assertEquals(actualResponse.getBody(), expectedResponse.getBody());

    }


}
