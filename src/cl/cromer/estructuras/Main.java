package cl.cromer.estructuras;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * Estructuras de Datos
 * Creado como proyecto semestral para la asignatura de estructuras de datos por la profesora Karina Rojas y el profesor Jorge Elgueta.
 * Creado en 2016-1
 * Se necesita java 8 instalado.
 * @author Chris Cromer
 * @version 1.0.0
 */
public class Main extends Application {
    /**
     * Estado de depuración.
     */
    static final public boolean DEBUG = false;

    /**
     * Inicilizar el logeo y lanzar la interfaz grafica.
     * @param args String[]: Argumentos desde la consola.
     */
    public static void main(String[] args) {
        if (DEBUG) {
            new Logs();
        }

        launch(args);
    }

    /**
     * Crear el stage y la scene para la aplicación grafica.
     * @param stage El primer stage donde va todas las cosas visuales.
     */
    @Override
    public void start(Stage stage) {
        Locale locale = new Locale("es", "ES");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("cl.cromer.estructuras.bundles.Idioma", locale);

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/cl/cromer/estructuras/fxml/main.fxml"), ResourceBundle.getBundle("cl.cromer.estructuras.bundles.Idioma", locale));
            stage.setTitle(resourceBundle.getString("titulo"));
            Scene scene = new Scene(parent, 1024, 768);
            scene.getStylesheets().add("/cl/cromer/estructuras/css/style.css");
            stage.setScene(scene);
        }
        catch (IOException exception) {
            // Este error es fatal, hay que cerrar la aplicación.
            Logs.log(Level.SEVERE, "No se pudo abrir el archivo de fxml.");
            stage.close();
        }

        //stage.setMaximized(true);
        stage.setMinHeight(640);
        stage.setMinWidth(768);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/cl/cromer/estructuras/images/icon.png")));
        stage.show();
    }
}