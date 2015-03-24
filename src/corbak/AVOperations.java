package corbak;

/**
 * Interface definition : AV
 * 
 * @author OpenORB Compiler
 */
public interface AVOperations {
	/**
	 * Operation revocCertif
	 */
	public void revocCertif(org.omg.CORBA.Object client,
			corbak.Certificat certif);

	/**
	 * Operation verification
	 */
	public boolean verification(corbak.Certificat certificat)
			throws corbak.certificatInvalide;

}
