## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is used to calculate the vowels present in the file and generate output file which consist the average number of vowels per word and
grouped by set of vowels present in a word and length of the word

Example file content
Platon made bamboo boats.

Output file 

({a, o}, 6) -> 2.5
({a, o}, 5) -> 2
({a, e}, 4) -> 2

#### Note : It calculates the count of vowels only for words


## Technologies
Project is created with:
* Java: 11
* Spring Boot version: 2.5.8
* Junit: 5
* Swagger Version : 2.4.0

## Setup
###Instructions to run this project :

- Install Java 11
- Add the output file location in application.properties (result.file.location)
- Run the project, once the application is starts in local open with the below url

http://localhost:8080/swagger-ui.html#!/

- click on vowels-count-controller
- click on  /readTextFile and add input file by choose file 
- Click on Try it out! to execute the program

