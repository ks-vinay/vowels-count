package com.luxsoft.assignment.vowelscount.dto;

import lombok.Data;

import java.util.Set;

@Data
public class VowelsCountDto {

    private Set<Character> uniqueVowelsChar;
    private double vowelsAverage;
    private int wordsLength;
    private int totalNumOfVowels;
    private int totalNumOfWords;


}
