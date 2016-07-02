package cl.cromer.estructuras;

public class HashTable {
    private HashItem hashArray[];
    private int tamano;
    private int size;

    public HashTable(int tamano) {
        this.tamano = tamano;
        hashArray = new HashItem[tamano];
    }

    public int size() {
        return size;
    }

    public int hashMejor(String string) {
        int intLength = string.length() / 4;
        int sum = 0;
        for (int j = 0; j < intLength; j++) {
            char c[] = string.substring(j * 4, (j * 4) + 4).toCharArray();
            int mult = 1;
            for (char aC : c) {
                sum = sum + aC * mult;
                mult = mult * 256;
            }
        }

        char c[] = string.substring(intLength * 4).toCharArray();
        int mult = 1;
        for (char aC : c) {
            sum = sum + aC * mult;
            mult = mult * 256;
        }

        return (Math.abs(sum) % tamano);
    }

    public int hash(String string) {
        int  hash = 31;
        for (int i = 0; i < string.length(); i++) {
            hash = hash * 31 + string.charAt(i);
        }
        if (hash < 0) {
            hash = hash * -1;
        }
        return hash % tamano;
    }

    public boolean insertar(String llave, int valor) {
        HashItem hashItem = new HashItem(llave, valor);
        int hashIndice = hash(hashItem.getLlave());
        int i = 0;
        while (hashArray[hashIndice] != null && hashArray[hashIndice].getLlave() != null && i < tamano) {
            if (hashArray[hashIndice].getLlave().equals(llave)) {
                return false;
            }
            hashIndice++;
            hashIndice = hashIndice % tamano;
            i++;
        }
        if (i == 10) {
            return false;
        }
        else {
            hashItem.setIndice(hashIndice);
            hashArray[hashIndice] = hashItem;
            size++;
            return true;
        }
    }

    public boolean eliminar(String llave) {
        HashItem hashItem = new HashItem(llave, 0);
        int hashIndice = hash(hashItem.getLlave());
        int i = 0;
        while (hashArray[hashIndice] != null && hashArray[hashIndice].getLlave() != null && i < tamano) {
            if (hashArray[hashIndice].getLlave().equals(llave)) {
                hashArray[hashIndice] = null;
                return true;
            }
            hashIndice++;
            hashIndice = hashIndice % tamano;
            i++;
        }
        return false;
    }

    public HashItem buscar(String llave) {
        for (int i = 0; i < tamano; i++) {
            if (hashArray[i] != null && hashArray[i].getLlave().equals(llave)) {
                return hashArray[i];
            }
        }
        return null;
    }

    /**
     * Devolver el valor que está guardado en cada indice. Se usa para construir la grafica.
     *
     * @param indice int: El indice que desea ver.
     * @return String: El valor que está en dicho indice.
     */
    public HashItem getIndice(int indice) {
        if (indice >= 0 && indice < hashArray.length) {
            return hashArray[indice];
        }
        else {
            return null;
        }
    }
}
