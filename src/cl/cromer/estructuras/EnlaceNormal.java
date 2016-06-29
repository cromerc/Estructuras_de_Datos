package cl.cromer.estructuras;

/**
 * Esta clase es de tipo de enlace normal.
 *
 * @author Chris Cromer
 */
final public class EnlaceNormal implements Enlace {
    /**
     * La llave.
     */
    private int llave;

    /**
     * El siguente enlace.
     */
    private EnlaceNormal siguente;

    /**
     * El enlace previo por doble enlazada.
     */
    private EnlaceNormal previo;

    /**
     * Incializar.
     */
    public EnlaceNormal() {
        siguente = null;
        previo = null;
    }

    /**
     * Devolver la llave.
     *
     * @return int: La llave.
     */
    public int getLlave() {
        return llave;
    }

    /**
     * Cambiar el valor de la llave.
     *
     * @param llave int: El valor de la llave.
     */
    public void setLlave(int llave) {
        this.llave = llave;
    }

    /**
     * Devolver el siguente enlace.
     *
     * @return EnlaceNormal: El enlace a devolver.
     */
    public EnlaceNormal getSiguente() {
        return siguente;
    }

    /**
     * Cambiar el siguiente enlace.
     *
     * @param siguente Object: El siguente enlace nuevo de tipo {@link EnlaceNormal}.
     */
    public void setSiguente(Object siguente) {
        this.siguente = (EnlaceNormal) siguente;
    }

    /**
     * Devolver el enlace previo.
     *
     * @return EnlaceNormal: El enlace previo.
     */
    public EnlaceNormal getPrevio() {
        return previo;
    }

    /**
     * Cambiar el previo enlace.
     *
     * @param previo Object: El enlace previo nuevo de tipo {@link EnlaceNormal}.
     */
    public void setPrevio(Object previo) {
        this.previo = (EnlaceNormal) previo;
    }
}