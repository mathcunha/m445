package arcademis;

/**
 * This interface determines the factory of connectors. This factory is
 * reponsible for the creation of these components. If the connector is going
 * to be changed, it suffices to exchange the old factory by a new one, that
 * creates the new component. The other parts of the middleware usually do not
 * have to be modified.
 */
public interface ConnectorFc {
	/**
	 * Returns a new instance of connector.
	 * @return an object of the <CODE>connector</CODE> type.
	 */
	public Connector createConnector ();

	/**
	 * Returns a new instance of connector that is determined by the type informed
	 * as a parameter.
	 * @param objType a parameter that determines the type of connector that is
	 * going to be produced. In a actual instance of Arcademis, this parameter
	 * could be used to discriminate a specific component among a collection of
	 * possible implementations.
	 * @return an object of the <CODE>Connector</CODE> type.
	 */
	public Connector createConnector (int objType);
}
