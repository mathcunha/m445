package arcademis.server;

import arcademis.*;

/**
 * This class implements the request receiver component of the request-response
 * pattern that arcademis uses to allows the communication between stubs and
 * skeletons. The function of a request receiver is to receive, in the server
 * side of a distributed application, a message containing the description of
 * a remote method invocation.
 */
public class RequestReceiver extends ProtocolHandler {

	protected Dispatcher dispatcher = null;

	/**
	 * This method defines the dispatcher associated with this request receiver.
	 * The request receiver will pass to this dispather all the method request it
	 * receives.
	 * @param dispatcher the dispatcher that will be bound to this request
	 * receiver.
	 */
	public void setDispatcher(Dispatcher dispatcher){
		this.dispatcher = dispatcher;
	}


	/**
	 * Returns a reference to the dispatcher.
	 * @return an object of the <CODE>Dispatcher</CODE> type.
	 */
	public Dispatcher getDispatcher() {
		return this.dispatcher;
	}
}