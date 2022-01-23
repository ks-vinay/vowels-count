package com.luxsoft.assignment.vowelscount.service.impl;

import com.luxsoft.assignment.vowelscount.dto.VowelsCountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

class FileWriterServiceImplTest {

    FileWriterServiceImpl fileWriterServiceImpl;
    VowelsCountDto vowelsCountDtoSixChar;
    VowelsCountDto vowelsCountDtoFiveChar;
    VowelsCountDto vowelsCountDtoFourChar;
    List<VowelsCountDto> vowelsCountDtoList;
    Map<Integer, VowelsCountDto> inputMap;

    @TempDir
    static Path tempDir;
    static Path tempFile;

    @BeforeEach
    void setUp() throws IOException {

        vowelsCountDtoSixChar = new VowelsCountDto();
        vowelsCountDtoSixChar.setWordsLength(6); vowelsCountDtoSixChar.setTotalNumOfVowels(5); vowelsCountDtoSixChar.setVowelsAverage(2.5); vowelsCountDtoSixChar.setTotalNumOfWords(2); vowelsCountDtoSixChar.setUniqueVowelsChar(new HashSet<>(Arrays. asList('a','o')));
        vowelsCountDtoFiveChar = new VowelsCountDto();
        vowelsCountDtoFiveChar.setWordsLength(5); vowelsCountDtoFiveChar.setTotalNumOfVowels(2); vowelsCountDtoFiveChar.setVowelsAverage(2); vowelsCountDtoFiveChar.setTotalNumOfWords(1); vowelsCountDtoFiveChar.setUniqueVowelsChar(new HashSet<>(Arrays. asList('a','o')));
        vowelsCountDtoFourChar = new VowelsCountDto();
        vowelsCountDtoFourChar.setWordsLength(4); vowelsCountDtoFourChar.setTotalNumOfVowels(2); vowelsCountDtoFourChar.setVowelsAverage(2); vowelsCountDtoFourChar.setTotalNumOfWords(1); vowelsCountDtoFourChar.setUniqueVowelsChar(new HashSet<>(Arrays. asList('a','e')));
        vowelsCountDtoList = new ArrayList<>();
        vowelsCountDtoList.add(vowelsCountDtoSixChar);
        vowelsCountDtoList.add(vowelsCountDtoFiveChar);
        vowelsCountDtoList.add(vowelsCountDtoFourChar);

        inputMap = new HashMap<>();
        inputMap.put(6, vowelsCountDtoSixChar);
        inputMap.put(5,vowelsCountDtoFiveChar);
        inputMap.put(4, vowelsCountDtoFourChar);



    }

    @Test
    void writeDatToFileSuccessfullySave() throws IOException {

        String expectedStr = "Successfully Processed";
        tempFile = Files.createFile(tempDir.resolve("outfile.txt"));
        fileWriterServiceImpl = new FileWriterServiceImpl(tempFile.toString());
        String actualStr = fileWriterServiceImpl.writeDataToFile(inputMap);
        assertEquals(expectedStr,actualStr);
        assertThat(actualStr, containsString(expectedStr));
    }

    @Test
    void getDoubleValue(){
        String expectedString = "2.5";
        fileWriterServiceImpl = new FileWriterServiceImpl(tempFile.toString());
        String actualString =  fileWriterServiceImpl.getDoubleValue(5,2);
        assertEquals(expectedString,actualString);
        assertThat(actualString, containsString(expectedString));
    }

    @Test
    void getIntValue(){
        String expectedString = "3";
        fileWriterServiceImpl = new FileWriterServiceImpl(tempFile.toString());
        String actualString =  fileWriterServiceImpl.getIntValue(6,2);
        assertEquals(expectedString,actualString);
        assertThat(actualString, containsString(expectedString));
    }

}