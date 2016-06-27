package cl.cromer.estructuras;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Esta clase es para trabajar con graficos.
 * @author Chris Cromer
 */
public class Grafico {
    /**
     * Tipo de dibujo rectuangular.
     */
    static final public int RECTANGULO = 0;

    /**
     * Tipo de dibujo circular.
     */
    static final public int CIRCULO = 1;

    /**
     * Contiene la animación de destacar.
     */
    private SequentialTransition blinkTransition;

    /**
     * El valor de cual caja está destacado actualmente
     */
    private int destacado;

    /**
     * El tipo de objeto que está destacado.
     */
    private int tipo;

    /**
     * El color original de fondo para volver cuando no es destacado.
     */
    private Color destacadoBG;

    /**
     * El color original de text para volver cuando no es destacado.
     */
    private Color destacadoFG;

    /**
     * La escena donde está cosas graficas.
     */
    private Scene scene;

    /**
     * Graficar una escena.
     * @param scene La scene a destacar.
     */
    public Grafico(Scene scene) {
        this.scene = scene;
        destacado = -1;
    }

    /**
     * Crear una flecha que apunta por abajo.
     * @return StackPane: Devolver el stackpane que contiene la flecha.
     */
    public static StackPane crearFlechaAbajo() {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
            0.0, 0.0,
            10.0, 0.0,
            5.0, 10.0
        );

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(polygon);
        return stackPane;
    }

    /**
     * Crear una flecha que apunta por arriba.
     * @return StackPane: Devolver el stackpane que contiene la flecha.
     */
    public static StackPane crearFlechaArriba() {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
            5.0, 0.0,
            0.0, 10.0,
            10.0, 10.0
        );

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(polygon);
        return stackPane;
    }

    /**
     * Crear una linea vertical.
     * @return StackPane: Devolver el stackpane que contiene la linea vertical.
     */
    public static StackPane crearLineaVertical() {
        Line line = new Line();
        line.setStartX(20);
        line.setEndX(20);
        line.setStartY(0);
        line.setEndY(20);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(line);
        return stackPane;
    }

    /**
     * Crear la linea circular con flecha.
     * @param cajas int: La cantidad de cajas que están.
     * @return StackPane: Devolver el stackpane que contiene la linea horizontal.
     */
    public static Pane crearLineaCircular(int cajas) {
        int height = (cajas - 1) * (40 + 20 + 10);
        height = height + 20 + 10;

        Line top = new Line();
        top.setStartY(20);
        top.setEndY(20);
        top.setStartX(0);
        top.setEndX(20);

        Polygon flechaDerecha = new Polygon();
        flechaDerecha.getPoints().addAll(
            10.0, 15.0,
            20.0, 20.0,
            10.0, 25.0
        );

        Line vertical = new Line();
        vertical.setStartY(20);
        vertical.setEndY(height);

        Line bottom = new Line();
        bottom.setStartY(height);
        bottom.setEndY(height);
        bottom.setStartX(0);
        bottom.setEndX(20);


        Pane stackPane = new Pane();
        stackPane.getChildren().addAll(top, flechaDerecha, vertical, bottom);

        return stackPane;
    }

    /**
     * Crear un rectangulo con texto adentro.
     * @param colores Colores: Los colores para dar color al rectangulo.
     * @param label String: El texto por el ID de fxml.
     * @return StackPane: Devolver el stackpane que contiene el rectangulo y texto.
     */
    public static StackPane crearCaja(Colores colores, String label) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(40);
        rectangle.setWidth(40);
        rectangle.setFill(colores.getFondo());
        rectangle.setStroke(Color.BLACK);
        rectangle.setId("border_" + label);
        Text text = new Text();
        text.setId("caja_" + label);
        text.setStroke(colores.getTexto());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, text);
        return stackPane;
    }

    /**
     * Crear un rectangulo con texto adentro.
     * @param colores Colores: Los colores para dar color al rectangulo.
     * @param label String: El texto por el ID de fxml.
     * @param texto String: El texto a colocar dentro el rectangulo.
     * @return StackPane: Devolver el stackpane que contiene el rectangulo y texto.
     */
    public static StackPane crearCaja(Colores colores, String label, String texto) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(40);
        rectangle.setWidth(40);
        rectangle.setFill(colores.getFondo());
        rectangle.setStroke(Color.BLACK);
        rectangle.setId("border_" + label);
        Text text = new Text();
        text.setId("caja_" + label);
        text.setStroke(colores.getTexto());
        text.setText(texto);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, text);
        return stackPane;
    }

    /**
     * Crear un rectangulo con texto adentro.
     * @param colores Colores: Los colores para dar color al rectangulo.
     * @param label String: El texto por el ID de fxml.
     * @param texto String: El texto a colocar dentro el rectangulo.
     * @param tamano int: El tamaño del rectangulo.
     * @return StackPane: Devolver el stackpane que contiene el rectangulo y texto.
     */
    public static StackPane crearCaja(Colores colores, String label, String texto, int tamano) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(tamano);
        rectangle.setWidth(tamano);
        rectangle.setFill(colores.getFondo());
        rectangle.setStroke(Color.BLACK);
        rectangle.setId("border_" + label);
        Text text = new Text();
        text.setId("caja_" + label);
        text.setStroke(colores.getTexto());
        text.setText(texto);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, text);
        return stackPane;
    }

    /**
     * Crear un animacion de transicion usando colores que cambian.
     * @param rectangle Rectangle: El objeto que desea animar.
     * @param text Text: El texto que desea animar.
     * @param colorBackground Color: Color del fondo de destacer.
     * @param colorText Color: Color del texto.
     * @return PauseTransition: La transition creado con los elementos y colores.
     */
    private static PauseTransition createPauseTransition(Rectangle rectangle , Text text, Color colorBackground, Color colorText) {
        PauseTransition changeColor = new PauseTransition(new Duration(100));
        changeColor.setOnFinished(actionEvent -> {
            rectangle.setFill(colorBackground);
            text.setStroke(colorText);
        });
        return changeColor ;
    }

    /**
     * Crear un animacion de transicion usando colores que cambian.
     * @param circle Circle: El objeto que desea animar.
     * @param text Text: El texto que desea animar.
     * @param colorBackground Color: Color del fondo de destacer.
     * @param colorText Color: Color del texto.
     * @return PauseTransition: La transition creado con los elementos y colores.
     */
    private static PauseTransition createPauseTransition(Circle circle , Text text, Color colorBackground, Color colorText) {
        PauseTransition changeColor = new PauseTransition(new Duration(100));
        changeColor.setOnFinished(actionEvent -> {
            circle.setFill(colorBackground);
            text.setStroke(colorText);
        });
        return changeColor ;
    }

    /**
     * Destacar un elemento
     * @param valor int: El indice a destacar.
     * @param tipo int: El tipo de objeto a destacer, {@value #RECTANGULO} o {@value #CIRCULO}
     */
    public void destacer(int valor, int tipo) {
        if (tipo != RECTANGULO && tipo != CIRCULO) {
            return;
        }
        else {
            this.tipo = tipo;
        }

        destacado = valor;
        Colores colores = new Colores();
        Rectangle rectangle = null;
        Circle circle = null;
        if (this.tipo == RECTANGULO) {
            rectangle = (Rectangle) scene.lookup("#border_" + String.valueOf(valor));
            destacadoBG = (Color) rectangle.getFill();
        }
        else if (this.tipo == CIRCULO) {
            circle = (Circle) scene.lookup("#border_" + String.valueOf(valor));
            destacadoBG = (Color) circle.getFill();
        }
        Text text = (Text) scene.lookup("#caja_" + String.valueOf(valor));
        destacadoFG = (Color) text.getStroke();
        PauseTransition changeColor[] = new PauseTransition[Colores.MAX_COLORS];
        for (int i = 0; i < Colores.MAX_COLORS; i++) {
            if (this.tipo == RECTANGULO) {
                changeColor[i] = createPauseTransition(rectangle, text, colores.getFondo(), colores.getTexto());
            }
            else if (this.tipo == CIRCULO) {
                changeColor[i] = createPauseTransition(circle, text, colores.getFondo(), colores.getTexto());
            }
            colores.siguinteColor();
        }
        blinkTransition = new SequentialTransition(changeColor);
        blinkTransition.setCycleCount(Animation.INDEFINITE);
        blinkTransition.play();
    }

    /**
     * Remover el efecto de destacar.
     */
    public void removerDestacar() {
        if (destacado != -1) {
            blinkTransition.stop();
            if (tipo == RECTANGULO) {
                Rectangle rectangle = (Rectangle) scene.lookup("#border_" + String.valueOf(destacado));
                rectangle.setFill(destacadoBG);
            }
            else if (tipo == CIRCULO) {
                Circle circle = (Circle) scene.lookup("#border_" + String.valueOf(destacado));
                circle.setFill(destacadoBG);
            }
            Text text = (Text) scene.lookup("#caja_" + String.valueOf(destacado));
            text.setStroke(destacadoFG);
            destacado = -1;
        }
    }
}
