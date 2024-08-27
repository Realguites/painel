FROM openjdk:17-jdk-alpine

RUN mkdir /app
WORKDIR /app

RUN wget https://services.gradle.org/distributions/gradle-8.10-bin.zip && \
    unzip gradle-8.10-bin.zip

# Copiar o arquivo build.gradle
# Set environment variables for Gradle
ENV GRADLE_HOME=/app/gradle-8.10
ENV PATH=$GRADLE_HOME/bin:$PATH
COPY build.gradle /app

# Executar o Gradle Build
RUN gradle build

# Copiar o JAR gerado
COPY build/libs/*.jar /app/app.jar

CMD ["java","-jar","/app/app.jar"]