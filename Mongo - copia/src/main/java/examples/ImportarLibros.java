package examples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class ImportarLibros {
    public static void main(String[] args) {
        // Ruta del archivo JSON
        String rutaArchivo = "files/Libros.json"; // Cámbialo según tu proyecto

        // Conectar con MongoDB
        String uri = "mongodb://admin:adminpassword@localhost:27017/ProyectoBBDD?authSource=admin/"; // Si usas MongoDB Atlas, cambia la URI
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("ProyectoBBDD");
            MongoCollection<Document> libros = database.getCollection("Libros");

            // Leer el archivo JSON
            Gson gson = new Gson();
            List<Document> documentos = new ArrayList<>();

            try (JsonReader reader = new JsonReader(new FileReader(rutaArchivo))) {
                reader.beginArray();

                while (reader.hasNext()) {
                    JsonElement element = gson.fromJson(reader, JsonElement.class);
                    if (element.isJsonObject()) {
                        documentos.add(Document.parse(gson.toJson(element)));
                    }
                }

                reader.endArray();
            } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
                System.out.println("Error al leer el archivo JSON: " + e.getMessage());
                return;
            }

            // Insertar en MongoDB
            if (!documentos.isEmpty()) {
                try {
                    libros.insertMany(documentos);
                    System.out.println("Libros importados exitosamente.");
                } catch (MongoException e) {
                    System.out.println("Error al insertar los libros en MongoDB: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron documentos para importar.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}