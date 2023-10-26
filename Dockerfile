# Define a imagem base
FROM openjdk:17
WORKDIR /app
COPY target/prestadora-app.jar .
CMD ["java", "-jar", "prestadora-app.jar"]
