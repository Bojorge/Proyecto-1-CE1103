import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private int n;
//  clase abstracta
    private Jugador jugadorVerde, jugadorAzul;
    
    private String jugador_1, jugador_2;

    
    private JFrame ventana;
    private JLabel modoError, tamanoError;

//  lista de jugadores disponibles
    String[] jugadores = {"Annunaki","Terricola","Marciano"};
    private JRadioButton[] tamanoButon;
    JComboBox<String> verdeLista, azulLista;
    ButtonGroup tamanoGrupo;

   
    public Main() {

        ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//      Array de objetos como vector de un array tipo String llamado "jugadores"
        verdeLista = new JComboBox<String>(jugadores);
        azulLista = new JComboBox<String>(jugadores);

        tamanoButon = new JRadioButton[8];
        tamanoGrupo = new ButtonGroup();
        for(int i=0; i<8; i++) {
            String size = String.valueOf(i+3);
            tamanoButon[i] = new JRadioButton(size + " x " + size);
            tamanoGrupo.add(tamanoButon[i]);
        }
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

//
//      Panel con una ubicacion de objetos siguiendo un patron de rejilla
        JPanel modePanel = new JPanel(new GridLayout(2, 2));
        modePanel.setPreferredSize(new Dimension(400, 50));
// 
//      cambia el color de fuente del nombre deljugador "Player-1" y "Player-2" en la ventana de inicio
        modePanel.add(new JLabel("<html><font color='green'>Verde:", SwingConstants.CENTER));
        modePanel.add(new JLabel("<html><font color='blue'>Azul:", SwingConstants.CENTER));
        
        modePanel.add(verdeLista);
        modePanel.add(azulLista);
        
//      indice de la lista de jugadores seleccionado por default
        verdeLista.setSelectedIndex(0);
        azulLista.setSelectedIndex(0);
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
        new Main().initGUI();
    }

}