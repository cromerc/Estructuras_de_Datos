package cl.cromer.estructuras;

/**
 * Esta interfaz es para los 2 arbolTipo de enlace, {@link ListaEnlaceNormal} y {@link ListaEnlaceCircular}.
 *
 * @author Chris Cromer
 */
interface ListaEnlace {
	/**
	 * Devolver la llave.
	 *
	 * @return int: La llave.
	 */
	int getLlave();

	/**
	 * Cambiar el valor de la llave.
	 *
	 * @param llave int: El valor de la llave.
	 */
	void setLlave(int llave);

	/**
	 * Devolver el siguente enlace.
	 *
	 * @return Object: El enlace a devolver.
	 */
	Object getSiguiente();

	/**
	 * Cambiar el siguiente enlace.
	 *
	 * @param siguiente Object: El siguiente enlace nuevo.
	 */
	void setSiguiente(Object siguiente);

	/**
	 * Devolver el enlace previo.
	 *
	 * @return Object: El enlace previo.
	 */
	Object getPrevio();

	/**
	 * Cambiar el previo enlace.
	 *
	 * @param previo Object: El enlace previo nuevo.
	 */
	void setPrevio(Object previo);
}