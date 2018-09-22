/**
 * 
 * @author Bojorge
 *
 * @param <E> - tipo de dato generico E = elemento 
 */
public class Nodo<E> {
	private E dato;
	private Nodo<E>siguiente;
	
	/**
	 * Metodo constructor de Nodo
	 * 
	 * @param dato - es la informacion que almacena el Nodo
	 * @param siguiente - hace referencia al proximo Nodo en la lista
	 */
	public Nodo(E dato,Nodo<E> siguiente) {
		this.dato=dato;
		this.siguiente=siguiente;
	}
	
	/**
	 * Devuelve el dato almacenado
	 * 
	 * @return Generico
	 */
	public E getDato() {
		return dato;
	}

	/**
	 * Este metodo se usa para ingresar o cambiar algun dato en cualquier Nodo
	 * @param dato - es el dato que se asigna a un Nodo
	 */
	public void setDato(E dato) {
		this.dato = dato;
	}

	/**
	 * Devuelve el Nodo siguiente
	 * @return Generico
	 */
	public Nodo<E> getSiguiente() {
		return siguiente;
	}

	/**
	 * Establece al Nodo siguiente con respecto al Nodo que tenga la condicion de Nodo actual durante la ejecucion 
	 * @param siguiente - es un Nodo que se adjunta a la lista
	 */
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}

}
