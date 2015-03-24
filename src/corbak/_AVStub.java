package corbak;

/**
 * Interface definition : AV
 * 
 * @author OpenORB Compiler
 */
public class _AVStub extends org.omg.CORBA.portable.ObjectImpl implements AV {
	static final String[] _ids_list = { "IDL:corbak/AV:1.0" };

	public String[] _ids() {
		return _ids_list;
	}

	private final static Class _opsClass = corbak.AVOperations.class;

	/**
	 * Operation revocCertif
	 */
	public void revocCertif(org.omg.CORBA.Object client,
			corbak.Certificat certif) {
		while (true) {
			if (!this._is_local()) {
				org.omg.CORBA.portable.InputStream _input = null;
				try {
					org.omg.CORBA.portable.OutputStream _output = this
							._request("revocCertif", true);
					corbak.IORHelper.write(_output, client);
					corbak.CertificatHelper.write(_output, certif);
					_input = this._invoke(_output);
					return;
				} catch (org.omg.CORBA.portable.RemarshalException _exception) {
					continue;
				} catch (org.omg.CORBA.portable.ApplicationException _exception) {
					String _exception_id = _exception.getId();
					throw new org.omg.CORBA.UNKNOWN(
							"Unexpected User Exception: " + _exception_id);
				} finally {
					this._releaseReply(_input);
				}
			} else {
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke(
						"revocCertif", _opsClass);
				if (_so == null)
					continue;
				corbak.AVOperations _self = (corbak.AVOperations) _so.servant;
				try {
					_self.revocCertif(client, certif);
					return;
				} finally {
					_servant_postinvoke(_so);
				}
			}
		}
	}

	/**
	 * Operation verification
	 */
	public boolean verification(corbak.Certificat certificat)
			throws corbak.certificatInvalide {
		while (true) {
			if (!this._is_local()) {
				org.omg.CORBA.portable.InputStream _input = null;
				try {
					org.omg.CORBA.portable.OutputStream _output = this
							._request("verification", true);
					corbak.CertificatHelper.write(_output, certificat);
					_input = this._invoke(_output);
					boolean _arg_ret = _input.read_boolean();
					return _arg_ret;
				} catch (org.omg.CORBA.portable.RemarshalException _exception) {
					continue;
				} catch (org.omg.CORBA.portable.ApplicationException _exception) {
					String _exception_id = _exception.getId();
					if (_exception_id.equals(corbak.certificatInvalideHelper
							.id())) {
						throw corbak.certificatInvalideHelper.read(_exception
								.getInputStream());
					}

					throw new org.omg.CORBA.UNKNOWN(
							"Unexpected User Exception: " + _exception_id);
				} finally {
					this._releaseReply(_input);
				}
			} else {
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke(
						"verification", _opsClass);
				if (_so == null)
					continue;
				corbak.AVOperations _self = (corbak.AVOperations) _so.servant;
				try {
					return _self.verification(certificat);
				} finally {
					_servant_postinvoke(_so);
				}
			}
		}
	}

}
