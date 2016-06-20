package cl.cromer.estructuras;

/**
 * Crear una estructura de dato de tipo array.
 * @author Chris Cromer
 */
public class Array {
    /**
     * El array.
     */
    private String array[];

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
     * @param temano int: El temaño del array a crear.
     */
    public Array(int temano) {
        this.array = new String[temano];
        size = 0;
        ordered = false;
    }

    /**
     * Devolver la cantidad de elementos en el array.
     * @return int: Devolver la cantidad de elementos en el array.
     */
    public int size() {
        return size;
    }

    /**
     * Dovolver si el tipo es ordenado o no.
     * @return boolean: Si el tipo de array es ordenado.
     */
    public boolean isOrdered() {
        return ordered;
    }

    /**
     * Cambiar el tipo de array entre ordenado o simple.
     * @param ordered boolean: Si es verdad, es de tipo ordenado, sino el tipo es simple.
     */
    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    /**
     * Insertar un valor al array.
     * @param valor int: El valor a insertar.
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
     * @param valor int: El valor a eliminar.
     * @return boolean: Verdad si fue encontrado y borrado, sino falso.
     */
    public boolean eliminar(int valor) {
        boolean borrado = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(String.valueOf(valor))) {
                // Eliminar el valor
                array[i] = null;
                borrado=true;
                size--;
                if (ordered) {
                    for (int j = i; j < array.length; j++) {
                        if (j != array.length - 1) {
                            // Correr la array hacia arriba
                            array[j] = array[j + 1];
                        }
                    }
                    array[array.length-1] = null;
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
     * @param valor int: Valor a buscar.
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
        return -1;
    }

    /**
     * Devolver el valor que está guardado en cada indice del array. Se usa para construir la grafica.
     * @param indice int: El indice que desea ver.
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
     * Ordenar el array usando burbuja.
     * @param paso boolean: Si es verdad, solo hago en paso del ordenamiento, sino ordenear todos los elementos.
     * @return boolean: Verdad si algo cambió, sino falso.
     */
    public boolean burbuja(boolean paso) {
        boolean cambio = false;

        for (int i = size() - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (Integer.valueOf(array[j]) > Integer.valueOf(array[j+1])) {
                    String temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
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
     * @param paso boolean: Si es verdad, solo hago en paso del ordenamiento, sino ordenear todos los elementos.
     * @return boolean: Verdad si algo cambió, sino falso.
     */
    public boolean insercion(boolean paso) {
        boolean cambio = false;

        for (int i = 1; i < size(); i++) {
            String temp = array[i];
            int j = i;
            while (j > 0 && Integer.valueOf(array[j-1]) >= Integer.valueOf(temp)) {
                array[j] = array[j-1];
                --j;
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
     * @param paso boolean: Si es verdad, solo hago en paso del ordenamiento, sino ordenear todos los elementos.
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
}
