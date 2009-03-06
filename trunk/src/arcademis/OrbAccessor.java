package arcademis;

import arcademis.server.*;

/**
 * The <CODE>OrbAccessor</CODE> works as a facade to the ORB, and its object is
 * to protect the middleware developer from the messy sintax that is related to
 * the creation of factories and instantiation of objects.
 */
public class OrbAccessor {

	/**
	 * This method returns an instance of the acceptor component.
	 * @return an object of the <CODE>Acceptor</CODE> type.
	 * @param epid the address to which the new acceptor will be bound.
	 */
    public static Acceptor getAcceptor(Epid epid) {
        AcceptorFc fc = ORB.getAcceptorFactory();
        return fc.createAcceptor(epid);
    }

	/**
	 * This method returns an instance of the activator component.
	 * @return an object of the <CODE>Activator</CODE> type.
	 */
    public static Activator getActivator() {
        ActivatorFc fc = ORB.getActivatorFactory();
        return fc.createActivator();
    }

	/**
	 * This method returns an instance of buffer.
	 * @return an object of the <CODE>Buffer</CODE> type.
	 */
    public static Buffer getBuffer() {
        BufferFc fc = ORB.getBufferFactory();
        return fc.createBuffer();
    }

	/**
	 * This method returns an instance of the Channel component.
	 * @return an object of the <CODE>Channel</CODE> type.
	 */
    public static Channel getChannel() {
        ChannelFc fc = ORB.getChannelFactory();
        return fc.createChannel();
    }

	/**
	 * This method returns an instance of connection server.
	 * @return an object of the <CODE>ConnectionServer</CODE> type.
	 * @param epid the address where the new connection server will wait for
	 * incoming connections.
	 */
    public static ConnectionServer getConnectionServer(Epid epid) {
        ConnectionServerFc fc = ORB.getConnectionServerFactory();
        return fc.createConnectionServer(epid);
    }

	/**
	 * This method returns an instance of connector.
	 * @return an object of the <CODE>Connector</CODE> type.
	 */
    public static Connector getConnector() {
        ConnectorFc fc = ORB.getConnectorFactory();
        return fc.createConnector();
    }

	/**
	 * This method returns an instance of dispatcher.
	 * @return an object of the <CODE>Dispatcher</CODE> type.
	 */
    public static Dispatcher getDispatcher() {
        DispatcherFc fc = ORB.getDispatcherFactory();
        return fc.createDispatcher();
    }

	/**
	 * This method returns an instance of end point identifier.
	 * @return an object of the <CODE>Epid</CODE> type.
	 */
    public static Epid getEpid() {
        EpidFc fc = ORB.getEpidFactory();
        return fc.createEpid();
    }

	/**
	 * This method returns an instance of unique identifier.
	 * @return an object of the <CODE>Identifier</CODE> type.
	 */
    public static Identifier getIdentifier() {
        IdentifierFc fc = ORB.getIdentifierFactory();
        return fc.createIdentifier();
    }

	/**
	 * This method returns an instance of invoker.
	 * @return an object of the <CODE>Invoker</CODE> type.
	 */
    public static Invoker getInvoker() {
        InvokerFc fc = ORB.getInvokerFactory();
        return fc.createInvoker();
    }

	/**
	 * This method returns an instance of invoker.
	 * @param invType the strategy that the invoker factory will use to determine
	 * the type of invoker that should be returned.
	 * @return an object of the <CODE>Invoker</CODE> type.
	 */
    public static Invoker getInvoker(int invType) {
        InvokerFc fc = ORB.getInvokerFactory();
        return fc.createInvoker(invType);
    }

	/**
	 * This method returns an instance of message.
	 * @return an object of the <CODE>Message</CODE> type.
	 * @param msgType the type of message that will be created.
	 */
    public static Message getMessage(int msgType) {
        MessageFc fc = ORB.getMessageFactory();
        return fc.createMessage(msgType);
    }

	/**
	 * This method returns an instance of protocol.
	 * @return an object of the <CODE>Protocol</CODE> type.
	 */
    public static Protocol getProtocol() {
        ProtocolFc fc = ORB.getProtocolFactory();
        return fc.createProtocol();
    }

	/**
	 * This method returns an instance of multi reference.
	 * @return an object of the <CODE>MultiReference</CODE> type.
	 */
    public static MultiReference getMultiRef() {
        RemoteRefFc fc = ORB.getRemoteRefFactory();
        return fc.createMultiRef();
    }


	/**
	 * This method returns an instance of remote reference.
	 * @return an object of the <CODE>RemoteReference</CODE> type.
	 */
    public static RemoteReference getRemoteRef() {
        RemoteRefFc fc = ORB.getRemoteRefFactory();
        return fc.createRemoteRef();
    }

	/**
	 * This method returns an instance of remote reference.
	 * @return an object of the <CODE>RemoteReference</CODE> type.
	 * @param epid the address to which the new remote reference will be bound.
	 */
    public static RemoteReference getRemoteRef(Epid epid) {
        RemoteRefFc fc = ORB.getRemoteRefFactory();
        return fc.createRemoteRef(epid);
    }

	/**
	 * This method returns an instance of remote reference.
	 * @return an object of the <CODE>RemoteReference</CODE> type.
	 * @param epid the address to which the new remote reference will be bound.
	 * @param id the identifier of the new remote reference.
	 */
    public static RemoteReference getRemoteRef(Epid epid, Identifier id) {
        RemoteRefFc fc = ORB.getRemoteRefFactory();
        return fc.createRemoteRef(epid, id);
    }

	/**
	 * This method returns an instance of scheduler.
	 * @return an object of the <CODE>Scheduler</CODE> type.
	 */
    public static Scheduler getScheduler() {
        SchedulerFc fc = ORB.getSchedulerFactory();
        return fc.createScheduler();
    }

	/**
	 * This method returns an instance of service handler.
	 * @return an object of the <CODE>ServiceHandler</CODE> type.
	 * @param type the type of service handler that will be generated.
	 */
	public static ServiceHandler getServiceHandler(int type) {
		ServiceHandlerFc fc = ORB.getServiceHandlerFactory();
		return fc.createServiceHandler(type);
	}

	/**
	 * This method returns an instance of stream.
	 * @return an object of the <CODE>Stream</CODE> type.
	 */
    public static Stream getStream() {
        StreamFc fc = ORB.getStreamFactory();
        return fc.createStream();
    }
}
