package rme.extras.server;

import rme.RmeRemoteCall;
import arcademis.MarshalException;
import arcademis.OrbAccessor;
import arcademis.RemoteCall;
import arcademis.Stream;
import arcademis.server.Dispatcher;
import arcademis.server.DispatcherDecorator;

/**
 * This dispatcher decorator makes a record of the number of requisitions
 * received in its previous history and reports it to the client whenever
 * asked.
 */
public class ReportLoadDispatcher extends DispatcherDecorator {

	private long oldTime = 0;
	private double oldAv = .0;
	private int oldN = 0;

	/**
	 * Constructor method of the report dispatcher decorator. It associates the
	 * decorated dispatcher with this one.
	 * @param dispatcher the decorated dispatcher.
	 */
	public ReportLoadDispatcher(Dispatcher dispatcher) {
		super(dispatcher);
		this.oldTime = System.currentTimeMillis();
		this.oldAv = .0;
		this.oldN = 0;
	}

	/**
	 * If the operation code of the call to be processed is smaller than zero,
	 * the dispatcher assumes the client is asking for its load.
	 * @param c the remote call to be processed.
	 * @return a stream holding the return value of the call.
	 */
	public Stream dispatch(RemoteCall c) {
		long load = this.updateLoad();
		System.out.println("Current load = " + load);
		if(c instanceof RmeRemoteCall)
			if( ((RmeRemoteCall)c).getOperationCode() < 0 ) {
				Stream returnValue = OrbAccessor.getStream();
				try {returnValue.write(load);} catch (MarshalException e) {}
				return returnValue;
			}
		return super.dispatcher.dispatch(c);
	}

	private long updateLoad() {
		long currentTime = System.currentTimeMillis();
		long timeInterval = currentTime - this.oldTime;
		this.oldAv = (this.oldAv * this.oldN + timeInterval) / (this.oldN + 1);
		this.oldTime = currentTime;
		this.oldN++;
		return (long)this.oldAv;
	}
}