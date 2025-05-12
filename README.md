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

**Capa de Dominio (Core)**
Contiene la lógica de negocio pura y es completamente independiente de frameworks o 
tecnologías externas.


    - Ubicación: src/main/java/com/zara/price/domain

    - Componentes:
        Entidades: Representan los objetos principales del dominio.
        Value Objects: Objetos inmutables que encapsulan valores.
        Interfaces de puertos: Contratos para interactuar con la lógica de negocio.

**Capa de Aplicación**
Coordina los casos de uso y orquesta la lógica de negocio.

    - Ubicación: src/main/java/com/zara/price/application

    - Componentes:
        Casos de uso: Implementan la lógica de los procesos del negocio.
        Servicios de aplicación: Gestionan la interacción entre el dominio y las capas externas.

**Capa de Infraestructura**
Maneja la interacción con el mundo exterior, como APIs REST.

    - Ubicación: src/main/java/com/zara/price/adapter/in
    
    - Componentes:
        Controladores: Exponen los endpoints para interactuar con el sistema.

**Capa de Salida (Adaptadores Secundarios)**
Implementa la comunicación con sistemas externos.


    - Ubicación: src/main/java/com/zara/price/adapter/out
    - Componentes:
        Kafka Producer: Envío de eventos a Kafka.
        Repositorios: Implementaciones para interactuar con la base de datos.

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