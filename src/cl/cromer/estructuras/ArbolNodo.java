package cl.cromer.estructuras;

final public class ArbolNodo {
	private ArbolNodo izquerda;
	private ArbolNodo derecha;
	private int valor;
	private int y;
	private int x;
	private Desde desde;

	public ArbolNodo(int valor) {
		this.izquerda = null;
		this.derecha = null;
		this.valor = valor;
		this.y = 0;
		this.x = 0;
		this.desde = Desde.RAIZ;
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

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y >= 0) {
			this.y = y;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (x >= 0) {
			this.x = x;
		}
	}

	public Desde getDesde() {
		return desde;
	}

	public void setDesde(Desde desde) {
		this.desde = desde;
	}

	public enum Desde {
		RAIZ,
		IQUERDA,
		DERECHA
	}
}
