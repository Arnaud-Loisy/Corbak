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
    public boolean authentification(String login, String password)
        throws corbak.authentificationEchouee;

    /**
     * Operation revocCertif
     */
    public boolean revocCertif(String login, String password, corbak.Certificat certif)
        throws corbak.droitsInsufisants, corbak.certificatInvalide;

    /**
     * Operation genererCertificat
     */
    public corbak.Certificat genererCertificat(String PubKey);

}
