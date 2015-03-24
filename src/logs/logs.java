package logs;

import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

public class logs {
	public static String verbLvl = "debug"; // prod - dev - debug

	public static void pagesJaunes(NamingContext nc, String type) {
		try {
			final int batchSize = 1000;
			BindingListHolder bList = new BindingListHolder();
			BindingIteratorHolder bIterator = new BindingIteratorHolder();

			nc.list(batchSize, bList, bIterator);

			for (int i = 0; i < bList.value.length; i++) {
				NameComponent[] name = { bList.value[i].binding_name[0] };
				if (bList.value[i].binding_type == BindingType.ncontext) {
					NamingContext context = NamingContextHelper.narrow(nc
							.resolve(name));
					pagesJaunes(context, name[0].id + ".");
				} else {
					if (type == "clients") {
						if (!name[0].id.matches("A\\p{Upper}\\p{Digit}")) {
							System.out.print("\t" + name[0].id);
						}
					} else if (type == "AE")
						if (name[0].id.matches("AE\\p{Digit}")) {
							System.out.print("\t" + name[0].id);
						}
				}
			}
			System.out.println();

		} catch (Exception e) {
			System.out.println("ERROR : " + e);
		}
	}

	public static void log(String msgLvl, String message) {

		switch (verbLvl) {
		case "prod":
			if (msgLvl.equals("info"))
				System.out.println("[" + msgLvl + "] : " + message);
			break;

		case "dev":
			if (msgLvl.equals("info") | msgLvl.equals("dev"))
				System.out.println("[" + msgLvl + "] : " + message);
			break;

		case "debug":
			if (msgLvl.equals("info") | msgLvl.equals("dev")
					| msgLvl.equals("debug"))
				System.out.println("[" + msgLvl + "] : " + message);
			break;

		default:
			if (msgLvl.equals("info"))
				System.out.println("[" + msgLvl + "] : " + message);
			break;
		}

	}

	public static void clear() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
