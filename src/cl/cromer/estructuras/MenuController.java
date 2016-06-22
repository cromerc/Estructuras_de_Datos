package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * Controlar las acciones cuando una opción es elegido en el menu.
 */
public class MenuController extends VBox implements Initializable {
    /**
     * La barra del menu.
     */
    @FXML private MenuBar menuBar;

    /**
     * Los idiomas.
     */
    private ResourceBundle resourceBundle;

    /**
     * Inicialicar el menu con el idioma.
     * @param location URL: Tiene URL de FXML en uso.
     * @param resourceBundle: Tiene los idiomas.
     */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     * Click en Array Simple.
     */
    @FXML
    protected void menuArraySimple() {
        Array array = new Array(1);
        array.setOrdered(false);
        loadStage(
            resourceBundle.getString("tituloArraySimple"),
            "/cl/cromer/estructuras/fxml/array.fxml",
            "/cl/cromer/estructuras/css/style.css",
            array
        );
    }

    /**
     * Click en Array Ordenado.
     */
    @FXML
    protected void menuArrayOrdenado() {
        Array array = new Array(1);
        array.setOrdered(true);
        loadStage(
            resourceBundle.getString("tituloArrayOrdenado"),
            "/cl/cromer/estructuras/fxml/array.fxml",
            "/cl/cromer/estructuras/css/style.css",
            array
        );
    }

    /**
     * Click en Burbuja.
     */
    @FXML
    protected void menuBurbuja() {
        loadStage(
            resourceBundle.getString("tituloBurbuja"),
            "/cl/cromer/estructuras/fxml/burbuja.fxml",
            "/cl/cromer/estructuras/css/style.css"
        );
    }

    /**
     * Click en Inserción.
     */
    @FXML
    protected void menuInsercion() {
        loadStage(
            resourceBundle.getString("tituloInsercion"),
            "/cl/cromer/estructuras/fxml/insercion.fxml",
            "/cl/cromer/estructuras/css/style.css"
        );
    }

    /**
     * Click en Selecion.
     */
    @FXML
    protected void menuSeleccion() {
        loadStage(
            resourceBundle.getString("tituloSeleccion"),
            "/cl/cromer/estructuras/fxml/seleccion.fxml",
            "/cl/cromer/estructuras/css/style.css"
        );
    }

    /**
     * Click en Shell.
     */
    @FXML
    protected void menuShell() {
        loadStage(
            resourceBundle.getString("tituloShell"),
            "/cl/cromer/estructuras/fxml/shell.fxml",
            "/cl/cromer/estructuras/css/style.css"
        );
    }

    /**
     * Click en Quick.
     */
    @FXML
    protected void menuQuick() {
        loadStage(
            resourceBundle.getString("tituloQuick"),
            "/cl/cromer/estructuras/fxml/quick.fxml",
            "/cl/cromer/estructuras/css/style.css"
        );
    }

    /**
     * Click en Merge.
     */
    @FXML
    protected void menuMerge() {
        loadStage(
            resourceBundle.getString("tituloMerge"),
            "/cl/cromer/estructuras/fxml/merge.fxml",
            "/cl/cromer/estructuras/css/style.css"
        );
    }

    /**
     * Click en Pila.
     */
    @FXML
    protected void menuPila() {
        loadStage(
            resourceBundle.getString("tituloPila"),
            "/cl/cromer/estructuras/fxml/pila.fxml",
            "/cl/cromer/estructuras/css/style.css"
        );
    }

    /**
     * Click en Cola.
     */
    @FXML
    protected void menuCola() {
        loadStage(
            resourceBundle.getString("tituloCola"),
            "/cl/cromer/estructuras/fxml/cola.fxml",
            "/cl/cromer/estructuras/css/style.css"
        );
    }

    /**
     * Click en Ingles.
     */
    @FXML
    protected void menuIngles() {
        ButtonType botonCambiar = new ButtonType(resourceBundle.getString("cambiar"), ButtonBar.ButtonData.OK_DONE);
        ButtonType botonCancelar = new ButtonType(resourceBundle.getString("cancelar"), ButtonBar.ButtonData.CANCEL_CLOSE);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("cambiarIdioma"));
        dialog.setContentText(resourceBundle.getString("cambiarIdiomaMensaje"));
        dialog.getDialogPane().getButtonTypes().add(botonCancelar);
        dialog.getDialogPane().getButtonTypes().add(botonCambiar);
        dialog.getDialogPane().setPrefSize(400, 120);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == botonCambiar) {
            // Si hace click en cambiar, cambiar el idioma y reiniciar.
            Locale locale = new Locale("en", "EN");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("cl.cromer.estructuras.bundles.Idioma", locale);

            loadStage(
                "/cl/cromer/estructuras/fxml/main.fxml",
                "/cl/cromer/estructuras/css/style.css",
                resourceBundle
            );
        }
    }

    /**
     * Click en Español.
     */
    @FXML
    protected void menuEspanol() {
        ButtonType botonCambiar = new ButtonType(resourceBundle.getString("cambiar"), ButtonBar.ButtonData.OK_DONE);
        ButtonType botonCancelar = new ButtonType(resourceBundle.getString("cancelar"), ButtonBar.ButtonData.CANCEL_CLOSE);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("cambiarIdioma"));
        dialog.setContentText(resourceBundle.getString("cambiarIdiomaMensaje"));
        dialog.getDialogPane().getButtonTypes().add(botonCancelar);
        dialog.getDialogPane().getButtonTypes().add(botonCambiar);
        dialog.getDialogPane().setPrefSize(400, 120);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == botonCambiar) {
            // Si hace click en cambiar, cambiar el idioma y reiniciar.
            Locale locale = new Locale("es", "ES");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("cl.cromer.estructuras.bundles.Idioma", locale);

            loadStage(
                "/cl/cromer/estructuras/fxml/main.fxml",
                "/cl/cromer/estructuras/css/style.css",
                resourceBundle
            );
        }
    }

    /**
     * Click en Acerca.
     */
    @FXML
    protected void menuAcerca() {
        ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("acerca"));
        dialog.setContentText(resourceBundle.getString("credito"));
        dialog.getDialogPane().getButtonTypes().add(botonCerrar);
        dialog.show();
    }

    /**
     * Cargar el fxml, css y titulo.
     * @param title String: El titulo de la escena.
     * @param fxml String: El archivo de fxml.
     * @param css String: El archivo de css.
     */
    private void loadStage(String title, String fxml, String css) {
        Scene scene = menuBar.getScene();
        Stage stage = (Stage) scene.getWindow();

        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxml), resourceBundle);
            scene.setRoot(parent);
        }
        catch (IOException exception) {
            // Este error es fatal, hay que cerrar la aplicación.
            Logs.log(Level.SEVERE, "No se pudo abrir el archivo de fxml.");
            stage.close();
        }

        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle(resourceBundle.getString("titulo") + " - " + title);
    }

    /**
     * Cargar el fxml y css.
     * @param fxml String: El archivo de fxml.
     * @param css String: El archivo de css.
     * @param resourceBundle ResourceBundle: El idioma nuevo para cambiarlo.
     */
    private void loadStage(String fxml, String css, ResourceBundle resourceBundle) {
        Scene scene = menuBar.getScene();
        Stage stage = (Stage) scene.getWindow();

        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxml), resourceBundle);
            scene.setRoot(parent);
        }
        catch (IOException exception) {
            // Este error es fatal, hay que cerrar la aplicación.
            Logs.log(Level.SEVERE, "No se pudo abrir el archivo de fxml.");
            stage.close();
        }

        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle(resourceBundle.getString("titulo"));
    }

    /**
     * Cargar el fxml, css y titulo.
     * @param title String: El titulo de la escena.
     * @param fxml String: El archivo de fxml.
     * @param css String: El archivo de css.
     * @param object Object: El objeto a pasar a la nueva escena.
     */
    private void loadStage(String title, String fxml, String css, Object object) {
        Scene scene = menuBar.getScene();
        Stage stage = (Stage) scene.getWindow();

        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxml), resourceBundle);
            scene.setRoot(parent);
        }
        catch (IOException exception) {
            // Este error es fatal, hay que cerrar la aplicación.
            Logs.log(Level.SEVERE, "No se pudo abrir el archivo de fxml.");
            stage.close();
        }

        scene.getStylesheets().add(css);
        scene.setUserData(object);
        stage.setScene(scene);
        stage.setTitle(resourceBundle.getString("titulo") + " - " + title);
    }
}