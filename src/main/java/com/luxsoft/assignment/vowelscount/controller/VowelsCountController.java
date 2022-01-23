package com.luxsoft.assignment.vowelscount.controller;


import com.luxsoft.assignment.vowelscount.service.ProcessFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class VowelsCountController {

    @Autowired
    ProcessFile processFile;

    public VowelsCountController(){}

    public VowelsCountController(ProcessFile processFile){
        this.processFile = processFile;
    }

    private static final Logger logger = LoggerFactory.getLogger(VowelsCountController.class);

    /**
     * Return the response of the file processed
     * @param textFile which contains the data
     * @return ResponseEntity response of the file processed
     */
    @PostMapping("/readTextFile")
    public ResponseEntity<String> readTextFile(@RequestBody MultipartFile textFile ) {
        logger.info("File started processing");
        return textFile.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(processFile.processTextFile(textFile), HttpStatus.OK);
    }

}
