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
 * Esta clase es para controlar todos la interfaz de TablaHash.
 *
 * @author Chris Cromer
 */
public class TablaHashController implements Initializable {
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
	 * Donde poner el contenido de tablaHash.
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
	 * El tablaHash usado en la aplicación.
	 */
	private TablaHash tablaHash;

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
			contenidoHashTable.getChildren().addAll(Grafico.crearHashCajas(colores, String.valueOf(i)));
			colores.siguinteColor();
		}
	}

	/**
	 * Llenar el tablaHash con numeros al azar.
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
			while (! tablaHash.insertar(palabras.getPalabra(), numero)) {
				if (tablaHash.size() == 10) {
					break;
				}
			}
		}
		generarGrafico();
	}

	/**
	 * Vaciar el tablaHash de todos los valores.
	 */
	@FXML
	protected void botonVaciar() {
		if (scene == null) {
			initializeHashTable();
		}

		tablaHash = new TablaHash(10);
		generarGrafico();
	}

	/**
	 * Insertar un valor al tablaHash y mostrar el codigo en la pantalla.
	 */
	@FXML
	protected void botonInsertar() {
		if (scene == null) {
			initializeHashTable();
		}

		// Mostrar el codigo
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/tablaHash/insertar")).useDelimiter("\\Z").next();
		codigoHashTable.setText(codigoTexto);

		if (llaveHashTable.getText() != null && ! llaveHashTable.getText().trim().equals("") && valorHashTable.getText() != null && ! valorHashTable.getText().trim().equals("")) {
			try {
				boolean exito = tablaHash.insertar(llaveHashTable.getText().trim(), Integer.valueOf(valorHashTable.getText()));
				if (exito) {
					llaveHashTable.setText("");
					valorHashTable.setText("");
					generarGrafico();
				}
				else {
					if (tablaHash.size() == 10) {
						Main.mostrarError(resourceBundle.getString("tablaHashLleno"), resourceBundle);
					}
					else {
						Main.mostrarError(resourceBundle.getString("tablaHashLlaveExiste"), resourceBundle);
					}
				}
			}
			catch (NumberFormatException exception) {
				// El error no es fatal, sigue
				Logs.log(Level.WARNING, "No es tipo int.");
				Main.mostrarError(resourceBundle.getString("tablaHashNoLlave"), resourceBundle);
			}
		}
		else {
			Main.mostrarError(resourceBundle.getString("tablaHashNoLlave"), resourceBundle);
		}
	}

	/**
	 * Eliminar un valor del tablaHash si existe y mostrar el codigo en la pantalla.
	 */
	@FXML
	protected void botonEliminar() {
		if (scene == null) {
			initializeHashTable();
		}

		// Mostrar el codigo
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/tablaHash/eliminar")).useDelimiter("\\Z").next();
		codigoHashTable.setText(codigoTexto);

		try {
			if (llaveHashTable.getText() != null && ! llaveHashTable.getText().trim().equals("")) {
				boolean exito = tablaHash.eliminar(llaveHashTable.getText());
				if (exito) {
					llaveHashTable.setText("");
					valorHashTable.setText("");
					generarGrafico();
				}
				else {
					Main.mostrarError(resourceBundle.getString("tablaHashNoEsta"), resourceBundle);
				}
			}
			else {
				Main.mostrarError(resourceBundle.getString("tablaHashNoLlave"), resourceBundle);
			}
		}
		catch (NumberFormatException exception) {
			// El error no es fatal, sigue
			Logs.log(Level.WARNING, "No es tipo int.");
			Main.mostrarError(resourceBundle.getString("tablaHashNoLlave"), resourceBundle);
		}
	}

	/**
	 * Buscar si existe un elemento en el tablaHash y mostrar el codigo en la pantalla
	 * Si existe el valor destacarlo.
	 */
	@FXML
	protected void botonBuscar() {
		if (scene == null) {
			initializeHashTable();
		}

		// Mostrar el codigo
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/tablaHash/buscar")).useDelimiter("\\Z").next();
		codigoHashTable.setText(codigoTexto);

		try {
			if (llaveHashTable.getText() != null && ! llaveHashTable.getText().trim().equals("")) {
				HashItem hashItem = tablaHash.buscar(llaveHashTable.getText());
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
					Main.mostrarError(resourceBundle.getString("tablaHashNoEsta"), resourceBundle);
				}
			}
			else {
				Main.mostrarError(resourceBundle.getString("tablaHashNoLlave"), resourceBundle);
			}
		}
		catch (NumberFormatException exception) {
			// El error no es fatal, sigue
			Logs.log(Level.WARNING, "No es tipo int.");
			Main.mostrarError(resourceBundle.getString("tablaHashNoLlave"), resourceBundle);
		}
	}

	/**
	 * Crear el tablaHash de tamaño 10.
	 */
	private void initializeHashTable() {
		scene = contenidoHashTable.getScene();
		grafico = new Grafico(scene);
		this.tablaHash = new TablaHash(10);
	}

	/**
	 * Poner los valores en el grafico.
	 */
	private void generarGrafico() {
		grafico.removerDestacar();
		for (int i = 0; i < 10; i++) {
			if (tablaHash.getIndice(i) != null) {
				Text text = (Text) scene.lookup("#indice_texto_" + String.valueOf(i));
				text.setText(String.valueOf(i));
				text = (Text) scene.lookup("#llave_texto_" + String.valueOf(i));
				text.setText(tablaHash.getIndice(i).getLlave());
				text = (Text) scene.lookup("#valor_texto_" + String.valueOf(i));
				text.setText(String.valueOf(tablaHash.getIndice(i).getValor()));
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
