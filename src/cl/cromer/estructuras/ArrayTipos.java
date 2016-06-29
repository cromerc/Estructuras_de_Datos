package cl.cromer.estructuras;

/**
 * Esta clase contiene los tipos de array.
 *
 * @author Chris Cromer
 */
final public class ArrayTipos {
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
    public ArrayTipos(int tipo) {
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