package corbak;

import java.util.ArrayList;

import logs.logs;

import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;


public class ACImpl extends ACPOA {

	public static AV monAV;
	public static String PubKey;
	public static Certificat monCertif;
	public static NamingContext nameRoot;
	public static String nomAC;
	public static ArrayList<Certificat> listCert;
	public static org.omg.CORBA.ORB orb;
	
	public static String genPubKey(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~#{[|`^@]}^$ù*¨£µ%!:;,?./§";
		String pubKey = "";
		for (int x = 0; x < length; x++) {
			int i = (int) Math.floor(Math.random() * 88);
			pubKey += chars.charAt(i);
		}
		logs.log("dev", "Clé publique générée : \n" + pubKey);
		return pubKey;
	}

	public static void main(String[] args) {

		System.out
				.println("##################################################\t"
						+ args[0]
						+ "\t##################################################");
		try {
			PubKey = genPubKey(128);
			nomAC = args[0];

			// initialisation de la liste.

			listCert = new ArrayList<Certificat>();
			// Intialisation de l'ORB
			// ************************
			final org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));

			// Creation du servant
			// *********************
			ACImpl monAC = new ACImpl();

			// Activer le servant au sein du POA et recuperer son ID
			byte[] monACId = rootPOA.activate_object(monAC);

			// Activer le POA manager
			rootPOA.the_POAManager().activate();

			// Enregistrement dans le service de nommage
			// *******************************************
			// Recuperation du naming service
			nameRoot = org.omg.CosNaming.NamingContextHelper
					.narrow(orb.resolve_initial_references("NameService"));

			// Construction du nom a enregistrer
			org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
			// System.out.println("Sous quel nom voulez-vous enregistrer l'objet Corba ?");
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(System.in));
			// String nomObj = in.readLine();
			nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomAC, "");

			// Enregistrement de l'objet CORBA dans le service de noms
			nameRoot.rebind(nameToRegister, rootPOA.servant_to_reference(monAC));
			logs.log("info", nomAC+ "' est enregistre dans le service de noms.");

			String IORServant = orb.object_to_string(rootPOA.servant_to_reference(monAC));
			logs.log("debug", "L'objet possede la reference suivante :");
			logs.log("debug", IORServant);
			
			
			/*
			//AV
			org.omg.CosNaming.NameComponent[] nameToFindAV = new org.omg.CosNaming.NameComponent[1];
			nameToFindAV[0] = new org.omg.CosNaming.NameComponent(
					nomAC.replaceAll("AC", "AV"), "");

			// Recherche aupres du naming service AV
			org.omg.CORBA.Object distantAV = nameRoot.resolve(nameToFindAV);
			logs.log("info", "Objet '" + nomAC.replaceAll("AC", "AV")
					+ "' trouve aupres du service de noms. IOR de l'objet :");
			logs.log("debug", orb.object_to_string(distantAV));
			monAV = AVHelper.narrow(distantAV);*/

			// Lancement de l'ORB et mise en attente de requete
			// **************************************************

			Thread t = new Thread(new Runnable() {
				public void run() {
					orb.run();
				}
			});
			t.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ACImpl() {
		listCert = new ArrayList<Certificat>();

	}

	public boolean revocCertif(Certificat cert) {
		for (int i = 0; i < listCert.size(); i++) {
			if (listCert.get(i).pubClef.equals( cert.pubClef))
				listCert.remove(i);
			logs.log("info", "Certif révoqué");
			break;
		}
		
		try {
			//AV
			org.omg.CosNaming.NameComponent[] nameToFindAV = new org.omg.CosNaming.NameComponent[1];
			nameToFindAV[0] = new org.omg.CosNaming.NameComponent(
					nomAC.replaceAll("AC", "AV"), "");

			// Recherche aupres du naming service AV
			org.omg.CORBA.Object distantAV = nameRoot.resolve(nameToFindAV);
			logs.log("info", "Objet '" + nomAC.replaceAll("AC", "AV")
					+ "' trouve aupres du service de noms.");
			logs.log("debug", orb.object_to_string(distantAV));
			monAV = AVHelper.narrow(distantAV);
			return monAV.revocCertif(cert);
		} catch (NotFound | CannotProceed | InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public Certificat generationCertificat(String PubKey, Date dateExpiration,String ACemmetrice, Signature sign) {

		Certificat cert = new Certificat(dateExpiration, ACemmetrice, PubKey,sign);

		listCert.add(cert);
		return cert;

	}

	@Override
	public boolean verification(Signature sign) {
		for (int i = 0; i < listCert.size(); i++) {
			;
			if (sign.hash.equals(listCert.get(i).sign.hash)) {
				logs.log("dev", "Valide");
				return true;
			}
		}
		return false;
	}
}
