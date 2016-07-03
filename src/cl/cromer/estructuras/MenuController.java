package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;

import static cl.cromer.estructuras.ListaEnlazadaTipos.SIMPLE;

/**
 * Controlar las acciones cuando una opción es elegido en el menu.
 */
public class MenuController extends VBox implements Initializable {
	/**
	 * La barra del menu.
	 */
	@FXML
	private MenuBar menuBar;

	/**
	 * Los idiomas.
	 */
	private ResourceBundle resourceBundle;

	/**
	 * Inicialicar el menu con el idioma.
	 *
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
		ArrayTipos arrayTipos = new ArrayTipos(ArrayTipos.SIMPLE);
		loadStage(
				resourceBundle.getString("tituloArraySimple"),
				"/cl/cromer/estructuras/fxml/array.fxml",
				"/cl/cromer/estructuras/css/style.css",
				arrayTipos
		);
	}

	/**
	 * Cargar el fxml, css y titulo.
	 *
	 * @param title String: El titulo de la escena.
	 * @param fxml String: El archivo de fxml.
	 * @param css String: El archivo de css.
	 * @param object Object: El objeto a pasar a la nueva escena.
	 */
	private void loadStage(String title, String fxml, String css, Object object) {
		Scene scene = menuBar.getScene();
		Stage stage = (Stage) scene.getWindow();

		openFXML(fxml, scene, stage);

		scene.getStylesheets().add(css);
		scene.setUserData(object);
		stage.setScene(scene);
		stage.setTitle(this.resourceBundle.getString("titulo") + " - " + title);
	}

	private void openFXML(String fxml, Scene scene, Stage stage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(fxml), this.resourceBundle);
			scene.setRoot(parent);
		}
		catch (IOException exception) {
			// Este error es fatal, hay que cerrar la aplicación.
			Logs.log(Level.SEVERE, "No se pudo abrir el archivo de fxml.");
			stage.close();
		}
	}

	/**
	 * Click en Array Ordenado.
	 */
	@FXML
	protected void menuArrayOrdenado() {
		ArrayTipos arrayTipos = new ArrayTipos(ArrayTipos.ORDENADO);
		loadStage(
				resourceBundle.getString("tituloArrayOrdenado"),
				"/cl/cromer/estructuras/fxml/array.fxml",
				"/cl/cromer/estructuras/css/style.css",
				arrayTipos
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
	 * Cargar el fxml, css y titulo.
	 *
	 * @param title String: El titulo de la escena.
	 * @param fxml String: El archivo de fxml.
	 * @param css String: El archivo de css.
	 */
	private void loadStage(String title, String fxml, String css) {
		Scene scene = menuBar.getScene();
		Stage stage = (Stage) scene.getWindow();

		openFXML(fxml, scene, stage);

		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.setTitle(this.resourceBundle.getString("titulo") + " - " + title);
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
	 * Click en Lista Enlazada Simple.
	 */
	@FXML
	protected void menuListaEnlazadaSimple() {
		ListaEnlazadaTipos listaEnlazadaTipos = new ListaEnlazadaTipos(SIMPLE);
		loadStage(
				resourceBundle.getString("tituloListaEnlazadaSimple"),
				"/cl/cromer/estructuras/fxml/listaEnlazada.fxml",
				"/cl/cromer/estructuras/css/style.css",
				listaEnlazadaTipos
		);
	}

	/**
	 * Click en Lista Enlazada Circular.
	 */
	@FXML
	protected void menuListaEnlazadaCircular() {
		ListaEnlazadaTipos listaEnlazadaTipos = new ListaEnlazadaTipos(ListaEnlazadaTipos.CIRCULAR);
		loadStage(
				resourceBundle.getString("tituloListaEnlazadaCircular"),
				"/cl/cromer/estructuras/fxml/listaEnlazada.fxml",
				"/cl/cromer/estructuras/css/style.css",
				listaEnlazadaTipos
		);
	}

	/**
	 * Click en Lista Enlazada Doble.
	 */
	@FXML
	protected void menuListaEnlazadaDoble() {
		ListaEnlazadaTipos listaEnlazadaTipos = new ListaEnlazadaTipos(ListaEnlazadaTipos.DOBLEMENTE_ENLAZADA);
		loadStage(
				resourceBundle.getString("tituloListaEnlazadaDoble"),
				"/cl/cromer/estructuras/fxml/listaEnlazada.fxml",
				"/cl/cromer/estructuras/css/style.css",
				listaEnlazadaTipos
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
	 * Click en Hash Table.
	 */
	@FXML
	protected void menuHashTable() {
		loadStage(
				resourceBundle.getString("tituloTablaHash"),
				"/cl/cromer/estructuras/fxml/hashTable.fxml",
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
		dialog.getDialogPane().getScene().getWindow().sizeToScene();
		Main.setIcon(dialog, getClass());

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
	 * Cargar el fxml y css.
	 *
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
		dialog.getDialogPane().getScene().getWindow().sizeToScene();
		Main.setIcon(dialog, getClass());

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
		dialog.getDialogPane().getScene().getWindow().sizeToScene();
		Main.setIcon(dialog, getClass());
		dialog.show();
	}
}