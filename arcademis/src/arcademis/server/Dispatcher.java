package arcademis.server;

import arcademis.*;

/**
 * The Dispatcher is one of the entities that determine one of the main aspects
 * of the middleware platform. It determines the
 * general structure of the server. For instance, the dispatcher determines if
 * remote call requests will be delivered directly to the skeleton and then to
 * the remote object, or if they will be inserted into a queue and then
 * processed by a scheduler. The dispatcher also determines how the server
 * handles exceptions during remote method processing and the thread policies
 * adopted by the server.
 */
public abstract class Dispatcher {

	private Scheduler scheduler = null;
	
	private Skeleton skeleton = null;

	/**
	 * This method receives a remote call and assures it will be passed to the
	 * implementation of the remote object.
	 * @param remoteCall the call to be processed.
	 * @return the value obtained after the processing of the call.
	 */
	public abstract Stream dispatch(RemoteCall remoteCall);


	/**
	 * This method defines the skeleton bound to this scheduler.
	 * @param skel an object of the <CODE>Skeleton</CODE> type.
	 */
	public void setSkeleton(Skeleton skel) {
		this.skeleton = skel;
	}


	/**
	 * Returns a reference to the skeleton bound to this scheduler.
	 * @return an object of the <CODE>Skeleton</CODE> type.
	 */
	public Skeleton getSkeleton() {
		return this.skeleton;
	}


	/**
	 * This method defines the scheduler bound to this dispatcher.
	 * @param scheduler an object of the <CODE>Scheduler</CODE> type.
	 */
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}


	/**
	 * Returns a reference to the scheduler bound to this dispatcher.
	 * @return an object of the <CODE>Scheduler</CODE> type.
	 */
	public Scheduler getScheduler() {
		return this.scheduler;
	}
}