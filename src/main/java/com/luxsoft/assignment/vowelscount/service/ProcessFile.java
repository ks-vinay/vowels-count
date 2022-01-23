package com.luxsoft.assignment.vowelscount.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProcessFile {

    /**
     * Return the response of the file processed
     * The method file takes MultipartFile as input
     * reads the content from the file and gives a Map of wordCount and VowelsCountDto
     * write data to the file
     * @param inputFile which contains the data
     * @return response of the file processed
     */
    String processTextFile(MultipartFile inputFile);

}
