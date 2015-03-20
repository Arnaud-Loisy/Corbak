package corbak;

import java.security.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContext;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class AEImpl extends AEPOA{

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

    // Creation du servant
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
    String nomObj = in.readLine();
    nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomObj,"");

    // Enregistrement de l'objet CORBA dans le service de noms
    nameRoot.rebind(nameToRegister,rootPOA.servant_to_reference(monAE));
    System.out.println("==> Nom '"+ nomObj + "' est enregistre dans le service de noms.");

    String IORServant = orb.object_to_string(rootPOA.servant_to_reference(monAE));
    System.out.println("L'objet possede la reference suivante :");
    System.out.println(IORServant);

    // Lancement de l'ORB et mise en attente de requete
    //**************************************************
    
    
    
    Thread t = new Thread(new Runnable(){
    			public void run(){
    				orb.run();
    			}
    		});
    t.start();
    
    //DEBUG
        //nomObj = in.readLine();
        
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
		try{
			
			System.out.println("#DEBUG1");
		AC monAC;
		
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
        String idObj = "AC1";
/*
        // Recuperation du naming service
        org.omg.CosNaming.NamingContext nameRoot = org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

        // Construction du nom a rechercher
        org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
         nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj,"");

        // Recherche aupres du naming service
        org.omg.CORBA.Object distantAC = nameRoot.resolve(nameToFind);
        System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
        System.out.println(orb.object_to_string(distantAC));
*/
        // Utilisation directe de l'IOR (SAUF utilisation du service de nommage)
        org.omg.CORBA.Object distantAC = orb.string_to_object("IOR:000000000000001249444C3A636F7262616B2F41433A312E3000000000000001000000000000006400010200000000103133302E3132302E3231322E31303500C0D0000000000014004F4F01BCCE33374C010000504F41FE5F39E36900000001000000010000002400000000100204E4000000030001000F0001000100010020000101090000000100010100");
        // Casting de l'objet CORBA au type convertisseur euro
        monAC = ACHelper.narrow(distantAC);
        Signature sig = new Signature(PubKey);
        monAC.generationCertificat(PubKey, null, monAC,sig);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}


