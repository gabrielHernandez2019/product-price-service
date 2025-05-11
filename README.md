# Product Price Service

Este proyecto es un servicio desarrollado en **Java** utilizando **Spring Boot** que permite gestionar y consultar precios de productos. Es una prueba técnica que incluye funcionalidades de carga concurrente y pruebas de rendimiento con **JMeter**.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.4.5**
- **Gradle** como herramienta de construcción
- **H2 Database** para pruebas
- **JMeter** para pruebas de carga
- **Jacoco** para cobertura de código

## Estructura del proyecto

- `src/main/java`: Código fuente principal del servicio.
- `src/test/java`: Pruebas unitarias y de integración.
- `src/test/load`: Archivos de configuración de pruebas de carga con JMeter.
- `build/`: Directorio de construcción generado por Gradle.

## Configuración del entorno

1. **Requisitos previos**:
   - Java 17 instalado.
   - Gradle instalado.
   - JMeter instalado (opcional para pruebas de carga).

2. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/gabrielHernandez2019/product-price-service.git
   cd product-price-service

## Ejecutar el proyecto

```bash
./gradlew bootRun
```

## Ejecutar pruebas unitarias

```bash
./gradlew test
```

## Generar reporte de cobertura
```bash
./gradlew jacocoTestReport
```

## Ejecutar pruebas de carga con JMeter
```bash
./gradlew runJMeter
```

## Endpoints principales

El servicio expone los siguientes endpoints:

- GET /products: Consulta precios de productos.
 
  - **Parámetros:**
    - applicationDate: Fecha de consulta.
    - productId: ID del producto.
    - brandId: ID de la marca.
  **Ejemplo de uso:**
      ```bash
        curl --location 'localhost:8080/products?applicationDate=2023-06-14T10%3A00%3A00&productId=35455&brandId=1'
      ``` 

### Cambios realizados:
1. Cerré correctamente la lista numerada en "Configuración del entorno".
2. Alineé los encabezados y subencabezados para mayor consistencia.
3. Ajusté el formato de los bloques de código y listas para que sean más claros.
4. Reorganicé la sección "Endpoints principales" para que los parámetros y el ejemplo de uso estén alineados.