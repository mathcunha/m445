package br.unifor.mia.cd.middleware.rme2.extras;

import arcademis.Invoker;
import arcademis.InvokerDecorator;
import arcademis.MultiReference;
import arcademis.NetworkException;
import arcademis.RemoteCall;
import arcademis.RemoteReference;
import arcademis.Stream;

/**
 * This invoker attempts to perform the call in a list of servers. It try each
 * of them, until the call can be performed successfully.
 */
public class TryInvoker extends InvokerDecorator {

	public TryInvoker(Invoker invoker) {
		super(invoker);
	}

	/**
	 * Performs a remote method invocation. The server will be choosen
	 */
	public Stream invoke(RemoteCall remoteCall, MultiReference mRef) throws NetworkException {
		// chooses server and then:
		for(int i = mRef.getNumberOfReferences(); i > 0; i--) {
			RemoteReference ref = mRef.nextReference();
			try {
				return super.invoker.invoke(remoteCall, ref);
			} catch (NetworkException e) {}
		}
		throw new NetworkException("There is not an achievable server in the list of references.");
	}
}