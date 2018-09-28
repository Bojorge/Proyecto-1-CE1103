package logica_del_juego;
/**
 * Esta clase le da la posicion a los bordes o lineas que conectan los puntos
 * @author Bojorge
 *
 */
public class Borde {

    private int x, y;
    private boolean horizontal;
    
    /**
     * Metodo constructor, establece una posicion vertical por default 
     */
    Borde() {
        x = y = -1;
        horizontal = false;
    }
    
    /**
     * Metodo constructor recibe los parametros quedan la posicion y orientacion de los bordes
     * @param x - pocicion horizontal
     * @param y - posicion vertical
     * @param horizontal - es un valor booleano que indica si la orientacion será horizontal o vertical
     */
    Borde(int x, int y, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
    }
    /**
     * devuelve un booleano, true si el borde es horizontal y false si es vertical
     * @return - la orientacion
     */
    public boolean isHorizontal() {
        return horizontal;
    }
/**
 * devuelve la posicion por el eje x
 * @return
 */
    public int getX() {
        return x;
    }
/**
 * devuelve la posicion en el eje y
 * @return
 */
    public int getY() {
        return y;
    }

}
