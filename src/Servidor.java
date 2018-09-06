

import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor  {

	public Servidor() {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		
			
	}	
}

class MarcoServidor extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MarcoServidor(){
		
		setBounds(700,300,400,500);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		se usa para ejecucion en segundo plano
		Thread mi_hilo=new Thread(this);
		mi_hilo.start();
		
		}
	
	private	JTextArea areatexto;

//	thread
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
//			abre el puerto
			ServerSocket servidor=new ServerSocket(9999);
			
			String nick,ip,mensaje;
			
			PaqueteEnvio paquete_recibido;
			
			while(true) {
				
//			acepta la coneccion 
			Socket socket=servidor.accept();
			
			ObjectInputStream paquete_datos=new ObjectInputStream(socket.getInputStream());
			
			paquete_recibido=(PaqueteEnvio) paquete_datos.readObject();
			
			nick=paquete_recibido.getNick();
			ip=paquete_recibido.getIp();
			mensaje=paquete_recibido.getMensaje();
			
//			recibe los datos de entrada
//			DataInputStream flujo_entrada=new DataInputStream(socket.getInputStream());
//			lee el texto recibido
//			String mensaje_texto=flujo_entrada.readUTF();
//			escrebe el mensaje en el area de texto
//			areatexto.append("\n"+mensaje_texto);
			
			areatexto.append("\n"+nick+": "+mensaje+" >>> para "+ip);
			
//			servidor-cliente, se usa para enviar el mensaje a otro cliente
			Socket enviaDestinatario=new Socket(ip,9090);
			
//			se crea un objeto y se especifica por cual socket se envia y se obtiene el objeto
			ObjectOutputStream paqueteReenvio=new ObjectOutputStream(enviaDestinatario.getOutputStream());
			
//			escribe en paqueteReenvio lo que esta en paquete_recibido
			paqueteReenvio.writeObject(paquete_recibido);
			
			paqueteReenvio.close();
			
			enviaDestinatario.close();
//			cierra la coneccion 
			socket.close();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
