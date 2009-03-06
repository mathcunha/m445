package br.unifor.mia.cd.middleware.rme2;

public interface RmeConstants {

	/**
	 * The port where normally the name server accepts connections
	 */
	public final int REGISTRY_PORT = 1101;

	/**
	 * This constant determines the time that a connection is kept alive by the
	 * request receiver responsible for handling the messages sent across it.
	 */
	public static final int TOLERANCE_TIME = 2000; // 2 seconds

	// This sequence of constants are related to the serialization protocol
	// adopted in RME.

	/**
	 * The constant NULL_REF represents the null references inside a byte stream.
	 */
	public final byte NULL_REF = (byte)0x70;

	/**
	 * The constant MARSHALIZABLE represents the marshalized objects inside a byte
	 * stream. A marshalizable object must provide the methods necessary to
	 * convert the object in a sequence of bytes and to fill the attributes of the
	 * object with a sequence of bytes.
	 */
	public final byte MARSHALABLE = (byte)0x73;

	/**
	 * The constant STRING represents the String objects marshalized in a byte
	 * stream.
	 */
	public final byte STRING = (byte)0x74;

	/**
	 * The constant MARSHALIZABLE_ARRAY represents an array of marshalizable
	 * objects in a byte stream.
	 */
	public final byte MARSHALABLE_ARRAY = (byte)0x75;

	/**
	 * Rme permits to report to the client application two different types of
	 * exceptions. The first type is formed by the group of marshalable
	 * exceptions, that is, the exceptions whose classes implements the
	 * MARSHALABLE interface. Exceptions that do not implement this interface
	 * can also be serialized, but just its type and message are transformed
	 * in a sequence of bytes.
	 */
	public final byte MARSHALABLE_EXCEPTION = (byte)0x7B;
	public final byte NON_MARSHALABLE_EXCEPTION = (byte)0x7C;

	/**
	 * The constant STRING_ARRAY represents an array of Strings in a byte stream.
	 */
	public final byte STRING_ARRAY = (byte)0x80;

	/**
	 * The constant BOOLEAN_ARRAY represents an array of booleans in a byte
	 * stream.
	 */
	public final byte BOOLEAN_ARRAY = (byte)0x81;

	/**
	 * The constant BYTE_ARRAY represents an array of bytes in a byte stream.
	 */
	public final byte BYTE_ARRAY = (byte)0x82;

	/**
	 * The constant CHAR_ARRAY represents an array of char in a byte stream.
	 */
	public final byte CHAR_ARRAY = (byte)0x83;

	/**
	 * The constant SHORT_ARRAY represents an array of short in a byte stream.
	 */
	public final byte SHORT_ARRAY = (byte)0x84;

	/**
	 * The constant INT_ARRAY represents an array of integer values in a
	 * byte stream.
	 */
	public final byte INT_ARRAY = (byte)0x85;

	/**
	 * The constant LONG_ARRAY represents an array of long values in a
	 * byte stream.
	 */
	public final byte LONG_ARRAY = (byte)0x86;

	// This sequence of constants defines the RMEP protocol adopted by RME

	/**
	 * The constant CALL_MESSAGE indicates a message that contains a method
	 * request, in the RMEP protocol.
	 */
	public final byte CALL_MESSAGE = (byte)0x50;

	/**
	 * The constant RETURN_MESSAGE, in the RMEP protocol, denotates a message that
	 * contains the value produced by a remote method.
	 */
	public final byte RETURN_MESSAGE = (byte)0x51;

	/**
	 * The constant PING_MESSAGE, in the RMEP protocol, denotates a message that
	 * just contains a ping request.
	 */
	public final byte PING_MESSAGE = (byte)0x52;

	/**
	 * The constant PING_ACKNOWLEDGE, in the RMEP protocol, denotates a message that
	 * is used to acknowledge a ping request.
	 */
	public final byte PING_ACKNOWLEDGE = (byte)0x53;

	public final byte INQ_MESSAGE = (byte)0x54;

	// This sequence of constants are related to the invocation strategies

	/**
	 * This constant characterizes the default invocation strategy that is
	 * implemented by the middleware system.
	 */
	public final byte DEFAULT_INVOCATION = (byte)0x0;

	/**
	 * Defines a two way strategy. According to it, the call originates a
	 * return value that is returned to the application, that stays blocked
	 * during the process of call processing.
	 */
	public final byte TWO_WAY = (byte)0x1;

	/**
	 * Defines a one way strategy of invocation. This strategy does not
	 * originate any return value, and the application does not wait for a
	 * replay from the server; therefore, it does not stay blocked during the
	 * process of remote method invocation.
	 */
	public final byte ONE_WAY = (byte)0x2;

	/**
	 * Adds a cache to the stub, so that it can record the result of some
	 * calls. That allows the stub to reduce the number of access to the
	 * network.
	 */
	public final byte CACHED_CALL = (byte)0x4;

	/**
	 * This constant defines a pattern of service combination according to each
	 * the invoker attempts to connect with a primary server. If this server is
	 * not available, the invoker attempts successive servers until the call can
	 * be acomplished.
	 */
	public final byte TRY_INVOKER = (byte)0x8;

	/**
	 * This constant characterizes the an implementation of the connector factory
	 * that reuses old connections whenever possible.
	 */
	public final byte REUSE_CHANNEL = (byte)0x1;
}