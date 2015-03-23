package corbak;

import java.security.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContext;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class AEImpl extends AEPOA{

	public static AC monAC;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("##AE##");
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
    AEImpl monAE = new AEImpl();
    

    // Activer le servant au sein du POA et recuperer son ID
    byte[] monAEId = rootPOA.activate_object(monAE);

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
    nameRoot.rebind(nameToRegister,rootPOA.servant_to_reference(monAE));
    System.out.println("==> Nom '"+ nomAE + "' est enregistre dans le service de noms.");

    String IORServant = orb.object_to_string(rootPOA.servant_to_reference(monAE));
    System.out.println("L'objet possede la reference suivante :");
    System.out.println(IORServant);
    
    System.out.println("Attribution AC ... ("+nomAE+")");
    String AC = "AC1";
    switch(nomAE){
    case "AE1": AC= "AC1";
    break;
    case "AE2": AC= "AC2";
    break;
    case "AE3": AC= "AC3";
    break;
    case "AE4": AC= "AC4";
    break;
    default : AC="AC1";
    break; 
    }
    
    System.out.println("demande de ratachement à l'"+AC+"...");
    
 // Construction du nom a rechercher
    org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
     nameToFind[0] = new org.omg.CosNaming.NameComponent(AC,"");

    // Recherche aupres du naming service
    org.omg.CORBA.Object distantAC = nameRoot.resolve(nameToFind);
    System.out.println("Objet '" + AC + "' trouve aupres du service de noms. IOR de l'objet :");
    System.out.println(orb.object_to_string(distantAC));
    monAC = ACHelper.narrow(distantAC);
    
    Thread t = new Thread(new Runnable(){
		public void run(){
			orb.run();
		}
	});
	t.start();
        
	}catch (Exception e) {
		e.printStackTrace();
	}


}
	
	public boolean authentification (String login, String password){
		
		System.out.println(login+" demande à s'authentifier : OK");
		return true;
		
	}

	@Override
	public void revocCertif(String login, String password, Certificat certif)
			throws droitsInsufisants, certificatInvalide {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Certificat genererCertificat(String PubKey) {
		Certificat cert=null;
		try{
			
			System.out.println("#DEBUG1");
			
		String hash = Integer.toString(PubKey.hashCode());
		System.out.println("#DEBUG1 hash =>"+hash);
        Signature sig = new Signature(hash);
        System.out.println("#DEBUG1 Sig.hash =>"+sig.hash);
        Date expir = new Date((short)2099,(short)1,(short)1,(short)1,(short)1,(short)1);
        System.out.println("#DEBUG2");
        cert = monAC.generationCertificat(PubKey, expir, monAC,sig);
        System.out.println("#DEBUG3");
        
		}catch(Exception e){
			e.printStackTrace();
		}
		return cert;
		
	}
	
}


