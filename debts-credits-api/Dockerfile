FROM gradle:7.2.0-jdk17
COPY --chown=gradle:gradle . .
WORKDIR .
RUN gradle build -x test --no-daemon
EXPOSE 8081
CMD ["gradle", "bootRun"]
