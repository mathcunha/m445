package arcademis;

/**
 * This interface determines the remote reference factory. Whenever necessary to
 * modify the implementation of these references, it is sufficient to change this
 * factory's implementation so it pass to return the proper implementation of
 * remote reference.
 */
public interface RemoteRefFc {

	/**
	 * Creates a default remote reference
	 * @return an object of the <CODE>RemoteReference</CODE> type.
	 */
	public RemoteReference createRemoteRef ();

	/**
	 * Creates a default multi reference
	 * @return an object of the <CODE>MultiReference</CODE> type.
	 */
	public MultiReference createMultiRef ();

	/**
	 * Creates a specific remote reference
	 * @param objType defines the type of the remote reference that will be created.
	 * @return an object of the <CODE>RemoteReference</CODE> type.
	 */
	public RemoteReference createRemoteRef (int objType);

	/**
	 * Creates a remote reference that is bound to the given address.
	 * @param epid the end point identifier that will be pointed by the new
	 * remote reference.
	 * @return an object of the <CODE>RemoteReference</CODE> type bound to the
	 * address passed as parameter.
	 */
	public RemoteReference createRemoteRef (Epid epid);

	/**
	 * Creates a remote reference that is bound to the given address and
	 * identifier.
	 * @param epid the end point identifier that will be pointed by the new
	 * remote reference.
	 * @param id the identifier of the new remote reference.
	 * @return an object of the <CODE>RemoteReference</CODE> type bound to the
	 * address passed as parameter.
	 */
	public RemoteReference createRemoteRef (Epid epid, Identifier id);
}
