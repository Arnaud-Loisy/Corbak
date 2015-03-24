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
            boolean _arg_result = authentification(arg0_in, arg1_in);

            _output = handler.createReply();
            _output.write_boolean(_arg_result);

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
            boolean _arg_result = revocCertif(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();
            _output.write_boolean(_arg_result);

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

        corbak.Certificat _arg_result = genererCertificat(arg0_in);

        _output = handler.createReply();
        corbak.CertificatHelper.write(_output,_arg_result);

        return _output;
    }

}
