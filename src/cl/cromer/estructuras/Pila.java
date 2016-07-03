package cl.cromer.estructuras;

import java.util.Random;

/**
 * Crear una estructura de dato de tipo pila.
 *
 * @author Chris Cromer
 */
final public class Pila {
	/**
	 * La pila.
	 */
	private String pila[];

	/**
	 * La cantidad de elementos en la pila.
	 */
	private int size;

	/**
	 * Inicializar.
	 */
	public Pila() {
		pila = null;
		size = 0;
	}

	/**
	 * Push un valor en la pila encima.
	 *
	 * @param valor int: El valor a push.
	 */
	public void push(int valor) {
		if (this.pila != null) {
			String pila[] = new String[this.pila.length + 1];
			int i;
			for (i = 0; i < this.pila.length; i++) {
				pila[i] = this.pila[i];
			}
			pila[i] = String.valueOf(valor);
			this.pila = pila;
			size++;
		}
		else {
			String pila[] = new String[1];
			pila[0] = String.valueOf(valor);
			this.pila = pila;
			size++;
		}
	}

	/**
	 * Pop un valor de encima de la pila.
	 *
	 * @return boolean: Verdad si fue exitoso.
	 */
	public boolean pop() {
		if (this.pila != null && size() > 0) {
			String pila[] = new String[this.pila.length - 1];
			System.arraycopy(this.pila, 0, pila, 0, pila.length);
			this.pila = pila;
			size--;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Peek al valor que está encima de la pila.
	 *
	 * @return int: El valor que está encima de la pila.
	 */
	public int peek() {
		if (pila != null && size() > 0) {
			return Integer.valueOf(pila[pila.length - 1]);
		}
		else {
			return Integer.MIN_VALUE;
		}
	}

	/**
	 * Devolver la cantidad de elementos en la pila.
	 *
	 * @return int: La cantidad de elementos.
	 */
	public int size() {
		return size;
	}

	/**
	 * Devolver el valor que está en un indice de la pila.
	 *
	 * @param indice int: El indice que desea devolver.
	 *
	 * @return String: El valor que está guardado en el indice.
	 */
	public String getIndice(int indice) {
		if (pila != null && indice >= 0 && indice < pila.length) {
			return pila[indice];
		}
		else {
			return null;
		}
	}

	/**
	 * Llenar la pila con valores al azar.
	 */
	@SuppressWarnings("Duplicates")
	public void llenar() {
		Random random = new Random();
		int maximo = 99;
		int minimo = 0;
		int rango = maximo - minimo + 1;

		for (int i = size(); i < 10; i++) {
			int numero = random.nextInt(rango) + minimo;
			push(numero);
		}
	}
}