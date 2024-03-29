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
import java.security.AccessControlException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.prefs.Preferences;

/**
 * Estructuras de Datos
 * Creado como proyecto semestral para la asignatura de estructuras de datos por la profesora Karina Rojas y el profesor Jorge Elgueta.
 * Creado en 2016-1
 * Se necesita java 8 con javafx instalado.
 *
 * @author Chris Cromer
 * @version 1.0.5
 */
public class Main extends Application {
	static public String VERSION = "1.0.5";

	/**
	 * El stage pricipal
	 */
	static public Stage stage;
	/**
	 * El logger.
	 */
	static private Logs logs = null;

	/**
	 * Crear el stage y la scene para la aplicación grafica.
	 *
	 * @param startStage Stage: El primer stage donde va todas las cosas visuales.
	 */
	@Override
	public void start(Stage startStage) {
		stage = startStage;
		String idioma;
		String idioma2;
		Preferences preferences;
		try {
			preferences = Preferences.userRoot().node(this.getClass().getName());
			idioma = preferences.get("idioma", "en");
			idioma2 = preferences.get("idioma2", "EN");
		}
		catch (AccessControlException exception) {
			// This will throw if the applet is not signed.
			preferences = null;
			idioma = "en";
			idioma2 = "EN";
		}

		Locale locale = new Locale(idioma, idioma2);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("cl.cromer.estructuras.bundles.Idioma", locale);

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/cl/cromer/estructuras/fxml/main.fxml"), ResourceBundle.getBundle("cl.cromer.estructuras.bundles.Idioma", locale));
			stage.setTitle(resourceBundle.getString("titulo"));
			Scene scene = new Scene(parent, 1024, 768);
			scene.getStylesheets().add("/cl/cromer/estructuras/css/main.css");
			stage.setScene(scene);
		}
		catch (IOException exception) {
			// Este error es fatal, hay que cerrar la aplicación.
			Logs.log(Level.SEVERE, exception);
			stage.close();
		}

		stage.setMaximized(true);
		stage.setMinHeight(640);
		stage.setMinWidth(768);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/cl/cromer/estructuras/images/icon.png")));
		stage.setUserData(preferences);
		stage.show();
	}

	@Override
	public void stop() {
		try {
			super.stop();
		}
		catch (Exception exception) {
			Logs.log(Level.SEVERE, exception);
		}
		if (logs != null) {
			logs.close();
		}
	}

	/**
	 * Inicilizar el logeo y lanzar la interfaz grafica.
	 *
	 * @param args String[]: Argumentos desde la consola.
	 */
	public static void main(String args[]) {
		if (Logs.DEBUG) {
			logs = new Logs();
		}

		launch(args);
	}

	/**
	 * Cambiar el icono de una ventana.
	 *
	 * @param dialog Dialog: El Dialog a cambiar.
	 * @param clase Class: La clase usado para abrir el Stream.
	 */
	static public void setIcon(Dialog dialog, Class clase) {
		try {
			Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(clase.getResourceAsStream("/cl/cromer/estructuras/images/icon.png")));
		}
		catch (Exception exception) {
			// El icono no está, no es tan critico.
			Logs.log(Level.WARNING, exception);
		}
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
		dialog.getDialogPane().setPrefWidth(mensaje.length() * 8);
		Main.setIcon(dialog, Main.class);
		dialog.show();
	}
}