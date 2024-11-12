# Usa la imagen base de OpenJDK 17 
FROM openjdk:17-jdk-slim

# Define el argumento para especificar el archivo JAR
ARG JAR_FILE=target/*.jar

# Copia el archivo JAR generado en el contenedor
COPY ${JAR_FILE} app.jar

# Expone el puerto que usa la aplicación 
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]