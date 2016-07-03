package cl.cromer.estructuras;

/**
 * La estructura de un elemento de una Tabla Hash.
 */
public class HashItem {
	/**
	 * La llave.
	 */
	final private String llave;

	/**
	 * El valor.
	 */
	final private int valor;

	/**
	 * Donde el elemento está en el array.
	 */
	private int indice;

	/**
	 * Inicilizar.
	 *
	 * @param llave String: La llave del elemento.
	 * @param valor int: El valor del elemento.
	 */
	public HashItem(String llave, int valor) {
		this.llave = llave;
		this.valor = valor;
	}

	/**
	 * Devolver la llave del elemento.
	 *
	 * @return String: La llave.
	 */
	public String getLlave() {
		return llave;
	}

	/**
	 * Devolver el valor del elemento.
	 *
	 * @return int: El valor.
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Devolver el indice del elemento.
	 *
	 * @return int: El indice.
	 */
	public int getIndice() {
		return indice;
	}

	/**
	 * Cambiar el indice.
	 *
	 * @param indice int: El indice.
	 */
	public void setIndice(int indice) {
		this.indice = indice;
	}
}