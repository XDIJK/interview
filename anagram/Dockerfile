FROM openjdk:17
COPY ./src/main/java/com/xdijk/anagram/AnagramComp.java /
COPY ./scripts/docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]

