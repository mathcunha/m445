package arcademis;

/**
 * The decorator is a common design pattern whose description can be found in
 * the GoF's book. Its main advantage is to allow the construction of chains of
 * independent functionalities.
 * Decorators can be used whenever the middleware developer intends to add new
 * functionalities to the channel. Examples of such functionalities are the use
 * of buffers to improve the access to the network, correction code, log
 * generators and algorithms for criptography and compactation.
 */
public class ChannelDecorator implements Channel {

    protected Channel channel;

	/**
	 * Constructor method. Associates the given channel with this decorator. This
	 * decorator will be able to intercept all the channel's methods. Therefore,
	 * the decorator can intercept that method's parameters, to add change them
	 * somehow and to repasse them to the decorated channel.
	 * @param channel the channel to be decorated.
	 */
    public  ChannelDecorator(Channel channel) {
        this.channel = channel;
    }

	/**
	 * Establishes a connection with the server that resides on the given address.
	 * @param epid the location of the target server.
	 * @throws NetworkException if an error takes place in the network layer.
	 */
    public void connect(Epid epid) throws NetworkException {
        channel.connect(epid);
    }

	/**
	 * Sends a byte sequence to the other side of the connection.
	 * @param m the sequence of bytes to be sent.
	 * @throws NetworkException if an error takes place in the network layer.
	 */
    public void send(byte[] m) throws NetworkException {
        channel.send(m);
    }

	/**
	 * Receives a byte sequence from the other side of the connection.
	 * @return a sequence of bytes that has been received.
	 * @throws NetworkException if an error takes place in the network layer.
	 */
    public byte[] recv() throws NetworkException {
        return channel.recv();
    }

	/**
	 * Determines the time out of this connection. The time out specifies a
	 * minimum time of tolerance that the channel waits for its counterpart. If no
	 * answer is obtained in this time interval, an exception is thrown.
	 * @param t the new timeout.
	 */
    public void setConnectionTimeout(int t) {
        channel.setConnectionTimeout(t);
    }

	/**
	 * Informs the time out of the channel. A value of zero specifies an infinite
	 * time out.
	 */
    public int getConnectionTimeout() {
        return channel.getConnectionTimeout();
    }

	/**
	 * Finalizes the connection held by this channel.
	 * @throws NetworkException if an error takes place in the network layer.
	 */
    public void close() throws NetworkException {
        channel.close();
    }

	/**
	 * Informs the address to which this channel is currently connected.
	 * @return an object of the <CODE>Epid</CODE> type describing the address of
	 * the other side of the connection.
	 */
    public Epid getLocalEpid() {
        return channel.getLocalEpid();
    }
}