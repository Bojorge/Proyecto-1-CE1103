

/**
 * 
 * @author Bojorge
 *
 */
public class Lista<E> {
	
	Nodo<E> primero;
	Nodo<E> ultimo;
	int cantidad;
	
	public Lista() {
		this.cantidad=0;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void vaciar() {
	cantidad=0;
	}
	
	public boolean estaVacia() {
		return (cantidad==0);
	}
	
	public E primero() {
		if(estaVacia()) {
			return null;
		}
		return primero.getDato();
	}
	
	public void insertarInicio(E dato) {
		primero=new Nodo<E>(dato,primero);
		if (estaVacia()) {
			ultimo=primero;
		}
		cantidad++;
	}
	
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
