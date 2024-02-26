# Sistema Web de Gestión de Vehículos

Este es un sistema web desarrollado con Angular para el frontend y Java Spring Boot para el backend. El sistema proporciona funcionalidades de gestión de vehículos, incluyendo un sistema de autenticación de usuarios, manejo de tokens, y operaciones CRUD para tres tablas relacionadas: Vehículos, Modelos de Vehículos y Marcas de Vehículos.

## Características

- **Autenticación de Usuarios:** El sistema cuenta con un sistema de autenticación de usuarios que permite a los usuarios registrados iniciar sesión de manera segura.

- **Tokens de Autenticación:** Se utiliza un sistema de tokens para gestionar la autenticación de usuarios y autorizar el acceso a las distintas partes de la aplicación.

- **Consumo de Usuarios en el Backend:** Se consume información de usuarios en el backend, que se inserta en la base de datos para permitir el inicio de sesión en la aplicación web.

- **Operaciones CRUD:** Se implementan operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las tres tablas relacionadas: Vehículos, Modelos de Vehículos y Marcas de Vehículos.

## Tecnologías Utilizadas

### Frontend

- **Angular:** Se utiliza Angular como framework de desarrollo frontend para la construcción de la interfaz de usuario y la lógica de la aplicación.

- **HTML/CSS:** Se emplean HTML y CSS para la estructura y el diseño de las páginas web.

- **Material Design:** Se utiliza Angular Material para implementar un diseño moderno y consistente en la aplicación.

### Backend

- **Java Spring Boot:** Se utiliza Java Spring Boot para el desarrollo del backend de la aplicación, incluyendo la lógica de negocio, la gestión de usuarios y las operaciones CRUD para la base de datos.

- **Base de Datos:** Se emplea una base de datos relacional (por ejemplo, MySQL, PostgreSQL) para almacenar la información de los vehículos, modelos y marcas.

## Funcionalidades

- **Autenticación de Usuarios:** Los usuarios pueden registrarse e iniciar sesión en la aplicación. Se implementan medidas de seguridad para proteger la autenticación, como la encriptación de contraseñas y el uso de tokens JWT.

- **Gestión de Vehículos:** Los usuarios pueden realizar operaciones CRUD sobre los vehículos, incluyendo la creación, lectura, actualización y eliminación de registros.

- **Gestión de Modelos de Vehículos:** Los usuarios pueden gestionar los modelos de vehículos, incluyendo la creación, lectura, actualización y eliminación de registros.

- **Gestión de Marcas de Vehículos:** Los usuarios pueden gestionar las marcas de vehículos, incluyendo la creación, lectura, actualización y eliminación de registros.

# Instrucciones para levantar el proyecto

Este repositorio contiene un proyecto que consta de un backend desarrollado en Spring Boot y un frontend desarrollado en Angular, ambos ejecutándose en contenedores Docker, junto con una base de datos MySQL también en un contenedor Docker.

## Requisitos previos

Antes de comenzar, asegúrate de tener instalado lo siguiente en tu sistema:

- Docker: Puedes descargar e instalar Docker desde [este enlace](https://www.docker.com/get-started).

## Pasos para levantar el proyecto

Sigue estos pasos para levantar el proyecto en tu entorno local:

1. Clona este repositorio en tu máquina local:

    ```bash
    git clone https://url-del-repositorio.git
    ```

2. Navega al directorio del proyecto:

    ```bash
    cd nombre-del-directorio-del-proyecto
    ```

3. Levanta los contenedores Docker utilizando Docker Compose:

    ```bash
    docker-compose up --build
    ```

   Esto creará y ejecutará los contenedores Docker para el backend, el frontend y la base de datos MySQL.

4. Una vez que los contenedores estén en funcionamiento, puedes acceder a la aplicación en tu navegador web:

   - **Backend**: http://localhost:8080
   - **Frontend**: http://localhost:4200


## Configuraciones previas

1. Ejecutar la api http://localhost:8080/rol/crearRoles con solicitud get para crear los roles de usuario necesarios para el funcionamiento del proyecto
2. Ejecutar la api localhost:8080/auth/usuariosExternos con solicitud get para crear los usuarios necesarios para el funcionamiento del proyecto 
3. Ir a http://localhost:4200 en el navegador e iniciar sesion con agun registro de esta api https://dummyjson.com/users (usar el campo username y password)
3. Por ahora el proyecto no tiene validaciones sobre si se elimina un registro que tiene un campo relacionado por lo que se sugiere que no haga eso, dicho esto disfruta del proyecto



## Detener y limpiar los contenedores

Para detener y eliminar los contenedores Docker, ejecuta el siguiente comando en el directorio del proyecto:

```bash
docker-compose down

## Contribución

Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama para tu nueva característica (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit de ellos (`git commit -am 'Añade una nueva característica'`).
4. Haz push de la rama (`git push origin feature/nueva-caracteristica`).
5. Crea un nuevo Pull Request.
