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
import java.util.logging.Level;

/**
 * Esta clase es para controlar todos la interfaz de HashTable.
 *
 * @author Chris Cromer
 */
public class HashTableController implements Initializable {
    /**
     * La caja para ingresar la llave.
     */
    @FXML
    private TextFieldLimited llaveHashTable;

    /**
     * La caja para ingresar el valor.
     */
    @FXML
    private TextFieldLimited valorHashTable;

    /**
     * Donde poner el contenido de hashTable.
     */
    @FXML
    private VBox contenidoHashTable;

    /**
     * Donde va el codigo a mostrar a la pantalla.
     */
    @FXML
    private Text codigoHashTable;

    /**
     * La escena donde está cosas graficas.
     */
    private Scene scene;

    /**
     * Donde está guardado los idiomas.
     */
    private ResourceBundle resourceBundle;

    /**
     * El hashTable usado en la aplicación.
     */
    private HashTable hashTable;

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

        scene = null;
        Colores colores = new Colores();

        for (int i = 0; i < 10; i++) {
            contenidoHashTable.getChildren().addAll(Grafico.crearHashCajas(colores, String.valueOf(i)));
            colores.siguinteColor();
        }
    }

    /**
     * Llenar el hashTable con numeros al azar.
     */
    @FXML
    protected void botonLlenar() {
        if (scene == null) {
            initializeHashTable();
        }

        Palabras palabras = new Palabras();

        Random random = new Random();
        int maximo = 99;
        int minimo = 0;
        int rango = maximo - minimo + 1;

        for (int i = 0; i < 10; i++) {
            int numero = random.nextInt(rango) + minimo;
            while (!hashTable.insertar(palabras.getPalabra(), numero)) {
                if (hashTable.size() == 10) {
                    break;
                }
            }
        }
        generarGrafico();
    }

    /**
     * Vaciar el hashTable de todos los valores.
     */
    @FXML
    protected void botonVaciar() {
        if (scene == null) {
            initializeHashTable();
        }

        hashTable = new HashTable(10);
        generarGrafico();
    }

    /**
     * Insertar un valor al hashTable y mostrar el codigo en la pantalla.
     */
    @FXML
    protected void botonInsertar() {
        if (scene == null) {
            initializeHashTable();
        }

        // Mostrar el codigo
        /*String tipo = (hashTable.isOrdered()) ? "Ordenado" : "Simple";
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/hashTable" + tipo + "/insertar")).useDelimiter("\\Z").next();
        codigoHashTable.setText(codigoTexto);*/

        if (llaveHashTable.getText() != null && !llaveHashTable.getText().trim().equals("") && valorHashTable.getText() != null && !valorHashTable.getText().trim().equals("")) {
            try {
                boolean exito = hashTable.insertar(llaveHashTable.getText().trim(), Integer.valueOf(valorHashTable.getText()));
                if (exito) {
                    llaveHashTable.setText("");
                    valorHashTable.setText("");
                    generarGrafico();
                }
                else {
                    ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitle(resourceBundle.getString("error"));
                    if (hashTable.size() == 10) {
                        dialog.setContentText(resourceBundle.getString("tablaHashLleno"));
                    }
                    else {
                        dialog.setContentText(resourceBundle.getString("tablaHashLlaveExiste"));
                    }
                    dialog.getDialogPane().getButtonTypes().add(botonCerrar);
                    Main.setIcon(dialog, getClass());
                    dialog.show();
                }
            }
            catch (NumberFormatException exception) {
                // El error no es fatal, sigue
                Logs.log(Level.WARNING, "No es tipo int.");
                errorNoLlave();
            }
        }
        else {
            Main.error(resourceBundle.getString("tablaHashNoLlave"), resourceBundle);
        }
    }

    /**
     * Eliminar un valor del hashTable si existe y mostrar el codigo en la pantalla.
     */
    @FXML
    protected void botonEliminar() {
        if (scene == null) {
            initializeHashTable();
        }

        // Mostrar el codigo
        /*String tipo = (hashTable.isOrdered()) ? "Ordenado" : "Simple";
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/hashTable" + tipo + "/eliminar")).useDelimiter("\\Z").next();
        codigoHashTable.setText(codigoTexto);*/

        try {
            if (llaveHashTable.getText() != null && !llaveHashTable.getText().trim().equals("")) {
                boolean exito = hashTable.eliminar(llaveHashTable.getText());
                if (exito) {
                    llaveHashTable.setText("");
                    valorHashTable.setText("");
                    generarGrafico();
                }
                else {
                    errorNoEsta();
                }
            }
            else {
                errorNoLlave();
            }
        }
        catch (NumberFormatException exception) {
            // El error no es fatal, sigue
            Logs.log(Level.WARNING, "No es tipo int.");
            errorNoLlave();
        }
    }

    /**
     * Buscar si existe un elemento en el hashTable y mostrar el codigo en la pantalla
     * Si existe el valor destacarlo.
     */
    @FXML
    protected void botonBuscar() {
        if (scene == null) {
            initializeHashTable();
        }

        // Mostrar el codigo
        /*String tipo = (hashTable.isOrdered()) ? "Ordenado" : "Simple";
        String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/hashTable" + tipo + "/buscar")).useDelimiter("\\Z").next();
        codigoHashTable.setText(codigoTexto);*/

        try {
            if (llaveHashTable.getText() != null && !llaveHashTable.getText().trim().equals("")) {
                HashItem hashItem = hashTable.buscar(llaveHashTable.getText());
                if (hashItem != null) {
                    generarGrafico();
                    grafico = new Grafico(scene);
                    grafico.destacar("#indice_caja_" + hashItem.getIndice(), Grafico.RECTANGULO);
                    grafico.destacar("#indice_texto_" + hashItem.getIndice(), Grafico.TEXTO);
                    grafico.destacar("#llave_caja_" + hashItem.getIndice(), Grafico.RECTANGULO);
                    grafico.destacar("#llave_texto_" + hashItem.getIndice(), Grafico.TEXTO);
                    grafico.destacar("#valor_caja_" + hashItem.getIndice(), Grafico.RECTANGULO);
                    grafico.destacar("#valor_texto_" + hashItem.getIndice(), Grafico.TEXTO);
                }
                else {
                    errorNoEsta();
                }
            }
            else {
                errorNoLlave();
            }
        }
        catch (NumberFormatException exception) {
            // El error no es fatal, sigue
            Logs.log(Level.WARNING, "No es tipo int.");
            errorNoLlave();
        }
    }

    /**
     * Se muestra un error si la persona no ingresa un valor en el TextField.
     */
    private void errorNoLlave() {

        /*ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("error"));
        dialog.setContentText(resourceBundle.getString("tablaHashNoLlave"));
        dialog.getDialogPane().getButtonTypes().add(botonCerrar);
        Main.setIcon(dialog, getClass());
        dialog.show();*/
    }

    /**
     * Error cuando el valor no está en el hashTable.
     */
    private void errorNoEsta() {
        ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("error"));
        dialog.setContentText(resourceBundle.getString("tablaHashNoEsta"));
        dialog.getDialogPane().getButtonTypes().add(botonCerrar);
        Main.setIcon(dialog, getClass());
        dialog.show();
    }

    /**
     * Crear el hashTable de tamaño 10.
     */
    private void initializeHashTable() {
        scene = contenidoHashTable.getScene();
        grafico = new Grafico(scene);
        this.hashTable = new HashTable(10);
    }

    /**
     * Poner los valores en el grafico.
     */
    private void generarGrafico() {
        grafico.removerDestacar();
        for (int i = 0; i < 10; i++) {
            if (hashTable.getIndice(i) != null) {
                Text text = (Text) scene.lookup("#indice_texto_" + String.valueOf(i));
                text.setText(String.valueOf(i));
                text = (Text) scene.lookup("#llave_texto_" + String.valueOf(i));
                text.setText(hashTable.getIndice(i).getLlave());
                text = (Text) scene.lookup("#valor_texto_" + String.valueOf(i));
                text.setText(String.valueOf(hashTable.getIndice(i).getValor()));
            }
            else {
                Text text = (Text) scene.lookup("#indice_texto_" + String.valueOf(i));
                text.setText("");
                text = (Text) scene.lookup("#llave_texto_" + String.valueOf(i));
                text.setText("");
                text = (Text) scene.lookup("#valor_texto_" + String.valueOf(i));
                text.setText("");
            }
        }
    }
}
