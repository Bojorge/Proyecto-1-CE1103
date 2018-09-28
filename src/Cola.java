
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
/**
 * Metodo constructor, inicia los valores iniciales del primer y ultimo elementos en la cola 
 */
    public Cola() {
        raiz=null;
        fondo=null;
    }    
/**
 * Devuelve si la cola está vacia 
 * @return
 */
    boolean vacia (){
        if (raiz == null)
            return true;
        else
            return false;
    }
/**
 * Este metodo inserta un elemento a la cola
 * @param info
 */
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
/**
 * Este metodo extrae el primer elemento de la cola
 * @return
 */
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
