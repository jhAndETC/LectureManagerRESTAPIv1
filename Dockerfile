# 먼저 Maven을 사용하여 Spring 애플리케이션을 빌드하는 단계
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Tomcat 이미지를 기반으로 최종 이미지를 빌드하는 단계
FROM tomcat:9.0.82-jre11
WORKDIR /usr/local/tomcat/webapps
# 빌드 단계에서 생성된 WAR 파일을 복사하여 배포합니다.
COPY --from=build /app/target/SpringMainforce.war ROOT.war
EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]
