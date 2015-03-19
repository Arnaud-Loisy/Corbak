package corbak;

//import EuroImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContext;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class AEimpl extends AEPOA{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("AE1");
try {
    // Intialisation de l'ORB
    //************************
    final org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

    // Gestion du POA
    //****************
    // Recuperation du POA
    POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

    // Creation du servant
    //*********************
    AEimpl monAE = new AEimpl();

    // Activer le servant au sein du POA et recuperer son ID
    byte[] monEuroId = rootPOA.activate_object(monAE);

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
    String nomObj = in.readLine();
    nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomObj,"");

    // Enregistrement de l'objet CORBA dans le service de noms
    nameRoot.rebind(nameToRegister,rootPOA.servant_to_reference(monAE));
    System.out.println("==> Nom '"+ nomObj + "' est enregistre dans le service de noms.");

    String IORServant = orb.object_to_string(rootPOA.servant_to_reference(monAE));
    System.out.println("L'objet possede la reference suivante :");
    System.out.println(IORServant);

    //DEBUG
        nomObj = in.readLine();
        
	}catch (Exception e) {
		e.printStackTrace();
	}


}
	
	public short authentification (String login, String password){
		
		
		return 1;
		
	}

	@Override
	public void revocCertif(String login, String password, Certificat certif)
			throws droitsInsufisants, certificatInvalide {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Certificat genererCertificat(String PubKey) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


