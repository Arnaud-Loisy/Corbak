package corbak;

import java.util.ArrayList;

import org.omg.CORBA.Object;

public class AVImpl extends AVPOA{
	
	private static ArrayList<Certificat> revokCertif;

	public static void main(String[] args) {
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
