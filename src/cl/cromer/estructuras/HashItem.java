package cl.cromer.estructuras;

public class HashItem {
    private int indice;
    private String llave;
    private int valor;

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