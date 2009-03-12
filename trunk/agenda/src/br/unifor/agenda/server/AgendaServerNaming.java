
package br.unifor.agenda.server;

import rme.RmeConfigurator;
import rme.naming.RmeServiceDirectory_Impl;
import arcademis.server.AlreadyBoundException;
import br.unifor.agenda.PhoneBook;
import br.unifor.agenda.PhoneBook_Stub;

public class AgendaServerNaming {
	/*public static void main(String args[]) {
		try {
			rme.RmeConfigurator c = new rme.RmeConfigurator();
			c.configure();
			PhoneBook o = new PhoneBook();
			rme.naming.RmeNaming.bind("obj", o);
			o.activate();

			o.insert("Matheus", "Marcos Macêdo", "Aldeota");

			o.insert("Fulano", " Macêdo Marcos", "Varjota");
			
			RmeServiceDirectory_Impl nameService = new RmeServiceDirectory_Impl();

			nameService.activate();
		} catch (arcademis.ArcademisException e) {
		} catch (arcademis.concreteComponents.MalformedURLException e) {
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
	public static void main(String[] args)
	{
        try
        {
			RmeConfigurator c = new RmeConfigurator();
			c.configure();
			
			RmeServiceDirectory_Impl nameService = new RmeServiceDirectory_Impl();

			nameService.activate();
        }
        catch (Exception e)
        {
			e.printStackTrace();
        }
	}
}

