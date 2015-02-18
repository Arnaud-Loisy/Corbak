package corbak;

/**
 * Interface definition : AE
 * 
 * @author OpenORB Compiler
 */
public interface AEOperations
{
    /**
     * Operation revocCertif
     */
    public void revocCertif(String login, String password, corbak.Certificat certif)
        throws corbak.droitsInsufisants, corbak.certificatInvalide;

    /**
     * Operation genererCertificat
     */
    public void genererCertificat(String PubKey, corbak.Date dateExpiration, org.omg.CORBA.Object ACemmetrice, corbak.Signature sign);

}
