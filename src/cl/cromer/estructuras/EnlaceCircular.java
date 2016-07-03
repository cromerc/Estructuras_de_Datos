package cl.cromer.estructuras;

/**
 * Esta clase es de tipo de enlace circular.
 *
 * @author Chris Cromer
 */
final public class EnlaceCircular implements Enlace {
	/**
	 * La llave.
	 */
	private int llave;

	/**
	 * El siguiente enlace.
	 */
	private EnlaceCircular siguiente;

	/**
	 * Incializar.
	 */
	public EnlaceCircular() {
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
	 * @return EnlaceCircular: El enlace a devolver.
	 */
	public EnlaceCircular getSiguiente() {
		return siguiente;
	}

	/**
	 * Cambiar el siguiente enlace.
	 *
	 * @param siguiente Object: El siguiente enlace nuevo de tipo {@link EnlaceCircular}.
	 */
	public void setSiguiente(Object siguiente) {
		this.siguiente = (EnlaceCircular) siguiente;
	}

	/**
	 * Devolver el enlace previo.
	 *
	 * @return EnlaceCircular: El enlace previo.
	 */
	public EnlaceCircular getPrevio() {
		return null;
	}

	/**
	 * Dummy metodo para usar interface {@link Enlace}
	 *
	 * @param previo Object: El enlace previo nuevo de tipo {@link EnlaceCircular}.
	 */
	public void setPrevio(Object previo) {
	}
}