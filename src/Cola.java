
/**
 * Cola simple
 * @author Bojorge
 *
 */
public class Cola {    

    class Nodo {
        Object info;
        Nodo sig;
    }    

    private Nodo raiz,fondo;    

    public Cola() {
        raiz=null;
        fondo=null;
    }    

    boolean vacia (){
        if (raiz == null)
            return true;
        else
            return false;
    }

    void insertar (Object info) {  
        Nodo nuevo;
        nuevo = new Nodo ();
        nuevo.info = info;
        nuevo.sig = null;

        if (vacia ()) {
            raiz = nuevo;
            fondo = nuevo;
        } else {
            fondo.sig = nuevo;
            fondo = nuevo;
        }
    }

    Object extraer () {    
        if (!vacia ()) {     
        	Object informacion = raiz.info;
            if (raiz == fondo){
                raiz = null;
                fondo = null;
            } else {
                raiz = raiz.sig;
            }
            return informacion;
        } else
            return Integer.MAX_VALUE;
    }
}
