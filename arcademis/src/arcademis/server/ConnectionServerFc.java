package arcademis.server;

import arcademis.*;

/**
 * This interface determines the factory of connection servers. This factory is
 * reponsible for the creation of these components. If the connection server is going to
 * be changed, it suffices to exchange the old factory by a new one, that
 * create the new component. The other parts of the middleware usually do not
 * have to be modified.
 */
public interface ConnectionServerFc {

	/**
	 * Returns a new instance of connection server bound to the given address.
	 * @param epid the address that will be used to receive connection.
	 * @return an object of the <CODE>ConnectionServer</CODE> type.
	 */
	public ConnectionServer createConnectionServer (Epid epid);

	/**
	 * Returns a new instance of channel that is determined by the type informed
	 * as a parameter.
	 * @param objType a parameter that determines the type of connection server that is
	 * going to be produced. In a actual instance of Arcademis, this parameter
	 * could be used to discriminate a specific component among a collection of
	 * possible implementations.
	 * @param epid the address that will be used to receive connection.
	 * @return an object of the <CODE>ConnectionServer</CODE> type.
	 */
	public ConnectionServer createConnectionServer (int objType, Epid epid);
}