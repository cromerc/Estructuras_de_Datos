package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Esta clase es para controlar todos la interfaz de Array.
 * @author Chris Cromer
 */
public class ArrayController implements Initializable {

    /**
     * La caja para ingresar textos.
     */
    @FXML private TextFieldLimited valorArray;

    /**
     * Donde poner el contenido de array.
     */
    @FXML private VBox contenidoArray;

    /**
     * Donde va el codigo a mostrar a la pantalla.
     */
    @FXML private Text codigoArray;

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

        scene = null;
        Colores colores = new Colores();

        for (int i = 0; i < 10; i++) {
            contenidoArray.getChildren().addAll(Grafico.crearCaja(colores, String.valueOf(i)));
            colores.siguinteColor();
        }
    }

    /**
     * Llenar el array con numeros al azar.
     */
    @FXML
    protected void botonLlenar() {
        if (scene == null) {
            initializeArray();
        }

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
     * Vaciar el array de todos los valores.
     */
    @FXML
    protected void botonVaciar() {
        if (scene == null) {
            initializeArray();
        }

        if (array.isOrdered()) {
            array = new Array(10);
            array.setOrdered(true);
        }
        else {
            array = new Array(10);
            array.setOrdered(false);
        }
        generarGrafico();
    }

    /**
     * Insertar un valor al array y mostrar el codigo en la pantalla.
     */
    @FXML
    protected void botonInsertar() {
        if (scene == null) {
            initializeArray();
        }

        // Mostrar el codigo
        String tipo = (array.isOrdered())?"Ordenado":"Simple";
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
        codigoArray.setText(codigoTexto);

        if (valorArray.getText() != null && !valorArray.getText().trim().equals("")) {
            try {
                boolean exito = array.insertar(Integer.valueOf(valorArray.getText()));
                if (exito) {
                    valorArray.setText("");
                    generarGrafico();
                }
                else {
                    ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitle(resourceBundle.getString("error"));
                    if (array.size() == 10) {
                        dialog.setContentText(resourceBundle.getString("arrayLleno"));
                    }
                    else {
                        dialog.setContentText(resourceBundle.getString("arrayValorExiste"));
                    }
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
     * Eliminar un valor del array si existe y mostrar el codigo en la pantalla.
     */
    @FXML
    protected void botonEliminar() {
        if (scene == null) {
            initializeArray();
        }

        // Mostrar el codigo
        String tipo = (array.isOrdered())?"Ordenado":"Simple";
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/eliminar")).useDelimiter("\\Z").next();
        codigoArray.setText(codigoTexto);

        try {
            if (valorArray.getText() != null && !valorArray.getText().trim().equals("")) {
                boolean exito = array.eliminar(Integer.valueOf(valorArray.getText()));
                if (exito) {
                    valorArray.setText("");
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
     * Buscar si existe un elemento en el array y mostrar el codigo en la pantalla
     * Si existe el valor destacarlo.
     */
    @FXML
    protected void botonBuscar() {
        if (scene == null) {
            initializeArray();
        }

        // Mostrar el codigo
        String tipo = (array.isOrdered())?"Ordenado":"Simple";
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/buscar")).useDelimiter("\\Z").next();
        codigoArray.setText(codigoTexto);

        try {
            if (valorArray.getText() != null && !valorArray.getText().trim().equals("")) {
                int encontrado = array.buscar(Integer.valueOf(valorArray.getText()));
                if (encontrado != -1) {
                    generarGrafico();
                    grafico = new Grafico(scene);
                    grafico.destacar(encontrado, Grafico.RECTANGULO);
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
     * Se muestra un error si la persona no ingresa un valor en el TextField.
     */
    private void errorNoValor() {
        ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("error"));
        dialog.setContentText(resourceBundle.getString("arrayNoValor"));
        dialog.getDialogPane().getButtonTypes().add(botonCerrar);
        Main.setIcon(dialog, getClass());
        dialog.show();
    }

    /**
     * Error cuando el valor no está en el array.
     */
    private void errorNoEsta() {
        ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("error"));
        dialog.setContentText(resourceBundle.getString("arrayNoEsta"));
        dialog.getDialogPane().getButtonTypes().add(botonCerrar);
        Main.setIcon(dialog, getClass());
        dialog.show();
    }

    /**
     * Crear el array de tamaño 10. La scene está usado para saber si es de tipo ordenado o simple segun el ménu.
     */
    private void initializeArray() {
        scene = contenidoArray.getScene();
        grafico = new Grafico(scene);
        this.array = new Array(10);
        ArrayTipos arrayTipos = (ArrayTipos) scene.getUserData();
        if (arrayTipos.getTipo() == ArrayTipos.ORDENADO) {
            this.array.setOrdered(true);
        }
    }

    /**
     * Poner los valores en el grafico.
     */
    private void generarGrafico() {
        grafico.removerDestacar();
        for (int i = 0; i < 10; i++) {
            Text text = (Text) scene.lookup("#caja_" + String.valueOf(i));
            text.setText(array.getIndice(i));
        }
    }
}
