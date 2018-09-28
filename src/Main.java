import javax.swing.JFrame;
/**
 * 
 * @author Bojorge
 *
 */
public class Main {
	
    private int n;
//  clase abstracta
    private Jugador jugadorVerde, jugadorAzul;
    
    private String jugador_1, jugador_2;

    
    private JFrame ventana;
   /**
    * Metodo constructor, crea una instancia de JFrame y establece el tamaño de la matriz (n) 
    * @param jugador_1 - nombre del primer jugador que se extrae de la cola
    * @param jugador_2 - nombre del segundo jugador que se extrae de la cola
    */
	public Main(String jugador_1,String jugador_2) {    	
    	this.jugador_1=jugador_1;
    	this.jugador_2=jugador_2;
    	
//    	Este bucle le da el tamaño al tablero
    	for(int i=0;i<4;i++) {
    		n=i+3;
    	}
        ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initGUI();
    }

	/**
	 * Este metodo usa objetos instanciados en esta clase para dar los parametros a la clase que tiene la logica principal del juego
	 */
    public void initGUI() {


        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        new Jugar(this, ventana, n, jugadorVerde, jugadorAzul, jugador_1, jugador_2);
    }

}
