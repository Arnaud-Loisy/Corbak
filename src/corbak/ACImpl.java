package corbak;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class ACImpl {

	public static void main(String[] args) {
		try
		{
			// Intialisation de l'orb
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);
			System.out.println("Quel est l'IOR de cette objet Corba ?");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String IOR = in.readLine();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
	
	public void revocCertif(Certificat cert){
		for(int i=0; i<listCert.size(); i++)
		{
			if (listCert.get(i).pubClef == cert.pubClef)
				listCert.remove(i);
			break;
		}
		
	}
}
