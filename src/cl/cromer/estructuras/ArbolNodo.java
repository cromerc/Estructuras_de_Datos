package cl.cromer.estructuras;

/**
 * Clase de estructura de nodos del arbol.
 *
 * @author Chris Cromer
 */
final public class ArbolNodo {
	/**
	 * El padre del nodo.
	 */
	private ArbolNodo padre;

	/**
	 * El hijo a la izquerda.
	 */
	private ArbolNodo izquerda;

	/**
	 * El hijo a la derecha.
	 */
	private ArbolNodo derecha;

	/**
	 * El valor del nodo.
	 */
	private int valor;

	/**
	 * La posición del nodo en el plano carteseano.
	 */
	private int x;

	/**
	 * Incilizar.
	 *
	 * @param valor int: El valor del nodo.
	 */
	public ArbolNodo(int valor) {
		this.padre = null;
		this.izquerda = null;
		this.derecha = null;
		this.valor = valor;
		this.x=0;
	}

	/**
	 * Devolver el padre.
	 *
	 * @return ArbolNodo: El padre.
	 */
	public ArbolNodo getPadre() {
		return padre;
	}

	/**
	 * Cambiar padre.
	 *
	 * @param padre ArbolNodo: El padre nuevo.
	 */
	public void setPadre(ArbolNodo padre) {
		this.padre = padre;
	}

	/**
	 * Devolver el hijo izquerdo.
	 *
	 * @return ArbolNodo: El hijo.
	 */
	public ArbolNodo getIzquerda() {
		return izquerda;
	}

	/**
	 * Devolver el hijo izquerdo.
	 *
	 * @param izquerda ArbolNodo: El hijo nuevo.
	 */
	public void setIzquerda(ArbolNodo izquerda) {
		this.izquerda = izquerda;
	}

	/**
	 * Devolver el hijo derecho.
	 *
	 * @return ArbolNodo: El hijo.
	 */
	public ArbolNodo getDerecha() {
		return derecha;
	}

	/**
	 * Cambiar el hijo derecho.
	 *
	 * @param derecha ArbolNodo: El hijo neuvo.
	 */
	public void setDerecha(ArbolNodo derecha) {
		this.derecha = derecha;
	}

	/**
	 * Devolver el valor del nodo.
	 * @return int: El valor.
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Devolver la coordinada X del nodo.
	 *
	 * @return int: La coordinada x.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Cambiar la coordinada x del nodo.
	 * @param x int: La nueva coordinada x.
	 */
	public void setX(int x) {
		this.x = x;
	}
}
