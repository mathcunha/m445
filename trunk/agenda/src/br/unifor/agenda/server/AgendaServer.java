package br.unifor.agenda.server;

import rme.RmeConfigurator;
import rme.naming.RmeServiceDirectory_Impl;
import arcademis.ArcademisException;
import arcademis.ReconfigurationException;
import arcademis.concreteComponents.MalformedURLException;
import arcademis.server.AlreadyBoundException;
import br.unifor.agenda.PhoneBook;
import br.unifor.agenda.PhoneBook_Stub;
import br.unifor.mobile.MobileAction;

public class AgendaServer {

	public static void main(String[] args) {
		RmeConfigurator c = new RmeConfigurator();
		try {
			c.configure();

			if (args.length == 0) {

				PhoneBook o = new PhoneBook();
				o.insert("Matheus", "Marcos Macêdo", "Aldeota");
				o.insert("Fulano", " Macêdo Marcos", "Varjota");
				rme.naming.RmeNaming.bind("obj", o);
				o.activate();

			} else {
				MobileAction.execute("127.0.0.1/obj");
			}

		} catch (ReconfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArcademisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
