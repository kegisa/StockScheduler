FROM bellsoft/liberica-openjdk-alpine-musl
COPY ./target/StockScheduler-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","StockScheduler-0.0.1-SNAPSHOT.jar"]