FROM bellsoft/liberica-openjdk-alpine-musl
COPY ./target/StockScheduler-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","StockScheduler-0.0.1-SNAPSHOT.jar"]

 Time for getting from Redis - 40
 Time for getting from Tinkoff - 580
 Time for saving to Redis - 79
 Time for update - 702