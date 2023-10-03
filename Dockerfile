FROM openjdk:17

RUN mkdir /media/stories
RUN mkdir /media/feeds
RUN mkdir /media/feeds/avatars
RUN mkdir /media/feeds/images

COPY target/clonegram-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "app.jar"]