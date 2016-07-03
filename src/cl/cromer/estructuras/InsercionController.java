package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Esta clase es para controlar todos la interfaz de Inserción.
 *
 * @author Chris Cromer
 */
public class InsercionController implements Initializable {
    /**
     * Donde poner el contenido de array.
     */
    @FXML
    private HBox contenidoInsercion;

    /**
     * Donde va el codigo a mostrar a la pantalla.
     */
    @FXML
    private Text codigoInsercion;

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
     * @param location URL: El URL de fxml en uso.
     * @param resourceBundle ResourceBundle: Tiene datos de idioma.
     */
    @Override
    @SuppressWarnings("Duplicates")
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;

        scene = null;

        Colores colores = new Colores();

        array = new Array(10);
        array.setOrdered(true);
        array.llenar();

        for (int i = 0; i < 10; i++) {
            contenidoInsercion.getChildren().addAll(Grafico.crearCaja(colores, String.valueOf(i), String.valueOf(array.getIndice(i))));
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

        array.nuevo();
        array.llenar();
        generarGrafico();
    }

    /**
     * Crear el array de tamaño 10.
     */
    private void initializeScene() {
        scene = contenidoInsercion.getScene();
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

    /**
     * Ordenarlo paso por paso.
     */
    @FXML
    protected void botonPaso() {
        if (scene == null) {
            initializeScene();
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/insercion/ordenar")).useDelimiter("\\Z").next();
        codigoInsercion.setText(codigoTexto);

        if (! array.insercion(true)) {
            Main.mostrarError(resourceBundle.getString("insercionYaOrdenado"), resourceBundle);
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
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/insercion/ordenar")).useDelimiter("\\Z").next();
        codigoInsercion.setText(codigoTexto);

        if (! array.insercion(false)) {
            Main.mostrarError(resourceBundle.getString("insercionYaOrdenado"), resourceBundle);
        }

		generarGrafico();
    }
}
