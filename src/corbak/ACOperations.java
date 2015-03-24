package corbak;

/**
 * Interface definition : AC
 * 
 * @author OpenORB Compiler
 */
public interface ACOperations
{
    /**
     * Operation revocCertif
     */
    public boolean revocCertif(corbak.Certificat certif)
        throws corbak.certificatInvalide;

    /**
     * Operation generationCertificat
     */
    public corbak.Certificat generationCertificat(String PubKey, corbak.Date dateExpiration, String ACemmetrice, corbak.Signature sign);

    /**
     * Operation verification
     */
    public boolean verification(corbak.Signature sign);

}
