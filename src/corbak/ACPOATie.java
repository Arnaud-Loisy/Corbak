package corbak;

/**
 * Interface definition : AC
 * 
 * @author OpenORB Compiler
 */
public class ACPOATie extends ACPOA {

	//
	// Private reference to implementation object
	//
	private ACOperations _tie;

	//
	// Private reference to POA
	//
	private org.omg.PortableServer.POA _poa;

	/**
	 * Constructor
	 */
	public ACPOATie(ACOperations tieObject) {
		_tie = tieObject;
	}

	/**
	 * Constructor
	 */
	public ACPOATie(ACOperations tieObject, org.omg.PortableServer.POA poa) {
		_tie = tieObject;
		_poa = poa;
	}

	/**
	 * Get the delegate
	 */
	public ACOperations _delegate() {
		return _tie;
	}

	/**
	 * Set the delegate
	 */
	public void _delegate(ACOperations delegate_) {
		_tie = delegate_;
	}

	/**
	 * _default_POA method
	 */
	public org.omg.PortableServer.POA _default_POA() {
		if (_poa != null)
			return _poa;
		else
			return super._default_POA();
	}

	/**
	 * Operation revocCertif
	 */
	public void revocCertif(corbak.Certificat certif)
			throws corbak.certificatInvalide {
		_tie.revocCertif(certif);
	}

	/**
	 * Operation generationCertificat
	 */
	public corbak.Certificat generationCertificat(String PubKey,
			corbak.Date dateExpiration, org.omg.CORBA.Object ACemmetrice,
			corbak.Signature sign) {
		return _tie.generationCertificat(PubKey, dateExpiration, ACemmetrice,
				sign);
	}

	/**
	 * Operation verification
	 */
	public boolean verification(corbak.Signature sign) {
		return _tie.verification(sign);
	}

}
