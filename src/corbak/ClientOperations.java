package corbak;

/**
 * Interface definition : Client
 * 
 * @author OpenORB Compiler
 */
public interface ClientOperations {
	/**
	 * Operation envoyerMessage
	 */
	public void envoyerMessage(corbak.Message msg, corbak.Certificat certif);

	/**
	 * Operation envoyer
	 */
	public void envoyer(corbak.Certificat certif);

}
