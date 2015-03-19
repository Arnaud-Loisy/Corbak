package corbak;

/**
 * Interface definition : AE
 * 
 * @author OpenORB Compiler
 */
public abstract class AEPOA extends org.omg.PortableServer.Servant
        implements AEOperations, org.omg.CORBA.portable.InvokeHandler
{
    public AE _this()
    {
        return AEHelper.narrow(_this_object());
    }

    public AE _this(org.omg.CORBA.ORB orb)
    {
        return AEHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:corbak/AE:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("authentification")) {
                return _invoke_authentification(_is, handler);
        } else if (opName.equals("genererCertificat")) {
                return _invoke_genererCertificat(_is, handler);
        } else if (opName.equals("revocCertif")) {
                return _invoke_revocCertif(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_authentification(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();

        try
        {
            authentification(arg0_in, arg1_in);

            _output = handler.createReply();

        }
        catch (corbak.authentificationEchouee _exception)
        {
            _output = handler.createExceptionReply();
            corbak.authentificationEchoueeHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_revocCertif(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        corbak.Certificat arg2_in = corbak.CertificatHelper.read(_is);

        try
        {
            revocCertif(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (corbak.droitsInsufisants _exception)
        {
            _output = handler.createExceptionReply();
            corbak.droitsInsufisantsHelper.write(_output,_exception);
        }
        catch (corbak.certificatInvalide _exception)
        {
            _output = handler.createExceptionReply();
            corbak.certificatInvalideHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_genererCertificat(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        corbak.Date arg1_in = corbak.DateHelper.read(_is);
        org.omg.CORBA.Object arg2_in = corbak.IORHelper.read(_is);
        corbak.Signature arg3_in = corbak.SignatureHelper.read(_is);

        genererCertificat(arg0_in, arg1_in, arg2_in, arg3_in);

        _output = handler.createReply();

        return _output;
    }

}
