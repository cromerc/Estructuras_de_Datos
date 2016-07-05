package cl.cromer.estructuras;

final public class ArbolNodo {
	private ArbolNodo padre;
	private ArbolNodo izquerda;
	private ArbolNodo derecha;
	private int valor;
	private int nivel;

	public ArbolNodo(int valor, ArbolNodo padre) {
		this.padre = padre;
		this.izquerda = null;
		this.derecha = null;
		this.valor = valor;
		this.nivel = 1;
	}

	public ArbolNodo getPadre() {
		return padre;
	}

	public void setPadre(ArbolNodo padre) {
		this.padre = padre;
	}

	public ArbolNodo getIzquerda() {
		return izquerda;
	}

	public void setIzquerda(ArbolNodo izquerda) {
		this.izquerda = izquerda;
	}

	public ArbolNodo getDerecha() {
		return derecha;
	}

	public void setDerecha(ArbolNodo derecha) {
		this.derecha = derecha;
	}

	public int getValor() {
		return valor;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
}