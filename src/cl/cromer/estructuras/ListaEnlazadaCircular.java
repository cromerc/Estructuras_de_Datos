package cl.cromer.estructuras;

/**
 * Crear una estructura de dato de tipo lista enlazada circular.
 *
 * @author Chris Cromer
 */
final public class ListaEnlazadaCircular {
	/**
	 * El primer enlace.
	 */
	private Enlace primer;

	/**
	 * El ultimo enlace.
	 */
	private Enlace ultimo;

	/**
	 * La cantidad de enlaces que hay.
	 */
	private int size;

	/**
	 * El tipo de lista enlazada.
	 */
	private int tipo;

	/**
	 * Inicilizar.
	 */
	public ListaEnlazadaCircular() {
		primer = null;
		ultimo = null;
	}

	/**
	 * Devolver el tipo de lista.
	 *
	 * @return int: El tipo de lista.
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
	 * @return boolean: Verdad si fue insertado, falso si ya existe la llave.
	 */
	public boolean insertar(int llave) {
		if (buscar(llave) == null) {
			// Crear una enlace y agregarla a la lista
			Enlace enlace = new EnlaceCircular();

			if (primer == null) {
				ultimo = enlace;
			}

			enlace.setLlave(llave);
			enlace.setSiguiente(primer);
			primer = enlace;
			ultimo.setSiguiente(primer);

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
	 * @param llave int: La llave a elminiar.
	 *
	 * @return boolean: Verdad se fue eliminado, falso si no estaba en la lista.
	 */
	public boolean eliminar(int llave) {
		if (primer != null) {
			// La lista no es vacia
			Enlace lista = this.primer;
			Enlace previo = lista;
			int i = 0;
			while (lista.getLlave() != llave && i < size()) {
				// Buscar hasta la llave es encontraddo
				if (lista.getSiguiente() != null) {
					// Buscar en la siguiente enlace
					previo = lista;
					lista = (Enlace) lista.getSiguiente();
				}
				i++;
			}

			if (lista.getLlave() != llave) {
				// No se encontró
				return false;
			}

			// Se encontró
			if (lista == this.primer) {
				// Si es la primera enlace, cambiarla al sigueinte enlace
				this.primer = (Enlace) this.primer.getSiguiente();
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
	 * @return Enlace: El enlace que contiene la llave.
	 */
	public Enlace buscar(int llave) {
		if (this.primer != null) {
			// La lista no es vacia
			Enlace lista = this.primer;
			int i = 0;
			while (lista.getLlave() != llave && i < size()) {
				// Buscar en la sigenute enlace hasta el final.
				lista = (Enlace) lista.getSiguiente();
				i++;
			}
			if (lista.getLlave() == llave) {
				// Devoler el enlace encontrado.
				return lista;
			}
			else {
				// No se encontró.
				return null;
			}
		}
		else {
			// La lista es vacia, nada para buscar
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
	 * Devolver un enlace con su llave y valor.
	 *
	 * @param indice int: El indice que desea ver.
	 *
	 * @return Enlace: El enlace a devolver.
	 */
	public Enlace getIndice(int indice) {
		if (primer != null && indice >= 0 && indice < size()) {
			int i = size();
			Enlace lista = this.primer;
			while (i > indice + 1) {
				lista = (Enlace) lista.getSiguiente();
				i--;
			}
			return lista;
		}
		else {
			return null;
		}
	}
}