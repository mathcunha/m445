package arcademis;

/**
 * In Arcademis, data transportation is implemented by two classes: <CODE>Channel</CODE>
 * and <CODE>ConnectionServer</CODE>. Channels are responsible for transmitting byte
 * sequences between clients and servers, whereas the function of connection servers
 * is to receive connection requests and to create channels. The framework does not 
 * assume the use of any specific transport protocol, and possible implementations 
 * can be based on UDP, TCP, HTTP, SOAP etc. In order to add further functionality
 * to a channel, Arcademis uses the decorator design pattern, which provides a way to 
 * modify the behavior of individual objects without creating new derived 
 * classes. A channel decorator is an object that extends that class and, in
 * addition to this, has an attribute of the <CODE>Channel</CODE> type. As a subclass of
 * channel, the decorator can overwrite some of its methods in order
 * to aggregate further capabilities to them.
 *<P>
 * Examples of extra capabilities that can be aggregated to channels by means of
 * decorators include mechanisms for compressing and cryptographing messages,
 * check points and error correcting code for handling transmission failures and
 * buffers to improve performance and to allow undo operations.
 */
public interface Channel {
	/**
	 * Establishes a connection with the server that resides on the given address.
	 * @param epid the location of the target server.
	 * @throws NetworkException if an error takes place in the network layer.
	 */
	public void connect(Epid epid) throws NetworkException;

	/**
	 * Sends a byte sequence to the other side of the connection.
	 * @param m the sequence of bytes to be sent.
	 * @throws NetworkException if an error takes place in the network layer.
	 */
	public void send(byte[] m) throws NetworkException;

	/**
	 * Receives a byte sequence from the other side of the connection.
	 * @return a sequence of bytes that has been received.
	 * @throws NetworkException if an error takes place in the network layer.
	 */
	public byte[] recv() throws NetworkException;

	/**
	 * Finalizes the connection held by this channel.
	 * @throws NetworkException if an error takes place in the network layer.
	 */
	public void close() throws NetworkException;

	/**
	 * Informs the address to which this channel is currently connected.
	 * @return an object of the <CODE>Epid</CODE> type describing the address of
	 * the other side of the connection.
	 */
	public Epid getLocalEpid();

	/**
	 * Determines the time out of this connection. The time out specifies a
	 * minimum time of tolerance that the channel waits for its counterpart. If no
	 * answer is obtained in this time interval, an exception is thrown.
	 * @param t the new timeout, in milliseconds.
	 */
	public void setConnectionTimeout(int t);

	/**
	 * Informs the time out of the channel. A value of zero specifies an infinite
	 * time out.
	 * @return an integer value that represents the time out in milliseconds.
	 */
	public int getConnectionTimeout();
}