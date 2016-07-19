package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

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
	 * La caja por el peso.
	 */
	@FXML
	private TextFieldLimited valorPeso;

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
	 * Tipo de grafoNoDirigido.
	 */
	private Grafo.Tipos grafoTipos;

	/**
	 * El grafoNoDirigido dirigido usado en la aplicación.
	 */
	private Grafo.Dirigido<GrafoNodo> dirigido;

	/**
	 * El grafoNoDirigido no dirigido usado en la aplicación.
	 */
	private Grafo.NoDirigido<GrafoNodo> noDirigido;

	/**
	 * Grafico rectangulos.
	 */
	private Grafico grafico;

	/**
	 * Nodos.
	 */
	private GrafoNodo[] grafoNodos;

	/**
	 * Un peso estatico.
	 */
	final static private int PESO_PREDETERMINADO = 0;

	/**
	 * Inicializar todos los datos y dibujar las graficas.
	 *
	 * @param location URL: El URL de fxml en uso.
	 * @param resourceBundle ResourceBundle: Tiene datos de idioma.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;

		noDirigido = null;
		scene = null;
	}

	/**
	 * LLenar el grafoNoDirigido con nodos y edges al azar.
	 */
	@FXML
	protected void botonLlenar() {
		if (scene == null) {
			initializeGrafo();
		}

		Random random = new Random();
		int maximo = 99;
		int minimo = 0;
		int rango = maximo - minimo + 1;

		Array array = new Array(5);
		for (int i = 0; i < 5; i++) {
			if (grafoNodos[i] != null) {
				array.insertar(grafoNodos[i].getValue());
			}
		}

		if (grafoTipos.getTipo() == Grafo.Tipos.NO_DIRIGIDO) {
			for (int i = 0; i < 5; i++) {
				if (grafoNodos[i] == null) {

					int numero = random.nextInt(rango) + minimo;
					// Check if value is in array
					while (array.buscar(numero) != - 1) {
						numero = random.nextInt(rango) + minimo;
					}

					grafoNodos[i] = new GrafoNodo(numero);

					noDirigido.addNode(grafoNodos[i]);
				}
			}
			noDirigido.addEdge(grafoNodos[0], grafoNodos[1]);
			noDirigido.addEdge(grafoNodos[1], grafoNodos[2]);
			noDirigido.addEdge(grafoNodos[2], grafoNodos[4]);
			noDirigido.addEdge(grafoNodos[4], grafoNodos[3]);
			noDirigido.addEdge(grafoNodos[3], grafoNodos[1]);
		}
		else {
			for (int i = 0; i < 5; i++) {
				if (grafoNodos[i] == null) {

					int numero = random.nextInt(rango) + minimo;
					// Check if value is in array
					while (array.buscar(numero) != - 1) {
						numero = random.nextInt(rango) + minimo;
					}

					grafoNodos[i] = new GrafoNodo(numero);

					Grafo.Vertex<GrafoNodo> vertex = new Grafo.Vertex<>(String.valueOf(grafoNodos[i].getValue()));
					vertex.setData(grafoNodos[i]);
					dirigido.addVertex(vertex);
				}
			}
			dirigido.addEdge(dirigido.getVertex(0), dirigido.getVertex(1), PESO_PREDETERMINADO);
			dirigido.addEdge(dirigido.getVertex(1), dirigido.getVertex(2), PESO_PREDETERMINADO);
			dirigido.addEdge(dirigido.getVertex(2), dirigido.getVertex(4), PESO_PREDETERMINADO);
			dirigido.addEdge(dirigido.getVertex(2), dirigido.getVertex(0), PESO_PREDETERMINADO);
			dirigido.addEdge(dirigido.getVertex(4), dirigido.getVertex(3), PESO_PREDETERMINADO);
			dirigido.addEdge(dirigido.getVertex(3), dirigido.getVertex(1), PESO_PREDETERMINADO);
		}

		generarGrafico();
	}

	/**
	 * Eliminar el grafoNoDirigido.
	 */
	@FXML
	protected void botonVaciar() {
		if (scene == null) {
			initializeGrafo();
		}

		if (grafoTipos.getTipo() == Grafo.Tipos.NO_DIRIGIDO) {
			this.noDirigido = new Grafo.NoDirigido<>();
		}
		else {
			this.dirigido = new Grafo.Dirigido<>();
		}
		this.grafoNodos = new GrafoNodo[5];
		generarGrafico();
	}

	/**
	 * Insertar un nodo en el grafoNoDirigido.
	 */
	@FXML
	protected void botonInsertar() {
		if (scene == null) {
			initializeGrafo();
		}

		// Mostrar el codigo
		String tipo = getTipoString();
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/grafo" + tipo + "/insertarNodo")).useDelimiter("\\Z").next();
		codigoGrafo.setText(codigoTexto);

		if (valorGrafo.getText() != null && ! valorGrafo.getText().trim().equals("")) {
			try {
				int i;
				for (i = 0; i < 5; i++) {
					if (grafoNodos[i] == null) {
						grafoNodos[i] = new GrafoNodo(Integer.valueOf(valorGrafo.getText()));
						break;
					}
					else if (grafoNodos[i].getValue() == Integer.valueOf(valorGrafo.getText())) {
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
					boolean exito;
					if (grafoTipos.getTipo() == Grafo.Tipos.NO_DIRIGIDO) {
						exito = noDirigido.addNode(grafoNodos[i]);
					}
					else {
						Grafo.Vertex<GrafoNodo> vertex = new Grafo.Vertex<>(String.valueOf(grafoNodos[i].getValue()));
						vertex.setData(grafoNodos[i]);
						exito = dirigido.addVertex(vertex);
					}
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
	 * Eliminar un nodo del grafoNoDirigido.
	 */
	@FXML
	protected void botonEliminar() {
		if (scene == null) {
			initializeGrafo();
		}

		// Mostrar el codigo
		String tipo = getTipoString();
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/grafo" + tipo + "/eliminarNodo")).useDelimiter("\\Z").next();
		codigoGrafo.setText(codigoTexto);

		if (valorGrafo.getText() != null && ! valorGrafo.getText().trim().equals("")) {
			try {
				int i;
				for (i = 0; i < 5; i++) {
					if (grafoNodos[i] != null && grafoNodos[i].getValue() == Integer.valueOf(valorGrafo.getText())) {
						break;
					}
				}

				if (i != 5) {
					boolean exito = false;
					if (grafoTipos.getTipo() == Grafo.Tipos.NO_DIRIGIDO) {
						exito = noDirigido.removeNode(grafoNodos[i]);
					}
					else {
						for (int j = 0; j < dirigido.size(); j++) {
							Grafo.Vertex<GrafoNodo> vertex = dirigido.getVertex(j);
							if (vertex.getData().getValue() == Integer.valueOf(valorGrafo.getText())) {
								exito = dirigido.removeVertex(vertex);
								break;
							}
						}
					}
					if (exito) {
						grafoNodos[i] = null;
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
	 * Insertar un edge al grafoNoDirigido y mostrar el codigo en la pantalla.
	 */
	@FXML
	protected void botonInsertarEdge() {
		if (scene == null) {
			initializeGrafo();
		}

		String tipo = getTipoString();
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/grafo" + tipo + "/insertarEdge")).useDelimiter("\\Z").next();
		codigoGrafo.setText(codigoTexto);

		if (valorNodo1.getText() != null && !valorNodo1.getText().trim().equals("") && valorNodo2.getText() != null && !valorNodo2.getText().trim().equals("")) {
			GrafoNodo[] nodos = getNodosEdge();

			if (grafoTipos.getTipo() == Grafo.Tipos.NO_DIRIGIDO) {
				if (nodos[0] == null || nodos[1] == null || !noDirigido.nodeExists(nodos[0]) || !noDirigido.nodeExists(nodos[1])) {
					Main.mostrarError(resourceBundle.getString("grafoNoNodo"), resourceBundle);
				}
				else if (noDirigido.edgeExists(nodos[0], nodos[1])) {
					Main.mostrarError(resourceBundle.getString("grafoEdgeExiste"), resourceBundle);
				}
				else {
					noDirigido.addEdge(nodos[0], nodos[1]);
				}
			}
			else {
				if (nodos[0] == null || nodos[1] == null || dirigido.findVertexByName(String.valueOf(nodos[0].getValue())) == null || dirigido.findVertexByName(String.valueOf(nodos[1].getValue())) == null) {
					Main.mostrarError(resourceBundle.getString("grafoNoNodo"), resourceBundle);
				}
				else if (edgeExists(nodos[0], nodos[1])) {
					Main.mostrarError(resourceBundle.getString("grafoEdgeExiste"), resourceBundle);
				}
				else {
					int peso = PESO_PREDETERMINADO;
					if (valorPeso.getText() != null || !valorPeso.getText().trim().equals("")) {
						try {
							peso = Integer.valueOf(valorPeso.getText());
						}
						catch (NumberFormatException exception) {
							peso = PESO_PREDETERMINADO;
						}
					}
					dirigido.addEdge(dirigido.findVertexByName(String.valueOf(nodos[0].getValue())), dirigido.findVertexByName(String.valueOf(nodos[1].getValue())), peso);
				}
			}
			valorNodo1.setText("");
			valorNodo2.setText("");
			valorPeso.setText("");
		}
		else {
			Main.mostrarError(resourceBundle.getString("grafoNoNumero"), resourceBundle);
		}

		generarGrafico();
	}

	/**
	 * Eliminar un edge del grafoNoDirigido y mostrar el codigo en la pantalla.
	 */
	@FXML
	protected void botonEliminarEdge() {
		if (scene == null) {
			initializeGrafo();
		}

		String tipo = getTipoString();
		String codigoTexto = new Scanner(getClass().getResourceAsStream("/cl/cromer/estructuras/code/grafo" + tipo + "/eliminarEdge")).useDelimiter("\\Z").next();
		codigoGrafo.setText(codigoTexto);

		if (valorNodo1.getText() != null && ! valorNodo1.getText().trim().equals("") && valorNodo2.getText() != null && ! valorNodo2.getText().trim().equals("")) {
			GrafoNodo[] nodos = getNodosEdge();

			if (grafoTipos.getTipo() == Grafo.Tipos.NO_DIRIGIDO) {
				if (nodos[0] == null || nodos[1] == null || !noDirigido.nodeExists(nodos[0]) || !noDirigido.nodeExists(nodos[1])) {
					Main.mostrarError(resourceBundle.getString("grafoNoNodo"), resourceBundle);
				}
				else if (!noDirigido.edgeExists(nodos[0], nodos[1])) {
					Main.mostrarError(resourceBundle.getString("grafoNoEdge"), resourceBundle);
				}
				else {
					noDirigido.removeEdge(nodos[0], nodos[1]);
				}
			}
			else {
				if (nodos[0] == null || nodos[1] == null || dirigido.findVertexByName(String.valueOf(nodos[0].getValue())) == null || dirigido.findVertexByName(String.valueOf(nodos[1].getValue())) == null) {
					Main.mostrarError(resourceBundle.getString("grafoNoNodo"), resourceBundle);
				}
				else if (!edgeExists(nodos[0], nodos[1])) {
					Main.mostrarError(resourceBundle.getString("grafoNoEdge"), resourceBundle);
				}
				else {
					dirigido.removeEdge(dirigido.findVertexByName(String.valueOf(nodos[0].getValue())), dirigido.findVertexByName(String.valueOf(nodos[1].getValue())));
				}
			}
			valorNodo1.setText("");
			valorNodo2.setText("");
			valorPeso.setText("");
		}
		else {
			Main.mostrarError(resourceBundle.getString("grafoNoEdge"), resourceBundle);
		}

		generarGrafico();
	}

	/**
	 * Crear un grafoNoDirigido nuevo.
	 */
	private void initializeGrafo() {
		scene = contenidoGrafo.getScene();
		grafico = new Grafico(scene);
		grafoTipos = (Grafo.Tipos) scene.getUserData();
		grafoNodos = new GrafoNodo[5];

		if (grafoTipos.getTipo() == Grafo.Tipos.NO_DIRIGIDO) {
			noDirigido = new Grafo.NoDirigido<>();
		}
		else {
			dirigido = new Grafo.Dirigido<>();
		}
	}

	/**
	 * Devolver los nodos que existen.
	 *
	 * @return GrafoNodo[]: Los nodos que se busca.
	 */
	private GrafoNodo[] getNodosEdge() {
		GrafoNodo[] nodos = new GrafoNodo[2];
		for (int i = 0; i < 5; i++) {
			if (grafoNodos[i] != null) {
				if (Integer.valueOf(valorNodo1.getText()) == grafoNodos[i].getValue()) {
					nodos[0] = grafoNodos[i];
				}
				if (Integer.valueOf(valorNodo2.getText()) == grafoNodos[i].getValue()) {
					nodos[1] = grafoNodos[i];
				}
				if (nodos[0] != null && nodos[1] != null) {
					break;
				}
			}
		}
		return nodos;
	}

	/**
	 * Buscar un grafoNoDirigido dirigido para ver si existe en edge entre los nodos.
	 *
	 * @param nodo1 GrafoNodo: El primer nodo a buscar.
	 * @param nodo2 GrafoNodo: El otro nodo a buscar.
	 *
	 * @return boolean: Verdad si existe el edge.
	 */
	private boolean edgeExists(GrafoNodo nodo1, GrafoNodo nodo2) {
		List<Grafo.Edge<GrafoNodo>> edges = dirigido.getEdges();
		for (Grafo.Edge<GrafoNodo> edge : edges) {
			Grafo.Vertex<GrafoNodo> vFrom = edge.getFrom();
			Grafo.Vertex<GrafoNodo> vTo = edge.getTo();
			if (vFrom.getData() == nodo1 && vTo.getData() == nodo2) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Devolver el tipo de grafo en un string.
	 *
	 * @return String: El tipo de grafo.
	 */
	private String getTipoString() {
		String tipo;
		switch (grafoTipos.getTipo()) {
			case Grafo.Tipos.NO_DIRIGIDO:
				tipo = "NoDirigido";
				break;
			default:
				tipo = "Dirigido";
		}
		return tipo;
	}

	/**
	 * Generar la canvas con el grafoNoDirigido.
	 */
	private void generarGrafico() {
		grafico.removerDestacar();

		Colores colores = new Colores();

		GraphicsContext graphicsContext = contenidoGrafo.getGraphicsContext2D();
		graphicsContext.clearRect(0, 0, contenidoGrafo.getWidth(), contenidoGrafo.getHeight());

		if (grafoNodos[0] != null) {
			graphicsContext.setFill(colores.getFondo());
			graphicsContext.setStroke(colores.getBorder());

			graphicsContext.fillOval(10, 10, 40, 40);
			graphicsContext.strokeOval(10, 10, 40, 40);

			graphicsContext.setFill(colores.getTexto());
			int x = textX(25, String.valueOf(grafoNodos[0].getValue()));
			graphicsContext.fillText(String.valueOf(grafoNodos[0].getValue()), x, 35);
		}
		colores.siguinteColor();

		if (grafoNodos[1] != null) {
			graphicsContext.setFill(colores.getFondo());
			graphicsContext.setStroke(colores.getBorder());

			graphicsContext.fillOval(210, 10, 40, 40);
			graphicsContext.strokeOval(210, 10, 40, 40);

			graphicsContext.setStroke(colores.getTexto());
			int x = textX(225, String.valueOf(grafoNodos[1].getValue()));
			graphicsContext.strokeText(String.valueOf(grafoNodos[1].getValue()), x, 35);
		}
		colores.siguinteColor();

		if (grafoNodos[2] != null) {
			graphicsContext.setFill(colores.getFondo());
			graphicsContext.setStroke(colores.getBorder());

			graphicsContext.fillOval(10, 210, 40, 40);
			graphicsContext.strokeOval(10, 210, 40, 40);

			graphicsContext.setStroke(colores.getTexto());
			int x = textX(25, String.valueOf(grafoNodos[2].getValue()));
			graphicsContext.strokeText(String.valueOf(grafoNodos[2].getValue()), x, 235);
		}
		colores.siguinteColor();

		if (grafoNodos[3] != null) {
			graphicsContext.setFill(colores.getFondo());
			graphicsContext.setStroke(colores.getBorder());

			graphicsContext.fillOval(210, 210, 40, 40);
			graphicsContext.strokeOval(210, 210, 40, 40);

			graphicsContext.setStroke(colores.getTexto());
			int x = textX(225, String.valueOf(grafoNodos[3].getValue()));
			graphicsContext.strokeText(String.valueOf(grafoNodos[3].getValue()), x, 235);
		}
		colores.siguinteColor();

		if (grafoNodos[4] != null) {
			graphicsContext.setFill(colores.getFondo());
			graphicsContext.setStroke(colores.getBorder());

			graphicsContext.fillOval(105, 410, 40, 40);
			graphicsContext.strokeOval(105, 410, 40, 40);

			graphicsContext.setStroke(colores.getTexto());
			int x = textX(120, String.valueOf(grafoNodos[4].getValue()));
			graphicsContext.strokeText(String.valueOf(grafoNodos[4].getValue()), x, 435);
		}

		graphicsContext.setStroke(colores.getBorder());
		graphicsContext.setFill(colores.getBorder());

		if (grafoTipos.getTipo() == Grafo.Tipos.NO_DIRIGIDO) {
			// Line between 0 and 0.
			if (grafoNodos[0] != null && noDirigido.edgeExists(grafoNodos[0], grafoNodos[0])) {
				graphicsContext.strokeArc(15, 40, 29, 30, 145, 250, ArcType.OPEN);
			}
			// Line between 1 and 1.
			if (grafoNodos[1] != null && noDirigido.edgeExists(grafoNodos[1], grafoNodos[1])) {
				graphicsContext.strokeArc(215, 40, 29, 30, 145, 250, ArcType.OPEN);
			}
			// Line between 2 and 2.
			if (grafoNodos[2] != null && noDirigido.edgeExists(grafoNodos[2], grafoNodos[2])) {
				graphicsContext.strokeArc(15, 240, 29, 30, 145, 250, ArcType.OPEN);
			}
			// Line between 3 and 3.
			if (grafoNodos[3] != null && noDirigido.edgeExists(grafoNodos[3], grafoNodos[3])) {
				graphicsContext.strokeArc(215, 240, 29, 30, 145, 250, ArcType.OPEN);
			}
			// Line between 4 and 4.
			if (grafoNodos[4] != null && noDirigido.edgeExists(grafoNodos[4], grafoNodos[4])) {
				graphicsContext.strokeArc(110, 440, 29, 30, 145, 250, ArcType.OPEN);
			}
			// Line between 0 and 1.
			if (grafoNodos[0] != null && grafoNodos[1] != null && noDirigido.edgeExists(grafoNodos[0], grafoNodos[1])) {
				graphicsContext.strokeLine(50, 30, 210, 30);
			}
			// Line between 0 and 2.
			if (grafoNodos[0] != null && grafoNodos[2] != null && noDirigido.edgeExists(grafoNodos[0], grafoNodos[2])) {
				graphicsContext.strokeLine(30, 50, 30, 210);
			}
			// Line between 0 and 3.
			if (grafoNodos[0] != null && grafoNodos[3] != null && noDirigido.edgeExists(grafoNodos[0], grafoNodos[3])) {
				graphicsContext.strokeLine(45, 45, 215, 215);
			}
			// Line between 0 and 4.
			if (grafoNodos[0] != null && grafoNodos[4] != null && noDirigido.edgeExists(grafoNodos[0], grafoNodos[4])) {
				graphicsContext.strokeLine(38, 50, 125, 410);
			}
			// Line between 1 and 2.
			if (grafoNodos[1] != null && grafoNodos[2] != null && noDirigido.edgeExists(grafoNodos[1], grafoNodos[2])) {
				graphicsContext.strokeLine(45, 215, 215, 45);
			}
			// Line between 1 and 3.
			if (grafoNodos[1] != null && grafoNodos[3] != null && noDirigido.edgeExists(grafoNodos[1], grafoNodos[3])) {
				graphicsContext.strokeLine(230, 50, 230, 210);
			}
			// Line between 1 and 4.
			if (grafoNodos[1] != null && grafoNodos[4] != null && noDirigido.edgeExists(grafoNodos[1], grafoNodos[4])) {
				graphicsContext.strokeLine(221, 50, 125, 410);
			}
			// Line between 2 and 3
			if (grafoNodos[2] != null && grafoNodos[3] != null && noDirigido.edgeExists(grafoNodos[2], grafoNodos[3])) {
				graphicsContext.strokeLine(50, 230, 210, 230);
			}
			// Line between 2 and 4.
			if (grafoNodos[2] != null && grafoNodos[4] != null && noDirigido.edgeExists(grafoNodos[2], grafoNodos[4])) {
				graphicsContext.strokeLine(38, 250, 125, 410);
			}
			// Line between 3 and 4.
			if (grafoNodos[3] != null && grafoNodos[4] != null && noDirigido.edgeExists(grafoNodos[3], grafoNodos[4])) {
				graphicsContext.strokeLine(221, 250, 125, 410);
			}
		}
		else {
			List<Grafo.Edge<GrafoNodo>> edges = dirigido.getEdges();
			for (Grafo.Edge<GrafoNodo> edge : edges) {
				Grafo.Vertex<GrafoNodo> vFrom = edge.getFrom();
				Grafo.Vertex<GrafoNodo> vTo = edge.getTo();
				GrafoNodo from = vFrom.getData();
				GrafoNodo to = vTo.getData();

				if (from == grafoNodos[0]) {
					if (to == from) {
						graphicsContext.strokeArc(15, 40, 29, 30, 145, 250, ArcType.OPEN);
					}
				}
				if (from == grafoNodos[1]) {
					if (to == from) {
						graphicsContext.strokeArc(215, 40, 29, 30, 145, 250, ArcType.OPEN);
					}
				}
				if (from == grafoNodos[2]) {
					if (to == from) {
						graphicsContext.strokeArc(15, 240, 29, 30, 145, 250, ArcType.OPEN);
					}
				}
				if (from == grafoNodos[3]) {
					if (to == from) {
						graphicsContext.strokeArc(215, 240, 29, 30, 145, 250, ArcType.OPEN);
					}
				}
				if (from == grafoNodos[4]) {
					if (to == from) {
						graphicsContext.strokeArc(110, 440, 29, 30, 145, 250, ArcType.OPEN);
					}
				}

				// Line between 0 and 1.
				if (from == grafoNodos[0] && to == grafoNodos[1]) {
					graphicsContext.strokeLine(50, 30, 210, 30);
					graphicsContext.fillPolygon(
							new double[] {200.0, 210.0, 200.0},
							new double[] {25.0, 30.0, 35.0},
					        3
					);
				}
				// Line between 1 and 0
				if (from == grafoNodos[1] && to == grafoNodos[0]) {
					graphicsContext.strokeLine(50, 30, 210, 30);
					graphicsContext.fillPolygon(
							new double[] {60.0, 50.0, 60.0},
							new double[] {25.0, 30.0, 35.0},
							3
					);
				}
				// Line between 2 and 3
				if (from == grafoNodos[2] && to == grafoNodos[3]) {
					graphicsContext.strokeLine(50, 230, 210, 230);
					graphicsContext.fillPolygon(
							new double[] {200.0, 210.0, 200.0},
							new double[] {225.0, 230.0, 235.0},
							3
					);
				}
				// Line between 3 and 2
				if (from == grafoNodos[3] && to == grafoNodos[2]) {
					graphicsContext.strokeLine(50, 230, 210, 230);
					graphicsContext.fillPolygon(
							new double[] {60.0, 50.0, 60.0},
							new double[] {225.0, 230.0, 235.0},
							3
					);
				}
				// Line between 0 and 2
				if (from == grafoNodos[0] && to == grafoNodos[2]) {
					graphicsContext.strokeLine(30, 50, 30, 210);
					graphicsContext.fillPolygon(
							new double[] {25.0, 35.0, 30.0},
							new double[] {200.0, 200.0, 210.0},
							3
					);
				}
				// Line between 2 and 0
				if (from == grafoNodos[2] && to == grafoNodos[0]) {
					graphicsContext.strokeLine(30, 50, 30, 210);
					graphicsContext.fillPolygon(
							new double[] {30.0, 25.0, 35.0},
							new double[] {50.0, 60.0, 60.0},
							3
					);
				}
				// Line between 1 and 3
				if (from == grafoNodos[1] && to == grafoNodos[3]) {
					graphicsContext.strokeLine(230, 50, 230, 210);
					graphicsContext.fillPolygon(
							new double[] {225.0, 235.0, 230.0},
							new double[] {200.0, 200.0, 210.0},
							3
					);
				}
				// Line between 3 and 1
				if (from == grafoNodos[3] && to == grafoNodos[1]) {
					graphicsContext.strokeLine(230, 50, 230, 210);
					graphicsContext.fillPolygon(
							new double[] {230.0, 225.0, 235.0},
							new double[] {50.0, 60.0, 60.0},
							3
					);
				}
				// Line between 0 and 3
				if (from == grafoNodos[0] && to == grafoNodos[3]) {
					graphicsContext.strokeLine(45, 45, 215, 215);

					// Rotation is a pain in the ass.
					graphicsContext.save();
					Rotate rotate = new Rotate(315, 213.0, 213.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {208.0, 218.0, 213.0},
							new double[] {208.0, 208.0, 218.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 3 and 0
				if (from == grafoNodos[3] && to == grafoNodos[0]) {
					graphicsContext.strokeLine(45, 45, 215, 215);

					graphicsContext.save();
					Rotate rotate = new Rotate(135, 44.0, 44.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {39.0, 49.0, 44.0},
							new double[] {34.0, 34.0, 44.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 1 and 2
				if (from == grafoNodos[1] && to == grafoNodos[2]) {
					graphicsContext.strokeLine(45, 215, 215, 45);

					graphicsContext.save();
					Rotate rotate = new Rotate(45, 47.0, 213.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {42.0, 52.0, 47.0},
							new double[] {208.0, 208.0, 218.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 2 and 1
				if (from == grafoNodos[2] && to == grafoNodos[1]) {
					graphicsContext.strokeLine(45, 215, 215, 45);

					graphicsContext.save();
					Rotate rotate = new Rotate(225, 217.0, 43.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {212.0, 222.0, 217.0},
							new double[] {33.0, 33.0, 43.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 0 and 4
				if (from == grafoNodos[0] && to == grafoNodos[4]) {
					graphicsContext.strokeLine(38, 50, 125, 410);

					graphicsContext.save();
					Rotate rotate = new Rotate(345, 125.0, 410.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {120.0, 130.0, 125.0},
							new double[] {400.0, 400.0, 410.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 4 and 0
				if (from == grafoNodos[4] && to == grafoNodos[0]) {
					graphicsContext.strokeLine(38, 50, 125, 410);

					graphicsContext.save();
					Rotate rotate = new Rotate(165, 38.0, 50.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {33.0, 43.0, 38.0},
							new double[] {40.0, 40.0, 50.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 1 and 4
				if (from == grafoNodos[1] && to == grafoNodos[4]) {
					graphicsContext.strokeLine(221, 50, 125, 410);

					graphicsContext.save();
					Rotate rotate = new Rotate(375, 125.0, 410.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {120.0, 130.0, 125.0},
							new double[] {400.0, 400.0, 410.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 4 and 1
				if (from == grafoNodos[4] && to == grafoNodos[1]) {
					graphicsContext.strokeLine(221, 50, 125, 410);

					graphicsContext.save();
					Rotate rotate = new Rotate(195, 222.0, 50.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {217.0, 227.0, 222.0},
							new double[] {40.0, 40.0, 50.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 2 and 4
				if (from == grafoNodos[2] && to == grafoNodos[4]) {
					graphicsContext.strokeLine(38, 250, 125, 410);

					graphicsContext.save();
					Rotate rotate = new Rotate(330, 125.0, 410.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {120.0, 130.0, 125.0},
							new double[] {400.0, 400.0, 410.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 4 and 2
				if (from == grafoNodos[4] && to == grafoNodos[2]) {
					graphicsContext.strokeLine(38, 250, 125, 410);

					graphicsContext.save();
					Rotate rotate = new Rotate(150, 37.0, 249.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {32.0, 42.0, 37.0},
							new double[] {239.0, 239.0, 249.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 3 and 4
				if (from == grafoNodos[3] && to == grafoNodos[4]) {
					graphicsContext.strokeLine(221, 250, 125, 410);

					graphicsContext.save();
					Rotate rotate = new Rotate(35, 125.0, 410.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {120.0, 130.0, 125.0},
							new double[] {400.0, 400.0, 410.0},
							3
					);
					graphicsContext.restore();
				}
				// Line between 4 and 3
				if (from == grafoNodos[4] && to == grafoNodos[3]) {
					graphicsContext.strokeLine(221, 250, 125, 410);

					graphicsContext.save();
					Rotate rotate = new Rotate(210, 221.0, 249.0);
					graphicsContext.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
					graphicsContext.fillPolygon(
							new double[] {216.0, 226.0, 221.0},
							new double[] {239.0, 239.0, 249.0},
							3
					);
					graphicsContext.restore();
				}

				// Todo: make this more effecient by removing the extra line draws that overlap
			}
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