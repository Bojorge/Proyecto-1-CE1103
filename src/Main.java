import javax.swing.JFrame;

public class Main {
	
    private int n;
//  clase abstracta
    private Jugador jugadorVerde, jugadorAzul;
    
    private String jugador_1, jugador_2;

    
    private JFrame ventana;
   
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

    public void initGUI() {


        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        new Jugar(this, ventana, n, jugadorVerde, jugadorAzul, jugador_1, jugador_2);
    }

}
