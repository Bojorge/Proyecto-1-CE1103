import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	
	public void run() {
		// TODO Auto-generated method stub
		
		try {
//			abre el puerto
			ServerSocket servidor=new ServerSocket(9999);
			String ip;
			
			Actualizacion paquete_recibido;
			
			while(true) {
				
//			acepta la coneccion 
			Socket socket=servidor.accept();
			
			ObjectInputStream paquete_datos=new ObjectInputStream(socket.getInputStream());
			
			paquete_recibido=(Actualizacion) paquete_datos.readObject();
			
			ip=paquete_recibido.getIp();
			
			
			
//			servidor-cliente, se usa para enviar el mensaje a otro cliente
			Socket enviaDestinatario=new Socket(ip,9090);
			
//			se crea un objeto y se especifica por cual socket se envia y se obtiene el objeto
			ObjectOutputStream paqueteReenvio=new ObjectOutputStream(enviaDestinatario.getOutputStream());
			
//			escribe en paqueteReenvio lo que esta en paquete_recibido
			paqueteReenvio.writeObject(paquete_recibido);
						
			enviaDestinatario.close();
			
			paqueteReenvio.close();
//			cierra la coneccion 
			socket.close();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
