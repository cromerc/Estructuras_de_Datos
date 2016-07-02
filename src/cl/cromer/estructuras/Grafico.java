package cl.cromer.estructuras;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es para trabajar con graficos.
 *
 * @author Chris Cromer
 */
public class Grafico {
    /**
     * Duración de la animación.
     */
    static final public int DURACION = 150;
    /**
     * Tipo de dibujo rectuangular.
     */
    static final public int RECTANGULO = 0;
    /**
     * Tipo de dibujo circular.
     */
    static final public int CIRCULO = 1;
    /**
     * Tipo de dibjuo texto
     */
    static final public int TEXTO = 2;
    /**
     * La escena donde está cosas graficas.
     */
    final private Scene scene;
    /**
     * Los elementos destacados.
     */
    private List<Destacados> destacados;

    /**
     * Graficar una escena.
     *
     * @param scene La scene a destacar.
     */
    public Grafico(Scene scene) {
        this.scene = scene;
        destacados = new ArrayList<>();
    }

    /**
     * Crear una flecha que apunta por abajo.
     *
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
     *
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
     *
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
     *
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
     * Crear un rectangulo.
     *
     * @param colores Colores: Los colores para dar color al rectangulo.
     * @param label   String: El texto por el ID de fxml.
     * @return StackPane: Devolver el stackpane que contiene el rectangulo y texto.
     */
    public static StackPane crearCaja(Colores colores, String label) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(40);
        rectangle.setWidth(40);
        rectangle.setFill(colores.getFondo());
        rectangle.setStroke(Color.BLACK);
        rectangle.setId("caja_" + label);
        Text text = new Text();
        text.setId("texto_" + label);
        text.setStroke(colores.getTexto());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, text);
        return stackPane;
    }

    /**
     * Crear un rectangulo con texto adentro.
     *
     * @param colores Colores: Los colores para dar color al rectangulo.
     * @param label   String: El texto por el ID de fxml.
     * @param texto   String: El texto a colocar dentro el rectangulo.
     * @return StackPane: Devolver el stackpane que contiene el rectangulo y texto.
     */
    public static StackPane crearCaja(Colores colores, String label, String texto) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(40);
        rectangle.setWidth(40);
        rectangle.setFill(colores.getFondo());
        rectangle.setStroke(Color.BLACK);
        rectangle.setId("caja_" + label);
        Text text = new Text();
        text.setId("texto_" + label);
        text.setStroke(colores.getTexto());
        text.setText(texto);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, text);
        return stackPane;
    }

    /**
     * Crear 3 rectangulos.
     *
     * @param colores Colores: Los colores para dar color a los rectangulos.
     * @param label   String: El texto por el ID de fxml.
     * @return StackPane: Devolver el stackpane que contiene los rectangulos y textos.
     */
    public static StackPane crearHashCajas(Colores colores, String label) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(40);
        rectangle.setWidth(40);
        rectangle.setFill(colores.getFondo());
        rectangle.setStroke(Color.BLACK);
        rectangle.setId("indice_caja_" + label);
        Text text = new Text();
        text.setId("indice_texto_" + label);
        text.setStroke(colores.getTexto());

        StackPane stackPane1 = new StackPane();
        stackPane1.getChildren().addAll(rectangle, text);

        rectangle = new Rectangle();
        rectangle.setHeight(40);
        rectangle.setWidth(120);
        rectangle.setFill(colores.getFondo());
        rectangle.setStroke(Color.BLACK);
        rectangle.setId("llave_caja_" + label);
        text = new Text();
        text.setId("llave_texto_" + label);
        text.setStroke(colores.getTexto());

        StackPane stackPane2 = new StackPane();
        stackPane2.getChildren().addAll(rectangle, text);

        rectangle = new Rectangle();
        rectangle.setHeight(40);
        rectangle.setWidth(40);
        rectangle.setFill(colores.getFondo());
        rectangle.setStroke(Color.BLACK);
        rectangle.setId("valor_caja_" + label);
        text = new Text();
        text.setId("valor_texto_" + label);
        text.setStroke(colores.getTexto());

        StackPane stackPane3 = new StackPane();
        stackPane3.getChildren().addAll(rectangle, text);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(stackPane1, stackPane2, stackPane3);
        hBox.setAlignment(Pos.TOP_CENTER);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(hBox);
        return stackPane;
    }

    /**
     * Crear un animacion de transicion usando colores que cambian.
     *
     * @param rectangle       Rectangle: El objeto que desea animar.
     * @param colorBackground Color: Color del fondo de destacar.
     * @return PauseTransition: La transition creado con los elementos y colores.
     */
    private static PauseTransition createPauseTransition(Rectangle rectangle, Color colorBackground) {
        PauseTransition changeColor = new PauseTransition(new Duration(DURACION));
        changeColor.setOnFinished(actionEvent -> rectangle.setFill(colorBackground));
        return changeColor;
    }

    /**
     * Crear un animacion de transicion usando colores que cambian.
     *
     * @param circle          Circle: El objeto que desea animar.
     * @param colorBackground Color: Color del fondo de destacar.
     * @return PauseTransition: La transition creado con los elementos y colores.
     */
    private static PauseTransition createPauseTransition(Circle circle, Color colorBackground) {
        PauseTransition changeColor = new PauseTransition(new Duration(DURACION));
        changeColor.setOnFinished(actionEvent -> circle.setFill(colorBackground));
        return changeColor;
    }

    /**
     * Crear un animacion de transicion usando colores que cambian.
     *
     * @param text            Text: El texto que desea animar.
     * @param colorText       Color: Color del texto.
     * @return PauseTransition: La transition creado con los elementos y colores.
     */
    private static PauseTransition createPauseTransition(Text text, Color colorText) {
        PauseTransition changeColor = new PauseTransition(new Duration(DURACION));
        changeColor.setOnFinished(actionEvent -> text.setStroke(colorText));
        return changeColor;
    }

    /**
     * Destacar un elemento
     *
     * @param id int: El indice a destacar.
     * @param tipo  int: El tipo de objeto a destacar, {@value #RECTANGULO}, {@value #CIRCULO} o {@value #TEXTO}
     */
    public void destacar(String id, int tipo) {
        if (tipo != RECTANGULO && tipo != CIRCULO && tipo != TEXTO) {
            return;
        }

        Colores colores = new Colores();
        Rectangle rectangle = null;
        Circle circle = null;
        Text text = null;
        if (tipo == RECTANGULO) {
            rectangle = (Rectangle) scene.lookup(id);
        }
        else if (tipo == CIRCULO) {
            circle = (Circle) scene.lookup(id);
        }
        else {
            text = (Text) scene.lookup(id);
        }
        PauseTransition changeColor[] = new PauseTransition[Colores.MAX_COLORS];
        for (int i = 0; i < Colores.MAX_COLORS; i++) {
            if (tipo == RECTANGULO) {
                changeColor[i] = createPauseTransition(rectangle, colores.getFondo());
            }
            else if (tipo == CIRCULO) {
                changeColor[i] = createPauseTransition(circle,  colores.getFondo());
            }
            else {
                changeColor[i] = createPauseTransition(text, colores.getTexto());
            }
            colores.siguinteColor();
        }

        if (tipo == RECTANGULO) {
            destacados.add(new Destacados(tipo, id, (Color) rectangle.getFill(), new SequentialTransition(changeColor)));
        }
        else if (tipo == CIRCULO) {
            destacados.add(new Destacados(tipo, id, (Color) circle.getFill(), new SequentialTransition(changeColor)));
        }
        else {
            destacados.add(new Destacados(tipo, id, (Color) text.getStroke(), new SequentialTransition(changeColor)));
        }

        destacados.get(destacados.size() - 1).getSequentialTransition().setCycleCount(Animation.INDEFINITE);
        destacados.get(destacados.size() - 1).getSequentialTransition().play();
    }

    /**
     * Remover todos los elementos destacados.
     */
    public void removerDestacar() {
        if (destacados.size() != 0) {
            for (int i = 0; i < destacados.size(); i++) {
                destacados.get(i).getSequentialTransition().stop();

                if (destacados.get(i).getTipo() == RECTANGULO) {
                    Rectangle rectangle = (Rectangle) scene.lookup(destacados.get(i).getId());
                    rectangle.setFill(destacados.get(i).getColor());
                }
                else if (destacados.get(i).getTipo() == CIRCULO) {
                    Circle circle = (Circle) scene.lookup(destacados.get(i).getId());
                    circle.setFill(destacados.get(i).getColor());
                }
                else {
                    Text text = (Text) scene.lookup(destacados.get(i).getId());
                    text.setStroke(destacados.get(i).getColor());
                }

                destacados.remove(i);
            }
        }
    }

    /**
     * Clase de elemento destacado.
     *
     * @author Chris Cromer
     */
    final static private class Destacados {
        /**
         * El tipo de objeto que está destacado.
         */
        private int tipo;
        /**
         * El id del elemento destacado.
         */
        private String id;
        /**
         * El color anterior del elemento destacado.
         */
        private Color color;
        /**
         * La animación del elemento destacado.
         */
        private SequentialTransition sequentialTransition;

        /**
         * Inicilizar.
         * @param tipo int: El tipo de elemento destacado, {@value #RECTANGULO}, {@value #CIRCULO} o {@value #TEXTO}.
         * @param id String: El id para identificar el elemento.
         * @param color Color: El color anterior para cambiarlo cuando {@link #removerDestacar()} es usado.
         * @param sequentialTransition SequentialTransition: La animación a usar.
         */
        public Destacados(int tipo, String id, Color color, SequentialTransition sequentialTransition) {
            this.tipo = tipo;
            this.id = id;
            this.color = color;
            this.sequentialTransition = sequentialTransition;
        }

        /**
         * Devolver el tipo de elemento destacado.
         * @return int: El tipo destacado.
         */
        public int getTipo() {
            return tipo;
        }

        /**
         * Devolver el ID de elemento destacado.
         * @return String: El ID destacado.
         */
        public String getId() {
            return id;
        }

        /**
         * Devolver el color anterior antes que fue destacado.
         * @return Color: El color anterior.
         */
        public Color getColor() {
            return color;
        }

        /**
         * Devolver la animación que es en uso con el elemento.
         * @return SequentialTransition: La animación.
         */
        public SequentialTransition getSequentialTransition() {
            return sequentialTransition;
        }
    }
}
