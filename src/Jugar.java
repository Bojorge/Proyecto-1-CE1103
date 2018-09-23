


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Jugar {
//	estas constantes estaticas asignan el tamaño de los puntos y el lado del cuadrado que se forma entre cuatro puntos
    private final static int tamAnchLineas = 7;
    private final static int ladoCuad = 50;

    private int n;
    private Tablero tablero;
    private int turno;
    private boolean mouseHabilitado;

    Jugador jugadorVerde, jugadorAzul, jugador;
    String verdeName, azulName;
    Main padre;

//   matrices de etiquetas y valores booleanos
    private JLabel[][] hBorde, vBorde, cuadro;
    
//	con esto se trata de hacer listas de listas para implementar una matriz
//  private Lista<Lista> HFilBorde;
//  private Lista<JLabel> HColBorde;
//  
//  private Lista<JLabel> VFilBorde;
//  private Lista<JLabel> VColBorde;
//  
//  private Lista<JLabel> Filcuadro;
//  private Lista<JLabel> Colcuadro;
    
    private boolean[][] isSetHBorde, isSetVBorde;

    private JFrame ventana;
    private JLabel etiquetaPuntosVerde, etiquetaPuntosAzul, etiquetaEstado;

//  Evento para cuando se hace click
    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(!mouseHabilitado) return;
            processMove(getSource(mouseEvent.getSource()));
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

//       Evento para cuando el mouse pasa sobre los lados de los cuadrados
        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            if(!mouseHabilitado) return;
            Borde location = getSource(mouseEvent.getSource());
            int x=location.getX(), y=location.getY();
            if(location.isHorizontal()) {
                if(isSetHBorde[x][y]) return;
                hBorde[x][y].setBackground((turno == Tablero.VERDE) ? Color.GREEN : Color.BLUE);
            }
            else {
                if(isSetVBorde[x][y]) return;
                vBorde[x][y].setBackground((turno == Tablero.VERDE) ? Color.GREEN : Color.BLUE);
            }
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            if(!mouseHabilitado) return;
            Borde location = getSource(mouseEvent.getSource());
            int x=location.getX(), y=location.getY();
            if(location.isHorizontal()) {
                if(isSetHBorde[x][y]) return;
//              cambia el color de la barra cuando el puntero la toca
                hBorde[x][y].setBackground(Color.WHITE);
            }
            else {
                if(isSetVBorde[x][y]) return;
//              cambia el color de la barra cuando el puntero la toca
                vBorde[x][y].setBackground(Color.WHITE);
            }
        }
    };
//	encuentra la posicion del cursor usando sus coordenadas
    private void processMove(Borde ubicacion) {
        int x=ubicacion.getX(), y=ubicacion.getY();
        ArrayList<Point> removerArea;
//      establece el color de las barras horizontales cuando se hace click sobre esta
        if(ubicacion.isHorizontal()) {
            if(isSetHBorde[x][y]) return;
            removerArea = tablero.setHBorde(x,y,turno);
            hBorde[x][y].setBackground(Color.BLACK);
            isSetHBorde[x][y] = true;
        }
//      establece el color de las barras verticales cuando se hace click sobre esta
        else {
            if(isSetVBorde[x][y]) return;
            removerArea = tablero.setVBorde(x,y,turno);
            vBorde[x][y].setBackground(Color.BLACK);
            isSetVBorde[x][y] = true;
        }
//        
//        
//		colorea el area encerrada de uno u otro color segun el jugador que hizo el ultimo movimiento
        for(Point p : removerArea)
            cuadro[p.x][p.y].setBackground((turno == Tablero.VERDE) ? Color.GREEN : Color.BLUE);

        etiquetaPuntosVerde.setText(String.valueOf(tablero.getPuntuacionVerde()));
        etiquetaPuntosAzul.setText(String.valueOf(tablero.getPuntuacionAzul()));
//
//
//       
//      si el tablero es completado ...
        if(tablero.isCompleto()) {
            int ganador = tablero.getGanador();
            if(ganador == Tablero.VERDE) {
                etiquetaEstado.setText("¡Verde es el ganador!");
                etiquetaEstado.setForeground(Color.GREEN);
            }
            else if(ganador == Tablero.AZUL) {
                etiquetaEstado.setText("¡Azul es el ganador!");
                etiquetaEstado.setForeground(Color.BLUE);
            }
            else {
                etiquetaEstado.setText("¡Empate!");
                etiquetaEstado.setForeground(Color.BLACK);
            }
        }

        if(removerArea.isEmpty()) {
            if(turno == Tablero.VERDE) {
                turno = Tablero.AZUL;
                jugador = jugadorAzul;
                etiquetaEstado.setText("Turno del azul");
                etiquetaEstado.setForeground(Color.BLUE);
            }
            else {
                turno = Tablero.VERDE;
                jugador = jugadorVerde;
                etiquetaEstado.setText("Turno del verde");
                etiquetaEstado.setForeground(Color.GREEN);
            }
        }

    }

    private void gestorJuego() {
//    	mientras el tablero no este completo
        while(!tablero.isCompleto()) {
            if(regresar) return;
            if(jugador == null) {
                mouseHabilitado = true;
            }
            else {
                mouseHabilitado = false;
                processMove(jugador.getSiguienteMovimiento(tablero, turno));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Borde getSource(Object object) {
        for(int i=0; i<(n-1); i++)
            for(int j=0; j<n; j++)
                if(hBorde[i][j] == object)
                    return new Borde(i,j,true);
        for(int i=0; i<n; i++)
            for(int j=0; j<(n-1); j++)
                if(vBorde[i][j] == object)
                    return new Borde(i,j,false);
        return new Borde();
    }

//  crea y le da color al borde o cada linea horizontal que conectan los puntos
    private JLabel getBordeHorizontal() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(ladoCuad, tamAnchLineas));
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label.setOpaque(true);
        label.addMouseListener(mouseListener);
        return label;
    }

//  crea y le da color al borde o cada linea vertical que conectan los puntos
    private JLabel getBordeVertical() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(tamAnchLineas, ladoCuad));
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label.setOpaque(true);
        label.addMouseListener(mouseListener);
        return label;
    }

//  crea cada punto
    private JLabel getPunto() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(tamAnchLineas, tamAnchLineas));
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        return label;
    }

    private JLabel getCuadro() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(ladoCuad, ladoCuad));
        label.setOpaque(true);
        return label;
    }
//	crea una etiqueta
    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
    }

//    metodo constructor, abre una ventana, con "n"se establece la matriz de puntos y nombre a los jugadores
    public Jugar(Main parent, JFrame ventana, int n, Jugador jugadorVerde, Jugador jugadorAzul, String verdeName, String azulName) {
       	this.padre = parent;
        this.ventana = ventana;
        this.n = n;
        this.jugadorVerde = jugadorVerde;
        this.jugadorAzul = jugadorAzul;
        this.verdeName = verdeName;
        this.azulName = azulName;
        iniciarJuego();
    }

    private boolean regresar;

    private ActionListener regresoListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            regresar = true;
        }
    };

    private void iniciarJuego() {

        tablero = new Tablero(n);
        int AnchoTablero = n * tamAnchLineas + (n-1) * ladoCuad;
        turno = Tablero.VERDE;
        jugador = jugadorVerde;

        JPanel cuadricula = new JPanel(new GridBagLayout());
        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.gridy = 0;
        cuadricula.add(getEmptyLabel(new Dimension(2 * AnchoTablero, 10)), restricciones);
        
//      crea un panel con el nombre de los jugadores
        JPanel playerPanel = new JPanel(new GridLayout(2, 2));
        if(n>3) playerPanel.setPreferredSize(new Dimension(2 * AnchoTablero, ladoCuad));
        else playerPanel.setPreferredSize(new Dimension(2 * AnchoTablero, 2 * ladoCuad));
        playerPanel.add(new JLabel("<html><font color='green'>Verde:", SwingConstants.CENTER));
        playerPanel.add(new JLabel("<html><font color='blue'>Azul:", SwingConstants.CENTER));
        playerPanel.add(new JLabel("<html><font color='green'>" + "jugador 1", SwingConstants.CENTER));
        playerPanel.add(new JLabel("<html><font color='blue'>" + "jugador 2", SwingConstants.CENTER));
        ++restricciones.gridy;
        cuadricula.add(playerPanel, restricciones);

        ++restricciones.gridy;
        cuadricula.add(getEmptyLabel(new Dimension(2 * AnchoTablero, 10)), restricciones);

        JPanel panelPuntuacion = new JPanel(new GridLayout(2, 2));
        panelPuntuacion.setPreferredSize(new Dimension(2 * AnchoTablero, ladoCuad));
        panelPuntuacion.add(new JLabel("<html><font color='green'>Puntuacion:", SwingConstants.CENTER));
        panelPuntuacion.add(new JLabel("<html><font color='blue'>Puntuacion:", SwingConstants.CENTER));
        etiquetaPuntosVerde = new JLabel("0", SwingConstants.CENTER);
        etiquetaPuntosVerde.setForeground(Color.GREEN.darker());
        panelPuntuacion.add(etiquetaPuntosVerde);
        etiquetaPuntosAzul = new JLabel("0", SwingConstants.CENTER);
        etiquetaPuntosAzul.setForeground(Color.BLUE);
        panelPuntuacion.add(etiquetaPuntosAzul);
        ++restricciones.gridy;
        cuadricula.add(panelPuntuacion, restricciones);

        ++restricciones.gridy;
        cuadricula.add(getEmptyLabel(new Dimension(2 * AnchoTablero, 10)), restricciones);

//        se instancian las matrices
        hBorde = new JLabel[n-1][n];
//        for(int k=1;k<=n-1;k++){
//        	HFilBorde.insertarInicio(HColBorde);
//            for (int i = 0; i<n; i++) {
//            	HColBorde.insertarInicio(getBordeHorizontal());
//            }
//        }
        isSetHBorde = new boolean[n-1][n];

        vBorde = new JLabel[n][n-1];
//        for(int k=1;k<=n;k++){
//        	VFilBorde.insertarInicio(getBordeVertical());
//            for (int i = 0; i<n-1; i++) {
//            	VColBorde.insertarInicio(getBordeVertical());
//            }
//        }
        isSetVBorde = new boolean[n][n-1];
        
        cuadro = new JLabel[n-1][n-1];
//        for(int s=1;s<=n-1;s++){
//        	Filcuadro.insertarInicio(getCuadro());
//            for (int v = 0; v<n-1; v++) {
//            	Colcuadro.insertarInicio(getCuadro());
//        
//            }
//        }

//        se recorren las matrices para ubicar los puntos y los bordes
        
//        La variable "i" en cada bucle se usa como indice para ubicar las lineas y los puntos
        for(int i=0; i<(2*n-1); i++) { 
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
            if(i%2==0) {
                panel.add(getPunto());
                for(int j=0; j<(n-1); j++) {
                    hBorde[j][i/2] = getBordeHorizontal();
                    panel.add(hBorde[j][i/2]);
                    panel.add(getPunto());
                }
            }
            else {
                for(int j=0; j<(n-1); j++) {
                    vBorde[j][i/2] = getBordeVertical();
                    panel.add(vBorde[j][i/2]);
                    cuadro[j][i/2] = getCuadro();
                    panel.add(cuadro[j][i/2]);
                }
                vBorde[n-1][i/2] = getBordeVertical();
                panel.add(vBorde[n-1][i/2]);
            }
            ++restricciones.gridy;
            cuadricula.add(panel, restricciones);
        }

        ++restricciones.gridy;
        cuadricula.add(getEmptyLabel(new Dimension(2 * AnchoTablero, 10)), restricciones);

        etiquetaEstado = new JLabel("Turno del VERDE", SwingConstants.CENTER);
        etiquetaEstado.setForeground(Color.GREEN);
        etiquetaEstado.setPreferredSize(new Dimension(2 * AnchoTablero, ladoCuad));
        ++restricciones.gridy;
        cuadricula.add(etiquetaEstado, restricciones);

        ++restricciones.gridy;
        cuadricula.add(getEmptyLabel(new Dimension(2 * AnchoTablero, 10)), restricciones);

//      crea un boton con un evento para regresar al menu principal
        JButton goBackButton = new JButton("Salir del juego");
        goBackButton.setPreferredSize(new Dimension(AnchoTablero, ladoCuad));
        goBackButton.addActionListener(regresoListener);
        ++restricciones.gridy;
        cuadricula.add(goBackButton, restricciones);

        ventana.getContentPane().removeAll();
        ventana.revalidate();
        ventana.repaint();

        ventana.setContentPane(cuadricula);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        regresar = false;
        gestorJuego();

        while(!regresar) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        padre.initGUI();
    }

}
