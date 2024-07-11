# Proyecto Literatura

Este proyecto utiliza Spring Boot y Hibernate para manejar libros y autores. Proporciona funcionalidades básicas como búsqueda de libros, gestión de autores, y filtrado por idioma.

## Inicio rápido

Para ejecutar este proyecto localmente, sigue estos pasos:

### Requisitos previos

Asegúrate de tener instalados:

- Java Development Kit (JDK) 11 o superior
- Maven
- PostgreSQL

### Configuración

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/tu-usuario/literatura.git
   cd literatura

2. **Configurar la base de datos:**

   - Crea una base de datos PostgreSQL llamada `literatura`.
   - Configura las credenciales de tu base de datos en `src/main/resources/application.properties`:

     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/literatura
     spring.datasource.username=tu-usuario
     spring.datasource.password=tu-contraseña
     spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
     spring.jpa.hibernate.ddl-auto = update
     ```

   Asegúrate de reemplazar `tu-usuario` y `tu-contraseña` con las credenciales adecuadas para tu entorno de base de datos PostgreSQL.

3. **Ejecutar el proyecto y funcionalidades:**

   - Asegúrate de tener configurada tu base de datos según el paso anterior.
   - Inicia la aplicación ejecutando `mvn spring-boot:run` desde la raíz del proyecto.
   - Una vez iniciada la aplicación, puedes usar las siguientes funcionalidades desde la consola:

     ```markdown
     ### Funcionalidades disponibles:

     1. **Buscar libro por título:**
        - Introduce el título del libro que deseas buscar. La aplicación consultará una API externa y guardará la información del libro encontrado en la base de datos.

     2. **Listar libros registrados:**
        - Muestra todos los libros actualmente almacenados en la base de datos, incluyendo detalles como título, número de descargas, idiomas y autor.

     3. **Listar autores registrados:**
        - Muestra todos los autores registrados en la base de datos junto con sus libros asociados.

     4. **Listar autores vivos en un determinado año:**
        - Filtra y muestra los autores que estaban vivos en el año especificado.

     5. **Listar libros por idiomas:**
        - Permite buscar y mostrar libros filtrados por idioma.

     0. **Salir del programa:**
        - Termina la ejecución de la aplicación.

     Nota: La funcionalidad de búsqueda de libros por título es la única que funciona sin la base de datos inicialmente. Una vez que se guarda un libro encontrado, todas las demás funcionalidades empiezan a operar correctamente.
     ```
