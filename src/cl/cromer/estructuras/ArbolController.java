package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Esta clase es para controlar todos la interfaz de Arbol.
 *
 * @author Chris Cromer
 */
public class ArbolController implements Initializable {
	/**
	 * La caja para ingresar textos.
	 */
	@FXML
	private TextFieldLimited valorArbol;

	/**
	 * El nodo a rotar.
	 */
	@FXML
	private TextFieldLimited valorRotar;

	/**
	 * Donde poner el contenido de array.
	 */
	@FXML
	private GridPane contenidoArbol;

	/**
	 * El contendido del orden.
	 */
	@FXML
	private HBox contenidoOrder;

	/**
	 * Donde va el codigo a mostrar a la pantalla.
	 */
	@FXML
	private Text codigoArbol;

	/**
	 * La escena donde está cosas graficas.
	 */
	private Scene scene;

	/**
	 * Donde está guardado los idiomas.
	 */
	private ResourceBundle resourceBundle;

	/**
	 * El arbol usado en la aplicación.
	 */
	private Arbol arbol;

	/**
	 * Inicializar todos los datos y dibujar las graficas.
	 *
	 * @param location URL: El URL de fxml en uso.
	 * @param resourceBundle ResourceBundle: Tiene datos de idioma.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;

		arbol = null;
		scene = null;
	}

	/**
	 * Llenar un arbol al azar.
	 */
	@FXML
	protected void botonLlenar() {
		if (scene == null) {
			initializeArbol();
		}

		/*Random random = new Random();
		int maximo = 99;
		int minimo = 0;
		int rango = maximo - minimo + 1;

		for (int i = 0; i < 5; i++) {
			if (arbol.size() >= 5) {
				break;
			}
			int numero = random.nextInt(rango) + minimo;
			while (!arbol.insertar(numero)) {
				numero = random.nextInt(rango) + minimo;
				if (arbol.size() >= 5) {
					break;
				}
			}
		}*/

		arbol.insertar(10);
		arbol.insertar(8);
		arbol.insertar(9);
		arbol.insertar(7);
		arbol.insertar(12);
		arbol.insertar(11);
		arbol.insertar(13);

		generarGrafico();
	}


	/**
	 * Vaciar el arbol de todos los valores.
	 */
	@FXML
	protected void botonVaciar() {
		if (scene == null) {
			initializeArbol();
		}

		arbol = new Arbol();
		generarGrafico();
	}

	/**
	 * Insertar un valor al array y mostrar el codigo en la pantalla.
	 */
	@FXML
	protected void botonInsertar() {
		if (scene == null) {
			initializeArbol();
		}

		// Mostrar el codigo
		/*String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);*/

		if (valorArbol.getText() != null && ! valorArbol.getText().trim().equals("")) {
			try {
				boolean exito = arbol.insertar(Integer.valueOf(valorArbol.getText()));
				if (exito) {
					valorArbol.setText("");
					generarGrafico();
				}
				else {
					Main.mostrarError(resourceBundle.getString("arbolValorExiste"), resourceBundle);
				}
			}
			catch (NumberFormatException exception) {
				// El error no es fatal, sigue
				Main.mostrarError(resourceBundle.getString("arbolNoValor"), resourceBundle);
			}
		}
		else {
			Main.mostrarError(resourceBundle.getString("arbolNoValor"), resourceBundle);
		}
	}

	/**
	 * Eliminar un valor del arbol y mostrar el codigo en la pantalla.
	 */
	@FXML
	protected void botonEliminar() {
		if (scene == null) {
			initializeArbol();
		}

		// Mostrar el codigo
		/*String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);*/

		if (valorArbol.getText() != null && ! valorArbol.getText().trim().equals("")) {
			try {
				boolean exito = arbol.eliminar(Integer.valueOf(valorArbol.getText()));
				if (exito) {
					valorArbol.setText("");
					generarGrafico();
				}
				else {
					Main.mostrarError(resourceBundle.getString("arbolNoEsta"), resourceBundle);
				}
			}
			catch (NumberFormatException exception) {
				// El error no es fatal, sigue
				Main.mostrarError(resourceBundle.getString("arbolNoValor"), resourceBundle);
			}
		}
		else {
			Main.mostrarError(resourceBundle.getString("arbolNoValor"), resourceBundle);
		}
	}

	/**
	 * Rotar el nodo a la izquerda.
	 */
	@FXML
	protected void botonRotarIzquerda() {
		if (scene == null) {
			initializeArbol();
		}

		// Mostrar el codigo
		/*String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);*/

		arbol.rotarIzquerda(Integer.valueOf(valorRotar.getText()));

		generarGrafico();
	}

	/**
	 * Rotar el nodo a la izquerda.
	 */
	@FXML
	protected void botonRotarDerecha() {
		if (scene == null) {
			initializeArbol();
		}

		// Mostrar el codigo
		/*String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);*/

		arbol.rotarDerecha(Integer.valueOf(valorRotar.getText()));

		generarGrafico();
	}

	/**
	 * Mostrar los elementos en orden de pre order.
	 */
	@FXML
	protected void botonPreOrder() {
		if (scene == null) {
			initializeArbol();
		}

		// Mostrar el codigo
		/*String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);*/

		Colores colores = new Colores();

		contenidoOrder.getChildren().clear();

		List<ArbolNodo> orden = arbol.preOrder();
		for (ArbolNodo anOrden : orden) {
			contenidoOrder.getChildren().addAll(Grafico.crearCaja(colores, String.valueOf(anOrden.getValor()), String.valueOf(anOrden.getValor())));
			colores.siguinteColor();
		}
	}

	/**
	 * Mostrar los elementos en orden de in order.
	 */
	@FXML
	protected void botonInOrder() {
		if (scene == null) {
			initializeArbol();
		}

		// Mostrar el codigo
		/*String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);*/

		Colores colores = new Colores();

		contenidoOrder.getChildren().clear();

		List<ArbolNodo> orden = arbol.inOrder();
		for (ArbolNodo anOrden : orden) {
			contenidoOrder.getChildren().addAll(Grafico.crearCaja(colores, String.valueOf(anOrden.getValor()), String.valueOf(anOrden.getValor())));
			colores.siguinteColor();
		}
	}

	/**
	 * Mostrar los elementos en orden de post order.
	 */
	@FXML
	protected void botonPostOrder() {
		if (scene == null) {
			initializeArbol();
		}

		// Mostrar el codigo
		/*String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);*/

		Colores colores = new Colores();

		contenidoOrder.getChildren().clear();

		List<ArbolNodo> orden = arbol.postOrder();
		for (ArbolNodo anOrden : orden) {
			contenidoOrder.getChildren().addAll(Grafico.crearCaja(colores, String.valueOf(anOrden.getValor()), String.valueOf(anOrden.getValor())));
			colores.siguinteColor();
		}
	}

	/**
	 * Crear un arbol nuevo.
	 */
	private void initializeArbol() {
		scene = contenidoArbol.getScene();
		// Make the grid line present on the screen
		//contenidoArbol.setGridLinesVisible(true);
		this.arbol = new Arbol();
		Arbol.Tipos tipos = (Arbol.Tipos) scene.getUserData();
	}

	/**
	 * Este metodo generará el grafico de arbol en la ventana.
	 */
	private void generarGrafico() {
		contenidoOrder.getChildren().clear();

		Colores colores = new Colores();

		for (int i = 0; i < arbol.size(); i++) {
			contenidoOrder.getChildren().addAll(Grafico.crearCaja(colores, String.valueOf(i)));
			colores.siguinteColor();
		}

		// Node 0 contains the grid lines, get them and restore them in the new drawing.
		//Node node = contenidoArbol.getChildren().get(0);
		contenidoArbol.getChildren().clear();
		//contenidoArbol.getChildren().add(0, node);

		List<List<ArbolNodo>> niveles = arbol.getNiveles();

		int altura = arbol.getAltura() - 1;
		// Thank you Claudio Gutiérrez
		int ancho = (int) Math.pow(2, altura) + (int) ((Math.pow(2, altura)) - 1);

		Text text;
		if (altura == 0) {
			contenidoArbol.addColumn(0);
			text = new Text();
			text.setText(" ");
			text.setId(0 + "_" + 0);
			contenidoArbol.add(text, 0, 0);
		}
		for (int i = 0; i < altura; i++) {
			contenidoArbol.addRow(i);
			for (int j = 0; j < ancho; j++) {
				contenidoArbol.addColumn(j);
				text = new Text();
				text.setText(" ");
				text.setId(j + "_" + i);
				contenidoArbol.add(text, j, i);
			}
		}

		colores = new Colores();
		int k;
		int l = 0;
		for (int i = niveles.size() - 1; i >= 0 ; i--) {
			k = l;
			for (int j = 0; j < niveles.get(i).size(); j++) {
				if (niveles.get(i).get(j) != null) {
					niveles.get(i).get(j).setX(k);
					if (niveles.get(i).get(j).getIzquerda() != null) {
						k = niveles.get(i).get(j).getIzquerda().getX() + 1;
						niveles.get(i).get(j).setX(k);
						contenidoArbol.add(Grafico.crearCirculo(colores, j + "_" + i), niveles.get(i).get(j).getIzquerda().getX() + 1, i);
					}
					else if (niveles.get(i).get(j).getDerecha() != null) {
						k = niveles.get(i).get(j).getDerecha().getX() - 1;
						niveles.get(i).get(j).setX(k);
						contenidoArbol.add(Grafico.crearCirculo(colores, j + "_" + i), niveles.get(i).get(j).getDerecha().getX() - 1, i);
					}
					else {
						contenidoArbol.add(Grafico.crearCirculo(colores, j + "_" + i), k, i);
					}

					// Check the right for visual conflicts
					if (niveles.get(i).get(j).getDerecha() != null && niveles.get(i).get(j).getDerecha().getX() > k + 1) {
						int parentX = niveles.get(i).get(j).getX();
						int childX = niveles.get(i).get(j).getDerecha().getX();
						for (int m = parentX + 1; m < childX; m++) {
							contenidoArbol.add(Grafico.crearLineaHorizontal(), m, i);
						}
					}

					colores.siguinteColor();
					text = (Text) scene.lookup("#texto_" + j + "_" + i);
					text.setText(String.valueOf(niveles.get(i).get(j).getValor()));

					if (i != 0) {
						if (niveles.get(i).get(j).getPadre().getIzquerda() == niveles.get(i).get(j)) {
							// El hijo está a la izquerda
							contenidoArbol.add(Grafico.crearEsquinaDerecha(), k, i - 1);
						}
						else {
							// El hijo está a la derecha
							contenidoArbol.add(Grafico.crearEsquinaIzquerda(), k, i - 1);
						}
					}
					k++;
				}
				else {
					k++;
				}
				k++;
			}
			l++;
		}
	}
}