package cl.cromer.estructuras;

/**
 * Esta clase es tipo de estructura de dato Tabla Hash.
 *
 * @author Chris Cromer
 */
public class TablaHash {
	/**
	 * El array donde están todos los elementos de la tabla hash.
	 */
	final private HashItem hashArray[];

	/**
	 * El tamaño maximo de la tabla hash.
	 */
	final private int tamano;

	/**
	 * Cantidad de elementos que están en la tabla hash.
	 */
	private int size;

	/**
	 * Inicilizar.
	 *
	 * @param tamano int: El tamaño maximo de la tabla hash.
	 */
	public TablaHash(int tamano) {
		this.tamano = tamano;
		hashArray = new HashItem[tamano];
	}

	/**
	 * Este metodo crea un hash muy único.
	 *
	 * @param string String: El string a hashear.
	 *
	 * @return int: El hash a devolver.
	 */
	public int hashMejor(String string) {
		int intLength = string.length() / 4;
		int sum = 0;
		for (int j = 0; j < intLength; j++) {
			char c[] = string.substring(j * 4, (j * 4) + 4).toCharArray();
			int mult = 1;
			for (char aC : c) {
				sum = sum + aC * mult;
				mult = mult * 256;
			}
		}

		char c[] = string.substring(intLength * 4).toCharArray();
		int mult = 1;
		for (char aC : c) {
			sum = sum + aC * mult;
			mult = mult * 256;
		}

		return (Math.abs(sum) % tamano);
	}

	/**
	 * Este metodo crea un hash usando una llave.
	 *
	 * @param string String: El string a hashear.
	 *
	 * @return int: El hash a devolver.
	 */
	@SuppressWarnings("unused")
	public int hash(String string) {
		int hash = 31;
		for (int i = 0; i < string.length(); i++) {
			hash = hash * 31 + string.charAt(i);
		}
		if (hash < 0) {
			hash = hash * - 1;
		}
		return hash % tamano;
	}

	/**
	 * Insertar una llave y valor en la tabla hash.
	 *
	 * @param llave String: La llave para identificar un elemento.
	 * @param valor int: El valor a insertar.
	 *
	 * @return boolean: Verdad si fue insertado, sino está llena la tabla hash.
	 */
	public boolean insertar(String llave, int valor) {
		HashItem hashItem = new HashItem(llave, valor);
		int hashIndice = hashMejor(hashItem.getLlave());
		int i = 0;
		while (hashArray[hashIndice] != null && hashArray[hashIndice].getLlave() != null && i < tamano) {
			if (hashArray[hashIndice].getLlave().equals(llave)) {
				return false;
			}
			hashIndice++;
			hashIndice = hashIndice % tamano;
			i++;
		}
		if (i == 10) {
			return false;
		}
		else {
			hashItem.setIndice(hashIndice);
			hashArray[hashIndice] = hashItem;
			size++;
			return true;
		}
	}

	/**
	 * Eliminar un elemento de la tabla hash.
	 *
	 * @param llave String: La llave a elminar.
	 *
	 * @return boolean: Verdad si fue borrado, sino no existiá.
	 */
	public boolean eliminar(String llave) {
		HashItem hashItem = new HashItem(llave, 0);
		int hashIndice = hashMejor(hashItem.getLlave());
		int i = 0;
		while (hashArray[hashIndice] != null && hashArray[hashIndice].getLlave() != null && i < tamano) {
			if (hashArray[hashIndice].getLlave().equals(llave)) {
				hashArray[hashIndice] = null;
				size--;
				return true;
			}
			hashIndice++;
			hashIndice = hashIndice % tamano;
			i++;
		}
		return false;
	}

	/**
	 * Buscar una llave en la tabla hash.
	 *
	 * @param llave String: La llave a buscar.
	 *
	 * @return HashItem: Devolver el elemento que contine la llave.
	 */
	public HashItem buscar(String llave) {
		for (int i = 0; i < tamano; i++) {
			if (hashArray[i] != null && hashArray[i].getLlave().equals(llave)) {
				return hashArray[i];
			}
		}
		return null;
	}

	/**
	 * Devolver la cantidad de elementos que están en la tabla.
	 *
	 * @return int: La cantidad.
	 */
	public int size() {
		return size;
	}

	/**
	 * Devolver el valor que está guardado en cada indice. Se usa para construir la grafica.
	 *
	 * @param indice int: El indice que desea ver.
	 *
	 * @return String: El valor que está en dicho indice.
	 */
	public HashItem getIndice(int indice) {
		if (indice >= 0 && indice < hashArray.length) {
			return hashArray[indice];
		}
		else {
			return null;
		}
	}
}
