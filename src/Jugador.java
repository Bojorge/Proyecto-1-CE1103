public abstract class Jugador {

    protected int colorReferencia;
    private final static int cPuntuacion = 20;
    private final static int cTres = 15;
    private final static int cDos = 1;

    protected int mecanicaJuego(final Tablero tablero, int color) {
        int valor;
        if(colorReferencia == Tablero.VERDE)
            valor = cPuntuacion * tablero.getPuntuacionVerde() - cPuntuacion * tablero.getPuntuacionAzul();
        else
            valor = cPuntuacion * tablero.getPuntuacionAzul() - cPuntuacion * tablero.getPuntuacionVerde();
        if(colorReferencia == color)
            valor += cTres * tablero.getConteoCuadros(3) - cDos * tablero.getConteoCuadros(2);
        else
            valor -= cTres * tablero.getConteoCuadros(3) - cDos * tablero.getConteoCuadros(2);
        return valor;
    }

    public abstract Borde getSiguienteMovimiento(final Tablero tablero, int color );
}

