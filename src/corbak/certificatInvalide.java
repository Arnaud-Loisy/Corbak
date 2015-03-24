package corbak;

/**
 * Exception definition : certificatInvalide
 * 
 * @author OpenORB Compiler
 */
public final class certificatInvalide extends org.omg.CORBA.UserException {
	/**
	 * Exception member raison
	 */
	public String raison;

	/**
	 * Exception member id
	 */
	public short id;

	/**
	 * Default constructor
	 */
	public certificatInvalide() {
		super(certificatInvalideHelper.id());
	}

	/**
	 * Constructor with fields initialization
	 * 
	 * @param raison
	 *            raison exception member
	 * @param id
	 *            id exception member
	 */
	public certificatInvalide(String raison, short id) {
		super(certificatInvalideHelper.id());
		this.raison = raison;
		this.id = id;
	}

	/**
	 * Full constructor with fields initialization
	 * 
	 * @param raison
	 *            raison exception member
	 * @param id
	 *            id exception member
	 */
	public certificatInvalide(String orb_reason, String raison, short id) {
		super(certificatInvalideHelper.id() + " " + orb_reason);
		this.raison = raison;
		this.id = id;
	}

}
