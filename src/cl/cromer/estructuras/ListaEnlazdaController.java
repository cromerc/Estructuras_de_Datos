package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Esta clase es para controlar todos la interfaz de ListaEnlazada.
 *
 * @author Chris Cromer
 */
public class ListaEnlazdaController implements Initializable {
    /**
     * La caja para ingresar el valor.
     */
    @FXML
    private TextFieldLimited valorLista;

    /**
     * Donde poner el contenido de lista.
     */
    @FXML
    private VBox contenidoLista;

    /**
     * Donde poner el contenido de lista circular.
     */
    @FXML
    private VBox contenidoListaCircular;

    /**
     * Donde va el codigo a mostrar a la pantalla.
     */
    @FXML
    private Text codigoLista;

    /**
     * La escena donde está cosas graficas.
     */
    private Scene scene;

    /**
     * Donde está guardado los idiomas.
     */
    private ResourceBundle resourceBundle;

    /**
     * La lista enlazada usado en la aplicación.
     */
    private ListaEnlazada listaEnlazada;

    /**
     * La lista enlazada circular usado en la aplicación.
     */
    private ListaEnlazadaCircular listaEnlazadaCircular;

    /**
     * Tipo de lista enlazada a trabajar.
     */
    private ListaEnlazadaTipos listaEnlazadaTipos;

    /**
     * Grafico rectangulos y lineas.
     */
    private Grafico grafico;

    /**
     * Colores por los dibjos.
     */
    private Colores colores;

    /**
     * Inicializar todos los datos y dibujar las graficas.
     *
     * @param location       URL: El URL de fxml en uso.
     * @param resourceBundle ResourceBundle: Tiene datos de idioma.
     */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;

        scene = null;
    }

    /**
     * Llenar la lista con numeros al azar.
     */
    @FXML
    protected void botonLlenar() {
        if (scene == null) {
            initializeLista();
        }

        Random random = new Random();
        int maximo = 99;
        int minimo = 0;
        int rango = maximo - minimo + 1;

        if (listaEnlazadaTipos.getTipo() != ListaEnlazadaTipos.CIRCULAR) {
            for (listaEnlazada.size(); listaEnlazada.size() < 5; ) {
                int numero = random.nextInt(rango) + minimo;
                while (listaEnlazada.buscar(numero) != null) {
                    numero = random.nextInt(rango) + minimo;
                }
                listaEnlazada.insertar(numero);
            }
        }
        else {
            for (listaEnlazadaCircular.size(); listaEnlazadaCircular.size() < 5; ) {
                int numero = random.nextInt(rango) + minimo;
                while (listaEnlazadaCircular.buscar(numero) != null) {
                    numero = random.nextInt(rango) + minimo;
                }
                listaEnlazadaCircular.insertar(numero);
            }
        }
        generarGrafico();
    }

    /**
     * Vaciar la lista de todos los valores.
     */
    @FXML
    protected void botonVaciar() {
        if (scene == null) {
            initializeLista();
        }

        if (listaEnlazadaTipos.getTipo() != ListaEnlazadaTipos.CIRCULAR) {
            listaEnlazada = new ListaEnlazada();
            listaEnlazada.setTipo(listaEnlazadaTipos.getTipo());
        }
        else {
            listaEnlazadaCircular = new ListaEnlazadaCircular();
            listaEnlazadaCircular.setTipo(ListaEnlazadaTipos.SIMPLE);
        }
        generarGrafico();
    }

    /**
     * Insertar un valor a la lista y mostrar el codigo en la pantalla.
     */
    @FXML
    protected void botonInsertar() {
        if (scene == null) {
            initializeLista();
        }

        String tipo;
        switch (listaEnlazadaTipos.getTipo()) {
            case ListaEnlazadaTipos.SIMPLE:
                tipo = "Simple";
                break;
            case ListaEnlazadaTipos.CIRCULAR:
                tipo = "Circular";
                break;
            case ListaEnlazadaTipos.DOBLEMENTE_ENLAZADA:
                tipo = "Doble";
                break;
            default:
                tipo = "Simple";
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/listaEnlazada" + tipo + "/insertar")).useDelimiter("\\Z").next();
        codigoLista.setText(codigoTexto);

        if (valorLista.getText() != null && !valorLista.getText().trim().equals("")) {
            try {
                boolean exito;
                if (listaEnlazadaTipos.getTipo() != ListaEnlazadaTipos.CIRCULAR) {
                    exito = listaEnlazada.insertar(Integer.valueOf(valorLista.getText()));
                }
                else {
                    exito = listaEnlazadaCircular.insertar(Integer.valueOf(valorLista.getText()));
                }
                if (exito) {
                    valorLista.setText("");
                    generarGrafico();
                }
                else {
                    ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitle(resourceBundle.getString("error"));
                    dialog.setContentText(resourceBundle.getString("listaLlaveExiste"));
                    dialog.getDialogPane().getButtonTypes().add(botonCerrar);
                    Main.setIcon(dialog, getClass());
                    dialog.show();
                }
            }
            catch (NumberFormatException exception) {
                // El error no es fatal, sigue
                Logs.log(Level.WARNING, "No es tipo int.");
                errorNoValor();
            }
        }
        else {
            errorNoValor();
        }
    }

    /**
     * Eliminar un valor de la lista si existe y mostrar el codigo en la pantalla.
     */
    @FXML
    protected void botonEliminar() {
        if (scene == null) {
            initializeLista();
        }

        String tipo;
        switch (listaEnlazadaTipos.getTipo()) {
            case ListaEnlazadaTipos.SIMPLE:
                tipo = "Simple";
                break;
            case ListaEnlazadaTipos.CIRCULAR:
                tipo = "Circular";
                break;
            case ListaEnlazadaTipos.DOBLEMENTE_ENLAZADA:
                tipo = "Doble";
                break;
            default:
                tipo = "Simple";
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/listaEnlazada" + tipo + "/eliminar")).useDelimiter("\\Z").next();
        codigoLista.setText(codigoTexto);

        try {
            if (valorLista.getText() != null && !valorLista.getText().trim().equals("")) {
                boolean exito;
                if (listaEnlazadaTipos.getTipo() != ListaEnlazadaTipos.CIRCULAR) {
                    exito = listaEnlazada.eliminar(Integer.valueOf(valorLista.getText()));
                }
                else {
                    exito = listaEnlazadaCircular.eliminar(Integer.valueOf(valorLista.getText()));
                }
                if (exito) {
                    valorLista.setText("");
                    generarGrafico();
                }
                else {
                    errorNoEsta();
                }
            }
            else {
                errorNoValor();
            }
        }
        catch (NumberFormatException exception) {
            // El error no es fatal, sigue
            Logs.log(Level.WARNING, "No es tipo int.");
            errorNoValor();
        }
    }

    /**
     * Buscar si existe una llave en la lista y mostrar el codigo en la pantalla
     * Si existe la llave destacarla.
     */
    @FXML
    protected void botonBuscar() {
        if (scene == null) {
            initializeLista();
        }

        String tipo;
        switch (listaEnlazadaTipos.getTipo()) {
            case ListaEnlazadaTipos.SIMPLE:
                tipo = "Simple";
                break;
            case ListaEnlazadaTipos.CIRCULAR:
                tipo = "Circular";
                break;
            case ListaEnlazadaTipos.DOBLEMENTE_ENLAZADA:
                tipo = "Doble";
                break;
            default:
                tipo = "Simple";
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/listaEnlazada" + tipo + "/buscar")).useDelimiter("\\Z").next();
        codigoLista.setText(codigoTexto);

        try {
            if (valorLista.getText() != null && !valorLista.getText().trim().equals("")) {
                Enlace enlace;
                if (listaEnlazadaTipos.getTipo() != ListaEnlazadaTipos.CIRCULAR) {
                    enlace = listaEnlazada.buscar(Integer.valueOf(valorLista.getText()));
                }
                else {
                    enlace = listaEnlazadaCircular.buscar(Integer.valueOf(valorLista.getText()));
                }
                if (enlace != null) {
                    generarGrafico();
                    grafico = new Grafico(scene);
                    grafico.destacar("#caja_" + enlace.getLlave(), Grafico.RECTANGULO);
                    grafico.destacar("#texto_" + enlace.getLlave(), Grafico.TEXTO);
                }
                else {
                    errorNoEsta();
                }
            }
            else {
                errorNoValor();
            }
        }
        catch (NumberFormatException exception) {
            // El error no es fatal, sigue
            Logs.log(Level.WARNING, "No es tipo int.");
            errorNoValor();
        }
    }

    /**
     * Se muestra un error si la persona no ingresa un valor y una llave en los TextField.
     */
    private void errorNoValor() {
        ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("error"));
        dialog.setContentText(resourceBundle.getString("listaNoValor"));
        dialog.getDialogPane().getButtonTypes().add(botonCerrar);
        Main.setIcon(dialog, getClass());
        dialog.show();
    }

    /**
     * Error cuando la llave no está en la lista.
     */
    private void errorNoEsta() {
        ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("error"));
        dialog.setContentText(resourceBundle.getString("listaNoEsta"));
        dialog.getDialogPane().getButtonTypes().add(botonCerrar);
        Main.setIcon(dialog, getClass());
        dialog.show();
    }

    /**
     * Crear una lista vacia.
     */
    private void initializeLista() {
        scene = contenidoLista.getScene();
        grafico = new Grafico(scene);
        listaEnlazadaTipos = (ListaEnlazadaTipos) scene.getUserData();
        if (listaEnlazadaTipos.getTipo() != ListaEnlazadaTipos.CIRCULAR) {
            listaEnlazada = new ListaEnlazada();
            listaEnlazada.setTipo(listaEnlazadaTipos.getTipo());
        }
        else {
            listaEnlazadaCircular = new ListaEnlazadaCircular();
            listaEnlazadaCircular.setTipo(ListaEnlazadaTipos.SIMPLE);
        }
    }

    /**
     * Poner los valores en el grafico.
     */
    private void generarGrafico() {
        grafico.removerDestacar();
        colores = new Colores();
        contenidoLista.getChildren().clear();
        contenidoListaCircular.getChildren().clear();

        if (listaEnlazadaTipos.getTipo() != ListaEnlazadaTipos.CIRCULAR) {
            for (int i = 0; i < listaEnlazada.size(); i++) {
                Enlace enlace = listaEnlazada.getIndice(i);
                if (listaEnlazada.getTipo() == ListaEnlazadaTipos.SIMPLE) {
                    dibujarSimple(enlace, false);
                }
                else if (listaEnlazada.getTipo() == ListaEnlazadaTipos.DOBLEMENTE_ENLAZADA) {
                    if (i != listaEnlazada.size() - 1) {
                        dibujarDoble(enlace, (i == 0));
                    }
                    else {
                        dibujarSimple(enlace, false);
                    }
                }
                colores.siguinteColor();
            }
        }
        else {
            for (int i = 0; i < listaEnlazadaCircular.size(); i++) {
                Enlace enlace = listaEnlazadaCircular.getIndice(i);
                dibujarSimple(enlace, (i == listaEnlazadaCircular.size() - 1));
                colores.siguinteColor();
            }
            if (listaEnlazadaCircular.size() > 0) {
                contenidoListaCircular.getChildren().addAll(Grafico.crearLineaCircular(listaEnlazadaCircular.size()));
            }
        }
    }

    /**
     * Dibujarlo con una flecha.
     *
     * @param enlace Enlace: El enlace que tiene la llave y valor.
     * @param sinFlecha boolean: Verdad si necesita dibujar una flecha.
     */
    private void dibujarSimple(Enlace enlace, boolean sinFlecha) {
        contenidoLista.getChildren().addAll(
                Grafico.crearCaja(colores, String.valueOf(enlace.getLlave()), String.valueOf(enlace.getLlave()))
        );
        if (!sinFlecha) {
            contenidoLista.getChildren().addAll(
                    Grafico.crearLineaVertical(),
                    Grafico.crearFlechaAbajo()
            );
        }
    }

    /**
     * Dibujarlo con dos flechas.
     *
     * @param enlace Enlace: El enlace que tiene la llave y valor.
     * @param primer boolean: Verdad si es el primer elemento de la lista.
     */
    private void dibujarDoble(Enlace enlace, boolean primer) {
        if (primer) {
            contenidoLista.getChildren().addAll(
                    Grafico.crearFlechaArriba(),
                    Grafico.crearLineaVertical()
            );
        }
        contenidoLista.getChildren().addAll(
                Grafico.crearCaja(colores, String.valueOf(enlace.getLlave()), String.valueOf(enlace.getLlave())),
                Grafico.crearFlechaArriba(),
                Grafico.crearLineaVertical(),
                Grafico.crearFlechaAbajo()
        );
    }
}
