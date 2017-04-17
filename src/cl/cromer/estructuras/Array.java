package cl.cromer.estructuras;

import java.util.Random;

/**
 * Crear una estructura de dato de tipo array.
 *
 * @author Chris Cromer
 */
final public class Array {
	/**
	 * El array.
	 */
	final private String array[];

	/**
	 * La cantidad de elementos en el array.
	 */
	private int size;

	/**
	 * Si es de tipo ordenado o simple.
	 */
	private boolean ordered;

	/**
	 * Crear el array con el tamaño pasador por argumento.
	 *
	 * @param tamano int: El tamaño del array a crear.
	 */
	public Array(int tamano) {
		this.array = new String[tamano];
		size = 0;
		ordered = false;
	}

	/**
	 * Dovolver si el tipo es ordenado o no.
	 *
	 * @return boolean: Si el tipo de array es ordenado.
	 */
	public boolean isOrdered() {
		return ordered;
	}

	/**
	 * Cambiar el tipo de array entre ordenado o simple.
	 *
	 * @param ordered boolean: Si es verdad, es de tipo ordenado, sino el tipo es simple.
	 */
	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}

	/**
	 * Insertar un valor al array.
	 *
	 * @param valor int: El valor a insertar.
	 *
	 * @return boolean: Verdad si fue exitoso, sino falso.
	 */
	public boolean insertar(int valor) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				array[i] = String.valueOf(valor);
				size++;
				return true;
			}
			else if (array[i].equals(String.valueOf(valor))) {
				// Ya existe el valor en el array
				return false;
			}
		}
		return false;
	}

	/**
	 * Eliminar un valor del array si existe.
	 *
	 * @param valor int: El valor a eliminar.
	 *
	 * @return boolean: Verdad si fue encontrado y borrado, sino falso.
	 */
	public boolean eliminar(int valor) {
		boolean borrado = false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i].equals(String.valueOf(valor))) {
				// Eliminar el valor
				array[i] = null;
				borrado = true;
				size--;
				if (ordered) {
					for (int j = i; j < array.length; j++) {
						if (j != array.length - 1) {
							// Correr la array hacia arriba
							array[j] = array[j + 1];
						}
					}
					array[array.length - 1] = null;
				}
				else {
					break;
				}
			}
		}
		return borrado;
	}

	/**
	 * Buscar si existe un valor dentro el array.
	 *
	 * @param valor int: Valor a buscar.
	 *
	 * @return int: Devuelve el indice donde fue encontrado, o -1 si no fue encontrado.
	 */
	public int buscar(int valor) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i].equals(String.valueOf(valor))) {
				// Se encontró
				return i;
			}
		}
		// No se encontró
		return - 1;
	}

	/**
	 * Borrar el array para que sea como nuevo.
	 */
	public void nuevo() {
		for (int i = 0; i < size(); i++) {
			array[i] = null;
		}
		size = 0;
	}

	/**
	 * Llenar el array con valores al azar.
	 */
	public void llenar() {
		Random random = new Random();
		int maximo = 99;
		int minimo = 0;
		int rango = maximo - minimo + 1;

		for (int i = size(); i < 10; i++) {
			int numero = random.nextInt(rango) + minimo;
			while (buscar(numero) != - 1) {
				numero = random.nextInt(rango) + minimo;
			}
			insertar(numero);
		}
	}

	/**
	 * Devolver el valor que está guardado en cada indice del array. Se usa para construir la grafica.
	 *
	 * @param indice int: El indice que desea ver.
	 *
	 * @return String: El valor que está en dicho indice.
	 */
	public String getIndice(int indice) {
		if (indice >= 0 && indice < array.length) {
			return array[indice];
		}
		else {
			return null;
		}
	}

	/**
	 * Devolver la cantidad de elementos en el array.
	 *
	 * @return int: Devolver la cantidad de elementos en el array.
	 */
	public int size() {
		return size;
	}

	/**
	 * Ordenar el array usando burbuja.
	 *
	 * @param paso boolean: Si es verdad, solo hago en paso del ordenamiento, sino ordenear todos los elementos.
	 *
	 * @return boolean: Verdad si algo cambió, sino falso.
	 */
	public boolean burbuja(boolean paso) {
		boolean cambio = false;

		for (int i = size() - 1; i > 1; i--) {
			for (int j = 0; j < i; j++) {
				if (Integer.valueOf(array[j]) > Integer.valueOf(array[j + 1])) {
					String temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					cambio = true;
					if (paso) {
						return true;
					}
				}
			}
		}

		return cambio;
	}

	/**
	 * Ordenar el array usando inserción.
	 *
	 * @param paso boolean: Si es verdad, solo hago en paso del ordenamiento, sino ordenear todos los elementos.
	 *
	 * @return boolean: Verdad si algo cambió, sino falso.
	 */
	public boolean insercion(boolean paso) {
		boolean cambio = false;

		for (int i = 1; i < size(); i++) {
			String temp = array[i];
			int j = i;
			while (j > 0 && Integer.valueOf(array[j - 1]) >= Integer.valueOf(temp)) {
				array[j] = array[j - 1];
				-- j;
				cambio = true;
			}
			array[j] = temp;
			if (paso && cambio) {
				return true;
			}
		}

		return cambio;
	}

	/**
	 * Ordenar el array usando selección.
	 *
	 * @param paso boolean: Si es verdad, solo hago en paso del ordenamiento, sino ordenear todos los elementos.
	 *
	 * @return boolean: Verdad si algo cambió, sino falso.
	 */
	public boolean seleccion(boolean paso) {
		boolean cambio = false;

		for (int i = 0; i < size() - 1; i++) {
			int minimo = i;
			for (int j = i + 1; j < size(); j++) {
				if (Integer.valueOf(array[j]) < Integer.valueOf(array[minimo])) {
					minimo = j;
					cambio = true;
				}
			}
			String temp = array[i];
			array[i] = array[minimo];
			array[minimo] = temp;
			if (paso && cambio) {
				return true;
			}
		}

		return cambio;
	}

	/**
	 * Ordenar el array usando shell.
	 *
	 * @param paso boolean: Si es verdad, solo hago en paso del ordenamiento, sino ordenear todos los elementos.
	 *
	 * @return boolean: Verdad si algo cambió, sino falso.
	 */
	public boolean shell(boolean paso) {
		boolean cambio = false;

		int j, i;
		String temp;

		int h = 1;
		while (h <= size() / 3) {
			h = h * 3 + 1;
		}

		while (h > 0) {
			for (i = h; i < size(); i++) {
				temp = array[i];
				j = i;
				while (j > h - 1 && Integer.valueOf(array[j - h]) >= Integer.valueOf(temp)) {
					array[j] = array[j - h];
					j -= h;
					cambio = true;
				}
				array[j] = temp;
				if (paso && cambio) {
					return true;
				}
			}
			h = (h - 1) / 3;
		}

		return cambio;
	}

	/**
	 * Ordenar el array usando quick.
	 *
	 * @param paso boolean: Si es verdad, solo hago en paso del ordenamiento, sino ordenear todos los elementos.
	 *
	 * @return boolean: Verdad si algo cambió, sino falso.
	 */
	public boolean quick(boolean paso) {
		String array[] = this.array.clone();
		boolean cambio = false;
		boolean cambio2;
		cambio2 = recurenciaQuick(0, size() - 1, paso);
		for (int i = 0; i < size(); i++) {
			if (! array[i].equals(this.array[i])) {
				cambio = true;
			}
		}
		return (cambio || cambio2);
	}

	/**
	 * Metodo recursivo para ordenar using quick sort.
	 *
	 * @param izquerda int: La posición del quick desded la izquerda.
	 * @param derecha int: La posición del quick desded la derecha..
	 * @param paso boolean: Si es verdad, solo hago en paso del ordenamiento, sino ordenear todos los elementos.
	 *
	 * @return boolean: Verdad si algo cambió, sino falso.
	 */
	private boolean recurenciaQuick(int izquerda, int derecha, boolean paso) {
		boolean cambio;
		boolean cambio2;

		if (derecha - izquerda <= 0) {
			return false;
		}
		else {
			String pivot = array[derecha];

			ParticionarResult particionarResult = particionar(izquerda, derecha, pivot);
			if (paso && particionarResult.getCambio()) {
				return particionarResult.getCambio();
			}
			cambio = recurenciaQuick(izquerda, particionarResult.getPunteroIzquerda() - 1, paso);
			cambio2 = recurenciaQuick(particionarResult.getPunteroIzquerda() + 1, derecha, paso);
			return (paso && (cambio || cambio2));
		}
	}

	/**
	 * Particionar el array desded la izquerda y derecho usando un pivot.
	 *
	 * @param izquerda int: La posición del quick desde la izquerda.
	 * @param derecha int: La posición del quick desde la derecha.
	 * @param pivot String: El valor a comparar con los otros.
	 *
	 * @return ParticionarResult: Los resultados de particionar.
	 */
	private ParticionarResult particionar(int izquerda, int derecha, String pivot) {
		boolean cambio = false;

		int punteroIzquerda = izquerda - 1;
		int punteroDerecha = derecha;
		while (true) {
			//noinspection StatementWithEmptyBody
			while (Integer.valueOf(array[++ punteroIzquerda]) < Integer.valueOf(pivot)) {
			}
			//noinspection StatementWithEmptyBody
			while (punteroDerecha > 0 && Integer.valueOf(array[-- punteroDerecha]) > Integer.valueOf(pivot)) {
			}

			if (punteroIzquerda >= punteroDerecha) {
				break;
			}
			else {
				String temp = array[punteroIzquerda];
				array[punteroIzquerda] = array[punteroDerecha];
				array[punteroDerecha] = temp;
				cambio = true;
			}
		}
		String temp = array[punteroIzquerda];
		array[punteroIzquerda] = array[derecha];
		array[derecha] = temp;

		return new ParticionarResult(cambio, punteroIzquerda);
	}

	/**
	 * Ordenar el array usando merge.
	 *
	 * @param paso boolean: Si es verdad, solo hago en paso del ordenamiento, sino ordenear todos los elementos.
	 *
	 * @return boolean: Verdad si algo cambió, sino falso.
	 */
	public boolean merge(boolean paso) {
		String array[] = this.array.clone();
		boolean cambio = false;
		boolean cambio2;
		String[] temp = new String[size()];
		cambio2 = recurenciaMerge(temp, 0, size() - 1, paso);
		for (int i = 0; i < size(); i++) {
			if (! array[i].equals(this.array[i])) {
				cambio = true;
			}
		}
		return (cambio || cambio2);
	}

	/**
	 * El metodo recursivo para ordenar con merge.
	 *
	 * @param temp String[]: El array temporario para trabajar.
	 * @param izquerda int: El lado izquerda.
	 * @param derecha int: El lado derecha.
	 * @param paso boolean: Verdad si es paso por paso.
	 *
	 * @return boolean: Devolver si algo cambió.
	 */
	private boolean recurenciaMerge(String[] temp, int izquerda, int derecha, boolean paso) {
		if (izquerda != derecha) {
			boolean cambio;
			boolean cambio2;
			boolean cambio3;

			int medio = (izquerda + derecha) / 2;
			cambio = recurenciaMerge(temp, izquerda, medio, paso);
			if (paso && cambio) {
				return true;
			}
			cambio2 = recurenciaMerge(temp, medio + 1, derecha, paso);
			if (paso && cambio2) {
				return true;
			}
			cambio3 = merge(temp, izquerda, medio + 1, derecha, paso);
			return (paso && cambio3);
		}
		else {
			return false;
		}
	}

	/**
	 * Este metodo hace los cambios al array.
	 *
	 * @param temp String[]: El array temporario para trabajar.
	 * @param prevIzquerda int: El valor previo de la izquerda.
	 * @param prevMedio int: El valor previo al medio.
	 * @param prevDerecha int: El valor previo de la derecha.
	 * @param paso boolean: Si es paso por paso.
	 *
	 * @return boolean: Devolver si algo cambió.
	 */
	private boolean merge(String[] temp, int prevIzquerda, int prevMedio, int prevDerecha, boolean paso) {
		boolean cambio = false;
		int j = 0;
		int izquerda = prevIzquerda;
		int medio = prevMedio - 1;
		int derecha = prevDerecha - izquerda + 1;

		while (prevIzquerda <= medio && prevMedio <= prevDerecha) {
			if (Integer.valueOf(array[prevIzquerda]) < Integer.valueOf(array[prevMedio])) {
				temp[j++] = array[prevIzquerda++];
			}
			else {
				temp[j++] = array[prevMedio++];
			}
		}

		while (prevIzquerda <= medio) {
			temp[j++] = array[prevIzquerda++];
		}

		while (prevMedio <= prevDerecha) {
			temp[j++] = array[prevMedio++];
		}

		for (j = 0; j < derecha; j++) {
			String temp2 = array[izquerda + j];
			array[izquerda + j] = temp[j];
			if (paso && ! array[izquerda + j].equals(temp2)) {
				cambio = true;
			}
		}
		return cambio;
	}

	/**
	 * Esta clase contiene los resultados de Partricionar.
	 */
	final public class ParticionarResult {
		/**
		 * Si habia algun cambio.
		 */
		final private boolean cambio;

		/**
		 * La parte izquerda que cambió.
		 */
		final private int punteroIzquerda;

		/**
		 * Inicializar.
		 *
		 * @param cambio boolean: Si habia un cambio o no.
		 * @param punteroIzquerda: El valor desde la izquerda donde fue un cambio.
		 */
		public ParticionarResult(boolean cambio, int punteroIzquerda) {
			this.cambio = cambio;
			this.punteroIzquerda = punteroIzquerda;
		}

		/**
		 * Devolver el cambio.
		 *
		 * @return boolean: Devolver el valor de cambio.
		 */
		public boolean getCambio() {
			return cambio;
		}

		/**
		 * Devolver el puntero izquerda.
		 *
		 * @return int: Devolver el valor de puntero.
		 */
		public int getPunteroIzquerda() {
			return punteroIzquerda;
		}
	}

	/**
	 * Esta clase contiene los arbolTipo de array.
	 *
	 * @author Chris Cromer
	 */
	final static public class Tipos {
		/**
		 * Tipo de array simple.
		 */
		static final public int SIMPLE = 0;

		/**
		 * Tipo de array ordenado.
		 */
		static final public int ORDENADO = 1;

		/**
		 * El tipo que está elegido.
		 */
		final private int tipo;

		/**
		 * Inicilizar el tipo.
		 *
		 * @param tipo int: Tipo de array, {@value #SIMPLE} o {@value #ORDENADO}
		 */
		public Tipos(int tipo) {
			switch (tipo) {
				case SIMPLE:
					this.tipo = SIMPLE;
					break;
				case ORDENADO:
					this.tipo = ORDENADO;
					break;
				default:
					this.tipo = SIMPLE;
			}
		}

		/**
		 * Devolver el tipo.
		 *
		 * @return int: El tipo de array.
		 */
		public int getTipo() {
			return tipo;
		}
	}
}
