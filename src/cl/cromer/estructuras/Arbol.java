package cl.cromer.estructuras;

public class Arbol {
	private ArbolNodo arbol;
	private int size;
	private int altura;
	private int ancho;

	public enum PrimerLado {
		IZQUERDA,
		DERECHA
	}

	public Arbol() {
		this.arbol = null;
		this.altura = 0;
		this.ancho = 0;
	}

	public boolean insertar(int valor) {
		if (this.arbol == null) {
			arbol = new ArbolNodo(valor);
			arbol.setY(1);
			arbol.setX(1);
			ancho = 1;
			altura = 1;
			return true;
		}
		else {
			PrimerLado primerLado = null;
			ArbolNodo nuevo = new ArbolNodo(valor);
			ArbolNodo actual = arbol;
			ArbolNodo padre;
			while (true) {
				padre = actual;
				if (valor == actual.getValor()) {
					// Ya existe.
					return false;
				}
				else if (valor < actual.getValor()) {
					// Izquerda
					if (primerLado == null) {
						primerLado = PrimerLado.IZQUERDA;
						nuevo.setX(1);
					}
					else {
						nuevo.setX(nuevo.getX() + 1);
					}
					nuevo.setY(nuevo.getY() + 1);
					actual = actual.getIzquerda();
					if (actual == null) {
						padre.setIzquerda(nuevo);
						size++;
						if (primerLado == PrimerLado.IZQUERDA) {
							ancho++;
						}
						setAltura(getAlturaRecursivo(arbol));
						return true;
					}
				}
				else {
					// Derecha
					if (primerLado == null) {
						primerLado = PrimerLado.DERECHA;
						nuevo.setX(1);
					}
					else {
						nuevo.setX(nuevo.getX() + 1);
					}
					nuevo.setY(nuevo.getY() + 1);
					actual = actual.getDerecha();
					if (actual == null) {
						padre.setDerecha(nuevo);
						size++;
						if (primerLado == PrimerLado.DERECHA) {
							ancho++;
						}
						setAltura(getAlturaRecursivo(arbol));
						return true;
					}
				}
			}
		}
	}

	public ArbolNodo getArbol() {
		return arbol;
	}

	public int size() {
		return size;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlturaRecursivo(ArbolNodo nodo) {
		if (nodo == null) {
			return 0;
		}
		else {
			int alturaIzquerda = getAlturaRecursivo(nodo.getIzquerda());
			int alturaDercha = getAlturaRecursivo(nodo.getDerecha());
			if (alturaIzquerda > alturaDercha) {
				return (alturaIzquerda + 1);
			}
			else {
				return (alturaDercha + 1);
			}
		}
	}
}
