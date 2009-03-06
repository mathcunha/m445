package rme.server;

import arcademis.server.*;

public class RmeDispatcherFc implements DispatcherFc {

	private RmeRemoteObject remoteObj = null;

	private boolean log = false;

	private boolean report = false;

	public Dispatcher createDispatcher() {
		Dispatcher d = new RmeDispatcher();
		try {
			((RmeDispatcher)d).setSkeleton(remoteObj.getSkeleton());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		if(log)
			d = new rme.extras.server.LogDispatcher(d);
		if(report)
			d = new rme.extras.server.ReportLoadDispatcher(d);
		return d;
	}

	public Dispatcher createDispatcher(int objType) {
		Dispatcher d = new RmeDispatcher();
		try {
			((RmeDispatcher)d).setSkeleton(remoteObj.getSkeleton());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		if(log)
			d = new rme.extras.server.LogDispatcher(d);
		if(report)
			d = new rme.extras.server.ReportLoadDispatcher(d);
		return d;
	}

	public void setRemoteObject(RmeRemoteObject remoteObj) {
		this.remoteObj = remoteObj;
	}
}
