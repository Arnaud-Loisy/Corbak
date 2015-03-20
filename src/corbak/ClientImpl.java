package corbak;

import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientImpl extends ClientPOA{

public static AE monAE;
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
        org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
         nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj,"");

        // Recherche aupres du naming service
        org.omg.CORBA.Object distantAE = nameRoot.resolve(nameToFind);
        System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
        System.out.println(orb.object_to_string(distantAE));

        // Utilisation directe de l'IOR (SAUF utilisation du service de nommage)
       // org.omg.CORBA.Object distantEuro = orb.string_to_object("IOR:000000000000001b49444c3a436f6e766572746973736575722f4575726f3a312e30000000000001000000000000007c000102000000000d3137322e31362e39362e35340000cb630000001c00564201000000022f0020200000000400000000000002925225d3ea00000003564953030000000500070801ff000000000000000000000800000000564953000000000100000018000000000001000100000001050100010001010900000000");
        // Casting de l'objet CORBA au type convertisseur euro
        monAE = AEHelper.narrow(distantAE);
        System.out.println("Login Client ?");
        String log = in.readLine();
        System.out.println("Password Client ?");
        String pass = in.readLine();
        short returned = monAE.authentification(log,pass);
        if (returned == 1) System.out.println("#Login successful");
        monCertif = monAE.genererCertificat("$1");
        System.out.println("#Certificat g�n�r� et r�cup�r�.");
       while(true) {
    	   
    	// Saisie du nom de l'objet (si utilisation du service de nommage)
           System.out.println("Quel objet client Corba voulez-vous contacter ?");
           
            idObj = in.readLine();

           // Recuperation du naming service
           nameRoot = org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

           // Construction du nom a rechercher
           nameToFind = new org.omg.CosNaming.NameComponent[1];
            nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj,"");

           // Recherche aupres du naming service
           org.omg.CORBA.Object distantClient = nameRoot.resolve(nameToFind);
           System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
           System.out.println(orb.object_to_string(distantClient));

           // Utilisation directe de l'IOR (SAUF utilisation du service de nommage)
          // org.omg.CORBA.Object distantEuro = orb.string_to_object("IOR:000000000000001b49444c3a436f6e766572746973736575722f4575726f3a312e30000000000001000000000000007c000102000000000d3137322e31362e39362e35340000cb630000001c00564201000000022f0020200000000400000000000002925225d3ea00000003564953030000000500070801ff000000000000000000000800000000564953000000000100000018000000000001000100000001050100010001010900000000");
           // Casting de l'objet CORBA au type convertisseur euro
           monCorrespondant = ClientHelper.narrow(distantClient);
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
