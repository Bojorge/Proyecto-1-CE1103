import java.awt.Point;
import java.util.ArrayList;

public class Tablero implements Cloneable {

    final static int VERDE = 0;
    final static int AZUL = 1;
    final static int NEGRO = 2;
    final static int BLANCO = 3;

    private int[][] hBorde;
    private int[][] vBorde;
    private int[][] cuadro;
    private int n, puntuacionVerde, puntuacionAzul;

    public Tablero(int n) {
        hBorde = new int[n-1][n];
        vBorde = new int[n][n-1];
        cuadro = new int[n-1][n-1];
        llenar(hBorde,BLANCO);
        llenar(vBorde,BLANCO);
        llenar(cuadro,BLANCO);
        this.n = n;
        puntuacionVerde = puntuacionAzul = 0;
    }
 
    public Tablero clone() {
        Tablero clonado = new Tablero(n);

        for(int i=0; i<(n-1); i++)
            for(int j=0; j<n; j++)
                clonado.hBorde[i][j] = hBorde[i][j];

        for(int i=0; i<n; i++)
            for(int j=0; j<(n-1); j++)
                clonado.vBorde[i][j] = vBorde[i][j];

        for(int i=0; i<(n-1); i++)
            for(int j=0; j<(n-1); j++)
                clonado.cuadro[i][j] = cuadro[i][j];

        clonado.puntuacionVerde = puntuacionVerde;
        clonado.puntuacionAzul = puntuacionAzul;

        return clonado;
    }

    private void llenar(int[][] array, int val) {
        for(int i=0; i<array.length; i++)
            for(int j=0; j<array[i].length; j++)
                array[i][j]=val;
    }

    public int getTamano() { return n; }

    public int getPuntuacionVerde() {
        return puntuacionVerde;
    }

    public int getPuntuacionAzul() {
        return puntuacionAzul;
    }

    public int getScore(int color) {
        if(color == VERDE) return puntuacionVerde;
        else return puntuacionAzul;
    }

    public static int alternarColor(int color) {
        if(color == VERDE)
            return AZUL;
        else
            return VERDE;
    }

    public ArrayList<Borde> getMovimientosDisponibles() {
        ArrayList<Borde> remover = new ArrayList<Borde>();
        for(int i=0; i<(n-1);i++)
            for(int j=0; j<n; j++)
                if(hBorde[i][j] == BLANCO)
                    remover.add(new Borde(i,j,true));
        for(int i=0; i<n; i++)
            for(int j=0; j<(n-1); j++)
                if(vBorde[i][j] == BLANCO)
                    remover.add(new Borde(i,j,false));
        return remover;
    }

//  metodo para determinar cuando se ha cerrado un cuadro y para quien es el punto
    public ArrayList<Point> setHBorde(int x, int y, int color) {
        hBorde[x][y]=NEGRO;
        ArrayList<Point> remover = new ArrayList<Point>();
        if(y<(n-1) && vBorde[x][y]==NEGRO && vBorde[x+1][y]==NEGRO && hBorde[x][y+1]==NEGRO) {
            cuadro[x][y]=color;
            remover.add(new Point(x,y));
            if(color == VERDE) puntuacionVerde++;
            else puntuacionAzul++;
        }
        if(y>0 && vBorde[x][y-1]==NEGRO && vBorde[x+1][y-1]==NEGRO && hBorde[x][y-1]==NEGRO) {
            cuadro[x][y-1]=color;
            remover.add(new Point(x,y-1));
            if(color == VERDE) puntuacionVerde++;
            else puntuacionAzul++;
        }
        return remover;
    }

    public ArrayList<Point> setVBorde(int x, int y, int color) {
        vBorde[x][y]=NEGRO;
        ArrayList<Point> remover = new ArrayList<Point>();
        if(x<(n-1) && hBorde[x][y]==NEGRO && hBorde[x][y+1]==NEGRO && vBorde[x+1][y]==NEGRO) {
            cuadro[x][y]=color;
            remover.add(new Point(x,y));
            if(color == VERDE) puntuacionVerde++;
            else puntuacionAzul++;
        }
        if(x>0 && hBorde[x-1][y]==NEGRO && hBorde[x-1][y+1]==NEGRO && vBorde[x-1][y]==NEGRO) {
            cuadro[x-1][y]=color;
            remover.add(new Point(x-1,y));
            if(color == VERDE) puntuacionVerde++;
            else puntuacionAzul++;
        }
        return remover;
    }

//  determina si todos los cuadros han sido completados
    public boolean isCompleto() {
        return (puntuacionVerde + puntuacionAzul) == (n - 1) * (n - 1);
    }

    public int getGanador() {
        if(puntuacionVerde > puntuacionAzul) return VERDE;
        else if(puntuacionVerde < puntuacionAzul) return AZUL;
        else return BLANCO;
    }

    public Tablero getNuevoTablero(Borde borde, int color) {
        Tablero remover = clone();
        if(borde.isHorizontal())
            remover.setHBorde(borde.getX(), borde.getY(), color);
        else
            remover.setVBorde(borde.getX(), borde.getY(), color);
        return remover;
    }

    private int getConteoBordes(int i, int j) {
        int count = 0;
        if(hBorde[i][j] == NEGRO) count++;
        if(hBorde[i][j+1] == NEGRO) count++;
        if(vBorde[i][j] == NEGRO) count++;
        if(vBorde[i+1][j] == NEGRO) count++;
        return count;
    }

    public int getConteoCuadros(int nLados) {
        int count = 0;
        for(int i=0; i<(n-1); i++)
            for(int j=0; j<(n-1); j++) {
                if(getConteoBordes(i, j) == nLados)
                    count++;
            }
        return count;
    }

}
