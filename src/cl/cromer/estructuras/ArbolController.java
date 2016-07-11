package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
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
	 * Donde poner el contenido de array.
	 */
	@FXML
	private GridPane contenidoArbol;

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

		arbol = null;
		scene = null;

		// TODO: Remove this
		arbol = new Arbol();
		/*arbol.insertar(50);

		arbol.insertar(25);
		arbol.insertar(75);

		arbol.insertar(35);
		arbol.insertar(15);*/

		arbol.insertar(5);
		arbol.insertar(3);
		arbol.insertar(4);
		arbol.insertar(2);
		arbol.insertar(1);
		arbol.insertar(8);
		arbol.insertar(7);
		arbol.insertar(6);
		arbol.insertar(9);
		//arbol.insertar(7);

		/*arbol.insertar(5);
		arbol.insertar(3);
		arbol.insertar(4);
		arbol.insertar(2);
		arbol.insertar(1);
		arbol.insertar(6);
		arbol.insertar(7);
		arbol.insertar(8);
		arbol.insertar(9);*/
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
		generarGrafico();
	}

	/**
	 * Eliminar un valor del arbol y mostrar el codigo en la pantalla.
	 */
	/*@FXML
	protected void botonEliminar() {
		if (scene == null) {
			initializeArbol();
		}

		// Mostrar el codigo
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);

		if (valorArbol.getText() != null && ! valorArbol.getText().trim().equals("")) {
			try {
				boolean exito = arbol.eliminar(Integer.valueOf(valorArbol.getText()));
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
		generarGrafico();
	}*/

	/**
	 * Crear un arbol nuevo.
	 */
	private void initializeArbol() {
		scene = contenidoArbol.getScene();
		// TODO: remove this
		//contenidoArbol.setGridLinesVisible(true);
		grafico = new Grafico(scene);
		//this.arbol = new Arbol();
		Arbol.Tipos tipos = (Arbol.Tipos) scene.getUserData();
	}

	private void generarGrafico() {
		grafico.removerDestacar();

		// Node 0 contains the grid
		//Node node = contenidoArbol.getChildren().get(0);
		contenidoArbol.getChildren().clear();
		//contenidoArbol.getChildren().add(0, node);

		List<List<ArbolNodo>> niveles = arbol.getNiveles();

		int altura = arbol.getAltura() - 1;
		// Thank you Claudio Gutiérrez
		int ancho = (int) Math.pow(2, altura) + (int) ((Math.pow(2, altura)) - 1);

		Text text;
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

		Colores colores = new Colores();
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

					// Check the left for visual conflicts

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