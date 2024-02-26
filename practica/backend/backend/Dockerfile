# Usar una imagen base oficial de Java
FROM openjdk:17-jdk-alpine as build

# Definir el directorio de trabajo
WORKDIR /app

# Copiar el proyecto y construirlo
COPY . .
RUN ./mvnw clean package -DskipTests

# Empaquetar la aplicación
FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto y ejecutar la aplicación
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]