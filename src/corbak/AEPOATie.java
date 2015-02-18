package corbak;

/**
 * Interface definition : AE
 * 
 * @author OpenORB Compiler
 */
public class AEPOATie extends AEPOA
{

    //
    // Private reference to implementation object
    //
    private AEOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public AEPOATie(AEOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public AEPOATie(AEOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public AEOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(AEOperations delegate_)
    {
        _tie = delegate_;
    }

    /**
     * _default_POA method
     */
    public org.omg.PortableServer.POA _default_POA()
    {
        if (_poa != null)
            return _poa;
        else
            return super._default_POA();
    }

    /**
     * Operation revocCertif
     */
    public void revocCertif(String login, String password, corbak.Certificat certif)
        throws corbak.droitsInsufisants, corbak.certificatInvalide
    {
        _tie.revocCertif( login,  password,  certif);
    }

    /**
     * Operation genererCertificat
     */
    public void genererCertificat(String PubKey, corbak.Date dateExpiration, org.omg.CORBA.Object ACemmetrice, corbak.Signature sign)
    {
        _tie.genererCertificat( PubKey,  dateExpiration,  ACemmetrice,  sign);
    }

}
