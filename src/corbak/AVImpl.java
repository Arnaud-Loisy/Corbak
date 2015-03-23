package corbak;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContext;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class AVImpl extends AVPOA{
	public static AC monAC;
	private static ArrayList<Certificat> revokCertif;

	public static void main(String[] args) {
		try {
		    // Intialisation de l'ORB
		    //************************
		    final org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

		    // Gestion du POA
		    //****************
		    // Recuperation du POA
		    POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

		    // Creation des servants
		    //*********************
		    AVImpl monAV = new AVImpl();
		    

		    // Activer le servant au sein du POA et recuperer son ID
		    byte[] monAVId = rootPOA.activate_object(monAV);

		    // Activer le POA manager
		    rootPOA.the_POAManager().activate();


		    // Enregistrement dans le service de nommage
		    //*******************************************
		    // Recuperation du naming service
		    NamingContext nameRoot=org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

		    // Construction du nom a enregistrer
		    org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
		    System.out.println("Sous quel nom voulez-vous enregistrer l'objet Corba ?");
		    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    String nomAE = in.readLine();
		    nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomAE,"");

		    // Enregistrement de l'objet CORBA dans le service de noms
		    nameRoot.rebind(nameToRegister,rootPOA.servant_to_reference(monAV));
		    System.out.println("==> Nom '"+ nomAE + "' est enregistre dans le service de noms.");

		    String IORServant = orb.object_to_string(rootPOA.servant_to_reference(monAV));
		    System.out.println("L'objet possede la reference suivante :");
		    System.out.println(IORServant);
		    
		    org.omg.CosNaming.NameComponent[] nameToFindAC = new org.omg.CosNaming.NameComponent[1];
	         nameToFindAC[0] = new org.omg.CosNaming.NameComponent(nomAE.replaceAll("AE", "AV"),"");

	        
	        // Recherche aupres du naming service AV
	        org.omg.CORBA.Object distantAC = nameRoot.resolve(nameToFindAC);
	        System.out.println("Objet '" + nomAE.replaceAll("AE", "AC") + "' trouve aupres du service de noms. IOR de l'objet :");
	        System.out.println(orb.object_to_string(distantAC));
		    monAC = ACHelper.narrow(distantAC);
		    
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		revokCertif = new ArrayList<Certificat>();
	}

	@Override
	public void revocCertif(Object client, Certificat certif) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verification(Certificat certificat) throws certificatInvalide {

		for(int i=0; i<revokCertif.size();i++){
			if(certificat.sign.hash.equals(revokCertif.get(i).sign.hash))
				System.out.println("Ce certificat est révoqué");
		}
		return monAC.verification(certificat.sign);
	}

}
