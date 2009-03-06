package br.unifor.mia.cd.middleware.rme2.naming;

import br.unifor.mia.cd.middleware.rme2.*;
import br.unifor.mia.cd.middleware.rme2.server.*;
import java.util.*;
import arcademis.*;
import arcademis.server.*;
import arcademis.concreteComponents.*;

/**
 * This class is part of the rme project.
 * Author: Fernando Magno Quintao Pereira.
 * ---------------------------------------
 * This class is a implementation of a name server. The name server
 * stores the location of distributed objects and informs it to requesting
 * clients
 */
public class RmeServiceDirectory_Impl extends RmeRemoteObject implements ClientNamingService, ServerNamingService, RmeConstants {
	private static Hashtable directory = null;

	public RmeServiceDirectory_Impl() {

		// creation of the epid
		HostPortEpid epid = (HostPortEpid)OrbAccessor.getEpid();
		epid.setHostName("localhost");
		epid.setPortNumber(REGISTRY_PORT);

		// creation of the object id
		RmeIdentifierFc idFc = (RmeIdentifierFc)ORB.getIdentifierFactory();
		HostTimeCountId nameServerIdentifier = (HostTimeCountId)idFc.createIdentifier((short)0);

		// creation of the remote reference
		RemoteReference rRef = OrbAccessor.getRemoteRef(epid, nameServerIdentifier);

		super.remoteRef = rRef;
		directory = new Hashtable();
	}

    public Remote lookup(String name) throws NotBoundException {
    	Stub stb = (Stub)directory.get(name);
    	if(stb == null)
    		throw new NotBoundException("The name " + name + " is not currently bound");
    	else if(!isAlive(stb)) {
    		directory.remove(name);
    		throw new NotBoundException("The name " + name + " is not currently bound");
    	}
    	return stb;
    }

	public void bind(String name, Stub obj) throws AlreadyBoundException {
		Stub stb = (Stub)directory.get(name);
		if(stb != null) {
			if(!isAlive(stb))
				directory.remove(name);
			else
				throw new AlreadyBoundException("The name " + name + " is already bound");
		}
		directory.put(name, obj);
	}

	public void unbind(String name) throws NotBoundException {
		Stub stb = (Stub)directory.remove(name);
		if(stb == null)
			throw new NotBoundException("The name " + name + " is not currently bound");
		else
			directory.remove(name);
	}

	public void rebind(String name, Stub obj) {
		directory.put(name, obj);
	}

	public String[] list() {
		Vector v = new Vector();

		Enumeration enumAux = directory.keys();

		if(!enumAux.hasMoreElements())
			return null;

		while(enumAux.hasMoreElements())
			v.addElement(enumAux.nextElement());

		String list[] = new String[v.size()];

		for(int c = 0; c < v.size(); c++)
			list[c] = (String)(v.elementAt(c));

		return list;
	}

	public Stub getStub() {
		RmeServiceDirectory_Impl_Stub stub = new RmeServiceDirectory_Impl_Stub();
		stub.attach(super.remoteRef);
		return stub;
	}


	/**
	 * Verifies if the remote object referenced by stb is receiving calls.
	 */
	private boolean isAlive(Stub stb) {
		try {
			ChannelVerifier cv = (ChannelVerifier)OrbAccessor.getServiceHandler(RmeServiceHandlerFc.CHANNEL_VERIFIER);
			cv.setProtocol(OrbAccessor.getProtocol());
			Connector c = OrbAccessor.getConnector();
			MultiReference mr = stb.getRemoteReferences();
			Epid epid = mr.nextReference().getEpid();
			c.connect(cv, epid);
			return cv.isOpen();
		} catch (NetworkException e) {
			return false;
		}
	}
}
