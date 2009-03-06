package arcademis;

/**
 * This interface determines the factory of channels. This factory is
 * reponsible for the creation of these components. If the channel is going to
 * be changed, it suffices to exchange the old factory by a new one, that
 * create the new component. The other parts of the middleware usually do not
 * have to be modified.
 */
public interface ChannelFc {
	/**
	 * Returns a new instance of channel.
	 * @return an object of the <CODE>Channel</CODE> type.
	 */
	public Channel createChannel ();

	/**
	 * Returns a new instance of channel that is determined by the type informed
	 * as a parameter.
	 * @param objType a parameter that determines the type of channel that is
	 * going to be produced. In a actual instance of Arcademis, this parameter
	 * could be used to discriminate a specific component among a collection of
	 * possible implementations.
	 * @return an object of the <CODE>Channel</CODE> type.
	 */
	public Channel createChannel (int objType);
}
