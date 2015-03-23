package corbak;

import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class ClientImpl extends ClientPOA{

public static AE monAE;
public static AV monAV;
public static Client monCorrespondant;
public static Certificat monCertif;
public void envoyerMessage (Message msg, Certificat certif){
	
	System.out.println(msg.text);
}


	public static void main(String args[]) {

		try {

		// Intialisation de l'orb
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);




        // Saisie du nom de l'objet (si utilisation du service de nommage)
        System.out.println("Quel objet AE Corba voulez-vous contacter ?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String idObj = in.readLine();

        // Recuperation du naming service
        org.omg.CosNaming.NamingContext nameRoot =
        		org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

        // Construction du nom a rechercher
        org.omg.CosNaming.NameComponent[] nameToFindAE = new org.omg.CosNaming.NameComponent[1];
         nameToFindAE[0] = new org.omg.CosNaming.NameComponent(idObj,"");
         
         org.omg.CosNaming.NameComponent[] nameToFindAV = new org.omg.CosNaming.NameComponent[1];
         nameToFindAV[0] = new org.omg.CosNaming.NameComponent(idObj.replaceAll("AE", "AV"),"");

        // Recherche aupres du naming service AE
        org.omg.CORBA.Object distantAE = nameRoot.resolve(nameToFindAE);
        System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
        System.out.println(orb.object_to_string(distantAE));
        // Recherche aupres du naming service AV
        org.omg.CORBA.Object distantAV = nameRoot.resolve(nameToFindAV);
        System.out.println("Objet '" + idObj.replaceAll("AE", "AV") + "' trouve aupres du service de noms. IOR de l'objet :");
        System.out.println(orb.object_to_string(distantAV));

        // Utilisation directe de l'IOR (SAUF utilisation du service de nommage)
       // org.omg.CORBA.Object distantEuro = orb.string_to_object("IOR:000000000000001b49444c3a436f6e766572746973736575722f4575726f3a312e30000000000001000000000000007c000102000000000d3137322e31362e39362e35340000cb630000001c00564201000000022f0020200000000400000000000002925225d3ea00000003564953030000000500070801ff000000000000000000000800000000564953000000000100000018000000000001000100000001050100010001010900000000");
        // Casting de l'objet CORBA au type convertisseur euro
        monAE = AEHelper.narrow(distantAE);
        monAV = AVHelper.narrow(distantAV);
        System.out.println("Login Client ?");
        String log = in.readLine();
        System.out.println("Password Client ?");
        String pass = in.readLine();
        short returned = monAE.authentification(log,pass);
        if (returned == 1) System.out.println("#Login successful");
        monCertif = monAE.genererCertificat("payetaclef"); 
        System.out.println("#Certificat généré et récupéré.");
       
    	// Gestion du POA
    	    //****************
    	    // Recuperation du POA
    	    POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

    	    // Creation du servant
    	    //*********************
    	    ClientImpl monClient = new ClientImpl();

    	    // Activer le servant au sein du POA et recuperer son ID
    	    byte[] monClientId = rootPOA.activate_object(monClient);

    	    // Activer le POA manager
    	    rootPOA.the_POAManager().activate();
    	   
    	// Enregistrement dans le service de nommage
    	    

    	    // Construction du nom a enregistrer
    	    org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
    	    System.out.println("Sous quel nom voulez-vous enregistrer votre client Corba ?");
    	     in = new BufferedReader(new InputStreamReader(System.in));
    	    String nomObj = in.readLine();
    	    nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomObj,"");

    	    // Enregistrement de l'objet CORBA dans le service de noms
    	    nameRoot.rebind(nameToRegister,rootPOA.servant_to_reference(monClient));
    	    System.out.println("==> Nom '"+ nomObj + "' est enregistre dans le service de noms.");

    	    String IORServant = orb.object_to_string(rootPOA.servant_to_reference(monClient));
    	    System.out.println("L'objet possede la reference suivante :");
    	    System.out.println(IORServant);
    	    Thread t = new Thread(new Runnable(){
				public void run(){
					orb.run();
				}
			});
			t.start();
    	  
    	// Saisie du nom de l'objet (si utilisation du service de nommage)
           System.out.println("Quel client Corba voulez-vous contacter ?");
           
            idObj = in.readLine();

           // Recuperation du naming service
           nameRoot = org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

           // Construction du nom a rechercher
           NameComponent[] nameToFindClient = new org.omg.CosNaming.NameComponent[1];
            nameToFindClient[0] = new org.omg.CosNaming.NameComponent(idObj,"");

           // Recherche aupres du naming service
           org.omg.CORBA.Object distantClient = nameRoot.resolve(nameToFindClient);
           System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
           System.out.println(orb.object_to_string(distantClient));

           // Utilisation directe de l'IOR (SAUF utilisation du service de nommage)
          // org.omg.CORBA.Object distantEuro = orb.string_to_object("IOR:000000000000001b49444c3a436f6e766572746973736575722f4575726f3a312e30000000000001000000000000007c000102000000000d3137322e31362e39362e35340000cb630000001c00564201000000022f0020200000000400000000000002925225d3ea00000003564953030000000500070801ff000000000000000000000800000000564953000000000100000018000000000001000100000001050100010001010900000000");
           // Casting de l'objet CORBA au type convertisseur euro
           monCorrespondant = ClientHelper.narrow(distantClient);
           while(true) {
           System.out.println("message ?");
           String message = in.readLine();
           Signature sign = new Signature("hash");
           Message msg = new Message(sign,message,false) ;
           monCorrespondant.envoyer(monCertif);
           monCorrespondant.envoyerMessage(msg, monCertif);
           
       }
        
     
	}
	catch (Exception e) {
		e.printStackTrace();
	}

}


	@Override
	public void envoyer(Certificat certif) {
		// TODO Auto-generated method stub
		
	}
	

}
