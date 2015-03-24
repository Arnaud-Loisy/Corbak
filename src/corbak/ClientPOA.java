package corbak;

/**
 * Interface definition : Client
 * 
 * @author OpenORB Compiler
 */
public abstract class ClientPOA extends org.omg.PortableServer.Servant
		implements ClientOperations, org.omg.CORBA.portable.InvokeHandler {
	public Client _this() {
		return ClientHelper.narrow(_this_object());
	}

	public Client _this(org.omg.CORBA.ORB orb) {
		return ClientHelper.narrow(_this_object(orb));
	}

	private static String[] _ids_list = { "IDL:corbak/Client:1.0" };

	public String[] _all_interfaces(org.omg.PortableServer.POA poa,
			byte[] objectId) {
		return _ids_list;
	}

	public final org.omg.CORBA.portable.OutputStream _invoke(
			final String opName, final org.omg.CORBA.portable.InputStream _is,
			final org.omg.CORBA.portable.ResponseHandler handler) {

		if (opName.equals("envoyer")) {
			return _invoke_envoyer(_is, handler);
		} else if (opName.equals("envoyerMessage")) {
			return _invoke_envoyerMessage(_is, handler);
		} else {
			throw new org.omg.CORBA.BAD_OPERATION(opName);
		}
	}

	// helper methods
	private org.omg.CORBA.portable.OutputStream _invoke_envoyerMessage(
			final org.omg.CORBA.portable.InputStream _is,
			final org.omg.CORBA.portable.ResponseHandler handler) {
		org.omg.CORBA.portable.OutputStream _output;
		corbak.Message arg0_in = corbak.MessageHelper.read(_is);
		corbak.Certificat arg1_in = corbak.CertificatHelper.read(_is);

		envoyerMessage(arg0_in, arg1_in);

		_output = handler.createReply();

		return _output;
	}

	private org.omg.CORBA.portable.OutputStream _invoke_envoyer(
			final org.omg.CORBA.portable.InputStream _is,
			final org.omg.CORBA.portable.ResponseHandler handler) {
		org.omg.CORBA.portable.OutputStream _output;
		corbak.Certificat arg0_in = corbak.CertificatHelper.read(_is);

		envoyer(arg0_in);

		_output = handler.createReply();

		return _output;
	}

}
