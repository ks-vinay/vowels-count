package com.luxsoft.assignment.vowelscount.service.impl;

import com.luxsoft.assignment.vowelscount.dto.VowelsCountDto;
import com.luxsoft.assignment.vowelscount.service.FileReaderService;
import com.luxsoft.assignment.vowelscount.service.FileWriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ProcessFileImplTest {

    FileReaderService fileReaderService;
    FileWriteService fileWriteService;
    ProcessFileImpl processFile;
    Map<Integer, VowelsCountDto> vowelsCountDtoMap;
    MockMultipartFile multipartFile;
    VowelsCountDto vowelsCountDtoSixChar;
    VowelsCountDto vowelsCountDtoFiveChar;
    VowelsCountDto vowelsCountDtoFourChar;
    VowelsCountDto vowelsCountDtoFiveCharMapping;
    VowelsCountDto vowelsCountDtoSixChar1;
    VowelsCountDto vowelsCountDtoSixChar2;
    VowelsCountDto vowelsCountDtoSixCharWithAllDetails;



    @BeforeEach
    void setUp() throws IOException {
        fileReaderService = mock(FileReaderService.class);
        fileWriteService = mock(FileWriteService.class);
        processFile = new ProcessFileImpl(fileReaderService, fileWriteService);

        vowelsCountDtoSixChar = new VowelsCountDto();
        vowelsCountDtoSixChar.setWordsLength(6); vowelsCountDtoSixChar.setVowelsAverage(2.5); vowelsCountDtoSixChar.setUniqueVowelsChar(new HashSet<>(Arrays. asList('a','o'))); vowelsCountDtoSixChar.setTotalNumOfVowels(5); vowelsCountDtoSixChar.setTotalNumOfWords(2);
        vowelsCountDtoFiveChar = new VowelsCountDto();
        vowelsCountDtoFiveChar.setWordsLength(5); vowelsCountDtoFiveChar.setVowelsAverage(2.0); vowelsCountDtoFiveChar.setUniqueVowelsChar(new HashSet<>(Arrays. asList('a','o'))); vowelsCountDtoFiveChar.setTotalNumOfVowels(2); vowelsCountDtoFiveChar.setTotalNumOfWords(1);
        vowelsCountDtoFourChar = new VowelsCountDto();
        vowelsCountDtoFourChar.setWordsLength(4); vowelsCountDtoFourChar.setVowelsAverage(2.0); vowelsCountDtoFourChar.setUniqueVowelsChar(new HashSet<>(Arrays. asList('a','e'))); vowelsCountDtoFourChar.setTotalNumOfVowels(2); vowelsCountDtoFourChar.setTotalNumOfWords(1);

        vowelsCountDtoFiveCharMapping = new VowelsCountDto();
        vowelsCountDtoFiveCharMapping.setWordsLength(0); vowelsCountDtoFiveCharMapping.setVowelsAverage(0.0); vowelsCountDtoFiveCharMapping.setUniqueVowelsChar(new HashSet<>(Arrays. asList('a','o'))); vowelsCountDtoFiveCharMapping.setTotalNumOfVowels(2); vowelsCountDtoFiveCharMapping.setTotalNumOfWords(1);

        vowelsCountDtoSixChar1 = new VowelsCountDto();
        vowelsCountDtoSixChar1.setWordsLength(6);  vowelsCountDtoSixChar1.setUniqueVowelsChar(new HashSet<>(Arrays.asList('a','o'))); vowelsCountDtoSixChar1.setTotalNumOfVowels(3); vowelsCountDtoSixChar1.setTotalNumOfWords(1);
        vowelsCountDtoSixChar2 = new VowelsCountDto();
        vowelsCountDtoSixChar2.setWordsLength(6); vowelsCountDtoSixChar2.setVowelsAverage(2.5); vowelsCountDtoSixChar2.setUniqueVowelsChar(new HashSet<>(Arrays.asList('o'))); vowelsCountDtoSixChar2.setTotalNumOfVowels(2); vowelsCountDtoSixChar2.setTotalNumOfWords(1);

        vowelsCountDtoSixCharWithAllDetails = new VowelsCountDto();
        vowelsCountDtoSixCharWithAllDetails.setWordsLength(0);  vowelsCountDtoSixCharWithAllDetails.setUniqueVowelsChar(new HashSet<>(Arrays.asList('a','o'))); vowelsCountDtoSixCharWithAllDetails.setTotalNumOfVowels(3); vowelsCountDtoSixCharWithAllDetails.setTotalNumOfWords(1);

        vowelsCountDtoMap = new HashMap<>();
        vowelsCountDtoMap.put(6, vowelsCountDtoSixCharWithAllDetails);

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("sample.txt");
        File file = new File(url.getFile());
        InputStream targetStream = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file","sample.txt","multipart/form-data",targetStream);
    }


    @Test
    void calculateVowelsCount(){

        VowelsCountDto expectedVowelsCountDto = vowelsCountDtoFiveCharMapping;

        VowelsCountDto actualVowelsCountDto = processFile.calculateVowelsCount("boats");

        assertEquals(expectedVowelsCountDto, actualVowelsCountDto);
        assertThat(expectedVowelsCountDto).usingRecursiveComparison().isEqualTo(actualVowelsCountDto);

    }

    @Test
    void updateVowelsCountDtoMap(){

        VowelsCountDto expectedVowelsCountDto = vowelsCountDtoSixChar;

        VowelsCountDto actualVowelsCountDto = processFile.updateVowelsCountDtoMap(vowelsCountDtoSixChar1,vowelsCountDtoSixChar2);

        assertEquals(expectedVowelsCountDto, actualVowelsCountDto);
        assertThat(expectedVowelsCountDto).usingRecursiveComparison().isEqualTo(actualVowelsCountDto);

    }

    @Test
    void generateVowelsCountMap(){

        Map<Integer, VowelsCountDto>  expectedResponse = vowelsCountDtoMap;

        List<String> listOfWords = new ArrayList<>();
        listOfWords.add("bamboo");

        Map<Integer, VowelsCountDto>  actualResponse = processFile.generateVowelsCountMap(listOfWords);
        assertEquals(expectedResponse.size(), actualResponse.size());
        assertEquals(expectedResponse.entrySet(), actualResponse.entrySet());

    }

}