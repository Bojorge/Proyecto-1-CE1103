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



public class Cliente {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Cliente c=new Cliente(150);
		Cliente d=new Cliente(700);
		Servidor s=new Servidor();
	}
	
	public Cliente(int x) {
		@SuppressWarnings("unused")
		VentanaPrincipal v=new VentanaPrincipal(x);
	}
	
}


class VentanaPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaPrincipal(int x){
		
		setBounds(x,300,500,100);
				
		PanelPrincipalCliente lamina=new PanelPrincipalCliente();
		add(lamina);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}	
	
}

class PanelPrincipalCliente extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField nickName,ip;
	
	private JButton botonEnviar;
	
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
	
	private class EnviarRegistro implements ActionListener{

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


class PaqueteEnvio implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nick,ip;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	}
}
