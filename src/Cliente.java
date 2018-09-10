
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;
import java.net.*;

/**
 * Hace la funcion de cliente en una comunicacion de tipo cliente-servidor
 * @author Bojorge
 *
 */
public class Cliente {

	public Cliente() {
		MarcoCliente mimarco=new MarcoCliente();

	}

}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(100,300,400,500);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}	
	
}

class LaminaMarcoCliente extends JPanel implements Runnable{
	
	public LaminaMarcoCliente(){
		
		nick=new JTextField(5);
		
		add(nick);
	
		JLabel texto=new JLabel("CHAT");
		
		add(texto);
		
		ip=new JTextField(8);
		
		add(ip);
		
		campochat=new JTextArea(12,20);
		
		add(campochat);
	
		campo1=new JTextField(20);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
		
		EnviaTexto mievento=new EnviaTexto();
		
		miboton.addActionListener(mievento);
		
		add(miboton);	
		
		Thread mihilo=new Thread(this);
		
		mihilo.start();
		
	}
	
	private class EnviaTexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
//			System.out.println(campo1.getText());
			
			try {
				Socket socket=new Socket("192.168.4.123",9999);
				
//				Objeto que contiene los datos
				PaqueteEnvio datos=new PaqueteEnvio();
				
				datos.setNick(nick.getText());
				datos.setIp(ip.getText());
				datos.setMensaje(campo1.getText());
				
//				flujo para envir el objeto al destinatario
				ObjectOutputStream paquete_datos=new ObjectOutputStream(socket.getOutputStream());
				paquete_datos.writeObject(datos);
				
				socket.close();
				
				
//				DataOutputStream flujo_salida=new DataOutputStream(socket.getOutputStream());
//				flujo_salida.writeUTF(campo1.getText());
//				flujo_salida.close();
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				
				System.out.println(e.getMessage());
			}
			
		}
		
	}
	
		
	private JTextField campo1,nick,ip;
	
	private JTextArea campochat;
	
	private JButton miboton;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
//			esta a la escucha en el puerto 9090 (se usa para recibir los mensajes de otro cliente
			ServerSocket servidor_cliente=new ServerSocket(9090);
			
			Socket cliente;
			
			PaqueteEnvio paqueteRecibido;
			
			while(true) {
				
				cliente=servidor_cliente.accept();
				
				ObjectInputStream flujoentrada=new ObjectInputStream(cliente.getInputStream());
				
				paqueteRecibido=(PaqueteEnvio) flujoentrada.readObject();
				
				campochat.append("\n"+paqueteRecibido.getNick()+": "+paqueteRecibido.getMensaje());
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}


class PaqueteEnvio implements Serializable{
	
	private String nick,ip,mensaje;

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

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}

