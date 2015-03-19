package corbak;

/**
 * Interface definition : AV
 * 
 * @author OpenORB Compiler
 */
public abstract class AVPOA extends org.omg.PortableServer.Servant
        implements AVOperations, org.omg.CORBA.portable.InvokeHandler
{
    public AV _this()
    {
        return AVHelper.narrow(_this_object());
    }

    public AV _this(org.omg.CORBA.ORB orb)
    {
        return AVHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:corbak/AV:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("revocCertif")) {
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
        org.omg.CORBA.Object arg0_in = corbak.IORHelper.read(_is);
        corbak.Certificat arg1_in = corbak.CertificatHelper.read(_is);

        revocCertif(arg0_in, arg1_in);

        _output = handler.createReply();

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_verification(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        corbak.Certificat arg0_in = corbak.CertificatHelper.read(_is);

        try
        {
            verification(arg0_in);

            _output = handler.createReply();

        }
        catch (corbak.certificatInvalide _exception)
        {
            _output = handler.createExceptionReply();
            corbak.certificatInvalideHelper.write(_output,_exception);
        }
        return _output;
    }

}
