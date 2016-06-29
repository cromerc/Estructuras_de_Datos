package cl.cromer.estructuras;

/**
 * Esta clase contiene los tipos de listas enlazadas.
 *
 * @author Chris Cromer
 */
final public class ListaEnlazadaTipos {
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
    public ListaEnlazadaTipos(int tipo) {
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
