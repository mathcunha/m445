package br.unifor.mia.cd.middleware.rme2.extras.server;

import br.unifor.mia.cd.middleware.rme2.RmeRemoteCall;
import arcademis.RemoteCall;
import arcademis.Stream;
import arcademis.server.Dispatcher;
import arcademis.server.DispatcherDecorator;

/**
 * This is a simple decorator that just prints the incoming call in the
 * standard output.
 */
public class LogDispatcher extends DispatcherDecorator {

	/**
	 * Constructor method of the log dispatcher decorator. It associates the
	 * decorated dispatcher with this one.
	 * @param dispatcher the decorated dispatcher.
	 */
	public LogDispatcher(Dispatcher dispatcher) {
		super(dispatcher);
	}

	/**
	 * The method verifies if this is a remote call of the
	 * <CODE>RmeCall</CODE> type, and if it is so, it print some relevant data
	 * in the standard output.
	 * @param c the remote call to be processed.
	 * @return a stream holding the return value of the call.
	 */
	public Stream dispatch(RemoteCall c) {
		if(c instanceof RmeRemoteCall) {
			RmeRemoteCall r = (RmeRemoteCall)c;
			System.out.println("-----------------------------------------------------------");
			System.out.println("Call    = " + r.getCallIdentifier());
			System.out.println("Origin  = " + r.getReturnAddress());
			System.out.println("Destiny = " + r.getTargetObjectIdentifier());
		}
		return super.dispatcher.dispatch(c);
	}
}