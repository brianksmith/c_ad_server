# Comcast Ad Server Coding Challenge

This coding challenge was writen in Java using Spring Boot and maven.  The persistence mechanism is just an in mmemory hashmap.  In a production environment it would probably have been in an sql database(mysql/postgres/etc) with a cache(redis) for read performance.  The web tier would consist of mulitple web servers behind a proxy utilizing a round robin distribution. 

## Building
mvn clean install

## Running 
mvn spring-boot:run

## Run Test Cases
mvn clean test

## Viewing an ad campaign
curl -X GET -H "Cache-Control: no-cache" "http://localhost:8080/ad/12"

## Creating an ad campaign
curl -X POST -H "accept: application/json" -H "Content-Type: application/json" -H "Cache-Control: no-cache"  -d '{
    "partner_id": "12",
    "ad_content": "Ad 1212",
    "duration" : 30
}' "http://localhost:8080/ad"

## Getting all ad campaigns
curl -X GET -H "Accept: application/json" -H "Cache-Control: no-cache"  "http://localhost:8080/ads"


