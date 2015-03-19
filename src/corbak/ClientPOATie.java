package corbak;

/**
 * Interface definition : Client
 * 
 * @author OpenORB Compiler
 */
public class ClientPOATie extends ClientPOA
{

    //
    // Private reference to implementation object
    //
    private ClientOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public ClientPOATie(ClientOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public ClientPOATie(ClientOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public ClientOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(ClientOperations delegate_)
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
     * Operation envoyerMessage
     */
    public void envoyerMessage(corbak.Message msg, corbak.Certificat certif)
    {
        _tie.envoyerMessage( msg,  certif);
    }

    /**
     * Operation envoyer
     */
    public void envoyer(corbak.Certificat certif)
    {
        _tie.envoyer( certif);
    }

}
