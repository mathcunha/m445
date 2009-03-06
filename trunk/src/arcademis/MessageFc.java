package arcademis;

/**
 * This interface determines the <CODE>Message</CODE> factory. Whenever necessary to
 * modify the implementation of messages, it is sufficient to change the factory's
 * implementation so it pass to return the proper message.
 */
public interface MessageFc {

	/**
	 * Creates a default message
	 * @return an object of the <CODE>Message</CODE> type.
	 */
	public Message createMessage ();

	/**
	 * Creates a specific message
	 * @param objType defines the type of the message that will be created.
	 * @return an object of the <CODE>Message</CODE> type.
	 */
	public Message createMessage (int objType);
	
}
