package Main_sockets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Esta clase hace la parte de cliente en la conexion por socket para enviar
 * la informacion de los jugadores al servidor 
 * @author Bojorge
 *
 */


public class Cliente {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Cliente c=new Cliente(150);
		Cliente d=new Cliente(700);
		Servidor s=new Servidor();
	}
	/**
	 * Metodo constructor de la clase cliente
	 * @param x - este parametro le da la posicion en el eje x a la ventana de registro de cada jugador
	 */
	public Cliente(int x) {
		@SuppressWarnings("unused")
		VentanaPrincipal v=new VentanaPrincipal(x);
	}
	
}

/**
 * Esta clase crea la ventana para el registro del jugador
 * @author Bojorge
 *
 */
class VentanaPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * metodo constructor
	 * @param x - le da la posicion en el eje x
	 */
	public VentanaPrincipal(int x){
		
		setBounds(x,300,500,100);
				
		PanelPrincipalCliente lamina=new PanelPrincipalCliente();
		add(lamina);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}	
	
}
/**
 * Esta clase crea el panel que contiene a los componentes que estan en la ventana
 * @author Bojorge
 *
 */
class PanelPrincipalCliente extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField nickName,ip;
	
	private JButton botonEnviar;
	/**
	 * Metodo constructor, agrega los elementos al panel
	 */
	public PanelPrincipalCliente(){
		
		JLabel Jugador=new JLabel("Jugador >>>");		
		add(Jugador);
		
		nickName=new JTextField(5);		
		add(nickName);
	
		JLabel direccionIP=new JLabel("IP >>>");
		add(direccionIP);
		
		ip=new JTextField(8);
		ip.setText("192.168.0.11");
		add(ip);		
	
		botonEnviar=new JButton("Jugar");
		
		EnviarRegistro enviaRegistro=new EnviarRegistro();
		
		botonEnviar.addActionListener(enviaRegistro);
		
		add(botonEnviar);	
		
		Thread hilo=new Thread(this);
		
		hilo.start();
		
	}
	/**
	 * Esta clase crea el evento que envia la informacion de cada cliente al servidor
	 * @author Bojorge
	 *
	 */
	private class EnviarRegistro implements ActionListener{
		/**
		 * este metodo se debe implementar al implementar la interface Action listener
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			
			try {
				Socket socket=new Socket("192.168.0.11",9999);
				
//				Objeto que contiene los datos
				PaqueteEnvio datos=new PaqueteEnvio();
				
				datos.setNick(nickName.getText());
				datos.setIp(ip.getText());
//				datos.setMensaje(campo1.getText());
				
//				flujo para enviar el objeto al servidor
				ObjectOutputStream paquete_datos=new ObjectOutputStream(socket.getOutputStream());
				paquete_datos.writeObject(datos);
				
				socket.close();
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				
			}
			
		}
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
			
}

/**
 * Esta clase crea un paquete donde se almacenan los datos nesesarios para enviar al servidor
 * @author Bojorge
 *
 */
class PaqueteEnvio implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nick,ip;
/**
 * 
 * @return - regresa el nombre del jugador
 */
	public String getNick() {
		return nick;
	}
/**
 * configura el nombre de usuario
 * @param nick - nombre de cada jugador
 */
	public void setNick(String nick) {
		this.nick = nick;
	}
/**
 * devuelve la direccion ip del equipo al que se debe conectar 
 * @return la direccion
 */
	public String getIp() {
		return ip;
	}
/**
 * configura la direccion ip
 * @param ip - direccion
 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	}
}
