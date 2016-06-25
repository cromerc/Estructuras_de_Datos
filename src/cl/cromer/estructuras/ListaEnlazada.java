package cl.cromer.estructuras;

public class ListaEnlazada {
    private Enlace lista;

    private int size;

    private int tipo;

    public ListaEnlazada() {
        lista = null;
    }

    public int size() {
        return size;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Enlace buscar(int llave) {
        if (this.lista != null) {
            // La lista no es vacia
            Enlace lista = this.lista;
            while (lista.getLlave() != llave) {
                // Buscar hasta la llave es encontraddo
                if (lista.getSiguente() != null) {
                    // Buscar en la sigenute enlace
                    lista = lista.getSiguente();
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

    public boolean insertar(int llave, int valor) {
        if (buscar(llave) == null) {
            // Crear una enlace y agregarla a la lista
            Enlace nueva = new Enlace();
            nueva.setLlave(llave);
            nueva.setValor(valor);
            nueva.setSiguente(lista);
            lista = nueva;
            size++;
            return true;
        }
        else {
            // Se falló porque la llave ya existe
            return false;
        }
    }

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
                    lista = lista.getSiguente();
                }
                else {
                    // No se encuentra
                    return false;
                }
            }
            // Se encontró
            if (lista == this.lista) {
                // Si es la primera enlace, cambiarla al siguente enlace
                this.lista = this.lista.getSiguente();
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
                lista = lista.getSiguente();
                i--;
            }
            return lista;
        }
        else {
            return null;
        }
    }

    // Estructura de enlaces
    public class Enlace {
        private int llave;
        private int valor;
        private Enlace siguente;

        protected Enlace() {
            siguente = null;
        }

        protected int getLlave() {
            return llave;
        }

        protected void setLlave(int llave) {
            this.llave = llave;
        }

        protected int getValor() {
            return valor;
        }

        protected void setValor(int valor) {
            this.valor = valor;
        }

        protected Enlace getSiguente() {
            return siguente;
        }

        protected void setSiguente(Enlace siguente) {
            this.siguente = siguente;
        }
    }
}

