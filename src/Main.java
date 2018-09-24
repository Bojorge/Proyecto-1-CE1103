import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	
	Lista<String> lista=new Lista<String>();
	
	JTextField userNameField;
	
	JLabel jugadorV;
	JLabel jugadorA;
	
	JPanel modePanel;
	
    private int n;
//  clase abstracta
    private Jugador jugadorVerde, jugadorAzul;
    
    private String jugador_1, jugador_2;

    
    private JFrame ventana;

//  lista de jugadores disponibles
    Lista<String> jugador1=new Lista<String>();
    Lista<String> jugador2=new Lista<String>();
    
    
    Lista<String> verdeLista, azulLista;
    
    int x;
   
	public Main() {    	
    	
        ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	public Main(int x) {
		this.x=x;
		ventana = new JFrame();
		ventana.setBounds(x,150,200,50);
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
        	
        	jugador_1=userNameField.getText();
        	jugador_2=userNameField.getText();
        	
        	iniciarJuego = true;
        	
//        	Este bucle le da el tamaño al tablero
        	for(int i=0;i<4;i++) {
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

        modePanel = new JPanel();
        modePanel.setPreferredSize(new Dimension(450, 70));
        

    	
    	userNameField=new JTextField(20);
    	modePanel.add(userNameField);
    	
    	String nombreJugador=userNameField.getText();
    	
//      Se llenan las listas con los jugadores
    	jugador1.insertarInicio("Annunaki");
        jugador1.insertarInicio(nombreJugador);
        
  	    jugador2.insertarInicio(nombreJugador);
  	    jugador2.insertarInicio("Terricola");
  	    
    	
//        jugadorV=new JLabel("Verde"+" >>> "+userNameField.getText()+"   < | >  ");
//        jugadorA=new JLabel("Azul"+" >>> "+userNameField.getText()+"   ");
//        
//        modePanel.add(jugadorV);
//        modePanel.add(jugadorA);
        
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
        ventana.setBounds(x,150,500,200);
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
//    	Servidor servidor=new Servidor();
		
		Thread clienteT1=new Thread(new Runnable() {
			@Override
			public void run() {
				Cliente cliente1=new Cliente(200);
			}
		});
		
		Thread clienteT2=new Thread(new Runnable() {
			@Override
			public void run() {
				Cliente cliente2=new Cliente(100);
			}
		});
		
		clienteT1.start();
		clienteT2.start();
    }

}