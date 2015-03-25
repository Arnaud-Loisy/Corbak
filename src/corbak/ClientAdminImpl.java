package corbak;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import logs.logs;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class ClientAdminImpl extends ClientPOA {
	public static String PubKey;
	public static AE monAE;
	public static AV monAV;
	public static Client monCorrespondant;
	public static Certificat monCertif;
	public static String from;
	public static ArrayList<Certificat> listCert;

	public void envoyerMessage(Message msg, Certificat certif) {

		Calendar c = Calendar.getInstance();

		if (certif.dateExpiration.year < c.get(Calendar.YEAR)) {
			logs.log("debug", "Certificat non expiré.");
			try {
				if (monAV.verification(certif)) {
					System.out.println(from + " : " + msg.text);
				}
			} catch (certificatInvalide e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
		} else
			logs.log("info", "Certificat expiré.");

	}

	@Override
	public void envoyer(Certificat certif) {
		Calendar c = Calendar.getInstance();

		if (certif.dateExpiration.year < c.get(Calendar.YEAR)) {
			logs.log("dev", "Certificat non expiré.");
		} else
			logs.log("info", "Certificat expiré.");

	}

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

	public static void main(String args[]) {

		try {
			System.out
					.println("##################################################\tAdmin\t##################################################");
			PubKey = genPubKey(128);

			// Intialisation de l'orb
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			// Recuperation du naming service
			NamingContext nameRoot = org.omg.CosNaming.NamingContextHelper
					.narrow(orb.resolve_initial_references("NameService"));
			//orb.

			// Saisie du nom de l'objet (si utilisation du service de nommage)
			logs.log("info", "Liste des AE disponibles");
			logs.pagesJaunes(nameRoot, "AE");
			logs.log("info", "Quelle AE voulez-vous contacter ?");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			String idObj = in.readLine();

			// Construction du nom a rechercher
			org.omg.CosNaming.NameComponent[] nameToFindAE = new org.omg.CosNaming.NameComponent[1];
			nameToFindAE[0] = new org.omg.CosNaming.NameComponent(idObj, "");

			org.omg.CosNaming.NameComponent[] nameToFindAV = new org.omg.CosNaming.NameComponent[1];
			nameToFindAV[0] = new org.omg.CosNaming.NameComponent(
					idObj.replaceAll("AE", "AV"), "");

			// Recherche aupres du naming service AE
			org.omg.CORBA.Object distantAE = nameRoot.resolve(nameToFindAE);
			logs.log("info", idObj + " trouve aupres du service de noms.");
			logs.log("debug", orb.object_to_string(distantAE));
			// Recherche aupres du naming service AV
			org.omg.CORBA.Object distantAV = nameRoot.resolve(nameToFindAV);
			logs.log("info", idObj.replaceAll("AE", "AV")
					+ " trouve aupres du service de noms.");
			logs.log("debug", orb.object_to_string(distantAV));
			logs.pagesJaunes(nameRoot, "AV");
			// Utilisation directe de l'IOR (SAUF utilisation du service de
			// nommage)
			// org.omg.CORBA.Object distantEuro =
			// orb.string_to_object("IOR:000000000000001b49444c3a436f6e766572746973736575722f4575726f3a312e30000000000001000000000000007c000102000000000d3137322e31362e39362e35340000cb630000001c00564201000000022f0020200000000400000000000002925225d3ea00000003564953030000000500070801ff000000000000000000000800000000564953000000000100000018000000000001000100000001050100010001010900000000");
			// Casting de l'objet CORBA au type convertisseur euro
			monAE = AEHelper.narrow(distantAE);
			monAV = AVHelper.narrow(distantAV);
			logs.log("info", "Login Client ?");
			String log = in.readLine();
			from = log;
			logs.log("info", "Password Client ?");
			String pass = in.readLine();
			while (!monAE.authentification(log, pass)){
				
			
				logs.log("info", "Login failed check your credentials");
				
				logs.log("info", "Login Client ?");
				log = in.readLine();
				from = log;
				logs.log("info", "Password Client ?");
				pass = in.readLine();
			}
			logs.log("info", "Login successful");
			monCertif = monAE.genererCertificat(PubKey);
			logs.log("dev", "Certificat généré et récupéré.");
			
			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));

			// Creation du servant
			// *********************
			ClientAdminImpl monClient = new ClientAdminImpl();

			// Activer le servant au sein du POA et recuperer son ID
			byte[] monClientId = rootPOA.activate_object(monClient);

			// Activer le POA manager
			rootPOA.the_POAManager().activate();

			// Enregistrement dans le service de nommage

			// Construction du nom a enregistrer
			org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];

			nameToRegister[0] = new org.omg.CosNaming.NameComponent(log, "");

			// Enregistrement de l'objet CORBA dans le service de noms
			nameRoot.rebind(nameToRegister,
					rootPOA.servant_to_reference(monClient));
			logs.log("dev", log + " est enregistre dans l'annuaire.");

			String IORServant = orb.object_to_string(rootPOA.servant_to_reference(monClient));
			logs.log("debug", "L'objet possede la reference suivante :");
			logs.log("debug", IORServant);
			Thread t = new Thread(new Runnable() {
				public void run() {
					orb.run();
				}
			});
			t.start();

			//logs.clear();
			//logs.log("info", "Liste des clients disponibles");
			//logs.pagesJaunes(nameRoot, "clients");

			// Saisie du nom de l'objet (si utilisation du service de nommage)
			//logs.log("info", "Qui voulez-vous contacter ?");
			logs.log("info", "Appuyez sur une touche pour révoquer votre certificat.");
						
			idObj = in.readLine();
			
			if(monAE.revocCertif(log, pass, monCertif))
			{
				logs.log("info", "Fin de revocation du certificat avec succès");
			}
			else logs.log("info", "Fin de revocation du certificat echouée");
			

		

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
