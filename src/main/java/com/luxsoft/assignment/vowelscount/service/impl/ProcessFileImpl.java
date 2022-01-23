package com.luxsoft.assignment.vowelscount.service.impl;

import com.luxsoft.assignment.vowelscount.dto.VowelsCountDto;
import com.luxsoft.assignment.vowelscount.service.FileReaderService;
import com.luxsoft.assignment.vowelscount.service.FileWriteService;
import com.luxsoft.assignment.vowelscount.service.ProcessFile;
import com.luxsoft.assignment.vowelscount.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProcessFileImpl implements ProcessFile {

    @Autowired
    FileReaderService fileReaderService;

    @Autowired
    FileWriteService fileWriteService;

    Utility utility;

    public ProcessFileImpl(){}

    public ProcessFileImpl(FileReaderService fileReaderService, FileWriteService fileWriteService){
        this.fileReaderService = fileReaderService;
        this.fileWriteService = fileWriteService;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProcessFileImpl.class);

    /**
     * Return the response of the file processed
     * The method file takes MultipartFile as input
     * reads the content from the file and gives a Map of wordCount and VowelsCountDto
     * write data to the file
     * @param inputFile which contains the data
     * @return response of the file processed
     */
    @Override
    public String processTextFile(MultipartFile inputFile) {
        logger.info("Started processing the file");
        String strFileContent ;
        Map<Integer, VowelsCountDto> mapOfWordsVowelsCountDto ;
        try {
            // read the content of file into a String
            strFileContent = fileReaderService.readTextFile(inputFile);
             if(!strFileContent.isEmpty()){
                 //generate Map<Integer,VowelsCountDto>
                 mapOfWordsVowelsCountDto = generateVowelsCountMap(Arrays.asList(strFileContent.split(" ")));
                 //Write the data into a file
                 return  fileWriteService.writeDataToFile(mapOfWordsVowelsCountDto);
             }
        } catch (IOException io) {
            logger.error("Failed to read data with StackMessage {} - Stacktrace - {} ",io.getMessage(),io.getStackTrace());
            return  utility.FAILED_TO_READ_THE_FILE;
        } catch(Exception e){
            logger.error("Failed to process the file with StackMessage {} - Stacktrace - {} ",e.getMessage(),e.getStackTrace());
            return  utility.FAILED_TO_PROCESS_FILE;
        }
        return utility.SUCCESSFULLY_PROCESSED;
    }

    /**
     * Input a list of String which add to a Map of Integer which is the length of words
     * and VowelsCountDto as the key It validates where it removes all the special characters in the String
     * It returns a List of VowelsCountDto
     * @param fileContent List of String
     * @return Map<Integer, VowelsCountDto> response of the file processed
     */
    public Map<Integer, VowelsCountDto> generateVowelsCountMap(List<String> fileContent) {
        Map<Integer, VowelsCountDto> vowelsCountDtoMap = new HashMap<>();
        VowelsCountDto vowelsCountDto;
        for(String splitWord: fileContent){
            String str =  splitWord.replaceAll("[^a-zA-Z]", "");    //Used to remove any other character in the String leaving alphabet
            if(vowelsCountDtoMap.containsKey(str.length())){
                vowelsCountDto = calculateVowelsCount(str);
                vowelsCountDto = updateVowelsCountDtoMap(vowelsCountDto, vowelsCountDtoMap.get(str.length()));
                vowelsCountDtoMap.put(str.length(), vowelsCountDto);
            }else {
                vowelsCountDto = calculateVowelsCount(str);
                vowelsCountDtoMap.put(str.length(), vowelsCountDto);
            }
        }
        return vowelsCountDtoMap;
    }


    /**
     * Inputs two VowelsCountDto which is used to update the Dto
     * It returns VowelsCountDto which has the updated values
     * @param newVowelsCountDto contains the VowelsCountDto which has the processed vowels
     * @param oldVowelsCountDto contains the vowelsCountDto which already present in Map
     * @return VowelsCountDto updated Dto
     */
    public VowelsCountDto updateVowelsCountDtoMap(VowelsCountDto newVowelsCountDto, VowelsCountDto oldVowelsCountDto) {
        int totalNumOfVowels;
        int totalNumOfWords;
        oldVowelsCountDto.setUniqueVowelsChar(newVowelsCountDto.getUniqueVowelsChar());
        totalNumOfVowels = newVowelsCountDto.getTotalNumOfVowels() + oldVowelsCountDto.getTotalNumOfVowels();
        totalNumOfWords = newVowelsCountDto.getTotalNumOfWords() + oldVowelsCountDto.getTotalNumOfWords();
        oldVowelsCountDto.setTotalNumOfWords(totalNumOfWords);
        oldVowelsCountDto.setTotalNumOfVowels(totalNumOfVowels);
        return oldVowelsCountDto;
    }

    /**
     * Inputs a word and calculates vowels number of vowels
     * @param splitWord String which is used to count vowels
     * @return VowelsCountDto updated Dto
     */
    public VowelsCountDto calculateVowelsCount(String splitWord) {
        VowelsCountDto vowelsCountDto = new VowelsCountDto();
        Set<Character> vowelsChar = new HashSet<>();
        int countOfVowels =0;
        for(int i =0; i<splitWord.length(); i++){
            char ch = splitWord.charAt(i);
            if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U'){
                vowelsChar.add(ch);
                countOfVowels++;
            }
        }
        vowelsCountDto.setUniqueVowelsChar(vowelsChar);
        vowelsCountDto.setTotalNumOfVowels(countOfVowels);
        vowelsCountDto.setTotalNumOfWords(1);
        return vowelsCountDto;
    }




}
