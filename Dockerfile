# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY build/libs/product-price-service-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]