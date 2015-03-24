package corbak;

/**
 * Interface definition : AV
 * 
 * @author OpenORB Compiler
 */
public interface AVOperations
{
    /**
     * Operation revocCertif
     */
    public boolean revocCertif(corbak.Certificat certif);

    /**
     * Operation verification
     */
    public boolean verification(corbak.Certificat certificat)
        throws corbak.certificatInvalide;

}
