package cl.cromer.estructuras;

/**
 * Esta clase contiene los tipos de arboles.
 *
 * @author Chris Cromer
 */
final public class ArbolTipos {
	/**
	 * Tipo general.
	 */
	static final public int GENERAL = 0;

	/**
	 * Tipo binario.
	 */
	static final public int BINARIO = 1;

	/**
	 * Tipo busqueda binaria.
	 */
	static final public int BUSQUEDA_BINARIA = 2;

	/**
	 * Tipo AVL.
	 */
	static final public int AVL = 3;

	/**
	 * Tipo rojo-negro.
	 */
	static final public int ROJO_NEGRO = 4;

	/**
	 * Tipo B-Tree.
	 */
	static final public int B_TREE = 5;

	/**
	 * El tipo elegido.
	 */
	final private int tipo;

	/**
	 * Inicilizar el tipo de arbol.
	 *
	 * @param tipo int: El tipo de lista enlazada, {@value #GENERAL}, {@value #BINARIO}, {@value #BUSQUEDA_BINARIA}, {@value #AVL}, {@value #ROJO_NEGRO} o {@value #B_TREE}
	 */
	public ArbolTipos(int tipo) {
		switch (tipo) {
			case GENERAL:
				this.tipo = GENERAL;
				break;
			case BINARIO:
				this.tipo = BINARIO;
				break;
			case BUSQUEDA_BINARIA:
				this.tipo = BUSQUEDA_BINARIA;
				break;
			case AVL:
				this.tipo = AVL;
				break;
			case ROJO_NEGRO:
				this.tipo = ROJO_NEGRO;
				break;
			case B_TREE:
				this.tipo = B_TREE;
				break;
			default:
				this.tipo = GENERAL;
		}
	}

	/**
	 * Devolver el tipo de arbol.
	 *
	 * @return int: El tipo.
	 */
	public int getTipo() {
		return tipo;
	}
}
