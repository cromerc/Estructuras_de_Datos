package cl.cromer.estructuras;

/**
 * Este clase es la estructura de un nodo de grafoNoDirigido.
 *
 * @author Chris Cromer
 */
class GrafoNodo {
	/**
	 * El valor del nodo.
	 */
	private int value;

	/**
	 * Inicializar un nodo.
	 *
	 * @param value int: El valor del nodo.
	 */
	public GrafoNodo(int value) {
		this.value = value;
	}

	/**
	 * Devolver el valor del nodo.
	 *
	 * @return int: El valor del nodo.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Cambiar el valor del nodo.
	 *
	 * @param value int: El valor nuevo.
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
