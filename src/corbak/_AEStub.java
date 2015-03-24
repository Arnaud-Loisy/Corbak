package corbak;

/**
 * Interface definition : AE
 * 
 * @author OpenORB Compiler
 */
public class _AEStub extends org.omg.CORBA.portable.ObjectImpl
        implements AE
{
    static final String[] _ids_list =
    {
        "IDL:corbak/AE:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = corbak.AEOperations.class;

    /**
     * Operation authentification
     */
    public boolean authentification(String login, String password)
        throws corbak.authentificationEchouee
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("authentification",true);
                    _output.write_string(login);
                    _output.write_string(password);
                    _input = this._invoke(_output);
                    boolean _arg_ret = _input.read_boolean();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(corbak.authentificationEchoueeHelper.id()))
                    {
                        throw corbak.authentificationEchoueeHelper.read(_exception.getInputStream());
                    }

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("authentification",_opsClass);
                if (_so == null)
                   continue;
                corbak.AEOperations _self = (corbak.AEOperations) _so.servant;
                try
                {
                    return _self.authentification( login,  password);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation revocCertif
     */
    public boolean revocCertif(String login, String password, corbak.Certificat certif)
        throws corbak.droitsInsufisants, corbak.certificatInvalide
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("revocCertif",true);
                    _output.write_string(login);
                    _output.write_string(password);
                    corbak.CertificatHelper.write(_output,certif);
                    _input = this._invoke(_output);
                    boolean _arg_ret = _input.read_boolean();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(corbak.droitsInsufisantsHelper.id()))
                    {
                        throw corbak.droitsInsufisantsHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(corbak.certificatInvalideHelper.id()))
                    {
                        throw corbak.certificatInvalideHelper.read(_exception.getInputStream());
                    }

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("revocCertif",_opsClass);
                if (_so == null)
                   continue;
                corbak.AEOperations _self = (corbak.AEOperations) _so.servant;
                try
                {
                    return _self.revocCertif( login,  password,  certif);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation genererCertificat
     */
    public corbak.Certificat genererCertificat(String PubKey)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("genererCertificat",true);
                    _output.write_string(PubKey);
                    _input = this._invoke(_output);
                    corbak.Certificat _arg_ret = corbak.CertificatHelper.read(_input);
                    return _arg_ret;
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("genererCertificat",_opsClass);
                if (_so == null)
                   continue;
                corbak.AEOperations _self = (corbak.AEOperations) _so.servant;
                try
                {
                    return _self.genererCertificat( PubKey);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
