package examples;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Libro {

	private ObjectId id;

	@BsonProperty("nombre")
	private String nombre;

	@BsonProperty("autor")
	private String autor;

	@BsonProperty("precio")
	private double precio;

	@BsonProperty("editorial")
	private String editorial;

	@BsonProperty("foto")
	private String foto;

	public ObjectId getId() {
		return id;
	}

	public Libro setId(ObjectId id) {
		this.id = id;
		return this;
	}

	public String getNombre() {
		return nombre;
	}

	public Libro setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getAutor() {
		return autor;
	}

	public Libro setAutor(String autor) {
		this.autor = autor;
		return this;
	}

	public double getPrecio() {
		return precio;
	}

	public Libro setPrecio(double precio) {
		this.precio = precio;
		return this;
	}

	public String getEditorial() {
		return editorial;
	}

	public Libro setEditorial(String editorial) {
		this.editorial = editorial;
		return this;
	}

	public String getFoto() {
		return foto;
	}

	public Libro setFoto(String foto) {
		this.foto = foto;
		return this;
	}

	@Override
	public String toString() {
		return "Libro Seleccionado: " + "\nNombre: " + nombre + "\nAutor: " + autor + "\nPrecio: " + precio
				+ "\nEditorial: " + editorial;
	}
}

