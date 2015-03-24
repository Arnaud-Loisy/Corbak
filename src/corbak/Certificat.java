package corbak;

/**
 * Struct definition : Certificat
 * 
 * @author OpenORB Compiler
 */
public final class Certificat implements org.omg.CORBA.portable.IDLEntity {
	/**
	 * Struct member dateExpiration
	 */
	public corbak.Date dateExpiration;

	/**
	 * Struct member ACemmetrice
	 */
	public org.omg.CORBA.Object ACemmetrice;

	/**
	 * Struct member pubClef
	 */
	public String pubClef;

	/**
	 * Struct member sign
	 */
	public corbak.Signature sign;

	/**
	 * Default constructor
	 */
	public Certificat() {
	}

	/**
	 * Constructor with fields initialization
	 * 
	 * @param dateExpiration
	 *            dateExpiration struct member
	 * @param ACemmetrice
	 *            ACemmetrice struct member
	 * @param pubClef
	 *            pubClef struct member
	 * @param sign
	 *            sign struct member
	 */
	public Certificat(corbak.Date dateExpiration,
			org.omg.CORBA.Object ACemmetrice, String pubClef,
			corbak.Signature sign) {
		this.dateExpiration = dateExpiration;
		this.ACemmetrice = ACemmetrice;
		this.pubClef = pubClef;
		this.sign = sign;
	}

}
