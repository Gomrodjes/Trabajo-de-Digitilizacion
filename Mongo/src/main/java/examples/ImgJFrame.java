package examples;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ImgJFrame extends JFrame{

	private JLabel imagenlbl;
	private JProgressBar progressBar;

	public ImgJFrame() {
		super("EL RINCON DEL PAPEL"); 
		initialize();
	}


	private void initialize() {
		
		setBounds(100, 100, 750, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
        ImageIcon imagen = new ImageIcon("files/Logo.png"); 
		imagenlbl = new JLabel(imagen);
        imagenlbl.setBounds((getWidth()/2)-(imagen.getIconWidth()/2), (getHeight()/2)-(imagen.getIconHeight()/2), imagen.getIconWidth(), imagen.getIconHeight());
		//imagenlbl.setBounds(50, 50, 600, 580);
        add(imagenlbl);
		setVisible(true);
		
		// Crear y configurar la barra de progreso
        progressBar = new JProgressBar();
        progressBar.setBounds((getWidth()/2)-(600/2), imagenlbl.getBounds().y+imagenlbl.getBounds().height+15, 600, 20);
        progressBar.setStringPainted(true); // Mostrar porcentaje
        // progressBar.setBounds(50, 250, 350, 20);
        //progressBar.setStringPainted(false); // Mostrar porcentaje
        add(progressBar);
        setVisible(true);
        // Configurar temporizador para simular progreso de carga
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int progreso = 0;
            @Override
            public void run() {
                // Incrementar el progreso
                progreso += 10;
                progressBar.setValue(progreso);

                if (progreso == 100) {
                    // Cuando la barra de progreso llega al 100%, cerrar el JFrame
                    dispose();
                    Inicio i = new Inicio();
                    cancel(); // Detener el temporizador
                }
            }
        }, 0, 500); // Cambiado a 500 milisegundos (0 segundos de espera antes de iniciar, 500 milisegundos entre cada actualización)


	}
}
