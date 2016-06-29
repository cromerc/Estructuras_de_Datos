package cl.cromer.estructuras;

/**
 * Esta interfaz es para los 2 tipos de enlace, {@link EnlaceNormal} y {@link EnlaceCircular}.
 *
 * @author Chris Cromer
 */
interface Enlace {
    /**
     * Devolver la llave.
     *
     * @return int: La llave.
     */
    int getLlave();

    /**
     * Cambiar el valor de la llave.
     *
     * @param llave int: El valor de la llave.
     */
    void setLlave(int llave);

    /**
     * Devolver el siguente enlace.
     *
     * @return Object: El enlace a devolver.
     */
    Object getSiguente();

    /**
     * Cambiar el siguiente enlace.
     *
     * @param siguente Object: El siguente enlace nuevo.
     */
    void setSiguente(Object siguente);

    /**
     * Devolver el enlace previo.
     *
     * @return Object: El enlace previo.
     */
    Object getPrevio();

    /**
     * Cambiar el previo enlace.
     *
     * @param previo Object: El enlace previo nuevo.
     */
    void setPrevio(Object previo);
}