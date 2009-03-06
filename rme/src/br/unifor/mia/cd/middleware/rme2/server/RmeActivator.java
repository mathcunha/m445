package br.unifor.mia.cd.middleware.rme2.server;

import arcademis.ArcademisConstants;
import arcademis.ORB;
import arcademis.OrbAccessor;
import arcademis.RemoteReference;
import arcademis.concreteComponents.BlockingAcceptor;
import arcademis.concreteComponents.HostPortEpid;
import arcademis.server.ActivationException;
import arcademis.server.Activator;
import arcademis.server.Dispatcher;

public class RmeActivator extends Activator implements Runnable {

	/**
	 * True while the remote object is active.
	 */
	private boolean isActive = false;

	/**
	 * The component responsible for passing remote call to the actual
	 * implementation of the remote object.
	 */
	private Dispatcher dispatcher = null;

	/**
	 * This method makes the remote object able to receive remote calls. In order
	 * to receive remote invocations, an object needs to create an acceptor to
	 * listen in a know point given by the pair <CODE>host name</CODE> and
	 * <CODE>port number</CODE>. In addition to this, the object need to generate
	 * an skeleton that will forward to the real object implementation the
	 * incomming calls.
	 * This method also fills the remote reference of the remote object. In order
	 * to do this, the <CODE>Rme</CODE>
	 * implementation gives the object a port number and the ip address of the
	 * host machine. Just after been instantiated, the remote object is not yet
	 * able to receive remote calls. In order to allow it to receive them, the
	 * method <CODE>activateObject</CODE> must be invoked on it.
	 */
	public void activate() throws ActivationException {
		// if the remote object has not a remote reference a new one must be created.
		if(super.remoteObject.getRef() == null) {
			java.net.InetAddress a = null;
			try {
				a = java.net.InetAddress.getLocalHost();
			} catch (java.net.UnknownHostException e) {
				throw new ActivationException("It was not possible to get a end point to this object");
			}
			HostPortEpid epid = (HostPortEpid)OrbAccessor.getEpid();
			epid.setHostName(a.getHostAddress());
			epid.assignRandomPortNumber();
			RemoteReference rRef = OrbAccessor.getRemoteRef(epid);
			super.remoteObject.setRef(rRef);
		}

		// creation of a dispatcher for the remote object
		RmeDispatcherFc dpFc = (RmeDispatcherFc)ORB.getDispatcherFactory();
		dpFc.setRemoteObject((RmeRemoteObject)super.remoteObject);
		this.dispatcher = dpFc.createDispatcher();

		// runs the initializer in a independent thread
		Thread t = new Thread(this);
		t.start();
	}


	public void run() {
		isActive = true;
		BlockingAcceptor acc = (BlockingAcceptor)OrbAccessor.getAcceptor(super.remoteObject.getRef().getEpid());
		try {
			while(isActive) {
				RmeRequestReceiver r = (RmeRequestReceiver)OrbAccessor.getServiceHandler(ArcademisConstants.REQUEST_RECEIVER);
				r.setProtocol(OrbAccessor.getProtocol());
				r.setDispatcher(this.dispatcher);
				acc.accept(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Interrupts the processing of the thread responsible for receiving the
	 * remote calls.
	 */
	public void deactivate() {
		isActive = false;
	}
}
