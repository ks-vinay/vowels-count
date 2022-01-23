package com.luxsoft.assignment.vowelscount.service;

import com.luxsoft.assignment.vowelscount.dto.VowelsCountDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileWriteService {

    /**
     * Input a List of VowelsCountDto and write the response in a file in separate line
     * Puts the file in the file location as specified in the properties
     * @param responseDtoMap contains Map of size of words and VowelsCountDto
     * @return String which has the response for writing file
     */
    String writeDataToFile(Map<Integer, VowelsCountDto> responseDtoMap) throws IOException;

}
