package cl.cromer.estructuras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Esta clase es para controlar la configuración.
 *
 * @author Chris Cromer
 */
public class ConfigController implements Initializable {
	/**
	 * El color ComboBox.
	 */
	@FXML
	private ComboBox<Integer> colors;

	/**
	 * Donde está guardado los idiomas.
	 */
	private ResourceBundle resourceBundle;

	/**
	 * Inicializar todos los datos y dibujar las graficas.
	 *
	 * @param location URL: El URL de fxml en uso.
	 * @param resourceBundle ResourceBundle: Tiene datos de idioma.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	/**
	 * Guardar los colores maximo a usar.
	 */
	@FXML
	protected void changeColor() {
		Stage stage = (Stage) colors.getScene().getWindow();
		Preferences preferences = (Preferences) stage.getScene().getUserData();
		if (preferences != null) {
			preferences.putInt("colors", colors.getValue());
		}
	}

	/**
	 * Cerrar la venta de configuración.
	 */
	@FXML
	protected void closeConfig() {
		Stage stage = (Stage) colors.getScene().getWindow();
		stage.close();
	}

	/**
	 * Este metodo es para el evento de windowshown.
	 */
	public void handleWindowShownEvent() {
		Stage stage = (Stage) colors.getScene().getWindow();
		Preferences preferences = (Preferences) stage.getScene().getUserData();

		for (int i = 2; i <= Colores.MAX_COLORS; i++) {
			colors.getItems().add(i);
		}

		if (preferences != null) {
			colors.setValue(preferences.getInt("colors", Colores.MAX_COLORS));
		}
		else {
			colors.setValue(Colores.MAX_COLORS);
		}
	}
}
