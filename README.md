# Proyecto: Aplicación Java con MongoDB

## Descripción
Este proyecto es una aplicación Java que se conecta a una base de datos MongoDB y permite gestionar usuarios y libros. Incluye una interfaz gráfica y funcionalidad para importar libros desde un archivo JSON.

## Tecnologías utilizadas
- **Java** (JDK 11 o superior)
- **MongoDB** (NoSQL database)
- **Maven** (Gestor de dependencias)
- **Gson** (Para manejar JSON)
- **JGoodies & MigLayout** (Para la interfaz gráfica)

## Estructura del Proyecto
```
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
```

## Configuración

Antes de ejecutar los ejemplos, asegúrate de haber configurado correctamente la conexión a la base de datos MongoDB en la clase `Conex.java`. Debes proporcionar la URL de conexión, el nombre de la base de datos y la colección que se utilizará.

```java
private static String conex = "mongodb+srv://usuario:contraseña@servidor.mongodb.net/";
private static String databaseName = "NombreBaseDeDatos";
private static String collectionName = "NombreColeccion";

ConnectionString connectionString = new ConnectionString(conex);

		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		// Creamos el cliente MongoDB
		MongoClient mongoClient = MongoClients.create(clientSettings);

		// Recuperar base de datos
		db = mongoClient.getDatabase(databaseName);

        // Recuperar coleccion
		collection = db.getCollection(collectionName);
```

## Funcionalidades
- Conexión con MongoDB para almacenamiento de datos.
- Importación de datos desde archivos JSON.
- Interfaz gráfica para gestionar usuarios y libros.

## Autores
- Jesus Andres Gomez Rodriguez
- Pablo Romo Grilo

## Licencia
Este proyecto se distribuye bajo la licencia [MIT](LICENSE).
