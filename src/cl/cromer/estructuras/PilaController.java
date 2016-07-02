package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Esta clase es para controlar todos la interfaz de Pila.
 *
 * @author Chris Cromer
 */
public class PilaController implements Initializable {
    /**
     * La caja para ingresar textos.
     */
    @FXML
    private TextFieldLimited valorPila;

    /**
     * Donde poner el contenido de array.
     */
    @FXML
    private VBox contenidoPila;

    /**
     * Donde va el codigo a mostrar a la pantalla.
     */
    @FXML
    private Text codigoPila;

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
     *
     * @param location       URL: El URL de fxml en uso.
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
                    valorPila.setText("");
                    generarGrafico();
                }
                else {
                    Main.mostrarError(resourceBundle.getString("pilaLlena"), resourceBundle);
                }
            }
            catch (NumberFormatException exception) {
                // El error no es fatal, sigue
                Logs.log(Level.WARNING, "No es tipo int.");
                Main.mostrarError(resourceBundle.getString("pilaNoValor"), resourceBundle);
            }
        }
        else {
            Main.mostrarError(resourceBundle.getString("pilaNoValor"), resourceBundle);
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
            if (!pila.pop()) {
                Main.mostrarError(resourceBundle.getString("pilaVacia"), resourceBundle);
            }
            else {
                generarGrafico();
            }
        }
        else {
            Main.mostrarError(resourceBundle.getString("pilaVacia"), resourceBundle);
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
            grafico.destacar("#caja_" + (pila.size() - 1), Grafico.RECTANGULO);
            grafico.destacar("#texto_" + (pila.size() - 1), Grafico.TEXTO);
        }
        else {
            Main.mostrarError(resourceBundle.getString("pilaVacia"), resourceBundle);
        }
    }

    /**
     * Poner los valores en el grafico.
     */
    private void generarGrafico() {
        grafico.removerDestacar();
        for (int i = 0; i < 10; i++) {
            Text text = (Text) scene.lookup("#texto_" + String.valueOf(i));
            text.setText(pila.getIndice(i));
        }
    }
}