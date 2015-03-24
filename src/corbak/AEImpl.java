package corbak;

import java.util.Calendar;
import java.util.Hashtable;

import logs.logs;

import org.omg.CosNaming.NamingContext;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class AEImpl extends AEPOA {

	public static AC monAC;
	private static Hashtable<String, String> listClient;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out
				.println("##################################################\t"
						+ args[0]
						+ "\t##################################################");
		String nomAE = args[0];
		
		// Init authentification
		
		listClient = new Hashtable<String, String>();
		listClient.put("mathieu", "raoux");
		listClient.put("arnaud", "loisy");
		listClient.put("julie", "thene");
		
		try {
			// Intialisation de l'ORB
			// ************************
			final org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));

			// Creation des servants
			// *********************
			AEImpl monAE = new AEImpl();

			// Activer le servant au sein du POA et recuperer son ID
			byte[] monAEId = rootPOA.activate_object(monAE);

			// Activer le POA manager
			rootPOA.the_POAManager().activate();

			// Enregistrement dans le service de nommage
			// *******************************************
			// Recuperation du naming service
			NamingContext nameRoot = org.omg.CosNaming.NamingContextHelper
					.narrow(orb.resolve_initial_references("NameService"));

			// Construction du nom a enregistrer
			org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
			// System.out.println("Sous quel nom voulez-vous enregistrer l'objet Corba ?");
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(System.in));
			// String nomAE = in.readLine();
			nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomAE, "");

			// Enregistrement de l'objet CORBA dans le service de noms
			nameRoot.rebind(nameToRegister, rootPOA.servant_to_reference(monAE));
			logs.log("info", nomAE
					+ "' est enregistre dans le service de noms.");

			String IORServant = orb.object_to_string(rootPOA
					.servant_to_reference(monAE));
			logs.log("dev", "L'objet possede la reference suivante :");
			logs.log("dev", IORServant);

			String nomAC = nomAE.replaceAll("AE", "AC");

			logs.log("info", "demande de ratachement à l'" + nomAC + "...");

			// Construction du nom a rechercher
			org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
			nameToFind[0] = new org.omg.CosNaming.NameComponent(nomAC, "");

			// Recherche aupres du naming service
			org.omg.CORBA.Object distantAC = nameRoot.resolve(nameToFind);
			logs.log("info", "Objet '" + nomAC
					+ "' trouve aupres du service de noms.");
			logs.log("debug", orb.object_to_string(distantAC));
			monAC = ACHelper.narrow(distantAC);

			logs.log("dev", "Shields online ;)");

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

	public boolean authentification(String login, String password) {
		logs.log("info", login + " demande à s'authentifier : OK");
		// Check si le login existe dans la liste
		if(listClient.containsKey(login))
		{
			// Check si le password correspond
			if(listClient.get(login).equals(password)){
				return true;
			}
			else 
				return false;
		}
		else
			return false;
	}



	@Override
	public boolean revocCertif(String login, String password, Certificat certif)
			throws droitsInsufisants, certificatInvalide {
		return monAC.revocCertif(certif);
	}

	@Override
	public Certificat genererCertificat(String PubKey) {
		Certificat cert = null;
		try {

			logs.log("debug", "genererCertificat inside");

			String hash = Integer.toString(PubKey.hashCode());
			logs.log("debug", "hash =>" + hash);
			Signature sig = new Signature(hash);
			logs.log("debug", "Sig.hash =>" + sig.hash);
			Calendar c = Calendar.getInstance();

			Date expir = new Date((short) (c.get(Calendar.YEAR + 1)),
					(short) c.get(Calendar.MONTH),
					(short) c.get(Calendar.DAY_OF_MONTH),
					(short) c.get(Calendar.HOUR),
					(short) c.get(Calendar.MINUTE),
					(short) c.get(Calendar.SECOND));
			logs.log("debug", "Date expir :" + c.get(Calendar.YEAR + 1));
			cert = monAC.generationCertificat(PubKey, expir, monAC, sig);
			logs.log("debug", "monAC.generationCertificat");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cert;

	}

}
