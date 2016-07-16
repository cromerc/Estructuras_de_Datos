package cl.cromer.estructuras;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Esta clase tiene la estrutura de dato de tipo arbol.
 *
 * @author Chris Cromer
 */
public class Arbol {
	/**
	 * El arbol.
	 */
	private ArbolNodo arbol;

	/**
	 * La cantidad de nodos que están en el arbol.
	 */
	private int size;

	/**
	 * La altura del arbol.
	 */
	private int altura;

	/**
	 * Los nivles del arbol con todos sus nodos.
	 */
	private List<List<ArbolNodo>> niveles;

	/**
	 * El orden que ha sido usado.
	 */
	private List<ArbolNodo> order;

	/**
	 * Inicilizar el arbol.
	 */
	public Arbol() {
		this.arbol = null;
		this.altura = 0;
		this.niveles = new ArrayList<>();
		this.order = new ArrayList<>();
	}

	/**
	 * Insertar un valor al arbol.
	 *
	 * @param valor int: El valor a insertar.
	 *
	 * @return boolean: Verdad si fue insertado, falso si ya existe el nodo.
	 */
	public boolean insertar(int valor) {
		if (this.arbol == null) {
			arbol = new ArbolNodo(valor, null);
			size++;

			setAltura(calcularAltura(arbol));

			niveles = new ArrayList<>();
			for (int i = 0; i <= getAltura(); i++) {
				niveles.add(new ArrayList<>());
			}
			calcularNiveles(arbol, 0);
			niveles.remove(niveles.size() - 1);

			return true;
		}
		else {
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
					actual = actual.getIzquerda();
					if (actual == null) {
						nuevo.setPadre(padre);
						padre.setIzquerda(nuevo);
						size++;
						setAltura(calcularAltura(arbol));

						niveles = new ArrayList<>();
						for (int i = 0; i <= getAltura(); i++) {
							niveles.add(new ArrayList<>());
						}
						calcularNiveles(arbol, 0);
						niveles.remove(niveles.size() - 1);

						return true;
					}
				}
				else {
					// Derecha
					actual = actual.getDerecha();
					if (actual == null) {
						nuevo.setPadre(padre);
						padre.setDerecha(nuevo);
						size++;
						setAltura(calcularAltura(arbol));

						niveles = new ArrayList<>();
						for (int i = 0; i <= getAltura(); i++) {
							niveles.add(new ArrayList<>());
						}
						calcularNiveles(arbol, 0);
						niveles.remove(niveles.size() - 1);

						return true;
					}
				}
			}
		}
	}

	/**
	 * Eliminar un valor del arbol.
	 *
	 * @param valor int: El valor a elminiar.
	 *
	 * @return boolean: Verdad si fue eliminado, falso si no existe.
	 */
	public boolean eliminar(int valor) {
		ArbolNodo actual = arbol;
		ArbolNodo padre = arbol;
		boolean izquerda = true;

		while (actual.getValor() != valor) {
			padre = actual;
			if (valor < actual.getValor()) {
				izquerda = true;
				actual = actual.getIzquerda();
			}
			else {
				izquerda = false;
				actual = actual.getDerecha();
			}
			if (actual == null) {
				return false;
			}
		}

		if (actual.getIzquerda() == null && actual.getDerecha() == null) {
			if (actual == arbol) {
				arbol = null;
			}
			else if (izquerda) {
				padre.setIzquerda(null);
			}
			else {
				padre.setDerecha(null);
			}
		}
		else if(actual.getDerecha() == null) {
			if (actual == arbol) {
				arbol = actual.getIzquerda();
			}
			else if (izquerda) {
				padre.setIzquerda(actual.getIzquerda());
			}
			else {
				padre.setDerecha(actual.getIzquerda());
			}
		}
		else if(actual.getIzquerda() == null) {
			if (actual == arbol) {
				arbol = actual.getDerecha();
			}
			else if (izquerda) {
				padre.setIzquerda(actual.getDerecha());
			}
			else {
				padre.setDerecha(actual.getDerecha());
			}
		}
		else {
			ArbolNodo successorParent = actual;
			ArbolNodo successor = actual;
			ArbolNodo current = actual.getDerecha();
			while (current != null) {
				successorParent = successor;
				successor = current;
				current = current.getIzquerda();
			}
			if (successor != actual.getDerecha()) {
				successorParent.setIzquerda(successor.getDerecha());
				successor.setDerecha(actual.getDerecha());
			}

			if (actual == arbol) {
				arbol = successor;
			}
			else if (izquerda) {
				padre.setIzquerda(successor);
			}
			else {
				padre.setDerecha(successor);
			}

			successor.setIzquerda(actual.getIzquerda());
		}
		size--;
		setAltura(calcularAltura(arbol));

		niveles = new ArrayList<>();
		for (int i = 0; i <= getAltura(); i++) {
			niveles.add(new ArrayList<>());
		}
		calcularNiveles(arbol, 0);
		niveles.remove(niveles.size() - 1);

		return true;
	}

	/**
	 * Rotar el arbol usando el hijo a la derecha como un pivot.
	 *
	 * @param valor int: El valor a rotar.
	 *
	 * @return boolean: Verdad si fue rotado.
	 */
	public boolean rotarIzquerda(int valor) {
		for (int i = 0; i < getAltura(); i++) {
			for (int j = 0; j < niveles.get(i).size(); j++) {
				if (niveles.get(i).get(j) != null && niveles.get(i).get(j).getValor() == valor) {
					ArbolNodo padre = niveles.get(i).get(j);
					ArbolNodo abuelo = padre.getPadre();

					ArbolNodo hijo = padre.getDerecha();
					if (hijo == null) {
						return false;
					}
					ArbolNodo hijoIzquerda = hijo.getIzquerda();

					if (arbol == padre) {
						arbol = hijo;
					}
					else {
						if (abuelo.getDerecha() == padre) {
							abuelo.setDerecha(hijo);
						}
						else {
							abuelo.setIzquerda(hijo);
						}
					}

					hijo.setIzquerda(padre);
					hijo.setPadre(abuelo);
					padre.setPadre(hijo);
					if (hijoIzquerda != null) {
						padre.setDerecha(hijoIzquerda);
						hijoIzquerda.setPadre(padre);
					}
					else {
						padre.setDerecha(null);
					}

					setAltura(calcularAltura(arbol));

					niveles = new ArrayList<>();
					for (int k = 0; k <= getAltura(); k++) {
						niveles.add(new ArrayList<>());
					}
					calcularNiveles(arbol, 0);
					niveles.remove(niveles.size() - 1);

					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Rotar el arbol usando el hijo a la izquerda como un pivot.
	 *
	 * @param valor int: El valor a rotar.
	 *
	 * @return boolean: Verdad si fue rotado.
	 */
	public boolean rotarDerecha(int valor) {
		for (int i = 0; i < getAltura(); i++) {
			for (int j = 0; j < niveles.get(i).size(); j++) {
				if (niveles.get(i).get(j) != null && niveles.get(i).get(j).getValor() == valor) {
					ArbolNodo padre = niveles.get(i).get(j);
					ArbolNodo abuelo = padre.getPadre();

					ArbolNodo hijo = padre.getIzquerda();
					if (hijo == null) {
						return false;
					}
					ArbolNodo hijoDerecha = hijo.getDerecha();

					if (arbol == padre) {
						arbol = hijo;
					}
					else {
						if (abuelo.getDerecha() == padre) {
							abuelo.setDerecha(hijo);
						}
						else {
							abuelo.setIzquerda(hijo);
						}
					}

					hijo.setDerecha(padre);
					hijo.setPadre(abuelo);
					padre.setPadre(hijo);
					if (hijoDerecha != null) {
						padre.setIzquerda(hijoDerecha);
						hijoDerecha.setPadre(padre);
					}
					else {
						padre.setIzquerda(null);
					}

					setAltura(calcularAltura(arbol));

					niveles = new ArrayList<>();
					for (int k = 0; k <= getAltura(); k++) {
						niveles.add(new ArrayList<>());
					}
					calcularNiveles(arbol, 0);
					niveles.remove(niveles.size() - 1);

					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Ordenar el arbol usando preOrder.
	 *
	 * @return List: La lista del orden.
	 */
	public List<ArbolNodo> preOrder() {
		order = new ArrayList<>();
		preOrder(arbol);
		return order;
	}

	/**
	 * Ordenar el arbol usando inOrder.
	 *
	 * @return List: La lista del orden.
	 */
	public List<ArbolNodo> inOrder() {
		order = new ArrayList<>();
		inOrder(arbol);
		return order;
	}

	/**
	 * Ordenar el arbol usando postOrder.
	 *
	 * @return List: La lista del orden.
	 */
	public List<ArbolNodo> postOrder() {
		order = new ArrayList<>();
		postOrder(arbol);
		return order;
	}

	/**
	 * El meteodo recursivo de preOrder que acumulará los nodos en una lista.
	 *
	 * @param nodo ArbolNodo: El nodo a trabajar.
	 */
	public void preOrder(ArbolNodo nodo) {
		if (nodo != null) {
			order.add(nodo);
			preOrder(nodo.getIzquerda());
			preOrder(nodo.getDerecha());
		}
	}

	/**
	 * El meteodo recursivo de inOrder que acumulará los nodos en una lista.
	 *
	 * @param nodo ArbolNodo: El nodo a trabajar.
	 */
	public void inOrder(ArbolNodo nodo) {
		if (nodo != null) {
			preOrder(nodo.getIzquerda());
			order.add(nodo);
			preOrder(nodo.getDerecha());
		}
	}

	/**
	 * El meteodo recursivo de postOrder que acumulará los nodos en una lista.
	 *
	 * @param nodo ArbolNodo: El nodo a trabajar.
	 */
	public void postOrder(ArbolNodo nodo) {
		if (nodo != null) {
			preOrder(nodo.getIzquerda());
			preOrder(nodo.getDerecha());
			order.add(nodo);
		}
	}

	/**
	 * Devolver el arbol.
	 *
	 * @return ArbolNodo: La raiz del arbol.
	 */
	public ArbolNodo getArbol() {
		return arbol;
	}

	/**
	 * Devolver la cantidad de nodos que están en el arbol.
	 *
	 * @return int: La cantidad.
	 */
	public int size() {
		return size;
	}

	/**
	 * Devolver la altura del arbol.
	 *
	 * @return int: La altura.
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Cambiar la altura del arbol.
	 *
	 * @param altura int: La altura nueva.
	 */
	private void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Devolver la lista de nivles del arbol.
	 *
	 * @return List: La lista de niveles.
	 */
	public List<List<ArbolNodo>> getNiveles() {
		return niveles;
	}

	/**
	 * Encontrar y devolver la altura del arbol usando recursividad.
	 *
	 * @param nodo ArbolNodo: El nodo a trabajar.
	 *
	 * @return int: La altura.
	 */
	public int calcularAltura(ArbolNodo nodo) {
		if (nodo == null) {
			return 0;
		}
		else {
			int alturaIzquerda = calcularAltura(nodo.getIzquerda());
			int alturaDercha = calcularAltura(nodo.getDerecha());
			if (alturaIzquerda > alturaDercha) {
				return (alturaIzquerda + 1);
			}
			else {
				return (alturaDercha + 1);
			}
		}
	}

	/**
	 * Calcular los nivles del arbol usando recursividad.
	 *
	 * @param nodo ArbolNodo: El nodo a trabajar.
	 * @param nivel int: El Nivel donde está el nodo.
	 */
	public void calcularNiveles(ArbolNodo nodo, int nivel) {
		try {
			if (nodo != null) {
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
