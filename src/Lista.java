

/**
 * 
 * @author Bojorge
 *
 */
public class Lista<E> {
	
	Nodo<E> primero;
	Nodo<E> ultimo;
	int cantidad;
	
	/**
	 * Metodo constructor
	 */
	public Lista() {
		this.cantidad=0;
	}
	/**
	 * devuelve la cantidad de elementos en la lista
	 * @return
	 */
	public int getCantidad() {
		return cantidad;
	}
	
	/**
	 * reinicia la matriz
	 */
	public void vaciar() {
	cantidad=0;
	}
	
	/**
	 * indica con un valor booleano si la matriz está vacia
	 * @return
	 */
	public boolean estaVacia() {
		return (cantidad==0);
	}
	/**
	 * regresa el dato del primer elemento
	 * @return
	 */
	public E primero() {
		if(estaVacia()) {
			return null;
		}
		return primero.getDato();
	}
	/**
	 * inserta al inicio de la lista
	 * @param dato
	 */
	public void insertarInicio(E dato) {
		primero=new Nodo<E>(dato,primero);
		if (estaVacia()) {
			ultimo=primero;
		}
		cantidad++;
	}
	/**
	 * regresa el ultimo elemento
	 * @return
	 */
	public E ultimo() {
		if(estaVacia()) {
			return null;
		}
		return ultimo.getDato();
	}
	
//	public void agregarAlInicio(E dato){
//        // Define un nuevo nodo.
//        Nodo<E> nuevo = new Nodo<E>(dato,p);
//        // Agrega al valor al nodo.
//        nuevo.setDato(dato);
//        // Consulta si la lista esta vacia.
//        if (estaVacia()) {
//            // Inicializa la lista agregando como inicio al nuevo nodo.
//            primero = nuevo;
//        // Caso contrario va agregando los nodos al inicio de la lista.
//        } else{
//            // Une el nuevo nodo con la lista existente.
//            nuevo.setSiguiente(primero);
//            // Renombra al nuevo nodo como el inicio de la lista.
//            primero = nuevo;
//        }
//        // Incrementa el contador de tamaño de la lista.
//        cantidad++;
//    }
}
