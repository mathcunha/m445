package br.unifor.mia.cd.middleware.rme2;

import arcademis.*;
import br.unifor.mia.cd.middleware.rme2.rmep.*;

/**
 * This invoker is used for carring on remote method invocations that need
 * confirmation from the server. The confirmation is the return value itself or
 * just a replay if the method does not originate a return value. In addition
 * to this, this invoker tries to reuse the communication channel whenever
 * possible.
 */
public class TwoWayInvoker implements Invoker {

	private Channel ch = null;

	private long lastConnectionTime = System.currentTimeMillis();

	/**
	 * In order to enhance the efficience of remote method invocation, before
	 * making a new connection with the remote server, the stub looks for an older
	 * connection and trys to reuse it. If a time greater than
	 * <CODE>CHECK_TIME</CODE> has passed since the last connection, then a ping is
	 * sent through the old channel in order to test if it is working until the
	 * present time.
	 */
	public static final long CHECK_TIME = (long)RmeConstants.TOLERANCE_TIME/2;

	/**
	 * This method performs the invocation of a remote operation. In order to
	 * enhance the efficience of the remote call, an attempt to reuse an old
	 * connection is made. A ping is sent through the connection and if a
	 * response is obtained the old connection can be reused, otherwise a
	 * new connection will be created.
	 * @param remoteCall the remote call to be performed.
	 * @param ref the reference to the object that will be responsible for
	 * carring on the remote invocation.
	 * @return a <CODE>Stream</CODE> containing the return value serialized.
	 * @throws NetworkException if something goes wrong during the remote
	 * invocation.
	 */
	public Stream invoke(RemoteCall remoteCall, RemoteReference ref) throws NetworkException {
		RmeRequestSender rs = getSender(remoteCall);
		if(this.ch != null) {
			if(timeHasExpired()) {
				if(isAlive(this.ch)) {
					rs.open(this.ch);
				} else {
					this.ch.close();
					createConnection(rs, ref);
				}
			} else {
				rs.open(this.ch);
			}
		} else {
			createConnection(rs, ref);
		}
		return rs.getFuture();
	}

	public Stream invoke(RemoteCall remoteCall, MultiReference mr) throws NetworkException {
		return null;
	}

	/**
	 * Constructs a request sender for the given remote call
	 */
	private RmeRequestSender getSender(RemoteCall remoteCall) {
		RmeRequestSender rs = (RmeRequestSender)OrbAccessor.
			getServiceHandler(ArcademisConstants.REQUEST_SENDER);
		RmeProtocol rmep = (RmeProtocol)OrbAccessor.getProtocol();
		rs.setProtocol(rmep);

		rs.setRemoteCall((RmeRemoteCall)remoteCall);
		return rs;
	}

	/**
	 * Tests if there is a server entity listening for messages in the other
	 * side of the channel. This test is performed by means of a ping message.
	 * If such message originates a replay, then it is possible to conclude
	 * that it is possible to use the channel.
	 */
	private boolean isAlive(Channel ch) {
		ChannelVerifier cv = (ChannelVerifier)OrbAccessor.
		getServiceHandler(ArcademisConstants.CHANNEL_VERIFIER);
		cv.setProtocol(OrbAccessor.getProtocol());
		cv.open(ch);
		return cv.isOpen();
	}

	private void createConnection(RmeRequestSender rs, RemoteReference ref)
	throws NetworkException {
		// stablishment of the connection
		ConnectorFc fc = ORB.getConnectorFactory();
		Connector c = fc.createConnector();
		Epid epid = ref.getEpid();
		c.connect(rs, epid);
		this.ch = rs.getChannel();
	}

	private boolean timeHasExpired() {
		long presentDate = System.currentTimeMillis();
		boolean timeHasExpired = false;

		if((presentDate - lastConnectionTime) > CHECK_TIME) {
			timeHasExpired = true;
		}
		lastConnectionTime = presentDate;

		return timeHasExpired;
	}
}
