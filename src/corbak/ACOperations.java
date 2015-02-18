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
    public void revocCertif(corbak.Certificat certif)
        throws corbak.certificatInvalide;

    /**
     * Operation generationCertificat
     */
    public corbak.Certificat generationCertificat(String PubKey, corbak.Date dateExpiration, org.omg.CORBA.Object ACemmetrice, corbak.Signature sign);

    /**
     * Operation verification
     */
    public void verification(corbak.Signature sign);

}
