package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Esta clase es para controlar todos la interfaz de Burbuja.
 *
 * @author Chris Cromer
 */
public class BurbujaController implements Initializable {
    /**
     * Donde poner el contenido de array.
     */
    @FXML
    private HBox contenidoBurbuja;

    /**
     * Donde va el codigo a mostrar a la pantalla.
     */
    @FXML
    private Text codigoBurbuja;

    /**
     * La escena donde está cosas graficas.
     */
    private Scene scene;

    /**
     * Donde está guardado los idiomas.
     */
    private ResourceBundle resourceBundle;

    /**
     * El array usado en la aplicación.
     */
    private Array array;

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

        Colores colores = new Colores();

        Random random = new Random();
        int maximo = 99;
        int minimo = 0;
        int rango = maximo - minimo + 1;

        array = new Array(10);
        array.setOrdered(true);

        for (int i = 0; i < 10; i++) {
            int numero = random.nextInt(rango) + minimo;
            while (array.buscar(numero) != -1) {
                numero = random.nextInt(rango) + minimo;
            }
            array.insertar(numero);
            contenidoBurbuja.getChildren().addAll(Grafico.crearCaja(colores, String.valueOf(i), String.valueOf(numero)));
            colores.siguinteColor();
        }
    }

    /**
     * Crear un array nuevo.
     */
    @FXML
    protected void botonNuevo() {
        if (scene == null) {
            initializeScene();
        }

        array = new Array(10);
        array.setOrdered(true);

        Random random = new Random();
        int maximo = 99;
        int minimo = 0;
        int rango = maximo - minimo + 1;

        for (int i = array.size(); i < 10; i++) {
            int numero = random.nextInt(rango) + minimo;
            while (array.buscar(numero) != -1) {
                numero = random.nextInt(rango) + minimo;
            }
            array.insertar(numero);
        }
        generarGrafico();
    }

    /**
     * Ordenarlo paso por paso.
     */
    @FXML
    protected void botonPaso() {
        if (scene == null) {
            initializeScene();
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/burbuja/ordenar")).useDelimiter("\\Z").next();
        codigoBurbuja.setText(codigoTexto);

        if (!array.burbuja(true)) {
            errorYaOrdenado();
        }

        generarGrafico();
    }

    /**
     * Ordenarlo completamente.
     */
    @FXML
    protected void botonCorrer() {
        if (scene == null) {
            initializeScene();
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/burbuja/ordenar")).useDelimiter("\\Z").next();
        codigoBurbuja.setText(codigoTexto);

        if (!array.burbuja(false)) {
            errorYaOrdenado();
        }

        generarGrafico();
    }

    /**
     * Se muestra un error si el array ya está ordenado.
     */
    private void errorYaOrdenado() {
        ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("error"));
        dialog.setContentText(resourceBundle.getString("burbujaYaOrdenado"));
        dialog.getDialogPane().getButtonTypes().add(botonCerrar);
        Main.setIcon(dialog, getClass());
        dialog.show();
    }

    /**
     * Crear el array de tamaño 10.
     */
    private void initializeScene() {
        scene = contenidoBurbuja.getScene();
    }

    /**
     * Poner los valores en el grafico.
     */
    private void generarGrafico() {
        for (int i = 0; i < 10; i++) {
            Text text = (Text) scene.lookup("#texto_" + String.valueOf(i));
            text.setText(array.getIndice(i));
        }
    }
}
