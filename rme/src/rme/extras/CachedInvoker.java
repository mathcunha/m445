package rme.extras;

import java.util.*;

import rme.*;
import arcademis.*;

/**
 * This decorator augments the invoker with a cache. Calls are stored in the
 * cache so that, if necessary, its result can be passed directly to the client
 * without the necessity of performing a remote method invocation.
 */
public class CachedInvoker extends InvokerDecorator {

	private Hashtable table = null;

	public CachedInvoker(Invoker invoker) {
		super(invoker);
		table = new Hashtable();
	}

	/**
	 * The remote invocation occurs only if the cache misses.
	 */
	public Stream invoke(RemoteCall c, RemoteReference ref) throws NetworkException {
		Stream args = ((RmeRemoteCall)c).getArguments();
		if(table.containsKey(args)) {
			Stream returnValue = OrbAccessor.getStream();
			returnValue.fill((RmeStream)table.get(args));
			return returnValue;
		}
		else {
			Stream returnValue = super.invoker.invoke(c, ref);
			Stream backupValue = OrbAccessor.getStream();
			backupValue.fill(returnValue);
			table.put(args, backupValue);
			return returnValue;
		}
	}
}
