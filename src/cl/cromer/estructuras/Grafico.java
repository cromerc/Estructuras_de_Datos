package cl.cromer.estructuras;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Esta clase es para trabajar con graficos.
 * @author Chris Cromer
 */
public class Grafico {
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
    private String tipo;

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
     * Destacar un elemento
     * @param valor int: El indice a destacar.
     * @param tipo String: El tipo de objeto a destacer(rectangulo o cicurlo)
     */
    public void destacer(int valor, String tipo) {
        if (!tipo.equals("rectangulo") && !tipo.equals("circulo")) {
            return;
        }
        this.tipo = tipo;
        destacado = valor;
        Colores colores = new Colores();
        Rectangle rectangle = null;
        Circle circle = null;
        if (tipo.equals("rectangulo")) {
            rectangle = (Rectangle) scene.lookup("#border_" + String.valueOf(valor));
            destacadoBG = (Color) rectangle.getFill();
        }
        else if (tipo.equals("circulo")) {
            circle = (Circle) scene.lookup("#border_" + String.valueOf(valor));
            destacadoBG = (Color) circle.getFill();
        }
        Text text = (Text) scene.lookup("#caja_" + String.valueOf(valor));
        destacadoFG = (Color) text.getStroke();
        PauseTransition changeColor[] = new PauseTransition[Colores.MAX_COLORS];
        for (int i = 0; i < Colores.MAX_COLORS; i++) {
            if (tipo.equals("rectangulo")) {
                changeColor[i] = createPauseTransition(rectangle, text, colores.getFondo(), colores.getTexto());
            }
            else if (tipo.equals("circulo")) {
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
            if (tipo.equals("rectangulo")) {
                Rectangle rectangle = (Rectangle) scene.lookup("#border_" + String.valueOf(destacado));
                rectangle.setFill(destacadoBG);
            }
            else if (tipo.equals("circulo")) {
                Circle circle = (Circle) scene.lookup("#border_" + String.valueOf(destacado));
                circle.setFill(destacadoBG);
            }
            Text text = (Text) scene.lookup("#caja_" + String.valueOf(destacado));
            text.setStroke(destacadoFG);
            destacado = -1;
        }
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
}
