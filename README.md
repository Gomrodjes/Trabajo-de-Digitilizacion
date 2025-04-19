
# 📚 Book Manager - Sistema de Gestión de Libros

**Book Manager** es una aplicación desarrollada en Java con el objetivo de digitalizar y simplificar la gestión de una colección de libros, ya sea en el contexto de una biblioteca o una tienda. Esta herramienta permite visualizar, agregar y gestionar libros almacenados en un archivo JSON, ofreciendo una interfaz gráfica amigable al usuario.

---

## 🧩 Resumen del Proyecto

Este proyecto nace como parte de una iniciativa de digitalización, buscando trasladar procesos tradicionales de gestión de inventarios de libros a una solución informática moderna, eficiente y accesible.

Book Manager se centra en:
- Visualizar los libros disponibles en un formato claro.
- Gestionar el stock de libros desde un entorno gráfico.
- Facilitar futuras integraciones con bases de datos externas.
- Fomentar el uso de tecnologías como Java, JSON, Docker y Git en proyectos educativos o de pequeña escala.

---

## 🛠️ Tecnologías y Entornos de Desarrollo

- **Lenguaje principal**: Java 17
- **Entorno de desarrollo**: Eclipse IDE
- **Interfaz gráfica**: Java Swing
- **Persistencia de datos**: Archivos JSON
- **Contenerización (opcional)**: Docker
- **Control de versiones**: Git
- **Sistema operativo**: Windows 10/11

---

## 🗂️ Estructura del Proyecto

```plaintext
📁 Digitalizacion Trabajo/
├── 📁 bin/                   # Archivos compilados (.class)
├── 📁 img/                   # Imágenes usadas en la interfaz gráfica
├── 📁 json/                  # Archivo JSON con la base de datos de libros
│   └── libros.json
├── 📁 src/                   # Código fuente
│   ├── 📄 App.java           # Clase principal
│   ├── 📄 Book.java          # Clase para representar libros
│   ├── 📄 BookManager.java   # Lógica de negocio
│   ├── 📄 JsonHandler.java   # Lectura y escritura del JSON
│   └── 📄 BookGUI.java       # Interfaz gráfica Swing
├── 📄 Dockerfile (opcional)
├── 📄 README.md              # Documento descriptivo
└── 📄 .gitignore
```

---

## 🧠 Principales Funcionalidades

- 📄 **Carga dinámica de libros** desde un archivo `JSON`.
- 🧭 **Interfaz gráfica amigable** con botones, iconos y paneles de navegación.
- 🔄 **Actualización en tiempo real** de los datos visualizados.
- 🖼️ **Visualización de portadas** de libros desde URLs (si están disponibles).
- 🔧 **Modularidad en el código** para facilitar ampliaciones o mantenimiento.

---

## 🪄 Ejecución del Proyecto

### 🔹 Desde el entorno Eclipse

1. Importa el proyecto como *Java Project*.
2. Asegúrate de tener Java 17 instalado.
3. Ejecuta `App.java` como aplicación Java.

### 🔹 Desde Docker (opcional)

1. Asegúrate de tener Docker instalado.
2. Construye la imagen:
   ```bash
   docker build -t book-manager .
   ```
3. Ejecuta el contenedor:
   ```bash
   docker run -it book-manager
   ```

> Nota: Se puede montar un volumen para mantener el archivo `libros.json` persistente fuera del contenedor.

---

## 🚀 Posibles Mejoras Futuras

- 📦 Integrar MongoDB o PostgreSQL para una gestión más robusta de los datos.
- 🌐 Añadir funcionalidades de búsqueda y filtrado avanzado.
- 👥 Implementar gestión de usuarios para bibliotecarios y clientes avanzado.
- ☁️ Implementar acceso a través de web (con Spring Boot y API REST).
- 🧪 Añadir pruebas automatizadas con JUnit.
- 🛡️ Validar los datos de entrada del usuario con un sistema de formularios más seguro.

---

## 🧾 Créditos

Este proyecto ha sido desarrollado como parte de un trabajo académico sobre digitalización y modernización de procesos. Todas las imágenes utilizadas tienen fines ilustrativos y educativos.

---

## 📬 Contacto

Si deseas más información sobre este proyecto o estás interesado en colaborar:

**Autor**: Jesus Andres Gomez Rodriguez    
