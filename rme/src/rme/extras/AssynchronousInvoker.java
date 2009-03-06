package rme.extras;

import arcademis.ArcademisException;
import arcademis.Invoker;
import arcademis.InvokerDecorator;
import arcademis.Marshalable;
import arcademis.OrbAccessor;
import arcademis.RemoteCall;
import arcademis.RemoteReference;
import arcademis.Stream;

/**
 * This class creates a separate thread to perform the remote method
 * invocation, so that the application does not have to wait for the result of
 * the call. This strategy only applies to one-way calls.
 */
public class AssynchronousInvoker extends InvokerDecorator {

	public AssynchronousInvoker(Invoker invoker) {
		super(invoker);
	}

	/**
	 * The remote invocation occurs only if the cache misses.
	 */
	public Stream invoke(RemoteCall remoteCall, RemoteReference ref) {
		Caller c = new Caller(super.invoker, remoteCall, ref);
		Thread t = new Thread(c);
		Stream s = OrbAccessor.getStream();
		try {
			t.start();
			s.write((Marshalable)null);
		} catch (ArcademisException ae) {
			ae.printStackTrace();
		}
		return s;
	}

	private class Caller implements Runnable {
		private Invoker internalInvoker = null;
		private RemoteCall internalCall = null;
		private RemoteReference internalRef = null;

		public Caller(Invoker i, RemoteCall c, RemoteReference r) {
			internalInvoker = i;
			internalCall = c;
			internalRef = r;
		}

		public void run() {
			try {
				internalInvoker.invoke(internalCall, internalRef);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
