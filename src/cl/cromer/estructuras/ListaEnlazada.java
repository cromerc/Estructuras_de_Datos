package cl.cromer.estructuras;

/**
 * Crear una estructura de dato de tipo lista enlazada.
 * @author Chris Cromer
 */
final public class ListaEnlazada {
    /**
     * El enlace principal de la lista.
     */
    private Enlace lista;

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
     * Devolver la cantidad de enlaces que están en la lista.
     * @return int: La cantidad.
     */
    public int size() {
        return size;
    }

    /**
     * Devolver el tipo de lista.
     * @return int: El tipo.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Cambiar el tipo de lista.
     * @param tipo int: El tipo a cambiar.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Buscar una llave en la lista.
     * @param llave int: La llave a buscar.
     * @return Enlace: El enlace que contiene la llave buscada.
     */
    public Enlace buscar(int llave) {
        if (this.lista != null) {
            // La lista no es vacia
            Enlace lista = this.lista;
            while (lista.getLlave() != llave) {
                // Buscar hasta la llave es encontraddo
                if (lista.getSiguente() != null) {
                    // Buscar en la sigenute enlace
                    lista = (Enlace) lista.getSiguente();
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
     * Insertar una llave en la lista.
     * @param llave int: La llave a insertar.
     * @return boolean: Verdad si fue insertado, sino falso.
     */
    public boolean insertar(int llave) {
        if (buscar(llave) == null) {
            // Crear una enlace y agregarla a la lista
            Enlace nueva = new EnlaceNormal();
            nueva.setLlave(llave);
            nueva.setSiguente(lista);
            if (lista != null) {
                lista.setPrevio(nueva);
            }
            lista = nueva;
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
     * @param llave int: La llave a eliminar.
     * @return boolean: Verdad si fue borrado, sino falso.
     */
    public boolean eliminar(int llave) {
        if (lista != null) {
            // La lista no es vacia
            Enlace lista = this.lista;
            Enlace previo = lista;
            while (lista.getLlave() != llave) {
                // Buscar hasta la llave es encontraddo
                if (lista.getSiguente() != null) {
                    // Buscar en la sigenute enlace
                    previo = lista;
                    lista = (Enlace) lista.getSiguente();
                }
                else {
                    // No se encuentra
                    return false;
                }
            }
            // Se encontró
            if (lista == this.lista) {
                // Si es la primera enlace, cambiarla al siguente enlace
                this.lista = (Enlace) this.lista.getSiguente();
                if (this.lista.getPrevio() != null) {
                    this.lista.setPrevio(null);
                }
            }
            else {
                // Sino cortar esta enlace de la lista
                previo.setSiguente(lista.getSiguente());
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
     * Devolver un enlace con su llave y valor.
     * @param indice int: El indice que desea ver.
     * @return Enlace: El enlace a devolver.
     */
    public Enlace getIndice(int indice) {
        if (lista != null && indice >= 0 && indice < size()) {
            int i = size();
            Enlace lista = this.lista;
            while (i > indice + 1) {
                lista = (Enlace) lista.getSiguente();
                i--;
            }
            return lista;
        }
        else {
            return null;
        }
    }
}

