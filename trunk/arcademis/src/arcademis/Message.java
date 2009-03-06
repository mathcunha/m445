package arcademis;

/**
 * This interface represents a message to be sent through a communication channel.
 * In order to implement communication protocols, it is possible to create
 * hierarchies of messages, so that the polimorfic characteristics of Java can be
 * used to determine how a particular message should be processed. Every message
 * in essence represents a sequence of bytes that will be transmited along a
 * channel. This interface extends the <CODE>Marshalable</CODE> interface. The
 * implementation of the marshal method determines how the message will be
 * transformed into a sequence of bytes ready to be sent across the network. The
 * unmarshal method determines how a message can be readen from a raw sequence
 * of bytes.
 * <P>
 * This interface is based on the command design pattern. Such
 * pattern is mainly used in the implementation of the protocol state machine,
 * because it allows a message to define the whole set of actions should be
 * performed when it is received by one service handler.
 */
public interface Message extends Marshalable {

	/**
	 * This method determines the set of actions should be performed by the
	 * message after being received by the proper service handler. Any message may
	 * be understood as a command that causes a transition in the protocol state
	 * machine.
	 * @param s Every message is received by a service handler, that must provide
	 * to it the necessary resource for its actions being processed.
	 */
	public void execute(ProtocolHandler s);

}
