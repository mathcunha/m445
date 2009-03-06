package arcademis.server;

import arcademis.*;

/**
 * The acceptor, together with the connector, constitutes the
 * acceptor-connector design pattern. This pattern allows to employ in the
 * channel processing elements (the service handlers) other than the ones used
 * it the process of connection establishment. The acceptor's function is to
 * wait for requests asking for connection set up from the connector and to
 * rise a communication channel with it.
 */
public abstract class Acceptor implements EventHandler {

	/**
	 * Every acceptor uses a connection server for receiving connections.
	 */
	protected ConnectionServer connectionServer = null;

	/**
	 * Because the <CODE>acceptor</CODE> class implements the
	 * <CODE>EventHandler</CODE> interface, it has to define this method, that
	 * hear has an empty body.
	 * @param e the event that will be handled.
	 */
	public void handleEvent(Event e) {}

	/**
	 * This method bound the acceptor to the address passed as a parameter.
	 * After this method being called, it will be possible to receive connections
	 * in the specified end point location.
	 * @param epid the address in which connections will be received.
	 */
	public Acceptor(Epid epid) {
		this.connectionServer = OrbAccessor.getConnectionServer(epid);
	}

	/**
	 * This method waits in the address passed by the constructor method until an
	 * incoming connection is perceived. After the channel has been set up, it is
	 * passed to the service handler, that will be responsible for processing it.
	 * @param h the service handler that will be used to process the channel.
	 * @throws NetworkException in the case an error takes place in the network
	 * layer.
	 */
	public abstract void accept(ServiceHandler h) throws NetworkException;
}
