package corbak;

/**
 * Interface definition : AC
 * 
 * @author OpenORB Compiler
 */
public class _ACStub extends org.omg.CORBA.portable.ObjectImpl
        implements AC
{
    static final String[] _ids_list =
    {
        "IDL:corbak/AC:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = corbak.ACOperations.class;

    /**
     * Operation revocCertif
     */
    public void revocCertif(corbak.Certificat certif)
        throws corbak.certificatInvalide
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("revocCertif",true);
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
                corbak.ACOperations _self = (corbak.ACOperations) _so.servant;
                try
                {
                    _self.revocCertif( certif);
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
     * Operation generationCertificat
     */
    public corbak.Certificat generationCertificat(String PubKey, corbak.Date dateExpiration, org.omg.CORBA.Object ACemmetrice, corbak.Signature sign)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("generationCertificat",true);
                    _output.write_string(PubKey);
                    corbak.DateHelper.write(_output,dateExpiration);
                    _output.write_Object(ACemmetrice);
                    corbak.SignatureHelper.write(_output,sign);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("generationCertificat",_opsClass);
                if (_so == null)
                   continue;
                corbak.ACOperations _self = (corbak.ACOperations) _so.servant;
                try
                {
                    return _self.generationCertificat( PubKey,  dateExpiration,  ACemmetrice,  sign);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation verification
     */
    public void verification(corbak.Signature sign)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("verification",true);
                    corbak.SignatureHelper.write(_output,sign);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("verification",_opsClass);
                if (_so == null)
                   continue;
                corbak.ACOperations _self = (corbak.ACOperations) _so.servant;
                try
                {
                    _self.verification( sign);
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
