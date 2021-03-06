package Main_sockets;


import javax.swing.*;

import estructuras.Cola;
import logica_del_juego.Main;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hace la funcion de servidor en una comunicacion de tipo cliente-servidor
 * Tiene una interfaz grafica (ventana) que se usa para pruebas
 * @author Bojorge
 *
 */
public class Servidor  {
	
	private String jugador1,jugador2;
	Main main;
	
	/**
	 * Metodo constructor crea una instancia de una clase que abre una ventana
	 */
	public Servidor() {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unused")
		VentanaServidor marco=new VentanaServidor();
		
		
			
	}
	
	/**
	 * Devuelve una cadena de texto con el nobre del jugador 1
	 * @return
	 */
	public String getJugador1() {
		return jugador1;
	}


	public void setJugador1(String jugador1) {
		this.jugador1 = jugador1;
	}

	/**
	 * Devuelve una cadena de texto con el nobre del jugador 2
	 * @return
	 */
	public String getJugador2() {
		return jugador2;
	}


	public void setJugador2(String jugador2) {
		this.jugador2 = jugador2;
	}

/**
 * Clase interna, contiene la intefaz grafica y crea un Thread para mantener al servidor 
 * a la escucha de cualquier conexion por el puerto especificado y acepte el mensaje enviado
 * @author Bojorge
 *
 */
	class VentanaServidor extends JFrame implements Runnable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public VentanaServidor(){
			
			setBounds(600,100,100,100);				
				
			JPanel lamina= new JPanel();
			
			lamina.setLayout(new BorderLayout());
			
			areatexto=new JTextArea();
			
			lamina.add(areatexto,BorderLayout.CENTER);
			
			add(lamina);
			
			setVisible(true);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
//			se usa para ejecucion en segundo plano
			Thread hilo=new Thread(this);
			hilo.start();
			
			}
//		en esta area de texto se muestran los nombres de los jugadores
		private	JTextArea areatexto;

//		thread; se mantiene a la escucha de nuevas conecciones (se ejecuta en segundo plano)
		@SuppressWarnings({ "resource", "unused" })
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Cola colaJugadores=new Cola();
			try {
//				abre el puerto
				ServerSocket servidor=new ServerSocket(9999);
				int jugadores= 0;
				String nick,ip;
				
				PanelPrincipalCliente.PaqueteEnvio paquete_recibido;
				
				while(true) { 
					
//				acepta la coneccion 
				Socket socket=servidor.accept();
				
				ObjectInputStream paquete_datos=new ObjectInputStream(socket.getInputStream());
				
				paquete_recibido=(PanelPrincipalCliente.PaqueteEnvio) paquete_datos.readObject();
				
				nick=paquete_recibido.getNick();
				ip=paquete_recibido.getIp();
				
				colaJugadores.insertar(nick);
				jugadores++;
				
				if(jugadores==1) {
					setJugador1((String) colaJugadores.extraer());
				}else if(jugadores==2){
					setJugador2((String) colaJugadores.extraer());
					
					areatexto.append("\n"+getJugador1()+"\n"+getJugador2());
					main=new Main(getJugador1(),getJugador2());
					jugadores=0;
				}
				
				
//				cierra la coneccion 
				socket.close();
				}
				
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


