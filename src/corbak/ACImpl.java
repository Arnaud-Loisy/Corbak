package corbak;

import java.util.ArrayList;

import org.omg.CORBA.Object;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class ACImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}
	
	ArrayList<Certificat> listCert;
	org.omg.CORBA.ORB orb;
	
	public ACImpl (org.omg.CORBA.ORB orb)
	{
		listCert = new ArrayList<Certificat>();
		this.orb=orb;
	}
	
	public Certificat generateCertificat(String PubKey, Date dateExpiration, org.omg.CORBA.Object ACemmetrice, Signature sign)
	{
		Certificat cert = new Certificat();
		cert.pubClef=PubKey;
		cert.dateExpiration=dateExpiration;
		cert.ACemmetrice=ACemmetrice;
		cert.sign=sign;
		listCert.add(cert);
		return cert;
	}
}
