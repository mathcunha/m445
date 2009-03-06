package arcademis.concreteComponents;

import arcademis.*;

/**
 * This connector is responsible for synchronously carring on a connection
 * with the server that listen at the specified address point. The application
 * remains blocked during all the process of connection establishment.
 */
public class SynchronousConnector extends Connector {

	/**
	 * This method establishes a connection with a specified acceptor and passes
	 * the just built channel to the service handler. This process is performed
	 * synchronously, that is, the application remains blocked during all the 
	 * time in which the channel is being built.
	 * @param h the service handler that will be used to process the channel after
	 * it has been generated by the connector.
	 * @param epid the location of the acceptor that will be contacted in order to
	 * generate a channel with this connector.
	 * @throws NetworkException in the case some error takes place in the
	 * transport layer.
	 */
	public void connect(ServiceHandler h, Epid epid) throws NetworkException {
		// 1) create a channel
		Channel ch = OrbAccessor.getChannel();
		// 2) stablish a connection with the given channel
		ch.connect(epid);
		// 3) pass the channel to the service handler
		// 4) and open the service handler
		h.open(ch);
	}

}
