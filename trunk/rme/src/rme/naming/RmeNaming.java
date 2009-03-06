package rme.naming;

import rme.*;
import arcademis.*;
import arcademis.server.*;
import arcademis.concreteComponents.*;

public final class RmeNaming implements RmeConstants {
	private static RmeServiceDirectory_Impl_Stub nameStub = null;
	private static HostTimeCountId nameServerIdentifier = null;


	static {
		// creation of the epid
		HostPortEpid epid = (HostPortEpid)OrbAccessor.getEpid();
		epid.setHostName("localhost");
		epid.setPortNumber(REGISTRY_PORT);

		// creation of the object id
		RmeIdentifierFc idFc = (RmeIdentifierFc)ORB.getIdentifierFactory();
		nameServerIdentifier = (HostTimeCountId)idFc.createIdentifier((short)0);

		// creation of the remote reference
		RemoteReference rRef = OrbAccessor.getRemoteRef(epid, nameServerIdentifier);
		nameStub = new RmeServiceDirectory_Impl_Stub();
		nameStub.attach(rRef);
	}


    /**
     * Disallow anyone from creating one of these
     */
	private RmeNaming(){}


	/**
     * Returns a stub for the remote object associated with the specified
     * <code>name</code>.
     * @param name the name parameter can be a single object identifier, eg.
     * "phone_list", or it can be preceded by an IP address specifieing a
     * remote host, eg. "algol.dcc.ufmg.br/phone_list".
	 */
    public static Remote lookup(String uri)
             throws ArcademisException,
                    MalformedURLException,
                    NotBoundException
	{
		String name = setTarget(uri);
    	return nameStub.lookup(name);
    }


	public static void bind(String uri, RemoteObject obj)
			throws ArcademisException,
					MalformedURLException,
					AlreadyBoundException
	{
		// parses the URL
		String name = setTarget(uri);
		Stub stub = null;
		try {
			stub = obj.getStub();
		} catch(Exception e) {
			throw new ArcademisException("Error generating stub in RmeNaming.rebind");
		}
		nameStub.bind(name, stub);
	}


	public static void unbind(String uri)
             throws ArcademisException,
                    MalformedURLException,
                    NotBoundException
	{
		String name = setTarget(uri);
		nameStub.unbind(name);
    }


	public static void rebind(String uri, RemoteObject obj)
             throws ArcademisException,
                    MalformedURLException
	{
		String name = setTarget(uri);
		Stub stub = null;
		try {
			stub = obj.getStub();
		} catch(Exception e) {
			throw new ArcademisException("Error generating stub in RmeNaming.rebind");
		}
        nameStub.rebind(name, stub);
    }


	public static String[] list(String uri)
             throws ArcademisException,
                    MalformedURLException
	{
		setTarget(uri);
		return nameStub.list();
    }

	private static String setTarget(String uri) throws MalformedURLException {
		ParsedURL url = new ParsedURL(uri);
		if(url.getHost() != null) {
			String newHost = url.getHost();
			MultiReference mr = nameStub.getRemoteReferences();
			RemoteReference ref = mr.nextReference();
			HostPortEpid newEpid = (HostPortEpid)ref.getEpid();
			newEpid.setHostName(newHost);
		}
		return url.getName();
	}
}