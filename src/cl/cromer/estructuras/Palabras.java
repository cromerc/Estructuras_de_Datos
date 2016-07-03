package cl.cromer.estructuras;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Palabras {
    final private List<String> palabras;

    public Palabras() {
        palabras = new ArrayList<>();
        palabras.add("hola");
        palabras.add("mundo");
        palabras.add("cruel");
        palabras.add("mi");
        palabras.add("tiempo");
        palabras.add("es");
        palabras.add("limitado");
        palabras.add("pero");
        palabras.add("puedo");
        palabras.add("lograr");
        palabras.add("el");
        palabras.add("proyecto");
        palabras.add("si");
        palabras.add("trabajo");
        palabras.add("bien");
        palabras.add("computador");
        palabras.add("test");
        palabras.add("mouse");
        palabras.add("clase");
        palabras.add("software");
        palabras.add("hardware");
        palabras.add("vaso");
        palabras.add("mesa");
        palabras.add("tabla");
        palabras.add("color");
        palabras.add("calor");
        palabras.add("edad");
        palabras.add("olor");
        palabras.add("ganar");
        palabras.add("dormir");
        palabras.add("tomar");
        palabras.add("comer");
        palabras.add("pensar");
        palabras.add("programar");
        palabras.add("hablar");
        palabras.add("sentir");
        palabras.add("perder");
        palabras.add("abrir");
        palabras.add("cerrar");
        palabras.add("mirar");
        palabras.add("agua");
        palabras.add("me");
        palabras.add("llaman");
        palabras.add("gringo");
        palabras.add("loco");
        palabras.add("no");
    }

    public String getPalabra() {
        Random random = new Random();
        int numero = random.nextInt(palabras.size());
        return palabras.get(numero);
    }
}
