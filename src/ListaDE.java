
/**
 * Esta clase es una lista simple con sus metodos básicos
 * @author Bojorge
 *
 */
public class ListaDE {
 
//	clase interna
    class Nodo {
        int info;
        Nodo ant,sig;
    }
    
    private Nodo raiz;
    
    public ListaDE () {
        raiz=null;
    }
      
    
    /**
     * inserta en una posicion indicada el objeto
     * si el indice es menor a la cantidad +1 lo inserta y desplaza todo a la siguiente posicion
     * si el indice es mayor no lo inserta 
     * @param pos - posicion o indice
     * @param x - dato del nodo
     */
    
    void insertar (int pos, int x)
    {
        if (pos <= cantidad () + 1)    {
            Nodo nuevo = new Nodo ();
            nuevo.info = x;
            if (pos == 1){
                nuevo.sig = raiz;
                if (raiz!=null)
                    raiz.ant=nuevo;
                raiz = nuevo;
            } else
                if (pos == cantidad () + 1)    {
                    Nodo reco = raiz;
                    while (reco.sig != null) {
                        reco = reco.sig;
                    }
                    reco.sig = nuevo;
                    nuevo.ant=reco;
                    nuevo.sig = null;
                } else {
                    Nodo reco = raiz;
                    for (int f = 1 ; f <= pos - 2 ; f++)
                        reco = reco.sig;
                    Nodo siguiente = reco.sig;
                    reco.sig = nuevo;
                    nuevo.ant=reco;
                    nuevo.sig = siguiente;
                    siguiente.ant=nuevo;
                }
        }
    }

    /**
     * extrae el dato, luego elimina el nodo
     * @param pos - posicion o indice del dato a extraer
     * @return si la posicion indicada es mayor a la cantidad devuelve Integer.MAX_VALUE
     */
    public int extraer (int pos) {
        if (pos <= cantidad ())    {
            int informacion;
            if (pos == 1) {
                informacion = raiz.info;
                raiz = raiz.sig;
                if (raiz!=null)
                    raiz.ant=null;
            } else {
                Nodo reco;
                reco = raiz;
                for (int f = 1 ; f <= pos - 2 ; f++)
                    reco = reco.sig;
                Nodo prox = reco.sig;
                reco.sig = prox.sig;
                Nodo siguiente=prox.sig;
                if (siguiente!=null)
                    siguiente.ant=reco;
                informacion = prox.info;
            }
            return informacion;
        }
        else
            return Integer.MAX_VALUE; // Una constante que contiene el valor máximo que un entero puede tener
    }

    /**
     * borra una posicion indicada
     * @param pos - posicion o indice
     */
    public void borrar (int pos)
    {
        if (pos <= cantidad ())    {
            if (pos == 1) {
                raiz = raiz.sig;
                if (raiz!=null)
                    raiz.ant=null;
            } else {
                Nodo reco;
                reco = raiz;
                for (int f = 1 ; f <= pos - 2 ; f++)
                    reco = reco.sig;
                Nodo prox = reco.sig;
                prox=prox.sig;
                reco.sig = prox;
                if (prox!=null)
                    prox.ant=reco;
            }
        }
    }
    
    /**
     * intercambia los datos de dos posiciones es pecificadas
     * @param pos1 - posicion 1
     * @param pos2 - posicion 2
     */
    public void intercambiar (int pos1, int pos2) {
        if (pos1 <= cantidad () && pos2 <= cantidad ())    {
            Nodo reco1 = raiz;
            for (int f = 1 ; f < pos1 ; f++)
                reco1 = reco1.sig;
            Nodo reco2 = raiz;
            for (int f = 1 ; f < pos2 ; f++)
                reco2 = reco2.sig;
            int aux = reco1.info;
            reco1.info = reco2.info;
            reco2.info = aux;
        }
    }
    
   
    
   
    /**
     * Devuelve la cantidad de elementos en la lista
     * @return retorna un entero
     */
    public int cantidad ()
    {
        int cant = 0;
        Nodo reco = raiz;
        while (reco != null) {
            reco = reco.sig;
            cant++;
        }
        return cant;
    }
    
    /**
     * devuelve un valor booleano para saber si la lista esta o no esta vacia 
     * @return true si esta vacia
     */
    public boolean vacia ()
    {
        if (raiz == null)
            return true;
        else
            return false;
    }
        
}
