package cl.cromer.estructuras;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Arbol {
	private ArbolNodo arbol;
	private int size;
	private int altura;
	private int ancho;
	private List<List<ArbolNodo>> niveles;

	public enum PrimerLado {
		IZQUERDA,
		DERECHA
	}

	public Arbol() {
		this.arbol = null;
		this.altura = 0;
		this.ancho = 0;
		this.niveles = new ArrayList<>();
	}

	public boolean insertar(int valor) {
		if (this.arbol == null) {
			arbol = new ArbolNodo(valor, null);
			setAncho(1);
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
						if (primerLado == PrimerLado.IZQUERDA) {
							setAncho(getAncho() + 1);
						}
						setAltura(getAlturaRecursivo(arbol));

						niveles = new ArrayList<>();
						for (int i = 0; i < getAltura(); i++) {
							niveles.add(new ArrayList<>());
						}
						getNivelesRecursivo(arbol);

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
						if (primerLado == PrimerLado.DERECHA) {
							setAncho(getAncho() + 1);
						}
						setAltura(getAlturaRecursivo(arbol));

						niveles = new ArrayList<>();
						for (int i = 0; i < getAltura(); i++) {
							niveles.add(new ArrayList<>());
						}
						getNivelesRecursivo(arbol);

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

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		//this.ancho = ancho;
		this.ancho = (int) Math.pow(2, altura - 1) - 1;
	}

	public int getAlturaRecursivo(ArbolNodo nodo) {
		if (nodo == null) {
			return 0;
		}
		else {
			int alturaIzquerda = getAlturaRecursivo(nodo.getIzquerda());
			int alturaDercha = getAlturaRecursivo(nodo.getDerecha());
			if (alturaIzquerda > alturaDercha) {
				nodo.setNivel(alturaIzquerda);
				return (alturaIzquerda + 1);
			}
			else {
				nodo.setNivel(alturaDercha);
				return (alturaDercha + 1);
			}
		}
	}

	public int getNivelesRecursivo(ArbolNodo nodo) {
		if (nodo == null) {
			return 0;
		}
		else {
			try {
				int alturaIzquerda = getNivelesRecursivo(nodo.getIzquerda());
				int alturaDerecha = getNivelesRecursivo(nodo.getDerecha());
				if (alturaIzquerda > alturaDerecha) {
					if (!niveles.get(niveles.size() - alturaIzquerda - 1).contains(nodo)) {
						niveles.get(niveles.size() - alturaIzquerda - 1).add(nodo);
					}
					if (nodo.getDerecha() == null) {
						niveles.get(niveles.size() - nodo.getNivel() - 1).add(null);
					}
					if (nodo.getIzquerda() == null) {
						niveles.get(niveles.size() - nodo.getNivel() - 1).add(null);
					}
					return (alturaIzquerda + 1);
				}
				else {
					if (!niveles.get(niveles.size() - alturaDerecha - 1).contains(nodo)) {
						niveles.get(niveles.size() - alturaDerecha - 1).add(nodo);
					}
					if (nodo.getDerecha() == null) {
						niveles.get(niveles.size() - nodo.getNivel() - 1).add(null);
					}
					if (nodo.getIzquerda() == null) {
						niveles.get(niveles.size() - nodo.getNivel() - 1).add(null);
					}
					return (alturaDerecha + 1);
				}
			}
			catch (Exception exception) {
				Logs.log(Level.SEVERE, exception);
				return 0;
			}
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
