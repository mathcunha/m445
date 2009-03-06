package arcademis;

/**
 * This interface determines the identifier factory. Whenever necessary to
 * modify the implementation of unique identifiers used by the middleware
 * platform, it is sufficient to change the factory's implementation so it will
 * return the proper identifier.
 */
public interface IdentifierFc {

	/**
	 * Creates a default identifier
	 * @return an object of the <CODE>Identifier</CODE> type.
	 */
	public Identifier createIdentifier ();

	/**
	 * Creates a specific identifier
	 * @param objType defines the type of the identifier that will be created.
	 * @return an object of the <CODE>Identifier</CODE> type.
	 */
	public Identifier createIdentifier (int objType);

}
