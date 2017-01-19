# Comcast Ad Server Coding Challenge

This coding challenge was writen in Java using Spring Boot and maven.

## Building
mvn clean install

## Running 
mvn spring-boot:run

## Viewing an ad campaign
curl -X GET -H "Cache-Control: no-cache" "http://localhost:8080/ad/12"

## Creating an ad campaign
curl -X POST -H "accept: application/json" -H "Content-Type: application/json" -H "Cache-Control: no-cache"  -d '{
    "partner_id": "12",
    "ad_content": "Ad 1212",
    "duration" : 30
}' "http://localhost:8080/ad"



