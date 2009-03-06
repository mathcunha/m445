package arcademis.server;

import arcademis.RemoteCall;
import arcademis.Stream;

/**
 * The dispatcher's decorator permits to aggregate extra functionalities into
 * the core dispatcher component. Such capabilities include, for example, log
 * generation and security policies. Besides adding more capabilities to the
 * dispatcher, this decorator also allows to change some aspects of the server
 * structure, such as the thread policy, for example.
 */
public class DispatcherDecorator extends Dispatcher {

	protected Dispatcher dispatcher = null;

	/**
	 * Constructor method of the dispatcher decorator. It associates the
	 * decorated dispatcher with this one.
	 * @param dispatcher the decorated dispatcher.
	 */
	public DispatcherDecorator(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	/**
	 * This method simply repasses to the decorated dispatcher the received
	 * parameter. In order to add further funcitonality to the dispatcher, this
	 * method can be overwritten in order to perform the extra processsing.
	 * @param c the remote call to be processed.
	 * @return a stream holding the return value of the call.
	 */
	public Stream dispatch(RemoteCall c) {
		return this.dispatcher.dispatch(c);
	}
}
