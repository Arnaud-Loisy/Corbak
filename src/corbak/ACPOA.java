package corbak;

/**
 * Interface definition : AC
 * 
 * @author OpenORB Compiler
 */
public abstract class ACPOA extends org.omg.PortableServer.Servant
        implements ACOperations, org.omg.CORBA.portable.InvokeHandler
{
    public AC _this()
    {
        return ACHelper.narrow(_this_object());
    }

    public AC _this(org.omg.CORBA.ORB orb)
    {
        return ACHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:corbak/AC:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("generationCertificat")) {
                return _invoke_generationCertificat(_is, handler);
        } else if (opName.equals("revocCertif")) {
                return _invoke_revocCertif(_is, handler);
        } else if (opName.equals("verification")) {
                return _invoke_verification(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_revocCertif(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        corbak.Certificat arg0_in = corbak.CertificatHelper.read(_is);

        try
        {
            boolean _arg_result = revocCertif(arg0_in);

            _output = handler.createReply();
            _output.write_boolean(_arg_result);

        }
        catch (corbak.certificatInvalide _exception)
        {
            _output = handler.createExceptionReply();
            corbak.certificatInvalideHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_generationCertificat(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        corbak.Date arg1_in = corbak.DateHelper.read(_is);
        String arg2_in = _is.read_string();
        corbak.Signature arg3_in = corbak.SignatureHelper.read(_is);

        corbak.Certificat _arg_result = generationCertificat(arg0_in, arg1_in, arg2_in, arg3_in);

        _output = handler.createReply();
        corbak.CertificatHelper.write(_output,_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_verification(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        corbak.Signature arg0_in = corbak.SignatureHelper.read(_is);

        boolean _arg_result = verification(arg0_in);

        _output = handler.createReply();
        _output.write_boolean(_arg_result);

        return _output;
    }

}
