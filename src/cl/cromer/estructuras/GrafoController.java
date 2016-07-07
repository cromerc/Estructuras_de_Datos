package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Esta clase es para controlar todos la interfaz de Arbol.
 *
 * @author Chris Cromer
 */
public class GrafoController implements Initializable {
	/**
	 * La caja para el valor.
	 */
	@FXML
	private TextFieldLimited valorGrafo;

	/**
	 * La caja por nodo 1.
	 */
	@FXML
	private TextFieldLimited valorNodo1;

	/**
	 * La caja por nodo 2.
	 */
	@FXML
	private TextFieldLimited valorNodo2;

	/**
	 * Donde poner el contenido de array.
	 */
	@FXML
	private Canvas contenidoGrafo;

	/**
	 * Donde va el codigo a mostrar a la pantalla.
	 */
	@FXML
	private Text codigoGrafo;

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
	private GrafoNoDirigido<Node> grafoNoDirigido;

	/**
	 * Grafico rectangulos.
	 */
	private Grafico grafico;

	/**
	 * Nodos.
	 */
	private Node[] nodes;

	/**
	 * Inicializar todos los datos y dibujar las graficas.
	 *
	 * @param location URL: El URL de fxml en uso.
	 * @param resourceBundle ResourceBundle: Tiene datos de idioma.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;

		grafoNoDirigido = null;
		scene = null;
	}

	@FXML
	protected void botonLlenar() {
		if (scene == null) {
			initializeGrafo();
		}

		Array array = new Array(5);
		for (int i = 0; i < 5; i++) {
			if (nodes[i] != null) {
				array.insertar(nodes[i].getValue());
			}
		}

		for (int i = 0; i < 5; i++) {
			if (nodes[i] == null) {
				Random random = new Random();
				int maximo = 99;
				int minimo = 0;
				int rango = maximo - minimo + 1;

				int numero = random.nextInt(rango) + minimo;
				// Check if value is in array
				while (array.buscar(numero) != -1) {
					numero = random.nextInt(rango) + minimo;
				}

				nodes[i] = new Node(numero);
				grafoNoDirigido.addNode(nodes[i]);
			}
		}
		generarGrafico();
	}

	@FXML
	protected void botonVaciar() {
		if (scene == null) {
			initializeGrafo();
		}

		this.grafoNoDirigido = new GrafoNoDirigido<>();
		this.nodes = new Node[5];
		generarGrafico();
	}

	/**
	 * Insertar un valor al array y mostrar el codigo en la pantalla.
	 */
	@FXML
	protected void botonInsertar() {
		if (scene == null) {
			initializeGrafo();
		}

		// Mostrar el codigo
		/*String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);*/

		if (valorGrafo.getText() != null && ! valorGrafo.getText().trim().equals("")) {
			try {
				int i;
				for (i = 0; i < 5; i++) {
					if (nodes[i] == null) {
						nodes[i] = new Node(Integer.valueOf(valorGrafo.getText()));
						break;
					}
					else if (nodes[i].getValue() == Integer.valueOf(valorGrafo.getText())) {
						Main.mostrarError(resourceBundle.getString("grafoNodoExiste"), resourceBundle);
						i = -1;
						break;
					}
				}

				if (i == 5) {
					// Maximo 5 nodos
					Main.mostrarError(resourceBundle.getString("grafoLleno"), resourceBundle);
				}
				else if (i != -1) {
					boolean exito = grafoNoDirigido.addNode(nodes[i]);
					if (exito) {
						valorGrafo.setText("");
						generarGrafico();
					}
					else {
						Main.mostrarError(resourceBundle.getString("grafoNodoExiste"), resourceBundle);
					}
				}
			}
			catch (NumberFormatException exception) {
				// El error no es fatal, sigue
				Main.mostrarError(resourceBundle.getString("grafoNoNumero"), resourceBundle);
			}
		}
		else {
			Main.mostrarError(resourceBundle.getString("grafoNoNumero"), resourceBundle);
		}
	}

	/**
	 * Insertar un valor al array y mostrar el codigo en la pantalla.
	 */
	@FXML
	protected void botonEliminar() {
		if (scene == null) {
			initializeGrafo();
		}

		// Mostrar el codigo
		/*String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/array" + tipo + "/insertar")).useDelimiter("\\Z").next();
		codigoArray.setText(codigoTexto);*/

		if (valorGrafo.getText() != null && ! valorGrafo.getText().trim().equals("")) {
			try {
				int i;
				for (i = 0; i < 5; i++) {
					if (nodes[i] != null && nodes[i].getValue() == Integer.valueOf(valorGrafo.getText())) {
						break;
					}
				}

				if (i != 5) {
					boolean exito = grafoNoDirigido.removeNode(nodes[i]);
					if (exito) {
						nodes[i] = null;
						valorGrafo.setText("");
						generarGrafico();
					}
					else {
						Main.mostrarError(resourceBundle.getString("grafoNoNodo"), resourceBundle);
					}
				}
				else {
					Main.mostrarError(resourceBundle.getString("grafoNoNodo"), resourceBundle);
				}
			}
			catch (NumberFormatException exception) {
				// El error no es fatal, sigue
				Main.mostrarError(resourceBundle.getString("grafoNoNumero"), resourceBundle);
			}
		}
		else {
			Main.mostrarError(resourceBundle.getString("grafoNoNumero"), resourceBundle);
		}
	}

	/**
	 * Insertar un edge al grafo y mostrar el codigo en la pantalla.
	 */
	@FXML
	protected void botonInsertarEdge() {
		if (scene == null) {
			initializeGrafo();
		}

		if (valorNodo1.getText() != null && ! valorNodo1.getText().trim().equals("") && valorNodo2.getText() != null && ! valorNodo2.getText().trim().equals("")) {
			Node[] nodos = getNodosEdge();

			if (nodos[0] == null || nodos[1] == null || !grafoNoDirigido.nodeExists(nodos[0]) || !grafoNoDirigido.nodeExists(nodos[1])) {
				Main.mostrarError(resourceBundle.getString("grafoNoNodo"), resourceBundle);
			}
			else if (grafoNoDirigido.edgeExists(nodos[0], nodos[1])) {
				Main.mostrarError(resourceBundle.getString("grafoEdgeExiste"), resourceBundle);
			}
			else {
				grafoNoDirigido.addEdge(nodos[0], nodos[1]);
			}
		}
		else {
			Main.mostrarError(resourceBundle.getString("grafoNoNumero"), resourceBundle);
		}

		generarGrafico();
	}

	/**
	 * Insertar un edge al grafo y mostrar el codigo en la pantalla.
	 */
	@FXML
	protected void botonEliminarEdge() {
		if (scene == null) {
			initializeGrafo();
		}

		if (valorNodo1.getText() != null && ! valorNodo1.getText().trim().equals("") && valorNodo2.getText() != null && ! valorNodo2.getText().trim().equals("")) {
			Node[] nodos = getNodosEdge();

			if (nodos[0] == null || nodos[1] == null || !grafoNoDirigido.nodeExists(nodos[0]) || !grafoNoDirigido.nodeExists(nodos[1])) {
				Main.mostrarError(resourceBundle.getString("grafoNoNodo"), resourceBundle);
			}
			else if (!grafoNoDirigido.edgeExists(nodos[0], nodos[1])) {
				Main.mostrarError(resourceBundle.getString("grafoNoEdge"), resourceBundle);
			}
			else {
				grafoNoDirigido.removeEdge(nodos[0], nodos[1]);
			}
		}
		else {
			Main.mostrarError(resourceBundle.getString("grafoNoEdge"), resourceBundle);
		}

		generarGrafico();
	}

	/**
	 * Crear un grafo nuevo.
	 */
	private void initializeGrafo() {
		scene = contenidoGrafo.getScene();
		grafico = new Grafico(scene);
		this.grafoNoDirigido = new GrafoNoDirigido<>();
		this.nodes = new Node[5];
	}

	/**
	 * Devolver los nodos que existen.
	 *
	 * @return Node[]: Los nodos que se busca.
	 */
	private Node[] getNodosEdge() {
		Node[] nodos = new Node[2];
		for (int i = 0; i < 5; i++) {
			if (nodes[i] != null) {
				if (Integer.valueOf(valorNodo1.getText()) == nodes[i].getValue()) {
					nodos[0] = nodes[i];
				}
				if (Integer.valueOf(valorNodo2.getText()) == nodes[i].getValue()) {
					nodos[1] = nodes[i];
				}
				if (nodos[0] != null && nodos[1] != null) {
					break;
				}
			}
		}
		return nodos;
	}

	/**
	 * Generar la canvas con el grafo.
	 */
	private void generarGrafico() {
		grafico.removerDestacar();

		Colores colores = new Colores();

		GraphicsContext graphicsContext = contenidoGrafo.getGraphicsContext2D();
		graphicsContext.clearRect(0, 0, contenidoGrafo.getWidth(), contenidoGrafo.getHeight());

		if (nodes[0] != null) {
			graphicsContext.setFill(colores.getFondo());
			graphicsContext.setStroke(colores.getBorder());

			graphicsContext.fillOval(10, 10, 40, 40);
			graphicsContext.strokeOval(10, 10, 40, 40);

			graphicsContext.setFill(colores.getTexto());
			int x = textX(25, String.valueOf(nodes[0].getValue()));
			graphicsContext.fillText(String.valueOf(nodes[0].getValue()), x, 35);
		}
		colores.siguinteColor();

		if (nodes[1] != null) {
			graphicsContext.setFill(colores.getFondo());
			graphicsContext.setStroke(colores.getBorder());

			graphicsContext.fillOval(210, 10, 40, 40);
			graphicsContext.strokeOval(210, 10, 40, 40);

			graphicsContext.setStroke(colores.getTexto());
			int x = textX(225, String.valueOf(nodes[1].getValue()));
			graphicsContext.strokeText(String.valueOf(nodes[1].getValue()), x, 35);
		}
		colores.siguinteColor();

		if (nodes[2] != null) {
			graphicsContext.setFill(colores.getFondo());
			graphicsContext.setStroke(colores.getBorder());

			graphicsContext.fillOval(10, 210, 40, 40);
			graphicsContext.strokeOval(10, 210, 40, 40);

			graphicsContext.setStroke(colores.getTexto());
			int x = textX(25, String.valueOf(nodes[2].getValue()));
			graphicsContext.strokeText(String.valueOf(nodes[2].getValue()), x, 235);
		}
		colores.siguinteColor();

		if (nodes[3] != null) {
			graphicsContext.setFill(colores.getFondo());
			graphicsContext.setStroke(colores.getBorder());

			graphicsContext.fillOval(210, 210, 40, 40);
			graphicsContext.strokeOval(210, 210, 40, 40);

			graphicsContext.setStroke(colores.getTexto());
			int x = textX(225, String.valueOf(nodes[3].getValue()));
			graphicsContext.strokeText(String.valueOf(nodes[3].getValue()), x, 235);
		}
		colores.siguinteColor();

		if (nodes[4] != null) {
			graphicsContext.setFill(colores.getFondo());
			graphicsContext.setStroke(colores.getBorder());

			graphicsContext.fillOval(105, 410, 40, 40);
			graphicsContext.strokeOval(105, 410, 40, 40);

			graphicsContext.setStroke(colores.getTexto());
			int x = textX(120, String.valueOf(nodes[4].getValue()));
			graphicsContext.strokeText(String.valueOf(nodes[4].getValue()), x, 435);
		}

		graphicsContext.setStroke(colores.getBorder());



		// Line between 0 and 0.
		if (nodes[0] != null && grafoNoDirigido.edgeExists(nodes[0], nodes[0])) {
			graphicsContext.strokeArc(15, 40, 29, 30, 145, 250, ArcType.OPEN);
		}
		// Line between 1 and 1.
		if (nodes[1] != null && grafoNoDirigido.edgeExists(nodes[1], nodes[1])) {
			graphicsContext.strokeArc(215, 40, 29, 30, 145, 250, ArcType.OPEN);
		}
		// Line between 2 and 2.
		if (nodes[2] != null && grafoNoDirigido.edgeExists(nodes[2], nodes[2])) {
			graphicsContext.strokeArc(15, 240, 29, 30, 145, 250, ArcType.OPEN);
		}
		// Line between 3 and 3.
		if (nodes[3] != null && grafoNoDirigido.edgeExists(nodes[3], nodes[3])) {
			graphicsContext.strokeArc(215, 240, 29, 30, 145, 250, ArcType.OPEN);
		}
		// Line between 4 and 4.
		if (nodes[4] != null && grafoNoDirigido.edgeExists(nodes[4], nodes[4])) {
			graphicsContext.strokeArc(110, 440, 29, 30, 145, 250, ArcType.OPEN);
		}

		// Line between 0 and 1.
		if (nodes[0] != null && nodes[1] != null && grafoNoDirigido.edgeExists(nodes[0], nodes[1])) {
			graphicsContext.strokeLine(50, 30, 210, 30);
		}
		// Line between 0 and 2.
		if (nodes[0] != null && nodes[2] != null && grafoNoDirigido.edgeExists(nodes[0], nodes[2])) {
			graphicsContext.strokeLine(30, 50, 30, 210);
		}
		// Line between 0 and 3.
		if (nodes[0] != null && nodes[3] != null && grafoNoDirigido.edgeExists(nodes[0], nodes[3])) {
			graphicsContext.strokeLine(45, 45, 215, 215);
		}
		// Line between 0 and 4.
		if (nodes[0] != null && nodes[4] != null && grafoNoDirigido.edgeExists(nodes[0], nodes[4])) {
			graphicsContext.strokeLine(38, 50, 125, 410);
		}
		// Line between 1 and 2.
		if (nodes[1] != null && nodes[2] != null && grafoNoDirigido.edgeExists(nodes[1], nodes[2])) {
			graphicsContext.strokeLine(45, 215, 215, 45);
		}
		// Line between 1 and 3.
		if (nodes[1] != null && nodes[3] != null && grafoNoDirigido.edgeExists(nodes[1], nodes[3])) {
			graphicsContext.strokeLine(230, 50, 230, 210);
		}
		// Line between 1 and 4.
		if (nodes[1] != null && nodes[4] != null && grafoNoDirigido.edgeExists(nodes[1], nodes[4])) {
			graphicsContext.strokeLine(221, 50, 125, 410);
		}
		// Line between 2 and 3
		if (nodes[2] != null && nodes[3] != null && grafoNoDirigido.edgeExists(nodes[2], nodes[3])) {
			graphicsContext.strokeLine(50, 230, 210, 230);
		}
		// Line between 2 and 4.
		if (nodes[2] != null && nodes[4] != null && grafoNoDirigido.edgeExists(nodes[2], nodes[4])) {
			graphicsContext.strokeLine(38, 250, 125, 410);
		}
		// Line between 3 and 4.
		if (nodes[3] != null && nodes[4] != null && grafoNoDirigido.edgeExists(nodes[3], nodes[4])) {
			graphicsContext.strokeLine(221, 250, 125, 410);
		}
	}

	/**
	 * Calcular la posición del texto basado en la cantidad de caracters.
	 *
	 * @param x int: La posición donde desea el texto.
	 * @param texto String: El texto a posicionar.
	 *
	 * @return int: La posición nueva.
	 */
	private int textX(int x, String texto) {
		if (texto.length() == 1) {
			return x;
		}
		else if (texto.length() == 2) {
			return x - 3;
		}
		else {
			return x - 7;
		}
	}
}