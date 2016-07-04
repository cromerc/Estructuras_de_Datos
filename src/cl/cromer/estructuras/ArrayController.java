package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Esta clase es para controlar todos la interfaz de Array.
 *
 * @author Chris Cromer
 */
public class ArrayController implements Initializable {

	/**
	 * La caja para ingresar textos.
	 */
	@FXML
	private TextFieldLimited valorArray;

	/**
	 * Donde poner el contenido de array.
	 */
	@FXML
	private VBox contenidoArray;

	/**
	 * Donde va el codigo a mostrar a la pantalla.
	 */
	@FXML
	private Text codigoArray;

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
	 *
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

		array.llenar();
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
		String tipo = (array.isOrdered()) ? "Ordenado" : "Simple";
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);

		if (valorArray.getText() != null && ! valorArray.getText().trim().equals("")) {
			try {
				boolean exito = array.insertar(Integer.valueOf(valorArray.getText()));
				if (exito) {
					valorArray.setText("");
					generarGrafico();
				}
				else {
					if (array.size() == 10) {
						Main.mostrarError(resourceBundle.getString("arrayLleno"), resourceBundle);
					}
					else {
						Main.mostrarError(resourceBundle.getString("arrayValorExiste"), resourceBundle);
					}
				}
			}
			catch (NumberFormatException exception) {
				// El error no es fatal, sigue
				Logs.log(Level.WARNING, "No es tipo int.");
				Main.mostrarError(resourceBundle.getString("arrayNoValor"), resourceBundle);
			}
		}
		else {
			Main.mostrarError(resourceBundle.getString("arrayNoValor"), resourceBundle);
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
		String tipo = (array.isOrdered()) ? "Ordenado" : "Simple";
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/eliminar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);

		try {
			if (valorArray.getText() != null && ! valorArray.getText().trim().equals("")) {
				boolean exito = array.eliminar(Integer.valueOf(valorArray.getText()));
				if (exito) {
					valorArray.setText("");
					generarGrafico();
				}
				else {
					Main.mostrarError(resourceBundle.getString("arrayNoEsta"), resourceBundle);
				}
			}
			else {
				Main.mostrarError(resourceBundle.getString("arrayNoValor"), resourceBundle);
			}
		}
		catch (NumberFormatException exception) {
			// El error no es fatal, sigue
			Logs.log(Level.WARNING, "No es tipo int.");
			Main.mostrarError(resourceBundle.getString("arrayNoValor"), resourceBundle);
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
		String tipo = (array.isOrdered()) ? "Ordenado" : "Simple";
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/buscar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);

		try {
			if (valorArray.getText() != null && ! valorArray.getText().trim().equals("")) {
				int encontrado = array.buscar(Integer.valueOf(valorArray.getText()));
				if (encontrado != - 1) {
					generarGrafico();
					grafico = new Grafico(scene);
					grafico.destacar("#caja_" + encontrado, Grafico.RECTANGULO);
					grafico.destacar("#texto_" + encontrado, Grafico.TEXTO);
				}
				else {
					Main.mostrarError(resourceBundle.getString("arrayNoEsta"), resourceBundle);
				}
			}
			else {
				Main.mostrarError(resourceBundle.getString("arrayNoValor"), resourceBundle);
			}
		}
		catch (NumberFormatException exception) {
			// El error no es fatal, sigue
			Logs.log(Level.WARNING, "No es tipo int.");
			Main.mostrarError(resourceBundle.getString("arrayNoValor"), resourceBundle);
		}
	}

	/**
	 * Crear el array de tamaño 10. La scene está usado para saber si es de tipo ordenado o simple segun el ménu.
	 */
	private void initializeArray() {
		scene = contenidoArray.getScene();
		grafico = new Grafico(scene);
		this.array = new Array(10);
		Array.Tipos arrayTipos = (Array.Tipos) scene.getUserData();
		if (arrayTipos.getTipo() == Array.Tipos.ORDENADO) {
			this.array.setOrdered(true);
		}
	}

	/**
	 * Poner los valores en el grafico.
	 */
	private void generarGrafico() {
		grafico.removerDestacar();
		for (int i = 0; i < 10; i++) {
			Text text = (Text) scene.lookup("#texto_" + String.valueOf(i));
			text.setText(array.getIndice(i));
		}
	}
}
