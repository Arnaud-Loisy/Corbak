package corbak;

/**
 * Helper class for : Client
 * 
 * @author OpenORB Compiler
 */
public class ClientHelper {
	/**
	 * Insert Client into an any
	 * 
	 * @param a
	 *            an any
	 * @param t
	 *            Client value
	 */
	public static void insert(org.omg.CORBA.Any a, corbak.Client t) {
		a.insert_Object(t, type());
	}

	/**
	 * Extract Client from an any
	 * 
	 * @param a
	 *            an any
	 * @return the extracted Client value
	 */
	public static corbak.Client extract(org.omg.CORBA.Any a) {
		if (!a.type().equal(type()))
			throw new org.omg.CORBA.MARSHAL();
		try {
			return corbak.ClientHelper.narrow(a.extract_Object());
		} catch (final org.omg.CORBA.BAD_PARAM e) {
			throw new org.omg.CORBA.MARSHAL(e.getMessage());
		}
	}

	//
	// Internal TypeCode value
	//
	private static org.omg.CORBA.TypeCode _tc = null;

	/**
	 * Return the Client TypeCode
	 * 
	 * @return a TypeCode
	 */
	public static org.omg.CORBA.TypeCode type() {
		if (_tc == null) {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
			_tc = orb.create_interface_tc(id(), "Client");
		}
		return _tc;
	}

	/**
	 * Return the Client IDL ID
	 * 
	 * @return an ID
	 */
	public static String id() {
		return _id;
	}

	private final static String _id = "IDL:corbak/Client:1.0";

	/**
	 * Read Client from a marshalled stream
	 * 
	 * @param istream
	 *            the input stream
	 * @return the readed Client value
	 */
	public static corbak.Client read(org.omg.CORBA.portable.InputStream istream) {
		return (corbak.Client) istream.read_Object(corbak._ClientStub.class);
	}

	/**
	 * Write Client into a marshalled stream
	 * 
	 * @param ostream
	 *            the output stream
	 * @param value
	 *            Client value
	 */
	public static void write(org.omg.CORBA.portable.OutputStream ostream,
			corbak.Client value) {
		ostream.write_Object((org.omg.CORBA.portable.ObjectImpl) value);
	}

	/**
	 * Narrow CORBA::Object to Client
	 * 
	 * @param obj
	 *            the CORBA Object
	 * @return Client Object
	 */
	public static Client narrow(org.omg.CORBA.Object obj) {
		if (obj == null)
			return null;
		if (obj instanceof Client)
			return (Client) obj;

		if (obj._is_a(id())) {
			_ClientStub stub = new _ClientStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl) obj)
					._get_delegate());
			return stub;
		}

		throw new org.omg.CORBA.BAD_PARAM();
	}

	/**
	 * Unchecked Narrow CORBA::Object to Client
	 * 
	 * @param obj
	 *            the CORBA Object
	 * @return Client Object
	 */
	public static Client unchecked_narrow(org.omg.CORBA.Object obj) {
		if (obj == null)
			return null;
		if (obj instanceof Client)
			return (Client) obj;

		_ClientStub stub = new _ClientStub();
		stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl) obj)
				._get_delegate());
		return stub;

	}

}
