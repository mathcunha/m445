package arcademis.concreteComponents;

import arcademis.*;

/**
 * This decorator prints the size of the message being transmitted by the
 * <CODE>Channel</CODE>. It is useful for debugging.
 */
public class ReportSizeDecorator extends ChannelDecorator {

	/**
	 * Constructor method. Assign the given channel to this object.
	 * @param channel the element to be decorated.
	 */
    public  ReportSizeDecorator(Channel channel) {
        super(channel);
    }

	/**
	 * Prints the message's length before sending it.
	 * @param a the array of bytes to be sent.
	 */
    public void send(byte[] a) throws NetworkException {
    	System.out.println("Message size (send): " + a.length);
        super.channel.send(a);
    }

	/**
	 * Prints the message's length after receiving it.
	 * @return an array of bytes that represents the message.
	 */
    public byte[] recv() throws NetworkException {
		byte[] buf = super.channel.recv();
		System.out.println((buf != null) ? "Message size (recv): " + buf.length : "null message");
		return buf;
    }
}
