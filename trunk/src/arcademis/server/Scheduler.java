package arcademis.server;

import arcademis.*;

/**
 * Arcademis supports the establishment of priorities among remote calls. 
 * The <CODE>Scheduler</CODE> is the component of the framework in charge of applying such 
 * priorities. Three possible priority policies, from the simplest to the most 
 * complex, are: the assignment of priorities to remote methods, the assignment of 
 * priorities to clients and the  assignment of priorities to servers' end points.
 * In the last case, it is assumed that servers may receive request in more than
 * one endpoint. Besides changing the scheduler, the implementation of some
 * priority policies also requires changes in other components. For example, in
 * order to assign each method a different priority, it is necessary to modify the
 * implementation of stubs.
 */
public abstract class Scheduler {

	private Skeleton skel = null;

	/**
	 * This method inserts the remote call into a queue where it will stay until
	 * it is possible to send it to be executed by the actual implementation of
	 * the remote object.
	 * @param remoteCall the call to be processed.
	 */
	public abstract void Schedule(RemoteCall remoteCall);


	/**
	 * This method defines the skeleton bound to this scheduler.
	 * @param skel an object of the <CODE>Skeleton</CODE> type.
	 */
	public void setSkeleton(Skeleton skel) {
		this.skel = skel;
	}


	/**
	 * Returns a reference to the skeleton bound to this scheduler.
	 * @return an object of the <CODE>Skeleton</CODE> type.
	 */
	public Skeleton getSkeleton() {
		return this.skel;
	}
}