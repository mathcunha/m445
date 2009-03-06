package rme.server;

import arcademis.Marshalable;
import arcademis.OrbAccessor;
import arcademis.RemoteReference;
import arcademis.Stub;
import arcademis.concreteComponents.HostPortEpid;
import arcademis.server.ActivationException;
import arcademis.server.Activator;
import arcademis.server.Active;
import arcademis.server.RemoteObject;
import arcademis.server.Skeleton;

/**
 * This class
 */
public class RmeRemoteObject extends RemoteObject implements Active, Marshalable {

	/**
	 * The entity responsible for the remote object initialization.
	 */
	private Activator activator = null;

    public void activate() throws ActivationException {
    	this.activator = OrbAccessor.getActivator();
    	this.activator.setRemoteObject(this);
    	this.activator.activate();
    }

	/**
	 * This constructor defines a remote reference for this object.
	 */
	public RmeRemoteObject() {
		java.net.InetAddress a = null;
		try {
			a = java.net.InetAddress.getLocalHost();
		} catch (java.net.UnknownHostException e) {
			e.printStackTrace();
		}
		HostPortEpid epid = (HostPortEpid)OrbAccessor.getEpid();
		epid.setHostName(a.getHostAddress());
		epid.assignRandomPortNumber();
		RemoteReference rRef = OrbAccessor.getRemoteRef(epid);
		super.setRef(rRef);
	}

    public void deactivate() throws ActivationException {
		this.activator.deactivate();
    }

	public Stub getStub() {
		String stubClassName = this.getClass().getName();
		stubClassName += "_Stub";
		Stub stub = null;
		try {
			Class stubClass = Class.forName(stubClassName);
			stub = (Stub)stubClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		stub.attach(super.remoteRef);
		return stub;
	}

	public Skeleton getSkeleton() {
		String skelName = this.getClass().getName();
		skelName = skelName + "_Skeleton";
		Skeleton skel = null;
		try {
			Class skelClass = Class.forName(skelName);
			skel = (Skeleton)skelClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		skel.setRemoteObject(this);
		return skel;
	}

}