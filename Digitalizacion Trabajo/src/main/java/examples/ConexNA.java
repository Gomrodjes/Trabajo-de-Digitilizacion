package examples;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
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

public class ConexNA extends JFrame {

	private static String conex = "mongodb://admin:adminpassword@localhost:27017/ProyectoBBDD?authSource=admin";
	private static String databaseName = "ProyectoBBDD";
	private static String collectionName = "Libros";
	private static MongoCollection<Document> collection;
	private JComboBox comboLibros;
	private JButton carButton;
	private JButton cargarButton;
	private JButton sugerenciaButton;
	private JLabel logo;
	private JButton btnSalir;
	private JLabel seleccionLabel;
	private static MongoDatabase db;
	private static List<Libro> libros;

	public ConexNA() {
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
		seleccionLabel.setBounds(10, 150, getWidth() - 20, 50);
		add(seleccionLabel);

		comboLibros = new JComboBox();
		comboLibros.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		comboLibros.setEnabled(true);
		comboLibros.addActionListener(new manejadorButton());
		comboLibros.setBounds(10, 200, 700, 50);
		add(comboLibros);

		JPanel panel = new JPanel();
		panel.setBounds(10, 260, 700, 100);
		add(panel);

		cargarButton = new JButton("Cargar");
		cargarButton.addActionListener(new manejadorButton());
		cargarButton.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		cargarButton.setVisible(false);
		panel.add(cargarButton);

		carButton = new JButton("Características");
		carButton.addActionListener(new manejadorButton());
		carButton.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		carButton.setEnabled(false);
		panel.add(carButton);

		sugerenciaButton = new JButton("Sugerencia");
		sugerenciaButton.addActionListener(new manejadorButton());
		sugerenciaButton.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		panel.add(sugerenciaButton);

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

		collection = db.getCollection(collectionName);
	}

	private class manejadorButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			generarConex();

			if (e.getSource() == sugerenciaButton) {
				dispose();
				Sugerencia s = new Sugerencia();
			}

			if (e.getSource() == cargarButton) {
				MongoCollection<Libro> collectionLibro = db.getCollection(collectionName, Libro.class);
				MongoCursor<Libro> resultDocument = collectionLibro.find().iterator();
				libros = new ArrayList<>();
				while (resultDocument.hasNext()) {
					Libro libro = resultDocument.next();
					libros.add(libro);
				}
				for (Libro libro : libros) {
					comboLibros.addItem(libro.getNombre());
				}
				if (comboLibros.getSelectedItem() != null) {
					carButton.setEnabled(true);
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

			if (e.getSource() == btnSalir) {
				JOptionPane.showMessageDialog(null, "Gracias por usar nuestro programa :)");
				System.exit(0);
			}
		}
	}
}
