package com.luxsoft.assignment.vowelscount.service.impl;

import com.luxsoft.assignment.vowelscount.dto.VowelsCountDto;
import com.luxsoft.assignment.vowelscount.service.FileWriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

@Service
public class FileWriterServiceImpl implements FileWriteService {

    @Value("${result.file.location}")
    String outputFileLocation;

    public FileWriterServiceImpl(){}

    public FileWriterServiceImpl(String outputFileLocation){
        this.outputFileLocation = outputFileLocation;
    }

    private static final Logger logger = LoggerFactory.getLogger(FileWriterServiceImpl.class);

    /**
     * Input a List of VowelsCountDto and write the response in a file in separate line
     * Puts the file in the file location as specified in the properties
     * @param responseDtoMap contains Map of size of words and VowelsCountDto
     * @return String which has the response for writing file
     */
    @Override
    public String writeDataToFile(Map<Integer, VowelsCountDto> responseDtoMap)  {
        logger.info("Writing data to text file");
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(outputFileLocation))) {
            for (Map.Entry<Integer, VowelsCountDto> entry : responseDtoMap.entrySet()) {
                StringBuilder responseString = new StringBuilder();
                int totalVowels = entry.getValue().getTotalNumOfVowels();
                int totalWords = entry.getValue().getTotalNumOfWords();
                String str = totalVowels % totalWords == 0 ? getIntValue(totalVowels, totalWords) : getDoubleValue(totalVowels, totalWords);
                printWriter.println(responseString.append("(").append(getFormattedSet(entry.getValue().getUniqueVowelsChar())).append(", ").append(entry.getKey()).append(")").append(" -> ").append(str));

            }
        }catch(IOException e){
            logger.error("Failed to write data to the provided location with StackMessage {} - Stacktrace - {} ",e.getMessage(),e.getStackTrace());
            return "Failed to write data to file";
        }
        return "Successfully Processed";
    }

    /**
     * Input totalVowels count and total words count
     * @param totalVowels count
     * @param totalWords count
     * @return String which has the response as double String
     */
    public String getDoubleValue(int totalVowels, int totalWords) {
        double avg =  Math.round(((double)totalVowels / (double)totalWords) * 10.0) / 10.0;
        return String.valueOf(avg);
    }

    /**
     * Input totalVowels count and total words count
     * @param totalVowels count
     * @param totalWords count
     * @return String which has the response as int String
     */
    public String getIntValue(int totalVowels, int totalWords) {
        int avg = totalVowels/totalWords;
        return String.valueOf(avg);
    }

    /**
     * Input Set of Character to replace [] with {}
     * @param uniqueVowels Set of Character
     * @return StringJoiner which replaces the [] with {}
     */
    public StringJoiner getFormattedSet(Set<Character> uniqueVowels){
        StringJoiner strJoin = new StringJoiner(",","{","}");
        uniqueVowels.forEach((s)->strJoin.add(s.toString()));
        return strJoin;
    }

}
