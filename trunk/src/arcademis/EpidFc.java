package arcademis;

/**
 * This interface determines the factory of addresses. This factory is
 * reponsible for the creation of these components. If the representation of
 * addresses is going to
 * be changed, it suffices to exchange the old factory by a new one, that
 * create the new component. The other parts of the middleware usually do not
 * have to be modified.
 */
public interface EpidFc {
	/**
	 * Returns a new instance of location.
	 * @return an object of the <CODE>Epid</CODE> type.
	 */
	public Epid createEpid ();

	/**
	 * Returns a new instance of location that is determined by the type informed
	 * as a parameter.
	 * @param objType a parameter that determines the type of address that is
	 * going to be produced. In a actual instance of Arcademis, this parameter
	 * could be used to discriminate a specific component among a collection of
	 * possible implementations.
	 * @return an object of the <CODE>Epid</CODE> type.
	 */
	public Epid createEpid (int objType);

	/**
	 * Returns a new instance of address that will be determined by the given
	 * <CODE>String</CODE>.
	 * @param str an <CODE>String</CODE> that will be used to determine the
	 * content of the new instance of location that is been created. This
	 * <CODE>String</CODE> could countain, for example, an url specifing a
	 * location, or a sequence of characters such as "host:port", for example.
	 * @return an object of the <CODE>Channel</CODE> type.
	 */
	public Epid createEpid (String str);
}