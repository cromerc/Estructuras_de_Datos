package cl.cromer.estructuras;

/**
 * Crear una estructura de dato de tipo lista enlazada.
 *
 * @author Chris Cromer
 */
final public class ListaEnlazada {
	/**
	 * El enlace principal de la lista.
	 */
	private ListaEnlace lista;

	/**
	 * La cantidad de enlaces que están en la lista.
	 */
	private int size;

	/**
	 * El tipo de lista enlazada.
	 */
	private int tipo;

	/**
	 * Inicilizar.
	 */
	public ListaEnlazada() {
		lista = null;
	}

	/**
	 * Devolver el tipo de lista.
	 *
	 * @return int: El tipo.
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Cambiar el tipo de lista.
	 *
	 * @param tipo int: El tipo a cambiar.
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Insertar una llave en la lista.
	 *
	 * @param llave int: La llave a insertar.
	 *
	 * @return boolean: Verdad si fue insertado, sino falso.
	 */
	public boolean insertar(int llave) {
		if (buscar(llave) == null) {
			// Crear una enlace y agregarla a la lista
			ListaEnlace nuevo = new ListaEnlaceNormal();
			nuevo.setLlave(llave);
			nuevo.setSiguiente(lista);
			if (lista != null) {
				lista.setPrevio(nuevo);
			}
			lista = nuevo;
			size++;
			return true;
		}
		else {
			// Se falló porque la llave ya existe
			return false;
		}
	}

	/**
	 * Eliminar un enlace de la lista.
	 *
	 * @param llave int: La llave a eliminar.
	 *
	 * @return boolean: Verdad si fue borrado, sino falso.
	 */
	public boolean eliminar(int llave) {
		if (lista != null) {
			// La lista no es vacia
			ListaEnlace lista = this.lista;
			ListaEnlace previo = lista;
			while (lista.getLlave() != llave) {
				// Buscar hasta la llave es encontraddo
				if (lista.getSiguiente() != null) {
					// Buscar en la siguiente enlace
					previo = lista;
					lista = (ListaEnlace) lista.getSiguiente();
				}
				else {
					// No se encuentra
					return false;
				}
			}
			// Se encontró
			if (lista == this.lista) {
				// Si es la primera enlace, cambiarla al siguiente enlace
				this.lista = (ListaEnlace) this.lista.getSiguiente();
				if (this.lista.getPrevio() != null) {
					this.lista.setPrevio(null);
				}
			}
			else {
				// Sino cortar esta enlace de la lista
				previo.setSiguiente(lista.getSiguiente());
			}
			size--;
			return true;
		}
		else {
			// La lista es vacia, no hay nada para eliminar
			return false;
		}
	}

	/**
	 * Buscar una llave en la lista.
	 *
	 * @param llave int: La llave a buscar.
	 *
	 * @return ListaEnlace: El enlace que contiene la llave buscada.
	 */
	public ListaEnlace buscar(int llave) {
		if (this.lista != null) {
			// La lista no es vacia
			ListaEnlace lista = this.lista;
			while (lista.getLlave() != llave) {
				// Buscar hasta la llave es encontrado
				if (lista.getSiguiente() != null) {
					// Buscar en la siguiente enlace
					lista = (ListaEnlace) lista.getSiguiente();
				}
				else {
					// No se encuentra
					return null;
				}
			}
			// Se encontró
			return lista;
		}
		else {
			// La lista es vacia, nada para buscar
			return null;
		}
	}

	/**
	 * Devolver un enlace con su llave y valor.
	 *
	 * @param indice int: El indice que desea ver.
	 *
	 * @return ListaEnlace: El enlace a devolver.
	 */
	public ListaEnlace getIndice(int indice) {
		if (lista != null && indice >= 0 && indice < size()) {
			int i = size();
			ListaEnlace lista = this.lista;
			while (i > indice + 1) {
				lista = (ListaEnlace) lista.getSiguiente();
				i--;
			}
			return lista;
		}
		else {
			return null;
		}
	}

	/**
	 * Devolver la cantidad de enlaces que están en la lista.
	 *
	 * @return int: La cantidad.
	 */
	public int size() {
		return size;
	}

	/**
	 * Esta clase contiene los tipos de listas enlazadas.
	 *
	 * @author Chris Cromer
	 */
	final static public class Tipos {
		/**
		 * Tipo simple.
		 */
		static final public int SIMPLE = 0;

		/**
		 * Tipo circular.
		 */
		static final public int CIRCULAR = 1;

		/**
		 * Tipo doblemente enlazada.
		 */
		static final public int DOBLEMENTE_ENLAZADA = 2;

		/**
		 * El tipo elegido.
		 */
		final private int tipo;

		/**
		 * Inicilizar el tipo de lista enlazada.
		 *
		 * @param tipo int: El tipo de lista enlazada, {@value #SIMPLE}, {@value #CIRCULAR} o {@value #DOBLEMENTE_ENLAZADA}
		 */
		public Tipos(int tipo) {
			switch (tipo) {
				case SIMPLE:
					this.tipo = SIMPLE;
					break;
				case CIRCULAR:
					this.tipo = CIRCULAR;
					break;
				case DOBLEMENTE_ENLAZADA:
					this.tipo = DOBLEMENTE_ENLAZADA;
					break;
				default:
					this.tipo = SIMPLE;
			}
		}

		/**
		 * Devolver el tipo de lista enlazada.
		 *
		 * @return int: El tipo.
		 */
		public int getTipo() {
			return tipo;
		}
	}
}