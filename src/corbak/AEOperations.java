package corbak;

/**
 * Interface definition : AE
 * 
 * @author OpenORB Compiler
 */
public interface AEOperations
{
    /**
     * Operation authentification
     */
    public short authentification(String login, String password)
        throws corbak.authentificationEchouee;

    /**
     * Operation revocCertif
     */
    public void revocCertif(String login, String password, corbak.Certificat certif)
        throws corbak.droitsInsufisants, corbak.certificatInvalide;

    /**
     * Operation genererCertificat
     */
    public corbak.Certificat genererCertificat(String PubKey);

}
