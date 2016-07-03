package cl.cromer.estructuras;

import java.util.Random;

/**
 * Crear una estructura de dato de tipo cola.
 *
 * @author Chris Cromer
 */
final public class Cola {
	/**
	 * La cola.
	 */
	private String cola[];

	/**
	 * La cantidad de elementos que están en la cola.
	 */
	private int size;

	/**
	 * Inicializar.
	 */
	public Cola() {
		this.cola = null;
		size = 0;
	}

	/**
	 * Push un valor en la cola encima.
	 *
	 * @param valor int: El valor a push.
	 */
	public void push(int valor) {
		if (this.cola != null) {
			String cola[] = new String[this.cola.length + 1];
			int i;
			for (i = 0; i < this.cola.length; i++) {
				cola[i] = this.cola[i];
			}
			cola[i] = String.valueOf(valor);
			this.cola = cola;
			size++;
		}
		else {
			String pila[] = new String[1];
			pila[0] = String.valueOf(valor);
			this.cola = pila;
			size++;
		}
	}

	/**
	 * Pop un valor del principio de la cola.
	 *
	 * @return boolean: Verdad si fue exitoso.
	 */
	public boolean pop() {
		if (this.cola != null) {
			String cola[] = new String[this.cola.length - 1];
			// Nueva array sin el valor del primer
			System.arraycopy(this.cola, 1, cola, 0, cola.length);
			this.cola = cola;
			size--;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Peek al valor que está al principio de la cola.
	 *
	 * @return int: El valor que está al principio de la cola.
	 */
	public int peek() {
		if (this.cola != null && size() > 0) {
			return Integer.valueOf(cola[0]);
		}
		else {
			return Integer.MIN_VALUE;
		}
	}

	/**
	 * Llenar la cola con valores al azar.
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

	/**
	 * Devolver la cantidad de elementos que están en la cola.
	 *
	 * @return int: La cantidad de elementos.
	 */
	public int size() {
		return size;
	}

	/**
	 * Devolver el valor que está en un indice de la cola.
	 *
	 * @param indice int: El indice que desea devolver.
	 *
	 * @return String: El valor que está guardado en el indice.
	 */
	public String getIndice(int indice) {
		if (cola != null && indice >= 0 && indice < cola.length) {
			return cola[indice];
		}
		else {
			return null;
		}
	}
}
