package corbak;

/**
 * Interface definition : AV
 * 
 * @author OpenORB Compiler
 */
public class AVPOATie extends AVPOA
{

    //
    // Private reference to implementation object
    //
    private AVOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public AVPOATie(AVOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public AVPOATie(AVOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public AVOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(AVOperations delegate_)
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
    public void revocCertif(org.omg.CORBA.Object client, corbak.Certificat certif)
    {
        _tie.revocCertif( client,  certif);
    }

    /**
     * Operation verification
     */
    public boolean verification(corbak.Certificat certificat)
        throws corbak.certificatInvalide
    {
        return _tie.verification( certificat);
    }

}
