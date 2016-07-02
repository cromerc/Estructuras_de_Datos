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
 * Esta clase es para controlar todos la interfaz de Cola.
 *
 * @author Chris Cromer
 */
public class ColaController implements Initializable {
    /**
     * La caja para ingresar textos.
     */
    @FXML
    private TextFieldLimited valorCola;

    /**
     * Donde poner el contenido de array.
     */
    @FXML
    private VBox contenidoCola;

    /**
     * Donde va el codigo a mostrar a la pantalla.
     */
    @FXML
    private Text codigoCola;

    /**
     * La escena donde está cosas graficas.
     */
    private Scene scene;

    /**
     * Donde está guardado los idiomas.
     */
    private ResourceBundle resourceBundle;

    /**
     * La cola usado en la aplicación.
     */
    private Cola cola;

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

        cola = new Cola();

        scene = null;
        Colores colores = new Colores();

        for (int i = 9; i >= 0; i--) {
            contenidoCola.getChildren().addAll(Grafico.crearCaja(colores, String.valueOf(i)));
            colores.siguinteColor();
        }
    }

    /**
     * Llenar la cola con numeros al azar.
     */
    @FXML
    protected void botonLlenar() {
        if (scene == null) {
            scene = contenidoCola.getScene();
            grafico = new Grafico(scene);
        }

        Random random = new Random();
        int maximo = 99;
        int minimo = 0;
        int rango = maximo - minimo + 1;

        for (int i = cola.size(); i < 10; i++) {
            int numero = random.nextInt(rango) + minimo;
            cola.push(numero);
        }
        generarGrafico();
    }

    /**
     * Vaciar la cola de todos los valores.
     */
    @FXML
    protected void botonVaciar() {
        if (scene == null) {
            scene = contenidoCola.getScene();
            grafico = new Grafico(scene);
        }

        cola = new Cola();
        generarGrafico();
    }

    /**
     * Push un valor a la cola y mostrar el codigo en la pantalla.
     */
    @FXML
    protected void botonPush() {
        if (scene == null) {
            scene = contenidoCola.getScene();
            grafico = new Grafico(scene);
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/cola/push")).useDelimiter("\\Z").next();
        codigoCola.setText(codigoTexto);

        if (valorCola.getText() != null && !valorCola.getText().trim().equals("")) {
            try {
                if (cola.size() < 10) {
                    cola.push(Integer.valueOf(valorCola.getText()));
                    valorCola.setText("");
                    generarGrafico();
                }
                else {
                    Main.mostrarError(resourceBundle.getString("colaLlena"), resourceBundle);
                }
            }
            catch (NumberFormatException exception) {
                // El error no es fatal, sigue
                Logs.log(Level.WARNING, "No es tipo int.");
                Main.mostrarError(resourceBundle.getString("colaNoValor"), resourceBundle);
            }
        }
        else {
            Main.mostrarError(resourceBundle.getString("colaNoValor"), resourceBundle);
        }
    }

    /**
     * Pop un valor de la pila si existe y mostrar el codigo en la pantalla.
     */
    @FXML
    protected void botonPop() {
        if (scene == null) {
            scene = contenidoCola.getScene();
            grafico = new Grafico(scene);
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/cola/pop")).useDelimiter("\\Z").next();
        codigoCola.setText(codigoTexto);

        if (cola.size() > 0) {
            if (!cola.pop()) {
                Main.mostrarError(resourceBundle.getString("colaVacia"), resourceBundle);
            }
            else {
                generarGrafico();
            }
        }
        else {
            Main.mostrarError(resourceBundle.getString("colaVacia"), resourceBundle);
        }
    }

    /**
     * Peek a ver si existe un elemento en la pila y mostrar el codigo en la pantalla
     * Si existe un valor destacarlo.
     */
    @FXML
    protected void botonPeek() {
        if (scene == null) {
            scene = contenidoCola.getScene();
            grafico = new Grafico(scene);
        }

        // Mostrar el codigo
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/cola/peek")).useDelimiter("\\Z").next();
        codigoCola.setText(codigoTexto);

        int encontrado = cola.peek();
        if (encontrado != Integer.MIN_VALUE) {
            generarGrafico();
            grafico.destacar("#caja_" + 0, Grafico.RECTANGULO);
            grafico.destacar("#texto_" + 0, Grafico.TEXTO);
        }
        else {
            Main.mostrarError(resourceBundle.getString("colaVacia"), resourceBundle);
        }
    }

    /**
     * Poner los valores en el grafico.
     */
    private void generarGrafico() {
        grafico.removerDestacar();
        for (int i = 0; i < 10; i++) {
            Text text = (Text) scene.lookup("#texto_" + String.valueOf(i));
            text.setText(cola.getIndice(i));
        }
    }
}