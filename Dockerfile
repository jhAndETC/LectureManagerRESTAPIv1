# Tomcat 9.0.82 이미지를 사용합니다.
FROM tomcat:9.0.82-jre11

# 작업 디렉토리를 설정합니다.
WORKDIR /usr/local/tomcat/webapps

# 호스트 머신에서 Spring 애플리케이션의 WAR 파일을 복사합니다. (빌드한 WAR 파일 이름에 맞게 수정하세요)
COPY target/SpringMainforce.war ROOT.war

# 포트 설정 (Tomcat 기본 포트는 8080입니다. 필요에 따라 수정하세요)
EXPOSE 8080

# 컨테이너가 시작될 때 실행할 명령을 설정합니다.
ENTRYPOINT ["catalina.sh", "run"]

