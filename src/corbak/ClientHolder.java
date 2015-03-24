package corbak;

/**
 * Holder class for : Client
 * 
 * @author OpenORB Compiler
 */
final public class ClientHolder implements org.omg.CORBA.portable.Streamable {
	/**
	 * Internal Client value
	 */
	public corbak.Client value;

	/**
	 * Default constructor
	 */
	public ClientHolder() {
	}

	/**
	 * Constructor with value initialisation
	 * 
	 * @param initial
	 *            the initial value
	 */
	public ClientHolder(corbak.Client initial) {
		value = initial;
	}

	/**
	 * Read Client from a marshalled stream
	 * 
	 * @param istream
	 *            the input stream
	 */
	public void _read(org.omg.CORBA.portable.InputStream istream) {
		value = ClientHelper.read(istream);
	}

	/**
	 * Write Client into a marshalled stream
	 * 
	 * @param ostream
	 *            the output stream
	 */
	public void _write(org.omg.CORBA.portable.OutputStream ostream) {
		ClientHelper.write(ostream, value);
	}

	/**
	 * Return the Client TypeCode
	 * 
	 * @return a TypeCode
	 */
	public org.omg.CORBA.TypeCode _type() {
		return ClientHelper.type();
	}

}
