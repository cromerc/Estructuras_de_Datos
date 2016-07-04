package cl.cromer.estructuras;

/**
 * Esta clase es de tipo de enlace circular.
 *
 * @author Chris Cromer
 */
final public class ListaEnlaceCircular implements ListaEnlace {
	/**
	 * La llave.
	 */
	private int llave;

	/**
	 * El siguiente enlace.
	 */
	private ListaEnlaceCircular siguiente;

	/**
	 * Incializar.
	 */
	public ListaEnlaceCircular() {
		siguiente = null;
	}

	/**
	 * Devolver la llave.
	 *
	 * @return int: La llave.
	 */
	public int getLlave() {
		return llave;
	}

	/**
	 * Cambiar el valor de la llave.
	 *
	 * @param llave int: El valor de la llave.
	 */
	public void setLlave(int llave) {
		this.llave = llave;
	}

	/**
	 * Devolver el siguiente enlace.
	 *
	 * @return ListaEnlaceCircular: El enlace a devolver.
	 */
	public ListaEnlaceCircular getSiguiente() {
		return siguiente;
	}

	/**
	 * Cambiar el siguiente enlace.
	 *
	 * @param siguiente Object: El siguiente enlace nuevo de tipo {@link ListaEnlaceCircular}.
	 */
	public void setSiguiente(Object siguiente) {
		this.siguiente = (ListaEnlaceCircular) siguiente;
	}

	/**
	 * Devolver el enlace previo.
	 *
	 * @return ListaEnlaceCircular: El enlace previo.
	 */
	public ListaEnlaceCircular getPrevio() {
		return null;
	}

	/**
	 * Dummy metodo para usar interface {@link ListaEnlace}
	 *
	 * @param previo Object: El enlace previo nuevo de tipo {@link ListaEnlaceCircular}.
	 */
	public void setPrevio(Object previo) {
	}
}