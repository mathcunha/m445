package arcademis;

import arcademis.server.*;

/**
 * The ORB is the most important component of arcademis because it aggregates
 * all the factories that are used to create the other components of the
 * middleware platform.
 * The ORB is implemented as a sigleton, that is, it has just one representant
 * peer middleware instance. To assure the singleton property, it is declared
 * as a final class and do not have any public constructor.
 */
public final class ORB {

	private static boolean isOpenForReconfiguration = true;

	private static AcceptorFc         acFc = null;
	private static ActivatorFc        atFc = null;
	private static BufferFc           quFc = null;
	private static ChannelFc          chFc = null;
	private static ConnectionServerFc csFc = null;
	private static ConnectorFc        cnFc = null;
	private static DispatcherFc       dpFc = null;
	private static EpidFc             epFc = null;
	private static IdentifierFc       idFc = null;
	private static InvokerFc          ivFc = null;
	private static MessageFc          msFc = null;
	private static ProtocolFc         ptFc = null;
	private static RemoteRefFc        rrFc = null;
	private static SchedulerFc        scFc = null;
	private static ServiceHandlerFc   shFc = null;
	private static StreamFc           smFc = null;


	/**
	 * Defines the factory of acceptors
	 * @param the new factory of acceptors
	 * @throws ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setAcceptorFactory(AcceptorFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change acceptor factory dynamically");
		else {
			acFc = fc;
		}
	}

	/**
	 * Return a reference to the acceptor factory.
	 * @return a reference to the acceptor factory.
	 */
	public static AcceptorFc getAcceptorFactory() {
		return acFc;
	}


	/**
	 * Defines the factory of activators
	 * @param the new factory of activators
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setActivatorFactory(ActivatorFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change activator factory dynamically");
		else {
			atFc = fc;
		}
	}

	/**
	 * Return a reference to the activator factory.
	 * @return a reference to the activator factory.
	 */
	public static ActivatorFc getActivatorFactory() {
		return atFc;
	}


	/**
	 * Defines the factory of buffers
	 * @param the new factory of buffers
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setBufferFactory(BufferFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change buffer factory dynamically");
		else {
			quFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of buffers.
	 * @return a reference to the factory of buffers.
	 */
	public static BufferFc getBufferFactory() {
		return quFc;
	}


	/**
	 * Defines the factory of channels
	 * @param the new factory of channels
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setChannelFactory(ChannelFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change channel factory dynamically");
		else {
			chFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of channels.
	 * @return a reference to the factory of channels.
	 */
	public static ChannelFc getChannelFactory() {
		return chFc;
	}


	/**
	 * Defines the factory of connection servers
	 * @param the new factory of connection servers
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setConnectionServerFactory(ConnectionServerFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change connection server factory dynamically");
		else {
			csFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of connection servers.
	 * @return a reference to the factory of connection servers.
	 */
	public static ConnectionServerFc getConnectionServerFactory() {
		return csFc;
	}


	/**
	 * Defines the factory of connectors
	 * @param the new factory of connectors
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setConnectorFactory(ConnectorFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change connector factory dynamically");
		else {
			cnFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of connectors.
	 * @return a reference to the factory of connectors.
	 */
	public static ConnectorFc getConnectorFactory() {
		return cnFc;
	}


	/**
	 * Defines the factory of dispatchers
	 * @param the new factory of dispatchers
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setDispatcherFactory(DispatcherFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change dispatcher factory dynamically");
		else {
			dpFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of dispatchers.
	 * @return a reference to the factory of dispatchers.
	 */
	public static DispatcherFc getDispatcherFactory() {
		return dpFc;
	}


	/**
	 * Defines the factory of end point identifiers
	 * @param the new factory of end point identifiers
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setEpidFactory(EpidFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change end point id factory dynamically");
		else {
			epFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of end point identifiers.
	 * @return a reference to the factory of end point identifiers.
	 */
	public static EpidFc getEpidFactory() {
		return epFc;
	}


	/**
	 * Defines the factory of identifiers. These components are used in Arcademis to
	 * unically discriminate remote objects and remote calls.
	 * @param the new factory of identifiers.
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setIdentifierFactory(IdentifierFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change identifier factory dynamically");
		else {
			idFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of identifiers.
	 * @return a reference to the factory of identifiers.
	 */
	public static IdentifierFc getIdentifierFactory() {
		return idFc;
	}


	/**
	 * Defines the factory of invokers
	 * @param the new factory of invokers
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setInvokerFactory(InvokerFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change invoker factory dynamically");
		else {
			ivFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of invokers.
	 * @return a reference to the factory of invokers.
	 */
	public static InvokerFc getInvokerFactory() {
		return ivFc;
	}


	/**
	 * Defines the factory of messages
	 * @param the new factory of messages
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setMessageFactory(MessageFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change message factory dynamically");
		else {
			msFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of messages.
	 * @return a reference to the factory of messages.
	 */
	public static MessageFc getMessageFactory() {
		return msFc;
	}


	/**
	 * Defines the factory of protocols
	 * @param the new factory of protocols
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setProtocolFactory(ProtocolFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change message factory dynamically");
		else {
			ptFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of protocols.
	 * @return a reference to the factory of protocols.
	 */
	public static ProtocolFc getProtocolFactory() {
		return ptFc;
	}


	/**
	 * Defines the factory of remote references
	 * @param the new factory of remote references
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setRemoteRefFactory(RemoteRefFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change remote reference factory dynamically");
		else {
			rrFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of remote references.
	 * @return a reference to the factory of remote references.
	 */
	public static RemoteRefFc getRemoteRefFactory() {
		return rrFc;
	}


	/**
	 * Defines the factory of schedulers
	 * @param the new factory of schedulers
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setSchedulerFactory(SchedulerFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change scheduler factory dynamically");
		else {
			scFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of schedulers.
	 * @return a reference to the factory of schedulers.
	 */
	public static SchedulerFc getSchedulerFactory() {
		return scFc;
	}


	/**
	 * Defines the factory of service handlers
	 * @param the new factory of service handlers
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setServiceHandlerFactory(ServiceHandlerFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change connector factory dynamically");
		else {
			shFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of service handlers.
	 * @return a reference to the factory of service handlers.
	 */
	public static ServiceHandlerFc getServiceHandlerFactory() {
		return shFc;
	}


	/**
	 * Defines the factory of streams
	 * @param the new factory of streams
	 * @return ReconfigurationException in case the ORB is not open for
	 * futher reconfigurations.
	 */
	public static void setStreamFactory(StreamFc fc) throws ReconfigurationException {
		if(!ORB.isOpenForReconfiguration())
			throw new ReconfigurationException("Attempt to change stream factory dynamically");
		else {
			smFc = fc;
		}
	}

	/**
	 * Return a reference to the factory of streams.
	 * @return a reference to the factory of streams.
	 */
	public static StreamFc getStreamFactory() {
		return smFc;
	}


	/**
	 * The private constructor forbbids the instantiation of this class.
	 */
	private  ORB() {
	}

	/**
	 * After this method is called on the ORB, it is not more possible to
	 * reconfigure its components. Every new attempt to change any of its
	 * factories will throw an exception of the type
	 * <CODE>ReconfigurationException</CODE>.
	 */
	public static void closeForReconfiguration() {
		isOpenForReconfiguration = false;
	}

	/**
	 * Informs if the ORB can be reconfigured or not. After the method
	 * <CODE>closeForReconfiguration</CODE> is invoker on the ORB, it is not
	 * possible anymore to change any of its factories.
	 * @return a boolean value that is true if the ORB can be reconfigured.
	 */
	public static boolean isOpenForReconfiguration() {
		return isOpenForReconfiguration;
	}
}
