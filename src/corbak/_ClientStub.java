package corbak;

/**
 * Interface definition : Client
 * 
 * @author OpenORB Compiler
 */
public class _ClientStub extends org.omg.CORBA.portable.ObjectImpl
        implements Client
{
    static final String[] _ids_list =
    {
        "IDL:corbak/Client:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = corbak.ClientOperations.class;

    /**
     * Operation envoyerMessage
     */
    public void envoyerMessage(corbak.Message msg, corbak.Certificat certif)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("envoyerMessage",true);
                    corbak.MessageHelper.write(_output,msg);
                    corbak.CertificatHelper.write(_output,certif);
                    _input = this._invoke(_output);
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("envoyerMessage",_opsClass);
                if (_so == null)
                   continue;
                corbak.ClientOperations _self = (corbak.ClientOperations) _so.servant;
                try
                {
                    _self.envoyerMessage( msg,  certif);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation envoyer
     */
    public void envoyer(corbak.Certificat certif)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("envoyer",true);
                    corbak.CertificatHelper.write(_output,certif);
                    _input = this._invoke(_output);
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("envoyer",_opsClass);
                if (_so == null)
                   continue;
                corbak.ClientOperations _self = (corbak.ClientOperations) _so.servant;
                try
                {
                    _self.envoyer( certif);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
