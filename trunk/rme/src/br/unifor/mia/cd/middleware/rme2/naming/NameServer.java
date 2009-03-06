package br.unifor.mia.cd.middleware.rme2.naming;

import br.unifor.mia.cd.middleware.rme2.RmeConfigurator;
import br.unifor.mia.cd.middleware.rme2.RmeConstants;

public class NameServer implements RmeConstants {
	public static final int QTD = 10;

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
