package cl.cromer.estructuras;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
	 * Estado de depuración.
	 */
	static final public boolean DEBUG = false;

	/**
	 * Tipos de depuración.
	 */
	private enum DEBUG_TIPOS {
		ARCHIVO,
		CONSOLA
	}

	/**
	 * Tipo de depuración a usar.
	 */
	static final public DEBUG_TIPOS DEBUG_TIPO = DEBUG_TIPOS.ARCHIVO;

	/**
	 * Nombre de archivo para guardar los logs.
	 */
	static final public String LOGFILE = "./EDD.log";

	/**
	 * Nombre del log.
	 */
	static final public String LOGNAME = "EDD";

	public FileHandler fileHandler;

	/**
	 * Crear un logger usando {@value #LOGNAME}. Guardar los logs en el archivo de {@value #LOGFILE}. Pero solo logear si {@value #DEBUG} es vardad.
	 */
	public Logs() {
		if (DEBUG && DEBUG_TIPO == DEBUG_TIPOS.ARCHIVO) {
			Logger logger = Logger.getLogger(LOGNAME);
			try {
				fileHandler = new FileHandler(LOGFILE, true);
				logger.addHandler(fileHandler);
				SimpleFormatter formatter = new SimpleFormatter();
				fileHandler.setFormatter(formatter);
			}
			catch (SecurityException | IOException exception) {
				exception.printStackTrace();
			}
		}
	}

	/**
	 * Cerrar el archivo.
	 */
	public void close() {
		if (DEBUG && DEBUG_TIPO == DEBUG_TIPOS.ARCHIVO) {
			if (fileHandler != null) {
				fileHandler.close();
			}
		}
	}

	/**
	 * Agregar un log al logger con un mensaje.
	 *
	 * @param mensaje String: El mensaje de lo que pasó.
	 */
	static public void log(String mensaje) {
		if (DEBUG) {
			if (DEBUG_TIPO == DEBUG_TIPOS.ARCHIVO) {
				Logger.getLogger(LOGNAME).log(Level.INFO, mensaje);
			}
			else {
				System.out.println(mensaje + "\n");
			}
		}
	}

	/**
	 * Agregar un log al logger con un mensaje.
	 *
	 * @param level Level: El tipo de error o mensaje que ha sido generado.
	 * @param mensaje String: El mensaje de lo que pasó.
	 */
	static public void log(Level level, String mensaje) {
		if (DEBUG) {
			if (DEBUG_TIPO == DEBUG_TIPOS.ARCHIVO) {
				Logger.getLogger(LOGNAME).log(level, mensaje);
			}
			else {
				System.out.println(mensaje + "\n");
			}
		}
	}

	/**
	 * Agregar un log al logger y agregar el stack trace.
	 *
	 * @param level Level: El tipo de error o mensaje que ha sido generado.
	 * @param exception String: El mensaje de lo que pasó.
	 */
	static public void log(Level level, Exception exception) {
		if (DEBUG) {
			if (DEBUG_TIPO == DEBUG_TIPOS.ARCHIVO) {
				StringWriter stringWriter = new StringWriter();
				PrintWriter printWriter = new PrintWriter(stringWriter);
				exception.printStackTrace(printWriter);
				Logger.getLogger(LOGNAME).log(level, stringWriter.toString());
				printWriter.close();
				try {
					stringWriter.close();
				}
				catch (IOException ioexception) {
					Logs.log(Level.SEVERE, ioexception);
				}
			}
			else {
				exception.printStackTrace();
			}
		}
	}
}
