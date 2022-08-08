FROM java:8
ARG JAR_FILE
VOLUME /usr/fzz
MAINTAINER   2223080662@qq.com
COPY ${JAR_FILE} /home/app.jar
ENV JAVA_OPTS="-Xmx512m -Xms512m -Xmn512m -Xss256K -XX:SurvivorRatio=8 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=4 -XX:+CMSClassUnloadingEnabled -Dfile.encoding=UTF-8 -XX:+DisableExplicitGC -XX:+PrintGC -XX:+PrintGCTimeStamps -Xloggc:./gc.log"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/app.jar"]