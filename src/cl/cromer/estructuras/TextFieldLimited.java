package cl.cromer.estructuras;

import javafx.beans.property.IntegerProperty;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableIntegerProperty;
import javafx.css.StyleableProperty;
import javafx.css.converter.SizeConverter;
import javafx.scene.control.TextField;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Crear un TextField especial que tiene un maximo de digitos que puede ingresar. Se extiene a TextField.
 *
 * @author Chris Cromer
 */
public class TextFieldLimited extends TextField {
	/**
	 * La cantidad maxima de caracters permitidas en el TextFieldLimited
	 */
	private IntegerProperty maxLength;

	/**
	 * Llamar a TextField.
	 */
	public TextFieldLimited() {
		super();
	}

	/**
	 * Lista de estilos aplicable.
	 *
	 * @return List: La lista de estilos.
	 */
	@SuppressWarnings("unused")
	public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
		return TextFieldLimited.StyleableProperties.STYLEABLES;
	}

	/**
	 * Reemplazar el texto basado en cambios de teclado, no deja ingresar mas text si length es mayor al maximo.
	 *
	 * @param start int: Donde empece el cambio.
	 * @param end int: Donde termina.
	 * @param text String: Texto a cambiar.
	 */
	@Override
	public void replaceText(int start, int end, String text) {
		if (getMaxLength() != 0) {
			if (text.equals("")) {
				super.replaceText(start, end, text);
			}
			else if (getText().length() < getMaxLength()) {
				super.replaceText(start, end, text);
			}
		}
		else {
			super.replaceText(start, end, text);
		}
	}

	/**
	 * Reemplazar un selección de texto.
	 *
	 * @param text String: El texto a reemplazar.
	 */
	@Override
	public void replaceSelection(String text) {
		if (getMaxLength() != 0) {
			if (text.equals("")) {
				super.replaceSelection(text);
			}
			else if (getText().length() < getMaxLength()) {
				if (text.length() > getMaxLength() - getText().length()) {
					text = text.substring(0, getMaxLength() - getText().length());
				}
				super.replaceSelection(text);
			}
		}
		else {
			super.replaceSelection(text);
		}
	}

	/**
	 * Devolver la cantidad maxima si está asignado.
	 *
	 * @return int: Cantidad de caracters.
	 */
	public final int getMaxLength() {
		return maxLength == null ? 0 : maxLength.get();
	}

	/**
	 * Asignar un valor maximo de caracters permitidio en el TextFieldLimited.
	 *
	 * @param value int: La cantidad maxima.
	 */
	@SuppressWarnings("unused")
	public final void setMaxLength(int value) {
		if (maxLength != null || value > 0) {
			maxLengthProperty().set(value);
		}
	}

	/**
	 * JavaFX FXML field property por tamaño maximo
	 *
	 * @return IntegerProperty: Property.
	 */
	public final IntegerProperty maxLengthProperty() {
		if (maxLength == null) {
			maxLength = new StyleableIntegerProperty() {

				@Override
				public CssMetaData<TextFieldLimited, Number> getCssMetaData() {
					return TextFieldLimited.StyleableProperties.MAX_LENGTH;
				}

				@Override
				public Object getBean() {
					return TextFieldLimited.this;
				}

				@Override
				public String getName() {
					return "maxLength";
				}
			};
		}
		return maxLength;
	}

	/**
	 * CSS por FXML con un maximo tamaño
	 */
	private static class StyleableProperties {
		private static final CssMetaData<TextFieldLimited, Number> MAX_LENGTH =
				new CssMetaData<TextFieldLimited, Number>("-fx-max-length", SizeConverter.getInstance(), 0) {
					@Override
					public boolean isSettable(TextFieldLimited node) {
						return node.maxLength == null || ! node.maxLength.isBound();
					}

					@Override
					@SuppressWarnings("unchecked")
					public StyleableProperty<Number> getStyleableProperty(TextFieldLimited node) {
						return (StyleableProperty<Number>) node.maxLengthProperty();
					}

				};

		private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

		static {
			final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Shape.getClassCssMetaData());
			styleables.add(MAX_LENGTH);
			STYLEABLES = Collections.unmodifiableList(styleables);
		}
	}
}
