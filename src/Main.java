import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	
	Lista<String> lista=new Lista<String>();
	
	
    private int n;
//  clase abstracta
    private Jugador jugadorVerde, jugadorAzul;
    
    private String jugador_1, jugador_2;

    
    private JFrame ventana;

//  lista de jugadores disponibles
    Lista<String> jugador1=new Lista<String>();
    Lista<String> jugador2=new Lista<String>();
    
    
    Lista<String> verdeLista, azulLista;
   
	public Main() {    	
    	
        ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }

//  devuelve la etiqueta
    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
    }

    private boolean iniciarJuego;


//  evento para cuando es pulsado el boton "Empezar juego"
    private ActionListener enviarOyente = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        	
        	iniciarJuego = true;
        	
//        	Este bucle le da el tamaño al tablero
        	for(int i=0;i<7;i++) {
        		n=i+3;
        	}
        }
    };

    public void initGUI() {

        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;


        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500,25)), constraints);

        JPanel modePanel = new JPanel();
        modePanel.setPreferredSize(new Dimension(450, 70));
        
//        Se llenan las listas con los jugadores
        jugador1.insertarInicio("Marciano");
    	jugador1.insertarInicio("Annunaki");
    	jugador2.insertarInicio("Bojorge");
    	jugador2.insertarInicio("Terricola");
        
        JLabel jugadorV=new JLabel("Verde"+" >>> "+jugador1.primero()+"   < | >  ");
        JLabel jugadorA=new JLabel("Azul"+" >>> "+jugador2.primero()+"   ");
        
        modePanel.add(jugadorV);
        modePanel.add(jugadorA);
        
        ++constraints.gridy;
        grid.add(modePanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500,25)), constraints);

//      Panel con una ubicacion de objetos siguiendo un patron de rejilla
        JPanel sizePanel = new JPanel(new GridLayout(4, 2));
        sizePanel.setPreferredSize(new Dimension(400, 100));
        
//      boton que se preciona para empezar el juego
        JButton jugarButton = new JButton("Jugar");
        jugarButton.addActionListener(enviarOyente);
        ++constraints.gridy;
        grid.add(jugarButton, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);

        ventana.setContentPane(grid);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        iniciarJuego = false;
        while(!iniciarJuego) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new Jugar(this, ventana, n, jugadorVerde, jugadorAzul, jugador_1, jugador_2);
    }

    public static void main(String[] args) {
    	Servidor servidor=new Servidor();
		
		Thread clienteT1=new Thread(new Runnable() {
			@Override
			public void run() {
				Cliente cliente1=new Cliente();
			}
		});
		
		Thread clienteT2=new Thread(new Runnable() {
			@Override
			public void run() {
				Cliente cliente2=new Cliente(); 
			}
		});
		
		clienteT1.start();
		clienteT2.start();
    }

}