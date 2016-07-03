package cl.cromer.estructuras;

import javafx.scene.paint.Color;

/**
 * Rotación de colores.
 *
 * @author Chris Cromer
 */
public class Colores {
	/**
	 * Cuantos colores estan definidos en esta clase.
	 */
	static final public int MAX_COLORS = 7;

	/**
	 * El color actual en forma numerica.
	 */
	private int color;

	/**
	 * El color de texto actual.
	 */
	private Color texto;

	/**
	 * El color de fondo actual.
	 */
	private Color fondo;

	/**
	 * Inicializar el primer color.
	 */
	public Colores() {
		siguinteColor();
	}

	/**
	 * Cambiar el color al siguinte. Si no hay, voler al primer.
	 */
	public void siguinteColor() {
		switch (color) {
			case 1:
				color = 2;
				texto = Color.WHITE;
				fondo = Color.RED;
				break;
			case 2:
				color = 3;
				texto = Color.BLACK;
				fondo = Color.WHITE;
				break;
			case 3:
				color = 4;
				texto = Color.BLACK;
				fondo = Color.PINK;
				break;
			case 4:
				color = 5;
				texto = Color.BLACK;
				fondo = Color.YELLOW;
				break;
			case 5:
				color = 6;
				texto = Color.BLACK;
				fondo = Color.GREEN;
				break;
			case 6:
				color = 7;
				texto = Color.BLACK;
				fondo = Color.ORANGE;
				break;
			default:
				color = 1;
				texto = Color.WHITE;
				fondo = Color.BLUE;
		}
	}

	/**
	 * Devolver el color del texto actual.
	 *
	 * @return Color: Color del texto.
	 */
	public Color getTexto() {
		return texto;
	}

	/**
	 * Devolver el color del fondo actual.
	 *
	 * @return Color: Color del fondo.
	 */
	public Color getFondo() {
		return fondo;
	}
}
