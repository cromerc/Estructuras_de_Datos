package cl.cromer.estructuras;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Arbol {
	private ArbolNodo arbol;
	private int size;
	private int altura;
	private List<List<ArbolNodo>> niveles;

	public enum PrimerLado {
		IZQUERDA,
		DERECHA
	}

	public Arbol() {
		this.arbol = null;
		this.altura = 0;
		this.niveles = new ArrayList<>();
	}

	public boolean insertar(int valor) {
		if (this.arbol == null) {
			arbol = new ArbolNodo(valor, null);
			setAltura(1);
			return true;
		}
		else {
			PrimerLado primerLado = null;
			ArbolNodo nuevo = new ArbolNodo(valor, null);
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
					}
					actual = actual.getIzquerda();
					if (actual == null) {
						nuevo.setPadre(padre);
						padre.setIzquerda(nuevo);
						size++;
						setAltura(getAlturaRecursivo(arbol));

						niveles = new ArrayList<>();
						for (int i = 0; i <= getAltura(); i++) {
							niveles.add(new ArrayList<>());
						}
						calcularNiveles(arbol, 0);

						return true;
					}
				}
				else {
					// Derecha
					if (primerLado == null) {
						primerLado = PrimerLado.DERECHA;
					}
					actual = actual.getDerecha();
					if (actual == null) {
						nuevo.setPadre(padre);
						padre.setDerecha(nuevo);
						size++;
						setAltura(getAlturaRecursivo(arbol));

						niveles = new ArrayList<>();
						for (int i = 0; i <= getAltura(); i++) {
							niveles.add(new ArrayList<>());
						}
						calcularNiveles(arbol, 0);

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

	public List<List<ArbolNodo>> getNiveles() {
		return niveles;
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

	public void calcularNiveles(ArbolNodo nodo, int nivel) {
		try {
			if (nodo != null) {
				nodo.setNivel(nivel);
				niveles.get(nivel).add(nodo);
				nivel++;
				calcularNiveles(nodo.getIzquerda(), nivel);
				calcularNiveles(nodo.getDerecha(), nivel);
			}
			else if (nivel != getAltura()) {
				niveles.get(nivel).add(null);
				nivel++;
				calcularNiveles(null, nivel);
				calcularNiveles(null, nivel);
			}
		}
		catch (Exception exception) {
			Logs.log(Level.SEVERE, exception);
		}
	}

	/**
	 * Esta clase contiene los tipos de arboles.
	 *
	 * @author Chris Cromer
	 */
	final static public class Tipos {
		/**
		 * Tipo general.
		 */
		static final public int GENERAL = 0;

		/**
		 * Tipo binario.
		 */
		static final public int BINARIO = 1;

		/**
		 * Tipo busqueda binaria.
		 */
		static final public int BUSQUEDA_BINARIA = 2;

		/**
		 * Tipo AVL.
		 */
		static final public int AVL = 3;

		/**
		 * Tipo rojo-negro.
		 */
		static final public int ROJO_NEGRO = 4;

		/**
		 * Tipo B-Tree.
		 */
		static final public int B_TREE = 5;

		/**
		 * El tipo elegido.
		 */
		final private int tipo;

		/**
		 * Inicilizar el tipo de arbol.
		 *
		 * @param tipo int: El tipo de lista enlazada, {@value #GENERAL}, {@value #BINARIO}, {@value #BUSQUEDA_BINARIA}, {@value #AVL}, {@value #ROJO_NEGRO} o {@value #B_TREE}
		 */
		public Tipos(int tipo) {
			switch (tipo) {
				case GENERAL:
					this.tipo = GENERAL;
					break;
				case BINARIO:
					this.tipo = BINARIO;
					break;
				case BUSQUEDA_BINARIA:
					this.tipo = BUSQUEDA_BINARIA;
					break;
				case AVL:
					this.tipo = AVL;
					break;
				case ROJO_NEGRO:
					this.tipo = ROJO_NEGRO;
					break;
				case B_TREE:
					this.tipo = B_TREE;
					break;
				default:
					this.tipo = GENERAL;
			}
		}

		/**
		 * Devolver el tipo de arbol.
		 *
		 * @return int: El tipo.
		 */
		public int getTipo() {
			return tipo;
		}
	}
}
