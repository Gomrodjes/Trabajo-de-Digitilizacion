Proyecto: Aplicación Java con MongoDB

Descripción

Este proyecto es una aplicación Java que se conecta a una base de datos MongoDB y permite gestionar usuarios y libros. Incluye una interfaz gráfica y funcionalidad para importar libros desde un archivo JSON.

Tecnologías utilizadas

Java (JDK 11 o superior)

MongoDB (NoSQL database)

Maven (Gestor de dependencias)

Gson (Para manejar JSON)

JGoodies & MigLayout (Para la interfaz gráfica)

Estructura del Proyecto

Mongo/
├── src/main/java/examples/
│   ├── AppPrincipal.java  # Punto de entrada de la aplicación
│   ├── Conex.java         # Manejo de conexión con MongoDB
│   ├── CrearUsuarios.java # Lógica para crear usuarios
│   ├── Usuario.java       # Clase modelo para usuarios
│   ├── ImportarLibros.java # Importación de libros desde JSON
│   ├── Libro.java         # Clase modelo para libros
│   ├── ImgJFrame.java, Inicio.java, Nuevo.java # Componentes de la interfaz gráfica
├── files/Libros.json      # Archivo JSON con datos de libros
├── pom.xml               # Archivo de configuración de Maven

Instalación y Ejecución

Clonar el repositorio

git clone <URL_DEL_REPOSITORIO>
cd Mongo

Configurar MongoDB

Asegúrate de tener MongoDB instalado y en ejecución.

Configura la conexión en Conex.java si es necesario.

Compilar y ejecutar

mvn clean install
mvn exec:java -Dexec.mainClass="examples.AppPrincipal"

Funcionalidades

Conexión con MongoDB para almacenamiento de datos.

Importación de datos desde archivos JSON.

Interfaz gráfica para gestionar usuarios y libros.

Autores

[Tu Nombre o Equipo]

Licencia

Este proyecto se distribuye bajo la licencia MIT.
