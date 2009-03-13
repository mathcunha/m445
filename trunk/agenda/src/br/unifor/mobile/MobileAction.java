package br.unifor.mobile;

import rme.RmeConfigurator;
import rme.naming.RmeNaming;
import arcademis.Stream;
import arcademis.Stub;

public class MobileAction {
	public static void execute(String uri) {
		try {			
			MobileStub stub = (MobileStub) RmeNaming.lookup(uri);
			
			Class mobileRemoteObjectClass = Class.forName(stub.getClass().getName().replaceFirst("_Stub", ""));
			MobileRemoteObject lMobileRemoteObject = (MobileRemoteObject)mobileRemoteObjectClass.newInstance();
			
			Stream lStream = stub.getRemoteState();
			
			RmeNaming.unbind(uri);
			
			
			lMobileRemoteObject.restoreState(lStream);
			lMobileRemoteObject.activate();
			
			RmeNaming.bind(uri, lMobileRemoteObject);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
