package br.unifor.mobile;

import rme.RmeConfigurator;
import rme.RmeStreamFc;
import rme.naming.RmeNaming;
import arcademis.Stream;
import arcademis.Stub;

public class MobileAction {

	public static void execute(String uri) {
		try {
			MobileStub stub = (MobileStub) RmeNaming.lookup(uri);

			Class mobileRemoteObjectClass = Class.forName(stub.getClass()
					.getName().replaceFirst("_Stub", ""));
			MobileRemoteObject lMobileRemoteObject = (MobileRemoteObject) mobileRemoteObjectClass
					.newInstance();

			Stream lStream = stub.getRemoteState();

			Stream stream = (new RmeStreamFc()).createStream();
			stream.write("deactivate");
			stream.write(0);

			stub.invoke(stream, 1, '?', 0);

			RmeNaming.unbind(uri);

			lMobileRemoteObject.restoreState(lStream);

			RmeNaming.bind(uri, lMobileRemoteObject);
			lMobileRemoteObject.activate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
