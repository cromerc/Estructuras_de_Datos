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
 * Esta clase es para controlar todos la interfaz de Pila.
 * @author Chris Cromer
 */
public class PilaController implements Initializable {
    /**
     * La caja para ingresar textos.
     */
    @FXML private TextFieldLimited valorPila;

    /**
     * Donde poner el contenido de array.
     */
    @FXML private VBox contenidoPila;

    /**
     * Donde va el codigo a mostrar a la pantalla.
     */
    @FXML private Text codigoPila;

    /**
     * La escena donde está cosas graficas.
     */
    private Scene scene;

    /**
     * Donde está guardado los idiomas.
     */
    private ResourceBundle resourceBundle;

    /**
     * La pila usado en la aplicación.
     */
    private Pila pila;

    /**
     * Grafico rectangulos.
     */
    private Grafico grafico;

    /**
     * Inicializar todos los datos y dibujar las graficas.
     * @param location URL: El URL de fxml en uso.
     * @param resourceBundle ResourceBundle: Tiene datos de idioma.
     */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;

        pila = new Pila();

        scene = null;
        Colores colores = new Colores();

        for (int i = 9; i >= 0; i--) {
            contenidoPila.getChildren().addAll(Grafico.crearCaja(colores, String.valueOf(i)));
            colores.siguinteColor();
        }
    }

    /**
     * Llenar la pila con numeros al azar.
     */
    @FXML
    protected void botonLlenar() {
        if (scene == null) {
            scene = contenidoPila.getScene();
            grafico = new Grafico(scene);
        }

        Random random = new Random();
        int maximo = 99;
        int minimo = 0;
        int rango = maximo - minimo + 1;

        for (int i = pila.size(); i < 10; i++) {
            int numero = random.nextInt(rango) + minimo;
            pila.push(numero);
        }
        generarGrafico();
    }

    /**
     * Vaciar la pila de todos los valores.
     */
    @FXML
    protected void botonVaciar() {
        if (scene == null) {
            scene = contenidoPila.getScene();
            grafico = new Grafico(scene);
        }

        pila = new Pila();
        generarGrafico();
    }

    /**
     * Push un valor a la pila y mostrar el codigo en la pantalla.
     */
    @FXML
    protected void botonPush() {
        if (scene == null) {
            scene = contenidoPila.getScene();
            grafico = new Grafico(scene);
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/pila/push")).useDelimiter("\\Z").next();
        codigoPila.setText(codigoTexto);

        if (valorPila.getText() != null && !valorPila.getText().trim().equals("")) {
            try {
                if (pila.size() < 10) {
                    pila.push(Integer.valueOf(valorPila.getText()));
                    generarGrafico();
                }
                else {
                    ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitle(resourceBundle.getString("error"));
                    dialog.setContentText(resourceBundle.getString("pilaLlena"));
                    dialog.getDialogPane().getButtonTypes().add(botonCerrar);
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
     * Pop un valor de la pila si existe y mostrar el codigo en la pantalla.
     */
    @FXML
    protected void botonPop() {
        if (scene == null) {
            scene = contenidoPila.getScene();
            grafico = new Grafico(scene);
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/pila/pop")).useDelimiter("\\Z").next();
        codigoPila.setText(codigoTexto);

        if (pila.size() > 0) {
            pila.pop();
            generarGrafico();
        }
        else {
            errorVacia();
        }
    }

    /**
     * Peek a ver si existe un elemento en la pila y mostrar el codigo en la pantalla
     * Si existe un valor destacarlo.
     */
    @FXML
    protected void botonPeek() {
        if (scene == null) {
            scene = contenidoPila.getScene();
            grafico = new Grafico(scene);
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/pila/peek")).useDelimiter("\\Z").next();
        codigoPila.setText(codigoTexto);

        int encontrado = pila.peek();
        if (encontrado != Integer.MIN_VALUE) {
            generarGrafico();
            grafico.destacer(pila.size() - 1, Grafico.RECTANGULO);
        }
        else {
            errorVacia();
        }
    }

    /**
     * Se muestra un error si la persona no ingresa un valor en el TextField.
     */
    private void errorNoValor() {
        ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("error"));
        dialog.setContentText(resourceBundle.getString("pilaNoValor"));
        dialog.getDialogPane().getButtonTypes().add(botonCerrar);
        dialog.show();
    }

    /**
     * Error cuando la pila está vacía.
     */
    private void errorVacia() {
        ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("error"));
        dialog.setContentText(resourceBundle.getString("pilaVacia"));
        dialog.getDialogPane().getButtonTypes().add(botonCerrar);
        dialog.show();
    }

    /**
     * Poner los valores en el grafico.
     */
    private void generarGrafico() {
        grafico.removerDestacar();
        for (int i = 0; i < 10; i++) {
            Text text = (Text) scene.lookup("#caja_" + String.valueOf(i));
            text.setText(pila.getIndice(i));
        }
    }
}