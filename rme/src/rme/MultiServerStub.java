package rme;

import arcademis.Invoker;
import arcademis.NetworkException;
import arcademis.OrbAccessor;
import arcademis.RemoteReference;
import arcademis.Stream;
import arcademis.Stub;

/**
 * This stub has a list of servers that it uses in order to choose the best one
 * to perform a given remote call.
 */
public class MultiServerStub extends Stub implements RmeConstants {

	private Invoker invoker = OrbAccessor.getInvoker();

	/**
	 * This method is responsible for creating a call with the given parameters
	 * and passing it to the server side of a distributed application.
	 * @param args the arguments of the call.
	 * @param opCode the code of the operation that is being invoked. In order
	 * to allow the communication between the stub and the skeleton, any
	 * remote operation is given a particular code.
	 * @servers a <CODE>char</CODE> that describes the pattern for server
	 * invocation. This character may hold three different service combinators:
	 * sequential invocation (>), non-deterministic invocation (?) and concurrent
	 * invocation (|).
	 * @return NetworkException in case some error occurs during the invocation
	 * or remote processing of the call.
	 * @throws NetworkException if something goes wrong during the remote
	 * invocation.
	 */
	public Stream invoke(Stream args, int opCode, char serviceCombinator, int priority)
	throws NetworkException {
		// Chooses the proper invoker type
		// Just a simple pattern for chosing the server.
		RemoteReference rRef = this.mr.nextReference();
		RmeRemoteCall remoteCall = new RmeRemoteCall(rRef.getIdentifier(), args, opCode);
		return invoker.invoke(remoteCall, rRef);
	}
}
