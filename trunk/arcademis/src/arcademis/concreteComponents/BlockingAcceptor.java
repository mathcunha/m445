package arcademis.concreteComponents;

import arcademis.*;
import arcademis.server.*;

/**
 * This acceptor listen for connections in the same thread it is created; therefore, it
 * blocks the application.
 */
public class BlockingAcceptor extends Acceptor {

	/**
	 * This method bound the acceptor to the address passed as a parameter.
	 * After this method being called, it will be possible to receive connections
	 * in the specified end point location.
	 * @param epid the address in which connections will be received.
	 */
	public BlockingAcceptor(Epid epid) {
		super(epid);
	}

	/**
	 * This method waits in the address passed by the constructor method until an
	 * incoming connection is received. After the channel has been set up, it is
	 * passed to the service handler, that will be responsible for processing it.
	 * @param sh the service handler that will be used to process the channel.
	 * @throws NetworkException in the case an error takes place in the network
	 * layer.
	 */
	public void accept(ServiceHandler sh) throws NetworkException {
		super.connectionServer.accept();
		Channel ch = super.connectionServer.getChannel();
		sh.open(ch);
	}
}
