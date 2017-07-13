web: java $JAVA_OPTS -jar target/dropwizard-1.0-SNAPSHOT.jar db migrate config.yaml && java $JAVA_OPTS -Ddw.server.connector.port=$PORT -jar target/dropwizard-1.0-SNAPSHOT.jar server config.yaml
