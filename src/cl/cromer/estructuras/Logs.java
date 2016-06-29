package cl.cromer.estructuras;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Esta clase es para configurar el logeo de la aplicación.
 *
 * @author Chris Cromer
 */
public class Logs {
    /**
     * Nombre de archivo para guardar los logs.
     */
    static final public String LOGFILE = "./EDD.log";

    /**
     * Nombre del log.
     */
    static final public String LOGNAME = "EDD";

    /**
     * Crear un logger usando {@value #LOGNAME}. Guardar los logs en el archivo de {@value #LOGFILE}. Pero solo logear si Main.DEBUG es vardad.
     */
    public Logs() {
        if (Main.DEBUG) {
            Logger logger = Logger.getLogger(LOGNAME);
            try {
                FileHandler fileHandler = new FileHandler(LOGFILE, true);
                logger.addHandler(fileHandler);
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
            }
            catch (SecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Agregar un log al logger.
     *
     * @param level   Level: El tipo de error o mensaje que ha sido generado.
     * @param mensaje String: El mensaje de lo que pasó.
     */
    static public void log(Level level, String mensaje) {
        if (Main.DEBUG) {
            Logger.getLogger(LOGNAME).log(level, mensaje);
        }
    }
}
