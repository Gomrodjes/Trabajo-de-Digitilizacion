package examples;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class ConexA extends JFrame {

	private static String conex = "mongodb://admin:adminpassword@localhost:27017/ProyectoBBDD?authSource=admin";
	private static String databaseName = "ProyectoBBDD";
	private static String collectionName1 = "Libros";
	private static String collectionName2 = "Sugerencias";
	private static MongoCollection<Document> collection1;
	private static MongoCollection<Document> collection2;
	private JComboBox comboLibros;
	private JComboBox comboSugerencias;
	private JButton carButton;
	private JButton cargarButton;
	private JButton deleteButton;
	private JButton addButton;
	private JButton exportarButton;
	private JLabel logo;
	private JButton btnSalir;
	private JLabel seleccionLabel;
	private static MongoDatabase db;
	private static List<Libro> libros;
	private static List<Libro> librossugerencias;

	public ConexA() {
		super("Menú Principal");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 750, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		logo = new JLabel();
		logo.setIcon(new ImageIcon("files/logopequeño.png"));
		logo.setBounds((getWidth() / 2) - (logo.getIcon().getIconWidth() / 2), 11, 250, 130);
		add(logo);

		btnSalir = new JButton(new ImageIcon("files/salir.png"));
		btnSalir.addActionListener(new manejadorButton());
		btnSalir.setBounds(getWidth() - 75, getHeight() - 100, 50, 50);
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		btnSalir.setIcon(new ImageIcon("files/salir.png"));
		add(btnSalir);

		seleccionLabel = new JLabel("Selecciona un libro: ");
		seleccionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		seleccionLabel.setBounds(10, 150, getWidth() / 2 - 20, 50);
		add(seleccionLabel);

		comboLibros = new JComboBox();
		comboLibros.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		comboLibros.setEnabled(true);
		comboLibros.addActionListener(new manejadorButton());
		comboLibros.setBounds(10, 200, getWidth() / 2 - 20, 50);
		add(comboLibros);

		JLabel sugerenciaLabel = new JLabel("Sugerencias: ");
		sugerenciaLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		sugerenciaLabel.setBounds(getWidth() / 2 + 10, 150, getWidth() / 2 - 20, 50);
		add(sugerenciaLabel);

		comboSugerencias = new JComboBox();
		comboSugerencias.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		comboSugerencias.setEnabled(true);
		comboSugerencias.addActionListener(new manejadorButton());
		comboSugerencias.setBounds(getWidth() / 2 + 10, 200, getWidth() / 2 - 20, 50);
		add(comboSugerencias);

		JPanel panel = new JPanel();
		panel.setBounds(10, 260, 700, 100);
		add(panel);

		carButton = new JButton("Características");
		carButton.addActionListener(new manejadorButton());
		carButton.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		carButton.setEnabled(false);
		panel.add(carButton);

		deleteButton = new JButton("Eliminar");
		deleteButton.addActionListener(new manejadorButton());
		deleteButton.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		deleteButton.setEnabled(false);
		panel.add(deleteButton);

		addButton = new JButton("Nuevo");
		addButton.addActionListener(new manejadorButton());
		addButton.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		panel.add(addButton);

		exportarButton = new JButton("Exportar");
		exportarButton.addActionListener(new manejadorButton());
		exportarButton.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		panel.add(exportarButton);

		cargarButton = new JButton("Cargar");
		cargarButton.addActionListener(new manejadorButton());
		cargarButton.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		cargarButton.setVisible(false);
		panel.add(cargarButton);


		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		cargarButton.doClick(); // Simulate button click
		carButton.setEnabled(comboLibros.getItemCount() > 0); // Enable carButton if there are items
	}

	public static void generarConex() {

		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);

		ConnectionString connectionString = new ConnectionString(conex);

		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		// Creamos el cliente MongoDB
		MongoClient mongoClient = MongoClients.create(clientSettings);

		// Recuperar base de datos
		db = mongoClient.getDatabase(databaseName);

		collection1 = db.getCollection(collectionName1);
		collection2 = db.getCollection(collectionName2);
	}

	private class manejadorButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			generarConex();

			if (e.getSource() == addButton) {
				dispose();
				Nuevo n = new Nuevo();
			}

			if (e.getSource() == cargarButton) {
				MongoCollection<Libro> collectionLibro1 = db.getCollection(collectionName1, Libro.class);
				MongoCursor<Libro> resultDocument1 = collectionLibro1.find().iterator();
				libros = new ArrayList<>();
				while (resultDocument1.hasNext()) {
					Libro libro = resultDocument1.next();
					libros.add(libro);
				}
				for (Libro libro : libros) {
					comboLibros.addItem(libro.getNombre());
				}

				MongoCollection<Libro> collectionLibro2 = db.getCollection(collectionName2, Libro.class);
				MongoCursor<Libro> resultDocument2 = collectionLibro2.find().iterator();
				libros = new ArrayList<>();
				while (resultDocument2.hasNext()) {
					Libro libro = resultDocument2.next();
					libros.add(libro);
				}
				for (Libro libro : libros) {
					comboSugerencias.addItem(libro.getNombre());
				}

				if (comboLibros.getSelectedItem() != null) {
					carButton.setEnabled(true);
					deleteButton.setEnabled(true);
				}
			}

			if (e.getSource() == carButton) {
				String libroSeleccionado = comboLibros.getSelectedItem().toString();
				for (Libro libro : libros) {
					if (libro.getNombre().equalsIgnoreCase(libroSeleccionado)) {
						JFrame frame = new JFrame("Características de " + libro.getNombre());
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setSize(650, 400);
						frame.setLayout(new BorderLayout());
						try {
							URL url = new URL(libro.getFoto());
							ImageIcon imageIcon = new ImageIcon(url);
							JLabel label = new JLabel(imageIcon);
							String textoString = String.format("<html><body>%s</body></html>",
									libro.toString().replace("\n", "<br>"));
							JLabel texto = new JLabel(textoString);
							texto.setFont(new Font("Arial", Font.BOLD, 15));
							texto.setHorizontalAlignment(JLabel.CENTER);
							JPanel panel = new JPanel();
							panel.add(label);
							panel.add(texto);
							frame.add(panel, BorderLayout.CENTER);
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
						frame.setVisible(true);
					}
				}
			}

			if (e.getSource() == deleteButton) {
				// Verificar si hay un elemento seleccionado en el JComboBox
				if (comboLibros.getSelectedItem() != null) {
					// Obtener el nombre del modelo seleccionado
					String libroSeleccionado = comboLibros.getSelectedItem().toString();

					// Verificar que la conexión a la base de datos esté establecida correctamente
					if (db != null) {
						System.out.println("Conexión a la base de datos establecida correctamente");

						// Eliminar el documento de la base de datos
						DeleteResult deleteResult = collection1.deleteOne(new Document("nombre", libroSeleccionado));

						// Verificar si se eliminó correctamente el documento
						if (deleteResult.getDeletedCount() > 0) {
							// Eliminar el elemento seleccionado del JComboBox
							comboLibros.removeItem(libroSeleccionado);
							JOptionPane.showMessageDialog(null,
									"Documento eliminado exitosamente de la base de datos y del JComboBox.");
							System.out.println("Documento eliminado correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "No se encontró el documento en la base de datos.");
							System.out.println("No se encontró el documento en la base de datos");
						}
					} else {
						System.out.println("Error: No se pudo establecer conexión a la base de datos");
					}
				}
			}

			if (e.getSource() == exportarButton) {
				// Verificar si hay un elemento seleccionado en el comboSugerencias
				if (comboSugerencias.getSelectedItem() != null) {
					// Obtener el nombre del libro seleccionado
					String libroSeleccionado = comboSugerencias.getSelectedItem().toString();

					// Verificar que la conexión a la base de datos esté establecida correctamente
					if (db != null) {
						System.out.println("Conexión a la base de datos establecida correctamente");

						// Buscar el documento en la colección2
						Document libro = collection2.find(new Document("nombre", libroSeleccionado)).first();

						if (libro != null) {
							// Insertar el documento en la colección1
							collection1.insertOne(libro);

							// Eliminar el documento de la colección2
							DeleteResult deleteResult = collection2
									.deleteOne(new Document("nombre", libroSeleccionado));

							// Verificar si se eliminó correctamente el documento
							if (deleteResult.getDeletedCount() > 0) {
								// Eliminar el elemento seleccionado del comboSugerencias
								comboSugerencias.removeItem(libroSeleccionado);
								JOptionPane.showMessageDialog(null,
										"Libro exportado exitosamente a la colección principal y eliminado de sugerencias.");
								System.out.println("Libro exportado y eliminado correctamente");
							} else {
								JOptionPane.showMessageDialog(null,
										"No se encontró el documento en la colección de sugerencias.");
								System.out.println("No se encontró el documento en la colección de sugerencias");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"No se encontró el libro en la colección de sugerencias.");
						}
					} else {
						System.out.println("Error: No se pudo establecer conexión a la base de datos");
					}
				}
				cargarButton.doClick(); // Simulate button click
			}

			if (e.getSource() == btnSalir) {
				JOptionPane.showMessageDialog(null, "Gracias por usar nuestro programa :)");
				System.exit(0);
			}
		}
	}
}



