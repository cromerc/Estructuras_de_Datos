package cl.cromer.estructuras;

/**
 * Esta clase es de tipo de enlace normal.
 *
 * @author Chris Cromer
 */
final public class ListaEnlaceNormal implements ListaEnlace {
	/**
	 * La llave.
	 */
	private int llave;

	/**
	 * El siguiente enlace.
	 */
	private ListaEnlaceNormal siguiente;

	/**
	 * El enlace previo por doble enlazada.
	 */
	private ListaEnlaceNormal previo;

	/**
	 * Incializar.
	 */
	public ListaEnlaceNormal() {
		siguiente = null;
		previo = null;
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
	 * @return ListaEnlaceNormal: El enlace a devolver.
	 */
	public ListaEnlaceNormal getSiguiente() {
		return siguiente;
	}

	/**
	 * Cambiar el siguiente enlace.
	 *
	 * @param siguiente Object: El siguiente enlace nuevo de tipo {@link ListaEnlaceNormal}.
	 */
	public void setSiguiente(Object siguiente) {
		this.siguiente = (ListaEnlaceNormal) siguiente;
	}

	/**
	 * Devolver el enlace previo.
	 *
	 * @return ListaEnlaceNormal: El enlace previo.
	 */
	public ListaEnlaceNormal getPrevio() {
		return previo;
	}

	/**
	 * Cambiar el previo enlace.
	 *
	 * @param previo Object: El enlace previo nuevo de tipo {@link ListaEnlaceNormal}.
	 */
	public void setPrevio(Object previo) {
		this.previo = (ListaEnlaceNormal) previo;
	}
}