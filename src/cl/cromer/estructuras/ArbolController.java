package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
		arbol.insertar(4);
		arbol.insertar(3);
		arbol.insertar(2);
		arbol.insertar(1);
		arbol.insertar(6);
		arbol.insertar(7);
		arbol.insertar(8);
		arbol.insertar(9);
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

		/*if (valorArbol.getText() != null && ! valorArbol.getText().trim().equals("")) {
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
				Logs.log(Level.WARNING, "No es tipo int.");
				Main.mostrarError(resourceBundle.getString("arbolNoValor"), resourceBundle);
			}
		}
		else {
			Main.mostrarError(resourceBundle.getString("arbolNoValor"), resourceBundle);
		}*/
		generarGrafico();
	}

	/**
	 * Crear un arbol nuevo.
	 */
	private void initializeArbol() {
		scene = contenidoArbol.getScene();
		// TODO: remove this
		contenidoArbol.setGridLinesVisible(true);
		grafico = new Grafico(scene);
		//this.arbol = new Arbol();
		Arbol.Tipos tipos = (Arbol.Tipos) scene.getUserData();
	}

	private void generarGrafico() {
		grafico.removerDestacar();
		Node node = contenidoArbol.getChildren().get(0);
		contenidoArbol.getChildren().clear();
		contenidoArbol.getChildren().add(0, node);

		List<List<ArbolNodo>> niveles = arbol.getNiveles();

		Colores colores = new Colores();
		int k = niveles.get(niveles.size() - 1).size();
		for (int i = niveles.size() - 1; i >= 0 ; i--) {
			int l = k - niveles.get(i).size();
			if (i != niveles.size() - 1) {
				l--;
			}
			for (int j = 0; j < niveles.get(i).size(); j++) {
		        contenidoArbol.add(Grafico.crearCirculo(colores, j + "_" + i), l, i);
				colores.siguinteColor();
				if (niveles.get(i).get(j) != null) {
					Text text = (Text) scene.lookup("#texto_" + j + "_" + i);
					text.setText(String.valueOf(niveles.get(i).get(j).getValor()));
				}
				l++;
			}
		}
	}

	/**
	 * Poner los valores en el grafico.
	 */
	/*private void generarGrafico() {
		grafico.removerDestacar();
		Node node = contenidoArbol.getChildren().get(0);
		contenidoArbol.getChildren().clear();
		contenidoArbol.getChildren().add(0, node);

		Text text;

		int ancho = arbol.getAncho();
		if (ancho % 2 == 0) {
			ancho++;
		}
		for (int i = 0; i < arbol.getAltura(); i++) {
			contenidoArbol.addRow(i);
			for (int j = 0; j < ancho; j++) {
				contenidoArbol.addColumn(j);
				text = new Text();
				text.setText(" ");
				text.setId(j + "_" + i);
				contenidoArbol.add(text, j, i);
			}
		}

		int medio = ancho / 2;

		ArbolNodo tempArbol = this.arbol.getArbol();
		Stack globalStack = new Stack();
		globalStack.push(tempArbol);
		boolean filaVacio = false;
		int x = medio;
		int y = 0;
		Colores colores = new Colores();
		while (!filaVacio) {
			Stack localStack = new Stack();
			filaVacio = true;

			text = new Text();
			text.setText(" ");
			text.setId(x + "_" + y);
			contenidoArbol.add(text, x, y);

			while (!globalStack.isEmpty()) {
				ArbolNodo temp = (ArbolNodo) globalStack.pop();
				if (temp != null) {
					//System.out.print(temp.iData);
					text = new Text();
					text.setText(String.valueOf(temp.getValor()));
					text.setId(x + "_" + y);
					contenidoArbol.add(Grafico.crearCirculo(colores, x + "_" + y), x, y);
					colores.siguinteColor();
					localStack.push(temp.getIzquerda());
					localStack.push(temp.getDerecha());

					if(temp.getIzquerda() != null ||
					   temp.getDerecha() != null)
						filaVacio = false;
				}
				else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				x++;
			}
			y++;
			x = 0;
			// Next level
			while(!localStack.isEmpty())
				globalStack.push( localStack.pop() );
		}
	}*/
}