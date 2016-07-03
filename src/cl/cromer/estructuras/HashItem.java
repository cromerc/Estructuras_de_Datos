package cl.cromer.estructuras;

public class HashItem {
    final private String llave;

    final private int valor;

    private int indice;

    public HashItem(String llave, int valor) {
        this.llave = llave;
        this.valor = valor;
    }

    public String getLlave() {
        return llave;
    }

    public int getValor() {
        return valor;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
}