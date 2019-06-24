FROM buildpack-deps:jessie-curl

# the version has to be the same as the one developed against
RUN curl -sL https://github.com/shyiko/jabba/raw/master/install.sh | \
    JABBA_COMMAND="install openjdk@1.11.0 -o /jdk" bash

ENV JAVA_HOME /jdk
ENV PATH $JAVA_HOME/bin:$PATH

#speedup startup(?)
VOLUME /tmp
ARG JAR_FILE

ENV _JAVA_OPTIONS "-Djava.awt.headless=true"

COPY ${JAR_FILE} /opt/app.jar

WORKDIR /opt

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/app.jar"]