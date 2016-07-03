package cl.cromer.estructuras;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
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
 *
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
	 *
	 * @param args String[]: Argumentos desde la consola.
	 */
	public static void main(String args[]) {
		if (DEBUG) {
			new Logs();
		}

		launch(args);
	}

	/**
	 * Mostrar una ventana con mensaje en la pantalla.
	 *
	 * @param mensaje String: El mensaje a mostrar.
	 * @param resourceBundle ResourceBundle: Contiene el idioma actual.
	 */
	static public void mostrarError(String mensaje, ResourceBundle resourceBundle) {
		ButtonType botonCerrar = new ButtonType(resourceBundle.getString("cerrar"), ButtonBar.ButtonData.OK_DONE);
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(resourceBundle.getString("error"));
		dialog.setContentText(mensaje);
		dialog.getDialogPane().getButtonTypes().add(botonCerrar);
		dialog.getDialogPane().getScene().getWindow().sizeToScene();
		Main.setIcon(dialog, Main.class);
		dialog.show();
	}

	/**
	 * Cambiar el icono de una ventana.
	 *
	 * @param dialog Dialog: El Dialog a cambiar.
	 * @param clase Class: La clase usado para abrir el Stream.
	 */
	static public void setIcon(Dialog dialog, Class clase) {
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(clase.getResourceAsStream("/cl/cromer/estructuras/images/icon.png")));
	}

	/**
	 * Crear el stage y la scene para la aplicación grafica.
	 *
	 * @param stage Stage: El primer stage donde va todas las cosas visuales.
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