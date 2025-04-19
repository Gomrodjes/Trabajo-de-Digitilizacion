package examples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Nuevo extends JFrame {

    private JLabel nombreLabel;
    private JLabel priceLabel;
    private JLabel autorLabel;
    private JLabel editorialLabel;
    private JLabel urlLabel;
    private JTextField nombre;
    private JTextField modelo;
    private JTextField precio;
    private JTextField autor;
    private JTextField editorial;
    private JTextField url;
    private JButton addButton;
    private static String conex = "mongodb://admin:adminpassword@localhost:27017/ProyectoBBDD?authSource=admin";
    private static String databaseName = "ProyectoBBDD";
    private static String collectionName = "Libros";
    private JButton btnVolver;

    public Nuevo() {
        super("Añadir nuevo Libro");
        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        nombreLabel = new JLabel("Nombre");
        nombreLabel.setBounds(16, 56, 61, 16);
        add(nombreLabel);

        nombre = new JTextField();
        nombre.setBounds(71, 51, 130, 26);
        add(nombre);
        nombre.setColumns(10);

        autorLabel = new JLabel("Autor");
        autorLabel.setBounds(232, 84, 61, 16);
        add(autorLabel);

        autor = new JTextField();
        autor.setColumns(10);
        autor.setBounds(303, 79, 130, 26);
        add(autor);

        priceLabel = new JLabel("Precio");
        priceLabel.setBounds(16, 84, 61, 16);
        add(priceLabel);

        precio = new JTextField();
        precio.setColumns(10);
        precio.setBounds(71, 79, 130, 26);
        add(precio);

        editorialLabel = new JLabel("Editorial");
        editorialLabel.setBounds(16, 112, 61, 16);
        add(editorialLabel);

        editorial = new JTextField();
        editorial.setColumns(10);
        editorial.setBounds(71, 108, 130, 26);
        add(editorial);

        urlLabel = new JLabel("Url Foto");
        urlLabel.setBounds(232, 112, 61, 16);
        add(urlLabel);

        url = new JTextField();
        url.setColumns(10);
        url.setBounds(303, 108, 130, 26);
        add(url);

        addButton = new JButton("Añadir");
        addButton.addActionListener(new ManejadorBoton());
        addButton.setBounds(272, 224, 117, 29);
        add(addButton);
        addButton.setEnabled(true);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnVolver) {
                    dispose();
                    new ConexA();
                }
            }
        });
        btnVolver.setBounds(71, 224, 117, 29);
        add(btnVolver);
        setVisible(true);
    }

    private class ManejadorBoton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (nombre.getText().isEmpty() || precio.getText().isEmpty()
                    || autor.getText().isEmpty() || editorial.getText().isEmpty() || url.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos deben estar relleno", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    CodecRegistry pojoCodecRegistry = CodecRegistries
                            .fromProviders(PojoCodecProvider.builder().automatic(true).build());
                    CodecRegistry codecRegistry = CodecRegistries
                            .fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

                    ConnectionString connectionString = new ConnectionString(conex);

                    MongoClientSettings clientSettings = MongoClientSettings.builder()
                            .applyConnectionString(connectionString).codecRegistry(codecRegistry).build();

                    MongoClient mongoClient = MongoClients.create(clientSettings);

                    MongoDatabase db = mongoClient.getDatabase(databaseName);

                    MongoCollection<Libro> collection = db.getCollection(collectionName, Libro.class);

                    Libro l = new Libro().setNombre(nombre.getText()).setPrecio(Double.parseDouble(precio.getText()))
                            .setAutor(autor.getText()).setEditorial(editorial.getText())
                            .setFoto(url.getText());
                    collection.insertOne(l);
                    JOptionPane.showMessageDialog(null, "Libro insertado correctamente",
                            "LIBRO AÑADIDO", JOptionPane.INFORMATION_MESSAGE);
                    mongoClient.close();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El precio debe ser un número válido", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

