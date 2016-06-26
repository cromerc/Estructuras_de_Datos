package cl.cromer.estructuras;

/**
 * Esta clase es de tipo de enlace circular.
 * @author Chris Cromer
 */
final public class EnlaceCircular implements Enlace {
    /**
     * La llave.
     */
    private int llave;

    /**
     * El siguente enlace.
     */
    private EnlaceCircular siguente;

    /**
     * Incializar.
     */
    public EnlaceCircular() {
        siguente = null;
    }

    /**
     * Devolver la llave.
     * @return int: La llave.
     */
    public int getLlave() {
        return llave;
    }

    /**
     * Cambiar el valor de la llave.
     * @param llave int: El valor de la llave.
     */
    public void setLlave(int llave) {
        this.llave = llave;
    }

    /**
     * Devolver el siguente enlace.
     * @return EnlaceCircular: El enlace a devolver.
     */
    public EnlaceCircular getSiguente() {
        return siguente;
    }

    /**
     * Cambiar el siguiente enlace.
     * @param siguente Object: El siguente enlace nuevo de tipo {@link EnlaceCircular}.
     */
    public void setSiguente(Object siguente) {
        this.siguente = (EnlaceCircular) siguente;
    }

    /**
     * Devolver el enlace previo.
     * @return EnlaceCircular: El enlace previo.
     */
    public EnlaceCircular getPrevio() {
        return null;
    }

    /**
     * Dummy metodo para usar interface {@link Enlace}
     * @param previo Object: El enlace previo nuevo de tipo {@link EnlaceCircular}.
     */
    public void setPrevio(Object previo) {
    }
}