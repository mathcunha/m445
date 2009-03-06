package arcademis.server;

import arcademis.*;

/**
 * In Arcademis, data transportation is implemented by two classes: <CODE>Channel</CODE>
 * and <CODE>ConnectionServer</CODE>. Channels are responsible for transmitting byte
 * sequences between clients and servers, whereas the function of connection servers
 * is to receive connection requests and to create channels.
 * <P>
 * Every <CODE>Acceptor</CODE> encapsulates a connection server. It appears, at first,
 * that these components have the same function, but that is not the case. The acceptor
 * determines the strategy adopted for receiving connections, for example, if a separate
 * thread is created to hold connections, if channels are reused, etc. The connection
 * server encapsulates the functionalities to concretly set up connections, for example,
 * sockets or http connections.
 */
public abstract class ConnectionServer {

	/**
	 * Every connection server receives connections on a well defined address point.
	 */
    protected Epid epid = null;

	/**
	 * Creates a connection server that receives connections on the given address.
	 * @param epid the address in which connections will be received.
	 */
    public ConnectionServer(Epid epid) {
        this.epid = epid;
    }

	/**
	 * This method waits in the address passed by the constructor method until an
	 * incoming connection is received. After the connection has been received, a
	 * channel is built with the client that is trying to connect.
	 * @throws NetworkException in the case an error takes place in the network
	 * layer.
	 */
    public abstract void accept() throws NetworkException;

	/**
	 * Returns an instance of the last established <CODE>Channel</CODE>.
	 * @return an object of the <CODE>Channel</CODE> type.
	 */
    public abstract Channel getChannel();

	/**
	 * Determines the time out of this connection. The time out specifies a
	 * minimum time of tolerance that the connection server waits for an incoming
	 * connection. If no answer is obtained on the stipulated time interval, an
	 * exception of the <CODE>NetworkException</CODE> is thrown.
	 * @param t the new timeout, in milliseconds.
	 */
    public abstract void setConnectionTimeout(int t);

	/**
	 * Informs the connection time out of this connection server.
	 * @return an integer value.
	 */
    public abstract int getConnectionTimeout();
}
