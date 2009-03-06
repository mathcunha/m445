package arcademis;

/**
 * This interface defines the concept of multi-reference, that is, a reference
 * to several remote objects, instead of just one. Such entity is meaningful
 * only in a over-provisioned environment, in which the clients could take
 * benefit of the several available servers.
 */
public interface MultiReference extends Marshalable {

	/**
	 * Adds a new reference to the set of remote references.
	 * @param r the remote reference that is been inserted into the list.
	 */
	public void attach(RemoteReference r);

	/**
	 * This method returns a reference to one of the remote references stored
	 * in the list. The following code can be used to get a reference to all
	 * elements stored into the list:
	 * <PRE>
	 * int i = mr.getNumberOfReferences();
	 * for(int j = 0; j < i; j++)
	 *     mr.nextReference()
	 * </PRE>
	 * @return an object of the <CODE>RemoreReference</CODE> type.
	 */
	public RemoteReference nextReference();

	/**
	 * Informs the number of elements stored in the reference list.
	 * @return an integer number representing the quantity of remote references
	 * stored in the list.
	 */
	public int getNumberOfReferences();

	/**
	 * Removes the remote reference from the set of references.
	 * @param r the reference to be removed.
	 * @return true if the reference has been remove successfully and false
	 * otherwise.
	 */
	public boolean removeReference(RemoteReference r);
}
